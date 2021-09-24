import ReusableLibrary.Abstract_Class;
import ReusableLibrary.Reusable_Actions;
import com.relevantcodes.extentreports.LogStatus;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import static io.restassured.RestAssured.given;


public class HiringProject_TestScript extends Abstract_Class {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "http://127.0.0.1:8080/";
    }

    @Test(priority = 1)
    public void getStatusCodeForApplicationServer() {
            driver.navigate().to("http://127.0.0.1:8080");

        Response Resp = given()
                .when()
                .get();

        if (Resp.statusCode() == 200) {
            logger.log(LogStatus.PASS, "Able to navigate to application server. Status code is 200 and successful.");
        } else {
            logger.log(LogStatus.FAIL, "Unable to navigate to application server. Status code is not successful: " + Resp.statusCode());
        }
    }

    @Test(priority = 2)
    public void chooseAndUploadImageUI() {
        WebElement imageFile = driver.findElement(By.cssSelector("input[type='file']"));
        WebElement clickUpload = driver.findElement(By.cssSelector("input[type='submit']"));

        Reusable_Actions.sendKeysMethod(driver, imageFile, "C:\\Users\\sharu\\IdeaProjects\\Mediafly Project\\src\\main\\resources\\51661_anime_scenery.jpg", logger, "UI Image File");
        Reusable_Actions.clickOnElement(driver, clickUpload, logger, "Upload File Button");
    }

    @Test (priority = 3)
    public void uploadNonImageFile() throws InterruptedException {
        WebElement nonImageFile = driver.findElement(By.cssSelector("input[type='file']"));
        WebElement clickUpload = driver.findElement(By.cssSelector("input[type='submit']"));

        Reusable_Actions.sendKeysMethod(driver, nonImageFile, "C:\\Users\\sharu\\IdeaProjects\\Mediafly Project\\src\\main\\resources\\nonimagetextfile.txt", logger, "Non-image File");
        Reusable_Actions.clickOnElement(driver, clickUpload, logger, "Upload File Button");
    }

    @Test (priority = 4)
    public void postImageDirectlyAPI() {
        File testImage = new File("src/main/resources/705445.jpg");
        Response Resp = given()
                .multiPart(testImage)
                .when()
                .post();

        if (Resp.statusCode() == 302) {
            logger.log(LogStatus.PASS, "Status code is 302 and successful. Image was posted via API.");
        } else {
            logger.log(LogStatus.FAIL, "Status code is not successful: " + Resp.statusCode());
        }
    }

    @Test (priority = 5)
    public void viewImages() throws InterruptedException {
        Thread.sleep(5000);
        driver.navigate().refresh();

        WebElement clickOriginalUIimage = driver.findElement(By.cssSelector("a[href='/51661_anime_scenery.jpg']"));
        Reusable_Actions.clickOnElement(driver, clickOriginalUIimage, logger, "Original UI Image");

        Thread.sleep(2500);
        driver.navigate().back();

        WebElement clickProcessedUIimage = driver.findElement(By.cssSelector("a[href='/processed-51661_anime_scenery.jpg']"));
        Reusable_Actions.clickOnElement(driver, clickProcessedUIimage, logger, "Processed UI Image");

        Thread.sleep(2500);
        driver.navigate().back();

        WebElement clickOriginalAPIimage = driver.findElement(By.cssSelector("a[href='/705445.jpg']"));
        Reusable_Actions.clickOnElement(driver, clickOriginalAPIimage, logger, "Original API Image");

        Thread.sleep(2500);
        driver.navigate().back();

        WebElement clickProcessedAPIimage = driver.findElement(By.cssSelector("a[href='/processed-705445.jpg']"));
        Reusable_Actions.clickOnElement(driver, clickProcessedAPIimage, logger, "Processed UI Image");
        Thread.sleep(2500);
    }

    @Test (priority = 6)
    public void getImageResponse() {
        Response Resp = given()
                .when()
                .get("/images");

        ResponseBody body = Resp.getBody();
        logger.log(LogStatus.INFO, "Response Body is: " + body.asString());

        if (Resp.statusCode() == 200) {
            logger.log(LogStatus.PASS, "Status code is 200 and successful.");
        } else {
            logger.log(LogStatus.FAIL, "Status code is not successful: " + Resp.statusCode());
        }
    }


}





