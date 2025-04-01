package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.util.ClassUtil;

import base.ProjectSpecificationMethod;
import page.HomePage;

public class TC_001HomePageTest extends ProjectSpecificationMethod {
	@BeforeTest
	public void setup() {
		sheetname="LoginData";
		
	}
		    @Test
	    public void testElementsVisibility() {
		    	HomePage homepage=new HomePage(driver);
	        Assert.assertTrue(homepage.IsusernameVisible(), "Username field is not visible");
	        Assert.assertTrue(homepage.IspasswordVisible(), "Password field is not visible");
	        Assert.assertTrue(homepage.Islogoisprsent(), "Logo is not present");
	        Assert.assertTrue(homepage.IsforgetPasswordEnable(), "Forget Password link is not enabled");
	        Assert.assertTrue(homepage.IsloginbuttonEnable(), "Login button is not enabled");
	    }

	    @Test(dataProvider = "readdata")
	    public void testLoginFunctionality(String Username,String Password,String expectedResult) throws InterruptedException {
	    	HomePage homepage=new HomePage(driver);
	        homepage.Isusername(Username)
	                .Ispassword(Password)
	                .ClickLogin();
	        String actualErrorMessage = homepage.CheckerrorMsg();
	        
	        // Validate expected result
	        
	        if (expectedResult.equalsIgnoreCase("Hello sivachandran!")) {
	            Assert.assertTrue(driver.getCurrentUrl().contains("SearchHotel.php"), 
	                "Login failed for valid credentials.");
	            
	        } else {
	            Assert.assertEquals(actualErrorMessage, expectedResult, 
	                "Error message mismatch.");
	        }
	    }
	        
	       
	    

	    
	}
