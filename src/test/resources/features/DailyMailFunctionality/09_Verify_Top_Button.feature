@Regression

Feature: Verify top button functionality
  Scenario Outline: Verify on clicking Top button, user is taken to the Top of the Page
    Given I navigate to DailyMail website 
    When I select web and Pass the word "<Keyword>"
    And I click on Top Button
    Then I verify that i am on the top of the page
    
Examples: 
      |Keyword  | 
      |Australia| 