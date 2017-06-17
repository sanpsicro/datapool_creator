package project.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import Excel_Clean_S.Clean_Datapool;
import Excell_filter.filter_descripcion1;

import org.apache.commons.collections.map.MultiValueMap;



public class Main {
	
	public static MultiValueMap relX = new MultiValueMap();
	public static HashMap<String,Integer>  index_column = new HashMap<String,Integer>();
	public static ArrayList<String> elements = new ArrayList<String>();
	 //archivo preparado para la insercion de datos
	public static  String OUT_FILE= "";
	public static  String OUT_FILE_testC= "";
	
	//archivo preparado para la extracci�n de datos
	public static final String IN_FILE = "C:/Users/Usuario/Desktop/Matriz_Tasas_Rango_Comision2 (002).xlsx";
	
	//Elegir cual es el datapool que se quiere crear
	
	public static final String Empe�o_ = "Alhajas";
	//public static String Empe�o_ = "Varios";
	//public static String Empe�o_ = "Autos";
	//public static String Empe�o_ = "Auto Rodando";
	//public static String Empe�o_ = "Motos";
	//public static String Empe�o_ = "Relojes";
	 //public static String Empe�o_ = "Plata";
	
	 
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		switch (Empe�o_) {
		case "Alhajas":
			  Alhajas_prendario alhajas_prendario = new Alhajas_prendario();
			break;
		case "Varios":
			Varios_prendario varios_prendario = new Varios_prendario();
			break;
		case "Autos":
			  Autos_prendario autos_Prendario = new Autos_prendario();
			break;
		case "Auto Rodando":
			  AutosRodando_prendario autoRodando_prendario = new AutosRodando_prendario();
			break;
		case "Motos":
			  Motos_prendario motos_prendario = new Motos_prendario();
				break;
	    case "Relojes":
		      Relojes_prendario relojes_prendario = new Relojes_prendario();
		    	break;
	    case "Plata":
	    	  Plata_prendario plata_prendario = new Plata_prendario();
	    	break;

		default:
			break;
		} 
		  
		
		 
		filter_descripcion1.main(args);
		  	
		  
		 Main main_relX = new Main();
		  	
		Iterator<String> relX_iterator = main_relX.relX.values().iterator();
		  					
		 while (relX_iterator.hasNext()) {
					 elements.add(relX_iterator.next());	
		}
		 
		 //erase duplicate columns in new headers
		 elements = new ArrayList<String>(new LinkedHashSet<String>(elements));
		 
		    file_writer.columns =  elements.toArray();
			file_writer.file_name = "Datapool_" + Empe�o_;
			file_writer.sheetName = Empe�o_;
			
			file_writer writer = new file_writer();
			
			OUT_FILE =  "C:\\Users\\Usuario\\Desktop\\" + file_writer.file_name + ".xlsx" ;
			OUT_FILE_testC =  "C:\\Users\\Usuario\\Desktop\\" + file_writer.file_name + "_testC.xlsx" ;
		  		
		  
		  
		  try (FileInputStream file = new FileInputStream(new File(OUT_FILE))) {
		    	XSSFWorkbook workbook = new XSSFWorkbook (OUT_FILE); 
		    	XSSFSheet sheet = workbook.getSheetAt(0);
		    	 Iterator<Row> rowIterator = sheet.iterator();
		    	
			        for (Row row : sheet) {
			            Iterator<Cell> cellIterator = row.cellIterator();
			            if(row.getRowNum() == 0){
			            for (Cell cell : row) {
			            	
			            	try {
							String	a=cell.getStringCellValue();
							int b = cell.getColumnIndex();
							System.out.println(a);
							index_column.put(a, b);
							} catch (Exception e) {
								// TODO Auto-generated catch block
							double	a =cell.getNumericCellValue();
							System.out.println(a);
							}   
			            }
							
			            }
			        }
		  }catch(Exception e){
			  
			  
		  }
		  
		  
		
		  
		readingExcel a = new readingExcel();
		readingExcel.readingMatrix(filter_descripcion1.IN_FILE_CUSTOM,OUT_FILE);
		
		Clean_Datapool.main(args);
		
	}

}
