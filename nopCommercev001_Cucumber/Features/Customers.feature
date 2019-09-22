Feature: Customers
Background: Below are the common steps for each scenario
	Given User launches Chrome browser
	When User opens URL"http://admin-demo.nopcommerce.com/login"
	And User enters Email as "admin@yourstore.com" and Password a "admin"
	And click on Login
	Then User can view Dashboard
	
@sanity
Scenario: Add a new Customer
	
	
	When User clicks on customer menu
	And clicks on customer Menu Item
	And click on add button
	Then User can view add new customer page
	When User enters customer info
	And click on Save button
	Then User can view confirmation message "The new customer has been added successfully."
	And close browser
	
@regression
Scenario: Search Customer by EmailID
	
	When User clicks on customer menu
	And clicks on customer Menu Item
	And Enter customer Email
	When Click on search button
	Then User should find email in the Search table
	And close browser
	
	
@regression	
Scenario: Search Customer by EmailID
	
	When User clicks on customer menu
	And clicks on customer Menu Item
	And Enter customer FirstName
	And Enter customer LastName
	When Click on search button
	Then User should find name in the Search table
	And close browser