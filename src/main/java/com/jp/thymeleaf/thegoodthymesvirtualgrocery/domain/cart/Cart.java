package com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.cart;

import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.customer.Customer;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.order.OrderLine;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
@AllArgsConstructor
@Data
@Slf4j
public class Cart {

    private Integer id;
    private Customer customer;
    private List<OrderLine> orderLines;
    private BigDecimal totalValue;

    public Cart(){
        this.totalValue = BigDecimal.ZERO;
        this.orderLines = new ArrayList<OrderLine>();
    }

    public Integer quantityProducts(){
        return orderLines.stream()
                .map(OrderLine::getAmount)
                .reduce(0, Integer::sum);
    }

    public void countOrderLines(){
        Optional<BigDecimal> sumPrices = orderLines.stream()
                .map(OrderLine::getPurchasePrice)
                .reduce(BigDecimal::add);

        this.totalValue = sumPrices.orElse(BigDecimal.ZERO);
    }
}
