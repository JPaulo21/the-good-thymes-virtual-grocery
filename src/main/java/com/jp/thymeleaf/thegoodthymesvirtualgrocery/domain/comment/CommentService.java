package com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public List<Comment> findByProductId(Integer productId){
        return commentRepository.findByProductId(productId).orElseThrow(
                () -> new RuntimeException("Não há comentários para o produto: " + productId)
        );
    }

    public void save(Comment comment){
        commentRepository.save(comment);
    }
}
