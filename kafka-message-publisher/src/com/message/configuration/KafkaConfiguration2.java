package com.message.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.message.resource.NotificationResponse;

@Configuration
public class KafkaConfiguration2 {

	@Bean
	public ProducerFactory<String, NotificationResponse> producerfactory() {
		Map<String, Object> configs = new HashMap<String, Object>();

		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		
		return new DefaultKafkaProducerFactory<String, NotificationResponse>(configs);
	}
	
	@Bean
	public KafkaTemplate<String, NotificationResponse> kafkatemplate() {
		
		return new KafkaTemplate<String, NotificationResponse>(producerfactory());
	}

}
