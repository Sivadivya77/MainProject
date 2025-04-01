package test;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethod;
import page.BookHotelPage;
import page.HomePage;
import page.SearchHotelPage;
import page.SelectHotelPage;

public class TC_007BookHotelTest extends ProjectSpecificationMethod{
	@BeforeTest
	public void setup() {
		sheetname="BookHotel";
	}
 
	
	
	@Test(dataProvider = "readdata")
	 public void verifyHotelBooking(String username, String password,String location, String firstName, String lastName, String address, String cardNumber,
	            String cardType, String expiryMonth, String expiryYear, String cvv, String expectedResult) throws InterruptedException {
		HomePage homepage=new HomePage(driver);
        homepage.Isusername(username)
                .Ispassword(password)
                .ClickLogin();
        SearchHotelPage searchHotelPage=new SearchHotelPage(driver);
        searchHotelPage.selectLocation(location);
        searchHotelPage.clickSearch();
        SelectHotelPage selecthotel=new SelectHotelPage(driver);
        selecthotel.selectFirstHotel();
        selecthotel.clickContinue();
		
		
		
		
		BookHotelPage bookHotelPage =new BookHotelPage(driver);
	        bookHotelPage.enterFirstName(firstName);
	        bookHotelPage.enterLastName(lastName);
	        bookHotelPage.enterBillingAddress(address);
	        bookHotelPage.enterCreditCardNumber(cardNumber);
	        bookHotelPage.selectCreditCardType(cardType);
	        
	        bookHotelPage.selectExpiryMonth(expiryMonth);
	        bookHotelPage.selectExpiryYear(expiryYear);
	        bookHotelPage.enterCVV(cvv);
	        bookHotelPage.clickBookNow();
	        Thread.sleep(2000);

	        if (expectedResult.equalsIgnoreCase("Success")) {
	            Assert.assertTrue(bookHotelPage.isBookingSuccessful(), "Please wait! We are processing your Hotel Booking...");
	        } else if (expectedResult.contains("Error")) {
	            switch (expectedResult) {
	                case "First Name Error":
	                    Assert.assertEquals(bookHotelPage.getFirstNameError(), "Please enter your First Name");
	                    break;
	                case "Last Name Error":
	                    Assert.assertEquals(bookHotelPage.getLastNameError(), "Please enter your Last Name");
	                    break;
	                case "Address Error":
	                    Assert.assertEquals(bookHotelPage.getAddressError(), "Please enter your Address");
	                    break;
	                case "Card Number Error":
	                    Assert.assertEquals(bookHotelPage.getCCNumberError(), "Please Enter your 16 Digit Credit Card Number");
	                    break;
	                case "Card Type Error":
	                    Assert.assertEquals(bookHotelPage.getCCTypeError(), "Please select your Credit Card Type");
	                    break;
	                case"Expiry Month Error":
	                	Assert.assertEquals(bookHotelPage.getExpiryDateError(), "Please Select your Credit Card Type");
	                case"Expiry Year Error":
	                	Assert.assertEquals(bookHotelPage.getExpiryDateError(), "Please Select your Credit Card Type");
	                case "Expiry Date Error":
	                    Assert.assertEquals(bookHotelPage.getExpiryDateError(), "Please select Expiry Date");
	                    break;
	                case "CVV Error":
	                    Assert.assertEquals(bookHotelPage.getCVVError(), "Please enter your CVV Number");
	                    break;
	                default:
	                    Assert.fail("Unexpected test case result: " + expectedResult);
	            }
	        }
	    }
	

}
