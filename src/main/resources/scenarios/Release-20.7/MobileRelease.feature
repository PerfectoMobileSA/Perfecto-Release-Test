Feature: Web Release test features

@visualText @20.7
Scenario: Verify Visual Analysis
Given I am on Github home Page
When I click "Sign up" with Visual Analysis
Then I should verify "Username" visually
And I should verify "Email address" visually
And I should verify "Password" visually

@phoneCall
Scenario: Verify incoming call
Given I make call to the device
Then I should see the incoming call

@sendSMS
Scenario: Verify SMS
Given I send SMS to the device
Then I should see SMS in message app

@accessibilityAudit
Scenario: Verify accessibility audit
Given I start application by name "Calculator"
Given I perform an audit of the accessibility on tag application screen "First Screen"
Then I should be able to download the audit results

