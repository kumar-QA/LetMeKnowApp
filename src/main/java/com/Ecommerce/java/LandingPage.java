package com.Ecommerce.java;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends GenericPage {

	public WebDriver driver;

	//constructor 
	public LandingPage(WebDriver Driver){
		super(Driver);
		driver=Driver;
		PageFactory.initElements(Driver, this);
	}
	
	//page factory
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement Password;
	@FindBy(id="login")
	WebElement loginBtn;
	
	//div[@class='ng-tns-c4-6 ng-star-inserted ng-trigger ng-trigger-flyInOut ngx-toastr toast-error']

	@FindBy(css="div[class*='flyInOut']")
	WebElement ErrorMessage;
	
	
	//Actions Methods
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductCatalog loginApplication(String Email,String Pwd) {
		userEmail.clear();
		userEmail.sendKeys(Email);
		Password.clear();
		Password.sendKeys(Pwd);
		loginBtn.click();
		ProductCatalog productcatalog=new ProductCatalog(driver);
		return productcatalog;
	}
	
	public String getErrorMessage() {
		waitForElementToAppear(ErrorMessage);
		return ErrorMessage.getText();
	}

}
