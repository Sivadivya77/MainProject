package test;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethod;
import page.BookHotelPage;
import page.BookingConfirmPage;
import page.HomePage;
import page.SearchHotelPage;
import page.SelectHotelPage;
import util.Utility;

public class TC_008BookIngConfirmTest extends ProjectSpecificationMethod {
	
	@Test
	 public void verifyBookingConfirmation() throws IOException {
		 
		 
		 HomePage homepage=new HomePage(driver)	;			 
	        homepage.Isusername("sivachandran")
	                .Ispassword("987654321")
	                .ClickLogin();
	     //  Perform a valid search for a hotel
		 SearchHotelPage searchHotelPage=new SearchHotelPage(driver);
		 searchHotelPage.selectLocation("Sydney");
		 searchHotelPage.clickSearch();
		 SelectHotelPage selecthotel=new SelectHotelPage(driver);
	        selecthotel.selectFirstHotel();
	        selecthotel.clickContinue();
	        
	        BookHotelPage bookHotelPage =new BookHotelPage(driver);
	        bookHotelPage.enterFirstName("siva");
	        bookHotelPage.enterLastName("Leo");
	        bookHotelPage.enterBillingAddress("MRG");
	        bookHotelPage.enterCreditCardNumber("1234567890123456");
	        bookHotelPage.selectCreditCardType("VISA");
	        
	        bookHotelPage.selectExpiryMonth("March");
	        bookHotelPage.selectExpiryYear("2025");
	        bookHotelPage.enterCVV("123");
	        bookHotelPage.clickBookNow();
		 
	        BookingConfirmPage bookingConfirmPage = new BookingConfirmPage(driver);
	        
	        // Verify confirmation page is displayed
	        Assert.assertTrue(bookingConfirmPage.isBookingConfirmed(), "Booking confirmation failed!");

	        
	        // Verify "My Itinerary" button is enabled
	        Assert.assertTrue(bookingConfirmPage.isMyItineraryButtonEnabled(), 
	                "My Itinerary button is not enabled!");

	        // Verify "Search Hotel" button is enabled
	        Assert.assertTrue(bookingConfirmPage.isSearchHotelButtonEnabled(), 
	                "Search Hotel button is not enabled!");

	        System.out.println("My Itinerary and Search Hotel buttons are enabled.");

	       
	        // Capture and print booking details
	        Map<String, String> bookingDetails = bookingConfirmPage.getBookingDetails();

	        // Verify that details are present
	        Assert.assertNotEquals(bookingDetails.get("Hotel Name"), "Not Found", "Hotel Name is missing!");
	        Assert.assertNotEquals(bookingDetails.get("Location"), "Not Found", "Location is missing!");
	        Assert.assertNotEquals(bookingDetails.get("Room Type"), "Not Found", "Room Type is missing!");
	        Assert.assertNotEquals(bookingDetails.get("Total Price"), "Not Found", "Total Price is missing!");

	        System.out.println(" Booking details verified successfully!");
	        // Get and store Order ID dynamically
	        String orderId = bookingConfirmPage.getOrderId();
	        System.out.println("Captured Order ID: " + orderId);
	        
	        
	        
	        
	     // Logout after booking
	        bookingConfirmPage.clickLogout();
	        String actualResult = "You have successfully logged out. Click here to login again";
	        Assert.assertEquals(bookingConfirmPage.LogoutConfirmation(), actualResult, 
	            "Logout confirmation message does not match!");
	        
	        
	    }
	       
	       
	       

	        
	    }
	
	

    
