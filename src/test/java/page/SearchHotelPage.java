package page;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.ProjectSpecificationMethod;

public class SearchHotelPage extends ProjectSpecificationMethod {
	
	  @FindBy(id = "username_show")private WebElement Welcomemsg;
	 @FindBy(id = "location") private WebElement locationDropdown;
	    @FindBy(id = "hotels") private WebElement hotelsDropdown;
	    @FindBy(id = "room_type") private WebElement roomTypeDropdown;
	    @FindBy(id = "room_nos") private WebElement numRoomsDropdown;
	    @FindBy(id = "datepick_in") private WebElement checkInDate;
	    @FindBy(id = "datepick_out") private WebElement checkOutDate;
	    @FindBy(id = "adult_room") private WebElement adultsDropdown;
	    @FindBy(id = "child_room") private WebElement childrenDropdown;
	    @FindBy(id = "Submit") private WebElement searchButton;
	    @FindBy(id = "Reset") private WebElement resetButton;
	    @FindBy(css ="a[href='BookedItinerary.php']")private WebElement ClickBookedItinerary;
	    
	    
	    @FindBy(xpath = "//span[@id='location_span']")
	    private WebElement locationError;

	    @FindBy(xpath = "//span[@id='num_room_span']")
	    private WebElement roomsError;

	    @FindBy(xpath = "//span[@id='checkin_span']")
	    private WebElement checkInError;

	    @FindBy(id = "checkout_span")
	    private WebElement checkOutError;

	    @FindBy(xpath = "//span[@id='adults_room_span']")
	    private WebElement adultsError;
         
	    @FindBy(id = "select_hotel_form")
	    private WebElement selectHotelPage;
        
	    @FindBy(xpath = "//a[text()='Search Hotel']")
	    private WebElement searchHotelBtn;

	    @FindBy(xpath = "//a[text()='Booked Itinerary']")
	    private WebElement bookedItineraryBtn;

	    @FindBy(xpath = "//a[text()='Change Password']")
	    private WebElement changePasswordBtn;

	    @FindBy(xpath = "//a[text()='Logout']")
	    private WebElement logoutBtn;
	    
	    @FindBy(xpath = "//a[normalize-space()='Change Password']")
	    private WebElement ClickChangePassword;
	    @FindBy(id="username_show")
	    private WebElement Showusername;

	    // Constructor
	    public SearchHotelPage(WebDriver driver) {
	        this.driver = driver;
	      
	        PageFactory.initElements(driver, this);
	    }
	    
	    public String ShowUsername() {
	    	return Showusername.getText();
	    }
	    
	    public void ClickChangePasswordButton() {
	    	ClickChangePassword.click();
	    }

	    // Methods to interact with elements
	    public void selectLocation(String location) {
	        new Select(locationDropdown).selectByVisibleText(location);
	    }

	    public void selectHotel(String hotel) {
	        new Select(hotelsDropdown).selectByVisibleText(hotel);
	    }

	    public void selectRoomType(String roomType) {
	        new Select(roomTypeDropdown).selectByVisibleText(roomType);
	    }

	    public void selectNumRooms(String numRooms) {
	        new Select(numRoomsDropdown).selectByVisibleText(numRooms);
	    }

	    public void enterCheckInDate(String date) {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].value='';", checkInDate); // Clears the field
	        checkInDate.sendKeys(date);
	    }

	    // Method to clear and enter check-out date
	    public void enterCheckOutDate(String date) {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].value='';", checkOutDate); // Clears the field
	        checkOutDate.sendKeys(date);
	    }
	    public void selectAdults(String adults) {
	        new Select(adultsDropdown).selectByVisibleText(adults);
	    }

	    public void selectChildren(String children) {
	        new Select(childrenDropdown).selectByVisibleText(children);
	    }

	    public void clickSearch() {
	        searchButton.click();
	    }

	    public void clickReset() {
	        resetButton.click();
	    }
	    public void ClickBookedItinerarybutton() {
	    	ClickBookedItinerary.click();
	    	
	    }
	    public String getErrorMessage() {
	        if (locationError.isDisplayed()) return locationError.getText();
	        if (roomsError.isDisplayed()) return roomsError.getText();
	        if (checkInError.isDisplayed()) return checkInError.getText();
	        if (checkOutError.isDisplayed()) return checkOutError.getText();
	        if (adultsError.isDisplayed()) return adultsError.getText();
	        return "";
	    }
	    public boolean isSelectHotelPageDisplayed() {
	        return selectHotelPage.isDisplayed();
	    }
	    public void verifyButtonsAreClickable() {
	        Assert.assertTrue(searchHotelBtn.isDisplayed() && searchHotelBtn.isEnabled(), "Search Hotel button is NOT clickable");
	        Assert.assertTrue(bookedItineraryBtn.isDisplayed() && bookedItineraryBtn.isEnabled(), "Booked Itinerary button is NOT clickable");
	        Assert.assertTrue(changePasswordBtn.isDisplayed() && changePasswordBtn.isEnabled(), "Change Password button is NOT clickable");
	        Assert.assertTrue(logoutBtn.isDisplayed() && logoutBtn.isEnabled(), "Logout button is NOT clickable");

	}

}
