/*
package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.output.persistence;


import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.output.persistence.entity.EmployeeEntity;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.output.persistence.mapper.EmployeePersistenceMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class EmployeePersistenceAdapter {

  private final DynamoDBMapper dynamoDBMapper;
  private final EmployeePersistenceMapper employeePersistenceMapper;

  public EmployeePersistenceAdapter(DynamoDBMapper dynamoDBMapper, EmployeePersistenceMapper employeePersistenceMapper) {
    this.dynamoDBMapper = dynamoDBMapper;
    this.employeePersistenceMapper = employeePersistenceMapper;
  }

  public List<EmployeeEntity> listAll() {
    PaginatedList<EmployeeEntity> results = dynamoDBMapper.scan(EmployeeEntity.class, new DynamoDBScanExpression());
    results.loadAllResults();
    return results;
  }

  public EmployeeEntity save(EmployeeEntity funcionario) {
    EmployeeEntity novoFuncionario = new EmployeeEntity(UUID.randomUUID().toString(), funcionario);
    dynamoDBMapper.save(novoFuncionario);
    return novoFuncionario;
  }

  public EmployeeEntity getFuncionarioById(String funcionarioId) {
    return dynamoDBMapper.load(EmployeeEntity.class, funcionarioId);
  }

  public String delete(String funcionarioId) {
    EmployeeEntity funcionario = dynamoDBMapper.load(EmployeeEntity.class, funcionarioId);
    dynamoDBMapper.delete(funcionario);
    return "Funcionário Deletado!!";
  }

  public String update(String funcionarioId, EmployeeEntity funcionario) {
    dynamoDBMapper.save(funcionario,
        new DynamoDBSaveExpression().withExpectedEntry("funcionarioId",
            new ExpectedAttributeValue(new AttributeValue().withS(funcionarioId))));
    return funcionarioId;
  }
}*/
