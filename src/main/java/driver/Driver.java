package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Driver {

    public enum DriverType {
        chrome, safari, firefox
    }

    public static WebDriver driver;

    public static void setup(DriverType driverType) {
        Properties properties = new Properties();
        try {properties.load(new FileReader("src/test/resources/test.properties"));}
        catch (IOException notFoundException) {throw new RuntimeException("The Properties not exist!");}

        boolean headless = Boolean.parseBoolean(properties.getProperty("headless","false"));

        String downloadsDirectoryPath = new File("src/test/resources/files/downloads").getAbsolutePath();
        String downloadsDirectory = properties.getProperty("downloadsPath", downloadsDirectoryPath);

            switch (driverType) {
                case chrome:
                    ChromeOptions options = new ChromeOptions();
                    Map<String, Object> prefs = new HashMap<>();
                    if (headless)
                        options.addArguments("--headless=new");
                    prefs.put("download.default_directory", downloadsDirectory);
                    options.setExperimentalOption("prefs", prefs);

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

        public static void quitDriver() {
            driver.quit();

    }
}
