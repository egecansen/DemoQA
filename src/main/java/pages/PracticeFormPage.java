package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import pages.components.DatePicker;
import pages.components.SubmissionRow;
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
    @FindBy (id = "subjectsInput")
    public WebElement subjectBox;

    @FindBy(css = "#subjectsContainer #react-select-2-option-0")
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

    @FindBy (id = "dateOfBirthInput")

    public WebElement dateOfBirthInput;
    @FindBy(css = ".react-datepicker__month-container")
    public DatePicker datePicker;

    @FindBy(css = ".modal-body tbody tr")
    public List<SubmissionRow> submissionRows;

    public SubmissionRow getSubmissionRow(String label) {
        for (SubmissionRow row: submissionRows) {
            if (row.getLabel().equals(label))
                return row;
        }
        throw new RuntimeException("Row not found!!");
    }

}