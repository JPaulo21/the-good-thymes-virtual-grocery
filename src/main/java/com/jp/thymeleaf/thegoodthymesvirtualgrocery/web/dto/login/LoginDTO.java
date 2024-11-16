package com.jp.thymeleaf.thegoodthymesvirtualgrocery.web.dto.login;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @Email
        String email,
        @NotBlank
        String password
) {
}
