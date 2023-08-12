package Ninja.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Ninja_BaseClass.Base;

public class Search extends Base {
	
	
	
	  public Search() { super(); }
	 
	 
	WebDriver driver;

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@BeforeMethod
	public void SetUp() {

		
		driver = InitializeBrowserAndOpenApplication(prop.getProperty("browserName"));
	}

	@Test(priority = 1)
	public void VerifySearchValidProduct() {
		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("validProduct"));
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click(); 
		Assert.assertTrue(driver.findElement(By.linkText("HP LP3065")).isDisplayed());
	}

	@Test(priority = 2)
	public void VerifySearchWithNonValidProject() {
		driver.findElement(By.name("search")).sendKeys(dataProp.getProperty("invalidProduct"));
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click(); 
		String actualRESULT = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		Assert.assertEquals(actualRESULT, dataProp.getProperty("noProduct"),
				"No PRODUCT search message is not displayed");
	}

	@Test(priority = 3)
	public void VerifySearchWithoutAnyProduct() {
		driver.findElement(By.name("search")).sendKeys("");
		driver.findElement(By.xpath("//div[@id='search']/descendant::button")).click(); 
		String actualRESULT = driver.findElement(By.xpath("//div[@id='content']/h2/following-sibling::p")).getText();
		Assert.assertEquals(actualRESULT, dataProp.getProperty("noProduct"),
				"No PRODUCT search message is not displayed");
	}
}
