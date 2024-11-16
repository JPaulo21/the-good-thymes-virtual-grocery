package com.jp.thymeleaf.thegoodthymesvirtualgrocery.web.exception.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
public class EntityErrorResponse {

    private String entity;
    private String field;
    private String message;
}
