package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import utils.PropertiesReader;
import utils.StringUtilities;

import java.time.Duration;
import java.util.Properties;

public class Driver {

    PropertiesReader reader = new PropertiesReader("properties-from-pom.properties");
    StringUtilities strUtils = new StringUtilities();

    public enum DriverType {
        chrome, safari, firefox
    }

    public static WebDriver driver;

    public static void setup(DriverType driverType){
        PropertiesReader reader = new PropertiesReader("properties-from-pom.properties");
        StringUtilities strUtils = new StringUtilities();
        switch (driverType) {
            case chrome:
                ChromeOptions options = new ChromeOptions();

                driver = new ChromeDriver(options);
                break;
            case firefox:
                driver = new FirefoxDriver();
                break;
            case safari:
                driver = new SafariDriver();
                break;

        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    public static void quitDriver(){
        driver.quit();
    }

}
