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
package com.amazonaws.services.lambda.model.transform;

import static com.amazonaws.util.StringUtils.UTF8;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.List;

import com.amazonaws.AmazonClientException;
import com.amazonaws.Request;
import com.amazonaws.DefaultRequest;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.lambda.model.*;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.BinaryUtils;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;

/**
 * Invoke Request Marshaller
 */
public class InvokeRequestMarshaller implements Marshaller<Request<InvokeRequest>, InvokeRequest> {

    public Request<InvokeRequest> marshall(InvokeRequest invokeRequest) {
    if (invokeRequest == null) {
        throw new AmazonClientException("Invalid argument passed to marshall(...)");
    }

        Request<InvokeRequest> request = new DefaultRequest<InvokeRequest>(invokeRequest, "AWSLambda");
        String target = "AWSLambda.Invoke";
        request.addHeader("X-Amz-Target", target);

        request.setHttpMethod(HttpMethodName.POST);
        if (invokeRequest.getInvocationType() != null)
          request.addHeader("X-Amz-Invocation-Type", StringUtils.fromString(invokeRequest.getInvocationType()));
        
        if (invokeRequest.getLogType() != null)
          request.addHeader("X-Amz-Log-Type", StringUtils.fromString(invokeRequest.getLogType()));
        
        if (invokeRequest.getClientContext() != null)
          request.addHeader("X-Amz-Client-Context", StringUtils.fromString(invokeRequest.getClientContext()));
        
        String uriResourcePath = "/2015-03-31/functions/{FunctionName}/invocations?Qualifier={Qualifier}"; 
        uriResourcePath = uriResourcePath.replace("{FunctionName}", (invokeRequest.getFunctionName() == null) ? "" : StringUtils.fromString(invokeRequest.getFunctionName())); 
        uriResourcePath = uriResourcePath.replace("{Qualifier}", (invokeRequest.getQualifier() == null) ? "" : StringUtils.fromString(invokeRequest.getQualifier())); 

        uriResourcePath = uriResourcePath.replaceAll("//", "/");

        if (uriResourcePath.contains("?")) {
            String queryString = uriResourcePath.substring(uriResourcePath.indexOf("?") + 1);
            uriResourcePath    = uriResourcePath.substring(0, uriResourcePath.indexOf("?"));

            for (String s : queryString.split("[;&]")) {
                String[] nameValuePair = s.split("=");
                if (nameValuePair.length == 2) {
                    if(!(nameValuePair[1].isEmpty()))
                        request.addParameter(nameValuePair[0], nameValuePair[1]);
                }
            }
        }
        request.setResourcePath(uriResourcePath);
        
        request.addHeader("Content-Length", Integer.toString(invokeRequest.getPayload().remaining()));
        request.setContent(BinaryUtils.toStream(invokeRequest.getPayload()));
        if (!request.getHeaders().containsKey("Content-Type")) {
            request.addHeader("Content-Type", "binary/octet-stream");
        }

        return request;
    }
}
