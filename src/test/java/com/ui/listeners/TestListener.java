package com.ui.listeners;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import static com.aventstack.extentreports.Status.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.ui.tests.TestBase;

import static com.utility.ExtentReporterUtility.*;

import com.utility.BrowserUtility;
import com.utility.LoggerUtility;

public class TestListener implements ITestListener {
	Logger logger = LoggerUtility.getLogger(this.getClass());
	ExtentSparkReporter extentSparkReporter;
	ExtentReports extentReports;
	ExtentTest extentTest;

	public void onTestStart(ITestResult result) {
		logger.info(result.getMethod().getMethodName());
		logger.info(result.getMethod().getDescription());
		logger.info(Arrays.toString(result.getMethod().getGroups()));
		createExtentTest(result.getMethod().getMethodName());

	}

	public void onTestSuccess(ITestResult result) {
		logger.info(result.getMethod().getMethodName() + " " + " PASSED");
		getTest().log(PASS, result.getMethod().getMethodName() + " " + " PASSED");
	}

	public void onTestFailure(ITestResult result) {
		logger.error(result.getMethod().getMethodName() + " " + " FAILED");
		logger.error(result.getThrowable().getMessage());
		getTest().log(FAIL, result.getMethod().getMethodName() + " " + " FAILED");
		getTest().log(FAIL, result.getThrowable().getMessage());

		Object testClass = result.getInstance();
		BrowserUtility browserUtility = ((TestBase) testClass).getInstance();
		String screenShotPath = browserUtility.takeScreenShot(result.getMethod().getMethodName());

		logger.info("Adding ScreenShot of Failed Test to the report");
		getTest().addScreenCaptureFromPath(screenShotPath);

	}

	public void onTestSkipped(ITestResult result) {
		logger.warn(result.getMethod().getMethodName() + " " + " SKIPPED");
		getTest().log(SKIP, result.getMethod().getMethodName() + " " + " SKIPPED");

	}

	public void onStart(ITestContext context) {
		logger.info("TEST SUITE STARTED");
		setUpSparkReporter("report.html");
	}

	public void onFinish(ITestContext context) {
		logger.info("TEST SUITE COMPLETED");
		flushReport();
	}

}
