package page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.ProjectSpecificationMethod;

public class BookedItineraryPage extends ProjectSpecificationMethod {
	
	 // Page header element 
    @FindBy(xpath = "//td[@class='login_title']")
    private WebElement pageHeader;
    
    // Booking ID search field (assume the id is order_id_text)
    @FindBy(id = "order_id_text")
    private WebElement bookingIdField;
    
    // Search button for booking ID
    @FindBy(id = "search_hotel_id")
    private WebElement searchButton;
    
    // Cancel Selected button
    @FindBy(xpath  = "//input[@name='cancelall']")
    private WebElement cancelSelectedButton;
    
    // Table listing all bookings
    @FindBy(xpath  = "//td[@align='right']//table")
    private WebElement bookedTable;
    
    // Error message element 
    @FindBy(xpath = "//label[@id='search_result_error']")
    private WebElement errorMessage;
    
    // Other header buttons
    @FindBy(xpath = "//a[text()='Search Hotel']")
    private WebElement searchHotelBtn;
    
    @FindBy(xpath = "//a[text()='Booked Itinerary']")
    private WebElement bookedItineraryBtn;
    
    @FindBy(xpath = "//a[text()='Change Password']")
    private WebElement changePasswordBtn;
    
    @FindBy(xpath = "//a[text()='Logout']")
    private WebElement logoutBtn;
    
    public BookedItineraryPage(WebDriver driver) {
      //  this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    // Check if the Booked Itinerary page is displayed by verifying the header text
    public boolean isPageDisplayed() {
        return pageHeader.isDisplayed();
    }
    
    // Verify that header buttons are clickable 
    public void verifyHeaderButtonsAreClickable() {
        if(!searchHotelBtn.isDisplayed() || !searchHotelBtn.isEnabled()) {
            throw new RuntimeException("Search Hotel button not clickable");
        }
        if(!bookedItineraryBtn.isDisplayed() || !bookedItineraryBtn.isEnabled()) {
            throw new RuntimeException("Booked Itinerary button not clickable");
        }
        if(!changePasswordBtn.isDisplayed() || !changePasswordBtn.isEnabled()) {
            throw new RuntimeException("Change Password button not clickable");
        }
        if(!logoutBtn.isDisplayed() || !logoutBtn.isEnabled()) {
            throw new RuntimeException("Logout button not clickable");
        }
    }
    
    // Search for a booking using booking ID
    public void searchBooking(String bookingId) {
       
        bookingIdField.sendKeys(bookingId);
      
    }
    public void ClicksearchButton() {
    	  searchButton.click();
    }
    
    // Returns the error message 
    public String getErrorMessage() {
        try {
            return errorMessage.getText();
        } catch(Exception e) {
            return "";
        }
    }
    
    // Cancel selected bookings (Assuming you need to check checkboxes first)
    public void cancelSelectedBooking() {
        // Here, we assume that each row in the table has a checkbox with name "check_all"
        List<WebElement> checkboxes = driver.findElements(By.name("check_all"));
        if (checkboxes.size() == 0) {
            throw new RuntimeException("No booking checkboxes found!");
        }
        // For example, select the first booking
        checkboxes.get(0).click();
        cancelSelectedButton.click();
        
        // Handle the confirmation alert if it appears
        driver.switchTo().alert().accept();
    }
    
    // Get the list of booking rows
    public List<WebElement> getBookingRows() {
        return bookedTable.findElements(By.tagName("tr"));
    }
}


