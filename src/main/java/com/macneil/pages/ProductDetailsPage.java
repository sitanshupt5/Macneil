package com.macneil.pages;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailsPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//h1[@class='product_title entry-title']")
    WebElement productTitle;

    @FindBy(xpath = "//span[@class='woocommerce-Price-amount amount']/child::bdi")
    WebElement productPrice;

    @FindBy(xpath = "//div[@class='quantity']/input")
    WebElement productQuantity;

    @FindBy(xpath = "//button[@name='add-to-cart']")
    WebElement addToCart;

    @FindBy(xpath = "//a[@title='View your shopping cart']")
    WebElement shoppingCart;

    public ProductDetailsPage(WebDriver driver)
    {
        this.driver = driver;
        wait = new WebDriverWait(driver, 20);
        PageFactory.initElements(driver, this);
    }

    public void loadProductDetailsPage(){
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor)driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
        wait.until(pageLoadCondition);
    }

    public void verifyProductDetailsPage(){
        Assert.assertTrue("Product title is missing from the page", productTitle.isDisplayed());
        Assert.assertTrue("Product price is missing from the page", productPrice.isDisplayed());
        Assert.assertTrue("Product quantity is missing from the page", productQuantity.isDisplayed());
    }

    public ShoppingCartPage addProductToCart(){
        verifyProductDetailsPage();
        wait.until(ExpectedConditions.elementToBeClickable(addToCart)).click();
        wait.until(ExpectedConditions.attributeContains(addToCart, "class", "added"));
        wait.until(ExpectedConditions.elementToBeClickable(shoppingCart)).click();
        return new ShoppingCartPage(driver);
    }

}
