package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.Utils;

public class DownloadAndUploadPage extends Utils {

    @FindBy(id = "downloadButton")
    public WebElement downloadButton;

    @FindBy(id = "uploadFile")
    public WebElement uploadBox;

}
