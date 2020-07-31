package com.message.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.message.resource.NotificationEvent;

@RestController
@RequestMapping("/getrawmessage")
public class RawMessageController {

	@GetMapping("/{mesageType}/{messageBody}/{topic}/{service}/{assetid}")
	public NotificationEvent getmessage(@PathVariable("mesageType") String mesageType,
			@PathVariable("messageBody") String messageBody, @PathVariable("topic") String topic,
			@PathVariable("service") String service, @PathVariable("assetid") String assetid) {
		
		return new NotificationEvent(mesageType, messageBody, topic, service, assetid);
	}

}
