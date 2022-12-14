Feature: ErrorValidaiton on EcommerceWebsite

@ErrorValidation
Scenario Outline: Error validaiton on eccomerce website
	Given Land on Ecommerce page
	And  Logged in with username<UserName>and password<Password>
	Then "Incorrect email or password." message should display
	
Examples:
|  UserName          		|  Password    | 
|bensonneal59@gmail.com     |  Benson@5   | 