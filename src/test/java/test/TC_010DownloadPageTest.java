package test;

import java.io.IOException;

import org.testng.annotations.Test;

import base.ProjectSpecificationMethod;
import page.DownloadPage;
import page.HomePage;

public class TC_010DownloadPageTest extends ProjectSpecificationMethod{
	
	@Test
	 public void testDownloadAndClick() throws IOException, InterruptedException {
		
		HomePage homepage=new HomePage(driver);
		homepage.Isusername("Sivachandran")
		
        .Ispassword("987654321")
        .ClickLogin();

	        DownloadPage downloadPage = new DownloadPage(driver);
	        
	        // Click on Hotel App Download link and verify navigation
	        downloadPage.clickHotelAppLink();
	        
	        // Click on Web Services link and verify navigation
	        downloadPage.clickWebServiceLink();
	     // Click on SampleTestCases link and verify navigation
	        downloadPage.clickSampleTestCasesLink();
	     // Click on clickBookOnAutomation link and verify navigation
	        downloadPage.clickBookOnAutomationLink();
	     // Click on KnownDefectsLink link and verify navigation 
	        downloadPage.clickKnownDefectsLink();
	      //  Thread.sleep(2000);
	       // driver.quit();
	        
	   /*  // Click on Sample Test Cases Download link and verify file download
	        downloadPage.clickSampleTestCasesLink();
	        downloadPage.validateFileDownload("Sample-TestCases_HotelApplication.pdf", 
	            "https://adactinhotelapp.com/resources/Sample-TestCases_HotelApplication.pdf");

	        // Click on Known Defects Download link and verify file download
	        downloadPage.clickKnownDefectsLink();
	        downloadPage.validateFileDownload("Known_Defects_List.pdf", 
	            "https://adactinhotelapp.com/resources/Known_Defects_List.pdf");
	        // Click on Book on Automation link and verify navigation
	        downloadPage.clickBookOnAutomationLink();
	    }*/}

}
