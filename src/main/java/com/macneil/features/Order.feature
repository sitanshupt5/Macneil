Feature: To verify placement of orders.

  Scenario: Verify card authorization error during order placement
    Given User is on the home page
    When User navigates to parts page
    And User Adds item "40-745-00-MP" to cart
    And User proceeds to Checkout
    When User places order with unauthorized card
    Then Unauthorized payment error should be returned