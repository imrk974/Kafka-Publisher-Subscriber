package com.message.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GraphStoreDB {
	
	private static List<List<String>> ownerInfo;

	

	public static List<List<String>> getOwnerInfo(String assetName) {
		getConn(assetName);
		return ownerInfo;
	}

	public static void setOwnerInfo(List<List<String>> ownerInfo) {
		GraphStoreDB.ownerInfo = ownerInfo;
	}

	private static List<List<String>> getConn(String assetName) {
		List<List<String>> finalList = new ArrayList<List<String>>();
		List<String> temp;
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/rakesh?useSSL=false", "root", "mysql");
			Statement stmt = con.createStatement();
			String query = "select username,subscriptiontype,notificationtype from graph_store where assetname='"
					+ assetName + "'";
			System.out.println(query);
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				temp = new ArrayList<String>();
				temp.add(rs.getString(1));
				temp.add(rs.getString(2));
				temp.add(rs.getString(3));
				finalList.add(temp);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		setOwnerInfo(finalList);
		return finalList;
	}

}
