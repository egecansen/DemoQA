package pages.component;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.Utils;

import java.util.List;

public class DatePicker extends Utils {

    @FindBy(css = ".react-datepicker__year-select option")
    public List<WebElement> years;

    @FindBy(css = ".react-datepicker__month-select option")
    public List<WebElement> months;

    @FindBy(css = "[role='option']")
    public List<WebElement> days;

    public WebElement getYear(String yearInput) {
        return getElementFromList(yearInput, years);
    }

    public WebElement getMonth(String monthInput) {
        return getElementFromList(monthInput, months);
    }

    public WebElement getDay(String dayInput) {
       if (dayInput.startsWith("0")) dayInput = dayInput.replace("0", "");
       return getElementFromList(dayInput,days);
    }

}
