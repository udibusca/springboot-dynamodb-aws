package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.output.persistence.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@DynamoDBDocument
@NoArgsConstructor
@AllArgsConstructor
public class DepartamentoEntity {

  @DynamoDBAttribute
  private String nome;

  @DynamoDBAttribute
  private String codigo;
}
