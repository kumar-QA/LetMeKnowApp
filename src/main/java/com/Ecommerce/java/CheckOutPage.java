package com.Ecommerce.java;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends GenericPage {

	public WebDriver driver;

	//constructor 
	
	public CheckOutPage(WebDriver Driver){
		super(Driver);
		driver=Driver;
		PageFactory.initElements(Driver, this);
	}
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement EnterCountry;
	@FindBy(css=".ta-results button:last-child")
	WebElement SelectCountry;
	@FindBy(css=".action__submit")
	WebElement placeOrderBtn;
	
	public void selectCountry(String Country) throws InterruptedException{
		Thread.sleep(1000);
		EnterCountry.sendKeys(Country);
		System.out.println(Country);
		SelectCountry.click();
	}
	public ConfirmationPage Placeorder() {
//		placeOrderBtn.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", placeOrderBtn);
		ConfirmationPage conformationpage=new ConfirmationPage(driver);
		return conformationpage;
				
		
	}
}
