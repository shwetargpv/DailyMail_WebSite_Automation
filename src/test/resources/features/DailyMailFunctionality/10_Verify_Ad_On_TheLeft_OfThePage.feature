@Regression

Feature: Verify ad on the top left of the page
  Scenario Outline: Verify click on the ad and verify it is open
    Given I navigate to DailyMail website 
    When I select web and Pass the word "<Keyword>"
    And I click on the Ad
    Then I verify that Ad is open
    
Examples: 
      |Keyword  | 
      |Australia| 