package com.qa.flightbooking.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.flightbooking.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC 001: Tests for Home Page")
@Story("US 001: Search for flight from home page")
public class HomePageTest extends BaseTest{
	
	@Description("login page title test......")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void homePageTitleTest() {
		String actTitle = homePage.getHomePageTitle();
		homePage.getTilesNumFromHomePage();
		homePage.printNameAndClickTilesFromHomePage();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);

	}
}
