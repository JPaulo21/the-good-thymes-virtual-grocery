package com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.customer.exceptions;

public class InvalidCredentialsCustomerException extends RuntimeException {
    public InvalidCredentialsCustomerException(String message){
        super(message);
    }
}
