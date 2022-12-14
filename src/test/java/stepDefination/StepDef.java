package stepDefination;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.Ecommerce.Test.BaseTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDef extends BaseTest{

	public WebDriver driver;

	@Given("Launch appliaction")
	public void launch_appliaction() throws IOException {
		driver=initializeDriver();

	}

	@When("Enter useraName and Password")
	public void enter_usera_name_and_password() {
		driver.findElement(By.id("form-username")).clear();
		driver.findElement(By.id("form-username")).sendKeys("admin@gmail.com");
		driver.findElement(By.id("form-password")).clear();
		driver.findElement(By.id("form-password")).sendKeys("Admin@123");
	}

	@When("Enter into the application")
	public void enter_into_the_application() {
		driver.findElement(By.className("btn")).click();
	}

	@Then("logout the application")
	public void logout_the_application() {
		driver.findElement(By.cssSelector(".user-menu")).click();
		driver.findElement(By.xpath("//a[@class='pull-right']")).click();
		
	}

}
