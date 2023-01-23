/*
package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest;

import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.output.persistence.entity.EmployeeEntity;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.output.persistence.EmployeePersistenceAdapter;
import java.util.List;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/funcionario")
public class EmployeeRestAdapter {

  private final Logger LOG = LoggerFactory.getLogger(getClass());

  private EmployeePersistenceAdapter funcionarioRepository;

  @GetMapping
  public List<EmployeeEntity> getFuncionarios() {
    return funcionarioRepository.listAll();
  }

  @GetMapping("/{id}")
  public EmployeeEntity getFuncionarioById(@PathVariable("id") String funcionarioId) {
    return funcionarioRepository.getFuncionarioById(funcionarioId);
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable("id") String funcionarioId) {
    return funcionarioRepository.delete(funcionarioId);
  }

  @PutMapping("/{id}")
  public String update(@PathVariable("id") String funcionarioId, @RequestBody EmployeeEntity funcionario) {
    return funcionarioRepository.update(funcionarioId, funcionario);
  }

}
*/