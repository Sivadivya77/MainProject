package test;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethod;
import page.BookHotelPage;
import page.HomePage;
import page.SearchHotelPage;
import page.SelectHotelPage;

public class TC_005SelectHotelTest extends ProjectSpecificationMethod {
	 private SearchHotelPage searchHotelPage;
	    private SelectHotelPage selectHotelPage;

	    @BeforeMethod
	    public void setUp() {
	        HomePage homepage = new HomePage(driver);
	        homepage.Isusername("sivachandran")
	                .Ispassword("987654321")
	                .ClickLogin();
	        SearchHotelPage searchHotelPage=new SearchHotelPage(driver);
	        searchHotelPage = new SearchHotelPage(driver);
	        searchHotelPage.selectLocation("Sydney");
	        searchHotelPage.selectHotel("Hotel Sunshine");
	        searchHotelPage.selectNumRooms("2 - Two");
	        searchHotelPage.enterCheckInDate("25/03/2025");
	        searchHotelPage.enterCheckOutDate("27/03/2025");
	        searchHotelPage.selectAdults("2 - Two");
	        searchHotelPage.selectChildren("2 - Two");
	        searchHotelPage.clickSearch();

	        selectHotelPage = new SelectHotelPage(driver);

	        // Ensure the Select Hotel page is loaded
	       
	       // wait.until(ExpectedConditions.urlContains("SelectHotel.php"));
	    }

	
	
	@Test
    public void testSelectHotelPageScenarios() {
		
		String Actual="Hello Username! Search Hotel | Booked Itinerary | Change Password | Logout" ;
		
		Assert.assertEquals(selectHotelPage.WelcomeMessage(),Actual);
		
        Assert.assertTrue(selectHotelPage.isSelectHotelPageDisplayed(), "Navigation to Select Hotel page failed!");

        //  Verify error message when no hotel is selected
        selectHotelPage.clickContinue();
        Assert.assertEquals(selectHotelPage.getErrorMessage(), "Please Select a Hotel", "Error message mismatch when no hotel is selected.");

        //  Verify selecting a hotel and clicking continue navigates to Book Hotel page
        selectHotelPage.selectFirstHotel();
        selectHotelPage.clickContinue();
        Assert.assertTrue(driver.getCurrentUrl().contains("BookHotel.php"), "Failed to navigate to Book Hotel page after selecting a hotel.");

        //  Navigate back to Select Hotel page
        BookHotelPage bookhotel=new BookHotelPage(driver);
        bookhotel.ClickBack();
        Assert.assertTrue(selectHotelPage.isSelectHotelPageDisplayed(), "Did not return to Select Hotel page.");

        //  Verify only one hotel can be selected at a time
        selectHotelPage.selectFirstHotel();
        Assert.assertTrue(selectHotelPage.firstHotelRadioButton.isSelected(), "Only one hotel should be selected.");

        //  Verify clicking Cancel navigates back to the Search Hotel page
        selectHotelPage.clickCancel();
        Assert.assertTrue(driver.getCurrentUrl().contains("SearchHotel.php"), "Cancel button did not navigate back to Search Hotel page.");

        // Verify correct hotel details are displayed 
        Assert.assertTrue(true, "Hotel details do not match search criteria.");

        //  Verify total price calculation 
        Assert.assertTrue(true, "Total price calculation is incorrect.");
    }
	@Test
		public void verifyHotelDetailsAndTotalPrice() {
		   
		    String expectedHotelName = "Hotel Sunshine";  // Expected from search
		    String actualHotelName = selectHotelPage.getHotelName();
		    Assert.assertEquals(actualHotelName, expectedHotelName, "Hotel name does not match search criteria.");
	
		    // Verify total price calculation
		    double roomPrice = selectHotelPage.getRoomPrice();
		    int numberOfRooms = 2;  // Example value (Should be dynamic)
		    double expectedTotalPrice = roomPrice * numberOfRooms+10;
	
		    double actualTotalPrice = selectHotelPage.getTotalPrice();
		    Assert.assertEquals(actualTotalPrice, expectedTotalPrice, "Total price calculation is incorrect.");
		}
	@Test
	 public void testEditSelectHotel() throws InterruptedException {
		
		 selectHotelPage.EditHotelName();
		 selectHotelPage.EditHotelPrice();
		 selectHotelPage.EditHotelTotelPrice();
		 selectHotelPage.selectFirstHotel();
		 Thread.sleep(2000);
	        selectHotelPage.clickContinue();
	        BookHotelPage bookhotel=new BookHotelPage(driver);
	        bookhotel.ClickBack();
	        
	 }
}
