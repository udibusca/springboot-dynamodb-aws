package com.alca.dynamodb.dynamodbcrudaws.domain.exception;

import lombok.Getter;

/**
 * Centraliza type e title da representação de respostas de erro.
 * Curso AlgaWorks
 * @author André LCA
 */
@Getter
public enum TipoProblema {

  RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
  ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
  ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
  MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
  PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
  ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
  DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
  ACESSO_NEGADO("/acesso-negado", "Aceso negado");

  private String title;
  private String uri;

  private TipoProblema(String path, String title) {
    this.uri = "https://sistema-funcionario.com" + path;
    this.title = title;
  }

}