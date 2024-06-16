package com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.order;

import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.product.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Embeddable
@Data
public class OrderLine {

    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "amount",nullable = false)
    private Integer amount;

    @Column(name = "purchase_price", nullable = false)
    private BigDecimal purchasePrice;

}
