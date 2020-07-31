package com.message.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigStore {

	
	// userid - <assetid, subscriptiontype >
	
	Map<String, String> subscriptiontypeMap= new HashMap<String, String>();
	private static Map<String, List<Map<String, String>>> folowerSubscriptionDetails;
	
	public static Map<String, List<Map<String, String>>> getFolowerSubscriptionDetails() {
		return folowerSubscriptionDetails;
	}
	public static void setFolowerSubscriptionDetails(Map<String, List<Map<String, String>>> folowerSubscriptionDetails) {
		ConfigStore.folowerSubscriptionDetails = folowerSubscriptionDetails;
	}
	
}
