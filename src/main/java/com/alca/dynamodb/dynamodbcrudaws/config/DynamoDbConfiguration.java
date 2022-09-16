package com.alca.dynamodb.dynamodbcrudaws.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDbConfiguration {

  @Bean
  public DynamoDBMapper dynamoDBMapper() {
    return new DynamoDBMapper(buildAmazonDynamoDB());
  }

  private AmazonDynamoDB buildAmazonDynamoDB() {
    return AmazonDynamoDBClientBuilder
        .standard()
        .withEndpointConfiguration(endpointConfiguration())
        .withCredentials(credentialsProvider())
        .build();
  }
  /**
   * Configurar a região e o endpoint da AWS
   *
   */
  private AwsClientBuilder.EndpointConfiguration endpointConfiguration() {
    return new AwsClientBuilder.EndpointConfiguration(
        "http://localhost:8000/",
        Regions.SA_EAST_1.getName()

    );
  }

  private AWSStaticCredentialsProvider credentialsProvider() {
    return new AWSStaticCredentialsProvider(
        new BasicAWSCredentials(
            "fakeId",
            "fakeSecret"
        )
    );
  }
}
