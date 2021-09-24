import ReusableLibrary.Abstract_Class;
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
        try {
            driver.navigate().to("http://127.0.0.1:8080");
            logger.log(LogStatus.PASS, "Successfully navigated to server!");
        } catch (Exception e) {
            logger.log(LogStatus.FAIL, "Failed to navigate to server!" + e);
        }
        Response Resp = given()
                .when()
                .get();

        if (Resp.statusCode() == 200) {
            logger.log(LogStatus.PASS, "Status code is 200 and successful.");
        } else {
            logger.log(LogStatus.FAIL, "Status code is not successful: " + Resp.statusCode());
        }
    }

    @Test(priority = 2)
    public void chooseAndUploadImageUI() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            WebElement imageFile = driver.findElement(By.cssSelector("input[type='file']"));
            wait.until(ExpectedConditions.visibilityOf(imageFile)).sendKeys("C:\\Users\\sharu\\IdeaProjects\\Mediafly Project\\src\\main\\resources\\51661_anime_scenery.jpg");
            logger.log(LogStatus.PASS, "Successfully chose an image to upload.");
        } catch (Exception e) {
            logger.log(LogStatus.FAIL, "Could not select image to upload." + e);
        }
        try {
            WebElement clickUpload = driver.findElement(By.cssSelector("input[type='submit']"));
            wait.until(ExpectedConditions.visibilityOf(clickUpload)).click();
            logger.log(LogStatus.PASS, "Successfully uploaded an image.");
        } catch (Exception e) {
            logger.log(LogStatus.FAIL, "Could not upload image." + e);
        }
    }

    @Test (priority = 3)
    public void uploadNonImageFile() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            WebElement nonImageFile = driver.findElement(By.cssSelector("input[type='file']"));
            wait.until(ExpectedConditions.visibilityOf(nonImageFile)).sendKeys("C:\\Users\\sharu\\IdeaProjects\\Mediafly Project\\src\\main\\resources\\nonimagetextfile.txt");
            logger.log(LogStatus.FAIL, "Chose a non-image file to upload. This is a bug.");
        } catch (Exception e) {
            logger.log(LogStatus.PASS, "Could not select a non-image file to upload." + e);
        }
        try {
            WebElement clickUpload = driver.findElement(By.cssSelector("input[type='submit']"));
            wait.until(ExpectedConditions.visibilityOf(clickUpload)).click();
            logger.log(LogStatus.FAIL, "Uploaded a non-image file. This is a bug.");
        } catch (Exception e) {
            logger.log(LogStatus.PASS, "Could not upload a non-image file." + e);
        }
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
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Thread.sleep(5000);
        driver.navigate().refresh();
        try {
            WebElement clickOriginalUIimage = driver.findElement(By.cssSelector("a[href='/51661_anime_scenery.jpg']"));
            wait.until(ExpectedConditions.visibilityOf(clickOriginalUIimage)).click();
            logger.log(LogStatus.PASS, "Was able to view original image uploaded via UI.");
        } catch (Exception e) {
            logger.log(LogStatus.FAIL, "Was not able to view original image uploaded via UI." + e);
        }
        Thread.sleep(2500);
        driver.navigate().back();
        try {
            WebElement clickProcessedUIimage = driver.findElement(By.cssSelector("a[href='/processed-51661_anime_scenery.jpg']"));
            wait.until(ExpectedConditions.visibilityOf(clickProcessedUIimage)).click();
            logger.log(LogStatus.PASS, "Was able to view processed image of UI upload.");
        } catch (Exception e) {
            logger.log(LogStatus.FAIL, "Was not able to view processed image of UI upload.");
        }
        Thread.sleep(2500);
        driver.navigate().back();
        try {
            WebElement clickOriginalAPIimage = driver.findElement(By.cssSelector("a[href='/705445.jpg']"));
            wait.until(ExpectedConditions.visibilityOf(clickOriginalAPIimage)).click();
            logger.log(LogStatus.PASS, "Was able to view original image of API post.");
        } catch (Exception e) {
            logger.log(LogStatus.FAIL, "Was not able to view original image of API post.");
        }
        Thread.sleep(2500);
        driver.navigate().back();
        try {
            WebElement clickProcessedAPIimage = driver.findElement(By.cssSelector("a[href='/processed-705445.jpg']"));
            wait.until(ExpectedConditions.visibilityOf(clickProcessedAPIimage)).click();
            logger.log(LogStatus.PASS, "Was able to view processed image of API post.");
        } catch (Exception e) {
            logger.log(LogStatus.FAIL, "Was not able to view processed image of API post.");
        }
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





