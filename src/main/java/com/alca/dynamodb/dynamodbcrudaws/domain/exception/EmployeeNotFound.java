package com.alca.dynamodb.dynamodbcrudaws.domain.exception;

public class EmployeeNotFound extends RuntimeException {

    public EmployeeNotFound(String message) {
        super(message);
    }

}
