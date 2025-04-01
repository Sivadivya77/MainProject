package page;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethod;

public class SelectHotelPage extends ProjectSpecificationMethod {
	
	 public SelectHotelPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

	    // Web Elements using @FindBy
	    @FindBy(xpath = "//input[@id='radiobutton_1']")
		public WebElement firstHotelRadioButton;

	    @FindBy(xpath = "//input[@id='continue']")
	    private WebElement continueButton;

	    @FindBy(xpath = "//input[@id='cancel']")
	    private WebElement cancelButton;

	    @FindBy(xpath = "//label[@id='radiobutton_span']")
	    private WebElement errorMessage;
	    
	    @FindBy(xpath = "//input[@id='hotel_name_1']") 
	    private WebElement hotelName;  // Fetch hotel name

	    @FindBy(xpath = "//input[@id='price_night_1']") 
	    private WebElement roomPrice;  // Fetch price per night

	    @FindBy(xpath = "//input[@id='total_price_1']") 
	    private WebElement totalPrice; // Fetch total price
	    
	    @FindBy(id="hotel_name_1")
	    private WebElement EditHotelname;
	    
	    @FindBy(id="price_night_1")
	    private WebElement EditPrice;
	    
	    @FindBy(id="total_price_1")
	    private WebElement EditTotelPrice;
	    
	    @FindBy(xpath = "//td[contains(text(),'Hello Username!')]")
	    private WebElement WelcomeMsg;
	    


	    // Actions
	   

	    public void selectFirstHotel() {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(firstHotelRadioButton)).click();
	    }

	    public void clickContinue() {
	        continueButton.click();
	    }
	    
	    public String WelcomeMessage() {
	    	return WelcomeMsg.getText();
	    	
	    }

	    public void clickCancel() {
	        cancelButton.click();
	    }

	    public String getErrorMessage() {
	        return errorMessage.getText();
	    }

	    public boolean isSelectHotelPageDisplayed() {
	        return driver.getCurrentUrl().contains("SelectHotel.php");
	    }
	    public void EditHotelTotelPrice() {
	    	EditTotelPrice.clear();
	    }
	    
	    public void EditHotelName() {
	    	EditHotelname.clear();
	    }
	    public void EditHotelPrice() {
	    	EditPrice.clear();
	    }
	    
	    
	    // Method to get the hotel name from the UI
	    public String getHotelName() {
	        return hotelName.getAttribute("value").trim();
	    }

	    public double getRoomPrice() {
	        String priceText = roomPrice.getAttribute("value").trim(); // Get the text
	        priceText = priceText.replaceAll("[^0-9.]", ""); // Remove non-numeric characters
	        return Double.parseDouble(priceText); // Convert to double
	    }

	    public double getTotalPrice() {
	        String totalText = totalPrice.getAttribute("value").trim(); // Get the text
	        totalText = totalText.replaceAll("[^0-9.]", ""); // Remove non-numeric characters
	        return Double.parseDouble(totalText); // Convert to double
	    }
	}


