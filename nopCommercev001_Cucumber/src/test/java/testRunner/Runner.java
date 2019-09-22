package testRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions
			(
				junit = "--step-notifications",
				features=".//Features/", //{".//Features/Login.feature",".//Features/Customers.feature"},
				glue="stepDefinitions",
				dryRun=false,
				monochrome = true,
				plugin= {"pretty","html:test-output"},
				tags= {"@sanity"}
				
					)

public class Runner {

}
