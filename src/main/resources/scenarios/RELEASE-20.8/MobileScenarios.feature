Feature: Mobile Release test features

@fileUploadAndroid @20.8
Scenario: Verify File upload Android
Given I upload file "appFiles/ExpenseTrackerAndroid.apk" to repository "PRIVATE:releasetestExpenseTrackerAndroid.apk" path
And I install application "PRIVATE:releasetestExpenseTrackerAndroid.apk"
And I start application by id "io.perfecto.expense.tracker.hybrid"
Then I should see expense tracker login screen

@imageAnalysis @20.8
Scenario: Verify image analysis
Given I open "https://www.perfecto.io/" webpage
When I select perfecto logo "PRIVATE:perfecto.png" visually
Then I should see perfecto logo "PRIVATE:perfecto.png" visually

@fileUploadiOS @20.8
Scenario: Verify File upload iOS
Given I upload file "appFiles/ExpenseTrackeriOS.ipa" to repository "PRIVATE:releasetestExpenseTrackeriOS.ipa" path
And I install instrumented application "PRIVATE:releasetestExpenseTrackeriOS.ipa"
And I start application by id "io.perfecto.expense.tracker"
Then I should see expense tracker login screen

@nativeTest
Scenario: Verify Hellow World App
Given I start application by id "com.sample.project.Hello"
Then I verify Hello text
When I click Hello world button
Then I verify Hello text
