package com.macneil.pages;

import com.macneil.testbase.BaseUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class PartsPage {

    WebDriver driver;
    WebDriverWait webDriverWait;
    Logger logger = LogManager.getLogger(PartsPage.class);

    @FindBy(id = "btn_register")
    WebElement signUp;

    @FindBy(id = "btn_login")
    WebElement login;

    @FindBy(id = "wpcrlRegisterSection")
    WebElement registerSection;

    @FindBy(xpath = "//input[@placeholder='First Name *']")
    WebElement firstName;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    WebElement lastName;

    @FindBy(xpath = "//input[@placeholder='Username *']")
    WebElement signup_username;

    @FindBy(xpath = "//input[@placeholder='Email *']")
    WebElement signup_email;

    @FindBy(xpath = "//input[@placeholder='Password *']")
    WebElement signup_password;

    @FindBy(xpath = "//input[@placeholder='Confirm Password *']")
    WebElement confirm_password;

    @FindBy(xpath = "//button[contains(text(),'Sign Up')]")
    WebElement confirmSignUp;

    @FindBy(id = "wpcrl-register-alert")
    WebElement registrationConfirmation;

    @FindBy(id = "wpcrlLoginSection")
    WebElement loginSection;

    @FindBy(xpath = "//input[@placeholder='Username']")
    WebElement login_username;

    @FindBy(xpath = "//input[@placeholder='Password']")
    WebElement login_password;

    @FindBy(xpath = "//button[text()='Login']")
    WebElement confirmLogin;

    @FindBy(xpath = "//a[text()='Forgot Password']")
    WebElement forgotPassword;

    @FindBy(id = "login_cls")
    WebElement closeLoginSection;

    @FindBy(xpath = "//input[@type='search']")
    WebElement searchTextField;

    @FindBy(xpath = "//div[@class='autocomplete-suggestion']/descendant::div")
    WebElement searchButton;

    @FindBy(xpath = "//a[@title='View your shopping cart']")
    WebElement shoppingCart;


    public PartsPage(WebDriver driver)
    {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, 20);
        PageFactory.initElements(driver, this);
    }


    public void loadPartsPage(){
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor)driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
        webDriverWait.until(pageLoadCondition);
    }

    public void userLogin(String username, String password){
        Assert.assertTrue("Login section is not displayed",webDriverWait.until(ExpectedConditions.visibilityOf(loginSection)).isDisplayed());
        webDriverWait.until(ExpectedConditions.visibilityOf(login_username)).sendKeys(username);
        webDriverWait.until(ExpectedConditions.visibilityOf(login_password)).sendKeys(password);
        webDriverWait.until(ExpectedConditions.visibilityOf(confirmLogin)).click();
    }

    public ProductDetailsPage searchProduct(String productId) throws InterruptedException {
        Thread.sleep(10000L);
        webDriverWait.until(ExpectedConditions.visibilityOf(searchTextField)).sendKeys(productId);
        logger.info("Product Id "+productId+" entered in search field");
        webDriverWait.until(ExpectedConditions.visibilityOf(searchButton)).click();
        logger.info("Suggested item has been clicked");
        return new ProductDetailsPage(driver);
    }

    public void userRegistration(String firstname, String lastname,String email,String username, String password){
        Assert.assertTrue("Sign Up section is not displayed",webDriverWait.until(ExpectedConditions.visibilityOf(registerSection)).isDisplayed());
        webDriverWait.until(ExpectedConditions.visibilityOf(firstName)).sendKeys(firstname);
        webDriverWait.until(ExpectedConditions.visibilityOf(lastName)).sendKeys(lastname);
        webDriverWait.until(ExpectedConditions.visibilityOf(signup_email)).sendKeys(email);
        webDriverWait.until(ExpectedConditions.visibilityOf(signup_username)).sendKeys(username);
        webDriverWait.until(ExpectedConditions.visibilityOf(signup_password)).sendKeys(password);
        webDriverWait.until(ExpectedConditions.visibilityOf(confirm_password)).sendKeys(password);
        webDriverWait.until(ExpectedConditions.visibilityOf(confirmSignUp)).click();
        Assert.assertTrue("Registration confirmation message is not displayed",
                registrationConfirmation.getText().equals("Thanks for signing up. Please check your email for confirmation!"));
    }



}
