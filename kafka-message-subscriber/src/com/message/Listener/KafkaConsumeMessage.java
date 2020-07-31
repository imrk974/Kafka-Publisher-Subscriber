package com.message.Listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaConsumeMessage {
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;
	
	@RequestMapping("/getmessage/{topic}")
	public void getMessagefromTopic() {
		
		System.out.println();
		
		
	}
	

}
