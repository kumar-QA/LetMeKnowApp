package com.Ecommerce.SeleniumTest;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String args[]) {
		String productName = "adidas original";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
	
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		driver.findElement(By.cssSelector("#userEmail")).sendKeys("mprasannakumar124@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Prasu@59");
		driver.findElement(By.cssSelector("#login")).click();
		
		
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		for (WebElement product : products) {
			if (product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)) {
				product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
				wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
				driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();
				break;
		}
			}
				List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cart h3"));
				for (WebElement cartproduct : cartproducts) {
					if (cartproduct.getText().equalsIgnoreCase(productName)) {
						driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
						driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("India");
						driver.findElement(By.cssSelector(".ta-results button:last-child")).click();
						WebElement btn = driver.findElement(By.cssSelector(".action__submit"));
						JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript("arguments[0].click();", btn);
						String text = driver.findElement(By.cssSelector(".hero-primary")).getText();
						Assert.assertEquals("THANKYOU FOR THE ORDER.", text);
					}
				}
				System.out.println("End");
				driver.close();

	}
}
