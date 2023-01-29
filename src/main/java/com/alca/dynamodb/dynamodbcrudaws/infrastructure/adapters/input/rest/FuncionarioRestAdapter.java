package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.hateoas.Link;
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

@Log4j2
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
  @ApiOperation(value = "Rota para criar um funcionario")
  public ResponseEntity<Response<FuncionarioResponse>> criarFuncionario(@RequestBody @Valid FuncionarioRequest employeeCreateRequest) {
    Response<FuncionarioResponse> response = new Response<>();

    Funcionario newFuncionario = funcionarioRestMapper.toFuncionario(employeeCreateRequest);
    newFuncionario = criarFuncionarioUseCase.salvar(newFuncionario);

    FuncionarioResponse funcionarioResponse = funcionarioRestMapper.criarResponse(newFuncionario);

    createSelfLinkHateoas(newFuncionario, funcionarioResponse);

    response.setData(funcionarioResponse);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping
  @ApiOperation(value = "Rota para listar todos os funcionarios")
  public ResponseEntity<Response<List<FuncionarioResponse>>> listarFuncionarios() throws FuncionarioNotFoundException {
    Response<List<FuncionarioResponse>> response = new Response<>();

    List<Funcionario> funcioanarios = allFuncionarioUseCase.listar();
    if (funcioanarios.isEmpty()) {
      throw new FuncionarioNotFoundException();
    }
    List<FuncionarioResponse> funcionarioResponse = funcionarioRestMapper.criarListaResponse(funcioanarios);

    funcionarioResponse.stream().forEach(dto -> {
      try {
        createSelfLinkHateoasCollections(dto);
      } catch (FuncionarioNotFoundException e) {
        log.error("Não econtrado registros de funcionarios = {}");
      }
    });

    response.setData(funcionarioResponse);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  @ApiOperation(value = "Rota para buscar um funcionario por id")
  public ResponseEntity<Response<FuncionarioResponse>> buscaFuncionarioPorId(@PathVariable("id") String funcionarioId) throws FuncionarioNotFoundException {
    Response<FuncionarioResponse> response = new Response<>();
    Funcionario funcionario = porIdUseCase.buscarPorId(funcionarioId);

    FuncionarioResponse funcionarioResponse = funcionarioRestMapper.criarResponse(funcionario);

    createSelfLinkHateoas(funcionario, funcionarioResponse);

    response.setData(funcionarioResponse);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  @ApiOperation(value = "Rota para atualizar um funcionario por id")
  public ResponseEntity<Response<FuncionarioResponse>> atualizarFuncionario(@PathVariable("id") String funcionarioId,
      @RequestBody FuncionarioRequest funcionarioRequestonario) throws FuncionarioNotFoundException {
    Response<FuncionarioResponse> response = new Response<>();
    Funcionario funcionarioAtualiza = funcionarioRestMapper.toFuncionario(funcionarioRequestonario);

    Funcionario funcionarioNovo = atualizarFuncionarioUseCase.atualizar(funcionarioId, funcionarioAtualiza);

    FuncionarioResponse funcionarioResponse = funcionarioRestMapper.criarResponse(funcionarioNovo);

    createSelfLinkHateoas(funcionarioNovo, funcionarioResponse);

    response.setData(funcionarioResponse);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @ApiOperation(value = "Rota para deletar um funcionario por id")
  public ResponseEntity<Response<String>> deletarFuncionario(@PathVariable("id") String funcionarioId) throws FuncionarioNotFoundException {
    Response<String> response = new Response<>();

    deletarFuncionarioUseCase.deletar(funcionarioId);

    response.setData("Funcionario id=" + funcionarioId + " deletado com sucesso");
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  /**
   * Método que cria um self link para o objeto Funcionario
   */
  private void createSelfLinkHateoas(Funcionario funcionario, FuncionarioResponse funcionarioResponse) {
    Link selfLink = linkTo(FuncionarioRestAdapter.class).slash(funcionario.getFuncionarioId()).withSelfRel();
    funcionarioResponse.add(selfLink);
  }

  /**
   * Método que cria um self link em uma coleção de Funcionarios
   */
  private void createSelfLinkHateoasCollections(final FuncionarioResponse funcionarioResponse)
      throws FuncionarioNotFoundException {
    Link selfLink = linkTo(methodOn(FuncionarioRestAdapter.class).buscaFuncionarioPorId(funcionarioResponse.getFuncionarioId()))
        .withSelfRel().expand();
    funcionarioResponse.add(selfLink);
  }

}
