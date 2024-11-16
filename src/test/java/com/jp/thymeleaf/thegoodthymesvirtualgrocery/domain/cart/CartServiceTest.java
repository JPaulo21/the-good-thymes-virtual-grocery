package com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.cart;

import com.jp.thymeleaf.thegoodthymesvirtualgrocery.common.Constants;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.cart.exceptions.ProductNotFoundException;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.order.OrderLine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @InjectMocks
    private CartService cartService;

    private Cart cart;

    private OrderLine ORDERLINE;

    @BeforeEach
    public void setup(){
        ORDERLINE = new OrderLine(Constants.PRODUCT, 1, Constants.PRODUCT.getPrice());
        cart = new Cart();
    }

    @Test
    @DisplayName("Add new orderline to cart")
    public void addProductToCart_withValid_VerifyProductInCart(){
        cartService.add(cart, ORDERLINE);

        assertThat(cart.getOrderLines()).isNotEmpty();
        assertThat(cart.getOrderLines().size()).isEqualTo(1);
        assertThat(cart.quantityProducts()).isEqualTo(ORDERLINE.getAmount());
        assertThat(cart.getTotalValue()).isEqualTo(Constants.PRODUCT.getPrice());
    }

    @Test
    @DisplayName("Update orderline in cart")
    public void updateProductToCart(){
        cart.getOrderLines().add(ORDERLINE);

        cartService.add(cart, ORDERLINE);

        assertThat(cart.getOrderLines()).isNotEmpty();
        assertThat(cart.getOrderLines().size()).isEqualTo(1);
        assertThat(cart.quantityProducts()).isEqualTo(2);
        assertThat(cart.getTotalValue()).isEqualTo(BigDecimal.valueOf(2.00));
    }

    @Test
    @DisplayName("Remove orderline in cart")
    public void removeProductToCart(){
        cart.getOrderLines().add(ORDERLINE);

        cartService.removeOrderLine(cart, ORDERLINE);

        assertThat(cart.getOrderLines().contains(ORDERLINE)).isFalse();
    }
    @Test
    @DisplayName("Throw error when removing order line with product not exists in cart")
    public void removeProductToCart_WithIdInvalid_ReturnThrows(){
        assertThatThrownBy( () -> cartService.removeOrderLine(cart, ORDERLINE)).isInstanceOf(ProductNotFoundException.class);
    }

    //@Test
    //public void updateOrderline_withValidData


}
