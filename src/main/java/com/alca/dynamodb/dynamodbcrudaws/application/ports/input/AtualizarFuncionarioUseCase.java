package com.alca.dynamodb.dynamodbcrudaws.application.ports.input;

import com.alca.dynamodb.dynamodbcrudaws.domain.model.Funcionario;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.output.persistence.entity.FuncionarioEntity;

public interface AtualizarFuncionarioUseCase {
  Funcionario atualizar(String funcionarioId, FuncionarioEntity funcionario);
}
