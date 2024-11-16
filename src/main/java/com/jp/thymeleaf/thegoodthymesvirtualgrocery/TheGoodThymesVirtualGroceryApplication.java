package com.jp.thymeleaf.thegoodthymesvirtualgrocery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TheGoodThymesVirtualGroceryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TheGoodThymesVirtualGroceryApplication.class, args);
	}

}
