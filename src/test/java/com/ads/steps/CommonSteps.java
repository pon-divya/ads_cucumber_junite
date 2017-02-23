package com.ads.steps;

import org.junit.Assert;
import org.openqa.selenium.TimeoutException;

import com.ads.pages.HomePage;
import com.ads.pages.LoginPage;
import com.ads.utility.lib;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

// TODO: Auto-generated Javadoc
/**
 * The Class CommonSteps.
 */
public class CommonSteps extends adsWebStep {
	String setEnviroment;
	String setBrowsername;
	String setBrowserVersione;
	String setOS;

	/**
	 * Instantiates a new common steps.
	 *
	 * @param driver
	 *            the driver
	 */
	public CommonSteps(BrowserDI driver) {
		super(driver);
		homeIndexPage = new HomePage(browser);
	}

	/**
	 * I_am_logged_into_the_ eas e_ web_ application_with_ user_name_and_
	 * password.
	 *
	 * @param dataUserName
	 *            the data user name
	 * @param dataPassword
	 *            the data password
	 * @throws Throwable
	 *             the throwable
	 */

	@Given("^I am logged into the qbak Web Application with User name \"([^\"]*)\" and Password \"([^\"]*)\"$")
	public void i_am_logged_into_the_qbak_Web_Application_with_User_name_and_Password(String dataUserName,
			String dataPassword) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String scenario = "Login";
		String testSuite = "Login";
		try {
			loginPage = new LoginPage(browser);

			loginPage.navigateUrl();

			homeIndexPage = loginPage.login(map.get(dataUserName).trim(), map.get(dataPassword).trim());
		}

		catch (RuntimeException run) {
			handleError(run, scenario, testSuite);
		}

	}


}
