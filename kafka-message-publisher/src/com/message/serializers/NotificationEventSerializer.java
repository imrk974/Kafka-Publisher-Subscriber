// Copyright (c) 2020 Informatica Corporation. All rights reserved.

package com.message.serializers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.kafka.common.serialization.Serializer;


public class NotificationEventSerializer implements Serializer<Object> {

	/*
	 * public byte[] serialize(NotificationEvent notificationEvent) { byte[] retVal
	 * = null; ObjectMapper objectMapper = new ObjectMapper(); try { retVal =
	 * objectMapper.writeValueAsString(notificationEvent).getBytes(); } catch
	 * (Exception e) { String msg =
	 * "Exception occured serializing object of type JobEvent with exception :\\n";
	 * throw new RuntimeException(msg, e); } return retVal; }
	 */

	@Override
	public byte[] serialize(String topic, Object data) {
		try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
            objectStream.writeObject(data);
            objectStream.flush();
            objectStream.close();
            return byteStream.toByteArray();
        }
        catch (IOException e) {
            throw new IllegalStateException("Can't serialize object: " + data, e);
        }
	}

}
