package com.quantum.utils;

import java.util.HashMap;
import java.util.Map;

public class PerfectoUtils {

	public void clickWithVisualText(String content) {
		Map<String, Object> params = new HashMap<>();
		params.put("content", content);
		params.put("threshold", 80);
		params.put("timeout", 10);
		DriverUtils.getAppiumDriver().executeScript("mobile:text:select", params);
	}
	
	public void clickWithVisualText(String content, int timeout) {
		Map<String, Object> params = new HashMap<>();
		params.put("content", content);
		params.put("threshold", 80);
		params.put("timeout", timeout);
		DriverUtils.getAppiumDriver().executeScript("mobile:text:select", params);
	}
	
	public boolean verifyVisualText(String text) {
		Map<String, Object> params = new HashMap<>();
		params.put("content", text);
		params.put("threshold", 80);
		params.put("timeout", 10);
		String res = (String)DriverUtils.getAppiumDriver().executeScript("mobile:checkpoint:text", params);
		return Boolean.parseBoolean(res);
	}
	
	public boolean verifyVisualText(String text, int timeout) {
		Map<String, Object> params = new HashMap<>();
		params.put("content", text);
		params.put("threshold", 80);
		params.put("timeout", timeout);
		String res = (String)DriverUtils.getAppiumDriver().executeScript("mobile:checkpoint:text", params);
		return Boolean.parseBoolean(res);
	}
	
	public void clickWithVisualImage(String img, int timeout) {
		Map<String, Object> params = new HashMap<>();
		params.put("content", img);
		params.put("match", "bounded");
		params.put("timeout", timeout);
		DriverUtils.getAppiumDriver().executeScript("mobile:image:select", params);
	}
	
	public void verifyVisualImage(String img, int timeout) {
		Map<String, Object> params = new HashMap<>();
		params.put("content", img);
		params.put("match", "bounded");
		params.put("timeout", timeout);
		DriverUtils.getAppiumDriver().executeScript("mobile:checkpoint:image", params);
	}
}
