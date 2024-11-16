package com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.order;

import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

import static jakarta.persistence.FetchType.LAZY;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Builder
public class OrderLine {

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    @EqualsAndHashCode.Include
    private Product product;

    @Column(name = "amount",nullable = false)
    private Integer amount;

    @Column(name = "purchase_price", nullable = false)
    private BigDecimal purchasePrice;

    public void setOrderline(OrderLine orderline){
        this.amount += orderline.getAmount();
        this.purchasePrice = purchasePrice.add(orderline.getPurchasePrice());
    }

    public void remove(Product product){
        removeAmount();
        removePurchasePrice(product.getPrice());
    }

    private void removeAmount() {
        this.amount--;
    }

    private void removePurchasePrice(BigDecimal value) {
        setPurchasePrice(getPurchasePrice().subtract(value));
    }

    public void update(Integer amount, BigDecimal purchasePrice){
        this.purchasePrice = purchasePrice;
        this.amount = amount;
    }
}
