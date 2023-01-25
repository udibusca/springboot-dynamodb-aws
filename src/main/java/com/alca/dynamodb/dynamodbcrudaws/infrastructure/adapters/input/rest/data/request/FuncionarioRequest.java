package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest.data.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class FuncionarioRequest {

  private String funcionarioId;

  @NotEmpty(message = "O nome não pode estar vazio")
  private String nome;

  @JsonProperty("departamento")
  private DepartamentoRequest departamentoRequest;
}
