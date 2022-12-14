Feature: purchase the item on EcommerceWebsite

Background:
Given Land on Ecommerce page

@Regression
Scenario Outline: positive test on purchase order
	Given Logged in with username<UserName>and password<Password>
	When  Add product<ProductName>to cart
	And  checkout<ProductName>and submit order
	Then  "THANKYOU FOR THE ORDER." message is displayed on confirmation page
Examples:
|  UserName          		|  Password    |  ProductName|
|bensonneal59@gmail.com     |  Benson@59   | zara coat 3|
|mprasannakumar124@gmail.com|  Prasu@59    |adidas original|


#Scenario: positive test on purchase order
#	Given Logged in with username "mprasannakumar124@gmail.com" and password "Prasu@59"
#	When  Add product "ZARA COAT 3" to cart
#	And  checkout "ZARA COAT 3"  and submit order
#	Then  "THANKYOU FOR THE ORDER." message is displayed on confirmation page