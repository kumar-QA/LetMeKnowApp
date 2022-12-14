package com.Ecommerce.java;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends GenericPage {

	public WebDriver driver;
	public Boolean flag;

	//constructor 
	
	public CartPage(WebDriver Driver){
		super(Driver);
		driver=Driver;
		PageFactory.initElements(Driver, this);
	}
	
	//page factory
	@FindBy(xpath="//button[contains(text(),'Checkout')]")
	WebElement checkoutBtn;
	
	@FindBy(css=".cart h3")
	List<WebElement> cartproductsList;
	
	public List<WebElement> getCartProductList() {
		waitForElementToAppear(cartproductsList);
		return cartproductsList;
	}
	
	public Boolean fetchSpecificProduct(String ProductName) {
		for (WebElement Singlecartproduct : getCartProductList()) {
			if (Singlecartproduct.getText().equalsIgnoreCase(ProductName)) {
				flag=true;
			}else {
				flag=false;
			}
		}
		System.out.println(" method return" +flag);
		return flag;
	}
	 public CheckOutPage gotoCheckout() {
//		 checkoutBtn.click();
		 JavascriptExecutor js=(JavascriptExecutor)driver;
		 js.executeScript("arguments[0].click()",checkoutBtn);
		CheckOutPage checkoutpage=new CheckOutPage(driver);
		return checkoutpage;
	 }
	
}
