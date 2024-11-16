package com.jp.thymeleaf.thegoodthymesvirtualgrocery.web.dto.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

public record CustomerResponseDTO(
        @NotBlank
        @Pattern(regexp = "[a-zA-ZÀ-ÿ\\s]+$", message = "Deve conter apenas letras")
        String name,
        @CPF
        String cpf,
        @Email
        String email
) {
}
