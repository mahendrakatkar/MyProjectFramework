package Ninja.qa.copy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Ninja_BaseClass.Base;
import ninjaQaPages.SearchPage;
import ninjaQaPages.homePages;

public class Search extends Base {
	SearchPage searchPage;
	homePages homepage;

	public Search() {
		super();
	}

	public WebDriver driver;

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@BeforeMethod
	public void SetUp() {

		driver = InitializeBrowserAndOpenApplication(prop.getProperty("browserName"));
		 homepage = new homePages(driver);
	}

	@Test(priority = 1)
	public void VerifySearchValidProduct() {
	
	searchPage= homepage.serchForProduct(dataProp.getProperty("validProduct"));		
	/*
	 * homepage = new homePages(driver);
	 * homepage.enterProductNameIntoSearchBoxField(dataProp.getProperty(
	 * "validProduct")); searchPage = homepage.clickOnSearchButtonOption();
	 */

		Assert.assertTrue(searchPage.displayStatusOfHPValidProdcut(), "validProduct is not displayed");
	}

	@Test(priority = 2)
	public void VerifySearchWithNonValidProject() {
		searchPage= homepage.serchForProduct(dataProp.getProperty("invalidProduct"));
		/*homepage.enterProductNameIntoSearchBoxField(dataProp.getProperty("invalidProduct"));
		searchPage = homepage.clickOnSearchButtonOption();*/

		String actualRESULT = searchPage.retreiveNoProductMessageText();
		Assert.assertEquals(actualRESULT, dataProp.getProperty("noProduct"),
				"No PRODUCT search message is not displayed");
	}

	@Test(priority = 3)
	public void VerifySearchWithoutAnyProduct() {

		searchPage = homepage.clickOnSearchButtonOption();

		String actualRESULT = searchPage.retreiveNoProductMessageText();
		Assert.assertEquals(actualRESULT, dataProp.getProperty("noProduct"),
				"No PRODUCT search message is not displayed");
	}
}
