package com.macneil.pages;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    WebDriver driver;
    WebDriverWait webDriverWait;

    @FindBy(css = ".pum-close")
    WebElement close_popup;

    @FindBy(xpath = "//a[text()='Home']/parent::li")
    WebElement home;

    @FindBy(xpath = "//a[text()='Systems']/parent::li")
    WebElement systems;

    @FindBy(xpath = "//a[text()='Equipment']/parent::li")
    WebElement equipment;

    @FindBy(xpath = "//a[text()='Parts']/parent::li")
    WebElement parts;

    @FindBy(xpath = "//a[text()='Sales and Service']/parent::li")
    WebElement sales;

    @FindBy(xpath = "//a[text()='CleanTouch Solutions']/parent::li")
    WebElement cleanTouch;

    @FindBy(xpath = "//a[text()='Resources']/parent::li")
    WebElement resources;

    @FindBy(xpath = "//a[text()='Contact']/parent::li")
    WebElement contact;

    public HomePage(WebDriver driver)
    {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, 20);
        PageFactory.initElements(driver, this);
    }

    public void loadHomePage(){
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor)driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
        webDriverWait.until(pageLoadCondition);

    }

    public PartsPage openPartsPage(){
        webDriverWait.until(ExpectedConditions.visibilityOf(close_popup)).click();
        webDriverWait.until(ExpectedConditions.visibilityOf(parts)).click();
        return new PartsPage(driver);
    }
}
