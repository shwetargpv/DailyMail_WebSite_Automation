@Regression
Feature: Verify breaking news page
    Scenario: Verify breaking news page
    Given I navigate to DailyMail website 
    When I click news link and verify breaking news
   
   
   Scenario: Verify TopRight corner day and date 
   Given I navigate to DailyMail website 
   When I verify the top right corner day and date
   
   Scenario: Verify overcast timing
   Given I navigate to DailyMail website 
   When I verify overcast timing is 1 hour later than current time 
  
   
   Scenario: Verify archive List
   Given I navigate to DailyMail website 
   When I verify archive list
  
   
   
   Scenario: Verify Yesterday date
   Given I navigate to DailyMail website 
   When I verify yesterday date
   
   Scenario: Verify SiteMap
   Given I navigate to DailyMail website 
   When I verify SiteMap
  
   
   
   