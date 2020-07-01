Feature: Web Release test features

@20.7Web @20.7 @20.8 @20.8Web
Scenario: Verify new Browser versions
When I am on Google Search Page
And I search for "Perfecto Mobile"
Then it should have "Perfecto Mobile" in search results
When I click perfecto link
Then I should be navigated to perfecto home page