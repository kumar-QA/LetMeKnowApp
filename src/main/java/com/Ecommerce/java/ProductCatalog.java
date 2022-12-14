package com.Ecommerce.java;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductCatalog extends GenericPage {

	public WebDriver driver;
	public WebElement product;
//	String productName = "adidas original";

	public ProductCatalog(WebDriver Driver) {
		super(Driver);
		driver = Driver;
		PageFactory.initElements(Driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement BufferImg;
	By ProductsList = By.cssSelector(".mb-3");
	By Text = By.cssSelector("b");
	By AddToCartbtn = By.cssSelector(".card-body button:last-of-type");
//	//div[@class='card-body']/button[2]

//	By AddToCartbtn = By.xpath("//div[@class='card-body']/button[2]");
	
	By Toaster=By.cssSelector("#toast-container");

	public List<WebElement> getProductsList() {
		waitForElementToAppear(ProductsList);
		return products;
	}

	public WebElement getProductByName(String productName) {
		for (WebElement singleproduct : getProductsList()) {
			if (singleproduct.findElement(Text).getText().equalsIgnoreCase(productName)) {
				product = singleproduct;
//				return product;
			}
		}
		return product;
	}

	public void addProductToCart(String productname) throws InterruptedException {
		
		Thread.sleep(2000);
//		waitForElementToAppear(AddToCartbtn);
		getProductByName(productname).findElement(AddToCartbtn).click();
		System.out.println("productname "+productname);
//		waitForElementToAppear(Toaster);
//		waitForElementToDisappear(BufferImg);
	}

}
