package steps;

import bookstore.BookStoreAuthorization;
import bookstore.models.CredentialModel;
import bookstore.models.TokenResponse;
import bookstore.models.UserResponse;
import driver.Driver;
import io.cucumber.java.*;
import io.cucumber.java.en.Given;
import utilities.TestStore;
import utilities.Utils;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.ObjectMapper;
import static driver.Driver.DriverType.chrome;


public class CommonSteps extends Utils {

    public Scenario scenario;
    public boolean authenticate;
    public boolean initialiseBrowser;
    BookStoreAuthorization bookStoreAuth = new BookStoreAuthorization();
    ObjectMapper objectMapper = new ObjectMapper();
    @Before
    public void before(Scenario scenario) {
        log.new Warning("Running: " + scenario.getName());
        processScenarioTags(scenario);
        if (initialiseBrowser) Driver.setup(getDriverType(scenario));
        if (scenario.getSourceTagNames().contains("@Authenticate")) {
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

    @After
    public void after(Scenario scenario) {
        if (initialiseBrowser) {
            Driver.quitDriver();
        }
        if (scenario.isFailed()) throw new RuntimeException(scenario.getName() + ": FAILED!");
        else log.new Success(scenario.getName() + ": PASS!");
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
