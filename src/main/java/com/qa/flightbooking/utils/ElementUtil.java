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
import com.qa.flightbooking.listeners.ExtentReportListener;
import com.qa.flightbooking.utils.JavaScriptUtil;

import io.qameta.allure.Step;

public class ElementUtil {

	private WebDriver driver;
	private JavaScriptUtil jsUtil;
	private ExtentReportListener extReport;

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
	public String waitForTitleIs(int timeOut) {
		if(isPageLoaded(timeOut)) {
			String title = driver.getTitle();
			return title;
		}
		else {
			String errorMsg = "ERROR: Page Load taking longer.";
			System.out.println(errorMsg);
			return errorMsg;
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
	 * Wait until provided element is ready to be clicked.
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
	
	/**
	 * Wait until provided link is ready to be clicked.
	 * @param locator
	 */
	public void waitForLinkReadyAndClickByText(String linkText) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.MEDIUM_TIME_OUT));
		try {
			WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(linkText)));
			ele.click();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Link with text " + linkText + " is not present...");
		}
	}
	
	/**
	 * return number of similar elements available on page.
	 * @param locator
	 * @return
	 */
	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}

	/**
	 * List of all the elements available on page.
	 * @param locator
	 * @return
	 */
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}
	
	/**
	 * Get list as text for elements.
	 * @param locator
	 * @return
	 */
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
	
	/**
	 * Check if page loaded properly.
	 * @param timeOut
	 * @return boolean
	 */
	public boolean isPageLoaded(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		String flag = wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete'")).toString();
		return Boolean.parseBoolean(flag);
	}
	
	/**
	 * Navigate to back page.
	 */
	public void goToBackPage() {
		if(isPageLoaded(AppConstants.LONG_TIME_OUT)) {
			driver.navigate().back();
		}
	}

	/**
	 * Wait for element to disappear that load the page.
	 */
	public void waitForProcessingElementToDisappear() {
		waitForElementToDisappear(By.xpath("//div[@class='rotatingDiv']"));
	}
	
	/**
	 * wait until clickable element available.
	 * @param locator
	 * @return
	 */
	public WebElement webDriverWaitForElementClickable(By locator) {
		WebElement ele = null;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.MEDIUM_TIME_OUT));
		try {
			ele = wait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(locator + " : Element is not present...");
		}
		return ele;
	}
	
	/**
	 * wait until provided element disappear.
	 * @param locator
	 * @return
	 */
	public boolean waitForElementToDisappear(By locator) {
		boolean eleDisappeared = false;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(AppConstants.MEDIUM_TIME_OUT));
		try {
			eleDisappeared = wait.until(ExpectedConditions.invisibilityOf(driver.findElement(locator)));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(locator + "Wait For Element To Disappear - False");
		}
		return eleDisappeared;
	}
	
	
	//***************************PrintForReports*************************//

	

}
