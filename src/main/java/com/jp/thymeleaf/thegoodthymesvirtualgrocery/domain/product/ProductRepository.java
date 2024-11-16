package com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, QueryByExampleExecutor<Product> {

    Optional<List<Product>> findByInStockTrue();

    Page<Product> findByInStockTrue(Pageable pageable);
}
