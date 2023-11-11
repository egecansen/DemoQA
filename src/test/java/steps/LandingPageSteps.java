package steps;

import io.cucumber.java.en.Given;
import pages.LandingPage;

public class LandingPageSteps {

    LandingPage landingPage = new LandingPage();

    @Given("Select the {} card on the landing page")
    public void clickCard(String labelText) {
        landingPage.clickElementUntil(landingPage.getElementFromList(labelText, landingPage.categories), true);
    }

}
