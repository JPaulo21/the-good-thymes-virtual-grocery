package com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.clients.address;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Address(
        @JsonProperty("cep")
        String zipCode,
        @JsonProperty("logradouro")
        String address,
        String number,
        @JsonProperty("bairro")
        String neighborhood,
        @JsonProperty("localidade")
        String city,
        @JsonProperty("estado")
        String state

) {
}
