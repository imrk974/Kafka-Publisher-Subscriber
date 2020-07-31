package com.message.handler;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MessageHandler {

	private static String messageBody = "";
	private static JSONObject res = null;

	public static void extractDetailsFromMessage(String message) throws JSONException, IOException {
		res = new JSONObject(message);
		messageBody = res.getString("message");
		sendNotificationToRealTimeSubs();
		sendNotificationToMonthlySubs();
		sendNotificationToDailySubs();
		sendNotificationToWeeklySubs();
	}

	private static void sendNotificationToMonthlySubs() throws JSONException, IOException {
		if (!res.get("listofWeeklySubs").equals(null)) {
			JSONArray realTimeSubs = res.getJSONArray("listofWeeklySubs");
			for (int i = 0; i < realTimeSubs.length(); i++) {
				createUsersFileWithMessage(realTimeSubs.get(i).toString(), messageBody, "Weekly");
			}
		}
		
	}

	private static void sendNotificationToWeeklySubs() throws JSONException, IOException {
		if (!res.get("listofMonthlySubs").equals(null)) {
			JSONArray realTimeSubs = res.getJSONArray("listofMonthlySubs");
			for (int i = 0; i < realTimeSubs.length(); i++) {
				createUsersFileWithMessage(realTimeSubs.get(i).toString(), messageBody, "Monthly");
			}
		}
		
	}

	private static void sendNotificationToDailySubs() throws JSONException, IOException {
		if (!res.get("listofDailySubs").equals(null)) {
			JSONArray realTimeSubs = res.getJSONArray("listofDailySubs");
			for (int i = 0; i < realTimeSubs.length(); i++) {
				createUsersFileWithMessage(realTimeSubs.get(i).toString(), messageBody, "Daily");
			}
		}
		
	}

	private static void sendNotificationToRealTimeSubs() throws JSONException, IOException {
		JSONArray realTimeSubs = res.getJSONArray("listofRealTimeSubs");
		for (int i = 0; i < realTimeSubs.length(); i++) {
			createUsersFileWithMessage(realTimeSubs.get(i).toString(), messageBody, "RealTime");
		}
		
	}
	
	private static void createUsersFileWithMessage(String username, String messageBody, String type) throws IOException {
		
		File file = new File("./Notifications/" + username + "_" + type);
		FileWriter fw = new FileWriter(file);
		fw.write(messageBody);
		fw.close();
		
	}
	
}
