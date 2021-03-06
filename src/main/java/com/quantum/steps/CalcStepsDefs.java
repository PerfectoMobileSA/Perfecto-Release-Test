/**
 * 
 */
package com.quantum.steps;

import org.openqa.selenium.By;

import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebElement;
import com.qmetry.qaf.automation.util.StringUtil;
import com.quantum.utils.*;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;


/**
 * @author chirag.jayswal
 *
 */
@QAFTestStepProvider
public class CalcStepsDefs {

	@When("clear Calculator")
	public void clearCalculator() {

		new QAFExtendedWebElement("btn.clear").click();
	}


	@When("add \"(.+)\" to \"(.+)\"")
	public void addInto(long l1, long l2) {

		DriverUtils.getDriver().findElement(MobileBy.AccessibilityId(String.valueOf(l1))).click();
		new QAFExtendedWebElement("btn.plus").click();
		DriverUtils.getDriver().findElement(MobileBy.AccessibilityId(String.valueOf(l1))).click();
		new QAFExtendedWebElement("btn.equal").click();;


	}



	@Then("result should be \"(.+)\"")
	public void resultShouldBe(long l1) {
		new QAFExtendedWebElement("input.box").verifyText("in:" + String.valueOf(l1));
	}

	@Then("I switch to frame \"(.*?)\"")
	public static void switchToFrame(String nameOrIndex) {
		if (StringUtil.isNumeric(nameOrIndex)) {
			int index = Integer.parseInt(nameOrIndex);
			new WebDriverTestBase().getDriver().switchTo().frame(index);
		} else {
			new WebDriverTestBase().getDriver().switchTo().frame(nameOrIndex);
		}
	}

	@Then("I switch to \"(.*?)\" frame by element")
	public static void switchToFrameByElement(String loc) {
		new WebDriverTestBase().getDriver().switchTo().frame(new QAFExtendedWebElement(loc));
	}

	@When("I am using an AppiumDriver")
	public void testForAppiumDriver() {
		if (ConfigurationUtils.getBaseBundle().getPropertyValue("driver.name").contains("Remote"))
			ConsoleUtils.logWarningBlocks("Driver is an instance of QAFExtendedWebDriver");
		else if (AppiumUtils.getAppiumDriver() instanceof IOSDriver)
			ConsoleUtils.logWarningBlocks("Driver is an instance of IOSDriver");
		else if (AppiumUtils.getAppiumDriver() instanceof AndroidDriver)
			ConsoleUtils.logWarningBlocks("Driver is an instance of AndroidDriver");
	}
	
	@When("I perform addition of \"(.*?)\" and \"(.*?)\" with Visual Analysis")
	public void iPerformAdditionWithVisualAnalysis(String num1, String num2) {
		new PerfectoUtils().clickWithVisualText(num1);
		new PerfectoUtils().clickWithVisualText("+");
		new PerfectoUtils().clickWithVisualText(num2);
		new PerfectoUtils().clickWithVisualText("=");
	}
	
	@Then("I should see the result \"(.*?)\"")
	public void iShouldSeeTheResultVisually(String res) {
		DeviceUtils.verifyVisualText(res);
	}


}
