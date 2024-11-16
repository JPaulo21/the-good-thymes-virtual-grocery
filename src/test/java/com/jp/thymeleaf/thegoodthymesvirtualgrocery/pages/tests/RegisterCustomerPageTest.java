package com.jp.thymeleaf.thegoodthymesvirtualgrocery.pages.tests;

import com.jp.thymeleaf.thegoodthymesvirtualgrocery.pages.RegisterCustomerPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlToBe;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestClassOrder(ClassOrderer.OrderAnnotation.class)
@Order(3)
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class RegisterCustomerPageTest {

    @Autowired
    private WebDriver browser;
    private final String URL_APPLICATION = "http://localhost:8081";

    @BeforeEach
    public void setUp(){
        browser.manage().window().maximize();
    }

    @AfterEach
    public void tearDown(){
        browser.quit();
    }

    @Test
    @DisplayName("CT003.001 - Cadastro de novo usuário")
    public void createCustomer_withValidData_ReturnsUrlHome(){
        browser.get(URL_APPLICATION+"/customers/register");
        RegisterCustomerPage registerCustomerPage = new RegisterCustomerPage(browser);

        registerCustomerPage.inputNameField("name");
        registerCustomerPage.inputCpfField("512.666.300-31");
        registerCustomerPage.inputEmailField("name@email.com");
        registerCustomerPage.inputPasswordField("123");
        registerCustomerPage.inputConfirmedPasswordField("123");
        registerCustomerPage.checkedAcceptTerms();
        registerCustomerPage.clickCreateAccount();

        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(15L));
        wait.until(urlToBe(URL_APPLICATION+"/"));

        String currentUrl = browser.getCurrentUrl();
        assertThat(URL_APPLICATION+"/").isEqualTo(currentUrl);
    }

    @Test
    @DisplayName("CT003.002 - Tentar criar usuário com e-mail já cadastrado")
    public void createCustomer_withInvalidData_EmailDuplicate_showMessage() throws InterruptedException {
        browser.get(URL_APPLICATION+"/customers/register");
        RegisterCustomerPage registerCustomerPage = new RegisterCustomerPage(browser);

        registerCustomerPage.inputNameField("name");
        registerCustomerPage.inputCpfField("512.666.300-31");
        registerCustomerPage.inputEmailField("jp.almeida@gmail.com");
        registerCustomerPage.inputPasswordField("123");
        registerCustomerPage.inputConfirmedPasswordField("123");
        registerCustomerPage.checkedAcceptTerms();
        registerCustomerPage.clickCreateAccount();

        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(5L));

        WebElement toastError = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("toast-danger")));
        String messageError = toastError.findElement(By.id("message")).getText();

        assertThat(messageError).isEqualTo("E-mail já cadastrado!");
    }

    @Test
    @DisplayName("CT003.003 - Login")
    public void loginCustomer_withValidData() throws InterruptedException {
        browser.get(URL_APPLICATION);

        // Encontrar e clicar no icone para pagina de login
        WebElement iconProfile = browser.findElement(By.id("icon-profile"));
        System.out.println(iconProfile.getText());
        Actions actions = new Actions(browser);
        actions.moveToElement(iconProfile).perform();
        browser.findElement(By.id("icon-profile-login")).click();

        // Esperar o navegador mudar
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(10L));
        wait.until(urlToBe(URL_APPLICATION+"/auth/login"));

        // Encontrar, digitar Email e Senha, clicar em logar
        browser.findElement(By.id("email")).sendKeys("name@email.com");
        browser.findElement(By.id("password")).sendKeys("123");
        browser.findElement(By.id("btn-login")).click();

        wait.until(urlToBe(URL_APPLICATION+"/"));

        actions.moveToElement(browser.findElement(By.id("icon-profile"))).perform();
        WebElement emailLogado = wait.until(ExpectedConditions.visibilityOf(browser.findElement(By.id("profile-login-email"))));

        assertThat(emailLogado.getText()).isEqualTo("name@email.com");
    }

    @Test
    @DisplayName("CT003.004 - Login Inválido")
    public void loginCustomer_withDataInvalid_(){
        browser.get(URL_APPLICATION+"/auth/login");

        browser.findElement(By.id("email")).sendKeys("invalid@email.com");
        browser.findElement(By.id("password")).sendKeys("invalidPassword");
        browser.findElement(By.id("btn-login")).click();

        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(5L));

        WebElement toastError = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("toast-danger")));
        String messageError = toastError.findElement(By.id("message")).getText();

        assertThat(messageError).isEqualTo("Usuário/Senha inválidos!");
    }

    @Test
    @DisplayName("CT003.005 - Logout")
    public void logout(){
        browser.get(URL_APPLICATION+"/auth/login");

        // Encontrar, digitar Email e Senha, clicar em logar
        browser.findElement(By.id("email")).sendKeys("jp.almeida@gmail.com");
        browser.findElement(By.id("password")).sendKeys("123");
        browser.findElement(By.id("btn-login")).click();

        // Esperar o navegador mudar
        WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(10L));
        wait.until(urlToBe(URL_APPLICATION+"/"));

        Actions actions = new Actions(browser);
        actions.moveToElement(browser.findElement(By.id("icon-profile"))).perform();
        browser.findElement(By.id("logout")).click();

        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                // Verificar o estado de carregamento da página
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        });

        actions.moveToElement(browser.findElement(By.id("icon-profile"))).perform();
        WebElement btnLogin = browser.findElement(By.id("icon-profile-login"));

        assertThat(btnLogin.getText()).isEqualTo("Entrar");
    }

}




