package com.ads.steps;

import org.junit.Assert;

import com.ads.pages.wallSummaryPage;
import com.ads.utility.ConfigProperty;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class wallSummarysteps extends adsWebStep {

	ConfigProperty property = new ConfigProperty();
	public wallSummarysteps(BrowserDI driver) {
		super(driver);
		wallPage = new wallSummaryPage(browser);
	}

	
	
}