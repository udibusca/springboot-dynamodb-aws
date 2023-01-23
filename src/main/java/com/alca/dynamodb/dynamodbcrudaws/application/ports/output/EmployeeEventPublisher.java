package com.alca.dynamodb.dynamodbcrudaws.application.ports.output;

import com.alca.dynamodb.dynamodbcrudaws.domain.event.EmployeeCreatedEvent;

public interface EmployeeEventPublisher {
  void publishEmployeeCreatedEvent(EmployeeCreatedEvent event);
}
