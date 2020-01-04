package unicodeTech.TestSuiteA;

import org.testng.SkipException;

import org.testng.annotations.BeforeSuite;

import unicodeTech.basePackage.BaseInit;
import unicodeTech.utility.MyMethods;

public class ChildTestSuiteA extends BaseInit{

	@BeforeSuite
	public void checkTestsuite() throws Exception {
		startUP();
		boolean output = MyMethods.checkTestSuiteExecution(suite, "TestSuite", "TestSuiteA");
		
		if(!output) {
			
			throw new SkipException("Execution mode of the test suite TestSuiteA is set to NO");
		}
	}
}
