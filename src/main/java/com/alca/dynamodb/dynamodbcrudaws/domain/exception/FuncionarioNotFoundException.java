package com.alca.dynamodb.dynamodbcrudaws.domain.exception;

/**
 * Classe que implementa FuncionarioNotFoundException na API
 * @author André LCA
 */
public class FuncionarioNotFoundException extends Exception {

    private static final long serialVersionUID = -7139304880555402679L;

    public FuncionarioNotFoundException(){
        super();
    }

    public FuncionarioNotFoundException(String message){
        super(message);
    }

    public FuncionarioNotFoundException(String message, Throwable cause){
        super(message, cause);
    }

}
