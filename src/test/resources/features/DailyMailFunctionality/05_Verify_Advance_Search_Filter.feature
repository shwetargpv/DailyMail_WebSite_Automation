@Regression

Feature: Verify advance search filter
  Scenario Outline: Verify advance search filter and search result
    Given I navigate to DailyMail website 
    When I select web and Pass the word "<Keyword>"
    And I click on checkbox in Advance search filter with "<catagory>" and later find the "<element>"
    
Examples: 
      |Keyword  | catagory|element|
      |Australia| cricket |Cricket|