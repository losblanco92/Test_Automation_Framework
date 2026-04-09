package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.constants.Browser;

public abstract class BrowserUtility {
	Logger logger = LoggerUtility.getLogger(this.getClass());

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

	public BrowserUtility(WebDriver driver) {
		this.driver.set(driver);
	}

	public BrowserUtility(Browser browserName, boolean isHeadLess) {
		logger.info("Launching Browser " + browserName);
		if (browserName == Browser.CHROME) {
			if (isHeadLess) {
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless=old");
				options.addArguments("disable-gpu");
				driver.set(new ChromeDriver(options));
			}

			else {
				driver.set(new ChromeDriver());
			}
		}

		else if (browserName == Browser.EDGE) {

			if (isHeadLess) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless=old");
				options.addArguments("--window-size=1920,1080");
				driver.set(new EdgeDriver(options));
			}

			else {
				driver.set(new EdgeDriver());
			}

		}

		else if (browserName == Browser.FIREFOX) {
			logger.info("Launching Browser " + browserName);
			if (isHeadLess) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless=old");
				options.addArguments("--window-size=1920,1080");
				driver.set(new FirefoxDriver(options));
			}

			else {
				driver.set(new FirefoxDriver());
			}

		}
	}

	public BrowserUtility(Browser browserName) {
		logger.info("Launching Browser " + browserName);
		if (browserName == Browser.CHROME) {

			driver.set(new ChromeDriver());
		}

		else if (browserName == Browser.EDGE) {
			logger.info("Launching Browser " + browserName);
			driver.set(new EdgeDriver());
		}

		else if (browserName == Browser.FIREFOX) {

			driver.set(new FirefoxDriver());
		}

	}

	public WebDriver getDriver() {
		return driver.get();
	}

	public void goToWebsite(String url) {
		logger.info("Launching the website " + url);
		driver.get().get(url);
	}

	public void maximizeWindow() {
		logger.info("Maximizing the page");
		driver.get().manage().window().maximize();

	}

	public void clickOn(By locator) {

		logger.info("Finding the element " + locator);
		WebElement element = driver.get().findElement(locator);

		logger.info("Element found, performing click");
		element.click();
	}

	public void enterText(By locator, String textToEnter) {
		logger.info("Finding the element " + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element found. Entering the text " + textToEnter);
		element.sendKeys(textToEnter);
	}

	public String getVisibletext(By locator) {
		logger.info("Finding the element " + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Element found. Displaying the visible text " + element.getText());
		return element.getText();
	}
	
	public void tearDown() {
		
		driver.get().quit();
	}
	

	public String takeScreenShot(String name) {
		TakesScreenshot screenshot = (TakesScreenshot) driver.get();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String timeStamp = format.format(date);
		File screenShotData = screenshot.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "\\screenshots\\" + name + "-" + timeStamp + ".png";
		File screnShotFile = new File(path);

		try {
			FileUtils.copyFile(screenShotData, screnShotFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;

	}

}
