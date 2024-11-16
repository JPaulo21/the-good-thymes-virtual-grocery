package com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.cart.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String msg) {
        super(msg);
    }
}
