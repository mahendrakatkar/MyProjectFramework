package Ninja.qa;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Ninja_BaseClass.Base;
import ninjaQaPages.AccountSuccessPage;
import ninjaQaPages.RegisterPage;
import ninjaQaPages.homePages;

public class RegisterTest extends Base {
	RegisterPage registerPage;
	public WebDriver driver;
	AccountSuccessPage accountSuccessPage;
	public RegisterTest() {
		super();
	}

	@BeforeMethod
	public void setup() {

		driver = InitializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		homePages homePage = new homePages(driver);
	   
		registerPage=homePage.navigateToRegisterPage();
		

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();

	}

	@Test(priority = 1)
	private void VerifyRegisteringAnAccountshowsMandatoryFields() {
		// TODO Auto-generated method stub

		accountSuccessPage=registerPage.registerwithMandarotyFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), 
				NinjaTutorialQAUtiles.Utilities.generateEmailAdreeTimeStamp(), dataProp.getProperty("telephoneNumber"), 
				prop.getProperty("validPassword"), prop.getProperty("validPassword"));
		String SuccessActualHeading = accountSuccessPage.retrieveAccountSuccssPageHeading();
		Assert.assertEquals(SuccessActualHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"),
				"account succeess is not dispalyed");
		/*
		 * registerPage.EnterfirstName(dataProp.getProperty("firstName"));
		 * registerPage.EnterLastName(dataProp.getProperty("lastName"));
		 * registerPage.enterEmailAddress(NinjaTutorialQAUtiles.Utilities.
		 * generateEmailAdreeTimeStamp());
		 * registerPage.enterTelephoneNumber(dataProp.getProperty("telephoneNumber"));
		 * registerPage.enterPassword(prop.getProperty("validPassword"));
		 * registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		 * registerPage.selectPrivacyPolicy(); accountSuccessPage =
		 * registerPage.clickContinueButton();
		 */
		

	}

	@Test(priority = 2)
	private void VerifyRegisteringAnAccountByProvidingAllFields() {
		// TODO Auto-generated method stub
		accountSuccessPage= registerPage.registerwithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), NinjaTutorialQAUtiles.Utilities.generateEmailAdreeTimeStamp(), dataProp.getProperty("telephoneNumber"),
				prop.getProperty("validPassword"), prop.getProperty("validPassword"));
		
		Assert.assertEquals(accountSuccessPage.retrieveAccountSuccssPageHeading(), dataProp.getProperty("accountSuccessfullyCreatedHeading"),
				"account succeess is not deispalyed");

	}

	@Test(priority = 3)
	private void VerifyRegisteringAnAccountByProvidingAllFieldswithExistingEmailAddress() {
		// TODO Auto-generated method stub
		
		registerPage.registerwithAllFields(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), prop.getProperty("validEmail"),
				dataProp.getProperty("telephoneNumber"),prop.getProperty("validPassword"), prop.getProperty("validPassword"));
		

		Assert.assertTrue(registerPage.retrieveDuplicateEmalAddressWarning().contains(dataProp.getProperty("duplicateEmailWarning")),
				"warning message is not displayed");

	}

	@Test(priority = 4)
	public void VerifyloggingintotheApplicatioWithouProvidingAnycredentials() {

		registerPage.clickContinueButton();
		
		Assert.assertTrue(registerPage.displayStatusOfWarningMessages(dataProp.getProperty("privacyPolicyWarning"), dataProp.getProperty("firstNameWarning"), dataProp.getProperty("lastNameWarning"), dataProp.getProperty("emailWarning"), dataProp.getProperty("telephoneWarning"), dataProp.getProperty("passwordWarning")));
	
		/*
		 * Assert.assertTrue(registerPage.retrievePrivacyPolicyWarning().contains(
		 * dataProp.getProperty("privacyPolicyWarning")),
		 * "warning policy message is not displayed");
		 * 
		 * //String FirstNameErrorMessage = registerPage.retreiveFIrstNameWarning(); //
		 * "//*[@class='text-danger' and contains(text(),'First Name must be between 1
		 * // and 32 characters!')]" or using sibling //
		 * "//*[@id='input-firstname']/following-sibling::div"
		 * Assert.assertEquals(registerPage.retreiveFIrstNameWarning(),
		 * dataProp.getProperty("firstNameWarning"),
		 * "firstName warning message is not displayed");
		 * 
		 * Assert.assertEquals(registerPage.retrieveLastNameWarning(),
		 * dataProp.getProperty("lastNameWarning"),
		 * "lastName warning message is not displayed");
		 * Assert.assertEquals(registerPage.retrieveEmailWarning(),
		 * dataProp.getProperty("emailWarning"),
		 * "email warning message is not displayed");
		 * Assert.assertEquals(registerPage.retrieveTelephoneWarning(),
		 * dataProp.getProperty("telephoneWarning"),
		 * "telephone warning message is not displayed");
		 * Assert.assertEquals(registerPage.retrievePasswordWarning(),
		 * dataProp.getProperty("passwordWarning"),
		 * "Password warning message is not displayed");
		 */
	}
}
