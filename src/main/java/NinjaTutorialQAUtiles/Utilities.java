package NinjaTutorialQAUtiles;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;


import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Utilities {

	public static final int Implicit_Wait_Time = 15;
	public static final int PageLoad_Time = 10;
	

	public static String generateEmailAdreeTimeStamp() {
		Date date = new Date();
		String timestamp = date.toString().replace(" ", "_").replace(":", "_");
		return "mahendra" + timestamp + "@gmail.com";
	}

	public static Object[][] getTetstDataFromExcel(String sheetName) {
		
		File excelFile= new  File(System.getProperty("user.dir"+"\\src\\main\\java\\qa\\testData\\ninjaqaTestData.xlsx")); 
		  XSSFWorkbook workbook = null;
		  try {
  FileInputStream  fisExcel = new FileInputStream(excelFile); 
   workbook = new XSSFWorkbook(fisExcel); 
   }
  catch (Throwable e) {
	  e.printStackTrace(); 
	  }
  
  XSSFSheet sheet= workbook.getSheet(sheetName);
  
  int rows = sheet.getLastRowNum();
  int colums= sheet.getRow(0).getLastCellNum(); 
  
  Object [][] data = new Object[rows][colums];
  for (int i=0;i<rows;i++) 
  { XSSFRow row =  sheet.getRow(i+1);
  
  for(int j=0;j<colums;j++)   { 
	  XSSFCell cell = row.getCell(j); 
	  CellType cellType= cell.getCellType();
    
  switch (cellType) 
  { case STRING:
	  data [i][j] = cell.getStringCellValue();
	  break;
  case NUMERIC:
	  data [i][j] =Integer.toString((int)cell.getNumericCellValue());
	  break;
case BLANK:
	break;
case BOOLEAN:
	break;
case ERROR:
	break;
case FORMULA:
	break;
case _NONE:
	break;
default:
	break;

  } 
  }}
  		return data;
  
  }
}