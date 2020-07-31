package com.message.Listener;

import java.io.IOException;

import org.json.JSONException;
import org.springframework.stereotype.Service;

import com.message.handler.MessageHandler;

@Service
public class KafkaListener {

	
	@org.springframework.kafka.annotation.KafkaListener(topics = "TOPIC_MODEL1", groupId = "group_id")
	public void consumeRealtime(String message) {
		try {
			MessageHandler.extractDetailsFromMessage(message);
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
		System.out.println(message + " Consumed.");
	}
	
	@org.springframework.kafka.annotation.KafkaListener(topics = "TOPIC_MODEL2", groupId = "group_id")
	public void consumeDaily(String message) {
		try {
			MessageHandler.extractDetailsFromMessage(message);
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
		System.out.println(message + " Consumed.");
	}
	
	@org.springframework.kafka.annotation.KafkaListener(topics = "TOPIC_CONTENT1", groupId = "group_id")
	public void consumeWeekly(String message) {
		try {
			MessageHandler.extractDetailsFromMessage(message);
		} catch (JSONException | IOException e) {
			e.printStackTrace();
		}
		System.out.println(message + " Consumed.");
	}
	
}
