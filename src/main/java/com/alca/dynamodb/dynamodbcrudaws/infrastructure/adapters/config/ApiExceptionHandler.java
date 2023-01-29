package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.config;

import com.alca.dynamodb.dynamodbcrudaws.domain.exception.FuncionarioNotFoundException;
import com.alca.dynamodb.dynamodbcrudaws.domain.exception.Problema;
import com.alca.dynamodb.dynamodbcrudaws.domain.exception.TipoProblema;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest.data.response.Response;
import java.time.OffsetDateTime;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Classe que implementa um handler de exceções e erros na API, usando {@ControllerAdvice}
 * e enviando a resposta adequada ao cliente.
 *
 *@author André LCA
 */
@ControllerAdvice
public class ApiExceptionHandler<T> extends ResponseEntityExceptionHandler {

  public static final String MSG_ERRO_GENERICA_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema.";

  @ExceptionHandler(value = { FuncionarioNotFoundException.class })
  protected ResponseEntity<Response<T>> handleNotFoundException(FuncionarioNotFoundException exception) {

    Response<T> response = new Response<>();
    response.addErrorMsgToResponse(exception.getLocalizedMessage());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {

    TipoProblema tipoProblema = TipoProblema.RECURSO_NAO_ENCONTRADO;

    String detail = String.format("O recurso '%s', que você tentou acessar, é inexistente.",
        ex.getRequestURL());

    Problema problema = createProblemBuilder(status, tipoProblema, detail)
        .userMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
        .build();

    return handleExceptionInternal(ex, problema, headers, status, request);
  }

  private Problema.ProblemaBuilder createProblemBuilder(HttpStatus status, TipoProblema tipoProblema, String detail) {
    return Problema.builder()
        .status(status.value())
        .type(tipoProblema.getUri())
        .title(tipoProblema.getTitle())
        .timestamp(OffsetDateTime.now())
        .detail(detail);
  }

}
