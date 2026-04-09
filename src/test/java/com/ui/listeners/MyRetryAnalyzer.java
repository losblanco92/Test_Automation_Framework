package com.ui.listeners;

import java.util.Properties;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.utility.JSONUtility;

import static com.constants.Env.*;
import static com.utility.PropertiesUtil.*;

public class MyRetryAnalyzer implements IRetryAnalyzer {

	// private static final int MAX_NUMBER_OF_ATTEMPTS =
	// Integer.parseInt(readProperty(QA, "MAX_NUMBER_OF_ATTEMPTS")) ;
	private static final int MAX_NUMBER_OF_ATTEMPTS 
	= JSONUtility.readJson(QA).getMAX_NUMBER_OF_ATTEMPTS();
	private static int current_attempt = 1;

	@Override
	public boolean retry(ITestResult result) {

		if (current_attempt <= MAX_NUMBER_OF_ATTEMPTS) {
			current_attempt++;

			return true;
		}

		return false;
	}

}
