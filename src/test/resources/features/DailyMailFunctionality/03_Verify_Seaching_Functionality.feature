@Regression

Feature: Verify search function and Pagination on search results
  Scenario Outline: Search and sort with relevance then Verify Pagination on search results page.
    Given I navigate to DailyMail website 
    When I select web and Pass the word "<Keyword>"
    And I sort the results with relevence
Examples: 
      |Keyword  |              
      |Australia| 