package test;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethod;
import page.BookedItineraryPage;
import page.HomePage;
import page.SearchHotelPage;

public class TC_006BookedltineraryTest extends ProjectSpecificationMethod {

	private HomePage homepage;
	private BookedItineraryPage bookedItineraryPage;

	@BeforeMethod
	public void navigateToBookedItinerary() {
		homepage = new HomePage(driver);
		bookedItineraryPage = new BookedItineraryPage(driver);
		homepage.Isusername("sivachandran").Ispassword("987654321").ClickLogin();

	}

	@Test(priority = 1)
	public void verifyNavigationToBookedItineraryPage() {

		SearchHotelPage searchhotel = new SearchHotelPage(driver);
		searchhotel.ClickBookedItinerarybutton();
		Assert.assertTrue(bookedItineraryPage.isPageDisplayed(), "Booked Itinerary page is not displayed.");
	}

	@Test(priority = 2)
	public void verifyHeaderButtonsClickability() {

		SearchHotelPage searchhotel = new SearchHotelPage(driver);
		searchhotel.ClickBookedItinerarybutton();
		bookedItineraryPage.verifyHeaderButtonsAreClickable();
	}

	@Test(priority = 3)
	public void verifySearchBookingWithValidBookingID() {

		SearchHotelPage searchhotel = new SearchHotelPage(driver);
		searchhotel.ClickBookedItinerarybutton();
		bookedItineraryPage.searchBooking("U9XI440KP4");
		// Wait until at least one row is present in the table
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@align='right']//table")));
		int bookingCount = bookedItineraryPage.getBookingRows().size();
		System.out.println("After Selected Hotel:" + bookedItineraryPage.getBookingRows().size());
		Assert.assertTrue(bookingCount > 0, "No bookings found for a valid booking ID.");

	}

	@Test(priority = 4)
	public void verifySearchBookingWithInvalidBookingID() {

		SearchHotelPage searchhotel = new SearchHotelPage(driver);
		searchhotel.ClickBookedItinerarybutton();
		bookedItineraryPage.searchBooking("9LAR959TQS");
		bookedItineraryPage.ClicksearchButton();
		String error = bookedItineraryPage.getErrorMessage();
		Assert.assertTrue(error.contains("0 result(s) found.") || error.contains("not found"),
				"Error message not displayed for non-existing booking.");
	}

	@Test(priority = 5)
	public void verifyCancelSelectedBookingWithoutSelectingAny() {

		SearchHotelPage searchhotel = new SearchHotelPage(driver);
		searchhotel.ClickBookedItinerarybutton();

		bookedItineraryPage.cancelSelectedBooking();

		// Wait for the error message to appear
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_result_error")));

		// Validate the expected error message
		// String actualErrorText = errorMsg.getText();
		// System.out.println(errorMsg.getText());
		// Assert.assertTrue(actualErrorText.contains("Please select a booking to
		// cancel"),
		// "Expected error message not displayed.");
	}

	@Test(priority = 6)
	public void verifyCancelSelectedBookingSuccessfully() {
		
		SearchHotelPage searchhotel = new SearchHotelPage(driver);
		searchhotel.ClickBookedItinerarybutton();
		try {
			bookedItineraryPage.cancelSelectedBooking();
			int bookingCountAfter = bookedItineraryPage.getBookingRows().size();
			Assert.assertTrue(bookingCountAfter < 5, "Booking was not cancelled successfully.");
		} catch (Exception e) {
			Assert.fail("Cancellation failed: " + e.getMessage());
		}
	}

	@Test(priority = 7)
	public void verifyUnauthorizedAccess() {
		
		SearchHotelPage searchhotel = new SearchHotelPage(driver);
		searchhotel.ClickBookedItinerarybutton();
		driver.get("https://adactinhotelapp.com/Logout.php");
		driver.get("https://adactinhotelapp.com/BookedItinerary.php");
		Assert.assertTrue(driver.getCurrentUrl().contains("index.php"),
				"Unauthorized access to Booked Itinerary page is allowed.");
	}
}
