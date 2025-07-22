package ninja.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	
	  int count = 0; int maxTry = 1;
	  
	  @Override public boolean retry(ITestResult result) {
	  
	  if (count < maxTry) { count++; return true; } return false; }
	 
	/*
	 * private int retryCount = 0; private static final int maxRetryCount = 2; //
	 * Max retry attempts
	 * 
	 * @Override public boolean retry(ITestResult result) { if (retryCount <
	 * maxRetryCount) { retryCount++; return true; // Retry the test } return false;
	 * // Stop retrying }
	 */
}