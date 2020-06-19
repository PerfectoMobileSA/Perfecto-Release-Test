package com.quantum.listeners;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.ui.webdriver.CommandTracker;
import com.qmetry.qaf.automation.ui.webdriver.QAFExtendedWebDriver;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebDriverCommandListener;
import com.qmetry.qaf.automation.util.PropertyUtil;
import com.quantum.utils.CloudUtils;

public class DriverCustomListener implements QAFWebDriverCommandListener {

	@Override
	public void beforeCommand(QAFExtendedWebDriver driver, CommandTracker commandHandler) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCommand(QAFExtendedWebDriver driver, CommandTracker commandHandler) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFailure(QAFExtendedWebDriver driver, CommandTracker commandHandler) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeInitialize(Capabilities desiredCapabilities) {
		// TODO Auto-generated method stub
		if(((DesiredCapabilities)desiredCapabilities).getCapability("localAppPath")!=null) {
			String localPath = ((DesiredCapabilities)desiredCapabilities).getCapability("localAppPath").toString();
			if(localPath !=null || localPath!="") {
				try {
					String appPath = ((DesiredCapabilities)desiredCapabilities).getCapability("app").toString();
					CloudUtils.uploadMedia(System.getProperty("user.dir")+File.separator+"appFiles"+File.separator+localPath, appPath);
				} catch (IOException | URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
//		if(((DesiredCapabilities)desiredCapabilities).getCapability("app")!=null) {
//			if(((DesiredCapabilities)desiredCapabilities).getCapability("app").toString().contains(".app")) {
//				System.out.println(System.getProperty("user.dir")+File.separator+"appFiles"+File.separator+((DesiredCapabilities)desiredCapabilities).getCapability("app").toString());
////				Object util1 = ConfigurationManager.getBundle().getProperty("driver.init.retry.timeout");
////				System.out.println(util1);
//				((DesiredCapabilities)desiredCapabilities).setCapability("app", System.getProperty("user.dir")+File.separator+"appFiles"+File.separator+((DesiredCapabilities)desiredCapabilities).getCapability("app").toString());
////				ConfigurationManager.getBundle().setProperty("driver.init.retry.timeout", 120);
////				PropertyUtil util = ConfigurationManager.getBundle();
////				System.out.println(util.getProperty("driver.init.retry.timeout"));
//			}
//		}
	}

	@Override
	public void onInitialize(QAFExtendedWebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onInitializationFailure(Capabilities desiredCapabilities, Throwable t) {
		// TODO Auto-generated method stub

	}

}
