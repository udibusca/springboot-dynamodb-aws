package com.alca.dynamodb.dynamodbcrudaws;

import java.time.LocalDateTime;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j2
@SpringBootApplication
public class DynamodbcrudAwsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamodbcrudAwsApplication.class, args);
		log.info("Api iniciada com sucesso as {}", LocalDateTime.now());
	}

}
