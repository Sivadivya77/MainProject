package test;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethod;
import page.HomePage;
import page.SearchHotelPage;

public class TC_004SearchHotelTest extends ProjectSpecificationMethod{
	
	@BeforeTest
	public void setup() {
		sheetname="SearchHotel";
	}
	
	  @Test
	    public void verifyButtonsClickabilityTest() {
		  HomePage homepage=new HomePage(driver)	;			 
	        homepage.Isusername("sivachandran")
	                .Ispassword("987654321")
	                .ClickLogin();
		  SearchHotelPage searchHotelPage=new SearchHotelPage(driver);
	        searchHotelPage.verifyButtonsAreClickable();
	    }
	
	 @Test(dataProvider = "readdata")
	    public void searchHotelTest(String Username,String Password,String Scenario,String location, String hotel, String roomType, String numRooms,
	                                String checkInDate, String checkOutDate, String adults, String children,String expectedError) throws InterruptedException {
		 HomePage homepage=new HomePage(driver)	;			 
	        homepage.Isusername(Username)
	                .Ispassword(Password)
	                .ClickLogin();
		 SearchHotelPage searchHotelPage=new SearchHotelPage(driver);
		 if (!location.isEmpty()) searchHotelPage.selectLocation(location);
		    if (!hotel.isEmpty()) searchHotelPage.selectHotel(hotel);
		    if (!roomType.isEmpty()) searchHotelPage.selectRoomType(roomType);
		    if (!numRooms.isEmpty()) searchHotelPage.selectNumRooms(numRooms);
		    if (!checkInDate.isEmpty()) searchHotelPage.enterCheckInDate(checkInDate);
		    if (!checkOutDate.isEmpty()) searchHotelPage.enterCheckOutDate(checkOutDate);
		    if (!adults.isEmpty()) searchHotelPage.selectAdults(adults);
		    if (!children.isEmpty()) searchHotelPage.selectChildren(children);
             Thread.sleep(1000);
		    searchHotelPage.clickSearch();

		    if (expectedError.equals("SUCCESS")) {
		        String expectedURL = "https://adactinhotelapp.com/SelectHotel.php"; // Replace with the actual next page URL
		        String actualURL = driver.getCurrentUrl();
		        Assert.assertEquals(actualURL, expectedURL, "Navigation to the Select Hotel page failed!");
		    } else {
		        Assert.assertEquals(searchHotelPage.getErrorMessage(), expectedError, "Error message mismatch!");
		    }

	 }
}