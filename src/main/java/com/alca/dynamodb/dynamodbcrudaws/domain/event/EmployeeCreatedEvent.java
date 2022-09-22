package com.alca.dynamodb.dynamodbcrudaws.domain.event;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCreatedEvent {

  private String funcionarioId;

  private LocalDateTime date;

  public EmployeeCreatedEvent(String funcionarioId) {
    this.funcionarioId = funcionarioId;
    this.date = LocalDateTime.now();
  }
}
