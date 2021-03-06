/*
 * Copyright 2010-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 *    http://aws.amazon.com/apache2.0
 *
 * This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and
 * limitations under the License.
 */

package com.amazonaws.mobileconnectors.kinesis.kinesisrecorder.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import com.amazonaws.mobileconnectors.kinesis.kinesisrecorder.KinesisRecorderConfig;
import com.amazonaws.mobileconnectors.kinesis.kinesisrecorder.internal.FileRecordStore.RecordIterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = Config.NONE)
public class FileRecordStoreTest {

    private static final File TEST_DIRECTORY = new File("FileRecordStoreTest");

    @Before
    public void setup() {
        if (TEST_DIRECTORY.exists()) {
            for (File topLevel : TEST_DIRECTORY.listFiles()) {
                for (File file : topLevel.listFiles()) {
                    assertTrue(file.delete());
                }
                assertTrue(topLevel.delete());
            }
            assertTrue(TEST_DIRECTORY.delete());
        }

        assertTrue(TEST_DIRECTORY.mkdir());
    }

    @After
    public void teardown() {
        if (TEST_DIRECTORY.exists()) {
            for (File topLevel : TEST_DIRECTORY.listFiles()) {
                for (File file : topLevel.listFiles()) {
                    assertTrue(file.delete());
                }
                assertTrue(topLevel.delete());
            }
            assertTrue(TEST_DIRECTORY.delete());
        }
    }

    @Test
    public void testFileRecordStore_putIterateAndRemove() throws IOException {

        FileManager fileManager = new FileManager(TEST_DIRECTORY);
        FileRecordStore recordStore = new FileRecordStore(TEST_DIRECTORY,
                new KinesisRecorderConfig());

        // Put some events into the store
        recordStore.put("1");
        recordStore.put("2");
        recordStore.put("3");
        recordStore.put("4");
        recordStore.put("5");
        recordStore.put("6");
        recordStore.put("7");
        recordStore.put("8");
        recordStore.put("9");
        recordStore.put("10");

        // Use the iterator to read through the events in the store
        int counter = 0;
        RecordIterator iter = recordStore.iterator();

        while (iter.hasNext()) {
            counter++;
            String record = iter.next();
            assertSame(Integer.valueOf(record), counter);
            // If we read 5 events remove the 5 last read events
            if (counter % 5 == 0) {
                iter.removeReadRecords();
                assertSame(getNumberOfLinesInFile(fileManager), (10 - counter));
            }
        }

        // Put some events into the store
        recordStore.put("1");
        recordStore.put("2");
        recordStore.put("3");
        recordStore.put("4");
        recordStore.put("5");
        recordStore.put("6");
        recordStore.put("7");
        recordStore.put("8");
        recordStore.put("9");
        recordStore.put("10");
        String nextRecord = null;
        counter = 0;
        iter = recordStore.iterator();
        while ((nextRecord = iter.next()) != null) {
            counter++;
            assertSame(Integer.valueOf(nextRecord), counter);
        }
        iter.removeReadRecords();
        assertSame(getNumberOfLinesInFile(fileManager), 0);

        // Try getting a new iterator while the store is empty and verify
        // that hasNext is false
        iter = recordStore.iterator();
        assertFalse(iter.hasNext());
        assertNull(iter.next());

        // Put another record in the store and try to read it back out with a
        // new iterator.
        recordStore.put("11");
        iter = recordStore.iterator();

        // Try peeking and the next record more than once and verify it will
        // only show the next record each time
        assertTrue(iter.peek().equalsIgnoreCase("11"));
        assertTrue(iter.peek().equalsIgnoreCase("11"));

        // Try calling remove and get an UnsupportedOperationException
        boolean unsupportedCaught = false;
        try {
            iter.remove();
        } catch (UnsupportedOperationException e) {
            unsupportedCaught = true;
        }
        assertTrue(unsupportedCaught);

        while (iter.hasNext()) {
            String record = iter.next();
            assertSame(Integer.valueOf(record), 11);
        }

    }

    @Test
    public void
            testWhenRecordssFileIsMissingAndRecordssDirectoryIsReadOnly_error() throws IOException {
        File kinesisDirectory = null;
        try {
            File eventDirectory = null;

            FileManager fileManager = new FileManager(TEST_DIRECTORY);
            FileRecordStore recordStore = new FileRecordStore(TEST_DIRECTORY,
                    new KinesisRecorderConfig());

            kinesisDirectory =
                    fileManager.createDirectory(Constants.RECORDS_DIRECTORY);
            File recordsFile = new File(eventDirectory, Constants.RECORDS_FILE_NAME);

            fileManager.deleteFile(recordsFile);
            kinesisDirectory.setReadOnly();
            recordStore.put("2");
        } finally {
            if (kinesisDirectory != null && kinesisDirectory.exists()) {

                kinesisDirectory.setWritable(true);
                kinesisDirectory.delete();
            }
        }

    }

    @Test
    public void testWhenWritingExceedsMaxStorageSize_noMoreRecordsWritten() throws IOException {
        File recordsDirectory = null;
        File recordsFile = null;

        KinesisRecorderConfig config = new KinesisRecorderConfig().withMaxStorageSize(100L);
        FileManager fileManager = new FileManager(TEST_DIRECTORY);
        FileRecordStore recordStore = new FileRecordStore(TEST_DIRECTORY,
                config);

        recordsDirectory = fileManager.getDirectory(Constants.RECORDS_DIRECTORY);
        recordsFile = new File(recordsDirectory, Constants.RECORDS_FILE_NAME);

        for (int i = 0; i < 10; i++) {
            recordStore.put("ten bytes");
        }
        assertSame(recordsFile.length(), 100L);

        recordStore.put("0123456789");
        assertSame(recordsFile.length(), 100L);

    }

    @Test
    public void testWhenWritingTooManyConcurrentRecords() throws
            InterruptedException, IOException {
        File recordsDirectory = null;
        File recordsFile = null;
        KinesisRecorderConfig config = new KinesisRecorderConfig();
        FileManager fileManager = new FileManager(TEST_DIRECTORY);
        final FileRecordStore recordStore = new FileRecordStore(TEST_DIRECTORY,
                config);

        recordsDirectory = fileManager.getDirectory(Constants.RECORDS_DIRECTORY);
        recordsFile = new File(recordsDirectory, Constants.RECORDS_FILE_NAME);

        SecureRandom random = new SecureRandom();

        // first fill the disk
        String tempRecordStr = "";
        for (int i = 0; i < 10000; i++) {
            tempRecordStr = tempRecordStr + new BigInteger(130, random).toString(32);
        }

        final String recordStr = tempRecordStr;

        for (int i = 0; i < 30; i++) {
            recordStore.put(recordStr);
        }

        long initialSize = recordsFile.length();
        assertTrue(recordsFile.length() <= config.getMaxStorageSize());

        final CountDownLatch latch = new CountDownLatch(1);
        ExecutorService threadPool = Executors.newFixedThreadPool(1);
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 100; i++) {
                        RecordIterator itr = recordStore.iterator();
                        if (itr.hasNext()) {
                            String next = itr.next();
                            assertEquals(next.length(), recordStr.length());
                            itr.removeReadRecords();
                        }
                        Thread.sleep(1);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    latch.countDown();
                }
            }
        });

        for (int i = 0; i < 10000; i++) {
            recordStore.put(recordStr);
            assertTrue(recordsFile.length() <= initialSize);
            recordStore.put(recordStr);
            assertTrue(recordsFile.length() <= initialSize);
            recordStore.put(recordStr);
            assertTrue(recordsFile.length() <= initialSize);
            Thread.sleep(1);
        }

        latch.await();
        assertEquals(recordsFile.length(), initialSize);
        assertTrue(recordsFile.length() < config.getMaxStorageSize());

    }

    @Test
    public void
            testPutEventsFromMultipleThreads_finishesInTimeAndNothingLost() throws IOException {
        KinesisRecorderConfig config = new KinesisRecorderConfig();
        final FileRecordStore recordStore = new FileRecordStore(TEST_DIRECTORY,
                config);

        final Map<Long, Long> threadWrites = new HashMap<Long, Long>();

        final CountDownLatch latch = new CountDownLatch(10000);

        long start = System.currentTimeMillis();
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10000; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Long eventsWritten = threadWrites.get(Thread.currentThread().getId());
                        eventsWritten = (eventsWritten == null) ? 0L : eventsWritten;
                        threadWrites.put(Thread.currentThread().getId(), ++eventsWritten);
                        recordStore.put(String.valueOf(Thread.currentThread().getId()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } finally {
                        latch.countDown();
                    }
                }
            });
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
        }

        long end = System.currentTimeMillis();
        assertFalse(end - start > 10000);

        final Map<Long, Long> actualThreadWrites = new HashMap<Long, Long>();
        RecordIterator iter = recordStore.iterator();
        while (iter.hasNext()) {
            String next = iter.next();
            Long id = Long.valueOf(next);
            Long eventsWritten = actualThreadWrites.get(id);
            eventsWritten = (eventsWritten == null) ? 0L : eventsWritten;
            actualThreadWrites.put(id, ++eventsWritten);
        }

        for (Map.Entry<Long, Long> entry : threadWrites.entrySet()) {
            assertTrue(actualThreadWrites.containsKey(entry.getKey()));
            assertEquals(entry.getValue(), actualThreadWrites.get(entry.getKey()));
        }

    }

    private int getNumberOfLinesInFile(final FileManager fileManager) throws NumberFormatException,
            IOException {
        BufferedReader reader = getRecordsFileReader(fileManager);
        int counter = 0;
        while (reader.readLine() != null) {
            counter++;
        }
        return counter;
    }

    private BufferedReader getRecordsFileReader(final FileManager fileManager) {
        InputStreamReader streamReader = null;
        try {
            streamReader = new InputStreamReader(
                    fileManager.newInputStream(Constants.RECORDS_DIRECTORY + File.separator
                            + Constants.RECORDS_FILE_NAME));
        } catch (FileNotFoundException e) {
        }

        if (streamReader != null) {
            return new BufferedReader(streamReader);
        }
        return null;
    }
}
