package util;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import base.ProjectSpecificationMethod;

public class ListenerClass  extends ProjectSpecificationMethod implements  ITestListener {

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		test.log(Status.PASS, "Test pass");
		String screenShotpath=null;
		try {
			screenShotpath = screenshot(result.getMethod().getMethodName()+" pass");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test.addScreenCaptureFromPath(screenShotpath, "Passed Test Screenshot");
	}
	

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
test.fail(result.getThrowable());
		
		String screenShotpath=null;
		try {
			screenShotpath = screenshot(result.getMethod().getMethodName()+ " fail");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		test.addScreenCaptureFromPath(screenShotpath, "Failed Test Screenshot");
	}
	
	
}


