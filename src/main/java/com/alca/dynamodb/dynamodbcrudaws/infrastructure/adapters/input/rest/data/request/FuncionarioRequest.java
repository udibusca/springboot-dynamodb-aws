package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest.data.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FuncionarioRequest {

  private String funcionarioId;

  @NotEmpty(message = "O nome não pode estar vazio")
  @Length(min=3, message="Nome deve ter no minimo 6 caracteres")
  private String nome;

  @JsonProperty("departamento")
  private DepartamentoRequest departamentoRequest;
}
