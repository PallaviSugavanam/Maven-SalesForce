package Framework;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.testng.annotations.Test;

 class DriverTest extends SFDC_Login {
@Test
	public static void mainmethod() throws  Exception {
	String curDir=System.getProperty("user.dir");

	String s_Path=curDir+"./src/test/resources/utility/TestSuite.xls";
    //String s="Login_Error_Message_1";
	String testCase=null;
	String flag=null;
    executionReport("Sprint1");
	String[][] testSuiteData=readxlData(s_Path, "Sheet1");
	for (int i = 1; i < testSuiteData.length; i++) {
		flag=testSuiteData[i][1];
		if(flag.equalsIgnoreCase("Y")) {
			testCase=testSuiteData[i][0];
			   Method ts=SFDC_Login.class.getMethod(testCase);
				ts.invoke(ts);
		}
			
				else {
					System.out.println("TestCase "+ testSuiteData[i][0]+"is not executed");
					
				}
		}
		
		
		    
		 
		   /* Login_Error_Message_1();
	
			Check_RememberMe_3();
		
			forgot_Password_4A();
	
		
		ValidateLoginErrorMessage_5();
			UserDropDownMenu();
		*/
		extent.flush();
	}

	}

