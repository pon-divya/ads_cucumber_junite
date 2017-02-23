package com.ads.runnerTest;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "html:target/html/Home/", "json:target/json/Home.json" }, features = {
		"src/test/resources/features/Home/Home.feature" }, glue = { "com.ads.steps",
				"com.ads.pages" }, tags = { "@smoke" })

public class Home {

}
