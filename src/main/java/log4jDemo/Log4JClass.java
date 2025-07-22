package log4jDemo;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;

import freemarker.log.Logger;

public class Log4JClass {
 private static org.apache.log4j.Logger logger =LogManager.getLogger(Log4JClass.class);
//	private static Logger logger=Logger.getLogger(Log4JClass.class);
	
public static void main(String[] args) {
	BasicConfigurator.configure();
System.out.println("print");	


logger.info("this is info message");
logger.error("this is error message");
logger.warn("this is warn message");
logger.fatal("this is fatal message");	
logger.trace("this is trace message");
System.out.println("completed");

}
}
