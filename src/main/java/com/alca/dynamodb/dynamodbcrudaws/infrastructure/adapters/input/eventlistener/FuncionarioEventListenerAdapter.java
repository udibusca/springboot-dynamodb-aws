package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.eventlistener;

import com.alca.dynamodb.dynamodbcrudaws.domain.event.FuncionarioCriadoEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FuncionarioEventListenerAdapter {

  @EventListener
  public void handle(FuncionarioCriadoEvent event){
    log.info("Funcionario criado com o ID " + event.getFuncionarioId() + " em " + event.getDate());
  }
}
