package com.jp.thymeleaf.thegoodthymesvirtualgrocery.pages.tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
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
@Order(2)
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class CartPageTest {

    @Autowired
    private WebDriver browser;
    private final String URL_APPLICATION = "http://localhost:8081";

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
    @DisplayName("CT002.001 - Adicionar produto ao carrinho")
    public void addProductToCart(){
        WebElement cardProduct = browser.findElement(By.cssSelector("[data-product-id='2']"));

        WebElement cardProductButtonAddToCart = cardProduct.findElement(By.className("add"));
        cardProductButtonAddToCart.click();

        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(15L));
        wait.until(urlToBe(URL_APPLICATION+"/cart"));

        WebElement cardInShopppingCart = browser.findElement(By.id("card-product-2"));
        WebElement titleCard = cardInShopppingCart.findElement(By.className("text-base"));

        assertThat(titleCard.getText()).isEqualTo("Ovo");
    }

    @Test
    @DisplayName("CT002.002 - Alterar a quantidade do produto na página do carrinho")
    public void updateProductInCart() throws InterruptedException {
        WebElement cardProduct = browser.findElement(By.cssSelector("[data-product-id='2']"));

        WebElement cardProductButtonAddToCart = cardProduct.findElement(By.className("add"));
        cardProductButtonAddToCart.click();

        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(15L));
        wait.until(urlToBe(URL_APPLICATION+"/cart"));

        WebElement cardInShoppingCart = browser.findElement(By.id("card-product-2"));
        Integer quantityProduct = Integer.parseInt(cardInShoppingCart.findElement(By.className("quantity-product")).getAttribute("value"));
        cardInShoppingCart.findElement(By.className("increment")).click();

        wait.until(ExpectedConditions.not(
                ExpectedConditions.attributeToBe(
                        cardInShoppingCart.findElement(By.className("quantity-product")),
                        "value",
                        quantityProduct.toString()
                )
        ));

        Integer updateQuantityProduct = Integer.parseInt(cardInShoppingCart.findElement(By.className("quantity-product")).getAttribute("value"));

        assertThat(updateQuantityProduct).isGreaterThan(quantityProduct);
    }

    @Test
    @DisplayName("CT002.003 - Alterar a quantidade do produto na página do carrinho para 0")
    public void removeProductFromCart(){
        WebElement cardProduct = browser.findElement(By.cssSelector("[data-product-id='2']"));

        WebElement cardProductButtonAddToCart = cardProduct.findElement(By.className("add"));
        cardProductButtonAddToCart.click();

        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(5L));
        wait.until(urlToBe(URL_APPLICATION+"/cart"));

        WebElement cardInShoppingCart = browser.findElement(By.id("card-product-2"));
        cardInShoppingCart.findElement(By.className("btn-remove-product")).click();

        wait.until(ExpectedConditions.textToBe(By.id("quantity-products"),"0"));
        Integer quantityProductsInCartHeader = Integer.parseInt(browser.findElement(By.id("quantity-products")).getText());

        assertThat(quantityProductsInCartHeader).isEqualTo(0);
        assertThat(browser.findElement(By.id("card-product-2")).isDisplayed()).isFalse();
    }

    @Test
    @DisplayName("CT004.001 - Realizar checkout sem login")
    public void checkoutWithoutLogin_NoAuthenticated_RedirectLoginPage(){
        WebElement cardProduct = browser.findElement(By.cssSelector("[data-product-id='2']"));

        WebElement cardProductButtonAddToCart = cardProduct.findElement(By.className("add"));
        cardProductButtonAddToCart.click();

        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(15L));
        wait.until(urlToBe(URL_APPLICATION+"/cart"));

        browser.findElement(By.id("proceed-checkout")).click();

        wait.until(urlToBe(URL_APPLICATION+"/auth/login"));

        String url = browser.getCurrentUrl();

        assertThat(url).isNotNull();
        assertThat(url).isEqualTo(URL_APPLICATION+"/auth/login");
    }

    @Test
    @DisplayName("CT006.001 - Total das compras")
    public void totalPurchases(){
        WebElement cardProductEgg = browser.findElement(By.cssSelector("[data-product-id='2']"));
        cardProductEgg.findElement(By.className("add")).click();

        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(15L));
        wait.until(urlToBe(URL_APPLICATION+"/cart"));

        browser.findElement(By.id("continue-shopping")).click();
        wait.until(urlToBe(URL_APPLICATION+"/"));

        WebElement cardProductBread = browser.findElement(By.cssSelector("[data-product-id='1']"));
        WebElement cardProductButtonAddToCart = cardProductBread.findElement(By.className("add"));
        cardProductButtonAddToCart.click();

        wait.until(urlToBe(URL_APPLICATION+"/cart"));

        WebElement productEgg = browser.findElement(By.id("card-product-2"));
        WebElement productBread = browser.findElement(By.id("card-product-1"));

        Double purchasePriceEgg = Double.valueOf(productEgg.findElement(By.id("purchase-price")).getText().substring("R$".length()));
        Double purchasePriceBread = Double.valueOf(productBread.findElement(By.id("purchase-price")).getText().substring("R$".length()));

        Double totalPrice = purchasePriceEgg + purchasePriceBread;

        WebElement totalPriceComponent = browser.findElement(By.id("total-price-products"));
        Double totalPriceScreen = Double.valueOf(totalPriceComponent.getText().substring("R$".length()));

        assertThat(totalPrice).isEqualTo(totalPriceScreen);
    }

}
