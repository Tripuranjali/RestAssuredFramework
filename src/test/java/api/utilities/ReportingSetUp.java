package api.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import api.endpoints.UserRoutes;



	public class ReportingSetUp implements ITestListener{
		public ExtentSparkReporter htmlreporter;
		public ExtentTest logger;
		public static ExtentReports extentReports;
		

    	public void onStart(ITestContext testContext) {
		
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName = "Test-Report-"+timestamp+".html";
		htmlreporter = new ExtentSparkReporter(System.getProperty("user.dir")+ "/TestExecutionReports/"+ repName);
		try {
			htmlreporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		extentReports = new ExtentReports();
		
		extentReports.attachReporter(htmlreporter);
		extentReports.setSystemInfo("Project Name", "RestAssured");
		extentReports.setSystemInfo("Environment", "Test");
		extentReports.setSystemInfo("Tester", "Tripuranjali");

		
		htmlreporter.config().setDocumentTitle("Selenium Java Hybrid Framework Test Automation");
		htmlreporter.config().setReportName("RestAssured API Test Report");
		htmlreporter.config().setTheme(Theme.DARK);	
		
	}	

    	public void onTestSuccess(ITestResult tr) {
    		
    		logger = extentReports.createTest(tr.getName());
    		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN))
    				.log(Status.INFO, "Response is: "+UserRoutes.resp.prettyPrint());
    		
    		

    	}
    	
    	public void onTestFailure(ITestResult tr)  {
    		
    		logger = extentReports.createTest(tr.getName());
    		String screenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
    		
    		logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED));
    		logger.log(Status.FAIL, tr.getThrowable());
    		
    		
    		File f = new File(screenshotPath);
    		
    		if(f.exists())
    		{
    		logger.fail(MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());		
    		}

    	}
    	
    	public void onTestSkipped(ITestResult tr) {
    		
    		logger = extentReports.createTest(tr.getName());
    		String screenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+tr.getName()+".png";
    		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
    		
    		File f = new File(screenshotPath);
    		
    		if(f.exists())
    		{
    		logger.skip(MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());		
    		}
    	}
    	
    	public void onFinish(ITestContext testContext) {
    		extentReports.flush();
    	}
	
}