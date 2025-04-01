package test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethod;
import page.ForgetPasswordPage;
import page.HomePage;

public class TC_002ForgetPasswordTest extends ProjectSpecificationMethod {
	private ForgetPasswordPage forgetPasswordPage;
	@BeforeMethod
	public void setup() {
		HomePage homepage=new HomePage(driver);
		homepage.ClickForgetButton();
		forgetPasswordPage = new ForgetPasswordPage(driver);
	}

	@Test
	public void testValidEmailForPasswordReset() {
		
		
        forgetPasswordPage.enterEmailId("validuser@example.com")
                          .clickSubmit();

        // Assume successful message appears (modify as per actual app behavior)
        String expectedMessage ="Email Address does not exsit in database";
        Assert.assertEquals(forgetPasswordPage.getErrorMessage(), expectedMessage, "Password reset not successful.");
    }

    @Test(priority = 2)
    public void testInvalidEmailForPasswordReset() throws InterruptedException {
    	
        forgetPasswordPage. enterEmailId("invalidemail")
                          .clickSubmit();
         
        Thread.sleep(2000);
        String expectedError = "Invalid email, Please enter correct email.";
        Assert.assertEquals(forgetPasswordPage.getErrorMessage(), expectedError, "Error message did not match.");
    }

    @Test(priority = 3)
    public void testBlankEmailField() throws InterruptedException {
    	
        forgetPasswordPage. enterEmailId("")
                          .clickSubmit();
        Thread.sleep(2000);
        String expectedError = "Email Address is Empty";
        Assert.assertEquals(forgetPasswordPage.getErrorMessage(), expectedError, "Error message did not match.");
    }

    @Test(priority = 4)
    public void testClickBackToLogin() throws InterruptedException {
    	
        forgetPasswordPage.clickBackToLogin();
        Thread.sleep(2000);
        String expectedUrl = "https://adactinhotelapp.com/index.php";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Did not navigate back to Login page.");
    }


}
