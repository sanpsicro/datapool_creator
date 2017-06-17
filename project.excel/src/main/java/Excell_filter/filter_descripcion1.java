package Excell_filter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAutoFilter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomFilter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTCustomFilters;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilter;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTFilterColumn;

import project.excel.Main;
import project.excel.file_writer;

public class filter_descripcion1 {
	public static XSSFSheet my_sheet;
	public static String IN_FILE_CUSTOM = "C:\\Users\\Usuario\\Desktop\\getData_" + Main.Empe�o_+ ".xlsx";
	
	public static void main(String[] args) throws IOException{
	FileInputStream file = new FileInputStream(new File(Main.IN_FILE));
	XSSFWorkbook workbook = new XSSFWorkbook (Main.IN_FILE);
	my_sheet  = workbook.getSheetAt(0);
	my_sheet.setAutoFilter(CellRangeAddress.valueOf("A1:AV300"));

	/* Step-1: Get the CTAutoFilter Object */
	CTAutoFilter sheetFilter=my_sheet.getCTWorksheet().getAutoFilter();                             
	/* Step -2: Add new Filter Column */
	CTFilterColumn  myFilterColumn=sheetFilter.insertNewFilterColumn(0);
	/* Step-3: Set Filter Column ID */
	myFilterColumn.setColId(1L);
	/* Step-4: Add new Filter */
	CTFilter myFilter=myFilterColumn.addNewFilters().insertNewFilter(0);
	/* Step -5: Define Auto Filter Condition - We filter Brand with Value of "A" */
	myFilter.setVal("*"+Main.Empe�o_+"*");                           
	XSSFRow r1;
	/* Step-6: Loop through Rows and Apply Filter */
	for(Row r : my_sheet) {
	        for (Cell c : r) {
	                if (c.getColumnIndex()==4 && !c.getStringCellValue().contains(Main.Empe�o_)) {
	                        r1=(XSSFRow) c.getRow();
	                        if (r1.getRowNum()!=0) { /* Ignore top row */
	                                /* Hide Row that does not meet Filter Criteria */
	                                r1.getCTRow().setHidden(true);
	                        	
	                        	}
	                                }                               
	        }
	

}
	
	FileOutputStream out = new FileOutputStream(new File(IN_FILE_CUSTOM));
    workbook.write(out);
    out.close();
	
	
	}
	
}


