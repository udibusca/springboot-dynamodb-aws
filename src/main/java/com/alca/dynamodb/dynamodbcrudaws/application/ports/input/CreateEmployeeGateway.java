package com.alca.dynamodb.dynamodbcrudaws.application.ports.input;

import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.output.persistence.entity.FuncionarioEntity;

public interface CreateEmployeeGateway {
  FuncionarioEntity createEmployee(FuncionarioEntity employee);
}
