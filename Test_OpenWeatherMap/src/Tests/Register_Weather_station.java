package Tests;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "Feature"
		,glue={"TestStepDefinition"}
		,monochrome = true
		,plugin = { "pretty", "junit:target/cucumber-reports/TestResults.xml" }
		)

public class Register_Weather_station {
	
	
}
