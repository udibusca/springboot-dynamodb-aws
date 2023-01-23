package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest;

import com.alca.dynamodb.dynamodbcrudaws.application.ports.input.CreateFuncionarioUseCase;
import com.alca.dynamodb.dynamodbcrudaws.application.ports.input.ListAllFuncionarioUseCase;
import com.alca.dynamodb.dynamodbcrudaws.domain.model.Employee;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest.data.request.EmployeeRequest;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest.data.response.EmployeeResponse;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest.mapper.EmployeeRestMapper;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class FuncionarioRestAdapter {

  private final CreateFuncionarioUseCase createFuncionarioUseCase;
  private final ListAllFuncionarioUseCase allFuncionarioUseCase;
  private final EmployeeRestMapper employeeRestMapper;

  public FuncionarioRestAdapter(CreateFuncionarioUseCase createFuncionarioUseCase,
      ListAllFuncionarioUseCase allFuncionarioUseCase, EmployeeRestMapper employeeRestMapper) {
    this.createFuncionarioUseCase = createFuncionarioUseCase;
    this.allFuncionarioUseCase = allFuncionarioUseCase;
    this.employeeRestMapper = employeeRestMapper;
  }

  @PostMapping(value = "/funcionario")
  public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody @Valid EmployeeRequest employeeCreateRequest) {
    Employee employee = this.employeeRestMapper.toEmployee(employeeCreateRequest);
    employee = this.createFuncionarioUseCase.createFuncionario(employee);
    return new ResponseEntity<>(this.employeeRestMapper.toEmployeeCreateResponse(employee), HttpStatus.CREATED);
  }

  @GetMapping(value = "/funcionario")
  public ResponseEntity<List<EmployeeResponse>> listAll() {
    List<Employee> funcioanarios = this.allFuncionarioUseCase.listAll();
    if (funcioanarios.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(this.employeeRestMapper.toEmployeeCreateListResponse(funcioanarios), HttpStatus.OK);
  }


}
