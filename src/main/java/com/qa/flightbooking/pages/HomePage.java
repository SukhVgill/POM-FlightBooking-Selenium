package com.qa.flightbooking.pages;

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
	private By elementTile = By.xpath("//h5[text() = 'Elements']/..");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");

	// Page Methods

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
	 * 
	 * @return
	 */
	public void printAllLinksAvailableOnHomePage() {
		List<String> eleList = eleUtil.getElementsTextList(By.xpath("//a"));
		for (String linkName : eleList) {
			if (!linkName.isEmpty()) {
				System.out.println((eleList.indexOf(linkName)+1)+ " --> " + linkName);
			}
		}
	}

	/**
	 * Provide list of all the tiles available on Home Page.
	 * 
	 * @return
	 */
	public void clickAllClickableLinksAvailableOnHomePage() {
		List<String> eleList = eleUtil.getElementsTextList(By.xpath("//a"));
		System.out.println("List of Links available on Home Page : " + eleList);

		for (String linkName : eleList) {
			if (!linkName.isEmpty()) {
				clickLinksUsingText(linkName.trim());
				eleUtil.goToBackPage();
			}
		}
	}

	/**
	 * Pass Name of link on home page to click it.
	 * 
	 * @param nameOfTile
	 */
	public void clickTilesFromHomePage(String nameOfTile) {
		String xpath = "//h5[text() = '" + nameOfTile + "']/..";
		By tileEle = By.xpath(xpath);
		eleUtil.waitForElementReadyAndClick(tileEle);
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
