package com.message.publish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.message"})
public class MessageProduceConsumeKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageProduceConsumeKafkaApplication.class, args);
	}

}
