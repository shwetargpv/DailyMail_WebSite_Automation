@Regression

Feature: Verify search result after page refresh
  Scenario Outline: Verify search results are remian same even after page is refreshed
    Given I navigate to DailyMail website 
    When I select web and Pass the word "<Keyword>"
    And I click on Total result count that is Ten
    Then I refresh the page and verify that the results are same 
    
Examples: 
      |Keyword  | 
      |Australia| 