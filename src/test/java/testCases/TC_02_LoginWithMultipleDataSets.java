package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.LoginPage;
import utility.ExcelUtil;

public class TC_02_LoginWithMultipleDataSets extends Base {
	
	@Test(dataProvider = "LoginCredentials")
	public void Login(String UserName,String Password) {
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(UserName);
		logger.info("Entered User Name "+UserName);
		lp.setPassword(Password);
		logger.info("Entered Password "+Password);
		lp.Login();
		System.out.println(driver.getTitle());
		if(driver.getTitle().equals("Execute Automation")) {
			lp.LogOut();
			Assert.assertTrue(true);
			
		}
		else
			Assert.assertTrue(false);
	}
		
	
	@DataProvider(name="LoginCredentials")
	public String[][] getData() throws IOException{
		
		String ExcelFile=System.getProperty("user.dir")+"\\src\\test\\java\\testData\\TestData.xlsx";
		int rowNum=ExcelUtil.getRowcount(ExcelFile, "Sheet1");
		int columnNum=ExcelUtil.getColumnCount(ExcelFile, "Sheet1", 1);
		
		String arr[][]=new String[rowNum][columnNum];
		for(int i=1;i<rowNum;i++) {
			for(int j=0;j<columnNum;j++) {
				arr[i-1][j]=ExcelUtil.getCellData(ExcelFile, "Sheet1", i, j);
			}
		}
		return arr;
	}
}
