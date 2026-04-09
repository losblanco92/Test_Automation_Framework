package com.ui.pages;

import static com.constants.Env.*;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browser;
import com.utility.BrowserUtility;
import com.utility.LoggerUtility;
import com.utility.PropertiesUtil;

import static com.utility.JSONUtility.*;

public final class HomePage extends BrowserUtility {
	Logger logger= LoggerUtility.getLogger(this.getClass());
	public HomePage(Browser browserName, boolean isHeadLess) {
		super(browserName, isHeadLess);
		goToWebsite(readJson(QA).getUrl());
		//goToWebsite(PropertiesUtil.readProperty(QA, "URL"));
		maximizeWindow();

	}

	public HomePage(WebDriver driver) {
		super(driver);
		goToWebsite(readJson(QA).getUrl());
		//goToWebsite(PropertiesUtil.readProperty(QA, "URL"));
		maximizeWindow();
	}

	private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(),'Sign in')]");

	public LoginPage goToLoginPage() {
     logger.info("Clicks the sign in link");
		clickOn(SIGN_IN_LINK_LOCATOR);
		
		return new LoginPage (getDriver());

	}
	
	public void quit() {
		
		tearDown();
	}

}
