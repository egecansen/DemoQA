package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import utilities.Utils;
import java.util.List;

public class PracticeFormPage extends Utils {

    @FindBy (id = "firstName")
    public WebElement firstName;

    @FindBy (id = "lastName")
    public WebElement lastName;

    @FindBy (id = "userEmail")
    public WebElement userMail;

    @FindBy (id = "userNumber")
    public WebElement mobileNumber;

    @FindBy (css = "[for*='gender-radio']")
    public List<WebElement> genderButtons;

    @FindBy (id = "dateOfBirthInput")
    public WebElement datePicker;

    @FindBy (css = "[class='react-datepicker__month-select'] option")
    public List<WebElement> months;

    @FindBy (css = "[class='react-datepicker__year-select'] option")
    public List<WebElement> years;

    @FindBy (css = ".react-datepicker__month [role='option']")
    public List<WebElement> days;

    @FindBy (id = "subjectsInput")
    public WebElement subjectBox;

    @FindBy(css = "[id=\"subjectsContainer\"] #react-select-2-option-0")
    public WebElement subjectSpawnBox;

    @FindBy (css = "[for*='hobbies-checkbox']")
    public List<WebElement> hobbyButtons;

    @FindBy (id = "uploadPicture")
    public WebElement uploadPictureButton;

    @FindBy (id = "currentAddress")
    public WebElement addressBox;

    @FindBy (id = "react-select-3-input")
    public WebElement selectStateBox;

    @FindBy (id = "react-select-4-input")
    public WebElement selectCityBox;

    @FindBy (id = "submit")
    public WebElement submitButton;

    @FindBy (css = "[class=\"table-responsive\"] tr td +td")
    public List<WebElement> submittedUserInfo;


    public void dateOfBirth(String day, String month, String year) {
        scrollAndClick(datePicker);
        clickElementUntil(getMonth(month));
        clickElementUntil(getYear(year));
        clickElementUntil(getDay(day));
    }

    public WebElement getMonth(String monthName){
        for (WebElement month : months) if (month.getText().equals(monthName)) return month;
        throw new RuntimeException("Month not found");
    }

    public WebElement getYear(String yearName){
        for (WebElement year : years) if (year.getText().equals(yearName)) return year;
        throw new RuntimeException("Year not found");
    }

    public WebElement getDay(String dayName){
        for (WebElement day : days) {
            if (!day.getText().equals(dayName)) {
                dayName = dayName.replace("0", "");
            }
            if (day.getText().equals(dayName))
                return day;
        }
        throw new RuntimeException("Day not found");
    }
}