package com.quantum.steps;

import java.util.concurrent.TimeUnit;

import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.quantum.pages.ScriptlessPage;
import com.quantum.utils.DriverUtils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@QAFTestStepProvider
public class ScriptlessStepDefs {
	ScriptlessPage scriptless = new ScriptlessPage();

	@Given("^I am on Perfecto cloud login Page$")
	public void I_am_on_Google_Search_Page() throws Throwable {
		try {
			new WebDriverTestBase().getDriver().manage().window().maximize();
		} catch (Exception e) {

		}
		DriverUtils.getDriver().manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		new WebDriverTestBase().getDriver().get("https://ps.app.perfectomobile.com");
		new WebDriverTestBase().getDriver().get("https://ps.app.perfectomobile.com");
	}
	
	@QAFTestStep(description="I login into perfecto")
	public void iLoginIntoPerfecto(){
		scriptless.perfectoLogin();
	}

	@When("^I open a scriptless test$")
	public void I_open_script() throws Throwable {
		scriptless.navigateToScriptess();
		scriptless.navigateToTest();
	}

	@Then("^I run a successful scriptless test$")
	public void I_run_script() throws Throwable {
		scriptless.runTest();
	}

	@Then("^I signout$")
	public void signout() throws Throwable {
		scriptless.signout();
	}

}
