package Framework;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;


 class ReUsableMethods extends SalesforceBaseclass{

	static ExtentHtmlReporter htmlReporter;
	static ExtentReports extent=null;
	static ExtentTest logger=null;
	
	public static String[][] readxlData(String path,String sheetName) throws IOException{
		
		FileInputStream fs=new FileInputStream(new File(path));
		HSSFWorkbook workbook=new HSSFWorkbook(fs);
		HSSFSheet sheet=workbook.getSheet(sheetName);
		int rows=sheet.getLastRowNum()+1;
		int cols=sheet.getRow(0).getLastCellNum();
		String[][] data=new String[rows][cols];
		for(int i=0;i<rows;i++){
			for(int j=0;j<cols;j++){
				//data[i][j]=sheet.getRow(i).getCell(j).getStringCellValue();
				HSSFCell cell=sheet.getRow(i).getCell(j);
				if(cell.getCellType()==CellType.STRING)
					data[i][j]=cell.getStringCellValue();
				else if(cell.getCellType()==CellType.NUMERIC)
					data[i][j]=String.valueOf(cell.getNumericCellValue());
			
			}
		}
		return data;
	}
	
	/*
	 * Name of th Method:Execution Of Extent Report
	 * Brief Description:Click on Object
	 * Arguments:obj--->WebObj,textVal,ObjName
	 * CreatedBy:Automation Team
	 * Created Date:10/10/2018
	 * Last Modified Date:10/10/2018
	 */
	
	 public static void verifyText(WebElement element,String elementName,String expectedText ) throws IOException
		{
			if (element.isDisplayed())
			{	
				if (element.getText().contains(expectedText))
					logger.log(Status.PASS, MarkupHelper.createLabel( elementName+" is displayed as expected", ExtentColor.GREEN));
				else{
					logger.log(Status.FAIL, MarkupHelper.createLabel( elementName+"is NOT as expected", ExtentColor.RED));
				String imagePath=takeScreenShot();
				logger.addScreenCaptureFromPath(imagePath);
				}
			}
			else
			{
				logger.log(Status.FAIL, MarkupHelper.createLabel( elementName+" not found", ExtentColor.RED));
			}
		}

	 public static void verifyAttribute(WebElement element,String value,String elementName,String expectedText ) throws IOException
		{
			if (element.isDisplayed())
			{	
				if (element.getAttribute(value).equals(expectedText))
					logger.log(Status.PASS, MarkupHelper.createLabel( elementName+" is displayed as expected", ExtentColor.GREEN));
				else{
					logger.log(Status.FAIL, MarkupHelper.createLabel( elementName+"is NOT as expected", ExtentColor.RED));
				String imagePath=takeScreenShot();
				logger.addScreenCaptureFromPath(imagePath);
				}
			}
			else
			{
				logger.log(Status.FAIL, MarkupHelper.createLabel( elementName+" not found", ExtentColor.RED));
			}
		}

	public static String takeScreenShot() throws IOException{
		String reportPath=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String curDir=System.getProperty("user.dir");
		String destination=curDir+"/ScreenShot/"+reportPath+"image.PNG";
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(destination),true);
		return destination;
	}
	public static void executionReport(String reportName) {
		String timeNow=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		System.out.println(reportName+"_"+timeNow+".html");	
		String curDir=System.getProperty("user.dir");

		htmlReporter = new ExtentHtmlReporter(curDir+"/ExtentReport/" + reportName+"_"+timeNow+".html");
		extent = new ExtentReports ();
		extent.attachReporter(htmlReporter);
	}
	
	
	/*
	 * Name of th Method:ClickObj
	 * Brief Description:Click on Object
	 * Arguments:obj--->WebObj,textVal,ObjName
	 * CreatedBy:Automation Team
	 * Created Date:10/10/2018
	 * Last Modified Date:10/10/2018
	 */
	
	
	public static void enterText(WebElement obj,String textVal,String objName) {
	if(obj.isDisplayed()) {
		obj.clear();
		obj.sendKeys(textVal);
		logger.log(Status.PASS, MarkupHelper.createLabel(textVal +" is Entered in" + objName +"Field", ExtentColor.GREEN));
	    }
		else {
			logger.log(Status.FAIL,MarkupHelper.createLabel(objName +" field does not exist, please check your application", ExtentColor.RED));
		}
		}
	
	


	/*
	 * Name of th Method:EnterTextKeys
	 * Brief Description:Enter Text with keys for AutoSuggestive
	 * Arguments:obj--->WebObj,textVal,ObjName
	 * CreatedBy:Automation Team
	 * Created Date:10/10/2018
	 * Last Modified Date:10/10/2018
	 */
	
	
	public static void enterTextKeys(WebElement obj,Keys textVal,String objName) {
	if(obj.isDisplayed()) {
		obj.clear();
		obj.sendKeys(textVal);
		logger.log(Status.PASS, MarkupHelper.createLabel("Selecting"+objName , ExtentColor.GREEN));
	    }
		else {
			logger.log(Status.FAIL,MarkupHelper.createLabel(objName +" field does not exist, please check your application", ExtentColor.RED));
		}
		}
	
	/*
	 * Name of th Method:ClickObj
	 * Brief Description:Click on Object
	 * Arguments:obj--->WebObj,textVal,ObjName
	 * CreatedBy:Automation Team
	 * Created Date:10/10/2018
	 * Last Modified Date:10/10/2018
	 */
	public static void clickObj(WebElement obj,String objName) {
	if(obj.isDisplayed()) {
		obj.click();
		logger.log(Status.PASS, MarkupHelper.createLabel(objName +" is clicked", ExtentColor.GREEN));
		}
		else {
			logger.log(Status.FAIL,MarkupHelper.createLabel(objName +" field does not exist, please check your application", ExtentColor.RED));

		}
}
	
	
	/*
	 * Name of th Method:CheckRadio
	 * Brief Description:Click on Radio
	 * Arguments:obj--->WebObj,ObjName
	 * CreatedBy:Automation Team
	 * Created Date:10/10/2018
	 * Last Modified Date:10/10/2018
	 */
	public static void checkBoxObj(WebElement obj,String objName) {
	if(obj.isDisplayed()) {
		if(obj.isSelected()) {
			logger.log(Status.PASS, MarkupHelper.createLabel(objName +" is already selected", ExtentColor.GREEN));

		}
		else {
		obj.click();
		logger.log(Status.PASS, MarkupHelper.createLabel(objName +" is clicked", ExtentColor.GREEN));
		}
	}
	else {
		logger.log(Status.PASS, MarkupHelper.createLabel(objName +" is not ClicKable", ExtentColor.RED));
	}
	}
		

	/*
	 * Name of th Method:Dropdown
	 * Brief Description:Click on dropdown
	 * Arguments:obj--->WebObj,listobj,objname
	 * CreatedBy:Automation Team
	 * Created Date:10/10/2018
	 * Last Modified Date:10/10/2018
	 */
	public static void dropdown(WebElement obj,List<WebElement> listObj,String objName) {
		
	if(obj.isDisplayed()) {
	obj.click();
	/*System.out.println("The DropdownMenu tabs are");
	for (int i = 0; i < listObj.size(); i++) {
		System.out.println(listObj.get(i).getText());
		}
	
	*/
//System.out.println("Select the"+objName+"Text");

	for (int i = 0; i < listObj.size(); i++) {
		
		if(listObj.get(i).getText().contains(objName)) {
			listObj.get(i).click();	
			break;	
		}	
	}
	logger.log(Status.PASS, MarkupHelper.createLabel("Pass:"+objName +" is Succesfully Selected", ExtentColor.GREEN));

}
	else {
		logger.log(Status.FAIL, MarkupHelper.createLabel("Fail:"+objName +" +"
				+ "Cannot be Selected", ExtentColor.RED));

	}
	}
	/*
	 * Name of th Method:getTitle
	 * Brief Description:get thetitle displayed
	 * Arguments:obj--->webObj---.text
	 * CreatedBy:Automation Team
	 * Created Date:10/10/2018
	 * Last Modified Date:10/10/2018
	 */
	public static String getTitle() {

				logger.log(Status.INFO, MarkupHelper.createLabel("The title of the page is "+driver.getTitle(), ExtentColor.GREEN));	   
		
		        return driver.getTitle();
		
	
}


	/*
	 * Name of th Method:getText
	 * Brief Description:get the text displayed
	 * Arguments:obj--->webObj---.text
	 * CreatedBy:Automation Team
	 * Created Date:10/10/2018
	 * Last Modified Date:10/10/2018
	 */
	public static String getText(WebElement text,String value) {
		if(text.isDisplayed()) {
			logger.log(Status.INFO, "The "+value+" displayed is:");
			logger.log(Status.INFO,  text.getText());	
			System.out.println(text.getText());
		}else {
			logger.log(Status.INFO,"No message is Displayed");
		}
		return text.getText();
		
	
}
	/*
	 * Name of th Method:getAttribute
	 * Brief Description:Attribute value isdisplayed
	 * Arguments:obj--->webObj---.text
	 * CreatedBy:Automation Team
	 * Created Date:10/10/2018
	 * Last Modified Date:10/10/2018
	 */
	public static String getAttribute(WebElement text,String attValue,String value) {
		if(text.isDisplayed()) {
			
			logger.log(Status.INFO, "The "+value+" displayed is:");
			logger.log(Status.INFO,  text.getAttribute(attValue));
			System.out.println(text.getAttribute(attValue));
		}else {
			logger.log(Status.INFO,"No message is Displayed");
		}
		
		return text.getAttribute(attValue);
		
	
}
	
	/*public static void launchBrowser(String browser) {
		if(browser.equalsIgnoreCase("firefox")) {
			logger.log(Status.INFO, MarkupHelper.createLabel("Launching"+browser+"browser ", ExtentColor.GREEN));

		    System.setProperty("webdriver.gecko.driver","./src/utility/geckodriver.exe");	   
		    driver=new FirefoxDriver();
			logger.log(Status.INFO, MarkupHelper.createLabel(browser+"browser is launched Succesfully ", ExtentColor.GREEN));

		}
		else if(browser.equalsIgnoreCase("Chrome")) {
			logger.log(Status.INFO, MarkupHelper.createLabel("Launching"+browser+"browser ", ExtentColor.GREEN));
		    System.setProperty("webdriver.chrome.driver","./src/utility/chromedriver.exe"); 
	   
		    driver=new ChromeDriver();
			logger.log(Status.INFO, MarkupHelper.createLabel(browser+"browser is launched Succesfully ", ExtentColor.GREEN));
			
		}
		else {
			logger.log(Status.INFO, MarkupHelper.createLabel("Please provide the Browser Name ", ExtentColor.RED));

	}
	}*/
	
	public static void GetApplication(String url) {

		driver.get(url);
		logger.log(Status.PASS, MarkupHelper.createLabel("Pass:"+url +" is Launched Succesfully ", ExtentColor.GREEN));

	}
	
	public static void checkGetTitle(String gettitleName,String titleName) {
		
		System.out.println(driver.getTitle());
		if(driver.getTitle().contains(gettitleName)) {
			logger.log(Status.PASS, MarkupHelper.createLabel("Pass:The "+titleName+"page is loaded Succesfully ", ExtentColor.GREEN));

		}
		else {
			logger.log(Status.FAIL, MarkupHelper.createLabel("Fail: The "+titleName+"Page is not Openend", ExtentColor.RED));

		}

	}
	
	
	public static void checkGetText(WebElement text,String textName) {		
	System.out.println(text.getText());	
	if(text.getText().contains(textName)) {
	logger.log(Status.PASS, MarkupHelper.createLabel("Pass:The Test Case is Passed Succesfully ", ExtentColor.GREEN));
}
else {
	logger.log(Status.FAIL, MarkupHelper.createLabel("Fail: The Test case is Failed", ExtentColor.RED));

}
	
}
	
	public static void close() {		
	driver.close();
	logger.log(Status.PASS, MarkupHelper.createLabel("The Driver is Closed Successfully ", ExtentColor.GREEN));
}
	
}


