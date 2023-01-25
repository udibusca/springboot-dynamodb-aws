package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.output.persistence;

import com.alca.dynamodb.dynamodbcrudaws.application.ports.output.FuncionarioOutputPort;
import com.alca.dynamodb.dynamodbcrudaws.domain.model.Funcionario;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.output.persistence.entity.FuncionarioEntity;
import com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.output.persistence.mapper.FuncionarioPersistenceMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioPersistenceAdapter implements FuncionarioOutputPort {

  private final DynamoDBMapper dynamoDBMapper;
  private final FuncionarioPersistenceMapper funcionarioPersistenceMapper;

  public FuncionarioPersistenceAdapter(DynamoDBMapper dynamoDBMapper, FuncionarioPersistenceMapper funcionarioPersistenceMapper) {
    this.dynamoDBMapper = dynamoDBMapper;
    this.funcionarioPersistenceMapper = funcionarioPersistenceMapper;
  }

  @Override
  public Funcionario salvar(Funcionario funcionario) {
    FuncionarioEntity funcionarioEntityNew = this.funcionarioPersistenceMapper.toEmployeeEntity(funcionario);

    funcionarioEntityNew = new FuncionarioEntity(UUID.randomUUID().toString(), funcionarioEntityNew);
    dynamoDBMapper.save(funcionarioEntityNew);
    return funcionarioPersistenceMapper.toEmployee(funcionarioEntityNew);
  }

  @Override
  public List<Funcionario> listar() {
    PaginatedList<FuncionarioEntity> results = dynamoDBMapper.scan(FuncionarioEntity.class, new DynamoDBScanExpression());
    results.loadAllResults();
    return funcionarioPersistenceMapper.toListEmployee(results);
  }

  @Override
  public Optional<Funcionario> buscarPorId(String funcionarioId) {
    FuncionarioEntity result = dynamoDBMapper.load(FuncionarioEntity.class, funcionarioId);
    return Optional.of(this.funcionarioPersistenceMapper.toEmployee(result));
  }

  @Override
  public Optional<Boolean> deletar(String funcionarioId) {
    FuncionarioEntity funcionario = dynamoDBMapper.load(FuncionarioEntity.class, funcionarioId);
    if (funcionario != null){
      dynamoDBMapper.delete(funcionario);
    } else {
      Optional.of(Boolean.FALSE);
  }
    return Optional.of(Boolean.TRUE);
  }

  @Override
  public Optional<Funcionario> atualizar(String funcionarioId, FuncionarioEntity funcionario) {
    dynamoDBMapper.save(funcionario,
        new DynamoDBSaveExpression().withExpectedEntry("funcionarioId",
            new ExpectedAttributeValue(new AttributeValue().withS(funcionarioId))));
    return Optional.empty();
  }
}
