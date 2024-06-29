package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
	static String path;
	
	public XLUtility(String path){
		XLUtility.path=path;
	}
	public static int getRowCount(String sheetname) throws IOException{
		
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(sheetname);
		int rowcount=ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;
		
	}
	
	public static int getCellCount(String sheetname , int rownum) throws IOException{
		
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(sheetname);
		row=ws.getRow(rownum);
		int cellcount=row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;
		
	}
	
	public static String getCellData(String sheetname,int rownum, int colnum) throws IOException{
		
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(sheetname);
		row=ws.getRow(rownum);
		cell=row.getCell(colnum);
		DataFormatter formatter= new DataFormatter();
		String CellData;

		try {
			
			
			CellData = formatter.formatCellValue(cell);
			return CellData;
		}
		catch (Exception e) {
			CellData="";			
		}
		wb.close();
		fi.close();
		return CellData;
	}
	
	public static void setCellData(String sheetname, int rownum, int colnum, String data) throws IOException{
		
		File xlfile=new File(path);
		if(!xlfile.exists()) {
		wb=new XSSFWorkbook();
		fo = new FileOutputStream(path);
		wb.write(fo);
		}
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		
		if(wb.getSheetIndex(sheetname)==-1) {
			wb.createSheet(sheetname);
			ws = wb.getSheet(sheetname);
		}
		
		if(ws.getRow(rownum)==null) {
			ws.createRow(rownum);
			row = ws.getRow(rownum);
		}

	}
		
		
		
}
	
	
	


