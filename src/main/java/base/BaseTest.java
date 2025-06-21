package base;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utils.ExtentReportManager;
import utils.Log;

public class BaseTest {

	protected WebDriver driver;

	@BeforeMethod
	public void setUp() {
		Log.info("Setting WebDriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		Log.info("Navigating to URL...");
		driver.get("https://admin-demo.nopcommerce.com/login");
	}

	@AfterMethod
//	public void tearDown() {
//		if(driver!=null)
//		{
//			Log.info("Closing Browser...");
//			driver.quit();
//		}
//	}
	
	public void tearDown(ITestResult result) {
	    if (result.getStatus() == ITestResult.FAILURE) {
	        ExtentReportManager.getTest().fail(result.getThrowable());
	    } else if (result.getStatus() == ITestResult.SUCCESS) {
	        ExtentReportManager.getTest().pass("Test passed");
	    }

	    ExtentReportManager.flushReport();

	    if (driver != null) {
	        Log.info("Closing Browser...");
	        driver.quit();
	    }
	}
}
