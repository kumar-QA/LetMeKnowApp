package com.Ecommerce.Test;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Ecommerce.java.CartPage;
import com.Ecommerce.java.CheckOutPage;
import com.Ecommerce.java.ConfirmationPage;
import com.Ecommerce.java.OrdersPage;
import com.Ecommerce.java.ProductCatalog;

public class SubmitOrderTest extends BaseTest {

	@Test(dataProvider="getData")	
	public void submitOrder(String Uname,String Pwd,String Prod) throws IOException, InterruptedException {
		ProductCatalog productcatalog = landingpage.loginApplication(Uname,Pwd);
		productcatalog.getProductsList();
		productcatalog.addProductToCart(Prod);
		CartPage cartpage = productcatalog.gotoCartPage();
		cartpage.getCartProductList();
		Boolean match = cartpage.fetchSpecificProduct(Prod);
		System.out.println("submit Order Page result"+match);
		Assert.assertTrue(match);
		CheckOutPage checkoutpage = cartpage.gotoCheckout();
		checkoutpage.selectCountry("india");
		ConfirmationPage conformationpage = checkoutpage.Placeorder();
		String text = conformationpage.getConfirmationMessage();
		System.out.println(text);
		Assert.assertEquals("THANKYOU FOR THE ORDER.", text);
	}
	
	@Test(dependsOnMethods = { "submitOrder" },dataProvider="verifyLoginData")
	public void verifyOrderdProductName(HashMap<String, String> data) {
		Boolean match;
		landingpage.loginApplication(data.get("Email"), data.get("Pwd"));
		OrdersPage orderspage = landingpage.gotoOrdersPage();
		orderspage.getOrderdList();
	     match = orderspage.verifyorderdProductNameDisplay(data.get("product"));
		Assert.assertTrue(match);
	}

	@DataProvider(name = "getData")
	public Object[][] getLoginData() {
		return new Object[][] { { "mprasannakumar124@gmail.com", "Prasu@59","ZARA COAT 3" },
				{ "bensonneal59@gmail.com", "Benson@59","ZARA COAT 3" } };
	}
	
    @DataProvider(name="verifyLoginData")
	public Object[][] getData() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("Email", "mprasannakumar124@gmail.com");
		map.put("Pwd", "Prasu@59");
		map.put("product", "ZARA COAT 3");
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("Email", "bensonneal59@gmail.com");
		map1.put("Pwd", "Benson@59");
		map1.put("product", "ZARA COAT 3");
		return new Object[][] { { map }, { map1 } };
	}
}