package test;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.ProjectSpecificationMethod;
import page.HomePage;
import page.SignupPage;

public class TC_003SignupTest extends ProjectSpecificationMethod {
	@BeforeTest
    public void setup() {
        sheetname = "SignupData"; 
    }
	
    @Test(dataProvider = "readdata")
    public void testSignup(String username, String password, String confirmPassword, String fullName, 
                           String email, String termsCheckbox, String expectedResult, String expectedErrorMessage) 
                           throws InterruptedException, TimeoutException {
        
        HomePage homepage = new HomePage(driver);
        homepage.ClickregisterButton();

        SignupPage signuppage = new SignupPage(driver);
     
        signuppage.enterUsername(username);
        signuppage.enterPassword(password);
        signuppage.enterConfirmPassword(confirmPassword);
        signuppage.enterFullName(fullName);
        signuppage.enterEmail(email);
          Thread.sleep(2000);
        if (termsCheckbox.equalsIgnoreCase("Yes")) {
            signuppage.clickTermsCheckbox();
        }
    //    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        signuppage.clickRegisterButton();

        // Handling Expected Results
        if (expectedResult.equalsIgnoreCase("Failure")) {
            String actualErrorMessage = signuppage.getAllErrorMessages();
            Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
                "Expected: " + expectedErrorMessage + ", but got: " + actualErrorMessage);
        } else {
            // If expected result is Success, ensure no error messages are displayed
            Assert.assertTrue(signuppage.getAllErrorMessages().isEmpty(), 
                "Signup should be successful but found errors: " + signuppage.getAllErrorMessages());
        }
    }

}
