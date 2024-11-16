package com.jp.thymeleaf.thegoodthymesvirtualgrocery.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterCustomerPage {

    private WebDriver browser;

    public RegisterCustomerPage(WebDriver browser){
        this.browser = browser;
    }

    private final By nameField = By.id("name");
    private final By cpfField = By.id("cpf");
    private final By emailField = By.id("email");
    private final By passwordField = By.id("password");
    private final By confirmedPasswordField = By.id("confirm-password");
    private final By acceptTerms = By.id("terms-conditions");
    private final By createAccountCheckbox = By.id("btn-create-account");

    public void inputNameField(String name){
        browser.findElement(nameField).sendKeys(name);
    }

    public void inputCpfField(String cpf){
        browser.findElement(cpfField).sendKeys(cpf);
    }

    public void inputEmailField(String email){
        browser.findElement(emailField).sendKeys(email);
    }

    public void inputPasswordField(String password){
        browser.findElement(passwordField).sendKeys(password);
    }

    public void inputConfirmedPasswordField(String confirmedPassword){
        browser.findElement(confirmedPasswordField).sendKeys(confirmedPassword);
    }

    public void checkedAcceptTerms(){
        browser.findElement(acceptTerms).click();
    }

    public void clickCreateAccount(){
        browser.findElement(createAccountCheckbox).click();
    }
}
