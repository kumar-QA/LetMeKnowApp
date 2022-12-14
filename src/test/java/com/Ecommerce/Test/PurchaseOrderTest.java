package com.Ecommerce.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Ecommerce.java.CartPage;
import com.Ecommerce.java.CheckOutPage;
import com.Ecommerce.java.ConfirmationPage;
import com.Ecommerce.java.ProductCatalog;

public class PurchaseOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(dataProvider="Data")	
	public void purchaseProduct(HashMap<String,String> input) throws IOException, InterruptedException {
		ProductCatalog productcatalog = landingpage.loginApplication(input.get("Email"),input.get("Pwd"));
		productcatalog.getProductsList();
		productcatalog.addProductToCart(input.get("Product"));
		CartPage cartpage = productcatalog.gotoCartPage();
		cartpage.getCartProductList();
		Boolean match = cartpage.fetchSpecificProduct(input.get("Product"));
		Assert.assertTrue(match);
		CheckOutPage checkoutpage = cartpage.gotoCheckout();
		checkoutpage.selectCountry("india");
		ConfirmationPage conformationpage = checkoutpage.Placeorder();
		String text = conformationpage.getConfirmationMessage();
		Assert.assertEquals("THANKYOU FOR THE ORDER.", text);
	}
	@DataProvider
	public Object[][] Data() throws IOException {
		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\com\\Ecommerce\\Resources\\PurchaseDetails.json");
		return new Object[][]{{data.get(0)},{data.get(1)}};
	}
	
	

}