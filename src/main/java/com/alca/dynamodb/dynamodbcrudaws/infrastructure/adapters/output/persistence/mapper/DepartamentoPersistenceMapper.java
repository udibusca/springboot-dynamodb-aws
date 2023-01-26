package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.output.persistence.mapper;

import com.alca.dynamodb.dynamodbcrudaws.domain.model.Departamento;
import com.alca.dynamodb.dynamodbcrudaws.domain.model.Funcionario;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest.data.request.DepartamentoRequest;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.output.persistence.entity.DepartamentoEntity;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.output.persistence.entity.FuncionarioEntity;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class DepartamentoPersistenceMapper {

  public static DepartamentoEntity criarDepartamentoEntity(Departamento departamento){
    return DepartamentoEntity.builder()
        .codigo(departamento.getCodigo())
        .nome(departamento.getNome())
        .build();
  }

  public static Departamento criarDepartamento(DepartamentoEntity departamentoEntity){
    return Departamento.builder()
        .codigo(departamentoEntity.getCodigo())
        .nome(departamentoEntity.getNome())
        .build();
  }
}
