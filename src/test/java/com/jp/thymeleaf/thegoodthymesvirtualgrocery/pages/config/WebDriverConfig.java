package com.jp.thymeleaf.thegoodthymesvirtualgrocery.pages.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebDriverConfig {

    @Bean
    public WebDriver webDriver() {
        //WebDriverManager.chromedriver().driverVersion("130.0.6723.92").setup();
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}
