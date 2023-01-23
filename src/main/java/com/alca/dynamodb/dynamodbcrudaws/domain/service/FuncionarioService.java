package com.alca.dynamodb.dynamodbcrudaws.domain.service;

import com.alca.dynamodb.dynamodbcrudaws.application.ports.input.CreateFuncionarioUseCase;
import com.alca.dynamodb.dynamodbcrudaws.application.ports.input.ListAllFuncionarioUseCase;
import com.alca.dynamodb.dynamodbcrudaws.application.ports.output.FuncionarioOutputPort;
import com.alca.dynamodb.dynamodbcrudaws.application.ports.output.EmployeeEventPublisher;
import com.alca.dynamodb.dynamodbcrudaws.domain.event.EmployeeCreatedEvent;
import com.alca.dynamodb.dynamodbcrudaws.domain.model.Employee;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioService implements CreateFuncionarioUseCase, ListAllFuncionarioUseCase {

  private final Logger LOGG = LoggerFactory.getLogger(getClass());
  private final FuncionarioOutputPort funcionarioOutputPort;
  private final EmployeeEventPublisher employeeEventPublisher;

  public FuncionarioService(FuncionarioOutputPort funcionarioOutputPort, EmployeeEventPublisher employeeEventPublisher) {
    this.funcionarioOutputPort = funcionarioOutputPort;
    this.employeeEventPublisher = employeeEventPublisher;
  }

  @Override
  public Employee createFuncionario(Employee employee) {
    LOGG.trace("Entrando createFuncionario() com {}", employee);
    employee = this.funcionarioOutputPort.saveEmployee(employee);
    this.employeeEventPublisher.publishEmployeeCreatedEvent(new EmployeeCreatedEvent(employee.getFuncionarioId()));
    return employee;
  }

  @Override
  public List<Employee> listAll() {
    LOGG.trace("Entrando listAll() com {}");
    return funcionarioOutputPort.listAll();
  }
}
