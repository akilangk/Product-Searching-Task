package atmecsTraining.productSearchingReUsables;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class VerificationReusables {
    public WebDriver driver;

    /**
     * Constructor Description: Used to initialize WebDriver variable
     *
     * @param driver WebDriver
     */

    public VerificationReusables(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Method Description: Used to verify the actual page title with the expected page title
     *
     * @param expectedTitle Expected page title
     */

    public void verifyPageTitle(String expectedTitle) {
        ActionReusables actionReusables = new ActionReusables(driver);
        String actualTitle = actionReusables.getPageTitle();
        Assert.assertEquals(actualTitle,expectedTitle,"Landing page title mismatch");
    }

   public boolean verifySearchResultsIsNonEmpty(List<WebElement> elementList){
        ActionReusables actionReusables =new ActionReusables(driver);
        return !actionReusables.isListEmpty(elementList);
   }
}
