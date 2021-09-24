package ReusableLibrary;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class Abstract_Class {

    public static WebDriver driver;
    public static ExtentReports reports;
    public static ExtentTest logger;

    @BeforeSuite
    public void setDriver(){
        driver = Reusable_Actions.defineTheDriver();
        reports = new ExtentReports("src/main/java/HTML_Reports/Automation_Report.html");
    }

    @BeforeMethod
    public void captureTestName(Method methodName){
        logger = reports.startTest(methodName.getName());
    }

    @AfterMethod
    public void endLogger(){
        reports.endTest(logger);
    }

    @AfterSuite
    public void closeDriver(){
        reports.flush();
        driver.quit();
    }
}

