package com.alca.dynamodb.dynamodbcrudaws.repository;

import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.output.persistence.entity.FuncionarioEntity;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


@Repository
@AllArgsConstructor
public class FuncionarioRepository {

  private final Logger LOG = LoggerFactory.getLogger(getClass());

  private DynamoDBMapper dynamoDBMapper;

  public List<FuncionarioEntity> listAll() {
    LOG.info("Entrando em listAll()");
    PaginatedList<FuncionarioEntity> results = dynamoDBMapper.scan(FuncionarioEntity.class, new DynamoDBScanExpression());
    results.loadAllResults();
    return results;
  }

  public FuncionarioEntity save(FuncionarioEntity funcionario) {
    LOG.info("Entrando em save() with {}", funcionario);
    FuncionarioEntity novoFuncionario = new FuncionarioEntity(UUID.randomUUID().toString(), funcionario);
    dynamoDBMapper.save(novoFuncionario);
    return novoFuncionario;
  }

  public FuncionarioEntity getFuncionarioById(String funcionarioId) {
    LOG.info("Entrando em getFuncionarioById() with {}", funcionarioId);
    return dynamoDBMapper.load(FuncionarioEntity.class, funcionarioId);
  }

  public String delete(String funcionarioId) {
    FuncionarioEntity funcionario = dynamoDBMapper.load(FuncionarioEntity.class, funcionarioId);
    dynamoDBMapper.delete(funcionario);
    return "Funcionário Deletado!!";
  }

  public String update(String funcionarioId, FuncionarioEntity funcionario) {
    dynamoDBMapper.save(funcionario,
        new DynamoDBSaveExpression().withExpectedEntry("funcionarioId",
            new ExpectedAttributeValue(new AttributeValue().withS(funcionarioId))));
    return funcionarioId;
  }
}
