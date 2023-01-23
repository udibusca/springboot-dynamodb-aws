package com.alca.dynamodb.dynamodbcrudaws.application.ports.output;

import com.alca.dynamodb.dynamodbcrudaws.domain.model.Employee;
import java.util.List;
import java.util.Optional;

public interface FuncionarioOutputPort {

  Employee saveEmployee(Employee employee);

  List<Employee> listAll();

  Optional<Employee> getById(String funcionarioId);
}
