package NinjaTutorialQAUtiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.NoSuchElementException;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.reporters.jq.Main;

import com.google.common.base.Function;

public class Utilities {

	public static final int Implicit_Wait_Time = 15;
	public static final int PageLoad_Time = 10;

	public static String generateEmailAdreeTimeStamp() {
		Date date = new Date();
		String timestamp = date.toString().replace(" ", "_").replace(":", "_");
		return "mahendra" + timestamp + "@gmail.com";
	}

	public static Object[][] getTetstDataFromExcel(String sheetName) {

		File excelFile = new File(
				System.getProperty("user.dir") + "\\src\\main\\java\\qa\\testData\\ninjaqaTestData.xlsx");
		FileInputStream fisExcel;
		XSSFWorkbook workbook = null;

		try {
			fisExcel = new FileInputStream(excelFile);
			workbook = new XSSFWorkbook(fisExcel);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// get specific sheet
		XSSFSheet sheet = workbook.getSheet(sheetName);

		// count number of rows and columns
		int rows = sheet.getLastRowNum();
		int colums = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rows][colums];
		for (int i = 0; i < rows; i++) {
			XSSFRow row = sheet.getRow(i + 1);

			for (int j = 0; j < colums; j++) {
				XSSFCell cell = row.getCell(j);
				// CellType cellType = cell.getCellType();
				CellType cellType = cell.getCellTypeEnum();
				switch (cellType) {
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = Integer.toString((int) cell.getNumericCellValue());
					break;
				case BLANK:
					break;
				case BOOLEAN:
					break;
				case ERROR:
					break;
				case FORMULA:
					break;
				case _NONE:
					break;
				default:
					break;
				}
			}
		}
		return data;

	}

	public static String captureScreenshot(WebDriver driver, String testName) {
		// File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		// FileUtils.copyFile(src, new
		// File("C://Users//Mahendrakumar_Katkar//Screenshots.png"));

		File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + testName + ".png";
		try {
			org.openqa.selenium.io.FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destinationScreenshotPath;
	}

	
	/*
	 * public class ScreenshotUtil {
	 * 
	 * WebDriver driver;
	 * 
	 * public ScreenshotUtil(WebDriver driver) { this.driver = driver; }
	 * 
	 * public void getScreenshot(String testcaseName) throws IOException {
	 * TakesScreenshot ts = (TakesScreenshot) driver; File source =
	 * ts.getScreenshotAs(OutputType.FILE); File destination = new
	 * File(System.getProperty("user.dir") + "/reports/" + testcaseName + ".png");
	 * FileUtils.copyFile(source, destination); } }
	 */
	  
	/*
	 * public static void name(WebDriver driver) { Wait<WebDriver> wait1 = new
	 * FluentWait<>(driver) .withTimeout(Duration.ofSeconds(15))
	 * .pollingEvery(Duration.ofSeconds(3)) .ignoring(NoSuchElementException.class);
	 * 
	 * WebElement timeoutEl = wait1.until(new Function<WebDriver, WebElement>() {
	 * public WebElement apply(WebDriver driver) { return
	 * driver.findElement(By.id("yourElementId")); // Replace with actual ID } });
	 * 
	 * 
	 * WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(5));
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(
	 * ".ta-results")));
	 * 
	 * }
	 */
}
