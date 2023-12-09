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
	
	@Description("Home page title.")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 1)
	public void homePageTitleTest() {
		homePage.waitForProcessingElement();
		String actTitle = homePage.getHomePageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Description("Available links on home page.")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void linksOnHomepageTest() {
		homePage.getLinksNumFromHomePage();
		homePage.printAllLinksAvailableOnHomePage();
	}
	
	@Description("Provide list of all feature flights.")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 3)
	public void listOfAllFeatureFlights() {
		homePage.getLinksNumFromHomePage();
		homePage.printAllLinksAvailableOnHomePage();
	}
	
	
}
