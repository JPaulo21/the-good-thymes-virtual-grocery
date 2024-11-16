package com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.customer;

import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.customer.exceptions.CustomerNotFoundException;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.customer.exceptions.EmailAlreadyRegisteredException;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.customer.exceptions.InvalidCredentialsCustomerException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Customer save(Customer customer) {
        if(customerRepository.findByEmail(customer.getEmail()).isPresent())
            throw new EmailAlreadyRegisteredException("E-mail já cadastrado!");

        customer.setCpf(customer.getCpf().replaceAll("\\D", ""));
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        return customerRepository.save(customer);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws InvalidCredentialsCustomerException {
        return customerRepository.findByEmail(login).orElseThrow(
                () -> new InvalidCredentialsCustomerException("Usuário/Senha inválidos!")
        );
    }

    public Customer getCustomerByEmail(String email){
        return customerRepository.findByEmail(email).orElseThrow(
                () -> new CustomerNotFoundException("Usuário não encontrado!")
        );
    }
}
