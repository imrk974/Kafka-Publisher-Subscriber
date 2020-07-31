package com.message.subscriber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.message"})
public class MessageConsumeKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageConsumeKafkaApplication.class, args);
	}

}
