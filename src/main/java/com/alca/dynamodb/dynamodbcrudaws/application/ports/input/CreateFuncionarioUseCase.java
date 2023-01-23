package com.alca.dynamodb.dynamodbcrudaws.application.ports.input;

import com.alca.dynamodb.dynamodbcrudaws.domain.model.Employee;

public interface CreateFuncionarioUseCase {
  Employee createFuncionario(Employee employee);
}
