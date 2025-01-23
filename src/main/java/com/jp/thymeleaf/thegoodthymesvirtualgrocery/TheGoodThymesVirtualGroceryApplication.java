package com.jp.thymeleaf.thegoodthymesvirtualgrocery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@EnableFeignClients
@SpringBootApplication
public class TheGoodThymesVirtualGroceryApplication {

	private static final Logger log = LoggerFactory.getLogger(TheGoodThymesVirtualGroceryApplication.class);

	public static void main(String[] args) throws UnknownHostException {
		SpringApplication app = new SpringApplication(TheGoodThymesVirtualGroceryApplication.class);
		Environment env = app.run(args).getEnvironment();

		String info = """
				\n-----------------------------------------------------------------------------------------------------------------------------------------------
				Application {} is running!
				-----------------------------------------------------------------------------------------------------------------------------------------------
					Access URLs:
						Local: \t\thttp://localhost:{}
						External: \thttp://{}:{}
				-----------------------------------------------------------------------------------------------------------------------------------------------
				""";
		log.info(info, env.getProperty("spring.application.name"),
				env.getProperty("server.port"),
				InetAddress.getLocalHost().getHostAddress(),
				env.getProperty("server.port"));
	}

}
