package com.ui.tests;

import static com.constants.Browser.*;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LambdaTestUtiliy;
import com.utility.LoggerUtility;

public class TestBase {

	protected HomePage homePage;
	Logger logger = LoggerUtility.getLogger(this.getClass());
	private boolean isLambdaTest;

	@Parameters({ "browser", "isLambdaTest", "isHeadLess" })
	@BeforeMethod(description = "Load the homepage of the website")
	public void setUp(@Optional("chrome") String browser, @Optional("false") boolean isLambdaTest,
			@Optional("true") boolean isHeadLess, ITestResult result) {

		WebDriver lamdaDriver;

		this.isLambdaTest = isLambdaTest;
		if (isLambdaTest) {

			lamdaDriver = LambdaTestUtiliy.initializeLambdaTestSession(browser, result.getMethod().getMethodName());
			homePage = new HomePage(lamdaDriver);
		}

		else {
			logger.info("Load the homepage of the website");
			homePage = new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadLess);
		}

	}

	public BrowserUtility getInstance() {

		return homePage;

	}

	public void tearDown() {

		if (isLambdaTest) {
			LambdaTestUtiliy.quitSession();
		}

		homePage.quit();
	}

}
