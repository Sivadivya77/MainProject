package test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethod;
import page.ChangePasswordPage;
import page.HomePage;
import page.SearchHotelPage;

public class TC_009ChangePasswordTest extends ProjectSpecificationMethod {
	
	@BeforeTest
	public void setup() {
		sheetname="ChangePasswordData";
		
		
	}

	@Test(dataProvider = "readdata")
    public void testChangePassword(String Username,String Password,String currentPassword, String newPassword, String confirmPassword, String expectedMessage) {
		HomePage homepage=new HomePage(driver);
		homepage.Isusername(Username)
		
        .Ispassword(Password)
        .ClickLogin();

        
        SearchHotelPage searchhotel=new SearchHotelPage(driver);
        searchhotel.ClickChangePasswordButton();
        
		ChangePasswordPage changePasswordPage = new ChangePasswordPage(driver);
        
        // Verify all buttons are displayed and enabled
        changePasswordPage.verifyButtonsDisplayedAndEnabled();
        
        // Change password with provided data
        changePasswordPage.changePassword(currentPassword, newPassword, confirmPassword);
        
        // Validate success or error message
        if (expectedMessage.equals("Your Password is successfully updated!!!")) {
            changePasswordPage.verifySuccessMessage(expectedMessage);
        } else {
            changePasswordPage.verifyErrorMessage(expectedMessage);
        }
    }
	
}
