package com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>, QueryByExampleExecutor<Comment> {
}
