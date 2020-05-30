@Regression

Feature: Verify search result on each page
  Scenario Outline: Verify search result display per page
    Given I navigate to DailyMail website 
    When I select web and Pass the word "<Keyword>"
    And I click on Total result count that is Ten
    
Examples: 
      |Keyword  | 
      |Australia| 