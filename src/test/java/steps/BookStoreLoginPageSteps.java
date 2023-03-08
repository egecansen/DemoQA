package steps;

import io.cucumber.java.en.Given;
import pages.BookStoreLoginPage;
import utilities.TestStore;

public class BookStoreLoginPageSteps {

    BookStoreLoginPage loginPage = new BookStoreLoginPage();

    @Given("Submit with the user in context")
    public void submit() {
        loginPage.log.new Info("Submitting...");
        loginPage.userNameBox.sendKeys(TestStore.get("userName").toString());
        loginPage.passwordBox.sendKeys(TestStore.get("password").toString());
        loginPage.clickElementUntil(false, loginPage.loginButton);
    }

}
