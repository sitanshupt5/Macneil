package com.macneil.pages;

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
        ((JavascriptExecutor)driver).executeScript("arguments[0].click()",shippingCalculator);
        Assert.assertTrue("Shipping details section is not expanded", wait.until(ExpectedConditions.visibilityOf(selectCountryDropdown)).isDisplayed());
        selectCountryDropdown.click();
        Assert.assertTrue("Country selection value is not displayed", wait.until(ExpectedConditions.visibilityOf(countrySelection)).isDisplayed());
        countrySelection.click();
        Assert.assertTrue("Shipping details section is not expanded", wait.until(ExpectedConditions.visibilityOf(selectStateDropdown)).isDisplayed());
        selectStateDropdown.click();
        Assert.assertTrue("Country selection value is not displayed", wait.until(ExpectedConditions.visibilityOf(stateSelection)).isDisplayed());
        stateSelection.click();
        wait.until(ExpectedConditions.visibilityOf(city)).sendKeys("St. Louis");
        wait.until(ExpectedConditions.visibilityOf(postalCode)).sendKeys("63105");
        wait.until(ExpectedConditions.visibilityOf(updateAddress)).click();

        Assert.assertTrue("Shipping address is not updated",wait.until(ExpectedConditions.textToBePresentInElement(shippingCalculator,"Change address")));
        Assert.assertTrue("Proceed to Checkout button is disabled", wait.until(ExpectedConditions.attributeToBe(proceedToCheckout.findElement(By.xpath("./parent::div")),"class","wc-proceed-to-checkout")));
        proceedToCheckout.click();
        return new CheckoutPage(driver);

    }
}
