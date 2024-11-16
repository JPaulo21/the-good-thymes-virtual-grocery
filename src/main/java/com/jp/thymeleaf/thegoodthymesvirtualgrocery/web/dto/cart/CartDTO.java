package com.jp.thymeleaf.thegoodthymesvirtualgrocery.web.dto.cart;

import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.order.OrderLine;

import java.math.BigDecimal;
import java.util.List;

public record CartDTO(
        List<OrderLine> orderLines,
        BigDecimal totalValue,
        Integer quantityProducts) {
}
