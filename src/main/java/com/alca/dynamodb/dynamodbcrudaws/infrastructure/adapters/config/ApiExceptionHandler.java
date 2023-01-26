package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.config;

import com.alca.dynamodb.dynamodbcrudaws.domain.exception.FuncionarioNotFoundException;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest.data.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe que implementa um handler de exceções e erros na API, usando {@ControllerAdvice}
 * e enviando a resposta adequada ao cliente.
 *
 *@author André LCA
 */
@ControllerAdvice
public class ApiExceptionHandler<T> {

  @ExceptionHandler(value = { FuncionarioNotFoundException.class })
  protected ResponseEntity<Response<T>> handleNotFoundException(FuncionarioNotFoundException exception) {

    Response<T> response = new Response<>();
    response.addErrorMsgToResponse(exception.getLocalizedMessage());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }
}
