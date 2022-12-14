package com.Ecommerce.java;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage extends GenericPage {
	public WebDriver driver;
	public boolean flag;

	OrdersPage(WebDriver Driver) {
		super(Driver);
		driver = Driver;
		PageFactory.initElements(Driver, this);
	}

	@FindBy(xpath = "//tbody/tr/td/img/following::td[1]")
	List<WebElement> OrderdProductNames;

	public List<WebElement> getOrderdList() {
		waitForElementToAppear(OrderdProductNames);
		System.out.println(OrderdProductNames);
		return OrderdProductNames;

	}

	public boolean verifyorderdProductNameDisplay(String productName) {
		for(WebElement product:getOrderdList()) {
			if(product.getText().equalsIgnoreCase(productName)) {
				flag=true;
			}else {
				flag=false;
			}
		}
		return flag;
		
	}

}
