package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.WaitHelper;

public class SearchCustomerPage {
	public WebDriver driver;
	WaitHelper waithelper;
	
	
	public SearchCustomerPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waithelper = new WaitHelper(driver);
	}
	@FindBy(how = How.ID, using = "SearchEmail")
	@CacheLookup
	WebElement txtEmail;
	
	@FindBy(how = How.ID, using = "SearchFirstName")
	@CacheLookup
	WebElement txtFirstName;
	
	@FindBy(how = How.ID, using = "SearchLastName")
	@CacheLookup
	WebElement txtLastName;
	
	@FindBy(how = How.ID, using = "SearchMonthOfBirth")
	@CacheLookup
	WebElement drpdobMonth;
	
	@FindBy(how = How.ID, using = "SearchDayOfBirth")
	@CacheLookup
	WebElement drpdobDay;
	
	@FindBy(how = How.XPATH, using="//button[@id='search-customers']")
	@CacheLookup
	WebElement searchbtn;
	
	@FindBy(how = How.XPATH, using="//table[@id='customers-grid']")
	@CacheLookup
	WebElement table;
	
	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody//tr")
	@CacheLookup
	List<WebElement> tableRows;
	
	@FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody//tr//td")
	@CacheLookup
	List<WebElement> tableColumns;
	
	public void setEmail(String email) throws InterruptedException {
		waithelper.WaitForElement(txtEmail, 30);
		Thread.sleep(3000);
		//driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/form[1]/div[2]/div/div/div[1]/div/div[1]/div[3]")).click();
		txtEmail.clear();
		txtEmail.sendKeys(email);
		
	}
	public void setFirstname(String fname) throws InterruptedException {
		waithelper.WaitForElement(txtFirstName, 30);
		Thread.sleep(3000);
		//driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/form[1]/div[2]/div/div/div[1]/div/div[1]/div[3]")).click();
		txtFirstName.clear();
		txtFirstName.sendKeys(fname);
		
	}
	public void setLastname(String lname) {
		waithelper.WaitForElement(txtLastName, 30);
		txtLastName.clear();
		txtLastName.sendKeys(lname);
		
	}
	public void clickOnSearch() {
		waithelper.WaitForElement(searchbtn, 30);
		searchbtn.click();
	}
	public int getNoOfRows() {
		return(tableRows.size());
	}
	public int getNoOfColumns()
	{
		return(tableColumns.size());
	}
	public boolean searchCustomerByEmail(String email)
	{
		boolean flag= false;
		for(int i=1;i<=tableRows.size();i++)
		{
			String emailid = table.findElement(By.xpath("//table[@id='customers-grid']//tbody//tr["+i+"]//td[2]")).getText();
			System.out.println(emailid);
			if(emailid.equals("victoria_victoria@nopCommerce.com"))
			{
				flag = true;
			}
			
		}
		return flag;
	}
	
	public boolean searchCustomerByName(String Name)
	{
		boolean flag = false;
		for(int i=1;i<=getNoOfRows();i++)
		{
			String name = table.findElement(By.xpath("//table[@id='customers-grid']//tbody//tr["+i+"]//td[3]")).getText();
			String names[] = name.split(" ");
			if(names[0].equals("Victoria")&&names[1].equals("Terces"))
			{
				flag = true;
			
			}
		}
		return flag;
	}
	
}
