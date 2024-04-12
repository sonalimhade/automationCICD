
@tag
Feature: Purchase the order from Ecommerce site
  I want to use this template for my feature file

	Background:
	Given I landed on Ecommerce Page


  @Regression
  Scenario Outline: Positive test for submitting order
    Given Logged in with username <name> and <Password>
    When I add <ProductName> to cart
    And Checkout <ProductName> and submit the order
    Then "Thankyou for the order." message is displayed on confirmation page

    Examples: 
      | name  | Password | ProductName  |
      | test254@gmail.com | Test@123 | ZARA COAT 3 |
      
