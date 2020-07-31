package com.message.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.message.resource.NotificationEvent;
import com.message.resource.NotificationResponse;
import com.message.serializers.NotificationEventSerializer;



public class KafkaConfiguration {

	@Bean
	public ProducerFactory<String, Map<String, String>> producerfactory() {
		Map<String, Object> configs = new HashMap<String, Object>();

		configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		
		return new DefaultKafkaProducerFactory<String, Map<String, String>>(configs);
	}
	
	@Bean
	public KafkaTemplate<String, Map<String, String>> kafkatemplate() {
		
		return new KafkaTemplate<String, Map<String, String>>(producerfactory());
	}
	
	/*
	 * @Bean public MappingJackson2HttpMessageConverter
	 * mappingJackson2HttpMessageConverter() { MappingJackson2HttpMessageConverter
	 * jsonConverter = new MappingJackson2HttpMessageConverter(); ObjectMapper
	 * objectMapper = new ObjectMapper();
	 * objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
	 * false); objectMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
	 * jsonConverter.setObjectMapper(objectMapper); return jsonConverter; }
	 */
}
