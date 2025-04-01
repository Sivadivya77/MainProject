package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.ProjectSpecificationMethod;

public class ForgetPasswordPage extends ProjectSpecificationMethod {
	
	@FindBy(name = "emailadd_recovery")
    private WebElement enterEmailId;

    @FindBy(name = "Submit")
    private WebElement clickSubmit;

    @FindBy(name = "rest")
    private WebElement clickReset;

    @FindBy(css = "a[href='index.php']")
    private WebElement backToLogin;

    @FindBy(id = "emailadd_span")
    private WebElement error_msg;

    // Constructor
    public ForgetPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Enter email ID
    public ForgetPasswordPage enterEmailId(String email) {
        enterEmailId.sendKeys(email);
        return this;
    }

    // Click Submit
    public ForgetPasswordPage clickSubmit() {
        clickSubmit.click();
        return this;
    }

    // Click Reset
    public ForgetPasswordPage clickReset() {
        clickReset.click();
        return this;
    }

    // Get error message
    public String getErrorMessage() {
        return error_msg.getText();
    }
    // Click Back to Login
    public ForgetPasswordPage clickBackToLogin() {
        backToLogin.click();
        return this;
    }
}
