@Regression
Feature: Verify sorting of comments 
  Scenario: Verify all comments are sorted as per the catagory selected 
    Given I navigate to DailyMail website 
    When I click on comment Link 
   # Then I verify by default all the comments are sorted with newest
   # And I verify clicking on Oldest it should display all comments with Oldest order
    And I verify best rated sorting
 
   
