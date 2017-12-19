package com.orangehrm.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils 
{
	Workbook wb;
	public ExcelUtils() throws Exception
	{
		FileInputStream fis = new FileInputStream("./TestInput/InputSheet.xlsx");
		wb = WorkbookFactory.create(fis);
	}
	
	public int getRowCount(String sheetname)
	{
		return wb.getSheet(sheetname).getLastRowNum();
	}
	
	public int getColCount(String sheetname, int row)
	{
		return wb.getSheet(sheetname).getRow(row).getLastCellNum();
	}
		
	public String getCellData(String sheetname, int row, int col)
	{
		String data = "";
		if(wb.getSheet(sheetname).getRow(row).getCell(col).getCellType() == Cell.CELL_TYPE_NUMERIC)
		{
			int celldata = (int)wb.getSheet(sheetname).getRow(row).getCell(col).getNumericCellValue();
			data = String.valueOf(celldata);
		}
		else
		{
			data = wb.getSheet(sheetname).getRow(row).getCell(col).getStringCellValue();
		}
		return data;
	}
	
	 public void setData(String sheetname, int row, int column, String data) throws Exception 
	   {
		  Sheet sh =wb.getSheet(sheetname);
		  Row rownum = sh.getRow(row);
		  Cell cell =rownum.createCell(column);
		  cell.setCellValue(data);
		  if(data.equalsIgnoreCase("PASS"))
		  {
			  CellStyle style =wb.createCellStyle();
			  Font font =wb.createFont();
			  font.setColor(IndexedColors.GREEN.getIndex());
			  font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			  style.setFont(font);
			  rownum.getCell(column).setCellStyle(style);
		  }
		  else
			  if(data.equalsIgnoreCase("Fail"))
			  {
				  CellStyle style =wb.createCellStyle();
				  Font font =wb.createFont();
				  font.setColor(IndexedColors.RED.getIndex());
				  font.setBoldweight(Font.BOLDWEIGHT_BOLD);
				  style.setFont(font);
				  rownum.getCell(column).setCellStyle(style);
				 }
			  else
				  if(data.equalsIgnoreCase("Not Executed"))
				  {
					  CellStyle style =wb.createCellStyle();
					  Font font =wb.createFont();
					  font.setColor(IndexedColors.BLUE.getIndex());
					  font.setBoldweight(Font.BOLDWEIGHT_BOLD);
					  style.setFont(font);
					  rownum.getCell(column).setCellStyle(style);
					 }
		        FileOutputStream fos= new FileOutputStream("./TestOutput/OutputSheet.xlsx");
		        wb.write(fos);
		        fos.close();
		   }
	}
		
