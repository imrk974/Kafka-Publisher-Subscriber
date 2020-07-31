package com.message.controller;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.message.handler.RequestHandler;
import com.message.resource.NotificationEvent;


public class RequestMessageController {

	@Autowired
	KafkaTemplate<String, Map<String, String>> kafkatemplate;

	@PostMapping("/publish/message")
	private Map<String, String> postMessage(@RequestBody NotificationEvent req) throws RestClientException, JSONException {

		String url = "http://localhost:8083/getrawmessage/" + req.getMesageType() + "/" + req.getMessageBody() + "/"+ req.getTopic()+ "/" + req.getService() ;
		RestTemplate restTemplate = new RestTemplate();
		JSONObject result = new JSONObject(restTemplate.getForObject(url, String.class));
		
		System.out.println(result.getString("topic"));
		
		try {
			kafkatemplate.send(result.getString("topic"), RequestHandler.processedMessageJS(result));
		} catch (JSONException e) {

			e.printStackTrace();
		}
		return RequestHandler.processedMessageJS(result);
	}
}
