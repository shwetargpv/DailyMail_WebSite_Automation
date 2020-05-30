@Regression
Feature: Verify AddComment section 
  Scenario Outline: Click on comment link and verify AddComment section
    Given I navigate to DailyMail website 
    When I click on comment Link 
    Then I verify the AddComment section with "<Comment>"
 Examples:
   |Comment                          |
   |This article is very informative.|

