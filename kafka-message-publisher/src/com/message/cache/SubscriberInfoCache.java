package com.message.cache;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.message.handler.RequestHandler;

@Component
public class SubscriberInfoCache {

	
	@Cacheable(value = "subscriber-cache", key = "#topicname")
	public List<List<String>> getSubscriberInfo(String topicname) {
		return RequestHandler.getUserSubscriptionDetailsFromDB("Model", topicname);
		
	}
}
