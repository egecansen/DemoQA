package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.Utils;

public class SliderPage extends Utils {

    @FindBy(id = "sliderValue")
    public WebElement sliderValue;
    @FindBy(css = ".range-slider__wrap input")
    public WebElement sliderButton;
}
