package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.eventlistener;

import com.alca.dynamodb.dynamodbcrudaws.domain.event.EmployeeCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmployeeEventListenerAdapter {

  @EventListener
  public void handle(EmployeeCreatedEvent event){
    log.info("Employee created with id " + event.getFuncionarioId() + " at " + event.getDate());
  }
}
