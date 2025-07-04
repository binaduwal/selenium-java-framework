package test;



import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.Log;


public class LoginTest extends BaseTest{
	@DataProvider(name="LoginData")
	public Object[][] getLoginData() throws IOException{
		String filePath=System.getProperty("user.dir")+"/testData/testdata.xlsx";
		System.out.println("Excel file path: " + filePath);

	ExcelUtils.loadExcel(filePath, "Sheet1");
	int rowCount=ExcelUtils.getRowCount();
	System.out.println("Row count in sheet: " + rowCount);

	
	if (rowCount <= 1) {
        throw new RuntimeException("No data found in Excel sheet or only header row present.");
    }
	Object[][] data=new Object[rowCount-1][2];
	
	for(int i=1;i<rowCount;i++) {
		data[i-1][0]=ExcelUtils.getCellData(i, 0);
		data[i-1][1]=ExcelUtils.getCellData(i, 1);
		
	}
	ExcelUtils.closeExcel();
	return data;
	
	}
	
	@DataProvider(name="LoginData2")
	public Object[][] getData()
			{
				return new Object[][] {
					{"user1","pass1"},
					{"user2","pass2"},
					{"user3","pass3"},
					
			};		
			}
	
	
//	@Test
//	@Parameters({"username","password"})
//	public void testValidLogin(String username,String password) {
//	Log.info("Starting Login test...");
//		LoginPage loginPage=new LoginPage(driver);	
//		
//		Log.info("Adding Credentials");
//		loginPage.enterUsername(username);
//		loginPage.enterPassword(password);
//		loginPage.clickLogin();
//	}
	
	@Test(dataProvider = "LoginData")
	public void testValidLogin(String username, String password) {
	    ExtentReportManager.createTest("Login Test with user: " + username);
	    Log.info("Starting Login test...");
	    
	    LoginPage loginPage = new LoginPage(driver);	
	    
	    Log.info("Adding Credentials");
	    loginPage.enterUsername(username);
	    loginPage.enterPassword(password);
	    loginPage.clickLogin();

	    ExtentReportManager.getTest().info("Login attempted with: " + username);
	}


}
