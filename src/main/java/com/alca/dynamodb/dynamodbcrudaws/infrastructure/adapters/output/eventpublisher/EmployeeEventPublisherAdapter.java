package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.output.eventpublisher;

import com.alca.dynamodb.dynamodbcrudaws.application.ports.output.EmployeeEventPublisher;
import com.alca.dynamodb.dynamodbcrudaws.domain.event.EmployeeCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
public class EmployeeEventPublisherAdapter implements EmployeeEventPublisher {

  private final ApplicationEventPublisher applicationEventPublisher;

  @Override
  public void publishEmployeeCreatedEvent(EmployeeCreatedEvent event) {
    applicationEventPublisher.publishEvent(event);
  }
}
