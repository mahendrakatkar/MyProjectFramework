package Ninja_BaseClass;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import javax.swing.text.Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Base {
	WebDriver driver;
	
	public Properties prop;
	public Properties dataProp;
	
	public Base() {				// here constructor of base class is created and called in other class
		                                 //(also another way is to other way is to create method like "public void loadPropertiesFile() and call in other class in setup)
		 prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\ninjaQA\\Confige\\config.properties");
	
		dataProp = new Properties();
		File dataPropFile= new File(System.getProperty("user.dir")+"\\src\\main\\java\\qa\\testData\\testData.properties");
		try{
			FileInputStream dataFis= new FileInputStream(dataPropFile);
				dataProp.load(dataFis);
		} catch (Throwable e){
			e.printStackTrace();
		}
		
		try {
			FileInputStream fis = new FileInputStream(propFile);
		prop.load(fis);
	}catch(Throwable e) {
		e.printStackTrace();
	}
		
	}
	/*
	 * public Properties prop;
	 * 
	 * FileInputStream fis; public Base() { prop =new Properties(); File propfile =
	 * new File(System.getProperty("user.dir"+
	 * "\\src\\main\\java\\ninjaQA\\Confige\\config.properties")); try { fis = new
	 * FileInputStream(propfile); prop.load(fis); }catch(Throwable e) {
	 * e.printStackTrace(); } }
	 */
	public WebDriver InitializeBrowserAndOpenApplication(String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mahendrakumar_Katkar\\Downloads\\chrome-win64\\chromedriver.exe");
		driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(NinjaTutorialQAUtiles.Utilities.Implicit_Wait_Time));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(NinjaTutorialQAUtiles.Utilities.PageLoad_Time));
		driver.get(prop.getProperty("url"));

		return driver;
	}
}
