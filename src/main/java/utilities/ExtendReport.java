package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReport {
	
	public static ExtentReports getReportObject()
	{
		String path = System.getProperty("user.dir") + "//reports//ExtentReport.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		
		reporter.config().setDocumentTitle("Testing Result Document");
		reporter.config().setReportName("Automation Testing Report");
		
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(reporter);
		
		extent.setSystemInfo("Tester", "Abdul Rahaman");
		extent.setSystemInfo("Environment", "SIT");
		
		return extent;
		
	}

}
