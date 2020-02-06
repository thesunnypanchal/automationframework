package unicodeTech.utility;


import java.io.File;


import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import unicodeTech.utility.ExcelFileReader;

import unicodeTech.basePackage.BaseInit;


public class MyMethods extends BaseInit {

	
	public static void signIN(String email,String password) {
		
		driver.get(sitedata.getProperty("url"));
		isElementPresent("lnk_logurselfin_linkText").click();
		isElementPresent("ip_email_name").sendKeys(email);
		isElementPresent("ip_password_name").sendKeys(password);
		isElementPresent("btn_signIn_id").click();
	}
	
	public static void signOut() {
		
		isElementPresent("lnk_logoff_linkText").click();
		isElementPresent("btn_continue_id").click();
	}
	
	public static boolean checkTestSuiteExecution(ExcelFileReader data, String sheetName, String testsuiteID) {
		
		int rows = data.totalRow(sheetName);
		for(int row = 1;row < rows; row++) {
			String tsID = data.getData(sheetName, row, 0);
			if(tsID.equalsIgnoreCase(testsuiteID)) {
				String exemode = data.getData(sheetName, row, 2);
				if(exemode.equalsIgnoreCase("Y"))
					return true;
				else
					return false;
			}
		}
		return false;
					
	}
	
	public static boolean checkTestCaseExecution(ExcelFileReader data, String sheetName, String testcaseID) {
	
		int rows = data.totalRow(sheetName);
		for(int row = 1; row < rows; row++) {
			String tcID = data.getData(sheetName, row, 0);
			if(tcID.equalsIgnoreCase(testcaseID)) {
				String exeMode = data.getData(sheetName, row, 2);
				if(exeMode.equalsIgnoreCase("Y"))
					return true;
				else 
					return false;
			}
		}
		return false;
	}
	
	public static Object[][] getTestData(ExcelFileReader data, String sheetName) {
		
		int rows = data.totalRow(sheetName);
		int cols = data.totalColumn(sheetName);
		
		Object[][] myData = new Object[rows-1][cols];
		for(int row = 1;row<rows;row++) {
			for(int col = 0;col<cols;col++) {
				myData[row-1][col] = data.getData(sheetName, row, col);
			}
		}
		return myData;
	}
	
public static String getScreenShot(String imageName, WebDriver driver) {
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		
		File scrFile = ts.getScreenshotAs(OutputType.FILE);
		
		String path = System.getProperty("user.dir")+"\\src\\main\\resources\\unicodeTech\\screenshots\\"+imageName+System.currentTimeMillis()+".png";
		
		//System.out.println(path);
		File destination =new File(path);
		
	    try {
	    	
	    	FileHandler.copy(scrFile,destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}	   
	    
	    return path;
	}
}
