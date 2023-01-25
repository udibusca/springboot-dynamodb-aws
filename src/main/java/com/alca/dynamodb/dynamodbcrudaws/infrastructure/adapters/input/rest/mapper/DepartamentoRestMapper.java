package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest.mapper;

import com.alca.dynamodb.dynamodbcrudaws.domain.model.Departamento;
import com.alca.dynamodb.dynamodbcrudaws.domain.model.Funcionario;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest.data.request.DepartamentoRequest;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest.data.request.FuncionarioRequest;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest.data.response.FuncionarioResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class DepartamentoRestMapper {

    public static Departamento criarDepartamento(DepartamentoRequest departamentoRequest){
        return Departamento.builder()
            .codigo(departamentoRequest.getCodigo())
            .nome(departamentoRequest.getNome())
        .build();
    }
}
