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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingCartPage {

    WebDriver driver;
    WebDriverWait wait;
    Logger logger = LogManager.getLogger(ShoppingCartPage.class);

    @FindBy(xpath = "//button[@name='update_cart']")
    WebElement updateButton;

    @FindBy(xpath = "//a[@class='shipping-calculator-button']")
    WebElement shippingCalculator;

    @FindBy(id = "select2-calc_shipping_country-container")
    WebElement selectCountryDropdown;

    @FindBy(xpath = "//li[text()='United States (US)']")
    WebElement countrySelection;

    @FindBy(id = "select2-calc_shipping_state-container")
    WebElement selectStateDropdown;

    @FindBy(xpath = "//li[text()='Missouri']")
    WebElement stateSelection;

    @FindBy(id = "calc_shipping_city")
    WebElement city;

    @FindBy(id = "calc_shipping_postcode")
    WebElement postalCode;

    @FindBy(xpath = "//button[@type='submit' and @name='calc_shipping']")
    WebElement updateAddress;

    @FindBy(xpath = "//a[@class='checkout-button button alt wc-forward']")
    WebElement proceedToCheckout;



    public ShoppingCartPage(WebDriver driver)
    {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
        PageFactory.initElements(driver, this);
    }



    public CheckoutPage enterShippingDetails()
    {
        Assert.assertTrue("Update cart button is not disabled", wait.until(ExpectedConditions.attributeContains(updateButton, "aria-disabled", "true")));
        logger.info("Update cart button is disabled");
        ((JavascriptExecutor)driver).executeScript("arguments[0].click()",shippingCalculator);
        Assert.assertTrue("Shipping details section is not expanded", wait.until(ExpectedConditions.visibilityOf(selectCountryDropdown)).isDisplayed());
        logger.info("Shipping details button is expanded");
        selectCountryDropdown.click();
        logger.info("Country selection dropdown is clicked");
        Assert.assertTrue("Country selection value is not displayed", wait.until(ExpectedConditions.visibilityOf(countrySelection)).isDisplayed());
        logger.info("Country selection value is displayed");
        countrySelection.click();
        logger.info("Country has been selected");
        Assert.assertTrue("State selection dropdown is disabled", wait.until(ExpectedConditions.visibilityOf(selectStateDropdown)).isDisplayed());
        logger.info("State selection dropdown is enabled");
        selectStateDropdown.click();
        logger.info("State selection dropdown is clicked");
        Assert.assertTrue("State selection value is not displayed", wait.until(ExpectedConditions.visibilityOf(stateSelection)).isDisplayed());
        logger.info("State selection value is displayed");
        stateSelection.click();
        logger.info("State has been selected");
        wait.until(ExpectedConditions.visibilityOf(city)).sendKeys("St. Louis");
        logger.info("City value has been entered");
        wait.until(ExpectedConditions.visibilityOf(postalCode)).sendKeys("63105");
        logger.info("Postal code has been entered");
        wait.until(ExpectedConditions.visibilityOf(updateAddress)).click();
        logger.info("Update address button has been clicked");

        Assert.assertTrue("Shipping address is not updated",wait.until(ExpectedConditions.textToBePresentInElement(shippingCalculator,"Change address")));
        logger.info("Shipping address is updated");
        Assert.assertTrue("Proceed to Checkout button is disabled", wait.until(ExpectedConditions.attributeToBe(proceedToCheckout.findElement(By.xpath("./parent::div")),"class","wc-proceed-to-checkout")));
        logger.info("Checkout button is enabled");
        proceedToCheckout.click();
        logger.info("Checkout button has been clicked");
        return new CheckoutPage(driver);

    }
}
