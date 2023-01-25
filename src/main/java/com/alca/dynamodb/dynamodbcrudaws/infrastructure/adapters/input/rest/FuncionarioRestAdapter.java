package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest;

import com.alca.dynamodb.dynamodbcrudaws.application.ports.input.AtualizarFuncionarioUseCase;
import com.alca.dynamodb.dynamodbcrudaws.application.ports.input.BuscarFuncionarioPorIdUseCase;
import com.alca.dynamodb.dynamodbcrudaws.application.ports.input.CriarFuncionarioUseCase;
import com.alca.dynamodb.dynamodbcrudaws.application.ports.input.DeletarFuncionarioUseCase;
import com.alca.dynamodb.dynamodbcrudaws.application.ports.input.ListarFuncionarioUseCase;
import com.alca.dynamodb.dynamodbcrudaws.domain.model.Funcionario;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest.data.request.FuncionarioRequest;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest.data.response.FuncionarioResponse;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest.mapper.FuncionarioRestMapper;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.output.persistence.entity.FuncionarioEntity;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/funcionario")
public class FuncionarioRestAdapter {

  private final CriarFuncionarioUseCase criarFuncionarioUseCase;
  private final ListarFuncionarioUseCase allFuncionarioUseCase;
  private final BuscarFuncionarioPorIdUseCase porIdUseCase;
  private final AtualizarFuncionarioUseCase atualizarFuncionarioUseCase;
  private final DeletarFuncionarioUseCase deletarFuncionarioUseCase;
  private final FuncionarioRestMapper funcionarioRestMapper;

  @PostMapping
  public ResponseEntity<FuncionarioResponse> criarFuncionario(@RequestBody @Valid FuncionarioRequest employeeCreateRequest) {
    Funcionario funcionario = funcionarioRestMapper.toFuncionario(employeeCreateRequest);
    funcionario = criarFuncionarioUseCase.salvar(funcionario);
    return new ResponseEntity<>(funcionarioRestMapper.criarResponse(funcionario), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<FuncionarioResponse>> listarFuncionarios() {
    List<Funcionario> funcioanarios = allFuncionarioUseCase.listar();
    if (funcioanarios.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(funcionarioRestMapper.criarListaResponse(funcioanarios), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<FuncionarioResponse> buscaFuncionarioPorId(@PathVariable("id") String funcionarioId) {
    Funcionario funcionario = porIdUseCase.buscarPorId(funcionarioId);
    return new ResponseEntity<>(funcionarioRestMapper.criarResponse(funcionario), HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> atualizarFuncionario(@PathVariable("id") String funcionarioId, @RequestBody FuncionarioEntity funcionario) {
    atualizarFuncionarioUseCase.atualizar(funcionarioId, funcionario);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deletarFuncionario(@PathVariable("id") String funcionarioId) {
    deletarFuncionarioUseCase.deletar(funcionarioId);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
