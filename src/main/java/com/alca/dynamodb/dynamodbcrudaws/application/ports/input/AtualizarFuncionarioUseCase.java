package com.alca.dynamodb.dynamodbcrudaws.application.ports.input;

import com.alca.dynamodb.dynamodbcrudaws.domain.exception.FuncionarioNotFoundException;
import com.alca.dynamodb.dynamodbcrudaws.domain.model.Funcionario;

public interface AtualizarFuncionarioUseCase {
  Funcionario atualizar(String funcionarioId, Funcionario funcionario) throws FuncionarioNotFoundException;
}
