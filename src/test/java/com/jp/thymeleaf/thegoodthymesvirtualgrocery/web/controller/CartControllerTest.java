package com.jp.thymeleaf.thegoodthymesvirtualgrocery.web.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.TheGoodThymesVirtualGroceryApplication;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.common.Constants;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.config.security.SecurityConfig;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.cart.Cart;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.cart.CartService;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.cart.exceptions.ProductNotFoundException;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.order.OrderLine;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.product.ProductService;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.web.controller.CartController;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.web.dto.orderline.OrderlineDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

@WebMvcTest(CartController.class)
@ContextConfiguration(classes = {TheGoodThymesVirtualGroceryApplication.class, SecurityConfig.class})
public class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private MockHttpSession session;

    private Cart cart;

    private OrderLine ORDERLINE;

    @InjectMocks
    private CartController cartController;

    @SpyBean
    private CartService cartService;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        ORDERLINE = new OrderLine(Constants.PRODUCT, 1, Constants.PRODUCT.getPrice());
        session = new MockHttpSession();
        cart = new Cart();
    }

    @Test
    public void updateProduct_WithValidId_returnOkAndCartSessionUpdate() throws Exception {
        cartService.add(cart, ORDERLINE);
        session.setAttribute("cart", cart);
        when(productService.findById(any())).thenReturn(Constants.PRODUCT);

        MvcResult mvcResult = mockMvc.perform(patch("/cart/product")
                    .with(anonymous())
                    .content(objectMapper.writeValueAsBytes(new OrderlineDTO(1, 2)))
                    .contentType(MediaType.APPLICATION_JSON)
                    .session(session))
                .andExpect(status().isNoContent())
                .andReturn();

        MockHttpSession sessionAfterRequest = (MockHttpSession) mvcResult.getRequest().getSession();
        Cart sut = (Cart) sessionAfterRequest.getAttribute("cart");

        assertThat(sut).isNotNull();
        assertThat(sut.quantityProducts()).isEqualTo(2);
        assertThat(sut.getTotalValue()).isEqualTo(BigDecimal.valueOf(2.0));
    }

    @Test
    public void removeProduct_WithDataValid_returnNoContent() throws Exception {
        cart.getOrderLines().add(ORDERLINE);
        session.setAttribute("cart", cart);
        when(productService.findById(any())).thenReturn(Constants.PRODUCT);

        MvcResult mvcResult = mockMvc
            .perform(MockMvcRequestBuilders.delete("/cart/product/" + Constants.PRODUCT.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .session(session))
                .andExpect(status().isNoContent())
                .andReturn();

        MockHttpSession sessionAfterRequest = (MockHttpSession) mvcResult.getRequest().getSession();
        Cart sut = (Cart) sessionAfterRequest.getAttribute("cart");

        assertThat(sut.getOrderLines().contains(ORDERLINE)).isFalse();
    }

    @Test
    public void removeProduct_InexistingInCart_returnBadRequest() throws Exception {
        when(productService.findById(any())).thenReturn(Constants.PRODUCT);
        doThrow(new ProductNotFoundException("Produto não está no carrinho"))
                .when(cartService)
                .removeOrderLine(any(Cart.class), any(OrderLine.class));

        mockMvc
                .perform(MockMvcRequestBuilders.delete("/cart/product/" + Constants.PRODUCT.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .session(session))
                .andExpect(status().isBadRequest());
    }

}
