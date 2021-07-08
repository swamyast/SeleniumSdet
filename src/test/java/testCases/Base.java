package testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import utility.ReadConfig;

public class Base {
	ReadConfig readconfig=new ReadConfig();
	public String BaseUrl=readconfig.getPropertyValue("BaseUrl");
	public String Password=readconfig.getPropertyValue("Password");
	public String UserName=readconfig.getPropertyValue("UserName");
	public static WebDriver driver;
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setUp(String browser) {
		logger=Logger.getLogger("eBanking");
		PropertyConfigurator.configure("log4j.properties");
		
		if(browser.contentEquals("firefox")) {
			System.setProperty("webdriver.gecko.driver", readconfig.getPropertyValue("FireFoxPath"));
			driver=new FirefoxDriver();
		}
		else if(browser.contentEquals("chrome")) {
			System.setProperty("webdriver.chrome.driver", readconfig.getPropertyValue("ChromePath"));
			driver=new ChromeDriver();
		}
		
		driver.get(BaseUrl);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		logger.info("Opened URL "+BaseUrl);
		
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	public void captureScreen(WebDriver driver,String tcName) {
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		File target=new File(System.getProperty("user.dir")+"/screenshots/"+tcName+".png");
		try {
			FileUtils.copyFile(source, target);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
