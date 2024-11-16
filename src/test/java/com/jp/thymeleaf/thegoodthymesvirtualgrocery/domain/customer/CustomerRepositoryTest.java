package com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.customer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static com.jp.thymeleaf.thegoodthymesvirtualgrocery.common.Constants.CUSTOMER;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @AfterEach // Método é executa a cada TESTE
    public void afterEach(){
        CUSTOMER.setId(null);
    }

    @Test
    public void createCustomer_withValidData_ReturnsCustomer(){
        Customer customer = customerRepository.save(CUSTOMER);

        Customer sut = testEntityManager.find(Customer.class, customer.getId());

        assertThat(sut).isNotNull();
        assertThat(sut.getName()).isEqualTo(CUSTOMER.getName());
        assertThat(sut.getEmail()).isEqualTo(CUSTOMER.getEmail());
        assertThat(sut.isEnabled()).isTrue();
    }

}
