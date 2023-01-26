package com.alca.dynamodb.dynamodbcrudaws.infrastructure.adapters.input.rest.data.response;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class ResponseError {

  @NotNull(message="Timestamp cannot be null")
  private LocalDateTime timestamp;

  @NotNull(message="Details cannot be null")
  private String details;

}
