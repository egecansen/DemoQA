package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.Utils;

import java.util.List;

public class ToolsPage extends Utils {
    @FindBy(css = "[class='btn btn-light ']")
    public List<WebElement> tools;

}
