package com.Ecommerce.java;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GenericPage {
	public	WebDriver driver;
	
	public GenericPage(WebDriver Driver) {
		driver=Driver;
		PageFactory.initElements(Driver, this);
	}
	
	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement cartBtn;
	@FindBy(css="button[routerlink*='myorders']")
	WebElement orderBtn;
	
	public void waitForElementToAppear(By LocatorName) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(LocatorName));
	}
	public void waitForElementToAppear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	public void waitForElementToDisappear(WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void waitForElementToAppear(List<WebElement> ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfAllElements(ele));
	}
	
	public CartPage gotoCartPage() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", cartBtn);
//		cartBtn.click();
		CartPage cartpage=new CartPage(driver);
		return cartpage;
	}
	
	public OrdersPage gotoOrdersPage() {
		orderBtn.click();
		OrdersPage orderspage=new OrdersPage(driver);
		return orderspage;
	}
		
}
