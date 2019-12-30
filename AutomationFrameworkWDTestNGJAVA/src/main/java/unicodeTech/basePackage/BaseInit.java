package unicodeTech.basePackage;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import unicodeTech.utility.ExcelFileReader;

public class BaseInit {
	
	public static  WebDriver driver;
	public static Properties sitedata;
	public static Properties storage;
	public static ExcelFileReader suite;
	public static ExcelFileReader tsa;
	public static ExcelFileReader tsb;
	public static ExcelFileReader tsc;
	public static ExtentHtmlReporter reporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"//src//main//resources//unicodeTech//reports//Testreport.html");
	public static ExtentReports reports = new ExtentReports();
	public static ExtentTest test;
	
	public void startUP() throws Exception {
		
		test = reports.createTest(this.getClass().getSimpleName());
		reports.setSystemInfo("Environment", "Production");
		reports.setSystemInfo("Tester Name", "Jepster");
		
		reports.attachReporter(reporter);
		
		sitedata = new Properties();
		FileInputStream fi = new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//unicodeTech//propertiesData//SiteData.properties");
		sitedata.load(fi);
		
		storage = new Properties();
		fi = new FileInputStream(System.getProperty("user.dir")+"//src//main//resources//unicodeTech//propertiesData//ObjectStorage.properties");
		storage.load(fi);
		
		String browserkey = sitedata.getProperty("browser");
		
		if(browserkey.equalsIgnoreCase("firefox")) {
			
			System.getProperty("webdriver.gecko.driver",
					"D:\\Software\\Testing-Tools\\Selenium\\WebDriver\\Drivers\\IEChromeFirefox\\19092018\\geckodriver.exe");
					driver = new FirefoxDriver();
					
		}else if(browserkey.equalsIgnoreCase("chrome")){
			
			System.getProperty("webdriver.chrome.driver",
			"D:\\Software\\Testing-Tools\\Selenium\\WebDriver\\Drivers\\IEChromeFirefox\\19092018\\chromedriver.exe");
			driver = new ChromeDriver();
			
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		
		suite = new ExcelFileReader(System.getProperty("user.die")+"//src//main//resources//unicodeTech//tesInformation//TestSuite.xlsx");
		tsa = new ExcelFileReader(System.getProperty("user.die")+"//src//main//resources//unicodeTech//tesInformation//TestSuiteA.xlsx");
		tsb = new ExcelFileReader(System.getProperty("user.die")+"//src//main//resources//unicodeTech//tesInformation//TestSuitB.xlsx");
		tsc = new ExcelFileReader(System.getProperty("user.die")+"//src//main//resources//unicodeTech//tesInformation//TestSuitC.xlsx");
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
