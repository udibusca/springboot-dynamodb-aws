package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDbConfiguration {

  @Value("${aws.region}")
  private String awsRegion;

  @Value("${amazon.dynamodb.endpoint}")
  private String amazonDynamoDBEndpoint;

  @Value("${amazon.aws.accesskey}")
  private String amazonAWSAccessKey;

  @Value("${amazon.aws.secretkey}")
  private String amazonAWSSecretKey;

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
        amazonDynamoDBEndpoint,
        awsRegion);
  }

  private AWSStaticCredentialsProvider credentialsProvider() {
    return new AWSStaticCredentialsProvider(
        new BasicAWSCredentials(
            amazonAWSAccessKey,
            amazonAWSSecretKey)
    );
  }
}
