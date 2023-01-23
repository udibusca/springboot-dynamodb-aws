package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.output.persistence.mapper;

import com.alca.dynamodb.dynamodbcrudaws.domain.model.Employee;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.output.persistence.entity.EmployeeEntity;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class EmployeePersistenceMapper {

  public EmployeeEntity toEmployeeEntity(Employee employee) {
    EmployeeEntity entity = new EmployeeEntity();
    entity.setFuncionarioId(employee.getFuncionarioId());
    entity.setNome(employee.getNome());
    //entity.setDepartamentoEntity(employee.getDepartament());
    return entity;
  }

  public Employee toEmployee(EmployeeEntity employeeEntity){
    Employee model = new Employee();
    model.setFuncionarioId(employeeEntity.getFuncionarioId());
    model.setNome(employeeEntity.getNome());
    //model.setDepartamentoEntity(employee.getDepartament());
    return model;
  }

  public List<Employee> toListEmployee(List<EmployeeEntity> list) {
    return list.stream()
        .map(employee -> toEmployee(employee))
        .collect(Collectors.toList());
  }
}
