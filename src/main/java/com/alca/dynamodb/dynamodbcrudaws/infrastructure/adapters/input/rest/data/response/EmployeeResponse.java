package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest.data.response;

import com.alca.dynamodb.dynamodbcrudaws.domain.model.Departament;
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
public class EmployeeResponse {

  private String funcionarioId;

  private String nome;

  private Departament departament;
}
