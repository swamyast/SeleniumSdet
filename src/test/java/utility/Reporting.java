package utility;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter{
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest extentTest;

	public void onStart(ITestContext textContext) {
		 String timeStamp=new SimpleDateFormat("yyy.MM.dd.HH.mm.ss").format(new Date());
		 String repName="Test-Report_"+timeStamp+".html";
		 System.out.println(System.getProperty("user.dir")+"\\test-output\\"+repName);
		 htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+"//test-output//"+repName);
		 
		 htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		 
		 extent=new ExtentReports();
		 extent.attachReporter(htmlReporter);
		 extent.setSystemInfo("Host Name", "localhost");
		 extent.setSystemInfo("Environment", "QA");
		 extent.setSystemInfo("user", "Swamy");
		 
		 htmlReporter.config().setDocumentTitle("Extent Demo Test Project");
		 htmlReporter.config().setReportName("Demo Extent Test Report");
		 htmlReporter.config().setTheme(Theme.DARK);
	}
	public void onTestSuccess(ITestResult tr) {
		extentTest=extent.createTest(tr.getName());
		extentTest.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));
	}
	
	public void onTestFailure(ITestResult tr) {
		extentTest=extent.createTest(tr.getName());
		extentTest.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));
		String ScreenShotPath=System.getProperty("user.dir")+"\\screenshots\\"+tr.getName()+".png";
		File file=new File(ScreenShotPath);
		if(file.exists()) {
			try {
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void onTestSkipped(ITestResult tr) {
		extentTest=extent.createTest(tr.getName());
		extentTest.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
}
