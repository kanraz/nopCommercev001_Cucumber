Feature: Login


@sanity
Scenario: Successful login with valid Credentials
	Given User launches Chrome browser
	When User opens URL"http://admin-demo.nopcommerce.com/login"
	And User enters Email as "admin@yourstore.com" and Password a "admin"
	And click on Login
	Then Page Title should be "Dashboard / nopCommerce administration"
	When User clicks on logout link
	Then Page Title should be "Your store. Login"
	And close browser
	
@regression
Scenario Outline: Login Data Driven
	Given User launches Chrome browser
	When User opens URL"http://admin-demo.nopcommerce.com/login"
	And User enters Email as "<email>" and Password a "<password>"
	And click on Login
	Then Page Title should be "Dashboard / nopCommerce administration"
	When User clicks on logout link
	Then Page Title should be "Your store. Login"
	And close browser
	
	Examples:
	|  email     |password |
	|admin@yourstore.com| admin |
	|admin1@yourstore.com| admin123 |