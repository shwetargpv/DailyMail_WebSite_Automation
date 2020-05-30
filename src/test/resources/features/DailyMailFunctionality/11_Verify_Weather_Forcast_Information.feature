@Regression
Feature: Verify 5days weather forcast
  Scenario Outline: Verify 5days weather forcast information
    Given I navigate to DailyMail website 
    When I click on 5days forcast
    Then I verify forcast information of "<city>" "<Searchword>"

Examples:
|Searchword             |city     |
#|Temprature of Sydney   |Sydney   |
#|Temprature of Melbourne|Melbourne|
|Temprature of Brisbane |Brisbane |