package Ninja.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Ninja_BaseClass.Base;

public class Register extends Base {

	WebDriver driver;

	
	
	  public Register() { super(); }
	 
	@BeforeMethod
	public void setup() {
	
			driver = InitializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		driver.findElement(By.xpath("//span[contains(text(),'My Account')]")).click();
		driver.findElement(By.linkText("Register")).click();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();

	}

	@Test(priority = 1)
	private void VerifyRegisteringAnAccountshowsMandatoryFields() {
		// TODO Auto-generated method stub

		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		driver.findElement(By.id("input-email"))
				.sendKeys(NinjaTutorialQAUtiles.Utilities.generateEmailAdreeTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//*[@value='Continue']")).click();

		String SuccessActualHeading = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(SuccessActualHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"),
				"account succeess is not dispalyed");

	}

	@Test(priority = 2)
	private void VerifyRegisteringAnAccountByProvidingAllFields() {
		// TODO Auto-generated method stub

		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		driver.findElement(By.id("input-email"))
				.sendKeys(NinjaTutorialQAUtiles.Utilities.generateEmailAdreeTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys(dataProp.getProperty("telephoneNumber"));
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//*[@name='newsletter' and @value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//*[@value='Continue']")).click();

		String SuccessActualHeading = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(SuccessActualHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"),
				"account succeess is not deispalyed");

	}

	@Test(priority = 3)
	private void VerifyRegisteringAnAccountByProvidingAllFieldswithExistingEmailAddress() {
		// TODO Auto-generated method stub

		driver.findElement(By.id("input-firstname")).sendKeys(dataProp.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys(dataProp.getProperty("lastName"));
		driver.findElement(By.id("input-email")).sendKeys("mahendramkatkar@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("23256");
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.id("input-confirm")).sendKeys(prop.getProperty("validPassword"));
		driver.findElement(By.xpath("//*[@name='newsletter' and @value='1']")).click();
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.xpath("//*[@value='Continue']")).click();

		String actualWarning = driver.findElement(By.xpath("//*[@class='alert alert-danger alert-dismissible']"))
				.getText();
		Assert.assertTrue(actualWarning.contains(dataProp.getProperty("duplicateEmailWarning")),
				"warning message is not displayed");

	}

	@Test(priority = 4)
	public void VerifyloggingintotheApplicatioWithouProvidingAnycredentials() {

		driver.findElement(By.xpath("//*[@value='Continue']")).click();

		String actualPrivacyPoolicyWarningMessage = driver
				.findElement(By.xpath("//*[@class='alert alert-danger alert-dismissible']")).getText();
		Assert.assertTrue(actualPrivacyPoolicyWarningMessage.contains(dataProp.getProperty("privacyPolicyWarning")),
				"warning policy message is not displayed");

		String FirstNameErrorMessage = driver.findElement(By.xpath("//*[@id='input-firstname']/following-sibling::div"))
				.getText();
		// "//*[@class='text-danger' and contains(text(),'First Name must be between 1
		// and 32 characters!')]" or using sibling
		// "//*[@id='input-firstname']/following-sibling::div"
		Assert.assertEquals(FirstNameErrorMessage, dataProp.getProperty("firstNameWarning"),
				"firstName warning message is not displayed");

		String LastNameErrorMessage = driver.findElement(By.xpath("//*[@id='input-lastname']/following-sibling::div"))
				.getText();
		Assert.assertEquals(LastNameErrorMessage, dataProp.getProperty("lastNameWarning"),
				"lastName warning message is not displayed");

		String EmailErrorMessage = driver.findElement(By.xpath("//*[@id='input-email']/following-sibling::div"))
				.getText();
		Assert.assertEquals(EmailErrorMessage, dataProp.getProperty("emailWarning"),
				"email warning message is not displayed");

		String TelephoneErrorMessage = driver.findElement(By.xpath("//*[@id='input-telephone']/following-sibling::div"))
				.getText();
		Assert.assertEquals(TelephoneErrorMessage, dataProp.getProperty("telephoneWarning"),
				"telephone warning message is not displayed");

		String PasswordErrorMessage = driver.findElement(By.xpath("//*[@id='input-password']/following-sibling::div"))
				.getText();
		Assert.assertEquals(PasswordErrorMessage, dataProp.getProperty("passwordWarning"),
				"Password warning message is not displayed");
	}
}
