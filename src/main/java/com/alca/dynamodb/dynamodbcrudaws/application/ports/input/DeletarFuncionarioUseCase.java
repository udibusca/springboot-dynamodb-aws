package com.alca.dynamodb.dynamodbcrudaws.application.ports.input;

import com.alca.dynamodb.dynamodbcrudaws.domain.exception.FuncionarioNotFoundException;

public interface DeletarFuncionarioUseCase {
  Boolean deletar(String funcionarioId) throws FuncionarioNotFoundException;
}
