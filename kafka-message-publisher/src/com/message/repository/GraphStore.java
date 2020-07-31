package com.message.repository;

import java.util.Map;

public class GraphStore {
	
	private static Map<String,String> ownersInfo;

	public static Map<String, String> getOwnersInfo() {
		return ownersInfo;
	}

	public static void setOwnersInfo(Map<String, String> ownersInfo) {
		GraphStore.ownersInfo = ownersInfo;
	}
	
	
}
