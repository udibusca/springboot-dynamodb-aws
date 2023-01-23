package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest.mapper;

import com.alca.dynamodb.dynamodbcrudaws.domain.model.Employee;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest.data.request.EmployeeRequest;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest.data.response.EmployeeResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeRestMapper {
    public Employee toEmployee(EmployeeRequest employeeCreateRequest){
      return Employee.builder()
          .funcionarioId(employeeCreateRequest.getFuncionarioId())
          .nome(employeeCreateRequest.getNome())
          //.departament(employeeCreateRequest.getDepartamentRequest())
          .build();
    }

    public EmployeeResponse toEmployeeCreateResponse(Employee employee){
    return EmployeeResponse.builder()
        .funcionarioId(employee.getFuncionarioId())
        .nome(employee.getNome())
        .departament(employee.getDepartament())
        .build();
    }

    public List<EmployeeResponse> toEmployeeCreateListResponse(List<Employee> list) {
        return list.stream()
            .map(employee -> toEmployeeCreateResponse(employee))
            .collect(Collectors.toList());
    }
}
