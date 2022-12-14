package com.Ecommerce.utilityFiles;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {

	static ArrayList<String> a=new ArrayList<String>();
	
	public static ArrayList<String> getData(String testcaseName) throws IOException {
		FileInputStream fis = new FileInputStream(
				"E:\\Eclipse_workspace\\Project\\src\\test\\java\\com\\Ecommerce\\Resources\\Data.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("data")) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				Iterator<Row> row = sheet.iterator();
				Row Firstrow = row.next();
				Iterator<Cell> ce = Firstrow.cellIterator();
				int k = 0;
				int column = 0;
				while (ce.hasNext()) {
					if (ce.next().getStringCellValue().equalsIgnoreCase("TestCases")) {
						column = k;
					}
					k++;
				}
				System.out.println(column);
				while (row.hasNext()) {
					Row r = row.next();
					if(r.getCell(column)==null) {
						workbook.close();
						return a;
					}else if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName)) {
						Iterator<Cell> c = r.cellIterator();
						while (c.hasNext()) {
							Cell cv=c.next();
							if(cv.getCellType()==CellType.STRING) {
								a.add(cv.getStringCellValue());
							}else {								
								a.add(NumberToTextConverter.toText(cv.getNumericCellValue()));

							}
						}
					}
				}
			}
		}
		workbook.close();
		return a;
		
	}
	public static void main(String args[]) throws IOException {
		ArrayList<String>data=getData("Login");
		System.out.println(data.get(0));
		System.out.println(data.get(1));
		System.out.println(data.get(2));
		System.out.println(data.get(3));
	}
	
}
