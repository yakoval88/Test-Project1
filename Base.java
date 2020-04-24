package com.javapoint;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {
	
	  protected static WebDriver driver;
	  private String chromeDriverPath = "C:\\chromedriver.exe"; 	 
	  
	  /**
	   * Create WebDriver object
	   */
	  public void setUp() throws IOException {			

		    System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		    Base.driver = new ChromeDriver();		   
		    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	  
		  }
	  
	  /**
	   * Load web page 
	   */
	  public WebDriver getDriver(String url) {
		  
			  try {
				  driver.get(url);
				  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
				  driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
				  
			  } catch (TimeoutException ex){
			  System.out.println("Page: " + url + "did not load in 10 seconds!");
	      }
			  return driver;
	  }
	  
	  public void closeDriver () {
		  driver.close();
	  }
}
