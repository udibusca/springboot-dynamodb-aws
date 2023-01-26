package com.alca.dynamodb.dynamodbcrudaws.domain.service;

import com.alca.dynamodb.dynamodbcrudaws.application.ports.input.AtualizarFuncionarioUseCase;
import com.alca.dynamodb.dynamodbcrudaws.application.ports.input.BuscarFuncionarioPorIdUseCase;
import com.alca.dynamodb.dynamodbcrudaws.application.ports.input.CriarFuncionarioUseCase;
import com.alca.dynamodb.dynamodbcrudaws.application.ports.input.DeletarFuncionarioUseCase;
import com.alca.dynamodb.dynamodbcrudaws.application.ports.input.ListarFuncionarioUseCase;
import com.alca.dynamodb.dynamodbcrudaws.application.ports.output.FuncionarioOutputPort;
import com.alca.dynamodb.dynamodbcrudaws.application.ports.output.FuncionarioEventPublisher;
import com.alca.dynamodb.dynamodbcrudaws.domain.event.FuncionarioCriadoEvent;
import com.alca.dynamodb.dynamodbcrudaws.domain.exception.FuncionarioNotFoundException;
import com.alca.dynamodb.dynamodbcrudaws.domain.model.Funcionario;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.output.persistence.entity.FuncionarioEntity;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class FuncionarioService implements CriarFuncionarioUseCase, ListarFuncionarioUseCase ,
    BuscarFuncionarioPorIdUseCase , DeletarFuncionarioUseCase, AtualizarFuncionarioUseCase {

  private final Logger LOGG = LoggerFactory.getLogger(getClass());
  private final FuncionarioOutputPort funcionarioOutputPort;
  private final FuncionarioEventPublisher funcionarioEventPublisher;

  @Override
  public Funcionario salvar(Funcionario funcionario) {
    LOGG.trace("Entrando createFuncionario() com {}", funcionario);
    funcionario = this.funcionarioOutputPort.salvar(funcionario);
    funcionarioEventPublisher.publishEmployeeCreatedEvent(new FuncionarioCriadoEvent(funcionario.getFuncionarioId()));
    return funcionario;
  }

  @Override
  public List<Funcionario> listar() {
    LOGG.trace("Entrando listAll() com {}");
    return funcionarioOutputPort.listar();
  }

  @Override
  public Funcionario atualizar(String funcionarioId, Funcionario funcionario) throws FuncionarioNotFoundException {
    return funcionarioOutputPort.atualizar(funcionarioId, funcionario)
        .orElseThrow(() -> new FuncionarioNotFoundException("Funcionario com o id: " +funcionarioId+ " não encontrado"));
  }

  @Override
  public Funcionario buscarPorId(String funcionarioId) throws FuncionarioNotFoundException {
    return funcionarioOutputPort.buscarPorId(funcionarioId)
        .orElseThrow(() -> new FuncionarioNotFoundException("Funcionario com o id: " +funcionarioId+ " não encontrado"));
  }

  @Override
  public Boolean deletar(String funcionarioId) throws FuncionarioNotFoundException {
    if (!funcionarioOutputPort.deletar(funcionarioId).get())
      throw new FuncionarioNotFoundException("Funcionario com o id: " +funcionarioId+ " não encontrado");
    return true;
  }
}
