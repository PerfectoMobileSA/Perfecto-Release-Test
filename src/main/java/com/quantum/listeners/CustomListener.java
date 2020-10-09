package com.quantum.listeners;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.quantum.utils.DriverUtils;
import com.quantum.utils.ReportUtils;
import com.perfecto.reportium.client.ReportiumClient;
import com.perfecto.reportium.client.ReportiumClientFactory;
import com.perfecto.reportium.exception.ReportiumException;
import com.perfecto.reportium.model.CustomField;
import com.perfecto.reportium.model.Job;
import com.perfecto.reportium.model.PerfectoExecutionContext;
import com.perfecto.reportium.model.Project;
import com.perfecto.reportium.test.TestContext;
import com.perfecto.reportium.test.result.TestResultFactory;

public class CustomListener implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		verifyHarFile();
	}

	@Override
	public void onTestFailure(ITestResult result) {
		result.getThrowable().getMessage();
		verifyHarFile();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}
	
	public static void verifyHarFile() {
		RemoteWebDriver driver =null;
		ReportiumClient reportiumClient =null;
		try {
			if(ConfigurationManager.getBundle().getBoolean("perfecto.harfile.enable",false)) {
				String reportUrl = ReportUtils.getReportClient().getReportUrl();
				System.out.println(reportUrl);
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
                        .withTestExecutionTags("Test", "HarFile")
                        .build();
				reportiumClient.testStart("Verify Har file", context);
				reportiumClient.stepStart("Navigate to URL");
				driver.get(reportUrl);
				reportiumClient.stepStart("Signin");
				driver.findElement(By.cssSelector("#username")).sendKeys(ConfigurationManager.getBundle().getPropertyValue("perfecto.capabilities.user"));
				driver.findElement(By.cssSelector("#password")).sendKeys(ConfigurationManager.getBundle().getPropertyValue("perfecto.capabilities.password"));
				driver.findElement(By.cssSelector("#kc-login")).click();
				reportiumClient.stepStart("Download Network file");
				driver.findElement(By.cssSelector("[class='pm-icon pm-icon-download-icon']")).click();
				driver.findElement(By.cssSelector("[data-aid='download-menu-item-NETWORK'][data-href*='.zip']")).click();
				reportiumClient.stepStart("Verify Network file");
				List<String> files = (List<String>) driver.executeScript("perfecto:file:list");
				for (String file : files) {
					if(file.contains(".zip")) {
						reportiumClient.reportiumAssert("Network files Validation.", true);
						break;
					}
					reportiumClient.reportiumAssert("Network files Validation.", false);
				}
				System.out.println("Report Url: "+reportiumClient.getReportUrl());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(driver!=null)
				driver.quit();
		}
	}

}
