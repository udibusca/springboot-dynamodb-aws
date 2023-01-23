package com.alca.dynamodb.dynamodbcrudaws.domain.model;

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
public class Employee {

  private String funcionarioId;

  private String nome;

  private Departament departament;
}
