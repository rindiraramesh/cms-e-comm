package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MSExcelMethods {

	XSSFWorkbook xssfWorkbook = null;

	XSSFSheet xssfSheet = null;

	@SuppressWarnings("deprecation")
	public String xssfcellToString(XSSFCell cell) {
		int type = cell.getCellType();
		Object result;
		switch (type) {
		case 0:
			result = cell.getNumericCellValue();
			break;
		case 1:
			result = cell.getStringCellValue();
			break;
		case 2:
			result = cell.getCellFormula();
			break;
		case 3:
			result = "";
			break;
		default:
			throw new RuntimeException("no support for this cell");
		}
		return result.toString();
	}

	public int getRowCount() {
		return xssfSheet.getLastRowNum() + 1;
	}

	public int getColumnCount() {
		return xssfSheet.getRow(0).getLastCellNum();
	}

	public String[][] readDataFromExcel(String filePath, String sheetName) throws IOException {
		String testdata[][] = new String[0][];
		if (filePath.endsWith(".xlsx")) {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			xssfWorkbook = new XSSFWorkbook(fis);
			xssfSheet = xssfWorkbook.getSheet(sheetName);
			int numberOfColumns = getColumnCount();
			int numberOfRows = getRowCount();
			testdata = new String[numberOfRows - 1][numberOfColumns];
			for (int i = 1; i < numberOfRows; i++) {
				for (int j = 0; j < numberOfColumns; j++) {
					XSSFRow row = xssfSheet.getRow(i);
					XSSFCell cell = row.getCell(j);
					String value = xssfcellToString(cell);
					testdata[i - 1][j] = value;
					if (value == null) {
						System.out.println("data empty");
					}
				}
			}
		}
		return testdata;
	}

	public String[][] readDataFromExcelByRange(String filePath, String sheetName, int row_start, int row_end)
			throws IOException {
		String testdata[][] = new String[0][];
		if (row_end >= row_start) {
			if (filePath.endsWith(".xlsx")) {
				File file = new File(filePath);
				FileInputStream fis = new FileInputStream(file);
				xssfWorkbook = new XSSFWorkbook(fis);
				xssfSheet = xssfWorkbook.getSheet(sheetName);
				int numberOfColumns = getColumnCount();
				int numberOfRows = (row_end - row_start) + 1;
				int temp = 0;
				testdata = new String[numberOfRows][numberOfColumns];
				for (int i = row_start; i <= row_end; i++) {
					for (int j = 0; j < numberOfColumns; j++) {
						XSSFRow row = xssfSheet.getRow(i);
						XSSFCell cell = row.getCell(j);
						String value = xssfcellToString(cell);
						testdata[temp][j] = value;
						if (value == null) {
							System.out.println("data empty");
						}
					}
					temp++;
				}
			}
		} else {
			System.out.println("Invalid Row Range");
		}
		return testdata;
	}

	public List<String> readDataFromExcelByRow(String filePath, String sheetName, int row_number) throws IOException {
		List<String> value = new ArrayList<>();
		if (row_number > 0) {
			if (filePath.endsWith(".xlsx")) {
				File file = new File(filePath);
				FileInputStream fis = new FileInputStream(file);
				xssfWorkbook = new XSSFWorkbook(fis);
				xssfSheet = xssfWorkbook.getSheet(sheetName);
				int numberOfColumns = getColumnCount();
				for (int j = 0; j < numberOfColumns; j++) {
					XSSFRow row = xssfSheet.getRow(row_number);
					XSSFCell cell = row.getCell(j);
					value.add(xssfcellToString(cell));
				}
			}
		}
		return value;
	}

	public String readDataFromExcelByCell(String filePath, String sheetName, int rowNumber, int columnNumber)
			throws IOException {
		String value = null;
		if (rowNumber > 0) {
			if (filePath.endsWith(".xlsx")) {
				File file = new File(filePath);
				FileInputStream fis = new FileInputStream(file);
				xssfWorkbook = new XSSFWorkbook(fis);
				xssfSheet = xssfWorkbook.getSheet(sheetName);
				XSSFRow row = xssfSheet.getRow(rowNumber);
				XSSFCell cell = row.getCell(columnNumber);
				value = xssfcellToString(cell);
			}
		}
		return value;
	}

	public List<String> readDataFromExcelByColumnName(String filePath, String sheetName, String columnName)
			throws IOException {
		List<String> value = new ArrayList<>();
		if (filePath.endsWith(".xlsx")) {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			xssfWorkbook = new XSSFWorkbook(fis);
			xssfSheet = xssfWorkbook.getSheet(sheetName);
			int numberOfRows = getRowCount();
			int numberOfColumns = getColumnCount();
			XSSFRow headerRow = xssfSheet.getRow(0);
			for (int i = 0; i < numberOfColumns; i++) {
				if (headerRow.getCell(i).getStringCellValue().trim().equals(columnName)) {
					int columnIndex = i;
					for (int j = 1; j < numberOfRows; j++) {
						XSSFRow row = xssfSheet.getRow(j);
						XSSFCell cell = row.getCell(columnIndex);
						value.add(xssfcellToString(cell));
					}
				}
			}
		}
		return value;
	}

}