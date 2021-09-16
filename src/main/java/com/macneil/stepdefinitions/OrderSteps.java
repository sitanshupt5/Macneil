package com.macneil.stepdefinitions;

import com.macneil.pages.*;
import com.macneil.testbase.BaseUtils;
import com.macneil.testbase.StepLogger;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.plugin.event.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class OrderSteps extends BaseUtils {

    PartsPage partsPage;
    ProductDetailsPage productPage;
    ShoppingCartPage cartPage;
    CheckoutPage checkoutPage;
    Scenario scenario;
    Logger logger = LogManager.getLogger(BaseUtils.class);

    @Before
    public void startTestCase(Scenario scenario)
    {
        this.scenario = scenario;
        logger.info("Scenario: "+scenario.getName());
    }



    @Given("User is on the home page")
    public void user_is_on_the_home_page() throws IOException {
//        logger.info("Executing Step: "+ StepLogger.stepName);
        openWebsite();
    }

    @When("User navigates to parts page")
    public void user_navigates_to_parts_page() {
//        logger.info("Executing Step: "+ StepLogger.stepName);
        partsPage = homePage.openPartsPage();
    }
    @When("User Adds item {string} to cart")
    public void user_adds_item_to_cart(String productCode) throws InterruptedException {
//        logger.info("Executing Step: "+ StepLogger.stepName);
        productPage = partsPage.searchProduct(productCode);
        productPage.verifyProductDetailsPage();
        cartPage = productPage.addProductToCart();
    }
    @When("User proceeds to Checkout")
    public void cart_should_be_updated() {
//        logger.info("Executing Step: "+ StepLogger.stepName);
        checkoutPage=cartPage.enterShippingDetails();
    }

    @When("User places order with unauthorized card")
    public void user_places_order_with_unauthorized_card() throws InterruptedException {
//        logger.info("Executing Step: "+ StepLogger.stepName);
        checkoutPage.placeOrder();
    }
    @Then("Unauthorized payment error should be returned")
    public void unauthorized_payment_error_should_be_returned() {
//        logger.info("Executing Step: "+ StepLogger.stepName);
        checkoutPage.verifyCCAuthError();
    }

}
