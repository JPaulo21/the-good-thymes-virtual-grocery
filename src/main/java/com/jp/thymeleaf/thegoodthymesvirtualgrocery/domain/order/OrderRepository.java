package com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>, QueryByExampleExecutor<Order> {
}
