package pages.components;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.Utils;

import java.util.List;

public class BookRow extends Utils {

    private int titleIndex;

    @FindBy(css = ".rt-td")
    public List<Cell> cells;

    public String getTitle(int titleIndex) {
        this.titleIndex = titleIndex;
        return cells.get(titleIndex).getText();
    }
    public void selectRow() {
        clickElementUntil(cells.get(titleIndex).innerContent);
    }

    public static class Cell extends Utils {
        @FindBy(css = ".mr-2")
        public WebElement innerContent;
    }

}
