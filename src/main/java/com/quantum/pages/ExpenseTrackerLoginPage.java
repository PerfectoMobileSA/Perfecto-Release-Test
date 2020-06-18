package com.quantum.pages;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.qmetry.qaf.automation.ui.WebDriverBaseTestPage;
import com.qmetry.qaf.automation.ui.annotations.FindBy;
import com.qmetry.qaf.automation.ui.api.PageLocator;
import com.qmetry.qaf.automation.ui.api.WebDriverTestPage;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.quantum.utils.DeviceUtils;
import com.quantum.utils.DriverUtils;
import com.quantum.utils.ReportUtils;

public class ExpenseTrackerLoginPage extends WebDriverBaseTestPage<WebDriverTestPage> {

	@Override
	protected void openPage(PageLocator locator, Object... args) {
		// TODO Auto-generated method stub
		
	}

	@FindBy(locator = "login.email.textfield")
	private QAFWebElement logintextfiled;
	
	public void verifyExpenseTrackerLoginScreen() {
		Map<String, Object> params = new HashMap<>();
		params.put("content", "Email");
		params.put("timeout", "15");
		driver.executeScript("mobile:text:find", params);
	}
}
