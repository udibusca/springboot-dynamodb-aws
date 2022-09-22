package com.alca.dynamodb.dynamodbcrudaws.application.ports.input;

public interface DeleteEmployeeGateway {
  String deleteEmployee(String employeeId);
}
