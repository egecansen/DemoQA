package steps;

import bookstore.BookStoreAuthorization;
import bookstore.models.CredentialModel;
import bookstore.models.TokenResponse;
import bookstore.models.UserResponse;
import driver.Driver;
import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utilities.TestStore;
import utilities.Utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.ObjectMapper;

import static driver.Driver.DriverType.chrome;


public class CommonSteps extends Utils {

    public Scenario scenario;
    public boolean authenticate;
    public boolean initialiseBrowser;
    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void before(Scenario scenario) {
        log.new Warning("Running: " + scenario.getName());
        processScenarioTags(scenario);
        if (initialiseBrowser) Driver.setup(getDriverType(scenario));
        if (authenticate) {
            CredentialModel user = new CredentialModel("Tillerman");
            user.setPassword("Tillerman1*");

            UserResponse userResponse = BookStoreAuthorization.createUser(user);
            TestStore.put("contextUser", user);
            TestStore.put("userId", userResponse.getUserID());

            TestStore.put("userName", userResponse.getUsername());
            TestStore.put("password", user.getPassword());

            TokenResponse tokenResponse = BookStoreAuthorization.generateToken(user);
            TestStore.put("token", tokenResponse.getToken());
        }
    }

    @After
    public void after(Scenario scenario) {
        if (initialiseBrowser && scenario.isFailed()) {
            captureScreen();
            Driver.quitDriver();
            log.new Error(scenario.getName() + ": FAILED!", null);
        }
        if (initialiseBrowser && !scenario.isFailed()) {
            Driver.quitDriver();
            log.new Success(scenario.getName() + ": PASS!");
        }
    }

    public void processScenarioTags(Scenario scenario){
        log.new Important(scenario.getSourceTagNames());
        this.scenario = scenario;
        authenticate = scenario.getSourceTagNames().contains("@Authenticate");
        initialiseBrowser = scenario.getSourceTagNames().contains("@Web-UI");
    }

    @SuppressWarnings("unused")
    @DefaultParameterTransformer
    @DefaultDataTableEntryTransformer
    @DefaultDataTableCellTransformer
    public Object transformer(Object fromValue, Type toValueType) {
        return objectMapper.convertValue(fromValue, objectMapper.constructType(toValueType));
    }

    public void captureScreen() {
        log.new Info("Capturing screen");
        File src=((TakesScreenshot)Driver.driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src, new File("src/test/resources/files/screenshots" + File.separator + "screenshot-" + scenario.getName() + ".png"));
        }
        catch (IOException e) {
            e.printStackTrace();
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
    @Given("Adjust window size to {}, {} and navigate to {}")
    public void navigateWithWindowSize(int width, int height, String url) {
        setWindowSize(width, height);
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
