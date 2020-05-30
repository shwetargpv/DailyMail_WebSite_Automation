@Regression
Feature: Verify sharing of article 
  Scenario: click on privacy policy and share an article 
    Given I navigate to DailyMail website 
    When I click on Privacy Policy
    Then I share the article and verify new window 
    

