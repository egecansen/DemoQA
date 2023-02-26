package steps;

import io.cucumber.java.en.Given;
import pages.ToolsPage;

public class ToolsPageSteps {

    ToolsPage toolsPage = new ToolsPage();

    @Given("Select {} from the selected card menu")
    public void clickItem(String toolText) {
        toolsPage.clickElementUntil(true, toolsPage.getElementFromList(toolText, toolsPage.tools));
    }
}
