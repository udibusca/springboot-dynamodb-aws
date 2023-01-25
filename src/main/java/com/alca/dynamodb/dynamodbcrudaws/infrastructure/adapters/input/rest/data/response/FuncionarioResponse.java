package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest.data.response;

import com.alca.dynamodb.dynamodbcrudaws.domain.model.Departamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioResponse {

  private String funcionarioId;

  private String nome;

  private Departamento departamento;
}
