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
  private final Logger LOGGER = LoggerFactory.getLogger(getClass());
  private final DynamoDBMapper dynamoDBMapper;
  private final FuncionarioPersistenceMapper funcionarioPersistenceMapper;

  public FuncionarioPersistenceAdapter(DynamoDBMapper dynamoDBMapper, FuncionarioPersistenceMapper funcionarioPersistenceMapper) {
    this.dynamoDBMapper = dynamoDBMapper;
    this.funcionarioPersistenceMapper = funcionarioPersistenceMapper;
  }

  @Override
  public Funcionario salvar(Funcionario funcionario) {
    LOGGER.trace("Entrando no metodo salvar() com {}", funcionario);
    FuncionarioEntity funcionarioEntityNew = funcionarioPersistenceMapper.toFuncionarioEntity(funcionario);

    funcionarioEntityNew = new FuncionarioEntity(UUID.randomUUID().toString(), funcionarioEntityNew);
    dynamoDBMapper.save(funcionarioEntityNew);
    return funcionarioPersistenceMapper.toFuncionario(funcionarioEntityNew);
  }

  @Override
  public List<Funcionario> listar() {
    LOGGER.trace("Entrando no metodo listar()");
    PaginatedList<FuncionarioEntity> results = dynamoDBMapper.scan(FuncionarioEntity.class, new DynamoDBScanExpression());
    results.loadAllResults();
    return funcionarioPersistenceMapper.toListEmployee(results);
  }

  @Override
  public Optional<Funcionario> buscarPorId(String funcionarioId) {
    LOGGER.trace("Entrando no metodo buscarPorId() com {}", funcionarioId);
    FuncionarioEntity entity = dynamoDBMapper.load(FuncionarioEntity.class, funcionarioId);
    if (entity != null){
      return Optional.of(funcionarioPersistenceMapper.toFuncionario(entity));
    }
    return Optional.empty();
  }

  @Override
  public Optional<Boolean> deletar(String funcionarioId) {
    LOGGER.trace("Entrando no metodo deletar() com {}", funcionarioId);
    FuncionarioEntity funcionario = dynamoDBMapper.load(FuncionarioEntity.class, funcionarioId);
    if (funcionario != null){
      dynamoDBMapper.delete(funcionario);
      return Optional.of(Boolean.TRUE);
    }
    return Optional.of(Boolean.FALSE);
  }

  @Override
  public Optional<Funcionario> atualizar(String funcionarioId, Funcionario funcionario) {
    LOGGER.trace("Entrando no metodo atualizar() com {}", funcionarioId);
    FuncionarioEntity funcionarioEntityPorId = dynamoDBMapper.load(FuncionarioEntity.class, funcionarioId);
    if (funcionarioEntityPorId != null){
      FuncionarioEntity funcionarioEntity = funcionarioPersistenceMapper.toFuncionarioEntity(funcionario);
      dynamoDBMapper.save(funcionarioEntity,
          new DynamoDBSaveExpression().withExpectedEntry("funcionarioId",
              new ExpectedAttributeValue(new AttributeValue().withS(funcionarioId))));

      return Optional.of(funcionarioPersistenceMapper.toFuncionario(funcionarioEntity));
    }
    return Optional.empty();
  }
}
