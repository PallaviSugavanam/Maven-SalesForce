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
	
		public static void Login_Error_Message_1() throws Exception  {
	
	 
		String curDir=System.getProperty("user.dir");
		Properties pro=loadProperty(curDir+"./src/test/resources/utility/config.properties");
		Properties objpro=loadProperty(curDir+"./src/test/resources/utility/ObjectProperties.properties");
	
        CreateReport("Login_Error_Message_1");
         
		String expectedErrorText="Please check";	
	    launchBrowser("firefox");			   
			    
		launchApplication(pro.getProperty("salesforceUrl"), "SalesForce");
			
			
			WebElement userName=driver.findElement(getLocator("Salesforce.LoginPage.Username", objpro));
			enterText(userName, objpro.getProperty("Salesforce.LoginPage.ValidUsername"), "UserName");
			
			WebElement password=driver.findElement(getLocator("Salesforce.LoginPage.Password",objpro));					
			enterText(password, objpro.getProperty("Salesforce.LoginPage.inValidPassword"), "Password");

			WebElement loginBtn=driver.findElement(getLocator("Salesforce.LoginPage.LoginBtn", objpro));
			clickObj(loginBtn, "LoginBtn");

			WebElement actualError=driver.findElement(getLocator("Salesforce.LoginPage.actError", objpro));
		    String message=getText(actualError,"Error");
		    
		    verifyText(actualError, "Error msg", expectedErrorText);
		    
			closeDriver();
						}
				
		@Test

				public static void Check_RememberMe_3() throws Exception {

			String curDir=System.getProperty("user.dir");
			Properties pro=loadProperty(curDir+"./src/test/resources/utility/config.properties");
			Properties objpro=loadProperty(curDir+"./src/test/resources/utility/ObjectProperties.properties");
		
	        CreateReport("Check_RememberMe_3");
	
	launchBrowser("firefox");
	launchApplication(pro.getProperty("salesforceUrl"), "SalesForce");


					String expectedErrorText="Pallavi@sugavanam.com";
					WebElement userName=driver.findElement(getLocator("Salesforce.LoginPage.Username", objpro));
					enterText(userName, objpro.getProperty("Salesforce.LoginPage.ValidUsername"), "UserName");
				
					WebElement password=driver.findElement(getLocator("Salesforce.LoginPage.Password",objpro));					
					enterText(password, objpro.getProperty("Salesforce.LoginPage.ValidPassword"), "Password");	
		

		
			WebElement rememberCheckBox=driver.findElement(getLocator("Salesforce.LoginPage.checkRememberMe", objpro));
            checkBoxObj(rememberCheckBox, "Remember Password Button");

            WebElement loginBtn=driver.findElement(getLocator("Salesforce.LoginPage.LoginBtn", objpro));
			clickObj(loginBtn, "LoginBtn");
           
					Thread.sleep(2000);
			WebElement userDropdown=driver.findElement(getLocator("Salesforce.HomePage.UserDropdown", objpro));
			List<WebElement> listOfUserMenu=driver.findElements(getLocator("Salesforce.HomePage.UserDropdownList", objpro));
            dropdown(userDropdown, listOfUserMenu,"Logout");


			
			WebElement afterLogoutUsermail=driver.findElement(getLocator("Salesforce.HomePage.AfterLogoutUsermail", objpro));
			 String message=getAttribute(afterLogoutUsermail, "value","username");
			 System.out.println(message);
			 verifyAttribute(afterLogoutUsermail, "value","Username", expectedErrorText);
		closeDriver();
				//System.out.println("Test is completed");

			    
			}

//@Test
public static void forgot_Password_4A() throws Exception {
	String curDir=System.getProperty("user.dir");
	Properties pro=loadProperty(curDir+"./src/test/resources/utility/config.properties");
	Properties objpro=loadProperty(curDir+"./src/test/resources/utility/ObjectProperties.properties");

    CreateReport("forgot_Password_4A");

launchBrowser("firefox");
launchApplication(pro.getProperty("salesforceUrl"), "SalesForce");


			
   
	
	
	
		
		WebElement forgotPassword=driver.findElement(getLocator("Salesforce.LoginPage.ForgetPassword", objpro));
        clickObj(forgotPassword, "forgot Password");	

		WebElement enterUsernameInForgot=driver.findElement(getLocator("Salesforce.ForgetPage.UsernameInForget", objpro));
		enterText(enterUsernameInForgot, objpro.getProperty("Salesforce.LoginPage.ValidUsername"),"UserName");
		
		WebElement continueInForgot=driver.findElement(getLocator("Salesforce.ForgetPage.ContinueBtn", objpro));
		clickObj(continueInForgot, "Continue");
		Thread.sleep(2000);
		//checkGetTitle("Check Your Email", "Check Your Email");
	
		
		WebElement textMsgInCheckEmail=driver.findElement(getLocator("Salesforce.ForgetPage.ActualMessage", objpro));
		checkGetText(textMsgInCheckEmail, "Check Your");
		System.out.println(textMsgInCheckEmail);
		System.out.println("Test is completed");
	    verifyText(textMsgInCheckEmail, "Error msg", "Check Your");


closeDriver();	}


//@Test
public static void ValidateLoginErrorMessage_5 () throws Exception {
	
	String curDir=System.getProperty("user.dir");
	Properties pro=loadProperty(curDir+"./src/test/resources/utility/config.properties");
	Properties objpro=loadProperty(curDir+"./src/test/resources/utility/ObjectProperties.properties");

    CreateReport("forgot_Password_4A");

launchBrowser("firefox");
launchApplication(pro.getProperty("salesforceUrl"), "SalesForce");


			
		String actualMsg=
				"Your login attempt has failed. The username or password may be incorrect, or your location or login time may be restricted. Please contact the administrator at your company for help";
		
		
		WebElement userName=driver.findElement(getLocator("Salesforce.LoginPage.Username", objpro));
		enterText(userName, "123", "UserName");
	
		WebElement password=driver.findElement(getLocator("Salesforce.LoginPage.Password",objpro));					
		enterText(password,"223132", "Password");	

		  WebElement loginBtn=driver.findElement(getLocator("Salesforce.LoginPage.LoginBtn", objpro));
	    clickObj(loginBtn, "LoginBtn");
         
	

	     Thread.sleep(2000);
		
		WebElement errorMsg=driver.findElement(getLocator("Salesforce.LoginPage.ErrorMsg", objpro));
		
		System.out.println(errorMsg);
         checkGetText(errorMsg, actualMsg);
         closeDriver();
}	

//@Test
public static void UserDropDownMenu() throws Exception {
	
	String curDir=System.getProperty("user.dir");
	Properties pro=loadProperty(curDir+"./src/test/resources/utility/config.properties");
	Properties objpro=loadProperty(curDir+"./src/test/resources/utility/ObjectProperties.properties");

    CreateReport("Check_RememberMe_3");

launchBrowser("firefox");
driver.manage().window().maximize();

launchApplication(pro.getProperty("salesforceUrl"), "SalesForce");


			WebElement userName=driver.findElement(getLocator("Salesforce.LoginPage.Username", objpro));
			enterText(userName, objpro.getProperty("Salesforce.LoginPage.ValidUsername"), "UserName");
		
			WebElement password=driver.findElement(getLocator("Salesforce.LoginPage.Password",objpro));					
			enterText(password, objpro.getProperty("Salesforce.LoginPage.ValidPassword"), "Password");	


    WebElement loginBtn=driver.findElement(getLocator("Salesforce.LoginPage.LoginBtn", objpro));
	clickObj(loginBtn, "LoginBtn");
   
			Thread.sleep(2000);
	WebElement userDropdown=driver.findElement(getLocator("Salesforce.HomePage.UserDropdown", objpro));
	List<WebElement> listOfUserMenu=driver.findElements(getLocator("Salesforce.HomePage.UserDropdownList", objpro));
    dropdown(userDropdown, listOfUserMenu,"My Profile");










WebElement editContact=driver.findElement(getLocator("Salesforce.MyProfile.EditContact", objpro));
clickObj(editContact, "The Edit Contact");

driver.switchTo().frame(1);

WebElement about=driver.findElement(getLocator("Salesforce.MyProfile.EditProfile.Contact",objpro));
clickObj(about,"The About Tab");

WebElement changeLastName=driver.findElement(getLocator("Salesforce.MyProfile.EditProfile.Contact.LastName",objpro));
enterText(changeLastName, "Sugavanam", "LastName");


WebElement saveChanged=driver.findElement(getLocator("Salesforce.MyProfile.EditProfile.Contact.Save",objpro));
clickObj(saveChanged, "Save Tab");
driver.switchTo().defaultContent();

WebElement changedUserName=driver.findElement(getLocator("Salesforce.MyProfile.NameTitle",objpro));
checkGetText(changedUserName, "Sugavanam");



//post

WebElement postInProfile=driver.findElement(getLocator("SalesForce.MyProfile.Post",objpro));
clickObj(postInProfile, "Post Button");
//postInProfile.click();

WebElement myFrame1=driver.findElement(getLocator("SalesForce.MyProfile.Post.myFrame",objpro));


driver.switchTo().frame(myFrame1);

WebElement writeInPost=driver.findElement(getLocator("SalesForce.MyProfile.Post.write",objpro));
enterText(writeInPost, "This is Learning Selenium", "post");


driver.switchTo().defaultContent();


WebElement ShareBtn=driver.findElement(getLocator("Salesforce.MyProfile.Post.Share", objpro));
clickObj(ShareBtn, "Share button");
//ShareBtn.click();

WebElement displayText1=driver.findElement(getLocator("Salesforce.MyProfile.Post.DisplatText", objpro));
		
verifyText(displayText1, "The Written Characters", "This is Learning");
//checkGetText(displayText1, "This is Learning");


//file


Thread.sleep(2000);
WebElement fileInProfile=driver.findElement(getLocator("Salesforce.MyProfile.File", objpro));
clickObj(fileInProfile, "The File Tab");

WebElement uploadBtn=driver.findElement(getLocator("Salesforce.MyProfile.File.Upload", objpro));
clickObj(uploadBtn, "The upload button");

WebElement browseInfile=driver.findElement(getLocator("Salesforce.MyProfile.File.browse", objpro));


enterText(browseInfile, objpro.getProperty( "Salesforce.MyProfile.File.PathToBrowseFile"), "The path");
System.out.println("The file is Successfully downlaoded");
//upload photo

WebElement browseInPhoto=driver.findElement(getLocator(" Salesforce.MyProfile.Photo", objpro));
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





				


