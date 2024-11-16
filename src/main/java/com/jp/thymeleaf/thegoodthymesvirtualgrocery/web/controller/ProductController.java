package com.jp.thymeleaf.thegoodthymesvirtualgrocery.web.controller;

import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.product.Product;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.product.ProductService;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.web.dto.orderline.OrderlineDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @ModelAttribute("products")
    public Page<Product> getAllProducts(@PageableDefault(sort = "name") Pageable pageable){
        Page<Product> products = productService.findAll(pageable);
        return products;
    }

    @GetMapping
    public ModelAndView homePage(){
        ModelAndView modelView = new ModelAndView("home");
        modelView.addObject("orderline", new OrderlineDTO(null,null));
        boolean isAuthenticated = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
        log.info("Autenticado: {}", isAuthenticated);
        return modelView;
    }

    @GetMapping("/product/{id}")
    public ModelAndView getPageProduct(@PathVariable Integer id){
        Product product = productService.findById(id);
        ModelAndView modelView = new ModelAndView("product");
        modelView.addObject("product", product);

        return modelView;
    }

}
