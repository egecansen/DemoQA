package steps;

import io.cucumber.java.en.Given;
import org.openqa.selenium.Keys;
import pages.SliderPage;


public class SliderSteps {
    SliderPage sliderPage = new SliderPage();
    @Given("Slide")
    public void slide() {
        int sliderWidth = sliderPage.sliderButton.getSize().getWidth();
        int sliderHeight = sliderPage.sliderButton.getSize().getHeight();
        int yCoord = sliderPage.sliderButton.getLocation().getY();
        int xCoord = sliderPage.sliderButton.getLocation().getX();
        sliderPage.log.new Info("Sliding");
        sliderPage.dragDropAction(sliderPage.sliderButton, (xCoord), yCoord);
    }
    @Given("Slide to {}")
    public void sendKeysToSlider(int count){
        count = count - 25;
        sliderPage.log.new Info("Navigating slide to %" + count);
        sliderPage.clickElementUntil(sliderPage.sliderButton);
    }
}
