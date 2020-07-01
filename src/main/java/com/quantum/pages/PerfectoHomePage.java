package com.quantum.pages;

import com.perfecto.reportium.client.ReportiumClient;
import com.qmetry.qaf.automation.testng.report.ReporterUtil;
import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.quantum.utils.ReportUtils;

public class PerfectoHomePage extends WebDriverBaseTestPage<WebDriverTestPage>{

	@Override
	protected void openPage(PageLocator locator, Object... args) {
		// TODO Auto-generated method stub
		
	}
	
	@FindBy(locator = "perfecto.home.header.blog")
	private QAFWebElement perfectoHeaderBlog;
	
	@FindBy(locator = "perfecto.home.header.logins")
	private QAFWebElement perfectoHeaderLogins;
	
	@FindBy(locator = "perfecto.home.header.integrations")
	private QAFWebElement perfectoHeaderIntegrations;
	
	@FindBy(locator = "perfecto.home.header.contact")
	private QAFWebElement perfectoHeaderContact;
	
	@FindBy(locator = "perfecto.home.header.company")
	private QAFWebElement perfectoHeaderCompany;
	
	
	public void verifyPerfectoHomePage() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ReportUtils.logAssert("Verify Perfecto Home Page Blog option", perfectoHeaderBlog.isDisplayed());
		ReportUtils.logAssert("Verify Perfecto Home Page Logins option", perfectoHeaderLogins.isDisplayed());
		ReportUtils.logAssert("Verify Perfecto Home Page integrations option", perfectoHeaderIntegrations.isDisplayed());
		ReportUtils.logAssert("Verify Perfecto Home Page Contact option", perfectoHeaderContact.isDisplayed());
		ReportUtils.logAssert("Verify Perfecto Home Page Company option", perfectoHeaderCompany.isDisplayed());
	}

}
