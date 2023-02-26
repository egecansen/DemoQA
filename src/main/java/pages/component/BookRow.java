package pages.component;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.Utils;

public class BookRow extends Utils {
    @FindBy(css = ".rt-td")
    public WebElement bookRow;

    public String getTitle() {
        return bookRow.getText();
    }

    public void clickTitle() {

    }
}
