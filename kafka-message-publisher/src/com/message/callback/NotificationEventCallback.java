package com.message.callback;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class NotificationEventCallback implements Callback {

	@Override
	public void onCompletion(RecordMetadata metadata, Exception exception) {
		
		if(exception != null) {
			System.out.println("Asynchronous Send failed with exception ");
			exception.printStackTrace();
		} else {
			System.out.println("Asynchronous send succeeded.");
		}
		
	}

}
