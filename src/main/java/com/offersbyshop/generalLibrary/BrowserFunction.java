package com.offersbyshop.generalLibrary;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFunction {
	public static String chromePath = System.getProperty("user.dir")+"\\chromedriver.exe";
	public static String iePath =System.getProperty("user.dir")+ "\\IEDriverServer.exe";
	public static WebDriver driver = null;
	private static Logger logger = Logger.getLogger(BrowserFunction.class);
	
	public static WebDriver invokeBrowser(String browser) {

		if (browser.equalsIgnoreCase("Chrome")) {
			logger.info("Chrome");
			System.getProperty("webdriver.chrome.driver", chromePath);
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("ie")) {
			logger.info("ie");
			System.getProperty("webdriver.ie.driver", iePath);
			driver = new InternetExplorerDriver();
		} else {
			logger.info("Default browser FF inbvoked ..");
			driver = new FirefoxDriver();
		}
		return driver;
	}
}
