package com.macneil.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "D:\\New Workspace\\Macneil\\src\\main\\java\\com\\macneil\\features\\Order.feature",
        glue = {"stepdefinitions"},
        monochrome = true,
        plugin = {"pretty","html:test-output"},
        dryRun = true

)
public class OrderRunner {
}
