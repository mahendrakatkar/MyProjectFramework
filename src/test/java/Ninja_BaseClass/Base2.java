package Ninja_BaseClass;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base2 {
	WebDriver driver;

	/*
	 * public Properties prop;
	 * 
	 * FileInputStream fis; public Base2() { prop =new Properties(); File propfile =
	 * new File(System.getProperty("user.dir"+
	 * "\\src\\main\\java\\ninjaQA\\Confige\\config.properties")); try { fis = new
	 * FileInputStream(propfile); prop.load(fis); }catch(Throwable e) {
	 * e.printStackTrace(); } }
	 */
	public WebDriver InitializeBrowserAndOpenApplication2(String browserName) {
		if (browserName.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equals("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		driver.get("https://tutorialsninja.com/demo/");

		return driver;
	}
}