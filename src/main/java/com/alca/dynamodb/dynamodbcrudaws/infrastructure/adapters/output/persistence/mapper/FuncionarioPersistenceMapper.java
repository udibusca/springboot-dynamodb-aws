package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.output.persistence.mapper;

import com.alca.dynamodb.dynamodbcrudaws.domain.model.Funcionario;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.output.persistence.entity.FuncionarioEntity;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioPersistenceMapper {

  public FuncionarioEntity toEmployeeEntity(Funcionario funcionario) {
    FuncionarioEntity entity = new FuncionarioEntity();
    entity.setFuncionarioId(funcionario.getFuncionarioId());
    entity.setNome(funcionario.getNome());
    //entity.setDepartamentoEntity(employee.getDepartament());
    return entity;
  }

  public Funcionario toEmployee(FuncionarioEntity funcionarioEntity){
    if (funcionarioEntity == null)
      return null;
    return Funcionario.builder()
        .funcionarioId(funcionarioEntity.getFuncionarioId())
        .nome(funcionarioEntity.getNome())
        //.departamento(funcionarioEntity.getDepartamentoEntity())
        .build();
  }

  public List<Funcionario> toListEmployee(List<FuncionarioEntity> list) {
    return list.stream()
        .map(employee -> toEmployee(employee))
        .collect(Collectors.toList());
  }
}
