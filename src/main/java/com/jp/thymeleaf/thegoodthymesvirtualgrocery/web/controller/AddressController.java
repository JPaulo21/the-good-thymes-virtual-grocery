package com.jp.thymeleaf.thegoodthymesvirtualgrocery.web.controller;

import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.clients.address.Address;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.clients.address.ViaCepClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

    private final ViaCepClient viaCepClient;

    @GetMapping("/{cep}")
    public ResponseEntity<Address> getAddressbyCep(@PathVariable("cep") Optional<String> optionalCep){
        String cep = optionalCep.orElseThrow(
                () -> new RuntimeException("Código do cep está nulo")
        );

        Address address = viaCepClient.getAddress(cep);
        log.info("get Address: {}", address);

        return ResponseEntity.ok(address);
    }

}
