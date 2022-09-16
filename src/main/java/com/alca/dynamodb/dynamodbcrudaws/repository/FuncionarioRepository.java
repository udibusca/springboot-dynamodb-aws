package com.alca.dynamodb.dynamodbcrudaws.repository;

import com.alca.dynamodb.dynamodbcrudaws.entity.Funcionario;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import java.util.List;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


@Repository
@AllArgsConstructor
public class FuncionarioRepository {

  private final Logger LOG = LoggerFactory.getLogger(getClass());

  private DynamoDBMapper dynamoDBMapper;

  public List<Funcionario> listAll() {
    LOG.trace("readAll()");
    PaginatedList<Funcionario> results = dynamoDBMapper.scan(Funcionario.class, new DynamoDBScanExpression());
    results.loadAllResults();
    return results;
  }

  public Funcionario save(Funcionario funcionario) {
    dynamoDBMapper.save(funcionario);
    return funcionario;
  }

  public Funcionario getFuncionarioById(String funcionarioId) {
    return dynamoDBMapper.load(Funcionario.class, funcionarioId);
  }

  public String delete(String funcionarioId) {
    Funcionario funcionario = dynamoDBMapper.load(Funcionario.class, funcionarioId);
    dynamoDBMapper.delete(funcionario);
    return "Funcionário Deletado!!";
  }

  public String update(String funcionarioId, Funcionario funcionario) {
    dynamoDBMapper.save(funcionario,
        new DynamoDBSaveExpression().withExpectedEntry("funcionarioId",
            new ExpectedAttributeValue(new AttributeValue().withS(funcionarioId))));
    return funcionarioId;
  }
}
