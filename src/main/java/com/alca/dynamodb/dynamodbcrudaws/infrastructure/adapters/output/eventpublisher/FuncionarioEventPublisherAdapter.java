package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.output.eventpublisher;

import com.alca.dynamodb.dynamodbcrudaws.application.ports.output.FuncionarioEventPublisher;
import com.alca.dynamodb.dynamodbcrudaws.domain.event.FuncionarioCriadoEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@RequiredArgsConstructor
public class FuncionarioEventPublisherAdapter implements FuncionarioEventPublisher {

  private final ApplicationEventPublisher applicationEventPublisher;

  @Override
  public void publishEmployeeCreatedEvent(FuncionarioCriadoEvent event) {
    applicationEventPublisher.publishEvent(event);
  }
}
