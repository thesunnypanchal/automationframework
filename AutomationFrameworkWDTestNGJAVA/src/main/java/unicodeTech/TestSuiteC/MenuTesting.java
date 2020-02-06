package unicodeTech.TestSuiteC;


import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import unicodeTech.utility.MyMethods;

public class MenuTesting extends ChildTestSuiteC {

	@BeforeTest
	public void checkTestCase() {
		boolean output = MyMethods.checkTestCaseExecution(tsc, "TestCase", this.getClass().getSimpleName());
		if(!output) {
			throw new SkipException("Execution mode of the test case "+this.getClass().getSimpleName()+" is set to NO");
		}
	}
	@Test
	public void testMenuTesting() {
		
		driver.get(sitedata.getProperty("url"));
		
		WebElement menus = isElementPresent("div_menu_xpath");
		List<WebElement> allMenu = menus.findElements(By.tagName("a"));
		
			for(int count=0;count<allMenu.size();count++) {
				System.out.println(allMenu.get(count).getText());
			}
	}
}
