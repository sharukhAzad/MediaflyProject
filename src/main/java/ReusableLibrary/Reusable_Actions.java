package ReusableLibrary;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

public class Reusable_Actions {

    public static WebDriver defineTheDriver() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        WebDriver driver = new ChromeDriver(options);

        return driver;
    }

    public static void clickOnElement(WebDriver driver, WebElement xpathLocator, ExtentTest logger, String elementName) {

        WebDriverWait wait = new WebDriverWait(driver, 10);

        try {
            wait.until(ExpectedConditions.visibilityOf(xpathLocator)).click();
            logger.log(LogStatus.PASS, "Successfully clicked on element " + elementName);
        } catch (Exception e) {
            logger.log(LogStatus.FAIL, "Unable to click on element " + elementName + " " + e);
            System.out.println("Unable to click element " + elementName + " " + e);
        }
    }

    public static void sendKeysMethod(WebDriver driver, WebElement xpathLocator, String userValue, ExtentTest logger, String elementName){

        WebDriverWait wait = new WebDriverWait(driver,10);

        try{
            WebElement element = wait.until(ExpectedConditions.visibilityOf(xpathLocator));
            element.sendKeys(userValue);
            logger.log(LogStatus.PASS,"Successfully selected " + elementName);
        } catch (Exception e) {
            logger.log(LogStatus.FAIL,"Unable to select " + elementName + " " + e);
            System.out.println("Unable to enter value on element " + elementName + " " + e);
        }
    }


}
