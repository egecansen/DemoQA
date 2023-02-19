package steps;

import driver.Driver;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.SliderPage;

import java.time.Duration;


public class SliderSteps {
    SliderPage sliderPage = new SliderPage();

    @Given("Slide the slider to {}")
    public void sliderInteraction(int value){
        WebElement slider = sliderPage.sliderButton;
        int initialValue = Integer.parseInt(slider.getAttribute("value"));
        int sliderLength = slider.getSize().width;
        int slideDistance = (value - initialValue) * sliderLength / 100;
        int initialX = (sliderLength * initialValue)/100;
        int initialXOffset = initialX - sliderLength/2;

        Actions action = new Actions(Driver.driver);
        action.moveToElement(slider, initialXOffset, 0)
                .clickAndHold()
                .moveByOffset((slideDistance),0)
                .release()
                .pause(Duration.ofSeconds(1))
                .build()
                .perform();

        int finalValue = Integer.parseInt(slider.getAttribute("value"));
        Assert.assertEquals("Slider value is incorrect!", value, finalValue);
        sliderPage.log.new Success("Final value is verified to be: " + finalValue);
    }

}
