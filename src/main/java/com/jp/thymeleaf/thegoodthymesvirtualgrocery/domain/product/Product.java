package com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.product;

import com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.comment.Comment;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "describe")
    private String describe;

    @Column(name = "price", precision = 11, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "in_stock", nullable = false)
    private Boolean inStock;

//    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
//    @JoinColumn(name = "comments")
//    private List<Comment> comments;

    @Column(name = "url_imagem")
    private String urlImagem;

}
