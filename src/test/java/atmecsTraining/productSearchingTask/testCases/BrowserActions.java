package atmecsTraining.productSearchingTask.testCases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BrowserActions {
    public static WebDriver driver;

    String userWorkingDirectory = System.getProperty("user.dir");
    String pathSeparator = System.getProperty("file.separator");
    String testDataFileName = "testData.properties";
    String locatorsFileName = "locators.properties";
    Properties locatorsFile = getPropertiesObject(filePath(locatorsFileName));
    Properties testDataFile = getPropertiesObject(filePath(testDataFileName));

    /**
     * Method Description: Used to generate a file path using System Properties
     *
     * @param fileName Name of the file
     * @return Filepath as String
     */

    public String filePath(String fileName) {
        return userWorkingDirectory + pathSeparator + "src" + pathSeparator + "test" +
                pathSeparator + "java" + pathSeparator + "atmecsTraining" + pathSeparator +
                "productSearchingTask" + pathSeparator + "pages" + pathSeparator + fileName;
    }

    /**
     * Method Description: Used to create properties object to read the .properties file
     *
     * @param filePath Path of the file
     * @return Properties object
     */

    public Properties getPropertiesObject(String filePath) {
        Properties property = new Properties();
        try {
            FileInputStream file = new FileInputStream(filePath);
            property.load(file);
        } catch (FileNotFoundException exception) {
            System.out.println("The specified file is not present in the given path.");
        } catch (IOException exception) {
            System.out.println("Check the file in the specified path.");
        }
        return property;
    }

    /**
     * Method Description: Used to launch the specified browser and maximize the window
     */

    @BeforeSuite
    public void launchBrowser() {
        String browserName = testDataFile.getProperty("browser");
        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
    }

    /**
     * Method Description: Used to close all the browser windows
     */

    @AfterSuite
    public void closeBrowser() {
        driver.quit();
    }
}
