package com.qa.flightbooking.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.flightbooking.utils.ElementUtil;
import com.itextpdf.text.log.SysoCounter;
import com.qa.flightbooking.constants.AppConstants;

import io.qameta.allure.Step;

public class HomePage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 1. Page Locators
	private By div_FeatureFlights = By.xpath("//div[@class='col-lg-4 responsive-column']");
	private By radioButton_OneWay = By.xpath("//input[@id='one-way']");
	private By radioButton_RoundTrip = By.xpath("//input[@id='round-trip']");
	private By dropdown_FromCity = By.xpath("//div[@class='input-items from_flights']//b[@role='presentation']");
	private By enter_City = By.xpath("//span[@class='select2-search select2-search--dropdown']//input[@class='select2-search__field']");
	private By dropdown_DestinationCity = By.xpath("//div[@class='input-items flights_arrival to_flights']//span[@class='select2-selection__arrow']");
	private By enter_DepartureDate = By.xpath("//input[@id='departure'][1]");
	private By button_Search = By.xpath("//button[@id='flights-search']");
	
	// Page Methods

	/**
	 * Print home page title
	 * @return String title
	 */
	public String getHomePageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.SHORT_TIME_OUT);
		System.out.println("Home Page title is: " + title);
		return title;
	}

	/**
	 * Get number of tiles available on Home Page.
	 * 
	 * @return
	 */
	public int getLinksNumFromHomePage() {
		int tileNum = eleUtil.getElementsCount(By.xpath("//a"));
		System.out.println("Total " + tileNum + " links available on Home Page.");
		return tileNum;
	}

	/**
	 * Provide list of all the tiles available on Home Page.
	 * @return 
	 * 
	 * @return
	 */
	public List<String> getAllLinksAvailableOnHomePage() {
		List<String> list = new ArrayList<String>();
		List<String> eleList = eleUtil.getElementsTextList(By.xpath("//a"));
		for (String linkName : eleList) {
			if (!linkName.isEmpty()) {
				//System.out.println((eleList.indexOf(linkName) + 1) + " --> " + linkName);
				list.add(linkName);
			}
		}
		return list;
	}

	/**
	 * Click all clickable links available on Home Page.
	 * 
	 * @return
	 */
	public void clickAllClickableLinksAvailableOnHomePage() {
		List<String> eleList = eleUtil.getElementsTextList(By.xpath("//a"));
		for (String linkName : eleList) {
			if (!linkName.isEmpty()) {
				if (eleUtil.ifElementClickable(By.linkText(linkName))) {
					eleUtil.getElement(By.linkText(linkName)).click();
					System.out.println("Link " + linkName + " clicked.");
					eleUtil.isPageLoaded(AppConstants.LONG_TIME_OUT);
					String title = eleUtil.waitForTitleIs(AppConstants.SHORT_TIME_OUT);
					System.out.println("Title of Page is :" + title);
					// eleUtil.goToBackPage();
				} else {
					System.out.println("Link " + linkName + " is not clickable.");
				}
			}
		}
	}

	/**
	 * This will provide list of all featured flights available on Home Page.
	 * 
	 * @return
	 */
	public List<String> getListOfAllFeaturedFlights() {

		String fromCity = null;
		String toCity = null;
		String price = null;
		List<String> allFeaturedFlights = new ArrayList<String>();

		List<WebElement> eleFeatureFlights = eleUtil.getElements(div_FeatureFlights);
		for (int i = 1; i <= eleFeatureFlights.size(); i++) {
			// Get Price
			String xpathPrice = "//div[@class='col-lg-4 responsive-column'][" + i
					+ "]//span[@class='price__num flex-grow-1 text-center d-block']";
			price = eleUtil.getElement(By.xpath(xpathPrice)).getText();

			// Get From and to city name
			String xpathCities = "//div[@class='col-lg-4 responsive-column'][" + i + "]//span[@class='h6']";
			List<WebElement> citiesName = eleUtil.getElements(By.xpath(xpathCities));
			for (int j = 1; j <= citiesName.size(); j++) {
				String xpathCity = "//div[@class='col-lg-4 responsive-column'][" + i + "]//span[@class='h6'][" + j
						+ "]";
				String city = eleUtil.getElement(By.xpath(xpathCity)).getText();
				if (j == 1) {
					fromCity = city;
				}
				if (j == 2) {
					toCity = city;
				}
			}
			allFeaturedFlights.add(fromCity + " TO " + toCity + " " + price);
			//System.out.println(fromCity + " TO " + toCity + " " + price);
		}
		return allFeaturedFlights;

	}
	
	public void oneWayFlightSearch(String fromCity, String destinationCity, String date) {
		eleUtil.isPageLoaded(AppConstants.LONG_TIME_OUT);
		waitForProcessingElement();
		eleUtil.waitForElementReadyAndClick(radioButton_OneWay);
		eleUtil.waitForElementReadyAndClick(dropdown_FromCity);
		eleUtil.waitForElementReadySendKeysAndClickTab(enter_City, fromCity);
		eleUtil.waitForElementReadyAndClick(dropdown_DestinationCity);
		eleUtil.waitForElementReadySendKeysAndClickTab(enter_City, destinationCity);
		eleUtil.waitForElementReadySendKeysAndClickTab(enter_DepartureDate, date);
		eleUtil.waitForElementReadyAndClick(button_Search);
		eleUtil.waitPlease(10000);
	}

	/**
	 * Pass Name of link on home page to click it.
	 * 
	 * @param Link Text
	 */
	public void clickLinkOnPage(String linkText) {
		eleUtil.waitForElementReadyAndClick(By.linkText(linkText));
	}

	/**
	 * Pass Name of link on home page to click it.
	 * 
	 * @param nameOfTile
	 */
	public void clickLinksUsingText(String linkText) {
		eleUtil.waitForLinkReadyAndClickByText(linkText);
	}

	public void waitForProcessingElement() {
		eleUtil.waitForProcessingElementToDisappear();
	}
}
