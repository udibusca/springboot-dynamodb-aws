package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest.data.request;

import javax.validation.constraints.NotEmpty;
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
public class DepartamentRequest {

  @NotEmpty(message = "O codigo não pode estar vazio")
  private String codigo;

  @NotEmpty(message = "O nome não pode estar vazio")
  private String nome;
}
