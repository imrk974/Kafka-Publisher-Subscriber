package com.message.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.message.cache.SubscriberInfoCache;
import com.message.handler.RequestHandler;
import com.message.resource.NotificationEvent;
import com.message.resource.NotificationResponse;

@RestController
@RequestMapping("/kafka")
public class RequestMessageController2 {

	@Autowired
	KafkaTemplate<String, NotificationResponse> kafkatemplate;

	@Autowired
	SubscriberInfoCache cache;

	@PostMapping("/processRawMessage")
	private NotificationResponse postMessage(@RequestBody NotificationEvent req)
			throws RestClientException, JSONException {

		String url = "http://localhost:8083/getrawmessage/" + req.getMesageType() + "/" + req.getMessageBody() + "/"
				+ req.getTopic() + "/" + req.getService() + "/" + req.getAssetid();
		RestTemplate restTemplate = new RestTemplate();
		JSONObject result = new JSONObject(restTemplate.getForObject(url, String.class));
		NotificationResponse response = getSubscriberPreference(result);
		if(response.getListofRealTimeSubs().size() > 0) {
			NotificationResponse realtimeresponse = new NotificationResponse();
			realtimeresponse.setService(response.getService());
			realtimeresponse.setTopic(response.getTopic());
			realtimeresponse.setMessage(response.getMessage());
			realtimeresponse.setAssetid(response.getAssetid());
			realtimeresponse.setListofRealTimeSubs(response.getListofRealTimeSubs());
			kafkatemplate.send(response.getTopic(), response);
		}
		
		RequestHandler.insertIntoDB(response);
		
		return response;
	}

	private NotificationResponse getSubscriberPreference(JSONObject result) throws JSONException {
		NotificationResponse response = new NotificationResponse();

		// Fetching subscriber info from DB
		List<List<String>> subsInfo = cache.getSubscriberInfo(result.getString("topic"));

		response.setMessage(result.getString("messageBody"));
		response.setTopic(result.getString("topic"));
		response.setService(result.getString("service"));
		response.setAssetid(result.getString("assetid"));
		List<String> realTimeSubsList = new ArrayList<String>();
		List<String> dailySubsList = new ArrayList<String>();
		List<String> weeklySubsList = new ArrayList<String>();
		List<String> monthlySubsList = new ArrayList<String>();

		for (int i = 0; i < subsInfo.size(); i++) {
			if (subsInfo.get(i).get(1).equalsIgnoreCase("Daily")) {
				dailySubsList.add(subsInfo.get(i).get(0));
			} else if (subsInfo.get(i).get(1).equalsIgnoreCase("Weekly")) {
				weeklySubsList.add(subsInfo.get(i).get(0));
			} else if (subsInfo.get(i).get(1).equalsIgnoreCase("Monthly")) {
				monthlySubsList.add(subsInfo.get(i).get(0));
			} else if (subsInfo.get(i).get(1).equalsIgnoreCase("RealTime")) {
				realTimeSubsList.add(subsInfo.get(i).get(0));
			}
		}

		response.setListofDailySubs(dailySubsList);
		response.setListofWeeklySubs(weeklySubsList);
		response.setListofMonthlySubs(monthlySubsList);
		response.setListofRealTimeSubs(realTimeSubsList);
		return response;
	}

	/*
	 * 
	 * ListenableFuture<SendResult<String, NotificationResponse>> ls =
	 * kafkatemplate.send(response.getTopic(), response); ls.isDone();
	 * 
	 * try { SendResult<String, NotificationResponse> data =
	 * kafkatemplate.send(response.getTopic(), response).get();
	 * 
	 * System.out.println(data.getRecordMetadata().offset() + ", Partion : " +
	 * data.getRecordMetadata().partition()); } catch (InterruptedException |
	 * ExecutionException e) { e.printStackTrace(); }
	 */
}