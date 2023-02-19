package steps;

import io.cucumber.java.en.Given;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LandingPage;

public class LandingPageSteps {

    LandingPage landingPage = new LandingPage();

    @Given("Select the card named {} on the landing page")
    public void clickCard(String labelText) throws InterruptedException {
        //Setting window size for unreachable elements.
        landingPage.clickElementUntil(landingPage.getElementFromList(labelText, landingPage.categories));
    }
}
