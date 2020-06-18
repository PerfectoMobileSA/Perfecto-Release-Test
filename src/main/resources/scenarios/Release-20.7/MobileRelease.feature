Feature: Web Release test features

@visualText @20.7
Scenario: Verify Visual Analysis
Given I am on Github home Page
When I click "Sign up" with Visual Analysis
Then I should verify "Username" visually
And I should verify "Email address" visually
And I should verify "Password" visually

@phoneCall
Scenario: Verify calls between 2 devices
Given I close application by name "Tide"