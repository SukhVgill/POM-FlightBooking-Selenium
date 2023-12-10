package com.qa.flightbooking.tests;

import org.testng.annotations.Test;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;

@Epic("EPIC 002: Flight Search Tests")
@Story("US 001: Search for flight from home page")
public class SearchFlightsTest extends BaseTest{

	@Test(priority = 1, enabled = true)
	public void searchOneWayFlights() {
		homePage.oneWayFlightSearch("Delhi", "Dubai", "19-12-2023");
	}
}
