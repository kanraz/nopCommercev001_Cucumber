package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AddCustomerPage {

	public WebDriver driver;
	public AddCustomerPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	By lnkCustomers_menu=By.xpath("//a[@href='#']//span[contains(text(),'Customers')]");
	By lnkCustomers_menuitem=By.xpath("/html/body/div[3]/div[2]/div/ul/li[4]/ul/li[1]/a/span");
	By btnAddNew = By.xpath("//a[@class='btn bg-blue']");
	
	By txtEmail = By.xpath("//input[@id='Email']");
	By txtPassword = By.xpath("//input[@id='Password']");
	By txtcustomerRoles = By.xpath("//div[@class='k-multiselect-wrap k-floatwrap']");
	
	By lstitemAdminstrator = By.xpath("//select[@id='SelectedCustomerRoleIds']//option[@value=\"1\"]");
	By lstitemRegistered = By.xpath("//select[@id='SelectedCustomerRoleIds']//option[@value=\"2\"]");
	By lstitemGuests = By.xpath("//select[@id='SelectedCustomerRoleIds']//option[@value=\"3\"]");
	By lstitemVendors = By.xpath("//select[@id='SelectedCustomerRoleIds']//option[@value=\"4\"]");
	
	By drpmgrOfVendor = By.xpath("//select[@id='VendorId']");
	By rdMaleGender=By.id("Gender_Male");
	By rdFemaleGender=By.id("Gender_Female");
	
	By txtDob=By.xpath("//input[@id='DateOfBirth']");
	
	By txtFirstName=By.xpath("//input[@id='FirstName']");
	By txtLastName=By.xpath("//input[@id='LastName']");
	
	By txtCompanyName=By.xpath("//input[@id='Company']");
	
	By txtAdmincontent = By.xpath("//textarea[@id='AdminComment']");
	
	By btnSave = By.xpath("//button[@name='save']");
	
	//action Methods
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public void  clickOncustomerMenu() {
		driver.findElement(lnkCustomers_menu).click();
		
	}
	public void clickOnCustomerMenuItem() {
		driver.findElement(lnkCustomers_menuitem).click();
	}
	public void clickOnAddnew() {
		driver.findElement(btnAddNew).click();
	}
	public void setEmail(String email) {
		driver.findElement(txtEmail).sendKeys(email);
	}
	public void setPassword(String password)
	{
		driver.findElement(txtPassword).sendKeys(password);
	}
	public void setCustomerRoles(String role) throws InterruptedException
	{
		/*if(!role.equals("Vendors"))
		{
			driver.findElement(By.xpath("//*[@id=\"customer-info\"]/div[2]/div[1]/div[10]/div[2]/div/div[1]/div")).click();
		
		}*/
		driver.findElement(By.xpath("//span[@class='k-icon k-delete']")).click();
		driver.findElement(txtcustomerRoles).click();
		WebElement listitem;
		Thread.sleep(3000);
		if(role.equals("Administrator")) {
			listitem=driver.findElement(lstitemAdminstrator);
		}
		else if(role.equals("Registered")) {
			listitem= driver.findElement(lstitemRegistered);
		}
		else if(role.equals("Vendors")) {
			listitem=driver.findElement(lstitemVendors);
		}
		else
		{
			listitem = driver.findElement(lstitemGuests);
		}
		//listitem.click();
		JavascriptExecutor js =(JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", listitem);
	}
	public void setManagerOfVendor(String value) throws InterruptedException
	{
		Thread.sleep(3000);
		Select drp=new Select(driver.findElement(drpmgrOfVendor));
		drp.selectByVisibleText(value);
	}
	public void setGender(String gender)
	{
		if(gender.equals("Male")) {
			driver.findElement(rdMaleGender).click();
		}
		else if(gender.equals("Female")) {
			driver.findElement(rdFemaleGender).click();
		}
		else
		{
			driver.findElement(rdMaleGender).click();
		}
	}
	public void setFirstName(String fname)
	{
		driver.findElement(txtFirstName).sendKeys(fname);
	}
	public void setLastName(String lname)
	{
		driver.findElement(txtLastName).sendKeys(lname);
	}
	public void setDOB(String dob)
	{
		driver.findElement(txtDob).sendKeys(dob);
	}
	public void setCompanyName(String comname)
	{
		driver.findElement(txtCompanyName).sendKeys(comname);
	}
	public void setAdminContent(String content)
	{
		driver.findElement(txtAdmincontent).sendKeys(content);
	}
	public void clickOnSave()
	{
		driver.findElement(btnSave).click();
	}
}
