package com.alca.dynamodb.dynamodbcrudaws.application.ports.output;

import com.alca.dynamodb.dynamodbcrudaws.domain.model.Funcionario;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.output.persistence.entity.FuncionarioEntity;
import java.util.List;
import java.util.Optional;

public interface FuncionarioOutputPort {

  Funcionario salvar(Funcionario funcionario);

  List<Funcionario> listar();

  Optional<Funcionario> buscarPorId(String funcionarioId);

  Optional<Boolean> deletar(String funcionarioId);

  Optional<Funcionario> atualizar(String funcionarioId, FuncionarioEntity funcionario);
}
