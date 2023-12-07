package com.qa.flightbooking.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.flightbooking.constants.AppConstants;
import com.qa.flightbooking.factory.DriverFactory;
import com.qa.flightbooking.utils.JavaScriptUtil;

import io.qameta.allure.Step;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
	}

	/**
	 * Return title of page, after page is fully loaded.
	 * 
	 * @param titleValue
	 * @param timeOut
	 * @return String title of page
	 */
	public String waitForTitleIs(String titleValue, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			if (wait.until(ExpectedConditions.titleIs(titleValue))) {
				return driver.getTitle();
			} else {
				System.out.println(titleValue + " title value is not present...");
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(titleValue + " title value is not present...");
			return null;
		}
	}

	/**
	 * Return Web Element
	 * 
	 * @param locator
	 * @return WebElement element
	 */
	public WebElement getElement(By locator) {
		WebElement element = driver.findElement(locator);
		if (Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(element);
		}
		return element;
	}

	/**
	 * 
	 * @param locator
	 */
	public void waitForElementReadyAndClick(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.MEDIUM_TIME_OUT));
		try {
			WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(locator));
			ele.click();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(locator + " : Element is not present...");
		}
	}
	
	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
	public boolean isPageLoaded(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		String flag = wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete'")).toString();
		return Boolean.parseBoolean(flag);
	}
	
	public void goToBackPage() {
		if(isPageLoaded(AppConstants.LONG_TIME_OUT)) {
			driver.navigate().back();
		}
	}

	public List<String> getElementsTextList(By locator) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();// pc=0
		for (WebElement e : eleList) {
			String text = e.getText();
			if (!text.isEmpty()) {
				eleTextList.add(text);
			}
		}
		return eleTextList;
	}

}
