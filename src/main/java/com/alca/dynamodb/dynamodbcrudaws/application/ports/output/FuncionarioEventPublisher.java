package com.alca.dynamodb.dynamodbcrudaws.application.ports.output;

import com.alca.dynamodb.dynamodbcrudaws.domain.event.FuncionarioCriadoEvent;

public interface FuncionarioEventPublisher {
  void publishEmployeeCreatedEvent(FuncionarioCriadoEvent event);
}
