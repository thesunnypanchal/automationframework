package unicodeTech.basePackage;

/*
1. Initialize and load properties file
2. Launch browser
3. Initialize WebDriver
4. Maximize browser
5. Define Timeunit
6. Object of ExcelFileReader class
7. Initiale Logger
8. Initialize ExtentReport
9. Delete Cookies
10. Create method isElementPresent 
*/

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//import org.apache.log4j.BasicConfigurator;
//import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import unicodeTech.utility.ExcelFileReader;

public class BaseInit {

	public static WebDriver driver;
	public static Properties sitedata;
	public static Properties storage;
	//public static Logger logs;
	public static ExcelFileReader suite;
	public static ExcelFileReader tsa;
	public static ExcelFileReader tsb;
	public static ExcelFileReader tsc;
	public static ExtentHtmlReporter reporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"//src//main//resources//unicodeTech//reports//TestReport.html");
	public static ExtentReports reports = new ExtentReports();
	public static ExtentTest test;
	
	public void startUP() throws Exception {
	
		if(driver==null) {
			
		test = reports.createTest(this.getClass().getSimpleName());
		reports.setSystemInfo("Environment", "Production");
		reports.setSystemInfo("Tester Name", "Jepser");
		
		reports.attachReporter(reporter);

		test.log(Status.INFO, "SiteData properties file is loading now");
		sitedata = new Properties();
		FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//unicodeTech//propertiesData//SiteData.properties");
		sitedata.load(fi);
		test.log(Status.INFO, "SiteData properties file loaded");
		test.log(Status.INFO, "ObjectStorage properties file is loading now");

		storage = new Properties();
		fi = new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//unicodeTech//propertiesData//ObjectStorage.properties");
		storage.load(fi);
		test.log(Status.INFO, "ObjectStorage properties file loaded");

		String browserKey = sitedata.getProperty("browser");
		
		test.log(Status.INFO, "Browser will be launching");

		
		if(browserKey.equalsIgnoreCase("firefox")) {
			
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\dj\\Downloads\\geckodriver.exe");
		
			driver = new FirefoxDriver();
			test.log(Status.INFO, "Firefox Browser launched");

		}else if(browserKey.equalsIgnoreCase("chrome")) {
			
			System.setProperty("webdriver.chrome.driver",
					"E:\\Unicode\\chrome\\chromedriver.exe");
		
			driver = new ChromeDriver();
			test.log(Status.INFO, "Firefox Browser launched");

		}else if(browserKey.equalsIgnoreCase("ie")) {
			
			driver = new InternetExplorerDriver();
		
		}
		
		driver.manage().window().maximize();
		test.log(Status.INFO, "Window Maximized");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		test.log(Status.INFO, "Timeunit defined");

		driver.manage().deleteAllCookies();
		test.log(Status.INFO, "Cookies deleted");

		suite = new ExcelFileReader(System.getProperty("user.dir")+"//src//main//resources//unicodeTech//testInformation//TestSuite.xlsx");
		tsa = new ExcelFileReader(System.getProperty("user.dir")+"//src//main//resources//unicodeTech//testInformation//TestSuiteA.xlsx");
		tsb = new ExcelFileReader(System.getProperty("user.dir")+"//src//main//resources//unicodeTech//testInformation//TestSuiteB.xlsx");
		tsc = new ExcelFileReader(System.getProperty("user.dir")+"//src//main//resources//unicodeTech//testInformation//TestSuiteC.xlsx");
		test.log(Status.INFO, "ExcelFile object created");

		reports.flush();
	}
	}
	
	public static WebElement isElementPresent(String propKey) {
		
		try {
			
			if(propKey.contains("xpath")) {
				
				return driver.findElement(By.xpath(storage.getProperty(propKey)));
			
			}else if(propKey.contains("id")) {
				
				return driver.findElement(By.id(storage.getProperty(propKey)));
			
			}if(propKey.contains("name")) {
				
				return driver.findElement(By.name(storage.getProperty(propKey)));
			
			}if(propKey.contains("linkText")) {
				
				return driver.findElement(By.linkText(storage.getProperty(propKey)));
			
			}else {
				
				System.out.println("Element not found in the properties file");
				return null;
			}
			
		}catch(Exception e) {
			
			System.out.println("Element not found in the properties file");

		}
		return null;
		
	}
	
	
}
