package ninjaQaPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class homePages {
	WebDriver driver;

	// objects
	@FindBy(xpath = "//span[text()='My Account']")
	private WebElement myAccountDropMenu;

	@FindBy(linkText = "Login")
	private WebElement loginOption;
	
	@FindBy(linkText = "Register")
	private WebElement RegisterOption;

	@FindBy(xpath = "//div[@id='search']/descendant::button")
	private WebElement searchButton;
	
	
	@FindBy(name = "search")
	private WebElement searchBoxField;
	public homePages(WebDriver driver) {
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	// actions
	public void clickOnMyAccount() {
		myAccountDropMenu.click();
		
	}
	public RegisterPage selectRegisterOption() {
		RegisterOption.click();
		return new RegisterPage(driver);
	} 
	
	public RegisterPage navigateToRegisterPage() {
		myAccountDropMenu.click();
		RegisterOption.click();
		return new RegisterPage(driver);
	} 
	public LoginPage selectLoginOption() {
		loginOption.click();
		return new LoginPage(driver);
	}
	
	public LoginPage navigateToLoginPage(){
		myAccountDropMenu.click();
		loginOption.click();
		return new LoginPage(driver);
	}
	
	
	public void enterProductNameIntoSearchBoxField(String productText) {
		searchBoxField.sendKeys(productText);
	}
	public SearchPage clickOnSearchButtonOption() {
		searchButton.click();
		return new SearchPage(driver);
	}
	
	public SearchPage serchForProduct(String productText) {
		searchBoxField.sendKeys(productText);
		searchButton.click();
		return new SearchPage(driver);
	}
}
