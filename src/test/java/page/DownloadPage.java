package page;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.ProjectSpecificationMethod;

public class DownloadPage extends ProjectSpecificationMethod {
	
	// Locators using @FindBy
    @FindBy(xpath = "//a[contains(text(),'DOWNLOAD')]")
    private WebElement hotelAppLink;

    @FindBy(xpath = "//a[normalize-space()='Click']")
    private WebElement webServiceLink;

    @FindBy(xpath = "//a[contains(text(),'DOWNLOAD')][contains(@href,'http://adactinhotelapp.com/resources/Sample-TestCases_HotelApplication.pdf')]")
    private WebElement sampleTestCasesLink;

    @FindBy(xpath = "//a[@href='http://adactinhotelapp.com/resources/KnownDefects_HotelApp.pdf']")
    private WebElement knownDefectsLink;

    @FindBy(xpath = "//h4[normalize-space()='Book on Automation']")
    private WebElement bookOnAutomationLink;

    // Constructor
    public DownloadPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to click Hotel App Download Link
    public void clickHotelAppLink() {
        Assert.assertTrue(hotelAppLink.isDisplayed(), "Hotel App Download link is not visible");
        Assert.assertTrue(hotelAppLink.isEnabled(), "Hotel App Download link is not clickable");
        hotelAppLink.click();
    }

    // Method to click Web Services Link
    public void clickWebServiceLink() {
        Assert.assertTrue(webServiceLink.isDisplayed(), "Web Services link is not visible");
        Assert.assertTrue(webServiceLink.isEnabled(), "Web Services link is not clickable");
        webServiceLink.click();
    }

    // Method to click Sample Test Cases Download Link
    public void clickSampleTestCasesLink() {
        Assert.assertTrue(sampleTestCasesLink.isDisplayed(), "Sample Test Cases link is not visible");
        Assert.assertTrue(sampleTestCasesLink.isEnabled(), "Sample Test Cases link is not clickable");
        sampleTestCasesLink.click();
    }

    // Method to click Known Defects Download Link
    public void clickKnownDefectsLink() {
        Assert.assertTrue(knownDefectsLink.isDisplayed(), "Known Defects link is not visible");
        Assert.assertTrue(knownDefectsLink.isEnabled(), "Known Defects link is not clickable");
        knownDefectsLink.click();
    }

    // Method to click Book on Automation Link
    public void clickBookOnAutomationLink() {
        Assert.assertTrue(bookOnAutomationLink.isDisplayed(), "Book on Automation link is not visible");
        Assert.assertTrue(bookOnAutomationLink.isEnabled(), "Book on Automation link is not clickable");
      //  bookOnAutomationLink.click();
    }
  /*  public void validateFileDownload(String expectedFileName, String expectedFileUrlPart) throws IOException {
        // Validate if browser navigated to expected URL
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        boolean isNavigated = wait.until(ExpectedConditions.urlContains(expectedFileUrlPart));
        Assert.assertTrue(isNavigated, "File URL does not match expected.");

        // Define file path
        String downloadPath = "C:\\Users\\siva\\Downloads\\adaHotel";
        File file = new File(downloadPath, expectedFileName);

        // Wait for the file to be downloaded
        int waitTime = 10; // seconds
        while (waitTime > 0) {
            if (file.exists() && Files.size(Paths.get(file.getAbsolutePath())) > 0) {
                Assert.assertTrue(true, "File is downloaded successfully: " + expectedFileName);
                return;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Preserve interrupt status
            }
            waitTime--;
        }

        Assert.fail("File was not downloaded: " + expectedFileName);
    }*/
}

