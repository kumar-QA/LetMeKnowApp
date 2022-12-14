package stepDefination;

import java.io.IOException;

import org.testng.Assert;

import com.Ecommerce.Test.BaseTest;
import com.Ecommerce.java.CartPage;
import com.Ecommerce.java.CheckOutPage;
import com.Ecommerce.java.ConfirmationPage;
import com.Ecommerce.java.LandingPage;
import com.Ecommerce.java.ProductCatalog;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationimpl extends BaseTest{

	public LandingPage landingpage;
	public ProductCatalog productcatalog;
	public CartPage cartpage;
	public CheckOutPage checkoutpage;
	public ConfirmationPage conformationpage;
	
	 @Given("^Land on Ecommerce page$")
	    public void land_on_ecommerce_page()  throws IOException {
			landingpage=launchApplication();
	    }

	    @Given("^Logged in with username(.+)and password(.+)$")
	    public void logged_in_with_usernameand_password(String username, String password){
	    	productcatalog=landingpage.loginApplication(username,password);
	    }

	    @When("^ Add product(.+)to cart$")
	    public void add_productto_cart(String productname) throws InterruptedException {
			productcatalog.getProductsList();
			productcatalog.addProductToCart(productname);
		}

	    @Then("^ \"([^\"]*)\" message is displayed on confirmation page$")
	    public void something_message_is_displayed_on_confirmation_page(String strArg1){
	    	conformationpage = checkoutpage.Placeorder();
			String text = conformationpage.getConfirmationMessage();
			Assert.assertEquals(strArg1, text);
			driver.close();
	    }

	    @And("^ checkout(.+)and submit order$")
	    public void checkoutand_submit_order(String productname) throws InterruptedException{
	    	cartpage = productcatalog.gotoCartPage();
			cartpage.getCartProductList();
			Boolean match = cartpage.fetchSpecificProduct(productname);
			Assert.assertTrue(match);
			checkoutpage = cartpage.gotoCheckout();
			checkoutpage.selectCountry("india");
	    }

	
	    @Then("^\"([^\"]*)\" message should display$")
	    public void something_message_should_display(String strArg1){
	    	Assert.assertEquals(strArg1,landingpage.getErrorMessage());
	    }
	
}
