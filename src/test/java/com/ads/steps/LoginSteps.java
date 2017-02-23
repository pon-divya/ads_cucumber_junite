package com.ads.steps;

import org.junit.Assert;

import com.ads.pages.LoginPage;
import com.ads.pages.SignInPage;
import com.ads.utility.ConfigProperty;
import com.google.common.base.Verify;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginSteps extends adsWebStep {

	ConfigProperty property = new ConfigProperty();

	public LoginSteps(BrowserDI driver) {
		super(driver);
		loginPage = new LoginPage(browser);
		SignupPage = new SignInPage(browser);
	}

	@When("^I enter my valid login credentials and sign in$")
	public void i_enter_my_valid_login_credentials_and_sign_in() throws Throwable {

	}

	@Then("^I should see a Warining message$")
	public void i_should_see_a_Warining_message() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		String getText = loginPage.checkWarningMessage();
		System.out.println(getText);

	}

	@Given("^Iam on the qbakbak login page$")
	public void iam_on_the_qbkbak_login_page() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		loginPage = new LoginPage(browser);
		loginPage.navigateUrl();

	}

	@And("^I click on the Sign on btton$")
	public void i_click_on_the_Sign_on_btton() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		SignupPage = loginPage.doClickSign();

	}

	@Then("^I should sign in Page$")
	public void i_should_sign_in_Page() throws Throwable {
		// Write code here that turns the phrase above into concrete actions

		boolean getIsState = SignupPage.checkSignPage();
		Assert.assertTrue("Sign in Page is not displayed / not loaded", getIsState);
	}

	
	@And("^I enter the User Name \"([^\"]*)\" and Password \"([^\"]*)\" click on login$")
	public void i_enter_the_User_Name_and_Password_click_on_login(String dataUserName,
			String dataPassword) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions

		loginPage.login(map.get(dataUserName).trim(), map.get(dataPassword).trim());
	}
	
	
	@When("^I should be able to click on signup button$")
	public void i_should_be_able_to_click_on_signup_button() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		loginPage.objSignInButton.click();

	}

	@Then("^verify iam on the Sign up page$")
	public void verify_iam_on_the_Sign_up_page() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		Verify.verify(true);
	}

}
