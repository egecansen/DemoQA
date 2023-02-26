package pages.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.Utils;

public class BookDetailRow extends Utils {

    @FindBy(css = "[class='col-md-3 col-sm-12']")
    public WebElement label;

    @FindBy(css = "[class='col-md-9 col-sm-12']")
    public WebElement value;

    public String getLabel(){return label.getText();}

    public String getValue(){return value.getText();}
}