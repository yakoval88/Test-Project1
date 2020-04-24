package myscreenshot;

import java.util.Date;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.javapoint.Base;

public class SaveScreenshot extends Base {
	
	/**
	 * this function take screenshot
	 */
	  public static void capture() {
		
		 FileOutputStream file;
		 String folderPath = "./src/screenshots/";
		 
		 setUp_folder();
		 
		 // Cast driver object to TakesScreenshot
	     TakesScreenshot screenshot = (TakesScreenshot) driver;
	    
         try {		
				file = new FileOutputStream(folderPath + timestamp() + ".jpeg");				
				byte[] info = screenshot.getScreenshotAs(OutputType.BYTES);
				// write bytes array to the file output stream
				file.write(info);
				file.close();
			
		      } catch (IOException ex) {
			
				System.out.println("Can not create screenshot");
				ex.printStackTrace();
		      }

	}
       
	  /**
	   * create folder for screenshots
	   */
		public static void setUp_folder() {
			  	File dir= new File("./src/screenshots");
			  	if(!dir.exists()) 
			  		dir.mkdir();		     
			  }
		  
		  /**
		   * Time stamp to give each screenshot unique name
		   */  
		public static String timestamp() {
		    // Time stamp to make each screenshot name unique
			SimpleDateFormat date_format = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
		    return date_format.format(new Date());
	}

}
