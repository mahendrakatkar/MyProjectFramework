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
import groovy.transform.Field;

public class myListeners implements ITestListener {

	ExtentReports extentReport;
	ExtentTest extendTest;

	@Override
	public void onStart(ITestContext context) {			//**** its context not result

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

	// for getting screenshot > just make any assertion value wrong like in testData property emailPasswordNomatchWarning
		//WebDriver driver = null;
		
		WebDriver driver = null;
		/*
		 * try { driver = (WebDriver)
		 * result.getTestClass().getRealClass().getDeclaredField("driver").get(result.
		 * getInstance()); } catch (IllegalArgumentException | IllegalAccessException |
		 * NoSuchFieldException | SecurityException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */
		try {
		    java.lang.reflect.Field driverField = result.getTestClass().getRealClass().getDeclaredField("driver");
		    driverField.setAccessible(true);  // Ensure access to private fields
		    driver = (WebDriver) driverField.get(result.getInstance());
		} catch (NoSuchFieldException | IllegalAccessException e) {
		    e.printStackTrace();
		    throw new RuntimeException("Unable to retrieve WebDriver instance from the test class: " + e.getMessage());
		}
		String destinationScreenshotPath = Utilities.captureScreenshot(driver, result.getName());
		extendTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extendTest.log(Status.INFO, result.getThrowable());
		extendTest.log(Status.FAIL, result.getName() + " got failed.");

		   // Attach RetryAnalyzer on failure
						/*
			 * Retry retryAnalyzer = new Retry(); if (retryAnalyzer.retry(result)) {
			 * result.setStatus(ITestResult.SUCCESS); // Mark as passed if retry is
			 * successful }
			 */
			  // 	imp about above code
				// If test cases are failing but the TestNG console output still shows all tests as passed, it usually means there is an issue with how the result status (ITestResult) is being modified or reported by your TestListener. Here's an analysis of likely causes and solutions:
				//  In  listener's onTestFailure method, you are possibly modifying the test result incorrectly, or the retry logic is interfering with the final result. Specifically, the following part of your listener code could be the issue:

			  
			  
			 
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
