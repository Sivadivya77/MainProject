package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.ProjectSpecificationMethod;

public class ChangePasswordPage extends ProjectSpecificationMethod{
	
	@FindBy(xpath = "//strong[normalize-space()='Change Password']")
	private WebElement ChangepasswordText;
	
	 @FindBy(id = "current_pass")  
	    private WebElement currentPasswordField;

	    @FindBy(id = "new_password")  
	    private WebElement newPasswordField;

	    @FindBy(id = "re_password")  
	    private WebElement confirmPasswordField;

	    @FindBy(id = "Submit")  
	    private WebElement changePasswordButton;

	    @FindBy(xpath  = "//span[normalize-space()='Your Password is successfully updated!!!']")  
	    private WebElement confirmationMessage;
	    
	    @FindBy(xpath  = "//span[normalize-space()='Please enter correct current password!!!']")  
	    private WebElement errorMessage; 

	    // Constructor to initialize elements
	    public ChangePasswordPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }
	    
	    public void verifyButtonsDisplayedAndEnabled() {
	    	Assert.assertTrue(currentPasswordField.isDisplayed()&&currentPasswordField.isEnabled(),"Current Password field is not available");
	    	Assert.assertTrue(newPasswordField.isDisplayed() && newPasswordField.isEnabled(), "New Password field is not available");
	        Assert.assertTrue(confirmPasswordField.isDisplayed() && confirmPasswordField.isEnabled(), "Confirm Password field is not available");
	        Assert.assertTrue(changePasswordButton.isDisplayed() && changePasswordButton.isEnabled(), "Change Password button is not available");
	    }

	    // Change password method
	    public void changePassword(String currentPassword, String newPassword, String confirmPassword) {
	        currentPasswordField.sendKeys(currentPassword);
	        newPasswordField.sendKeys(newPassword);
	        confirmPasswordField.sendKeys(confirmPassword);
	        changePasswordButton.click();
	    }
	    // Verify success message
	    public void verifySuccessMessage(String expectedMessage) {
	        Assert.assertTrue(confirmationMessage.isDisplayed(), "Success message is not displayed");
	        Assert.assertEquals(confirmationMessage.getText(), expectedMessage, "Incorrect success message");
	    }
	    // Verify error message
	    public String verifyErrorMessage(String expectedError) {
	        Assert.assertTrue(errorMessage.isDisplayed(), "Error message is not displayed");
	        Assert.assertEquals(errorMessage.getText(), expectedError, "Incorrect error message");
			return expectedError;
	    }
	
	

}
