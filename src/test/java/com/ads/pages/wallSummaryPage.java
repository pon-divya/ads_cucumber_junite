package com.ads.pages;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.ads.steps.BrowserDI;
import com.ads.utility.ConfigProperty;
import com.ads.utility.Logger;
import com.ads.utility.lib;

public class wallSummaryPage extends adsWebPage {
	ConfigProperty getData = new ConfigProperty();


	// ui element that include all accounts on the page
	@FindBy(how = How.NAME, using = "post_feed")
	public static WebElement objPost_feed;

	// title text for your accounts
	@FindBy(how = How.ID, using = "yourAccounts")
	public WebElement yourAccountsTitle;

    
	ConfigProperty Util = new ConfigProperty();
	int flg;
	

	static {
		
	}

	public wallSummaryPage(BrowserDI driver) {
		super(driver);
		

		//accountList = accountBlock.findElements(By.tagName("li"));
	}

	public boolean checkWallPage() {
		// TODO Auto-generated method stub
		
		boolean isState=objPost_feed.isDisplayed();
		return isState;
	}

	

	

}