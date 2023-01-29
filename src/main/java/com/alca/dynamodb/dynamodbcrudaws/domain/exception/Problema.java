package com.alca.dynamodb.dynamodbcrudaws.domain.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.OffsetDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@ApiModel("Problema")
@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problema {

  @ApiModelProperty(example = "400", position = 1)
  private Integer status;

  @ApiModelProperty(example = "https://https://sistema-funcionario.com/dados-invalidos", position = 1)
  private String type;

  @ApiModelProperty(example = "Dados inválidos", position = 5)
  private String title;

  @ApiModelProperty(example = "Um ou mais campos estão inválidos. Preencha corretamente e tente novamente.", position = 10)
  private String detail;

  @ApiModelProperty(example = "Um ou mais campos estão inválidos. Preencha corretamente e tente novamente.", position = 15)
  private String userMessage; // Mensagem destinada ao usuário final

  @ApiModelProperty(example = "2021-06-30T15:22:29.979349Z", position = 20)
  private OffsetDateTime timestamp;

  @ApiModelProperty(value = "Objetos ou campos que geraram erro", position = 25)
  private List<Object> objects;

  @ApiModel("ObjetoProblema")
  @Getter
  @Builder
  public static class Object {

    private String name;
    private String userMessage;

  }
}
