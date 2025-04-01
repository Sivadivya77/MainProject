package page;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethod;

public class HomePage extends ProjectSpecificationMethod {
	
	@FindBy(id = "username")
	WebElement username;
	
	@FindBy(id = "password")
	WebElement password;
	
	@FindBy(css = "a[href='ForgotPassword.php']")
	WebElement forgetpassword;
	 
	@FindBy(id = "login")
	WebElement loginbutton;
	
	@FindBy(xpath = "//img[@alt='AdactIn Group']")
	WebElement logo;
	
	@FindBy(css =  "div[class='auth_error'] b")
	WebElement errorMsg;
	
	@FindBy(xpath =  "//a[normalize-space()='Click here']")
	WebElement ClickhereButton;
    
	@FindBy(className = "login_register")
	WebElement Clickregister;
	
	@FindBy(id="username_span")
	WebElement UserError;
	 
	@FindBy(id = "password_span")
	WebElement PasswordError;
	
	
	
	private WebDriverWait wait;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean IsusernameVisible() {
		return username.isDisplayed();
		
	}
	public boolean IspasswordVisible() {
		return password.isDisplayed();
	}
	public boolean Islogoisprsent() {
		return logo.isDisplayed();
	}
	public boolean IsforgetPasswordEnable() {
		return forgetpassword.isDisplayed();
	}
	public boolean IsloginbuttonEnable() {
		return loginbutton.isDisplayed();
	}
	public HomePage Isusername(String userName) {
		username.sendKeys(userName);
		return this;
		
	}
	
	public HomePage Ispassword(String Password) {
		password.sendKeys(Password);
		return this;
	}
	public void ClickLogin() {
		loginbutton.click();
	}
	
	public void Clickhere() {
	    wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(ClickhereButton));
	    element.click();
	}
	public void ClickForgetButton() {
		forgetpassword.click();
		return;
	}
	public void ClickregisterButton() {
		Clickregister.click();
		return ;
	}
	public String CheckerrorMsg() {
		
	    wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    try {
	        if (isElementVisible(UserError)) {
	            return UserError.getText();  // Username error
	        } else if (isElementVisible(PasswordError)) {
	            return PasswordError.getText();  // Password error
	        } else if (isElementVisible(errorMsg)) {
	            return errorMsg.getText();  // Invalid login error
	        } else {
	            return "No error message displayed";
	        }
	    } catch (Exception e) {
	        return "Error retrieving message";
	    }
	}

	// Helper method to check element visibility
	private boolean isElementVisible(WebElement element) {
	    try {
	        return element.isDisplayed();
	    } catch (Exception e) {
	        return false;
	    }
	}
	}