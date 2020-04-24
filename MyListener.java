package Listener;

import org.testng.ITestListener;
import org.testng.ITestResult;

import myscreenshot.SaveScreenshot;

public class MyListener implements ITestListener {
	
	  
	// When Test case get failed, this method is called.
	
    @Override		
    public void onTestFailure(ITestResult Result) 					
    {		
	  
	   System.out.println("***** Error " + Result.getName() + " test has failed *****");
	 
       SaveScreenshot.capture();
	
    }	
	
}
