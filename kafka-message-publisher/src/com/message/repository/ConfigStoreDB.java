package com.message.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConfigStoreDB {
	
	private static  List<List<String>> allsubscriberInfo;

	public static List<List<String>> getAllsubscriberInfo(String category, String assetName) {
		getuserSubsInfo(category, assetName);
		return allsubscriberInfo;
	}

	public static void setAllsubscriberInfo(List<List<String>> allsubscriberInfo) {
		ConfigStoreDB.allsubscriberInfo = allsubscriberInfo;
	}

	private static List<List<String>> getuserSubsInfo(String category, String topicname) {
		List<List<String>> finalList = new ArrayList<List<String>>();
		List<String> temp;
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rakesh?useSSL=false", "root", "mysql");
			Statement stmt = con.createStatement();
			String query = "select username, notificationtype from config_store_new where topic='"
					+ topicname + "'" ; //+ " AND category = '" + category + "'"
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				temp = new ArrayList<String>();
				temp.add(rs.getString(1));
				temp.add(rs.getString(2));
				finalList.add(temp);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		setAllsubscriberInfo(finalList);
		return finalList;
	}

}
