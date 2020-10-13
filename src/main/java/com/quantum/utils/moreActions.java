package com.quantum.utils;

import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.test.TestContext;
import com.perfecto.reportium.test.result.TestResultFactory;
import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.core.TestBaseProvider;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by uzie on 2/27/17.
 */
public class moreActions {

	public static void type(String t) {
		HashMap params = new HashMap();
		params.put("text", String.valueOf(t));

		getQAFDriver().executeScript("mobile:typetext", new Object[]{params});
	}

	public static void closeKeyboard() {
		HashMap params = new HashMap();

		params.put("mode", "off");

		getQAFDriver().executeScript("mobile:keyboard:display", new Object[]{params});

	}
	public static void callMe(String n) {
		HashMap params = new HashMap();
		params.put("to.handset", String.valueOf(n));

		getQAFDriver().executeScript("mobile:gateway:call", new Object[]{params});
	}

	public static void gpsOn( ) {
		sendADB("settings put secure location_providers_allowed +gps");
	}
	public static void gpsOff( ) {
		sendADB("settings put secure location_providers_allowed -gps");
	}



	public static void sendADB(String cmd) {
		HashMap params = new HashMap();
		params.put("command", cmd);

		getQAFDriver().executeScript("mobile:handset:shell", new Object[]{params});
	}


	public static void visualClick(String tx) {

		getQAFDriver().findElement(By.linkText(tx));
	}

	public static QAFExtendedWebDriver getQAFDriver() {
		return (new WebDriverTestBase()).getDriver();
	}



	//Audioo
	public static String  startRecord() {
		HashMap params = new HashMap();

		return getQAFDriver().executeScript("mobile:audio.recording:start", new Object[]{params}).toString();
	}

	public static void  stopRecord() {
		HashMap params = new HashMap();

		getQAFDriver().executeScript("mobile:audio.recording:stop", new Object[]{params});
	}

	public static String  speech2Text(String link) {
		HashMap params = new HashMap();
		params.put("AudioLink", link);

		return  getQAFDriver().executeScript("mobile:Audio:speech2text", new Object[]{params}).toString();
	}

	public static String getDevicePhoneNumber(){

		Map<String, Object> params1 = new HashMap<>();
		params1.put("property", "phoneNumber");
		return (String) getQAFDriver().executeScript("mobile:handset:info", params1);

	}

	public static void verifyAuditResults() {
		RemoteWebDriver driver =null;
		ReportiumClient reportiumClient =null;
		try {
			String reportUrl = ReportUtils.getReportClient().getReportUrl();
			System.out.println(reportUrl);
			ReportUtils.getReportClient().testStop(TestResultFactory.createSuccess());
			DriverUtils.getDriver().quit();
			DesiredCapabilities capabilities = new DesiredCapabilities("", "", Platform.ANY);
			capabilities.setCapability("platformName", "Windows");
			capabilities.setCapability("platformVersion", "10");
			capabilities.setCapability("browserName", "Chrome");
			capabilities.setCapability("browserVersion", "latest");
			capabilities.setCapability("location", "US East");
			capabilities.setCapability("resolution", "1024x768");
			capabilities.setCapability("securityToken", ConfigurationManager.getBundle().getString("perfecto.capabilities.securityToken"));
			PerfectoExecutionContext perfectoExecutionContext = null;
			try {
				driver = new RemoteWebDriver(new URL(ConfigurationManager.getBundle().getString("remote.server")),capabilities);
				driver.manage().window().maximize();
				if(System.getProperty("reportium-job-name","")!="" && System.getProperty("reportium-job-number","")!="") {
					perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
							//								.withProject(new Project("Sample Reportium project", "1.0"))
							.withJob(new Job(System.getProperty("reportium-job-name",""), Integer.parseInt(System.getProperty("reportium-job-number",""))).withBranch("branch-name"))
							.withContextTags("Regression")
							.withWebDriver(driver)
							.build();
				}else {
					perfectoExecutionContext = new PerfectoExecutionContext.PerfectoExecutionContextBuilder()
							.withWebDriver(driver)
							.build();
				}

				reportiumClient = new ReportiumClientFactory().createPerfectoReportiumClient(perfectoExecutionContext);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			TestContext context = new TestContext.Builder()
					.withTestExecutionTags("Test", "AuditResults")
					.build();
			reportiumClient.testStart("Verify Audit Results", context);
			reportiumClient.stepStart("Navigate to URL");
			driver.get(reportUrl);
			reportiumClient.stepStart("Signin");
			driver.findElement(By.cssSelector("#username")).sendKeys(ConfigurationManager.getBundle().getPropertyValue("perfecto.capabilities.user"));
			driver.findElement(By.cssSelector("#password")).sendKeys(ConfigurationManager.getBundle().getPropertyValue("perfecto.capabilities.password"));
			driver.findElement(By.cssSelector("#kc-login")).click();
			reportiumClient.stepStart("Download Audit files");
			driver.findElement(By.cssSelector("[class='pm-icon pm-icon-download-icon']")).click();
			WebElement ele = driver.findElement(By.cssSelector("[data-aid='download-menu-item-Accessibility Report-text']"));
			ele.isDisplayed();
			ele.click();
			reportiumClient.stepStart("Verify Network file");
			List<String> files = (List<String>) driver.executeScript("perfecto:file:list");
			for (String file : files) {
				System.out.println("File:"+file);
				if(file.contains(".zip")) {
					reportiumClient.reportiumAssert("Network files Validation.", true);
					break;
				}
				reportiumClient.reportiumAssert("Network files Validation.", false);
			}
			reportiumClient.testStop(TestResultFactory.createSuccess());
			System.out.println("Report Url: "+reportiumClient.getReportUrl());
		} catch (Exception e) {
			e.printStackTrace();
			reportiumClient.testStop(TestResultFactory.createFailure(e));
		}finally {
			if(driver!=null)
				driver.quit();
		}
	}

}


