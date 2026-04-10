package com.ui.tests;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pojo.User;

@Listeners({com.ui.listeners.TestListener.class})
public class LoginTest extends TestBase {

	

//	@Test(description = "Verifies with valid credentials user is able to login to the application", groups = { "e2e",
//			"sanity" }, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginCredentailsDataProvider")
//	public void loginTest(User user) {
//        
//		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmail(), user.getPassword()).getUserName(),
//				"Neeraj Joshi");
//
//	}
//
//	@Test(description = "Verifies with valid credentials user is able to login to the application", groups = { "e2e",
//			"sanity" }, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginCredentailsDataProviderCSV")
//	public void loginTestWithCSV(User user) {
//
//		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmail(), user.getPassword()).getUserName(),
//				"Neeraj Joshi");
//
//	}

	@Test(description = "Verifies with valid credentials user is able to login to the application", groups = { "e2e",
			"sanity" }, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginCredentailsDataProviderXLSX", retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
	public void loginTestWithExel(User user) {
		
		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmail(), user.getPassword()).getUserName(),
				"Neeraj Joshi1");
		

	}
}
