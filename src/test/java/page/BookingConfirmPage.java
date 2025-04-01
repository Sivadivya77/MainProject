package page;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.ProjectSpecificationMethod;

public class BookingConfirmPage extends ProjectSpecificationMethod{
	
	@FindBy(xpath = "//td[contains(text(),'Booking Confirmation')]")
    private WebElement bookingConfirmationMessage;

    @FindBy(id = "order_no")
    private WebElement orderId;

    @FindBy(id = "hotel_name")
    private WebElement hotelName;

    @FindBy(id = "location")
    private WebElement location;

    @FindBy(id = "room_type")
    private WebElement roomType;

    @FindBy(id = "total_price")
    private WebElement totalPrice;

    @FindBy(xpath = "//a[text()='Logout']")
    private WebElement logoutBtn;
    
    @FindBy(id = "my_itinerary")
    private WebElement myItineraryButton;

    @FindBy(id = "search_hotel")
    private WebElement searchHotelButton;
    
    @FindBy(xpath = "//td[@class='reg_success']")
    private WebElement LogoutConfimationMessage;
    
    

    public BookingConfirmPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Verify if booking confirmation page is displayed
    public boolean isBookingConfirmed() {
        return bookingConfirmationMessage.isDisplayed();
    }

    // Get the Order ID dynamically
    public String getOrderId() {
        return orderId.getAttribute("value");
    }

    // Get all booking details
    public Map<String, String> getBookingDetails() {
        Map<String, String> bookingDetails = new HashMap<>();
        try {
            // Fetching and storing booking details
            bookingDetails.put("Hotel Name", hotelName.isDisplayed() ? hotelName.getText().trim() : "Not Found");
            bookingDetails.put("Location", location.isDisplayed() ? location.getText().trim() : "Not Found");
            bookingDetails.put("Room Type", roomType.isDisplayed() ? roomType.getText().trim() : "Not Found");
            bookingDetails.put("Total Price", totalPrice.isDisplayed() ? totalPrice.getText().trim() : "Not Found");
        } catch (Exception e) {
            System.out.println("Error fetching booking details: " + e.getMessage());
        }
        return bookingDetails;
    
    }
    
 // Check if "My Itinerary" button is enabled
    public boolean isMyItineraryButtonEnabled() {
        return myItineraryButton.isEnabled();
    }
  
    // Check if "Search Hotel" button is enabled
    public boolean isSearchHotelButtonEnabled() {
        return searchHotelButton.isEnabled();
    }

    // Logout
    public void clickLogout() {
        logoutBtn.click();
    }
    
    public String LogoutConfirmation() {
    	return LogoutConfimationMessage.getText();
    	}
}


