package Framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class SalesforceBaseclass {
    public static WebDriver driver=null;	

	public static WebDriver launchBrowser(String browser) {
		if(browser.equalsIgnoreCase("firefox")) {
			//logger.log(Status.INFO, MarkupHelper.createLabel("Launching"+browser+"browser ", ExtentColor.GREEN));
             System.out.println("Firefox Entered");
		    System.setProperty("webdriver.gecko.driver","./src/test/resources/utility/geckodriver.exe");	   
		    driver=new FirefoxDriver();		    
			//logger.log(Status.INFO, MarkupHelper.createLabel(browser+"browser is launched Succesfully ", ExtentColor.GREEN));

		}
		else if(browser.equalsIgnoreCase("Chrome")) {
            System.out.println("Chrome");

			//logger.log(Status.INFO, MarkupHelper.createLabel("Launching"+browser+"browser ", ExtentColor.GREEN));
		    System.setProperty("webdriver.chrome.driver","./src/test/resources/utility/chromedriver.exe"); 
	   
		    driver=new ChromeDriver();
			//logger.log(Status.INFO, MarkupHelper.createLabel(browser+"browser is launched Succesfully ", ExtentColor.GREEN));
			
		}
		else {
			System.out.println("Please enter browser name");
			//logger.log(Status.INFO, MarkupHelper.createLabel("Please provide the Browser Name ", ExtentColor.RED));

	}
		return driver;
	}
	public static void closeDriver(){
		driver.close();
	}

}
