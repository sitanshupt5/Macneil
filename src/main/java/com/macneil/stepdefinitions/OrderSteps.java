package com.macneil.stepdefinitions;

import com.macneil.pages.*;
import com.macneil.testbase.BaseUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

public class OrderSteps extends BaseUtils {

    PartsPage partsPage;
    ProductDetailsPage productPage;
    ShoppingCartPage cartPage;
    CheckoutPage checkoutPage;

    @Given("User is on the home page")
    public void user_is_on_the_home_page() throws IOException {
        openWebsite();
    }

    @When("User navigates to parts page")
    public void user_navigates_to_parts_page() {
        partsPage = homePage.openPartsPage();
    }
    @When("User Adds item {string} to cart")
    public void user_adds_item_to_cart(String productCode) throws InterruptedException {
        productPage = partsPage.searchProduct(productCode);
        productPage.verifyProductDetailsPage();
        cartPage = productPage.addProductToCart();
    }
    @When("User proceeds to Checkout")
    public void cart_should_be_updated() {
        checkoutPage=cartPage.enterShippingDetails();
    }

    @When("User places order with unauthorized card")
    public void user_places_order_with_unauthorized_card() throws InterruptedException {
        checkoutPage.placeOrder();
    }
    @Then("Unauthorized payment error should be returned")
    public void unauthorized_payment_error_should_be_returned() {
        checkoutPage.verifyCCAuthError();
    }

}
