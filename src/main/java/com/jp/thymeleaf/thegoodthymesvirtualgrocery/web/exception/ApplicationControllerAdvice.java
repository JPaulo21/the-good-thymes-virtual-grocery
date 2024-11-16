package com.jp.thymeleaf.thegoodthymesvirtualgrocery.web.exception;

import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.cart.exceptions.ProductNotFoundException;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.customer.exceptions.CustomerNotFoundException;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.customer.exceptions.EmailAlreadyRegisteredException;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.customer.exceptions.InvalidCredentialsCustomerException;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.web.exception.entities.EntityErrorResponse;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.web.exception.entities.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse runtimeException(RuntimeException e){
        return new ErrorResponse(e.getMessage());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> productNotFoundException(ProductNotFoundException e){
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public EntityErrorResponse emailAlreadyRegisteredException(EmailAlreadyRegisteredException e){
        return new EntityErrorResponse("Customer"
                , "E-mail"
                , e.getMessage());
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> customerNotFoundException(CustomerNotFoundException e){
        log.error("Erro ao buscar usuário");
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(InvalidCredentialsCustomerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse invalidCredentialsCustomerException(InvalidCredentialsCustomerException e){
        return new ErrorResponse(e.getMessage());
    }

}
