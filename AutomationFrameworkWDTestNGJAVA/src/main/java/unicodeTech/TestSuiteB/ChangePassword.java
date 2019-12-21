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
		
	}

}
