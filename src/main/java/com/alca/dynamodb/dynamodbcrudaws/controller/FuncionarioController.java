package com.alca.dynamodb.dynamodbcrudaws.controller;

import com.alca.dynamodb.dynamodbcrudaws.entity.Funcionario;
import com.alca.dynamodb.dynamodbcrudaws.repository.FuncionarioRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@AllArgsConstructor
@RequestMapping("/v1/funcionario")
public class FuncionarioController {

  private final Logger LOG = LoggerFactory.getLogger(getClass());

  private FuncionarioRepository funcionarioRepository;

  @GetMapping
  public List<Funcionario> getFuncionarios() {
    LOG.trace("Retorno list()");
    return funcionarioRepository.listAll();
  }

  @PostMapping
  public Funcionario save(@RequestBody Funcionario funcionario) {
    return funcionarioRepository.save(funcionario);
  }

  @GetMapping("/{id}")
  public Funcionario getFuncionarioById(@PathVariable("id") String funcionarioId) {
    return funcionarioRepository.getFuncionarioById(funcionarioId);
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable("id") String funcionarioId) {
    return funcionarioRepository.delete(funcionarioId);
  }

  @PutMapping("/{id}")
  public String update(@PathVariable("id") String funcionarioId, @RequestBody Funcionario funcionario) {
    return funcionarioRepository.update(funcionarioId, funcionario);
  }

}
