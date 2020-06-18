package com.quantum.steps;

import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.quantum.pages.ExpenseTrackerLoginPage;

import cucumber.api.java.en.Then;

@QAFTestStepProvider
public class ExpenseTrackerSteps {

	@Then("I should see expense tracker login screen")
	public void verifyExpenseTrackerLogin() {
		new ExpenseTrackerLoginPage().verifyExpenseTrackerLoginScreen();
	}
}
