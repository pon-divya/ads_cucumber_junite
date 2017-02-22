// This page defines properties that will be used by all page objects

package com.ads.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ads.steps.BrowserDI;
import com.ads.utility.ConfigProperty;


import static org.hamcrest.MatcherAssert.assertThat;

public class adsWebPage {
	public BrowserDI browser;
	// public HashMap<String, String> map = new HashMap<String, String>();

	Actions action;

	static List<String> accountRefIds = new ArrayList<String>();

	// This defines the wait that needs to be used for explicit wait
	protected WebDriverWait browserWait;
	public Map<String, String> map = new HashMap<String, String>();

	public adsWebPage(BrowserDI driver) {
		this.browser = driver;
		// getEaseManUrl("https://easeatla2-core-ol.aws-code-dqa.cb4good.com/EZMan-App/EZMan.html");

		browser.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		browser.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		// browser.manage().window().maximize();
		PageFactory.initElements(browser, this);
		browserWait = new WebDriverWait(browser, 30);

		setMap();

	}

	

	// Populating instance variable through static access of a static variable
	// of a Static attribute.
	public void setMap() {
		this.map = BrowserDI.map;
	}

}
