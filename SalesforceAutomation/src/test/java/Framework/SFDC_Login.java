package Framework;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

 class SFDC_Login extends ReUsableMethods {
	/*	public static void main(String[] args) throws IOException, InterruptedException {
			
		
		executionReport("Sprint1");
		Login_Error_Message_1();
	
			Check_RememberMe_3();
		
			forgot_Password_4A();
	
		
		ValidateLoginErrorMessage_5();
			UserDropDownMenu();
		
		extent.flush();
	}
*/

@Test
	
		public static void Login_Error_Message_1() throws IOException  {
		/*Properties pro=new Properties();
		BufferedReader reader = new BufferedReader(new FileReader("C:/Users/sugam/git/repository/enexusSelenium/src/gitsample/.git/enexusSelenium/src/DataFiles/config.properties"));
		pro.load(reader);
		*/
		logger = extent.createTest("Login_Error_Message_1");
		System.out.println("Test Case for login error message");
			    String expectedErrorText="Please check";	
			    launchBrowser("firefox");
			   // System.setProperty("webdriver.gecko.driver","./src/utility/geckodriver.exe"); 
			   
				//driver=new FirefoxDriver();
				logger.log(Status.PASS, MarkupHelper.createLabel("Firefox driver is launched", ExtentColor.GREEN));

				//System.out.println("Firefox Browser is launched");
			    driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
				driver.get("https://login.salesforce.com/");
						
				System.out.println("Salesforce Application is launched");
			
			WebElement userName=driver.findElement(By.id("username"));
			enterText(userName, "Pallavi@sugavanam.com", "UserName");
			
			WebElement password=driver.findElement(By.id("password"));
			enterText(password, "Palsv", "Password");

			WebElement loginBtn=driver.findElement(By.id("Login"));
			clickObj(loginBtn, "LoginBtn");

			WebElement actualError=driver.findElement(By.xpath("//div[@id='error']"));
		    String message=getText(actualError,"Error");
		    
		   //0 Assert.assertTrue(message.contains(expectedErrorText));	
		    verifyText(actualError, "Error msg", expectedErrorText);
		    
			driver.close();
			System.out.println("Test is completed");
						}
				
		@Test

				public static void Check_RememberMe_3() throws InterruptedException, IOException {
					logger = extent.createTest("Check_RememberMe_3");

	System.out.println("Test case Check Remember Me");

//System.setProperty("webdriver.gecko.driver","./src/utility/geckodriver.exe"); 

//driver=new FirefoxDriver();
	launchBrowser("firefox");
System.out.println("Firefox Browser is launched");
driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
driver.get("https://login.salesforce.com/");
System.out.println("Salesforce Application is launched");
					String expectedErrorText="Pallavi@sugavanam.com";	
				

			WebElement userName=driver.findElement(By.id("username"));
			enterText(userName, "Pallavi@sugavanam.com", "UserName");

			WebElement password=driver.findElement(By.id("password"));
			enterText(password, "Palsvenu80", "Password");


			WebElement rememberCheckBox=driver.findElement(By.xpath("//input[@id='rememberUn']"));
            checkBoxObj(rememberCheckBox, "Remember Password Button");


            WebElement loginBtn=driver.findElement(By.id("Login"));
            clickObj(loginBtn, "Login Button");
					Thread.sleep(2000);
			WebElement userDropdown=driver.findElement(By.xpath("//span[@id='userNavLabel']"));
			List<WebElement> listOfUserMenu=driver.findElements(By.xpath("//div[@id='userNav-menuItems']//a"));
            dropdown(userDropdown, listOfUserMenu,"Logout");


			
			WebElement afterLogoutUsermail=driver.findElement(By.xpath("//span[@id='idcard-identity']"));
			 String message=getAttribute(afterLogoutUsermail, "value","username");
			 System.out.println(message);
			 verifyAttribute(afterLogoutUsermail, "value","Username", expectedErrorText);
			// verifyText(element, elementName, expectedText);
			   // Assert.assertEquals(message, expectedErrorText);	
			    driver.close();
		//Assert.assertTrue(message.contains(expectedErrorText));		
				System.out.println("Test is completed");

			    
			}

//@Test
public static void forgot_Password_4A() throws InterruptedException, IOException {
	logger = extent.createTest("forgot_Password_4A");

	System.out.println("Test case for forgotPassword");
	    //System.setProperty("webdriver.gecko.driver","./src/utility/geckodriver.exe"); 
	   
		//driver=new FirefoxDriver();
	launchBrowser("firefox");
		System.out.println("Firefox Browser is launched");
	    driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.get("https://login.salesforce.com/");
		System.out.println("Salesforce Application is launched");
	Thread.sleep(2000);
		
		WebElement forgotPassword=driver.findElement(By.id("forgot_password_link"));
        clickObj(forgotPassword, "forgot Password");	

		WebElement enterUsernameInForgot=driver.findElement(By.id("un"));
		enterText(enterUsernameInForgot, "pallavi@sugavanam.com","UserName");
		
		WebElement continueInForgot=driver.findElement(By.id("continue"));
		clickObj(continueInForgot, "Continue");
		Thread.sleep(2000);
		//checkGetTitle("Check Your Email", "Check Your Email");
	
		
		WebElement textMsgInCheckEmail=driver.findElement(By.xpath("//div[@class='message']//p"));
		checkGetText(textMsgInCheckEmail, "Check Your");
		System.out.println(textMsgInCheckEmail);
		System.out.println("Test is completed");
	    verifyText(textMsgInCheckEmail, "Error msg", "Check Your");


closeDriver();	}


//@Test
public static void ValidateLoginErrorMessage_5 () {
	logger = extent.createTest("ValidateLoginErrorMessage_5");
		String actualMsg=
				"Your login attempt has failed. The username or password may be incorrect, or your location or login time may be restricted. Please contact the administrator at your company for help";
		System.out.println("Validate Login Message");
		   // System.setProperty("webdriver.gecko.driver","./src/utility/geckodriver.exe"); 		   
			//driver=new FirefoxDriver();
		launchBrowser("firefox");
			System.out.println("Firefox Browser is launched");
		    driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			driver.get("https://login.salesforce.com/");
			System.out.println("Salesforce Application is launched");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		WebElement userName=driver.findElement(By.id("username"));
		enterText(userName, "123","UserName");

		WebElement password=driver.findElement(By.id("password"));
		enterText(password, "22131","UserName");



        WebElement loginBtn=driver.findElement(By.id("Login"));
        clickObj(loginBtn, "Login Button");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		
		WebElement errorMsg=driver.findElement(By.xpath("//div[@id='error']"));
		
		System.out.println(errorMsg);
checkGetText(errorMsg, actualMsg);
 closeDriver();
}	

//@Test
public static void UserDropDownMenu() throws InterruptedException {

	logger = extent.createTest("UserDropDownMenu");


//System.setProperty("webdriver.chrome.driver","./src/utility/chromedriver.exe");		   
//driver=new ChromeDriver();
	launchBrowser("chrome");
driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
driver.manage().window().maximize();
driver.get("https://login.salesforce.com/");

WebElement userName=driver.findElement(By.id("username"));
enterText(userName, "Pallavi@sugavanam.com"," UserName");

WebElement password=driver.findElement(By.id("password"));
enterText(password, "Palsvenu80", "Password");




WebElement loginBtn=driver.findElement(By.id("Login"));
clickObj(loginBtn, "Login Button");

		Thread.sleep(2000);
WebElement userDropdown=driver.findElement(By.xpath("//span[@id='userNavLabel']"));
List<WebElement> listOfUserMenu=driver.findElements(By.xpath("//div[@id='userNav-menuItems']//a"));
dropdown(userDropdown, listOfUserMenu,"My Profile");





WebElement editContact=driver.findElement(By.xpath(" //a[@class='contactInfoLaunch editLink']//img[@title='Edit Profile']"));
clickObj(editContact, "The Edit Contact");
//editContact.click();

driver.switchTo().frame(1);

WebElement about=driver.findElement(By.xpath("//li[@id='aboutTab']//a[@aria-controls='TabPanel:0:Body:1']"));
clickObj(about,"The About Tab");
//about.click();

WebElement changeLastName=driver.findElement(By.id("lastName"));
enterText(changeLastName, "Sugavanam", "LastName");
//changeLastName.clear();
//changeLastName.sendKeys("Sugavanam");

WebElement saveChanged=driver.findElement(By.xpath("//div[@class='net-buttons zen-mtl']//input[@value='Save All']"));
clickObj(saveChanged, "Save Tab");
//saveChanged.click();
driver.switchTo().defaultContent();

WebElement changedUserName=driver.findElement(By.id("tailBreadcrumbNode"));
checkGetText(changedUserName, "Sugavanam");



//post

WebElement postInProfile=driver.findElement(By.xpath("//span[contains(@class,'publisherattachtext')][contains(text(),'Post')]"));
clickObj(postInProfile, "Post Button");
//postInProfile.click();

WebElement myFrame1=driver.findElement(By.xpath("//iframe[@class='cke_wysiwyg_frame cke_reset']"));


driver.switchTo().frame(myFrame1);

WebElement writeInPost=driver.findElement(By.xpath("//body[contains(@class,'chatterPublisherRTE ')]"));
enterText(writeInPost, "This is Learning Selenium", "post");
/*
writeInPost.click();
writeInPost.sendKeys("This is Learning Selenium");
*/

driver.switchTo().defaultContent();


WebElement ShareBtn=driver.findElement(By.xpath("//input[@id='publishersharebutton']"));
clickObj(ShareBtn, "Share button");
//ShareBtn.click();

WebElement displayText1=driver.findElement(By.xpath("//div[@class='cxfeeditemtextadditional']//span[@class='feeditemtext cxfeeditemtext']//p"));

checkGetText(displayText1, "This is Learning");


//file


Thread.sleep(2000);
WebElement fileInProfile=driver.findElement(By.xpath(" //a[@id='publisherAttachContentPost']"));
clickObj(fileInProfile, "The File Tab");

WebElement uploadBtn=driver.findElement(By.xpath(" //a[@id='chatterUploadFileAction']"));
clickObj(uploadBtn, "The upload button");
WebElement browseInfile=driver.findElement(By.xpath("//input[@id='chatterFile']"));
//browseInfile.click();
//browseInfile.sendKeys("F:\\Pallavi\\unixworking");

enterText(browseInfile, "F:\\Pallavi\\unixworking", "The path");
System.out.println("The file is Successfully downlaoded");
//upload photo

WebElement browseInPhoto=driver.findElement(By.xpath("//div[@id='photoSection']//img[@title='Pallavi Sugavanam']"));
Actions action=new Actions(driver);
action.moveToElement(browseInPhoto).build().perform();

WebElement addPhoto=driver.findElement(By.id("uploadLink"));
clickObj(addPhoto, "Photo");

WebElement myFrame3=driver.findElement(By.xpath("//iframe[@id='uploadPhotoContentId']"));

driver.switchTo().frame(myFrame3);

WebElement upload=driver.findElement(By.id("j_id0:uploadFileForm:uploadInputFile"));

enterText(upload,"G:\\Kaushik_8th_BDay\\Phone\\IMG_20160214_190005.jpg", "Upload" );
//upload.sendKeys("G:\\Kaushik_8th_BDay\\Phone\\IMG_20160214_190005.jpg");

WebElement savePhoto=driver.findElement(By.id("j_id0:uploadFileForm:uploadBtn"));

savePhoto.click();

WebElement save1=driver.findElement(By.id("j_id0:j_id7:save"));
save1.click();

//browseInfile.sendKeys("F:\\Pallavi\\unixworking");
closeDriver();


}

}





				


