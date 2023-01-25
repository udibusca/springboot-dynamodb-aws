package com.alca.dynamodb.dynamodbcrudaws.application.ports.input;

import com.alca.dynamodb.dynamodbcrudaws.domain.model.Funcionario;
import java.util.List;

public interface ListarFuncionarioUseCase {
  List<Funcionario> listar();
}
