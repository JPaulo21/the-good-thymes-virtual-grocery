package com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.customer;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class    Customer implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(unique = true, length = 11, nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(name = "url_photo")
    private String urlPhoto;

    @Column
    private Boolean enable;

    @Column(name = "customer_since", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar customerSince;



    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("CUSTOMER"));
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
