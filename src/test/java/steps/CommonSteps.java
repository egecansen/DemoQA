package steps;

import driver.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import utilities.Utils;

import java.util.concurrent.TimeUnit;

import static utils.WebUtilities.Color.PURPLE;

public class CommonSteps extends Utils {

    @Before
    public void before(Scenario scenario) {
        log.new Warning("Running: " + scenario.getName());
        Driver.setup();
    }

    @After
    public void after(Scenario scenario) {
        if (scenario.isFailed()) throw new RuntimeException(scenario.getName() + ": FAILED!");
        else {Driver.quitDriver(); log.new Success(scenario.getName() + ": PASS!");}
    }

    @Given("Navigate to {}")
    public void navigate(String url) {
        log.new Info("Navigating to '" + url + "'");
        Driver.driver.get(url);
    }

    @Given("Wait {} seconds")
    public void wait(int duration) {
        log.new Info("Waiting for " + duration + " seconds");
        try {
            TimeUnit.SECONDS.sleep(duration);}
        catch (InterruptedException ignored){}
    }
}
