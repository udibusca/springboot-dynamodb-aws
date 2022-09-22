package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.output.persistence.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedJson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "funcionario")
public class FuncionarioEntity {

  @DynamoDBHashKey
  private String funcionarioId;

  @DynamoDBAttribute
  private String nome;

  @DynamoDBAttribute
  @DynamoDBTypeConvertedJson(targetType = DepartamentoEntity.class)
  private DepartamentoEntity departamento;

  public FuncionarioEntity(String funcionarioId, FuncionarioEntity funcionario) {
    this.funcionarioId = funcionarioId;
    this.nome = funcionario.getNome();
    this.departamento = funcionario.getDepartamento();
  }
}