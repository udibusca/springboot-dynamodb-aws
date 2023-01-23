package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.output.persistence;

import com.alca.dynamodb.dynamodbcrudaws.application.ports.output.FuncionarioOutputPort;
import com.alca.dynamodb.dynamodbcrudaws.domain.model.Employee;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.output.persistence.entity.EmployeeEntity;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.output.persistence.mapper.EmployeePersistenceMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.SaveBehavior;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioPersistenceAdapter implements FuncionarioOutputPort {

  private final Logger LOGG = LoggerFactory.getLogger(getClass());
  private final DynamoDBMapper dynamoDBMapper;
  private final EmployeePersistenceMapper employeePersistenceMapper;

  public FuncionarioPersistenceAdapter(DynamoDBMapper dynamoDBMapper, EmployeePersistenceMapper employeePersistenceMapper) {
    this.dynamoDBMapper = dynamoDBMapper;
    this.employeePersistenceMapper = employeePersistenceMapper;
  }

  @Override
  public Employee saveEmployee(Employee employee) {
    EmployeeEntity employeeEntityNew = this.employeePersistenceMapper.toEmployeeEntity(employee);

    employeeEntityNew = new EmployeeEntity(UUID.randomUUID().toString(), employeeEntityNew);
    dynamoDBMapper.save(employeeEntityNew);
    return this.employeePersistenceMapper.toEmployee(employeeEntityNew);
  }

  @Override
  public List<Employee> listAll() {
    PaginatedList<EmployeeEntity> results = dynamoDBMapper.scan(EmployeeEntity.class, new DynamoDBScanExpression());
    results.loadAllResults();
    return this.employeePersistenceMapper.toListEmployee(results);
  }

  @Override
  public Optional<Employee> getById(String funcionarioId) {
    LOGG.trace("Entering read() with {}", funcionarioId);
    EmployeeEntity result = dynamoDBMapper.load(EmployeeEntity.class, funcionarioId);
    return Optional.ofNullable(this.employeePersistenceMapper.toEmployee(result));
  }
}
