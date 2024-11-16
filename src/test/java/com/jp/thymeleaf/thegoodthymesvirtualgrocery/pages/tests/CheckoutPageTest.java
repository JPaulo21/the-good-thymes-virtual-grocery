package com.jp.thymeleaf.thegoodthymesvirtualgrocery.pages.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlToBe;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@Order(4)
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class CheckoutPageTest {

    @Autowired
    private WebDriver browser;
    private static String URL_APPLICATION = "http://localhost:8081";

    @BeforeEach
    public void setUp(){
        browser.manage().window().maximize();
        browser.get(URL_APPLICATION);
    }

    @AfterEach
    public void tearDown(){
        browser.quit();
    }

    @Test
    @DisplayName("CT005.001 - Preenchimento automático de endereço")
    public void addressAutoCompletion(){
        WebElement cardProduct = browser.findElement(By.cssSelector("[data-product-id='2']"));

        WebElement cardProductButtonAddToCart = cardProduct.findElement(By.className("add"));
        cardProductButtonAddToCart.click();

        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(15L));
        wait.until(urlToBe(URL_APPLICATION+"/cart"));

        browser.findElement(By.id("proceed-checkout")).click();

        wait.until(urlToBe(URL_APPLICATION+"/auth/login"));

        browser.findElement(By.id("email")).sendKeys("jp.almeida@gmail.com");
        browser.findElement(By.id("password")).sendKeys("123");
        browser.findElement(By.id("btn-login")).click();

        wait.until(urlToBe(URL_APPLICATION+"/buy/checkout?continue"));

        browser.findElement(By.id("zipCode")).sendKeys("53130645");
        browser.findElement(By.id("zipCode")).sendKeys(Keys.TAB);

        WebElement fieldAddress = browser.findElement(By.id("address"));
        WebElement fieldCity = browser.findElement(By.id("city"));
        WebElement fieldState = browser.findElement(By.id("state"));

        wait.until(ExpectedConditions.attributeToBe(fieldAddress,"value", "Rua Carmelita Soares Muniz de Araújo"));

        assertThat(fieldAddress.getAttribute("value")).isEqualTo("Rua Carmelita Soares Muniz de Araújo");
        assertThat(fieldCity.getAttribute("value")).isEqualTo("Olinda");
        assertThat(fieldState.getAttribute("value")).isEqualTo("Pernambuco");

    }

}
