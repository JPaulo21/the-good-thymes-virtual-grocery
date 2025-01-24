package com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>, QueryByExampleExecutor<Comment> {
    Optional<List<Comment>> findByProductId(Integer productId);
}
