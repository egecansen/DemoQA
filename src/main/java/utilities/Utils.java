package utilities;

import com.github.webdriverextensions.WebComponent;
import com.github.webdriverextensions.WebDriverExtensionFieldDecorator;
import driver.Driver;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Printer;

import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.awt.Color.*;
import static org.apache.commons.net.smtp.SMTPCommand.RESET;


public abstract class Utils extends WebComponent {

    public final Printer log = new Printer(this.getClass());
    public int counter;

    public Utils() {
        PageFactory.initElements(new WebDriverExtensionFieldDecorator(Driver.driver), this);
    }

    public void waitFor(double seconds){
        if (seconds > 1) log.info("Waiting for " + seconds + " seconds");
        try {Thread.sleep((long) (seconds* 1000L));}
        catch (InterruptedException exception){Assert.fail(exception.getLocalizedMessage()+RESET);}
    }

    public void scroll(WebElement element) {
        log.info("Scrolling to element");
        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollAndSendKeys(WebElement element, CharSequence... text) {
        scrollTowardCenter(element);
        log.info("Submitting the given info");
        element.sendKeys(text);
    }

    @Deprecated
    public void scrollAndClick(WebElement element) {
        log.info("Scrolling toward element");
        scroll(element);
        //clickElementUntil(element);
    }

    public void clickAndDeleteAll(WebElement element) {
        clickElementUntil( element);
        log.info("Deleting text");
        element.sendKeys(Keys.COMMAND + "a", Keys.DELETE);
    }

    public void clickAllElements(List<WebElement> elements) {
        for (WebElement element : elements) {
            clickElementUntil(element);
        }
    }

    public void clickAtAnOffset(WebElement element, int xOffset, int yOffset, boolean scroll){

        if (scroll) centerElement(element);

        Actions builder = new org.openqa.selenium.interactions.Actions(Driver.driver);
        builder
                .moveToElement(element, xOffset, yOffset)
                .click()
                .build()
                .perform();
    }

    public WebElement centerElement(WebElement element){
        String scrollScript = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";

        ((JavascriptExecutor) Driver.driver).executeScript(scrollScript, element);

        waitFor(0.3);
        return element;
    }


    public void dragDropToAction(WebElement element, WebElement destinationElement){

        centerElement(element);

        Actions action = new Actions(Driver.driver);
        action.moveToElement(element)
                .clickAndHold(element)
                .moveToElement(destinationElement)
                .release()
                .build()
                .perform();
        waitFor(0.3);
    }

    //This method performs click, hold, dragAndDropBy action on at a certain offset
    public void dragDropByAction(WebElement element, int xOffset, int yOffset){

        centerElement(element);

        Actions action = new Actions(Driver.driver);
        action.moveToElement(element)
                .clickAndHold(element)
                .dragAndDropBy(element, xOffset, yOffset)
                .build()
                .perform();
        waitFor(0.3);
    }

    //This method performs click, hold, drag and drop action on at a certain offset
    public void dragDropAction(WebElement element, int xOffset, int yOffset){

        centerElement(element);

        Actions action = new Actions(Driver.driver);
        action.moveToElement(element)
                .clickAndHold(element)
                .moveToElement(element,xOffset,yOffset)
                .release()
                .build()
                .perform();
        waitFor(0.3);
    }

    public void scrollTowardCenter(WebElement element) {
        log.info("Scrolling toward elements center");
        String scrollElementIntoMiddle = "var viewPortHeight = " +
                "Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        ((JavascriptExecutor) Driver.driver).executeScript(scrollElementIntoMiddle, element);
    }

    public void waitUntilDisplayed(WebElement element) {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(Driver.driver);

        fluentWait.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);

        try {
            log.info("Waiting the element to be displayed");
            fluentWait.until(webDriver -> element.isDisplayed());
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred");

        }
    }

    public void waitUntilListToBeDisplayed(List<WebElement> elements) {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(Driver.driver);

        fluentWait.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);

        try {
            log.info("Waiting the element list to be displayed");
            fluentWait.until(ExpectedConditions.visibilityOfAllElements(elements));
        } catch (StaleElementReferenceException e) {
            throw new RuntimeException("Exception occurred: ", e);
        }
    }

    public void waitUntilStable(WebElement element) {
        FluentWait<WebDriver> fluentWait = new FluentWait<>(Driver.driver);

        log.info("Waiting " + element.getText() + " to be stable");
        final long startTime = System.currentTimeMillis();

        fluentWait.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);

        fluentWait.until(ExpectedConditions.stalenessOf(element));

        long endTime = System.currentTimeMillis();
        int totalTime = (int) (endTime - startTime);
        log.info("Waited for " + totalTime + " second(s)");
    }

    public void waitAndClickReCaptcha() {
        WebDriverWait wait = new WebDriverWait(Driver.driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(
                By.xpath("//iframe[starts-with(@name, 'a-') " +
                        "and starts-with(@src, 'https://www.google.com/recaptcha')]")));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.recaptcha-checkbox-border"))).click();
    }

    public void addCookies(Cookie... cookies){
        log.info("Adding specified cookies");
        for (Cookie cookie: cookies)
            Driver.driver.manage().addCookie(cookie);
    }

    public File getLastModified(String directoryFilePath) {
        File directory = new File(directoryFilePath);
        File[] files = directory.listFiles(File::isFile);
        long lastModifiedTime = Long.MIN_VALUE;
        File chosenFile = null;

        if (files != null) {
            for (File file : files) {
                if (file.lastModified() > lastModifiedTime) {
                    chosenFile = file;
                    lastModifiedTime = file.lastModified();
                }
            }
        }
        return chosenFile;
    }

    public void waitUntilDownloaded(String directory, String fileName) {
        long initialTime = System.currentTimeMillis();
        boolean timeOut;
        log.info("Waiting the files existence");

            do {
                timeOut = System.currentTimeMillis() - initialTime > 300000;
                waitFor(3);
            }
            while(!timeOut && !FileUtils.listFiles(new File(directory), new String[]{fileName}, false).isEmpty());
            if (timeOut) log.new Warning("Download time out!");
    }

    public void cleanDirectory(String directoryFilePath) {
        log.info("Deleting all items from the given directory");
        try {
            FileUtils.cleanDirectory(new File(directoryFilePath));
        }
        catch (IOException e) {e.getMessage();}
    }

    public void clickWithJS(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor)Driver.driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void clickElementUntil(WebElement element, Boolean scroll) {
        final long startTime = System.currentTimeMillis();
        boolean isClicked = false;
        int attemptCounter = 0;
        int duration = 30000;

        if (scroll)
            scroll(element);
        log.info("Clicking the element named: " + element.getText());
        while (System.currentTimeMillis() - startTime < duration) {
            try {
                element.click();
                isClicked = true;
                break;
            } catch (WebDriverException e) {
                attemptCounter++;
            }
        }
        if (!isClicked) {
            throw new RuntimeException("Could not click the element after " + attemptCounter + " attempts");
        }
    }

    public void clickElementUntil(WebElement element) {
        final long startTime = System.currentTimeMillis();
        boolean isClicked = false;
        int attemptCounter = 0;
        int duration = 30000;

        log.info("Clicking the element named: " + element.getText());
        while (System.currentTimeMillis() - startTime < duration) {
            try {
                element.click();
                isClicked = true;
                break;
            } catch (WebDriverException e) {
                attemptCounter++;
            }
        }
        if (!isClicked) {
            throw new RuntimeException("Could not click the element after " + attemptCounter + " attempts");
        }
    }

    public void moveToElement(WebElement target) {
        Actions actions = new Actions(Driver.driver);
        actions.moveToElement(target).perform();
    }

    public void moveToElementAndClick(WebElement element) {
        Actions actions = new Actions(Driver.driver);
        actions.moveToElement(element);
        actions.perform();
        clickElementUntil(element);
    }

    public void verifyImgStatus(WebElement imgElement) {
        try {
            RequestConfig customizedRequestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.IGNORE_COOKIES).build();
            HttpClientBuilder customizedClientBuilder = HttpClients.custom().setDefaultRequestConfig(customizedRequestConfig);
            CloseableHttpClient client = customizedClientBuilder.build();
            HttpGet request = new HttpGet(imgElement.getAttribute("src"));
            HttpResponse response = client.execute(request);
            String imgLink = imgElement.getAttribute("src");
            log.info("Image Link: " + imgLink);
            log.info("Status: " + response.getStatusLine());
            // print name
            //WebElement followingSibling = imgElement.findElement(By.xpath("following-sibling::*"));
            //log.info("Image name: " + followingSibling.getText());

            if (response.getStatusLine().getStatusCode() < 200) counter++;
            else if (response.getStatusLine().getStatusCode() > 226) counter++;
        } catch (Exception e) {
            throw new RuntimeException("An exception occurred\n" + e.getMessage());
        }
    }

    public void verifyLinkElements(List<WebElement> linkElements) {
        for (WebElement linkElement : linkElements) {
            if(linkElement.getAttribute("href") != null) {
                try {
                    String webLink = linkElement.getAttribute("href");
                    log.info("Link: " + webLink);

                    RequestConfig customizedRequestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.IGNORE_COOKIES).build();
                    HttpClientBuilder customizedClientBuilder = HttpClients.custom().setDefaultRequestConfig(customizedRequestConfig);
                    CloseableHttpClient client = customizedClientBuilder.build();
                    HttpGet request = new HttpGet(webLink);
                    HttpResponse response = client.execute(request);
                    log.info("Status: " + response.getStatusLine());

                    if (response.getStatusLine().getStatusCode() >= 400) counter++;
                } catch (WebDriverException | IOException e) {
                    log.new Warning(e.getMessage());
                }
            }
        }
    }

    public void saveLinkInfoOnTextFile(String fileName, List<WebElement> linkElements) {
        for (WebElement linkElement : linkElements) {
            if(linkElement.getText().length() > 1 && linkElement.getAttribute("href") != null) {
                try {
                    String webLink = linkElement.getAttribute("href");
                    String elementText = linkElement.getText();
                    log.info("Element name: " + elementText);
                    log.info("Link: " + webLink);

                    RequestConfig customizedRequestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.IGNORE_COOKIES).build();
                    HttpClientBuilder customizedClientBuilder = HttpClients.custom().setDefaultRequestConfig(customizedRequestConfig);
                    CloseableHttpClient client = customizedClientBuilder.build();
                    HttpGet request = new HttpGet(linkElement.getAttribute("href"));
                    HttpResponse response = client.execute(request);
                    log.new Success("Status: " + response.getStatusLine());

                    if (response.getStatusLine().getStatusCode() >= 400) counter++;

                    saveTextToTextFile(fileName, "Element name: " + elementText
                            + "\n" + "Status: " + response.getStatusLine()
                            + "\n" + "Links: " + webLink + "\n");

                } catch (WebDriverException | IOException e) {
                    log.new Warning(e.getMessage());
                }
            }
        }
    }

    public String turkishCharReplacer(String text) {
        return text.replace("ı", "i")
                .replace("ö", "o")
                .replace("ö", "o")
                .replace("ş", "s")
                .replace("ğ", "g")
                .replace("ç", "c")
                .replace("Ü", "U")
                .replace("İ", "I")
                .replace("Ö", "O")
                .replace("Ü", "U")
                .replace("Ş", "S")
                .replace("Ğ", "G")
                .replace("Ç", "C");
    }

    public void tabSwitcher(int tabNumber) {
        TestStore.put("parentHandle", Driver.driver.getWindowHandle());
        List<String> browserTabs = new ArrayList<>(Driver.driver.getWindowHandles());
        log.info(String.valueOf(browserTabs.size()));
        Driver.driver.switchTo().window(browserTabs.get(tabNumber));
        log.info("Tab focus switched");
    }

    public String switchWindowByIndex(Integer expectedNumberOfWindows, Integer tabIndex) {
        new WebDriverWait(Driver.driver, Duration.ofSeconds(5000)).until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows));
        log.info("Switching the tab with the window index: " + tabIndex);
        String parentWindowHandle = Driver.driver.getWindowHandle();
        List<String> handles = new ArrayList<>(Driver.driver.getWindowHandles());
        String handle = handles.get(tabIndex);
        Driver.driver = (ChromeDriver) Driver.driver.switchTo().window(handle);
        return parentWindowHandle;
    }

    public void windowSwitcher() {
        log.info("Changing window focus");
        TestStore.put("parentWindow", Driver.driver.getWindowHandle());
        String currentWindow = Driver.driver.getWindowHandle();
        Set<String> handles = Driver.driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(currentWindow)) {
                Driver.driver.switchTo().window(handle);
                break;
            }
        }
        log.info("Window focus changed");
    }

    public void twoTabSwitcher() {
        log.info("Changing window focus");
        new WebDriverWait(Driver.driver, Duration.ofSeconds(5000)).until(ExpectedConditions.numberOfWindowsToBe(2));
        TestStore.put("parentWindow", Driver.driver.getWindowHandle());
        String currentWindow = Driver.driver.getWindowHandle();
        Set<String> handles = Driver.driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(currentWindow)) {
                Driver.driver.switchTo().window(handle);
                break;
            }
        }
        log.info("Window focus changed");
    }

    public void verifyTextOfListedElement(String labelText, List<WebElement> elements) {
        Assert.assertEquals("Text of the listed element could not be verified", labelText, getElementFromList(labelText, elements).getText());
    }

    public WebElement getElementFromList(String labelText, List<WebElement> elements) {
        log.info("Getting '" + labelText + "' from the given list");
        for (WebElement element : elements){
            if (element.getText().equalsIgnoreCase(labelText)) return element;}
        throw new RuntimeException("Element not found!!");
    }

    public WebElement getElementFromListUntil(String labelText, List<WebElement> elements) {
        final long startTime = System.currentTimeMillis();
        int duration = 30000;
        log.info("Getting '" + labelText + "' from the given list");
        while (System.currentTimeMillis() - startTime < duration){
            for (WebElement element : elements)
                if (element.getText().equalsIgnoreCase(labelText)){
                    return element;
                }
        }
        throw new RuntimeException("Element not found!!");
    }

    public WebElement getElementFromElements(List<WebElement> elements) {
        try {
            for (WebElement element : elements)
                return element;
        }
        catch (WebDriverException e){
            log.new Warning(e.getMessage());
        }
        return null;
    }

    public void saveLinksToTextFile(String fileName, List<WebElement> elements) {
        log.info("Saving links to a text file named: " + fileName);
        File f = new File(fileName + ".txt");
        if (f.exists()) f.delete();
        try {
            FileWriter fw = new FileWriter(f, true);
            for (WebElement element : elements) {
                String linkText = element.getAttribute("href");
                //write text to file
                fw.write(linkText + "\n");
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.info("Error!" + e.getMessage());
        }
    }

    public List<String> readLinksOnBrowser(List<WebElement> elements) {
        List<String> linkList = new ArrayList<>();
        for (WebElement element : elements){
            log.info(element.getAttribute("href"));
            linkList.add(element.getAttribute("href"));
        }
        return linkList;

    }

    public List<String> readTextFromFile(String fileName) {
        List<String> result = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName + ".txt"));

            String line;
            while ((line = br.readLine()) != null) {
                result.add(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void saveTextToTextFile(String fileName, String text) {
        log.info("Saving text on a text file named: " + fileName);
        File f = new File("src/test/resources/results/" + fileName + ".txt");
        try {
            FileWriter fw = new FileWriter(f, true);
            fw.write(text + "\n");
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            log.info("Error!" + e.getMessage());
        }
    }

    public void setWindowSize(int width, int height) {
        log.info("Changing the window size to " + width + "/" + height);
        Driver.driver.manage().window().setSize(new Dimension(width, height));
    }

    public void maximizeWindowSize(){
        Driver.driver.manage().window().maximize();
    }

}
