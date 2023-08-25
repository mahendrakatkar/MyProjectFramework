package ninja.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import NinjaTutorialQAUtiles.Utilities;
import NinjaTutorialQAUtiles.extentReporter;

public class myListeners implements ITestListener {

	ExtentReports extentReport;
	ExtentTest extendTest;

	@Override
	public void onStart(ITestContext context) {

		extentReport = extentReporter.generateExtendRepot();
		// System.out.println("Execution of project started.");
	}

	@Override
	public void onTestStart(ITestResult result) {

		extendTest = extentReport.createTest(result.getName());
		extendTest.log(Status.INFO, result.getName() + " started execting...");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extendTest.log(Status.PASS, result.getName() + " got successfully executed.");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String destinationScreenshotPath = Utilities.captureScreenshot(driver, result.getName());
		extendTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extendTest.log(Status.INFO, result.getThrowable());
		extendTest.log(Status.FAIL, result.getName() + " got failed.");

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		extendTest.log(Status.INFO, result.getThrowable());
		extendTest.log(Status.SKIP, result.getName() + " got skipped.");

	}

	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush(); // line is compulsory, else other above nothing will be useful
		String PathOfExtentReport= System.getProperty("user.dir")+ "\\test-output\\ExtentReports\\extentReport.html";
File extentReport = new File(PathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
