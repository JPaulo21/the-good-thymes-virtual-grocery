package com.jp.thymeleaf.thegoodthymesvirtualgrocery.web.controller;

import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.customer.Customer;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.customer.CustomerService;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.web.dto.customer.CustomerDTO;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.web.dto.customer.CustomerResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Calendar;

@Slf4j
@Controller
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/register")
    public String getPageUserRegister(){
        return "userregister";
    }

    // ------------------------------------------------------------------------

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CustomerDTO customerDTO, UriComponentsBuilder ucb){
        Customer customer = customerService.save(Customer.builder()
                .cpf(customerDTO.cpf())
                .name(customerDTO.name())
                .email(customerDTO.email())
                .password(customerDTO.password())
                .enable(Boolean.TRUE)
                .customerSince(Calendar.getInstance())
                .build());

        URI location = ucb
                .path("/customers/{id}")
                .buildAndExpand(customer.getId())
                .toUri();

        log.info("USUARIO SALVO COM SUCESSO!");
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{email}")
    public ResponseEntity<CustomerResponseDTO> getCustomerByEmail(@PathVariable("email") String email){
        Customer customer = customerService.getCustomerByEmail(email);
        CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO(
                customer.getName(),
                customer.getCpf(),
                customer.getEmail());
        return ResponseEntity.ok(customerResponseDTO);
    }

}
