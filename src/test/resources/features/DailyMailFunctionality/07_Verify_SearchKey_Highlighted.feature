@Regression

Feature: Verify search key highlighted
  Scenario Outline: Verify search result display per page
    Given I navigate to DailyMail website 
    When I select web and Pass the word "<Keyword>"
    Then I verify the search key word is highlighted on the webpage
    
Examples: 
      |Keyword  | 
      |Australia| 