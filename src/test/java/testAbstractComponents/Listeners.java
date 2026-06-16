package testAbstractComponents;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utilities.ExtendReport;

public class Listeners implements ITestListener {

	ExtentReports extentReport = ExtendReport.getReportObject();

	// ⭐ Thread-safe test object
	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	@Override
	public void onTestStart(ITestResult result) {

		ExtentTest test = extentReport.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {

		extentTest.get().pass("Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		extentTest.get().fail(result.getThrowable());

		try {

			BaseTest baseTest = (BaseTest) result.getInstance();

			String path = baseTest.getScreenshot(BaseTest.driver, result.getMethod().getMethodName());

			extentTest.get().addScreenCaptureFromPath(path);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {

		extentTest.get().skip("Test Skipped");
	}

	@Override
	public void onFinish(ITestContext context) {

		extentReport.flush(); // ⭐ VERY IMPORTANT
	}
}