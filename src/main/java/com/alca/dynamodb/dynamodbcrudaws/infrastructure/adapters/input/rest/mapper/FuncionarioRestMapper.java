package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest.mapper;

import com.alca.dynamodb.dynamodbcrudaws.domain.model.Funcionario;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest.data.request.FuncionarioRequest;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest.data.response.FuncionarioResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioRestMapper {
    public Funcionario toFuncionario(FuncionarioRequest employeeCreateRequest){
      return Funcionario.builder()
          .funcionarioId(employeeCreateRequest.getFuncionarioId())
          .nome(employeeCreateRequest.getNome())
          .departamento(DepartamentoRestMapper.criarDepartamento(employeeCreateRequest.getDepartamentoRequest()))
          .build();
    }

    public FuncionarioResponse criarResponse(Funcionario funcionario){
        return FuncionarioResponse.builder()
        .funcionarioId(funcionario.getFuncionarioId())
        .nome(funcionario.getNome())
        .departamento(funcionario.getDepartamento())
        .build();
    }

    public List<FuncionarioResponse> criarListaResponse(List<Funcionario> list) {
        return list.stream()
            .map(employee -> criarResponse(employee))
            .collect(Collectors.toList());
    }
}
