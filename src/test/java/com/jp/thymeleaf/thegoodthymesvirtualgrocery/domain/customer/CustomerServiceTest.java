package com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.customer;

import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.customer.exceptions.CustomerNotFoundException;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.customer.exceptions.EmailAlreadyRegisteredException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.jp.thymeleaf.thegoodthymesvirtualgrocery.common.Constants.CUSTOMER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Spy
    private PasswordEncoder passwordEncoder;

    @Test
    public void createCustomer_withValidData_ReturnsCustomer(){
        when(customerRepository.save(CUSTOMER)).thenReturn(CUSTOMER);
        Customer sut = customerService.save(CUSTOMER);

        verify(passwordEncoder, times(1)).encode(any());
        assertThat(sut).isEqualTo(CUSTOMER);
    }

    @Test
    public void createCustomer_withEmailAlreadyRegistered_ReturnsException(){
        when(customerRepository.findByEmail(any())).thenReturn(Optional.of(CUSTOMER));

        assertThatThrownBy(() -> customerService.save(CUSTOMER))
                .hasMessage("E-mail já cadastrado!")
                .isInstanceOf(EmailAlreadyRegisteredException.class);
    }

    @Test
    public void getCustomer_withValidData_ReturnsUserDetails(){
        when(customerRepository.findByEmail(any())).thenReturn(Optional.ofNullable(CUSTOMER));

        UserDetails sut = customerService.loadUserByUsername(CUSTOMER.getEmail());

        assertThat(sut).isNotNull();
        assertThat(sut.getUsername()).isEqualTo(CUSTOMER.getEmail());
    }

    @Test
    public void getCustomer_withInvalidData_ReturnsException(){
        when(customerRepository.findByEmail(any())).thenReturn(Optional.ofNullable(null));

        assertThatThrownBy(() -> customerService.loadUserByUsername(any()))
                .hasMessage("Usuário não encontrado!")
                .isInstanceOf(CustomerNotFoundException.class);
    }

}
