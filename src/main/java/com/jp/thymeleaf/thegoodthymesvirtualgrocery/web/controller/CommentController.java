package com.jp.thymeleaf.thegoodthymesvirtualgrocery.web.controller;

import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.comment.Comment;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.comment.CommentService;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.customer.Customer;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.customer.CustomerService;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.product.Product;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.product.ProductService;
import com.jp.thymeleaf.thegoodthymesvirtualgrocery.web.dto.comment.CommentDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CustomerService customerService;
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Void> addComment(@RequestBody @Valid CommentDTO commentDTO){
        Customer customer = customerService.getCustomerByEmail(commentDTO.email());
        Product product = productService.findById(commentDTO.productId());
        Comment comment = Comment.builder()
                .customer(customer)
                .product(product)
                .text(commentDTO.text())
                .date(LocalDate.now())
                .build();
        commentService.save(comment);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
