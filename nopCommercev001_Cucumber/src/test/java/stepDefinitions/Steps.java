package stepDefinitions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import junit.framework.Assert;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass{
	@Before
	public void setUp() throws IOException
	{	//reading properties file
		
		logger = Logger.getLogger("nopCommerce");//added logger
		PropertyConfigurator.configure("log4j.properties");
		configProp = new Properties();
		FileInputStream configPropFile = new FileInputStream("config.properties");
		configProp.load(configPropFile);
		
		
		String br=configProp.getProperty("browser");
		if(br.equals("chrome"))
		{
		System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
		driver = new ChromeDriver();
		}
		else if(br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", configProp.getProperty("firefoxpath"));
			driver = new FirefoxDriver();
		}
		else if(br.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver", configProp.getProperty("iepath"));
			driver = new InternetExplorerDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		logger.info("******Launching browser************");
	}

	@Given("User launches Chrome browser")
	public void user_launches_Chrome_browser() {
		
		
		
		lp = new LoginPage(driver);
		
		
	   
	}

	@When("User opens URL{string}")
	public void user_opens_URL(String url) {
		logger.info("******Launching URL************");
		driver.get(url);
	   
	}

	@When("User enters Email as {string} and Password a {string}")
	public void user_enters_Email_as_and_Password_a(String email, String password) {
		logger.info("******Providing Login Details************");
		lp.setUserName(email);
		lp.setPassword(password);
	    
	}

	@When("click on Login")
	public void click_on_Login() {
		logger.info("******Started login process************");
		lp.clickLogin();
	    
	}

	@Then("Page Title should be {string}")
	public void page_Title_should_be(String title) {
		if(driver.getPageSource().contains("Login was unsuccessful.")) {
			driver.close();
			logger.info("******Login Passed************");
			Assert.assertTrue(false);
		}
		else {
			logger.info("******Login Failed************");
			Assert.assertEquals(title, driver.getTitle());
		}
		
	    
	}

	@When("User clicks on logout link")
	public void user_clicks_on_logout_link() throws InterruptedException {
		Thread.sleep(3000);
		logger.info("******Logout************");
		lp.clickLogout();
	    
	    
	}

	

	@Then("close browser")
	public void close_browser() {
		logger.info("******closing browser************");
		driver.quit();
	   
	}

	//Customer Feature step Definition-----------------------------------------------
	
	@Then("User can view Dashboard")
	public void user_can_view_Dashboard() {
		addCust = new AddCustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration", addCust.getPageTitle());
		
	    
	}

	@When("User clicks on customer menu")
	public void user_clicks_on_customer_menu() throws InterruptedException {
		Thread.sleep(3000);
		addCust.clickOncustomerMenu();
	    
	}

	@When("clicks on customer Menu Item")
	public void clicks_on_customer_Menu_Item() throws InterruptedException {
		Thread.sleep(3000);
		addCust.clickOnCustomerMenuItem();
	    
	}

	@When("click on add button")
	public void click_on_add_button() throws InterruptedException {
		Thread.sleep(3000);
		addCust.clickOnAddnew();
	    
	}

	@Then("User can view add new customer page")//user can view add new customer page
	public void user_can_view_add_new_customer_page() {
		 Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
	}

	@When("User enters customer info")
	public void user_enters_customer_info() throws InterruptedException {
		logger.info("******Adding new Customer************");
		String email = randomestring()+"@gmail.com";
		addCust.setEmail(email);
		addCust.setPassword("test123");
		addCust.setFirstName("Tom");
		addCust.setLastName("Peter");
		addCust.setGender("Female");
		addCust.setDOB("1/4/1988");
		addCust.setCompanyName("amazon");
		Thread.sleep(3000);
		//addCust.setCustomerRoles("Guests");
		addCust.setManagerOfVendor("Vendor 2");
		addCust.setAdminContent("This is for testing..............");
		
	   
	}

	@When("click on Save button")
	public void click_on_Save_button() {
		logger.info("******Saving customer Data************");
		addCust.clickOnSave();
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String msg) {
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("The new customer has been added successfully"));
	    
	}
	
	//Steps for searching a customer using Email ID....................................
	@When("Enter customer Email")
	public void eneter_customer_Email() throws InterruptedException {
		
		logger.info("******Searching a customer by EMail************");
		searchCust = new SearchCustomerPage(driver);
		
		searchCust.setEmail("victoria_victoria@nopCommerce.com");
	    
	}

	@When("Click on search button")
	public void click_on_search_button() throws InterruptedException {
		searchCust.clickOnSearch();
		Thread.sleep(3000);
	}

	@Then("User should find email in the Search table")
	public void user_should_find_email_in_the_Search_table() {
		
	boolean status =searchCust.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
		Assert.assertEquals(true, status);
	   
	}
	//Steps for searching a customer by firstname and last name
	
	@When("Enter customer FirstName")
	public void enter_customer_FirstName() throws InterruptedException {
		logger.info("******Searching customer by name************");
		searchCust = new SearchCustomerPage(driver);
		searchCust.setFirstname("Victoria");
	   
	}

	@When("Enter customer LastName")
	public void enter_customer_LastName() {
	   searchCust.setLastname("Terces");
	}

	@Then("User should find name in the Search table")
	public void user_should_find_name_in_the_Search_table() {
		boolean status = searchCust.searchCustomerByName("Victoria Terces");
		Assert.assertEquals(true, status);
	    
	}



}
