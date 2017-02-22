package com.ads.steps;

import org.junit.Assert;

import com.ads.pages.LoginPage;
import com.ads.utility.ConfigProperty;
import com.google.common.base.Verify;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginHappyPath extends adsWebStep {

	private static LoginPage SignupPage = null;
	ConfigProperty property = new ConfigProperty();

	public LoginHappyPath(BrowserDI driver) {
		super(driver);
		 SignupPage = new LoginPage(browser);
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
		loginPage.navigateUrl();
		

		
	}

	@Then("^Verify i am on Wall page$")
	public void verify_i_am_on_Wall_page() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

		 @When("^I should be able to click on signup button$")
		 public void i_should_be_able_to_click_on_signup_button() throws
		 Throwable {
		//Write code here that turns the phrase above into concrete actions
		 loginPage.objSignInButton.click();
		 
        }	

		 @Then("^verify iam on the Sign up page$")
		public void verify_iam_on_the_Sign_up_page() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
			 Verify.verify(true);
		// throw new PendingException();
	 }

	}
