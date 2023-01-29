package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest.data.response;

import com.alca.dynamodb.dynamodbcrudaws.domain.model.Departamento;
import com.alca.dynamodb.dynamodbcrudaws.domain.model.Funcionario;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncionarioResponse extends RepresentationModel<FuncionarioResponse> {

  @JsonProperty(value = "id_funcionario")
  private String funcionarioId;

  @JsonProperty(value = "nome_funcionario")
  private String nome;

  private Departamento departamento;
}
