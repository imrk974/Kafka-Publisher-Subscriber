package com.message.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.message.cache.SubscriberInfoCache;
import com.message.repository.ConfigStore;
import com.message.repository.ConfigStoreDB;
import com.message.repository.GraphStore;
import com.message.resource.NotificationEvent;
import com.message.resource.NotificationResponse;

public class RequestHandler {

	public static String getOwner(String assetID) {
		Map<String, String> owners = GraphStore.getOwnersInfo();
		if (owners.containsKey(assetID)) {
			return owners.get(assetID);
		}
		return null;
	}

	public static String getOwnerFromDB(String assetID) {
		Map<String, String> owners = GraphStore.getOwnersInfo();
		if (owners.containsKey(assetID)) {
			return owners.get(assetID);
		}
		return null;
	}

	public static List<List<String>> getUserSubscriptionDetailsFromDB(String service, String topicname) {
		System.out.println("Subscriber info not available in cache memory for topic : " + topicname);
		System.out.println("DB call initiated to fetch subscriber info ....");
		return ConfigStoreDB.getAllsubscriberInfo(service, topicname);
	}

	static List<List<String>> subsInfo;

	@Autowired
	SubscriberInfoCache subscriberInfoCache;

	public void getUserSubscriptionDetailsFromCache(String service, String topicname) {

		subsInfo = subscriberInfoCache.getSubscriberInfo(topicname);

	}

	public static String getKafkaTopic(String service, String assetID) {
		return getUserSubscriptionDetailsFromDB(service, assetID).get(0).get(1);

	}

	public static Map<String, String> processedMessage(NotificationEvent req, int eventId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", req.getMessageBody());
		map.put("topic", req.getTopic());
		map.put("service", req.getService());

		List<List<String>> topic = RequestHandler.getUserSubscriptionDetailsFromDB(req.getService(), req.getTopic());

		map.put("userName", topic.get(eventId).get(0));
		// map.put("notificationType", topic.get(eventId).get(1));
		List<String> realTimeSubsList = new ArrayList<String>();
		List<String> dailySubsList = new ArrayList<String>();
		List<String> weeklySubsList = new ArrayList<String>();
		List<String> monthlySubsList = new ArrayList<String>();

		for (int i = 0; i < topic.size(); i++) {
			if (topic.get(i).get(1).equalsIgnoreCase("Daily")) {
				dailySubsList.add(topic.get(i).get(0));
			} else if (topic.get(i).get(1).equalsIgnoreCase("Weekly")) {
				weeklySubsList.add(topic.get(i).get(0));
			} else if (topic.get(i).get(1).equalsIgnoreCase("Monthly")) {
				monthlySubsList.add(topic.get(i).get(0));
			} else if (topic.get(i).get(1).equalsIgnoreCase("RealTime")) {
				realTimeSubsList.add(topic.get(i).get(0));
			}
		}

		map.put("listofWeeklySubs", weeklySubsList.toString());
		map.put("listofMonthlySubs", monthlySubsList.toString());
		map.put("listofRealTimeSubs", realTimeSubsList.toString());
		map.put("listofDailySubs", dailySubsList.toString());

		return map;
	}

	public static JSONObject processedMessageJS(NotificationEvent req) throws JSONException {
		JSONObject map = new JSONObject();
		map.put("message", req.getMessageBody());
		map.put("topic", req.getTopic());
		map.put("service", req.getService());
		List<List<String>> subsInfo = RequestHandler.getUserSubscriptionDetailsFromDB(req.getService(), req.getTopic());

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

		map.put("listofDailySubs", dailySubsList);
		map.put("listofWeeklySubs", weeklySubsList);
		map.put("listofMonthlySubs", monthlySubsList);
		map.put("listofRealTimeSubs", realTimeSubsList);

		return map;

	}

	public static Map<String, String> processedMessageJS(JSONObject req) throws JSONException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", req.getString("messageBody"));
		map.put("topic", req.getString("topic"));
		map.put("service", req.getString("service"));
		List<List<String>> subsInfo = RequestHandler.getUserSubscriptionDetailsFromDB(req.getString("service"),
				req.getString("topic"));

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

		map.put("listofDailySubs", dailySubsList.toString());
		map.put("listofWeeklySubs", weeklySubsList.toString());
		map.put("listofMonthlySubs", monthlySubsList.toString());
		map.put("listofRealTimeSubs", realTimeSubsList.toString());

		return map;

	}

	public static NotificationResponse processedMessageNotificationResponse(JSONObject req) throws JSONException {
		NotificationResponse map = new NotificationResponse();
		map.setMessage(req.getString("messageBody"));
		map.setTopic(req.getString("topic"));
		map.setService(req.getString("service"));
		map.setAssetid(req.getString("assetid"));
		
		return map;

	}

	/*
	 * public static JSONObject processedMessageJS2JS(JSONObject req) throws
	 * JSONException { JSONObject map = new JSONObject(); map.put("message",
	 * req.getString("messageBody")); map.put("topic", req.getString("topic"));
	 * map.put("service", req.getString("service")); List<List<String>> subsInfo =
	 * RequestHandler.getUserSubscriptionDetailsFromDB(req.getString("service"),
	 * req.getString("topic"));
	 * 
	 * List<String> realTimeSubsList = new ArrayList<String>(); List<String>
	 * dailySubsList = new ArrayList<String>(); List<String> weeklySubsList = new
	 * ArrayList<String>(); List<String> monthlySubsList = new ArrayList<String>();
	 * 
	 * for (int i = 0; i < subsInfo.size(); i++) { if
	 * (subsInfo.get(i).get(1).equalsIgnoreCase("Daily")) {
	 * dailySubsList.add(subsInfo.get(i).get(0)); System.out.println(dailySubsList);
	 * } else if (subsInfo.get(i).get(1).equalsIgnoreCase("Weekly")) {
	 * weeklySubsList.add(subsInfo.get(i).get(0)); } else if
	 * (subsInfo.get(i).get(1).equalsIgnoreCase("Monthly")) {
	 * monthlySubsList.add(subsInfo.get(i).get(0)); } else if
	 * (subsInfo.get(i).get(1).equalsIgnoreCase("RealTime")) {
	 * realTimeSubsList.add(subsInfo.get(i).get(0)); } }
	 * 
	 * map.put("listofDailySubs", dailySubsList); map.put("listofWeeklySubs",
	 * weeklySubsList); map.put("listofMonthlySubs", monthlySubsList);
	 * map.put("listofRealTimeSubs", realTimeSubsList);
	 * 
	 * return map;
	 * 
	 * }
	 */

	public static Map<String, String> processedMessage(NotificationEvent req) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("message", req.getMessageBody());
		map.put("topic", req.getTopic());
		map.put("service", req.getService());
		List<List<String>> subsInfo = RequestHandler.getUserSubscriptionDetailsFromDB(req.getService(), req.getTopic());

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

		map.put("listofDailySubs", dailySubsList.toString());
		map.put("listofWeeklySubs", weeklySubsList.toString());
		map.put("listofMonthlySubs", monthlySubsList.toString());
		map.put("listofRealTimeSubs", realTimeSubsList.toString());

		return map;

	}

	public static List<List<String>> getUserSubscriptionDetails(String assetID) {

		Map<String, List<Map<String, String>>> map = ConfigStore.getFolowerSubscriptionDetails();
		List<List<String>> list = new ArrayList<List<String>>();
		for (Map.Entry<String, List<Map<String, String>>> entry : map.entrySet()) {
			List<String> temp = new ArrayList<String>();
			for (int i = 0; i < entry.getValue().size(); i++) {
				if (entry.getValue().get(i).containsKey(assetID)) {
					temp.add(entry.getKey());
					temp.add(entry.getValue().get(i).get(assetID));
					temp.add(assetID);
				}
			}
			if (temp.size() > 0) {
				list.add(temp);
			}
		}

		return list;
	}

	public static void insertIntoDB(NotificationResponse res) {

		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rakesh?useSSL=false", "root", "mysql");
			Statement stmt = con.createStatement();
			if (res.getListofDailySubs().size() > 0) {
				for (int i = 0; i < res.getListofDailySubs().size(); i++) {
					String query = "insert into rakesh.DIGEST_MSG_STORE Values('" + res.getListofDailySubs().get(i)
							+ "'," + "'" + res.getTopic() + "'," + "'" + res.getService() + "'," + "'"
							+ res.getMessage() + "','Daily'" + ")";

					stmt.executeUpdate(query);
				}
			}
			if (res.getListofWeeklySubs().size() > 0) {
				for (int i = 0; i < res.getListofDailySubs().size(); i++) {
					String query = "insert into DIGEST_MSG_STORE Values('" + res.getListofWeeklySubs().get(i) + "',"
							+ "'" + res.getTopic() + "'," + "'" + res.getService() + "'," + "'" + res.getMessage()
							+ "','Weekly'" + ")";
					stmt.executeUpdate(query);
				}
			}

			if (res.getListofMonthlySubs().size() > 0) {
				for (int i = 0; i < res.getListofDailySubs().size(); i++) {
					String query = "insert into DIGEST_MSG_STORE Values('" + res.getListofMonthlySubs().get(i) + "',"
							+ "'" + res.getTopic() + "'," + "'" + res.getService() + "'," + "'" + res.getMessage()
							+ "','Monthly'" + ")";
					stmt.executeUpdate(query);
				}
			}

			if (res.getListofRealTimeSubs().size() > 0) {
				for (int i = 0; i < res.getListofRealTimeSubs().size(); i++) {
					String query = "insert into DIGEST_MSG_STORE Values('" + res.getListofRealTimeSubs().get(i) + "',"
							+ "'" + res.getTopic() + "'," + "'" + res.getService() + "'," + "'" + res.getMessage()
							+ "','RealTime'" + ")";
					stmt.executeUpdate(query);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
