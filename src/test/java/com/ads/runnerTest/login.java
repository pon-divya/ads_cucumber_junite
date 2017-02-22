package com.ads.runnerTest;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(plugin = { "pretty", "html:target/html/login/", "json:target/json/login.json" }, features = {
		"src/test/resources/features/login/login.feature" }, glue = { "com.ads.steps",
				"com.ads.pages" }, tags = { "@smoke2" })

public class login {

}