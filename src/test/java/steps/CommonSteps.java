package steps;

import driver.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import utilities.Utils;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static driver.Driver.DriverType.chrome;


public class CommonSteps extends Utils {

    public Scenario scenario;
    public boolean authenticate;
    public boolean initialiseBrowser;

    @Before
    public void before(Scenario scenario) {
        log.new Warning("Running: " + scenario.getName());
        processScenarioTags(scenario);
        if (initialiseBrowser) Driver.setup(getDriverType(scenario));
    }

    public void processScenarioTags(Scenario scenario){
        log.new Important(scenario.getSourceTagNames());
        this.scenario = scenario;
        authenticate = scenario.getSourceTagNames().contains("@Authenticate");
        initialiseBrowser = scenario.getSourceTagNames().contains("@Web-UI");
    }

    @After
    public void after(Scenario scenario) {
        Driver.quitDriver();
        if (!initialiseBrowser){
            if (scenario.isFailed()) throw new RuntimeException(scenario.getName() + ": FAILED!");
            else log.new Success(scenario.getName() + ": PASS!");
        }
    }

    public Driver.DriverType getDriverType(Scenario scenario) {
        for (Driver.DriverType driverType: Driver.DriverType.values()) {
            if (scenario.getSourceTagNames().stream().anyMatch(tag -> tag.replaceAll("@", "").equalsIgnoreCase(driverType.name())))
                return driverType;
        }
        return chrome;
    }

    @Given("Navigate to {}")
    public void navigate(String url) {
        log.new Info("Navigating to '" + url + "'");
        Driver.driver.get(url);
    }

    @Given("Wait {} seconds")
    public void wait(int duration) {
        log.new Info("Waiting for " + duration + " seconds");
        try {TimeUnit.SECONDS.sleep(duration);}
        catch (InterruptedException ignored){}
    }
}
