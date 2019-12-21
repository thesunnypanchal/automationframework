package unicodeTech.TestSuiteC;

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
		
	}
}
