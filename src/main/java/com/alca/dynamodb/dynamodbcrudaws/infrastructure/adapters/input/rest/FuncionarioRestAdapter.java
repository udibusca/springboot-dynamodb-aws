package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest;

import com.alca.dynamodb.dynamodbcrudaws.application.ports.input.AtualizarFuncionarioUseCase;
import com.alca.dynamodb.dynamodbcrudaws.application.ports.input.BuscarFuncionarioPorIdUseCase;
import com.alca.dynamodb.dynamodbcrudaws.application.ports.input.CriarFuncionarioUseCase;
import com.alca.dynamodb.dynamodbcrudaws.application.ports.input.DeletarFuncionarioUseCase;
import com.alca.dynamodb.dynamodbcrudaws.application.ports.input.ListarFuncionarioUseCase;
import com.alca.dynamodb.dynamodbcrudaws.domain.exception.FuncionarioNotFoundException;
import com.alca.dynamodb.dynamodbcrudaws.domain.model.Funcionario;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest.data.request.FuncionarioRequest;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest.data.response.FuncionarioResponse;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest.data.response.Response;
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
  public ResponseEntity<Response<FuncionarioResponse>> criarFuncionario(@RequestBody @Valid FuncionarioRequest employeeCreateRequest) {
    Response<FuncionarioResponse> response = new Response<>();

    Funcionario funcionario = funcionarioRestMapper.toFuncionario(employeeCreateRequest);
    funcionario = criarFuncionarioUseCase.salvar(funcionario);

    response.setData(funcionarioRestMapper.criarResponse(funcionario));
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<Response<List<FuncionarioResponse>>> listarFuncionarios() throws FuncionarioNotFoundException {
    Response<List<FuncionarioResponse>> response = new Response<>();

    List<Funcionario> funcioanarios = allFuncionarioUseCase.listar();
    if (funcioanarios.isEmpty()) {
      throw new FuncionarioNotFoundException();
    }

    response.setData(funcionarioRestMapper.criarListaResponse(funcioanarios));
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Response<FuncionarioResponse>> buscaFuncionarioPorId(@PathVariable("id") String funcionarioId) throws FuncionarioNotFoundException {
    Response<FuncionarioResponse> response = new Response<>();
    Funcionario funcionario = porIdUseCase.buscarPorId(funcionarioId);

    response.setData(funcionarioRestMapper.criarResponse(funcionario));
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Response<FuncionarioResponse>> atualizarFuncionario(@PathVariable("id") String funcionarioId,
      @RequestBody FuncionarioRequest funcionarioRequestonario) throws FuncionarioNotFoundException {
    Response<FuncionarioResponse> response = new Response<>();
    Funcionario funcionarioAtualiza = funcionarioRestMapper.toFuncionario(funcionarioRequestonario);

    Funcionario funcionarioNovo = atualizarFuncionarioUseCase.atualizar(funcionarioId, funcionarioAtualiza);

    response.setData(funcionarioRestMapper.criarResponse(funcionarioNovo));
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Response<String>> deletarFuncionario(@PathVariable("id") String funcionarioId) throws FuncionarioNotFoundException {
    Response<String> response = new Response<>();

    deletarFuncionarioUseCase.deletar(funcionarioId);

    response.setData("Funcionario id=" + funcionarioId + " deletado com sucesso");
    return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
  }
}
