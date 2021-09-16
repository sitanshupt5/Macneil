package com.macneil.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage {

    WebDriver driver;
    WebDriverWait wait;
    Logger logger = LogManager.getLogger(CheckoutPage.class);

    @FindBy(xpath = "//input[@id='billing_first_name']")
    WebElement firstName;

    @FindBy(xpath = "//input[@id='billing_last_name']")
    WebElement lastName;

    @FindBy(xpath = "//input[@id='billing_address_1']")
    WebElement addressLine1;

    @FindBy(xpath = "//input[@id='billing_address_2']")
    WebElement addressLine2;

    @FindBy(xpath = "//input[@id='billing_phone']")
    WebElement phoneNumber;

    @FindBy(xpath = "//input[@id='billing_email']")
    WebElement billingEmail;

    @FindBy(xpath = "//input[@autocomplete='cc-number' and @name='cardnumber']")
    WebElement cardNumber;

    @FindBy(xpath = "//input[@aria-label='Credit or debit card expiration date']")
    WebElement expiryDate;

    @FindBy(xpath = "//input[@aria-label='Credit or debit card CVC/CVV']")
    WebElement cvvNumber;

    @FindBy(xpath = "//input[@name='terms']")
    WebElement acceptTerms;

    @FindBy(xpath = "//button[@id='place_order']")
    WebElement placeOrderButton;

    @FindBy(xpath = "//ul[contains(@class,'wc-stripe-error')]/li")
    WebElement ccAuthError;


    public CheckoutPage(WebDriver driver)
    {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
        PageFactory.initElements(driver, this);
    }

    public void placeOrder() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(firstName)).sendKeys("pawan");
        logger.info("First Name has been entered");
        wait.until(ExpectedConditions.visibilityOf(lastName)).sendKeys("tripathi");
        logger.info("Last name has been entered");
        wait.until(ExpectedConditions.visibilityOf(addressLine1)).sendKeys("Enterprise Holdings Inc.");
        logger.info("Address Line 1 has been entered");
        wait.until(ExpectedConditions.visibilityOf(addressLine2)).sendKeys("600 Corporate Park Drive");
        logger.info("Address Line 2 has been entered");
        wait.until(ExpectedConditions.visibilityOf(phoneNumber)).sendKeys("7894561236");
        logger.info("Phone number has been entered");
        wait.until(ExpectedConditions.visibilityOf(billingEmail)).sendKeys("abc@def.com");
        logger.info("Billing email has been entered");
        logger.info("Waiting for payment section to load");
        Thread.sleep(7000L);
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Secure card number input frame']")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",cardNumber);
        wait.until(ExpectedConditions.visibilityOf(cardNumber)).sendKeys("4444333322221111");
        logger.info("Credit card number has been entered");
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Secure expiration date input frame']")));
        wait.until(ExpectedConditions.visibilityOf(expiryDate)).sendKeys("1225");
        logger.info("Credit card expiration date has been entered");
        driver.switchTo().defaultContent();
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@title='Secure CVC input frame']")));
        wait.until(ExpectedConditions.visibilityOf(cvvNumber)).sendKeys("111");
        logger.info("Credit card CVV number has been entered");
        driver.switchTo().defaultContent();
        wait.until(ExpectedConditions.visibilityOf(acceptTerms)).click();
        logger.info("Terms and conditions are accepted");
        wait.until(ExpectedConditions.visibilityOf(placeOrderButton)).click();
        logger.info("Place order button has been clicked");

    }

    public void verifyCCAuthError()
    {
        Assert.assertTrue("Unauthourized Credit Card is accepted",
                wait.until(ExpectedConditions.visibilityOf(ccAuthError)).getText().
                        equals("Unable to process this payment, please try again or use alternative method."));
        logger.info("Error message is returned for Unauthorized Credit card");
    }
}
