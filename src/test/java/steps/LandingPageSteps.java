package steps;

import io.cucumber.java.en.Given;
import pages.LandingPage;

public class LandingPageSteps {

    LandingPage landingPage = new LandingPage();

    @Given("Select the card named {} on the landing page")
    public void clickCard(String labelText) {
        landingPage.clickElementUntil(true, landingPage.getElementFromList(labelText, landingPage.categories));
    }

}
