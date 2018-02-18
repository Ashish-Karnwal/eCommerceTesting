package com.offersbyshop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class Checkout {
	static WebDriver driver;
	static By Qty = By.xpath("//*[@id='checkout_page_container']/div[1]/table/tbody/tr[2]/td[3]/form/input[1]");
	static By Rmv = By.xpath("//*[@id='checkout_page_container']/div[1]/table/tbody/tr[2]/td[6]/form/input[4]");
	static By Update = By.xpath("//*[@id='checkout_page_container']/div[1]/table/tbody/tr[2]/td[3]/form/input[4]");
	static By ctn = By.xpath("//*[@id='checkout_page_container']/div[1]/a/span");
	static By home = By.xpath("  //*[@id='menu-item-53']");
	static By smp = By.xpath("//*[@id='menu-item-54']/a");
	static By myacc = By.xpath("//*[@id='menu-item-55']/a");
	static By Search = By.xpath("*[@name='s']");
	static By Curr_state = By.xpath("//a[@class=shipping_region text  wpsc-visitor-meta']");
	static By Email = By.xpath("//*[@id='wpsc_checkout_form_9']");
	static By F_name = By.xpath("//*[@id='wpsc_checkout_form_2']");
	static By L_name = By.xpath("//*[@id='wpsc_checkout_form_3']");
	static By address = By.xpath("//*[@id='wpsc_checkout_form_4']");
	static By city = By.xpath("//*[@id='wpsc_checkout_form_5']");
	static By state = By.xpath("//*[@id='wpsc_checkout_form_6']");
	static By Pocode = By.xpath("//*[@name='collected_data[8]']");
	static By Phone = By.xpath("//*[@name='collected_data[18]']");
	static By B_add = By.xpath("//*[@id='shippingSameBilling']");
	static By Pur_btn = By.xpath("//*[@name='submit'");
	static By Bck_btn = By.xpath("//a[@class='review group']/span");

	public static void proceedtocheckout()

	{
		driver.findElement(ctn).click();
	}

	public static void SPhome() {
		driver.findElement(home).click();
	}

	public static void Smp_page() {
		driver.findElement(smp).click();
	}

	public static void my_account() {
		driver.findElement(myacc).click();
	}

	public static void itm_rmv_cart() {
		driver.findElement(Rmv).click();
	}

	public static void quantity(String a) {
		driver.findElement(Qty).sendKeys("a");
		driver.findElement(Update).click();
	}
	
	public static void Shiping_detail(String  Curr_state,
									  String  Email,
									  String  Fname, 
									  String  Lname, 
									  String  Add, 
									  String  City,  
									  String  State,  
									  String  Poscode,  
									  String  Phn)

	{	
		WebElement  Curr_Conty =driver.findElement(By.xpath("//*[@id='current_country']"));
		Select sel= new Select(Curr_Conty);
		sel.selectByValue("IN");
//		driver.findElement(Curr_state).sendkeys(Curr_state);
//		driver.findElement(Email).sendkeys(Email);
//		driver.findElement(F_name).sendKeys(Fname);
//		driver.findElement(L_name).sendKeys(Lname);
//		driver.findElement(address).sendkeys(Add);
//		driver.findElement(city).sendKeys(City);
//		driver.findElement(state).sendKeys(State);
//		WebElement  Conty =driver.findelement(By.xpath("//*[@id="wpsc_checkout_form_7"]"));
//		Select select= new Select(Conty);
//		select.selectByValue("IN");
		driver.findElement(Pocode).sendKeys(Poscode);
		driver.findElement(Phone).sendKeys(Phn);
		driver.findElement(B_add).click();
		
	}

	public static void Purchase() {
		Shiping_detail("UP", "ashish.k@4qt.com", "Ashish", "Karnwal", "Sector 58", "Noida", "UP", "201301",
				"9654280823");
		driver.findElement(Pur_btn).click();
	}

	public static void Go_back() {
		driver.findElement(Bck_btn).click();
	}
	
	/**
	 * Constructor
	 * @param driver
	 * @return 
	 */
	public Checkout(WebDriver driver) {
		Checkout.driver = driver;
		PageFactory.initElements(driver, this);
	}

}
