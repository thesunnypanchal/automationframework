package unicodeTech.TestSuiteB;

import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import unicodeTech.utility.MyMethods;

public class ChangePassword extends ChildTestSuiteB  {
	
	@BeforeTest
	public void checkTestCase() {
		
		boolean output  =  MyMethods.checkTestCaseExecution(tsb, "TestCase", this.getClass().getSimpleName());
		if(!output) {
			throw new SkipException("Execution mode of the test case "+this.getClass().getSimpleName()+" is set to NO");
		}
	}
	
	
	@Test
	public void testChangePassword() {
		

		driver.get(sitedata.getProperty("url"));
		MyMethods.signIN("demo1@unicodetechnologies.in", "unicode1");
		MyMethods.isElementPresent("lnk_myaccount_linkText").click();
		MyMethods.isElementPresent("lnk_changemypass_linkText").click();
		MyMethods.isElementPresent("ip_current_password_name").sendKeys("unicode1");
		MyMethods.isElementPresent("ip_new_password_name").sendKeys("unicode123");
		MyMethods.isElementPresent("ip_confirm_password_name").sendKeys("unicode123");
		
		MyMethods.isElementPresent("btn_coninue_pwd_id").click();
		
		System.out.println("Password has been changed successfully");
		
		MyMethods.signOut();
		
		System.out.println("Login with new password");
		
		MyMethods.signIN("demo1@unicodetechnologies.in", "unicode123");
		
		MyMethods.isElementPresent("lnk_myaccount_linkText").click();
		MyMethods.isElementPresent("lnk_changemypass_linkText").click();
		MyMethods.isElementPresent("ip_current_password_name").sendKeys("unicode123");
		MyMethods.isElementPresent("ip_new_password_name").sendKeys("unicode1");
		MyMethods.isElementPresent("ip_confirm_password_name").sendKeys("unicode1");
		
		MyMethods.isElementPresent("btn_coninue_pwd_id").click();
		
		System.out.println("Password has been changed successfully");
		
	}

}
