package com.Ecommerce.Test;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.Ecommerce.java.CartPage;
import com.Ecommerce.java.ProductCatalog;
public class ErrorValidationTest extends BaseTest {
	@Test(groups="ErrorHandling")
	public void loginErrorValidation() throws IOException {
	   landingpage.loginApplication("mprasannakumar14@gmail.com", "Prasu@59");
		Assert.assertEquals("Incorrect email or password.",landingpage.getErrorMessage());
	}
	@Test(groups="ErrorHandling")
	@Parameters({"UserName","Password"})
	public void productErrorValidation(String Uname,String Pwd) throws InterruptedException {
		String productName="adidas original";
		   ProductCatalog productcatalog=landingpage.loginApplication(Uname, Pwd);
//		   List<WebElement>products=  productcatalog.getProductsList();
			productcatalog.getProductsList();
			productcatalog.addProductToCart(productName);
			CartPage cartpage=productcatalog.gotoCartPage();
			cartpage.getCartProductList();
			Boolean match=cartpage.fetchSpecificProduct("adidas origina");
			Assert.assertFalse(match);
	}
}