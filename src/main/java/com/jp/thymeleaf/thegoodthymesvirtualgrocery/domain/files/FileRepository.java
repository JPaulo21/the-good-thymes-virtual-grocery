package com.jp.thymeleaf.thegoodthymesvirtualgrocery.domain.files;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {
}
