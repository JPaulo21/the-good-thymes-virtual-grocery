package com.jp.thymeleaf.thegoodthymesvirtualgrocery.web.dto.customer;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Calendar;

public record CustomerDTO(
        @NotBlank
        @Pattern(regexp = "[a-zA-ZÀ-ÿ\\s]+$", message = "Deve conter apenas letras")
        String name,
        @CPF(message = "CPF inválido!")
        String cpf,
        @NotBlank
        String password,
        @Email
        String email
) {
}
