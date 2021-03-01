@scriptless
Feature: Scriptless test

  @executeTest
  Scenario: Scriptless Execution Test
    Given I am on Perfecto cloud login Page
    When I login into perfecto
    And I open a scriptless test
    Then I run a successful scriptless test
    And I signout