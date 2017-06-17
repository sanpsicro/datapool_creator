package Excel_Clean_S;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import project.excel.Main;

public class Clean_Datapool {
	


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		 FileInputStream file = new FileInputStream(new File(Main.OUT_FILE));
		    	XSSFWorkbook workbook = new XSSFWorkbook (Main.OUT_FILE); 
		    	XSSFSheet sheet = workbook.getSheetAt(0);
		  boolean isRowEmpty = false;  	
		 
		
		for(int i=0; i < sheet.getLastRowNum(); i++){
	        if(sheet.getRow(i)==null){
	            sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
	            i--;
	        continue;
	        }
	        for(int j=0; j<sheet.getRow(i).getLastCellNum();j++){
	            if(sheet.getRow(i).getCell(j,Row.CREATE_NULL_AS_BLANK).toString().trim().equals("")){
	                isRowEmpty=true;
	            }else {
	                isRowEmpty=false;
	                break;
	            }
	        }
	        if(isRowEmpty==true){
	            sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
	            i--;
	        }
	    }
		
		file.close();
		FileOutputStream out = new FileOutputStream(new File(Main.OUT_FILE_testC));
		workbook.write(out);
		out.flush();
		out.close();

		

}

	
}