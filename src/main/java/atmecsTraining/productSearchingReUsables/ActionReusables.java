package atmecsTraining.productSearchingReUsables;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class ActionReusables {
    public WebDriver driver;

    /**
     * Constructor Description: Used to initialize the WebDriver variable
     *
     * @param driver WebDriver
     */

    public ActionReusables(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Method Description: Used to open the specified url in the browser window
     *
     * @param url Uniform Resource Locator of the webpage
     */


    public void openUrl(String url) {
        driver.get(url);
    }

    /**
     * Method Description: Used to check if the element is present or not.
     *
     * @param element WebElement
     * @return true (if element is present) or false (if element is not present)
     */

    public boolean isElementPresent(WebElement element) {
        return element.isDisplayed();
    }

    /**
     * Method Description: Used to get the title of the current web page
     *
     * @return Page title in String
     */

    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Method Description: Used to enter text content in editable fields
     *
     * @param element WebElement of the editable field
     * @param text    Text to be entered in the given field
     */

    public void sendText(WebElement element, String text) {
        Assert.assertTrue(isElementPresent(element), "Element is not present in the webpage to send text.");
        element.sendKeys(text);
    }

    /**
     * Method Description: Used to perform click operation
     */

    public void click(WebElement element) {
        Assert.assertTrue(isElementPresent(element), "Element is not present in the webpage to perform click action.");
        element.click();
    }

    /**
     * Method Description: Used to perform click operation using JavaScript Executor.
     *
     * @param element WebElement of the field
     */

    public void clickUsingJavaScriptExecutor(WebElement element) {
        Assert.assertTrue(isElementPresent(element), "Element is not present in the webpage to perform click action.");
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    /**
     * Method Description: Used to get the text from the given WebElement
     *
     * @param element WebElement of the field
     * @return Text present in the WebElement as String
     */

    public String getTextFromElement(WebElement element) {
        Assert.assertTrue(isElementPresent(element), "Element is not present in the webpage to get the text.");
        return element.getText();
    }

    /**
     * Method Description: Used to find WebElement using id locator
     *
     * @param id locator to find the WebElement
     * @return WebElement of the given locator
     */

    public WebElement findElementById(String id) {
        return driver.findElement(By.id(id));
    }

    public WebElement findElementByName(String name) {
        return driver.findElement(By.name(name));
    }

    public WebElement findElementByCssSelector(String cssSelector){
        return driver.findElement(By.cssSelector(cssSelector));
    }

    public WebElement findElementByTagName(String tagName){
        return driver.findElement(By.tagName(tagName));
    }

    public List<WebElement> findElementsByXpath(String xPath){
        return driver.findElements(By.xpath(xPath));
    }

    public boolean isListEmpty(List<WebElement> elementList){
        return elementList.isEmpty();
    }
}
