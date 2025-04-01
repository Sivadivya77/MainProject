package page;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.ProjectSpecificationMethod;

public class SignupPage extends ProjectSpecificationMethod {
	
	 // Input Fields
    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "re_password")
    private WebElement confirmPassword;

    @FindBy(id = "full_name")
    private WebElement fullName;

    @FindBy(id = "email_add")
    private WebElement email;

    @FindBy(id = "captcha")
    private WebElement captchaInput;

    // Checkbox & Buttons
    @FindBy(id = "tnc_box")
    private WebElement termsCheckbox;

    @FindBy(id = "Submit")
    private WebElement registerButton;

    // Error Messages
    @FindBy(xpath = "//*[@id='username_span']")
    private WebElement usernameError;

    @FindBy(xpath = "//*[@id='password_span']")
    private WebElement passwordError;

    @FindBy(xpath = "//*[@id='re_password_span']")
    private WebElement confirmPasswordError;

    @FindBy(xpath = "//*[@id='full_name_span']")
    private WebElement fullNameError;

    @FindBy(xpath = "//*[@id='email_add_span']")
    private WebElement emailError;

    @FindBy(xpath = "//*[@id='captcha_span']")
    private WebElement captchaError;

    @FindBy(xpath = "//*[@id='tnc_span']")
    private WebElement termsError;

	private WebDriverWait wait;

    // Constructor
    public SignupPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        PageFactory.initElements(driver, this);
    }

    // Methods to interact with form fields
    public void enterUsername(String user) {
        wait.until(ExpectedConditions.visibilityOf(username)).sendKeys(user);
    }

    public void enterPassword(String pass) {
        wait.until(ExpectedConditions.visibilityOf(password)).sendKeys(pass);
    }

    public void enterConfirmPassword(String confirmPass) {
        wait.until(ExpectedConditions.visibilityOf(confirmPassword)).sendKeys(confirmPass);
    }

    public void enterFullName(String name) {
        wait.until(ExpectedConditions.visibilityOf(fullName)).sendKeys(name);
    }

    public void enterEmail(String emailAddress) {
        wait.until(ExpectedConditions.visibilityOf(email)).sendKeys(emailAddress);
    }

    public void enterCaptcha(String captchaText) {
        wait.until(ExpectedConditions.visibilityOf(captchaInput)).sendKeys(captchaText);
    }

    public void clickTermsCheckbox() {
        wait.until(ExpectedConditions.elementToBeClickable(termsCheckbox)); 
        if (!termsCheckbox.isSelected()) {
            termsCheckbox.click();
        }
    }

    public void clickRegisterButton() throws TimeoutException {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Submit")));
            wait.until(ExpectedConditions.visibilityOf(registerButton));

            if (registerButton.isEnabled()) {
                wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
            } else {
                System.out.println("Register button is disabled.");
            }
        } catch (Exception e) {
            System.out.println("Error clicking Register button: " + e.getMessage());
        }
    }


    // **Fetch all error messages dynamically**
    public String getAllErrorMessages() {
        List<String> errors = new ArrayList<>();

        if (isElementVisible(usernameError)) errors.add(usernameError.getText());
        if (isElementVisible(passwordError)) errors.add(passwordError.getText());
        if (isElementVisible(confirmPasswordError)) errors.add(confirmPasswordError.getText());
        if (isElementVisible(fullNameError)) errors.add(fullNameError.getText());
        if (isElementVisible(emailError)) errors.add(emailError.getText());
        System.out.println(emailError.getText());
        if (isElementVisible(captchaError)) errors.add(captchaError.getText());
        if (isElementVisible(termsError)) errors.add(termsError.getText());

        return String.join(" | ", errors);
    }

    // Helper method to check if an element is visible
    private boolean isElementVisible(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}