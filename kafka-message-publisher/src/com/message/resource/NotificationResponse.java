package com.message.resource;

import java.util.List;

public class NotificationResponse {

	private String message;
	private String service;
	private String topic;
	private String assetid;
	private List<String> listofRealTimeSubs;
	private List<String> listofDailySubs;
	private List<String> listofWeeklySubs;
	private List<String> listofMonthlySubs;

	public NotificationResponse() {
		
	}
	
	public NotificationResponse(String message, String category, String topic, String assetid, List<String> listofRealTimeSubs,
			List<String> listofDailySubs, List<String> listofWeeklySubs, List<String> listofMonthlySubs) {
		super();
		this.message = message;
		this.service = category;
		this.topic = topic;
		this.assetid = assetid;
		this.listofRealTimeSubs = listofRealTimeSubs;
		this.listofDailySubs = listofDailySubs;
		this.listofWeeklySubs = listofWeeklySubs;
		this.listofMonthlySubs = listofMonthlySubs;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getService() {
		return service;
	}

	public void setService(String category) {
		this.service = category;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public List<String> getListofRealTimeSubs() {
		return listofRealTimeSubs;
	}

	public void setListofRealTimeSubs(List<String> listofRealTimeSubs) {
		this.listofRealTimeSubs = listofRealTimeSubs;
	}

	public List<String> getListofDailySubs() {
		return listofDailySubs;
	}

	public void setListofDailySubs(List<String> listofDailySubs) {
		this.listofDailySubs = listofDailySubs;
	}

	public List<String> getListofWeeklySubs() {
		return listofWeeklySubs;
	}

	public void setListofWeeklySubs(List<String> listofWeeklySubs) {
		this.listofWeeklySubs = listofWeeklySubs;
	}

	public List<String> getListofMonthlySubs() {
		return listofMonthlySubs;
	}

	public void setListofMonthlySubs(List<String> listofMonthlySubs) {
		this.listofMonthlySubs = listofMonthlySubs;
	}

	public String getAssetid() {
		return assetid;
	}

	public void setAssetid(String assetid) {
		this.assetid = assetid;
	}
	
	@Override
	public String toString() {
		return "NotificationResponse [message=" + message + ", category=" + service + ", topic=" + topic
				+ ", listofRealTimeSubs=" + listofRealTimeSubs + ", listofDailySubs=" + listofDailySubs
				+ ", listofWeeklySubs=" + listofWeeklySubs + ", listofMonthlySubs=" + listofMonthlySubs + "]";
	}

	
}
