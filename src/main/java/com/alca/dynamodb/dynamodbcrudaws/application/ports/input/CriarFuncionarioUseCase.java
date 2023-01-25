package com.alca.dynamodb.dynamodbcrudaws.application.ports.input;

import com.alca.dynamodb.dynamodbcrudaws.domain.model.Funcionario;

public interface CriarFuncionarioUseCase {
  Funcionario salvar(Funcionario funcionario);
}
