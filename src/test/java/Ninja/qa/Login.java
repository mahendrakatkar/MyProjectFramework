package Ninja.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import NinjaTutorialQAUtiles.Utilities;
import Ninja_BaseClass.Base;

public class Login extends Base {
		
	
	  public Login() { super(); }
	 
	 
	  WebDriver driver;
	@BeforeMethod
	public void setup() {
	
		driver = InitializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
		driver.findElement(By.xpath("//*[contains(text(),'Login')]")).click();

	}

	@AfterMethod
	public void BrowserCloseMethod() { // created method to close browser after each method irrespective of pass/fail
										// of TC
		driver.quit();
	}

	@Test(priority = 1, dataProvider = "validCredentialsSupplier")
	public void VerifyLoginWithValidCreden(String email, String password) {
		driver.findElement(By.xpath("//*[@id='input-email']")).sendKeys(email);
		driver.findElement(By.xpath("//*[@id='input-password']")).sendKeys(password);
		// driver.findElement(By.xpath("//*[@id='input-email']")).sendKeys(prop.getProperty("validEmail"));
		// driver.findElement(By.xpath("//*[@id='input-password']")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(),
				"edit your account information is not dispayed");
		// driver.quit(); added at top to avoid browser remains open for failed TC
	}

	@DataProvider(name="validCredentialsSupplier")
	public Object[][] supplyTestData() {
		Object[][] data = Utilities.getTetstDataFromExcel("Login");
		return data;
		
		}
	
	@Test(priority = 2)
	public void VerifyLoginWithINValidCreden() {

		driver.findElement(By.xpath("//*[@id='input-email']")).sendKeys(Utilities.generateEmailAdreeTimeStamp());
		driver.findElement(By.xpath("//*[@id='input-password']")).sendKeys(dataProp.getProperty("invalidPassword"));
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		String ActualWarningMessage = driver
				.findElement(
						By.xpath("//div[contains(text(),'Warning: No match for E-Mail Address and/or Password.')]"))
				.getText();
		String ExpectedWarningMessage = dataProp.getProperty("emailPasswordNomatchWarning");
		Assert.assertTrue(ActualWarningMessage.contains(ExpectedWarningMessage),
				"expected warning message is not matching");
		// driver.quit();
	}

	@Test(priority = 3)
	public void VerifyLoginWithINValidEmailAndValidPassword() {

		driver.findElement(By.xpath("//*[@id='input-email']"))
				.sendKeys("mahendramka" + Utilities.generateEmailAdreeTimeStamp() + "@gmail.com");
		driver.findElement(By.xpath("//*[@id='input-password']")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		String ActualWarningMessage = driver
				.findElement(
						By.xpath("//div[contains(text(),'Warning: No match for E-Mail Address and/or Password.')]"))
				.getText();
		String ExpectedWarningMessage = dataProp.getProperty("emailPasswordNomatchWarning");
		Assert.assertTrue(ActualWarningMessage.contains(ExpectedWarningMessage),
				"expected warning message is not matching");
		// driver.quit();
	}

	@Test(priority = 4)
	public void VerifyLoginWithValidEmailAndINValidPassword() {

		driver.findElement(By.xpath("//*[@id='input-email']")).sendKeys(prop.getProperty("validEmail"));
		driver.findElement(By.xpath("//*[@id='input-password']")).sendKeys("Mahendffra@678");
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		String ActualWarningMessage = driver
				.findElement(
						By.xpath("//div[contains(text(),'Warning: No match for E-Mail Address and/or Password.')]"))
				.getText();
		String ExpectedWarningMessage = dataProp.getProperty("emailPasswordNomatchWarning");
		Assert.assertTrue(ActualWarningMessage.contains(ExpectedWarningMessage),
				"expected warning message is not matching");
		// driver.quit();
	}

	@Test(priority = 5)
	public void VerifyLoginwithoutCredentials() {

		driver.findElement(By.xpath("//*[@id='input-email']")).sendKeys("");
		driver.findElement(By.xpath("//*[@id='input-password']")).sendKeys("");
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		String ActualWarningMessage = driver
				.findElement(
						By.xpath("//div[contains(text(),'Warning: No match for E-Mail Address and/or Password.')]"))
				.getText();
		String ExpectedWarningMessage = dataProp.getProperty("emailPasswordNomatchWarning");
		Assert.assertTrue(ActualWarningMessage.contains(ExpectedWarningMessage),
				"expected warning message is not matching");
		// driver.quit();
	}

}
