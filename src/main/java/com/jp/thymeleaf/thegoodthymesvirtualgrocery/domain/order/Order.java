package com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.order;

import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.customer.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.Set;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar date;

    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "order_lines", joinColumns = @JoinColumn(name = "order_id", nullable = false))
    private Set<OrderLine> orderLines;

}
