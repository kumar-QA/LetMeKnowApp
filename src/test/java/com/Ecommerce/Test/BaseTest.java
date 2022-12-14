package com.Ecommerce.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.Ecommerce.java.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingpage;
	public WebDriver initializeDriver() throws IOException {
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\com\\Ecommerce\\Resources\\config.properties");
		prop.load(fis);
		String browsername=System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
		System.out.println(browsername);
		if(browsername.contains("chrome")){
			ChromeOptions options=new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browsername.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1400,900));
			driver.get("https://rahulshettyacademy.com/client");
		}else if(browsername.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			driver.get("https://rahulshettyacademy.com/client");
		}else if(browsername.equalsIgnoreCase("edge")) {
			WebDriverManager.iedriver().setup();
			driver=new EdgeDriver();
			driver.get("https://rahulshettyacademy.com/client");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		return driver;
	}
	@BeforeMethod(alwaysRun=true)//BeforeTest we cannot bcz when other test execuitng it wont available these test
	public LandingPage launchApplication() throws IOException{
		driver=initializeDriver();
	    landingpage=new LandingPage(driver);
		landingpage.goTo();
		return landingpage;
	}
	
	@AfterMethod(alwaysRun=true)//AfterTest
	public void tearDown() {
		driver.close();
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
		//read JSON To String
		String JSONContent=FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
		
		//String to HashMap --we need Jackson Databind library -add to pom.xml
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(JSONContent,new TypeReference<List<HashMap<String,String>>>(){});
		
		return data;
	}
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
		TakesScreenshot scrnshot=(TakesScreenshot)driver;
		File scrFile=scrnshot.getScreenshotAs(OutputType.FILE);
		File dstFile=new File("E:\\Eclipse_workspace\\Project\\Screenshots\\" +testCaseName +".png");
		FileUtils.copyFile(scrFile, dstFile);
		return "E:\\Eclipse_workspace\\Project\\Screenshots\\" +testCaseName +".png";
	}
	
}
