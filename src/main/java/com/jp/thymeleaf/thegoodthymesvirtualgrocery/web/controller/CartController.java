package com.jp.thymeleaf.thegoodthymesvirtualgrocery.web.controller;

import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.cart.Cart;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.cart.CartService;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.order.OrderLine;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.product.Product;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.product.ProductService;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.web.dto.cart.CartDTO;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.web.dto.orderline.OrderlineDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
@SessionAttributes("cart")
public class CartController {

    private final ProductService productService;
    private final CartService cartService;

    @ModelAttribute("cart")
    public Cart cart(){
        return new Cart();
    }

    @GetMapping
    public String PageCart(){
        return "cart";
    }

    // Métodos de interação JS

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity addOrdeline(@ModelAttribute("cart") Cart cart, @RequestBody OrderlineDTO orderlineDTO){
        Product product = productService.findById(orderlineDTO.productId());
        OrderLine orderline = OrderLine.builder()
                .product(product)
                .amount(orderlineDTO.quantity())
                // Multiplicando o preço pela quantidade
                .purchasePrice(product.getPrice().multiply(BigDecimal.valueOf(orderlineDTO.quantity())))
                .build();
        cartService.add(cart, orderline);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/session")
    @ResponseBody
    public CartDTO getCartSession(@SessionAttribute("cart") Cart cart){
        return new CartDTO(cart.getOrderLines(), cart.getTotalValue(), cart.quantityProducts());
    }

    @PatchMapping("/product")
    @ResponseBody
    public ResponseEntity<Void> updateOrderline(@SessionAttribute("cart") Cart cart, @RequestBody OrderlineDTO orderlineDTO){
        Product product = productService.findById(orderlineDTO.productId());
        OrderLine order = OrderLine.builder()
                .product(product)
                .amount(orderlineDTO.quantity())
                // Multiplicando o preço pela quantidade
                .purchasePrice(product.getPrice().multiply(BigDecimal.valueOf(orderlineDTO.quantity())))
                .build();
        cartService.updateOrderLine(cart, order);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/product/{id}")
    @ResponseBody
    public ResponseEntity<Void> removeProduct(@SessionAttribute("cart") Cart cart, @PathVariable("id") Integer productId){
        Product product = productService.findById(productId);
        OrderLine orderline = OrderLine.builder().product(product).build();
        cartService.removeOrderLine(cart, orderline);
        return ResponseEntity.noContent().build();
    }

}
