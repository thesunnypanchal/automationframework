package unicodeTech.TestSuiteA;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import unicodeTech.utility.MyMethods;

public class ExtractManufacturer extends ChildTestSuiteA {

	@BeforeTest
	public void checkTestCase() {
		
		boolean output = MyMethods.checkTestCaseExecution(tsa, "TestCase", this.getClass().getSimpleName());
		if(!output) {
			throw new SkipException("Execution mode of the test case "+this.getClass().getSimpleName()+" is set to NO"); 
		}
	}
	@Test
	public void testExtractManufacturer() throws InterruptedException {
		
		
	driver.get(sitedata.getProperty("url"));
	WebElement menufacturers = isElementPresent("dd_manufacturers_name");
	List<WebElement> manuValues = menufacturers.findElements(By.tagName("option"));
	
	for(int count = 1;count<manuValues.size();count++) {
		
		manuValues.get(count).click();
		Thread.sleep(2000);
		//MyMethods.getScreenShot("Manufacturer",driver);
		
		driver.navigate().back();
		menufacturers = isElementPresent("dd_manufacturers_name");
		manuValues = menufacturers.findElements(By.tagName("option"));
	}
	
	}
}
 