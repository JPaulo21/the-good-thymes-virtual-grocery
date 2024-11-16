package com.jp.thymeleaf.thegoodthymesvirtualgrocery.common;

import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.cart.Cart;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.customer.Customer;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.order.OrderLine;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.product.Product;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.web.dto.orderline.OrderlineDTO;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import static java.lang.Boolean.TRUE;

public class Constants {



    public static final Product PRODUCT = new Product(1, "TESTE", "", BigDecimal.valueOf(1.00), TRUE, null);

    public static final OrderLine ORDERLINE = new OrderLine(PRODUCT, 1, PRODUCT.getPrice());
    public static final Cart CART = new Cart(1, null, List.of(ORDERLINE), ORDERLINE.getPurchasePrice());

    public static final OrderLine ORDERLINE_EMPTY = new OrderLine();

    public static final OrderlineDTO ORDERLINE_DTO = new OrderlineDTO(1, 1);

    public static final Customer CUSTOMER = Customer.builder()
            .cpf("53662800012")
            .name("name")
            .password("123")
            .email("customer@email.com")
            .enable(true)
            .customerSince(Calendar.getInstance())
            .build();

}
