package com.ads.steps;

import org.junit.Assert;

import com.ads.pages.HomePage;
import com.ads.pages.LoginPage;
import com.ads.utility.ConfigProperty;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class HomeSteps extends adsWebStep {
	private static HomePage homeIndexPage = null;
	ConfigProperty property = new ConfigProperty();
	public HomeSteps(BrowserDI driver) {
		super(driver);
		homeIndexPage = new HomePage(browser);
	}


	@And("^I am on HomePage$")
	public void i_am_on_HomePage() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		homeIndexPage.WaitForHomePage();
	}
	
	@Then("^Verify i am on Wall page$")
	public void verify_i_am_on_Wall_page() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		homeIndexPage.isCheckHome();
	}
	
}