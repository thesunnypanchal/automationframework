package unicodeTech.TestSuiteA;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import unicodeTech.utility.MyMethods;


public class ValidateUser extends ChildTestSuiteA {

	@BeforeTest
	public void checkTestCase(){
		
		//call checktestexecution method

		boolean output = MyMethods.checkTestCaseExecution(tsa, "TestCase", this.getClass().getSimpleName());
		if(!output) {
			
			throw new SkipException("Execution mode of the test case "+this.getClass().getSimpleName()+" is set to NO");
		}		
	}
	@Test(dataProvider="getTestDataFromXLS")
	public void testValidateUser(String username, String password) {
		
		driver.get(sitedata.getProperty("url"));
		MyMethods.signIN(username, password);
		
		try {
			
			WebElement logOFflink = isElementPresent("lnk_logoff_linkText");
			
			if(logOFflink.isDisplayed()) {
				
				System.out.println("User session has started");
				MyMethods.getScreenShot("WelcomePageAfterLogin", driver);
				MyMethods.signOut();
			}
			
			}catch(Exception e) {
				
				System.out.println("Wrong Credential");
			}
	}
	
	@DataProvider
	public Object[][] getTestDataFromXLS() {

		return MyMethods.getTestData(tsa, "ValidateUser");
	}
}
