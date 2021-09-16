package com.macneil.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "D:\\New Workspace\\Macneil\\src\\main\\java\\com\\macneil\\features\\Order.feature",
        glue = {"com.macneil.stepdefinitions","com.macneil.testbase"},
        monochrome = true,
        plugin = {"html:test-output", "progress"},
        dryRun = false

)
public class OrderRunner {
}
