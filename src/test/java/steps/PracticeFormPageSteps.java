package steps;

import io.cucumber.java.en.Given;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pages.PracticeFormPage;
import utilities.ContextStore;
import utils.FileUtilities;

import java.io.File;
import java.util.Map;

public class PracticeFormPageSteps {

    PracticeFormPage practiceForm = new PracticeFormPage();

    @Given("Submit the user info by following:")
    public void userInfo(DataTable table) {
        Map<String, String> infoMap = table.asMap();

        practiceForm.scrollAndSendKeys(practiceForm.firstName, infoMap.get("First Name"));
        practiceForm.scrollAndSendKeys(practiceForm.lastName, infoMap.get("Last Name"));
        ContextStore.put("Name", infoMap.get("First Name") + " " + infoMap.get("Last Name"));

        practiceForm.scrollAndSendKeys(practiceForm.userMail, infoMap.get("Mail"));
        ContextStore.put("Email", infoMap.get("Mail"));


        practiceForm.scrollAndSendKeys(practiceForm.mobileNumber, infoMap.get("Mobile Number"));
        ContextStore.put("Mobile Number", infoMap.get("Mobile Number"));
    }

    @Given("Gender: {}")
    public void submitGender(String gender) {
        practiceForm.clickElementUntil(practiceForm.getElementFromList(gender, practiceForm.genderButtons));
        ContextStore.put("Gender", gender);
    }

    @Given("Select the date of birth from following:")
    public void datePicker2(DataTable table) {
        Map<String, String> dateMap = table.asMap();
        practiceForm.dateOfBirth(dateMap.get("Dat"), dateMap.get("Month"), dateMap.get("Year"));

        ContextStore.put("Day", dateMap.get("Dat"));
        ContextStore.put("Month", dateMap.get("Month"));
        ContextStore.put("Year", dateMap.get("Year"));
        ContextStore.put("Date Of Birth", ContextStore.get("Day") + " " + ContextStore.get("Month") + "," + ContextStore.get("Year"));
    }
    @Given("Day: {} Month: {} Year: {}")
    public void datePicker(String day, String month, String year){
        practiceForm.dateOfBirth(day, month, year);

        ContextStore.put("Day", day);
        ContextStore.put("Month", month);
        ContextStore.put("Year", year);
        ContextStore.put("Date Of Birth", ContextStore.get("Day") + " " + ContextStore.get("Month") + "," + ContextStore.get("Year"));
    }
    @Given("Subject: {}")
    public void submitSubject(String subject) {
        practiceForm.scrollAndSendKeys(practiceForm.subjectBox, subject);
        practiceForm.clickElementUntil(practiceForm.subjectSpawnBox);
        ContextStore.put("Subjects", subject);
    }
    @Given("Hobby: {}")
    public void submitHobby(String hobby) {
        practiceForm.clickElementUntil(practiceForm.getElementFromList(hobby, practiceForm.hobbyButtons));
        ContextStore.put("Hobby", hobby);
    }
    @Given("Upload picture from: {}")
    public void uploadPicture(String relativePath) {
        FileUtilities fileUtilities = new FileUtilities();
        File file = new File(relativePath);
        String absolutePath = fileUtilities.getAbsolutePath(relativePath);
        practiceForm.scrollAndSendKeys(practiceForm.uploadPictureButton, absolutePath);
        ContextStore.put("Picture", file.getName());
    }
    @Given("Address: {}")
    public void submitAddress(String address) {
        practiceForm.log.new Info("Submitting the address info");
        practiceForm.scrollAndSendKeys(practiceForm.addressBox, address);
        ContextStore.put("Address", address);
    }
    @Given("State: {}")
    public void selectState(String state) {
        practiceForm.log.new Info("Selecting the state");
        practiceForm.scrollAndSendKeys(practiceForm.selectStateBox, state, Keys.ENTER);
        ContextStore.put("State", state);
    }
    @Given("City: {}")
    public void selectCity(String city) {
        practiceForm.log.new Info("Selecting the city");
        practiceForm.scrollAndSendKeys(practiceForm.selectCityBox, city, Keys.ENTER);
        ContextStore.put("City", city);
        ContextStore.put("State and City", ContextStore.get("State") + " " + ContextStore.get("City"));
    }

    @Given("Click Submit button")
    public void submit() {
        practiceForm.log.new Info("Submitting");
        practiceForm.scrollAndClick(practiceForm.submitButton);
    }

    @Given("Verify the submitted value of {} on the submission modal")
    public void verifyTheSubmittedInfo(String labelText) {
        practiceForm.verifyTextOfListedElement(ContextStore.get(labelText).toString(), practiceForm.submittedUserInfo);
        practiceForm.log.new Success(labelText + " is verified!");
    }
}