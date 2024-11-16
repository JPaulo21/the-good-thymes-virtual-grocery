package com.jp.thymeleaf.thegoodthymesvirtualgrocery.pages.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@Order(1)
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class HomePageTest {

    @Autowired
    private WebDriver browser;

    @BeforeEach
    public void setUp(){
        browser.manage().window().maximize();
        String URL_APPLICATION = "http://localhost:8081";
        browser.get(URL_APPLICATION);
    }

    @AfterEach
    public void tearDown(){
        browser.quit();
    }


    @Test
    @DisplayName("CT001.001 - Aplicação no ar")
    public void accessHome_ValidURL_ReturnPage(){
        String name = browser.findElement(By.id("title")).getText();

        assertThat(name).isEqualTo("Good Thymes Virtual Grocery");
    }

}
