package com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.cart;

import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.cart.exceptions.ProductNotFoundException;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.order.OrderLine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartService {

    public void add(Cart cart, OrderLine orderline) {
        if (cart.getOrderLines().isEmpty() || !cart.getOrderLines().contains(orderline)){
            cart.getOrderLines().add(orderline);
        } else {
            Integer index = cart.getOrderLines().indexOf(orderline);
            cart.getOrderLines().get(index).setOrderline(orderline);
        }

        cart.countOrderLines();
    }

    public void removeOrderLine(Cart cart, OrderLine orderLine){
        if (!cart.getOrderLines().contains(orderLine))
            throw new ProductNotFoundException("Produto não está no carrinho");

        Integer index = cart.getOrderLines().indexOf(orderLine);
        OrderLine order = cart.getOrderLines().get(index);
        cart.getOrderLines().remove(order);
        cart.countOrderLines();
    }

    public void updateOrderLine(Cart cart, OrderLine orderline) {
        //Validations
        if(cart.getOrderLines().isEmpty())
            throw new RuntimeException("Não há produtos no carrinho!");

        if (!cart.getOrderLines().contains(orderline))
            throw new RuntimeException("Produto não está no carrinho");

        if(orderline.getAmount().equals(0)) {
            removeOrderLine(cart, orderline);
        } else {
            Integer indexOrder = cart.getOrderLines().indexOf(orderline);
            cart.getOrderLines().get(indexOrder).update(orderline.getAmount(), orderline.getPurchasePrice());

            cart.countOrderLines();
        }
    }


}
