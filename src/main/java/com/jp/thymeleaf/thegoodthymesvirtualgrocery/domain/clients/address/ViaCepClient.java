package com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.clients.address;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://viacep.com.br/ws", name = "viaCep")
public interface ViaCepClient {

    @GetMapping("/{cep}/json/")
    Address getAddress(@PathVariable("cep") String cep);

}
