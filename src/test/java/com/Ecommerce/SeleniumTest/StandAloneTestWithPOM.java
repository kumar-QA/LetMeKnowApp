package com.Ecommerce.SeleniumTest;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.Ecommerce.java.CartPage;
import com.Ecommerce.java.CheckOutPage;
import com.Ecommerce.java.ConfirmationPage;
import com.Ecommerce.java.LandingPage;
import com.Ecommerce.java.ProductCatalog;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTestWithPOM {

	public static void main(String args[]) throws InterruptedException {
		String productName = "adidas original";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		LandingPage landingpage=new LandingPage(driver);
		landingpage.goTo();
		landingpage.loginApplication("mprasannakumar124@gmail.com", "Prasu@59");
		ProductCatalog productcatalog=new ProductCatalog(driver);
		productcatalog.getProductsList();
		productcatalog.addProductToCart(productName);
		productcatalog.gotoCartPage();
		CartPage cartpage=new CartPage(driver);
		cartpage.getCartProductList();
		Boolean match=cartpage.fetchSpecificProduct(productName);
		Assert.assertTrue(match);
		cartpage.gotoCheckout();
		CheckOutPage checkoutpage=new CheckOutPage(driver);
		checkoutpage.selectCountry("india");
		checkoutpage.Placeorder();
		ConfirmationPage conformationpage=new ConfirmationPage(driver);
		String text=conformationpage.getConfirmationMessage();
		Assert.assertEquals("THANKYOU FOR THE ORDER.", text);
		driver.close();
	}
}
