package unicodeTech.TestSuiteC;

import org.testng.SkipException;
import org.testng.annotations.BeforeSuite;

import unicodeTech.basePackage.BaseInit;
import unicodeTech.utility.MyMethods;

public class ChildTestSuiteC extends BaseInit {

		@BeforeSuite
		public void checkTestsuit() throws Exception {
			
			startUP();
			boolean output = MyMethods.checkTestSuiteExecution(suite, "TestSuite", "TestSuiteC");
					
					if(!output) {
						throw new SkipException("Execution mode of the test suite TestSuiteC is set to NO");
					}
		}
	}

