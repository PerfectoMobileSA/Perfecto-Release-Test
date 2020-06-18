package com.quantum.steps;

import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.quantum.pages.PerfectoHomePage;

import cucumber.api.java.en.Then;

@QAFTestStepProvider
public class PerfectoHomeSteps {

	
	@Then("I should be navigated to perfecto home page")
	public void iShouldBeNavigatedToPerfectoHomePage() {
		new PerfectoHomePage().verifyPerfectoHomePage();
	}
}
