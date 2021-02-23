package com.test.qa.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty",
                "json:target/citybikeapi/cucumber-data/test-report-data.json"},
        features = "src/test/resources/features",
        glue = {"com.test.qa.config", "com.test.qa.steps"},
        tags = "@CityBike"
)
public class CityBikeApiTests {
}
