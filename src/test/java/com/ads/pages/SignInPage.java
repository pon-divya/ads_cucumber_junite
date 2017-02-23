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

public class SignInPage extends adsWebPage {

	@FindBy(how = How.NAME, using = "txtfname")
	public WebElement objFirstName;

	public int setShort_Time = 2000;
	ConfigProperty property = new ConfigProperty();

	public SignInPage(BrowserDI driver) {
		super(driver);
	}

	ConfigProperty Util = new ConfigProperty();
	int flg;

	public boolean checkSignPage() {
		// TODO Auto-generated method stub
		boolean getState = objFirstName.isDisplayed();
		return getState;
	}

}
