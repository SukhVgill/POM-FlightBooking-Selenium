package com.qa.flightbooking.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.flightbooking.factory.DriverFactory;
import com.qa.flightbooking.pages.HomePage;

public class BaseTest {
	
		protected WebDriver driver;
		protected Properties prop;
		protected HomePage homePage;
		
		
		DriverFactory df;
		protected SoftAssert softAssert;
		
		@BeforeTest
		public void setup() {
			df = new DriverFactory();
			prop = df.initProp();
			
			driver = df.initDriver(prop);
			homePage = new HomePage(driver);
			softAssert = new SoftAssert();
		}
		
		@AfterTest
		public void tearDown() {
			driver.quit();
		}
}
