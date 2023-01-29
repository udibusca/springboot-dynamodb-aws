package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest.data.response;

import com.alca.dynamodb.dynamodbcrudaws.domain.model.Departamento;
import com.alca.dynamodb.dynamodbcrudaws.domain.model.Funcionario;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
@JsonPropertyOrder({"id_funcionario", "nome_funcionario","departamento"})
public class FuncionarioResponse extends RepresentationModel<FuncionarioResponse> {

  @JsonProperty(value = "id_funcionario")
  private String funcionarioId;

  @JsonProperty(value = "nome_funcionario")
  private String nome;

  @JsonProperty(value = "departamento")
  private Departamento departamento;
}
