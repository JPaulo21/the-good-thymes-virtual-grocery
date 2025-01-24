package com.jp.thymeleaf.thegoodthymesvirtualgrocery.web.dto.comment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentDTO(
        @NotBlank
        String text,
        @NotNull
        Integer productId,
        @NotBlank
        @Email
        String email) {
}
