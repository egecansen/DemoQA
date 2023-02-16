package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.Utils;
import java.util.List;

public class LandingPage extends Utils {
    @FindBy(css = "[class='card mt-4 top-card']")
    public List<WebElement> categories;

    public void clickCard(String label){
        scroll(getCard(label));
        getCard(label).click();
    }

    public WebElement getCard(String label){
        for (WebElement card : categories)
            if (card.getText().equals(label))
                return card;
        throw new RuntimeException("Card not found");
    }
}
