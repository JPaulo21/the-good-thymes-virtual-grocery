package com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.customer.exceptions;

public class EmailAlreadyRegisteredException extends RuntimeException {
    public EmailAlreadyRegisteredException(String msg) {
        super(msg);
    }
}
