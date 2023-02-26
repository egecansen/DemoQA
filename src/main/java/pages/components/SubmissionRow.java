package pages.components;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.Utils;

public class SubmissionRow extends Utils {

    @FindBy(css = "td:first-of-type")
    public WebElement label;

    @FindBy(css = "td:last-of-type")
    public WebElement value;

    public String getLabel() {
        return label.getText();
    }

    public String getValue() {
        return value.getText();
    }

}
