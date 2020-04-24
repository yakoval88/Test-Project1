package mypackage;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.javapoint.Base;

@Listeners(Listener.MyListener.class)

public class TestLogin extends Base{
	
	private static String url = "https://www.leumi.co.il/";
	static TestLogin object;
	static WebDriver driver;
	
	/**
	 * Create WebDriver object and load web page
	 */
	
	@BeforeTest
	  public static void beforeTest() throws IOException {		  
		        
		    object = new TestLogin();
		    object.setUp();
		    driver = object.getDriver(url);	    
	  }
	
	  /**
	   * Open popup window and iterate to it
	   */
	  @BeforeClass
	    public static void ChangeWindow() {	  
		 
		  
			// Switch to english version
			//new window will open
			driver.findElement(By.xpath("//ul[@class='languages']//a[contains(text(),'EN')]")).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			// get all window handles
			Set<String> handles = driver.getWindowHandles();
			Iterator<String> iterator = handles.iterator();
			//move to pop-up window
			String MainWindow = iterator.next().toString();
			String ChildWindow = iterator.next().toString();
			//Remove main window  
			 handles.remove(MainWindow);
				
			driver.switchTo().window(ChildWindow);
			driver.manage().window().maximize();
	    }	  
	  
	  /**
	   * Open account page and try to login
	   */
	  @Test
	  public void MakeTest () throws IOException, NoSuchElementException {
	 
	        // Massage for incorrect login or password
		    String msg = "One or more of your identification details is incorrect.";
		    //find drop down menu 
			Select drp_login = new Select(driver.findElement(By.xpath("//select[@name='selObj']")));
			drp_login.selectByVisibleText("Leumi Israel Online");
			// fill up the form
			WebElement login = driver.findElement(By.xpath("//input[@id='uid']"));
			login.sendKeys("yakoval");
			WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
			password.sendKeys("123456");
			//submit the form
			login.submit();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);	
			// check out if form was submitted
			String ErrorText = driver.findElement(By.xpath("//label[@id='errHeader']")).getText();
			// if you did not pass authentication - test fails
			 Assert.assertFalse(ErrorText.contentEquals(msg));
			
	  }
	  
	  /**
	   * Close driver  after test completes
	   */
	  
	  @AfterTest
	  public static void afterTest() {
		  
		  object.closeDriver();
		  driver.quit();
	  }

}

