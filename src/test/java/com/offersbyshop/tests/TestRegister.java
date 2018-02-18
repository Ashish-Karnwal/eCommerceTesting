package com.offersbyshop.tests;

import java.io.File;
import java.io.FileInputStream;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.offersbyshop.generalLibrary.BrowserFunction;
import com.offersbyshop.pages.Checkout;
import com.offersbyshop.pages.Home;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


public class TestRegister {
	private static Logger logger = Logger.getLogger(TestRegister.class);
	public static WebDriver driver = null;
	public static String filepath = System.getProperty("user.dir")+"\\TestData.xlsx";
	public static String aut= "http://www.offersbyshop.com/";
	public static ExtentReports er;
	public static ExtentTest testRegister,testCompare;
	Home home;
	Checkout checkout;
	

	@BeforeSuite
	public void beforeSuite() {
		er = new ExtentReports(System.getProperty("user.dir")+"\\extentReport\\testreport.html", true);
		er.addSystemInfo("environment", "QA").addSystemInfo("Owner", "DucatTestTeam");
		er.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
	}
	
	@AfterSuite
	public void aftersuite() {
		// close and write status to report
		er.flush();
		er.close();
	}
	
	@Parameters("browser")
	@BeforeTest
	public void beforeTest(String browser) {
		// launch url
		logger.info("BeforeTest");
		
		// invoke browser
		driver = BrowserFunction.invokeBrowser(browser);
		
		driver.get(aut);
		
		// click register button
		driver.manage().window().maximize();
		
		// assign driver to Pages
		home = new Home(driver);
		checkout = new Checkout(driver);
	}
	
	@BeforeMethod
	public void beforeMethod() {

		home.btn_login_register.click();

		// check pop exists
		boolean actual = home.pop_register.isDisplayed();
		Assert.assertEquals(actual, true);
		
		logger.info(home.lnk_signUp.getText());

		// click signup
		home.lnk_signUp.click();
	}

	@Test(dataProvider = "registerData")
	public void registration(String testType,
							 String username,
							 String password, 
							 String confirmPassword ,
							 String email) {
		//start extent test
		testRegister = er.startTest(testType);
		// check if scenario is +ve or -ve
		if (testType.contains("Invalid") || testType.contains("Blank")) {
			testRegister.assignCategory("Registration-Negative");
		}else {
			testRegister.assignCategory("Registration-Positive");
		}
		
		// fill registration form
		logger.info(username);
		home.txt_username.sendKeys(username);
		logger.info(email);
		home.txt_email.sendKeys(email);
		logger.info(password);
		home.txt_password.sendKeys(password);
		home.txt_ConfirmPassword.sendKeys(confirmPassword);

		// submit form
		home.chk_terms.click();
		home.btn_register.click();
		// assertion
		try {
			Thread.sleep(2000);
			String actualContent = home.content_success.getText();
			logger.info(actualContent);
			logger.info("Registration success for : "+ username);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("failed to find success content : "+ e.getMessage());
			String failureMessage = home.content_failure.getText();
			logger.error("Failure message : " + failureMessage);
			if (!failureMessage.contains("already exists!")) {
				logger.error("Invalid Input data");
				Assert.assertTrue(false);
			}else {
				Assert.assertTrue(false);
			}
		}finally {
			er.endTest(testRegister);
			// close form
			home.btn_closePopUp.click();
		}
	}

	@Test(dataProvider="compareData")
	public void comparision() {
		
	}
	
	
	@AfterMethod
	public void AfterMethod() {

	}

	@AfterTest
	public void afterTest() {
		// close browser
		if (driver!=null) {
			driver.close();
		}
	}

	@DataProvider(name="registerData")
	public Object[][] registerData() {
		Object[][] td = null;
		try {
			// read filepath
			File f = new File(filepath);
			FileInputStream fis = new FileInputStream(f);
			// get workbook
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			// get sheet
			XSSFSheet sh = wb.getSheetAt(0);
			// last column & row
			int n = sh.getLastRowNum();
			int x = sh.getRow(0).getLastCellNum();
			td = new String[n][x];
			for (int i = 1; i <= n; i++) {
				for (int j = 0; j < x; j++) {
					// System.out.print(sh.getRow(i).getCell(j).getStringCellValue());
					td[i-1][j] = getCellData(sh, i, j);
				}
			}
			logger.info("exit DataProvider");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return td;
	}

	@DataProvider(name="compareData")
	public Object[][] compareData() {
		Object[][] td = null;
		try {
			// read filepath
			File f = new File(filepath);
			FileInputStream fis = new FileInputStream(f);
			// get workbook
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			// get sheet
			XSSFSheet sh = wb.getSheet("Compare");
			// last column & row
			int n = sh.getLastRowNum();
			int x = sh.getRow(0).getLastCellNum();
			td = new String[n][x];
			for (int i = 1; i <= n; i++) {
				for (int j = 0; j < x; j++) {
					// System.out.print(sh.getRow(i).getCell(j).getStringCellValue());
					td[i-1][j] = getCellData(sh, i, j);
				}
			}
			logger.info("exit DataProvider");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return td;
	}
	
	
	public Object getCellData(XSSFSheet sh, int i, int j) {
		Object cellvalue=null;
		Cell cell = sh.getRow(i).getCell(j);
		// check type of cell
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_FORMULA:
			cellvalue = cell.getCellFormula();
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			cellvalue = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			cellvalue = cell.getNumericCellValue();
			break;
		case Cell.CELL_TYPE_STRING:
			cellvalue = cell.getStringCellValue();
			break;

		default:
			logger.info("not valid type of cell");
		}
		return cellvalue;
	}
}
