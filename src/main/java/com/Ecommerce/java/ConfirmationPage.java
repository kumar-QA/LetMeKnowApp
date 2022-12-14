package com.Ecommerce.java;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends GenericPage{

	public WebDriver driver;

	public ConfirmationPage(WebDriver Driver) {
		super(Driver);
		driver=Driver;
		PageFactory.initElements(Driver, this);
	}

	@FindBy(css=".hero-primary")
	WebElement Message;
	
	public String getConfirmationMessage() {
	return Message.getText();
	}
}
