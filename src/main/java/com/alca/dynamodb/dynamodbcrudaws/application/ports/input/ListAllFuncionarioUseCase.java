package com.alca.dynamodb.dynamodbcrudaws.application.ports.input;

import com.alca.dynamodb.dynamodbcrudaws.domain.model.Employee;
import java.util.List;

public interface ListAllFuncionarioUseCase {
  List<Employee> listAll();
}
