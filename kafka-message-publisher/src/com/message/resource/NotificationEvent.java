package com.message.resource;

import java.util.Objects;

import org.springframework.stereotype.Component;

@Component
public class NotificationEvent {
	private String mesageType;
	private String messageBody;
	private String topic;
	private String service;
	private String assetid;
	
	public NotificationEvent() {
		
	}
	
	public NotificationEvent(String mesageType, String messageBody, String topic, String service, String assetid) {
		this.mesageType = mesageType;
		this.messageBody = messageBody;
		this.topic = topic;
		this.service = service;
		this.assetid = assetid;
	}

	public String getMesageType() {
		return mesageType;
	}

	public void setMesageType(String mesageType) {
		this.mesageType = mesageType;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}
	
	public String getTopic() {
		return topic;
	}

	public void setTopic(String assetId) {
		this.topic = assetId;
	}
	
	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}
	
	public String getAssetid() {
		return assetid;
	}

	public void setAssetid(String assetid) {
		this.assetid = assetid;
	}

	@Override
	public String toString() {
		return "NotificationEvent [mesageType=" + mesageType + ", messageBody=" + messageBody + ", assetId=" + topic
				+ ", category=" + service + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(getMesageType(),getMessageBody(),getTopic());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NotificationEvent other = (NotificationEvent) obj;
		if (mesageType == null) {
			if (other.mesageType != null)
				return false;
		} else if (!mesageType.equals(other.mesageType))
			return false;
		if (messageBody == null) {
			if (other.messageBody != null)
				return false;
		} else if (!messageBody.equals(other.messageBody))
			return false;
		
		if (topic == null) {
			if (other.topic != null)
				return false;
		} else if (!topic.equals(other.topic))
			return false;
		return true;
	}

		
}
