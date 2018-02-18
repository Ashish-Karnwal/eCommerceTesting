package com.offersbyshop.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class Home {
	private WebDriver driver;
	// login/register button
	@FindBy(xpath = "//div[@class='tabledisplay']/*/span[@data-type='login']")
	public WebElement btn_login_register;

	// signup link
	@FindBy(xpath = "//div[@class='pm-content']/*/div[3]/span[text()='Sign Up']")
	public WebElement lnk_signUp;

	// registeration pop-up
	@FindBy(xpath = "//div[@class='pm-content']")
	public WebElement pop_register;

	// registration username
	@FindBy(xpath = "//div[@class='pm-content']/*/form/div[1]/input")
	public WebElement txt_username;

	// registration email
	@FindBy(xpath = "//div[@class='pm-content']/*/form/div[2]/input")
	public WebElement txt_email;

	// password
	@FindBy(xpath = "//div[@class='pm-content']/*/form/div[3]/input")
	public WebElement txt_password;

	// confirm password
	@FindBy(xpath = "//div[@class='pm-content']/*/form/div[4]/input")
	public WebElement txt_ConfirmPassword;

	// terms & condition
	@FindBy(xpath = "//div[@class='pm-content']/*/form/div[5]/*/label/input")
	public WebElement chk_terms;

	// submit - register submit
	@FindBy(xpath = "//div[@class='pm-content']/*/form/div[6]/button")
	public WebElement btn_register;

	// verify success content
	@FindBy(xpath = "//div[@class='pm-content']/*/div[2]/div[@class='wpsm_box green_type']")
	public WebElement content_success;
	
	// verify failure content
	@FindBy(xpath = "//div[@class='pm-content']/*/div[2]/div[@class='wpsm_box warning_type']/p")
	public WebElement content_failure;

	@FindBy(xpath="//div[@id='pgwModal']/*/*/*/span[@class='pm-icon']")
	public WebElement btn_closePopUp;
	
	// Categories
	By lbl_CategoryAll = By.xpath("//h3[@class='vc_custom_heading']");
	
	// Total articles on a page
	By total_ArticlesonPage = By.xpath("//article[contains(@class,'offer_grid')]");
	
	// individual category
	By total_ArticlesInCategory = By.xpath("//div[@class='owl-stage-outer'][Index]/*/*");
	
	public String lnk_CategoryViewAll = "//h3[@class='vc_custom_heading'][contains(.,'Category')]/following-sibling::div[1]/a[contains(.,'View All')]"; 
	
	/**
	 * Constructor
	 * @param driver
	 */
	public Home(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	public List<String> getAllCategories() {
		
		return null;
		
	}
	
	public int articleCountOnHome() {
		
		return 0;
		
	}

}
