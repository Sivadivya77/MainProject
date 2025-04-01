package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import base.ProjectSpecificationMethod;

public class BookHotelPage extends ProjectSpecificationMethod {

	
	// First Name field
    @FindBy(id = "first_name")
    private WebElement firstNameField;

    // Last Name field
    @FindBy(id = "last_name")
    private WebElement lastNameField;

    // Billing Address field
    @FindBy(id = "address")
    private WebElement billingAddressField;

    // Credit Card Number field
    @FindBy(id = "cc_num")
    private WebElement creditCardNumberField;

    // Credit Card Type dropdown
    @FindBy(id = "cc_type")
    private WebElement creditCardTypeDropdown;

    // Expiry Month dropdown
    @FindBy(id = "cc_exp_month")
    private WebElement expiryMonthDropdown;

    // Expiry Year dropdown
    @FindBy(id = "cc_exp_year")
    private WebElement expiryYearDropdown;

    // CVV Number field
    @FindBy(id = "cc_cvv")
    private WebElement cvvField;

    // Book Now button
    @FindBy(id = "book_now")
    private WebElement bookNowButton;

    // Order Confirmation Number (Booking ID)
    @FindBy(id = "process_span")
    private WebElement Processing;

    // Error messages
    @FindBy(id = "first_name_span")
    private WebElement firstNameError;

    @FindBy(id = "last_name_span")
    private WebElement lastNameError;

    @FindBy(id = "address_span")
    private WebElement addressError;

    @FindBy(id = "cc_num_span")
    private WebElement ccNumberError;

    @FindBy(id = "cc_type_span")
    private WebElement ccTypeError;

    @FindBy(id = "cc_expiry_span")
    private WebElement expiryDateError;

    @FindBy(id = "cc_cvv_span")
    private WebElement cvvError;
    
  @FindBy(xpath = "//a[normalize-space()='Back']")
  private WebElement ClickBackButton;
  
    public BookHotelPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
     
    public void ClickBack() {
    	ClickBackButton.click();
    }

    // Methods to fill in booking details
    public void enterFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNameField.sendKeys(lastName);
    }

    public void enterBillingAddress(String address) {
        billingAddressField.sendKeys(address);
    }

    public void enterCreditCardNumber(String cardNumber) {
        creditCardNumberField.sendKeys(cardNumber);
    }

    public void selectCreditCardType(String cardType) {
    	creditCardTypeDropdown.sendKeys(cardType);
    }

    public void selectExpiryMonth(String month) {
        expiryMonthDropdown.sendKeys(month);
    }

    public void selectExpiryYear(String year) {
        expiryYearDropdown.sendKeys(year);
    }

    public void enterCVV(String cvv) {
        cvvField.sendKeys(cvv);
    }

    public void clickBookNow() {
        bookNowButton.click();
    }

    // Method to check if booking was successful
    public boolean isBookingSuccessful() {
        return Processing.isDisplayed();
    }

    // Get order confirmation number
    public String getOrderNumber() {
        return Processing.getAttribute("value");
    }

    // Methods to fetch error messages
    public String getFirstNameError() {
        return firstNameError.getText();
    }

    public String getLastNameError() {
        return lastNameError.getText();
    }

    public String getAddressError() {
        return addressError.getText();
    }

    public String getCCNumberError() {
        return ccNumberError.getText();
    }

    public String getCCTypeError() {
        return ccTypeError.getText();
    }

    public String getExpiryDateError() {
        return expiryDateError.getText();
    }

    public String getCVVError() {
        return cvvError.getText();
    }
}

