package com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.customer.exceptions;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException(String msg) {
        super(msg);
    }

}
