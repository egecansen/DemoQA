package steps;


import io.cucumber.java.en.Given;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.Keys;
import pages.PracticeFormPage;
import utilities.TestStore;
import utils.FileUtilities;
import java.io.File;
import java.util.Map;

public class PracticeFormPageSteps {

    PracticeFormPage practiceForm = new PracticeFormPage();

    @Given("Set window size to {}, {}")
    public void setWindowSize(int width, int height) {
        practiceForm.setWindowSize(width,height);
    }

    @Given("Submit the user info by following:")
    public void userInfo(DataTable table) {
        Map<String, String> userMap = table.asMap();

        practiceForm.scrollAndSendKeys(practiceForm.firstName, userMap.get("First Name"));
        practiceForm.scrollAndSendKeys(practiceForm.lastName, userMap.get("Last Name"));
        TestStore.put("Student Name", userMap.get("First Name") + " " + userMap.get("Last Name"));

        practiceForm.scrollAndSendKeys(practiceForm.userMail, userMap.get("Mail"));
        TestStore.put("Student Email", userMap.get("Mail"));

        practiceForm.scrollAndSendKeys(practiceForm.mobileNumber, userMap.get("Mobile Number"));
        TestStore.put("Mobile", userMap.get("Mobile Number"));
    }

    @Given("Gender: {}")
    public void submitGender(String gender) {
        practiceForm.clickElementUntil(practiceForm.getElementFromList(gender, practiceForm.genderButtons), true);
        TestStore.put("Gender", gender);
    }

    @Given("Day: {} Month: {} Year: {}")
    public void datePicker2(String day, String month, String year){
        practiceForm.clickElementUntil(practiceForm.dateOfBirthInput, true);
        practiceForm.clickElementUntil(practiceForm.datePicker.getMonth(month));
        practiceForm.clickElementUntil(practiceForm.datePicker.getYear(year));
        practiceForm.clickElementUntil(practiceForm.datePicker.getDay(day));
        TestStore.put("Day", day);
        TestStore.put("Month", month);
        TestStore.put("Year", year);
        TestStore.put("Date of Birth", TestStore.get("Day") + " " + TestStore.get("Month") + "," + TestStore.get("Year"));
    }

    @Given("Subject: {}")
    public void submitSubject(String subject) {
        practiceForm.scrollAndSendKeys(practiceForm.subjectBox, subject);
        practiceForm.clickElementUntil(practiceForm.subjectSpawnBox);
        TestStore.put("Subjects", subject);
    }

    @Given("Hobby: {}")
    public void submitHobby(String hobby) {
        practiceForm.clickElementUntil(practiceForm.getElementFromList(hobby, practiceForm.hobbyButtons));
        TestStore.put("Hobbies", hobby);
    }

    @Given("Upload picture from: {}")
    public void uploadPicture(String relativePath) {
        FileUtilities fileUtilities = new FileUtilities();
        File file = new File(relativePath);
        String absolutePath = fileUtilities.getAbsolutePath(relativePath);
        practiceForm.scrollAndSendKeys(practiceForm.uploadPictureButton, absolutePath);
        TestStore.put("Picture", file.getName());
    }

    @Given("Address: {}")
    public void submitAddress(String address) {
        practiceForm.log.new Info("Submitting the address info");
        practiceForm.scrollAndSendKeys(practiceForm.addressBox, address);
        TestStore.put("Address", address);
    }

    @Given("State: {}")
    public void selectState(String state) {
        practiceForm.log.new Info("Selecting the state");
        practiceForm.scrollAndSendKeys(practiceForm.selectStateBox, state, Keys.ENTER);
        TestStore.put("State", state);
    }

    @Given("City: {}")
    public void selectCity(String city) {
        practiceForm.log.new Info("Selecting the city");
        practiceForm.scrollAndSendKeys(practiceForm.selectCityBox, city, Keys.ENTER);
        TestStore.put("City", city);
        TestStore.put("State and City", TestStore.get("State") + " " + TestStore.get("City"));
    }

    @Given("Click Submit button")
    public void submit() {
        practiceForm.log.new Info("Submitting");
        practiceForm.clickElementUntil(practiceForm.submitButton, true);
    }

    @Given("Verify the submitted value of {} on the submission modal")
    public void verifyTheSubmittedInfo(String labelText) {
        String expectedValue = TestStore.get(labelText).toString();
        String actualValue = practiceForm.getSubmissionRow(labelText).getValue();
        Assert.assertEquals("Text of the listed element could not be verified", expectedValue, actualValue);
        practiceForm.log.new Success(labelText + " is verified!");
    }

}