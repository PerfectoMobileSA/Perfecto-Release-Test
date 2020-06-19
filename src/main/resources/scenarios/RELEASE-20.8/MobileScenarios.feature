Feature: Mobile Release test features

@fileUploadAndroid @20.8
Scenario: Verify File upload Android
Given I upload file "appFiles/ExpenseTrackerAndroid.apk" to repository "PRIVATE:releasetestExpenseTrackerAndroid.apk" path
And I install application "PRIVATE:releasetestExpenseTrackerAndroid.apk"
And I start application by id "io.perfecto.expense.tracker.hybrid"
Then I should see expense tracker login screen

@imageAnalysis @20.8
Scenario: Verify image analysis
Given I am on Google Search Page
When I select google logo "PRIVATE:googlelogo.png" visually
Then I should see google logo "PRIVATE:googlelogo.png" visually

@fileUploadiOS @20.8
Scenario: Verify File upload iOS
Given I upload file "appFiles/ExpenseTrackeriOS.ipa" to repository "PRIVATE:releasetestExpenseTrackeriOS.ipa" path
And I install instrumented application "PRIVATE:releasetestExpenseTrackeriOS.ipa"
And I start application by id "io.perfecto.expense.tracker"
Then I should see expense tracker login screen

@nativeTest
Scenario: Verify Expense tracker Login
Given I start application by id "io.perfecto.expense.tracker"
Then I should see expense tracker Native login screen
When I enter "test@test.com" and "password123" in native login screen
Then I should see expense tracker Native login screen
