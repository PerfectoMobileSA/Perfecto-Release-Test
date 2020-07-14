package com.quantum.steps;

import org.openqa.selenium.By;

import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.quantum.pages.ExpenseTrackerLoginPage;
import com.quantum.utils.ReportUtils;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@QAFTestStepProvider
public class ExpenseTrackerSteps {

	@Then("I should see expense tracker login screen")
	public void verifyExpenseTrackerLogin() {
		new ExpenseTrackerLoginPage().verifyExpenseTrackerLoginScreen();
	}
	
	@Then("I should see expense tracker Native login screen")
	public void verifyExpenseTrackerNativeLogin() {
		new ExpenseTrackerLoginPage().verifyExpenseTrackerNativeLoginScreen();
	}
	
	@When("I enter \"(.*?)\" and \"(.*?)\" in native login screen")
	public void iEnterLoginDetilsInNativeLoginScreen(String email, String password) {
		new ExpenseTrackerLoginPage().loginNative(email, password);
	}
	
	@Then("I verify Hello text")
	public void iverifyHelloText() {
		ReportUtils.logAssert("Verify Hello app.", new QAFExtendedWebElement(By.xpath("//*[@name='Hello World']")).isDisplayed());
	}
	
	@When("I click Hello world button")
	public void iclickHelloWorldButton() {
		 new QAFExtendedWebElement(By.xpath("//*[@name='Hello World']")).click();
	}
}
