
@tag
Feature: Error Validation
  I want to use this template for my feature file


  @ErrorValidation
  Scenario Outline: Login scenario validation
    Given I landed on Ecommerce Page
    When Logged in with username <name> and <Password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | name  						| Password |
      | test254@gmail.com | Test@124 |
