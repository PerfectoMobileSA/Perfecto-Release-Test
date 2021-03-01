package com.quantum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.qmetry.qaf.automation.util.PropertyUtil;

public class ScriptlessPage extends WebDriverBaseTestPage<WebDriverTestPage> {

	String projectName = "Genesis";
	String testName = "google";
	String pass = "success";
	String frame = "codeless-iframe";
	PropertyUtil prop = ConfigurationManager.getBundle();

	@Override
	protected void openPage(PageLocator locator, Object... args) {
		// TODO Auto-generated method stub

	}

	@FindBy(locator = "perfecto.scriptless.username.textfield")
	private QAFWebElement usernameTxt;

	@FindBy(locator = "perfecto.scriptless.password.textfield")
	private QAFWebElement passwordTxt;

	@FindBy(locator = "perfecto.scriptless.signin.button")
	private QAFWebElement signinBtn;

	@FindBy(locator = "perfecto.scriptless.build.web.test.button")
	private QAFWebElement buildWebTestBtn;

	@FindBy(locator = "perfecto.scriptless.projectSearch.textfield")
	private QAFWebElement projectSearch;

	@FindBy(locator = "perfecto.scriptless.test.run.button")
	private QAFWebElement run;

	@FindBy(locator = "perfecto.scriptless.test.disableBreakPoint.button")
	private QAFWebElement disableBreakPoint;

	@FindBy(locator = "perfecto.scriptless.test.start.button")
	private QAFWebElement start;

	@FindBy(locator = "perfecto.scriptless.test.finalRun.button")
	private QAFWebElement finalRun;

	@FindBy(locator = "perfecto.scriptless.test.viewOnCanvas.button")
	private QAFWebElement viewOnCanvas;

	@FindBy(locator = "perfecto.scriptless.test.tests.button")
	private QAFWebElement tests;

	@FindBy(locator = "perfecto.scriptless.settings.button")
	private QAFWebElement settings;

	@FindBy(locator = "perfecto.scriptless.signout.button")
	private QAFWebElement signout;

	public void perfectoLogin() {
		usernameTxt.waitForVisible(10000);
		usernameTxt.click();
		usernameTxt.sendKeys(ConfigurationManager.getBundle().getPropertyValue("perfecto.capabilities.user"));
		passwordTxt.waitForVisible(10000);
		passwordTxt.click();
		passwordTxt.sendKeys(ConfigurationManager.getBundle().getPropertyValue("perfecto.capabilities.password"));
		signinBtn.waitForVisible(10000);
		signinBtn.click();
		buildWebTestBtn.waitForVisible(10000);
	}

	public void navigateToScriptess() {
		buildWebTestBtn.waitForVisible(30000);
		buildWebTestBtn.click();
	}

	public void navigateToTest() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
		projectSearch.waitForVisible(10000);
		projectSearch.sendKeys(projectName);
		QAFExtendedWebElement project = new QAFExtendedWebElement(
				String.format(prop.getString("perfecto.scriptless.project.button"), projectName));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", project);
		project.click();
		QAFExtendedWebElement testBy = new QAFExtendedWebElement(
				String.format(prop.getString("perfecto.scriptless.testBy.button"), testName));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", testBy);
		testBy.click();
		Thread.sleep(5000);
	}

	public void runTest() throws InterruptedException {
		run.waitForVisible(30000);
		run.click();
		start.waitForVisible(30000);
		start.click();
		finalRun.waitForVisible(30000);
		finalRun.click();
		QAFExtendedWebElement success = new QAFExtendedWebElement(
				String.format(prop.getString("perfecto.scriptless.test.success.label"), pass));
		Thread.sleep(40000);
		success.waitForVisible(60000);
	}

	public void signout() {
		viewOnCanvas.click();
		tests.click();
		driver.switchTo().defaultContent();
		settings.click();
		signout.click();
	}
}
