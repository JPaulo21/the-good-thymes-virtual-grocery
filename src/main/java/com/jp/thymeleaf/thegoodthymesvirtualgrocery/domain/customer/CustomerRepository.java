package com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>, QueryByExampleExecutor<Customer> {
}
