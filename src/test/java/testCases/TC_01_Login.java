package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class TC_01_Login extends Base{
	@Test
	public void TC_01_VerifyLoginWithValidCredentials() {
		
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(UserName);
		logger.info("Entered User Name "+UserName);
		lp.setPassword(Password);
		logger.info("Entered Password "+Password);
		lp.Login();
		System.out.println(driver.getTitle());
		if(driver.getTitle().equals("Execute Automation")) {
			Assert.assertTrue(true);
		}
		else
			Assert.assertTrue(false);
	}
	
	@Test
	public void TC_02_VerifyLoginWithWrongTitle() {
		
		/*
		 * LoginPage lp=new LoginPage(driver); lp.setUserName(UserName);
		 * logger.info("Entered User Name "+UserName); lp.setPassword(Password);
		 * logger.info("Entered Password "+Password); lp.Login();
		 */
		System.out.println(driver.getTitle());
		if(driver.getTitle().equals("Execute Automationss")) {
			Assert.assertTrue(true);
		}
		else
			captureScreen(driver, "TC_02_VerifyLoginWithWrongTitle");
			Assert.assertTrue(false);
		
	}
}
