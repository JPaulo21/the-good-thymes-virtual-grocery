package com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.product;

import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.cart.exceptions.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<Product> findAll(Pageable pageable){
         Page<Product> products = productRepository.findByInStockTrue(pageable);

        return products;
    }

    public List<Product> findProductsInStock(){
        return productRepository.findByInStockTrue()
                .orElseThrow(
                        () -> new ProductNotFoundException("Não há produtos no estoque")
                );
    }

    public Product findById(Integer id){
        return productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException(String.format("Product id=%s not exists!", id))
        );
    }

}
