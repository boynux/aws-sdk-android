/*
 * Copyright 2010-2015 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 * 
 *  http://aws.amazon.com/apache2.0
 * 
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.amazonaws.services.dynamodbv2.model;

import java.io.Serializable;

/**
 * <p>
 * Represents the DynamoDB Streams configuration for a table in DynamoDB.
 * </p>
 */
public class StreamSpecification implements Serializable {

    /**
     * Indicates whether DynamoDB Streams is enabled (true) or disabled
     * (false) on the table.
     */
    private Boolean streamEnabled;

    /**
     * The DynamoDB Streams settings for the table. These settings consist
     * of: <ul> <li> <p><i>StreamEnabled</i> - Indicates whether DynamoDB
     * Streams is enabled (true) or disabled (false) on the table. </li> <li>
     * <p><i>StreamViewType</i> - When an item in the table is modified,
     * <i>StreamViewType</i> determines what information is written to the
     * stream for this table. Valid values for <i>StreamViewType</i> are:
     * <ul> <li><p><i>KEYS_ONLY</i> - Only the key attributes of the modified
     * item are written to the stream.</li> <li><p><i>NEW_IMAGE</i> - The
     * entire item, as it appears after it was modified, is written to the
     * stream.</li> <li><p><i>OLD_IMAGE</i> - The entire item, as it appeared
     * before it was modified, is written to the stream.</li>
     * <li><p><i>NEW_AND_OLD_IMAGES</i> - Both the new and the old item
     * images of the item are written to the stream.</li> </ul> </li> </ul>
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>NEW_IMAGE, OLD_IMAGE, NEW_AND_OLD_IMAGES, KEYS_ONLY
     */
    private String streamViewType;

    /**
     * Indicates whether DynamoDB Streams is enabled (true) or disabled
     * (false) on the table.
     *
     * @return Indicates whether DynamoDB Streams is enabled (true) or disabled
     *         (false) on the table.
     */
    public Boolean isStreamEnabled() {
        return streamEnabled;
    }
    
    /**
     * Indicates whether DynamoDB Streams is enabled (true) or disabled
     * (false) on the table.
     *
     * @param streamEnabled Indicates whether DynamoDB Streams is enabled (true) or disabled
     *         (false) on the table.
     */
    public void setStreamEnabled(Boolean streamEnabled) {
        this.streamEnabled = streamEnabled;
    }
    
    /**
     * Indicates whether DynamoDB Streams is enabled (true) or disabled
     * (false) on the table.
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     *
     * @param streamEnabled Indicates whether DynamoDB Streams is enabled (true) or disabled
     *         (false) on the table.
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     */
    public StreamSpecification withStreamEnabled(Boolean streamEnabled) {
        this.streamEnabled = streamEnabled;
        return this;
    }

    /**
     * Indicates whether DynamoDB Streams is enabled (true) or disabled
     * (false) on the table.
     *
     * @return Indicates whether DynamoDB Streams is enabled (true) or disabled
     *         (false) on the table.
     */
    public Boolean getStreamEnabled() {
        return streamEnabled;
    }

    /**
     * The DynamoDB Streams settings for the table. These settings consist
     * of: <ul> <li> <p><i>StreamEnabled</i> - Indicates whether DynamoDB
     * Streams is enabled (true) or disabled (false) on the table. </li> <li>
     * <p><i>StreamViewType</i> - When an item in the table is modified,
     * <i>StreamViewType</i> determines what information is written to the
     * stream for this table. Valid values for <i>StreamViewType</i> are:
     * <ul> <li><p><i>KEYS_ONLY</i> - Only the key attributes of the modified
     * item are written to the stream.</li> <li><p><i>NEW_IMAGE</i> - The
     * entire item, as it appears after it was modified, is written to the
     * stream.</li> <li><p><i>OLD_IMAGE</i> - The entire item, as it appeared
     * before it was modified, is written to the stream.</li>
     * <li><p><i>NEW_AND_OLD_IMAGES</i> - Both the new and the old item
     * images of the item are written to the stream.</li> </ul> </li> </ul>
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>NEW_IMAGE, OLD_IMAGE, NEW_AND_OLD_IMAGES, KEYS_ONLY
     *
     * @return The DynamoDB Streams settings for the table. These settings consist
     *         of: <ul> <li> <p><i>StreamEnabled</i> - Indicates whether DynamoDB
     *         Streams is enabled (true) or disabled (false) on the table. </li> <li>
     *         <p><i>StreamViewType</i> - When an item in the table is modified,
     *         <i>StreamViewType</i> determines what information is written to the
     *         stream for this table. Valid values for <i>StreamViewType</i> are:
     *         <ul> <li><p><i>KEYS_ONLY</i> - Only the key attributes of the modified
     *         item are written to the stream.</li> <li><p><i>NEW_IMAGE</i> - The
     *         entire item, as it appears after it was modified, is written to the
     *         stream.</li> <li><p><i>OLD_IMAGE</i> - The entire item, as it appeared
     *         before it was modified, is written to the stream.</li>
     *         <li><p><i>NEW_AND_OLD_IMAGES</i> - Both the new and the old item
     *         images of the item are written to the stream.</li> </ul> </li> </ul>
     *
     * @see StreamViewType
     */
    public String getStreamViewType() {
        return streamViewType;
    }
    
    /**
     * The DynamoDB Streams settings for the table. These settings consist
     * of: <ul> <li> <p><i>StreamEnabled</i> - Indicates whether DynamoDB
     * Streams is enabled (true) or disabled (false) on the table. </li> <li>
     * <p><i>StreamViewType</i> - When an item in the table is modified,
     * <i>StreamViewType</i> determines what information is written to the
     * stream for this table. Valid values for <i>StreamViewType</i> are:
     * <ul> <li><p><i>KEYS_ONLY</i> - Only the key attributes of the modified
     * item are written to the stream.</li> <li><p><i>NEW_IMAGE</i> - The
     * entire item, as it appears after it was modified, is written to the
     * stream.</li> <li><p><i>OLD_IMAGE</i> - The entire item, as it appeared
     * before it was modified, is written to the stream.</li>
     * <li><p><i>NEW_AND_OLD_IMAGES</i> - Both the new and the old item
     * images of the item are written to the stream.</li> </ul> </li> </ul>
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>NEW_IMAGE, OLD_IMAGE, NEW_AND_OLD_IMAGES, KEYS_ONLY
     *
     * @param streamViewType The DynamoDB Streams settings for the table. These settings consist
     *         of: <ul> <li> <p><i>StreamEnabled</i> - Indicates whether DynamoDB
     *         Streams is enabled (true) or disabled (false) on the table. </li> <li>
     *         <p><i>StreamViewType</i> - When an item in the table is modified,
     *         <i>StreamViewType</i> determines what information is written to the
     *         stream for this table. Valid values for <i>StreamViewType</i> are:
     *         <ul> <li><p><i>KEYS_ONLY</i> - Only the key attributes of the modified
     *         item are written to the stream.</li> <li><p><i>NEW_IMAGE</i> - The
     *         entire item, as it appears after it was modified, is written to the
     *         stream.</li> <li><p><i>OLD_IMAGE</i> - The entire item, as it appeared
     *         before it was modified, is written to the stream.</li>
     *         <li><p><i>NEW_AND_OLD_IMAGES</i> - Both the new and the old item
     *         images of the item are written to the stream.</li> </ul> </li> </ul>
     *
     * @see StreamViewType
     */
    public void setStreamViewType(String streamViewType) {
        this.streamViewType = streamViewType;
    }
    
    /**
     * The DynamoDB Streams settings for the table. These settings consist
     * of: <ul> <li> <p><i>StreamEnabled</i> - Indicates whether DynamoDB
     * Streams is enabled (true) or disabled (false) on the table. </li> <li>
     * <p><i>StreamViewType</i> - When an item in the table is modified,
     * <i>StreamViewType</i> determines what information is written to the
     * stream for this table. Valid values for <i>StreamViewType</i> are:
     * <ul> <li><p><i>KEYS_ONLY</i> - Only the key attributes of the modified
     * item are written to the stream.</li> <li><p><i>NEW_IMAGE</i> - The
     * entire item, as it appears after it was modified, is written to the
     * stream.</li> <li><p><i>OLD_IMAGE</i> - The entire item, as it appeared
     * before it was modified, is written to the stream.</li>
     * <li><p><i>NEW_AND_OLD_IMAGES</i> - Both the new and the old item
     * images of the item are written to the stream.</li> </ul> </li> </ul>
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>NEW_IMAGE, OLD_IMAGE, NEW_AND_OLD_IMAGES, KEYS_ONLY
     *
     * @param streamViewType The DynamoDB Streams settings for the table. These settings consist
     *         of: <ul> <li> <p><i>StreamEnabled</i> - Indicates whether DynamoDB
     *         Streams is enabled (true) or disabled (false) on the table. </li> <li>
     *         <p><i>StreamViewType</i> - When an item in the table is modified,
     *         <i>StreamViewType</i> determines what information is written to the
     *         stream for this table. Valid values for <i>StreamViewType</i> are:
     *         <ul> <li><p><i>KEYS_ONLY</i> - Only the key attributes of the modified
     *         item are written to the stream.</li> <li><p><i>NEW_IMAGE</i> - The
     *         entire item, as it appears after it was modified, is written to the
     *         stream.</li> <li><p><i>OLD_IMAGE</i> - The entire item, as it appeared
     *         before it was modified, is written to the stream.</li>
     *         <li><p><i>NEW_AND_OLD_IMAGES</i> - Both the new and the old item
     *         images of the item are written to the stream.</li> </ul> </li> </ul>
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     *
     * @see StreamViewType
     */
    public StreamSpecification withStreamViewType(String streamViewType) {
        this.streamViewType = streamViewType;
        return this;
    }

    /**
     * The DynamoDB Streams settings for the table. These settings consist
     * of: <ul> <li> <p><i>StreamEnabled</i> - Indicates whether DynamoDB
     * Streams is enabled (true) or disabled (false) on the table. </li> <li>
     * <p><i>StreamViewType</i> - When an item in the table is modified,
     * <i>StreamViewType</i> determines what information is written to the
     * stream for this table. Valid values for <i>StreamViewType</i> are:
     * <ul> <li><p><i>KEYS_ONLY</i> - Only the key attributes of the modified
     * item are written to the stream.</li> <li><p><i>NEW_IMAGE</i> - The
     * entire item, as it appears after it was modified, is written to the
     * stream.</li> <li><p><i>OLD_IMAGE</i> - The entire item, as it appeared
     * before it was modified, is written to the stream.</li>
     * <li><p><i>NEW_AND_OLD_IMAGES</i> - Both the new and the old item
     * images of the item are written to the stream.</li> </ul> </li> </ul>
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>NEW_IMAGE, OLD_IMAGE, NEW_AND_OLD_IMAGES, KEYS_ONLY
     *
     * @param streamViewType The DynamoDB Streams settings for the table. These settings consist
     *         of: <ul> <li> <p><i>StreamEnabled</i> - Indicates whether DynamoDB
     *         Streams is enabled (true) or disabled (false) on the table. </li> <li>
     *         <p><i>StreamViewType</i> - When an item in the table is modified,
     *         <i>StreamViewType</i> determines what information is written to the
     *         stream for this table. Valid values for <i>StreamViewType</i> are:
     *         <ul> <li><p><i>KEYS_ONLY</i> - Only the key attributes of the modified
     *         item are written to the stream.</li> <li><p><i>NEW_IMAGE</i> - The
     *         entire item, as it appears after it was modified, is written to the
     *         stream.</li> <li><p><i>OLD_IMAGE</i> - The entire item, as it appeared
     *         before it was modified, is written to the stream.</li>
     *         <li><p><i>NEW_AND_OLD_IMAGES</i> - Both the new and the old item
     *         images of the item are written to the stream.</li> </ul> </li> </ul>
     *
     * @see StreamViewType
     */
    public void setStreamViewType(StreamViewType streamViewType) {
        this.streamViewType = streamViewType.toString();
    }
    
    /**
     * The DynamoDB Streams settings for the table. These settings consist
     * of: <ul> <li> <p><i>StreamEnabled</i> - Indicates whether DynamoDB
     * Streams is enabled (true) or disabled (false) on the table. </li> <li>
     * <p><i>StreamViewType</i> - When an item in the table is modified,
     * <i>StreamViewType</i> determines what information is written to the
     * stream for this table. Valid values for <i>StreamViewType</i> are:
     * <ul> <li><p><i>KEYS_ONLY</i> - Only the key attributes of the modified
     * item are written to the stream.</li> <li><p><i>NEW_IMAGE</i> - The
     * entire item, as it appears after it was modified, is written to the
     * stream.</li> <li><p><i>OLD_IMAGE</i> - The entire item, as it appeared
     * before it was modified, is written to the stream.</li>
     * <li><p><i>NEW_AND_OLD_IMAGES</i> - Both the new and the old item
     * images of the item are written to the stream.</li> </ul> </li> </ul>
     * <p>
     * Returns a reference to this object so that method calls can be chained together.
     * <p>
     * <b>Constraints:</b><br/>
     * <b>Allowed Values: </b>NEW_IMAGE, OLD_IMAGE, NEW_AND_OLD_IMAGES, KEYS_ONLY
     *
     * @param streamViewType The DynamoDB Streams settings for the table. These settings consist
     *         of: <ul> <li> <p><i>StreamEnabled</i> - Indicates whether DynamoDB
     *         Streams is enabled (true) or disabled (false) on the table. </li> <li>
     *         <p><i>StreamViewType</i> - When an item in the table is modified,
     *         <i>StreamViewType</i> determines what information is written to the
     *         stream for this table. Valid values for <i>StreamViewType</i> are:
     *         <ul> <li><p><i>KEYS_ONLY</i> - Only the key attributes of the modified
     *         item are written to the stream.</li> <li><p><i>NEW_IMAGE</i> - The
     *         entire item, as it appears after it was modified, is written to the
     *         stream.</li> <li><p><i>OLD_IMAGE</i> - The entire item, as it appeared
     *         before it was modified, is written to the stream.</li>
     *         <li><p><i>NEW_AND_OLD_IMAGES</i> - Both the new and the old item
     *         images of the item are written to the stream.</li> </ul> </li> </ul>
     *
     * @return A reference to this updated object so that method calls can be chained
     *         together.
     *
     * @see StreamViewType
     */
    public StreamSpecification withStreamViewType(StreamViewType streamViewType) {
        this.streamViewType = streamViewType.toString();
        return this;
    }

    /**
     * Returns a string representation of this object; useful for testing and
     * debugging.
     *
     * @return A string representation of this object.
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (isStreamEnabled() != null) sb.append("StreamEnabled: " + isStreamEnabled() + ",");
        if (getStreamViewType() != null) sb.append("StreamViewType: " + getStreamViewType() );
        sb.append("}");
        return sb.toString();
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;
        
        hashCode = prime * hashCode + ((isStreamEnabled() == null) ? 0 : isStreamEnabled().hashCode()); 
        hashCode = prime * hashCode + ((getStreamViewType() == null) ? 0 : getStreamViewType().hashCode()); 
        return hashCode;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if (obj instanceof StreamSpecification == false) return false;
        StreamSpecification other = (StreamSpecification)obj;
        
        if (other.isStreamEnabled() == null ^ this.isStreamEnabled() == null) return false;
        if (other.isStreamEnabled() != null && other.isStreamEnabled().equals(this.isStreamEnabled()) == false) return false; 
        if (other.getStreamViewType() == null ^ this.getStreamViewType() == null) return false;
        if (other.getStreamViewType() != null && other.getStreamViewType().equals(this.getStreamViewType()) == false) return false; 
        return true;
    }
    
}
    