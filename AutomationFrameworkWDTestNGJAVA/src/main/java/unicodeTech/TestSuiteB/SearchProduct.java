package unicodeTech.TestSuiteB;

import org.openqa.selenium.Keys;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import unicodeTech.utility.MyMethods;

public class SearchProduct extends ChildTestSuiteB {
	
	@BeforeTest
	public void checkTestCase() {
		
		boolean output = MyMethods.checkTestCaseExecution(tsb, "TestCase", this.getClass().getSimpleName());
		if(!output) {
			throw new SkipException("Execution mode of the test case "+this.getClass().getSimpleName()+" is set to NO");
		}
	}

	@Test(dataProvider = "getTestDataFromXLS")
	public void testSearchProduct(String keyword) {
		
		driver.get(sitedata.getProperty("url"));
		
		MyMethods.isElementPresent("ip_searchkey_name").sendKeys(keyword);
		MyMethods.isElementPresent("ip_searchkey_name").sendKeys(Keys.ENTER);
		MyMethods.getScreenShot("Searchresult", driver);
	}
	
	@DataProvider
	public Object[][] getTestDataFromXLS(){
		
		return MyMethods.getTestData(tsb, "SearchProduct");
	}
}
