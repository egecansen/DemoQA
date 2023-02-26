package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.Utils;

public class BookStoreLoginPage extends Utils {

    @FindBy(id = "userName")
    public WebElement userNameBox;

    @FindBy(id = "password")
    public WebElement passwordBox;

    @FindBy(id = "login")
    public WebElement loginButton;

}
