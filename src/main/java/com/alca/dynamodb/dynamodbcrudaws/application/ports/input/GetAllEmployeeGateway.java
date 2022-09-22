package com.alca.dynamodb.dynamodbcrudaws.application.ports.input;

import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.output.persistence.entity.FuncionarioEntity;
import java.util.List;

public interface GetAllEmployeeGateway {
  List<FuncionarioEntity> getAllEmployee();
}
