package test;



import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.Log;

public class LoginTest extends BaseTest{
	
	@Test
	public void testValidLogin() {
	Log.info("Starting Login test...");
		LoginPage loginPage=new LoginPage(driver);	
		
		Log.info("Adding Credentials");
		loginPage.enterUsername("admin@yourstore.com");
		loginPage.enterPassword("admin");
		loginPage.clickLogin();
	}

}
