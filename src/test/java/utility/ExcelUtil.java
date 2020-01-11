package utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	public static int getRowcount(String XlFile,String XlSheet) throws IOException {
		fi=new FileInputStream(XlFile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(XlSheet);
		int rowCount=ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount;
	}
	
	public static int getColumnCount(String XlFile,String XlSheet,int RowNum) throws IOException {
		fi=new FileInputStream(XlFile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(XlSheet);
		row=ws.getRow(RowNum);
		int ColumnCount=row.getLastCellNum();
		wb.close();
		fi.close();
		return ColumnCount;	
	}
	
	public static String getCellData(String XlFile,String XlSheet,int RowNum,int columnNum) throws IOException {
		fi=new FileInputStream(XlFile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(XlSheet);
		row=ws.getRow(RowNum);
		cell=row.getCell(columnNum);
		String data;
		try {
			DataFormatter formatter=new DataFormatter();
			String cellData=formatter.formatCellValue(cell);
			return cellData;
			
		}catch(Exception e) {
			data="";
		}
		wb.close();
		fi.close();
		return data;
	}
	
	public static void setCellData(String XlFile,String XlSheet,int RowNum,int columnNum,String data) throws IOException {
		fi=new FileInputStream(XlFile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(XlSheet);
		row=ws.getRow(RowNum);
		cell=row.getCell(columnNum);
		cell.setCellValue(data);
		fo=new FileOutputStream(XlFile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
}
