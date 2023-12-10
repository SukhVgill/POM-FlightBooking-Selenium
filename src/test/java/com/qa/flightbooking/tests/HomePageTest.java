package com.qa.flightbooking.tests;
 
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.flightbooking.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC 001: Tests for Home Page")
@Story("US 001: Links and Flights info available on home page")
public class HomePageTest extends BaseTest{
	
	@Description("Home page title.")
	@Test(priority = 1, enabled = true)
	public void homePageTitleTest() {
		homePage.waitForProcessingElement();
		String actTitle = homePage.getHomePageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	@Description("Available links on home page.")
	@Test(priority = 2, enabled = true)
	public void linksOnHomepageTest() {
		homePage.getLinksNumFromHomePage();
		List<String> list = homePage.getAllLinksAvailableOnHomePage();
		System.out.println("---------List Of Visible Links on Home Page--------");
		for(String s : list) {
			System.out.println((list.indexOf(s)+1) + " --> " + s);
		}
		System.out.println("-------------------------------------");
	}
	
	@Description("Provide list of all feature flights.")
	@Test(priority = 3, enabled = true)
	public void listOfAllFeatureFlights() {
		List<String> list = homePage.getListOfAllFeaturedFlights();
		System.out.println("---------All Featured Flights--------");
		for(String s : list) {
			System.out.println((list.indexOf(s)+1) + " --> " + s);
		}
		System.out.println("-------------------------------------");
	}
	
	
}
