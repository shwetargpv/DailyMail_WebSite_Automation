@Regression

Feature: Verify search results displayed
  Scenario Outline: Click on random search and verify search result
    Given I navigate to DailyMail website 
    When I select web and Pass the word "<Keyword>"
    And I sort the results with relevence
    Then I click on random search and verify the search results
Examples: 
      |Keyword  |              
      |Australia| 