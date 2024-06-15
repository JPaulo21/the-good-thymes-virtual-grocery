package com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.customer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Entity
@Table(name = "Customer")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Customer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "customer_since", nullable = false)
    private Calendar customerSince;

}
