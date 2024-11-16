package com.jp.thymeleaf.thegoodthymesvirtualgrocery.web.dto.orderline;

import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.product.Product;

public record OrderlineDTO(

        Integer productId,
        Integer quantity
) {
}
