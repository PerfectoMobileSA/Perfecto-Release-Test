package com.quantum.steps;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.quantum.pages.GooglePage;
import com.quantum.utils.DeviceUtils;
import com.quantum.utils.DriverUtils;
import com.quantum.utils.PerfectoUtils;
import com.quantum.utils.ReportUtils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@QAFTestStepProvider
public class GoogleStepDefs {
	@Given("^I am on Google Search Page$")
	public void I_am_on_Google_Search_Page() throws Throwable {
		try {
			new WebDriverTestBase().getDriver().manage().window().maximize();
		} catch (Exception e) {
			
		}
		DriverUtils.getDriver().manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		new WebDriverTestBase().getDriver().get("http://www.google.com/");
		new WebDriverTestBase().getDriver().get("http://www.google.com/");
	}
	
	@Given("^I open \"([^\"]*)\" webpage$")
	public void I_Open_WebPage(String URL) throws Throwable {
		try {
			new WebDriverTestBase().getDriver().manage().window().maximize();
		} catch (Exception e) {
			
		}
		DriverUtils.getDriver().manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		new WebDriverTestBase().getDriver().get(URL);
		new WebDriverTestBase().getDriver().get(URL);
	}

	@When("^I search for \"([^\"]*)\"$")
	public void I_search_for(String searchKey) throws Throwable {
		QAFExtendedWebElement searchBoxElement = new QAFExtendedWebElement("search.text.box");
		QAFExtendedWebElement searchBtnElement = new QAFExtendedWebElement("search.button");

		searchBoxElement.clear();
		searchBoxElement.sendKeys(searchKey);
		searchBoxElement.sendKeys(Keys.ENTER);
		// Web and mobile elements are sometimes different so we have done two things we
		// used multiple/alternate locator strategy for finding the element.
		// We also used Javascript click because the element was getting hidden in
		// Desktop Web due to suggestions and was not clickable. This java script click
		// will work for both desktop and mobile in this case.
//		JavascriptExecutor js = (JavascriptExecutor) DeviceUtils.getQAFDriver();
//		js.executeScript("arguments[0].click();", searchBtnElement);

	}

	@Then("^it should have \"([^\"]*)\" in search results$")
	public void it_should_have_in_search_results(String result) throws Throwable {
		QAFExtendedWebElement searchResultElement = new QAFExtendedWebElement("partialLink=" + result);
		searchResultElement.verifyPresent(result);
	}

	@Then("^it should have following search results:$")
	public void it_should_have_all_in_search_results(List<String> results) {
		QAFExtendedWebElement searchResultElement;
		for (String result : results) {
			searchResultElement = new QAFExtendedWebElement("partialLink=" + result);
			searchResultElement.verifyPresent(result);
		}
	}
	
	@When("I click perfecto link")
	public void iClickPerfectoLink() {
		new GooglePage().clickPerfectoLink();
	}
	
	@Given("I am on Github home Page")
	public void iOpenPerfectoSupportPage() {
		try {
			new WebDriverTestBase().getDriver().manage().window().maximize();
		} catch (Exception e) {
			
		}
		DriverUtils.getDriver().manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		new WebDriverTestBase().getDriver().get("https://github.com/");
		new WebDriverTestBase().getDriver().get("https://github.com/");
	}
	
	@When("I click \"([^\"]*)\" with Visual Analysis")
	public void iClickWithVisualAnalysis(String content) {
		new PerfectoUtils().clickWithVisualText(content,30);
	}
	
	@Then("I should verify \"([^\"]*)\" visually")
	public void iVerifyTextVisually(String text) {
		ReportUtils.getReportClient().reportiumAssert("Verify Text: "+text, new PerfectoUtils().verifyVisualText(text,30));
	}
	
	@When("I select perfecto logo \"([^\"]*)\" visually")
	public void iSelectPerfectoWithImage(String img) throws InterruptedException {
		Thread.sleep(3000);
		new PerfectoUtils().clickWithVisualImage(img, 30);
	}
	
	@Then("I should see perfecto logo \"([^\"]*)\" visually")
	public void iVerifyPerfectoWithImage(String img) {
		new PerfectoUtils().verifyVisualImage(img, 30);
	}
	
}
