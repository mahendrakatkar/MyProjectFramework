package NinjaTutorialQAUtiles;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class extentReporter {
public static ExtentReports generateExtendRepot() {
	
	
	
	File extentReportFile= new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
	ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);    // ExtentSparkReporter for report details like name, theme, time etc
	sparkReporter.config().setTheme(Theme.DARK);
	sparkReporter.config().setReportName("Tuto Ninja test automation result");
	sparkReporter.config().setDocumentTitle("NINJA REPORT");
	sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
	
	
	ExtentReports extentReport = new ExtentReports();		//ExtentReports for property details like url, browser, email etc
	extentReport.attachReporter(sparkReporter);
	
	Properties configProp = new Properties();
	File configPropFile= new  File(System.getProperty("user.dir")+"\\src\\main\\java\\ninjaQA\\Confige\\config.properties");
	
	try {
	FileInputStream fisConfigProp = new FileInputStream(configPropFile);
	configProp.load(fisConfigProp);
	} catch (Throwable e) {
		e.printStackTrace();
	}
	extentReport.setSystemInfo("Application URL", configProp.getProperty("url"));
	extentReport.setSystemInfo("Browser Name", configProp.getProperty("browserName"));
	extentReport.setSystemInfo("Email", configProp.getProperty("validEmail"));
	extentReport.setSystemInfo("Password", configProp.getProperty("validPassword"));
	//extentReport.setSystemInfo("Password", configProp.getProperty("validPassword"));
	extentReport.setSystemInfo("operaing system", System.getProperty("os.name"));		// system property
	extentReport.setSystemInfo("User Name", System.getProperty("user.name"));
	extentReport.setSystemInfo("Java Version", System.getProperty("java.version"));

	return extentReport;
}
}
