package com.alca.dynamodb.dynamodbcrudaws.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Funcionario {

  private String funcionarioId;

  private String nome;

  private Departamento departamento;
}
