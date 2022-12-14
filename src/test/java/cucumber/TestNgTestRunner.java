package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		
				features="src\\test\\java\\cucumber\\ErrorValidation.feature",
				glue="E:\\Eclipse_workspace\\Project\\src\\test\\java\\stepDefination\\StepDefinationimpl.java",
				plugin= {"html:target/cucumber.html"}
		)

public class TestNgTestRunner extends AbstractTestNGCucumberTests {

	
}
