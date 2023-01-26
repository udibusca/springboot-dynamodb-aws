package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.output.persistence.mapper;

import com.alca.dynamodb.dynamodbcrudaws.domain.model.Funcionario;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.output.persistence.entity.FuncionarioEntity;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioPersistenceMapper {

  public FuncionarioEntity toFuncionarioEntity(Funcionario funcionario) {
    if (funcionario == null)
      return null;
    return FuncionarioEntity.builder()
        .funcionarioId(funcionario.getFuncionarioId())
        .nome(funcionario.getNome())
        .departamentoEntity(funcionario.getDepartamento() != null ?
            DepartamentoPersistenceMapper.criarDepartamentoEntity(funcionario.getDepartamento()) :
            null)
        .build();
  }

  public Funcionario toFuncionario(FuncionarioEntity funcionarioEntity){
    if (funcionarioEntity == null)
      return null;
    return Funcionario.builder()
        .funcionarioId(funcionarioEntity.getFuncionarioId())
        .nome(funcionarioEntity.getNome())
        .departamento(funcionarioEntity.getDepartamentoEntity() != null ?
            DepartamentoPersistenceMapper.criarDepartamento(funcionarioEntity.getDepartamentoEntity()) :
            null)
        .build();
  }

  public List<Funcionario> toListEmployee(List<FuncionarioEntity> list) {
    return list.stream()
        .map(employee -> toFuncionario(employee))
        .collect(Collectors.toList());
  }
}
