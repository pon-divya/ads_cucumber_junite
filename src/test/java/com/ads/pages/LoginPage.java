package com.ads.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.ads.pages.HomePage;
import com.ads.steps.BrowserDI;
import com.ads.utility.ConfigProperty;
import com.ads.utility.lib;

import net.sourceforge.htmlunit.corejs.javascript.ast.ReturnStatement;

//import com.capitalone.steps.ReportingOptions;

import java.net.URL;

public class LoginPage extends adsWebPage {

	@FindBy(how = How.NAME, using = "txtuemail_txtfuname_uphonenumber")
	public WebElement objUserName;

	@FindBy(how = How.NAME, using = "txtupass")
	public WebElement objPassword;

	@FindBy(how = How.NAME, using = "btn-login")
	public WebElement objloginButton;

	@FindBy(how = How.NAME, using = "btn-signup")
	public WebElement objSignInButton;

	@FindBy(how = How.CSS, using = "div.alert.alert-success")
	public WebElement objWarningMessage;

	@FindBy(how = How.CSS, using = "txtfname")
	public WebElement objFirstName;

	/** The workingdirectory. */
	static String workingdirectory = System.getProperty("user.dir");
	protected static String defaultLogFile = workingdirectory + "/src/test/attest/";

	// #########################################Updated for prod end
	// here##################

	public int setShort_Time = 2000;
	ConfigProperty property = new ConfigProperty();

	public LoginPage(BrowserDI driver) {
		super(driver);
	}

	// public LoginPage(BrowserDI driver) {
	// super(driver);
	// page = driver.Attest;
	// PageHeader header = new PageHeader(AttestDriver Attes);
	// }

	ConfigProperty Util = new ConfigProperty();
	int flg;

	public void navigateUrl() {

		try {

			boolean Enviroment = System.getProperty("environment") != null;
			String setEnviroment = System.getProperty("environment");

			if (Enviroment == false) {
				setEnviroment = map.get("environment");

			}
			boolean blnDevices = System.getProperty("execPlatform") != null;
			String setDevices = System.getProperty("execPlatform");

			if (blnDevices == false) {
				setDevices = map.get("execution_mode");
			}

			if (setEnviroment.equalsIgnoreCase("qa")) {
				System.out.println(map.get("qaURL"));

				browser.get(map.get("qaURL"));

			} else if (setEnviroment.equalsIgnoreCase("regression")) {

				browser.get(map.get("regressionURL"));

			} else if (setEnviroment.equalsIgnoreCase("dev")) {

				browser.get(map.get("dev"));

			} else if (setEnviroment.equalsIgnoreCase("prod")) {

				System.out.println(map.get("prodURL"));

				browser.get(map.get("prodURL"));

			}

			browserWait.until(ExpectedConditions.visibilityOf(objSignInButton));
			browserWait.until(ExpectedConditions.visibilityOf(objloginButton));

		} catch (

		TimeoutException e) {
			System.out.println("##############log Start here############");
			System.out.println(e);
			System.out.println("##############log End here############");

		} catch (NoSuchElementException e) {
			System.out.println("##############log Start here############");
			System.out.println(e);
			System.out.println("##############log End here############");
		}

	}

	public HomePage login(String userName, String password) throws InterruptedException {

		try {

			flg = ConfigProperty.ReloadCount();
			while (flg != 0) {
				if ((this.objUserName.isDisplayed())) {
					break;
				}
				flg--;
			}

			objUserName.click();
			objUserName.sendKeys(userName);
			objPassword.clear();
			objPassword.sendKeys(password);
			objloginButton.click();

		} catch (TimeoutException e) {
			System.out.println("##############log Start here############");
			System.out.println(e);
			System.out.println("##############log End here############");

		} catch (NoSuchElementException e) {
			System.out.println("##############log Start here############");
			System.out.println(e);
			System.out.println("##############log End here############");
		}
		return new HomePage(browser);
	}

	public String checkWarningMessage() {
		// TODO Auto-generated method stub

		return objWarningMessage.getText();
	}

	public SignInPage doClickSign() {
		// TODO Auto-generated method stub
		objSignInButton.click();
		return new SignInPage(browser);

	}

}
