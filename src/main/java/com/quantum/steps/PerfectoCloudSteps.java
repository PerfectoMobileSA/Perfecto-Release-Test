package com.quantum.steps;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import com.qmetry.qaf.automation.core.ConfigurationManager;
import com.qmetry.qaf.automation.step.QAFTestStepProvider;
import com.quantum.utils.CloudUtils;

import cucumber.api.java.en.When;

@QAFTestStepProvider
public class PerfectoCloudSteps {

	@When("I upload file \"(.*?)\" to repository \"(.*?)\" path")
	public void iUploadfileToRepo(String localPath, String cloudRepo) throws IOException, URISyntaxException {
		CloudUtils.uploadMedia(System.getProperty("user.dir")+File.separator+localPath, cloudRepo);
		ConfigurationManager.getBundle().setProperty("isFileUploaded", true);
	}
}
