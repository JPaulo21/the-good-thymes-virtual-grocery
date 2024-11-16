package com.jp.thymeleaf.thegoodthymesvirtualgrocery.web.controller;

import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.cart.Cart;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/buy")
@SessionAttributes("cart")
public class BuyController {

    @GetMapping("/checkout")
    public ModelAndView getPageCheckout(@SessionAttribute("cart") Cart cart){
        ModelAndView modelView = new ModelAndView("checkout");
        modelView.addObject("orderlines", cart.getOrderLines());

        return modelView;
    }
}
