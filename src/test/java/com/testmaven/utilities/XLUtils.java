package com.testmaven.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {

	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static String path = null;

	public XLUtils(String path) {
		this.path = path;
	}

	public static int getrowcount(String xlsheetname) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(xlsheetname);
		int rowcount = sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
	}

	public static int getcellcount(String xlsheetname, int rownum) throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(xlsheetname);
		row = sheet.getRow(rownum);
		int cellcount = row.getLastCellNum();
		// System.out.println("CellCount is"+cellcount);
		workbook.close();
		fi.close();
		return cellcount;
	}

	public static String getCellData(String xlsheetname, int rownum, int cellnum)
			throws IOException {
		fi = new FileInputStream(path);
		workbook = new XSSFWorkbook(fi);
		sheet = workbook.getSheet(xlsheetname);
		row = sheet.getRow(rownum);
		cell = row.getCell(cellnum);
		String data;
		try {
			DataFormatter dataformat = new DataFormatter();
			data = dataformat.formatCellValue(cell);
		} catch (Exception e) {
			data = "";
		}
		workbook.close();
		fi.close();
		return data;

	}

}
