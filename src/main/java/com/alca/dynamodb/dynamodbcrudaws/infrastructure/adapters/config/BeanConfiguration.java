package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.config;

import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.output.eventpublisher.EmployeeEventPublisherAdapter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public EmployeeEventPublisherAdapter employeeEventPublisherAdapter(ApplicationEventPublisher applicationEventPublisher) {
      return new EmployeeEventPublisherAdapter(applicationEventPublisher);
    }
}
