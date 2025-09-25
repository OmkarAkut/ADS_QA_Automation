package ExtentReport;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import webdriver.core.Driver;

public class ExcelUtility extends Driver {
	static Actions actions = new Actions(driver);

	public static String copyColumToExcel(List<WebElement> allRows, String filepath)
			throws Throwable, Throwable, IOException {
		actions.click(allRows.get(0)).perform();

		// Hold SHIFT and click last row to select all in between
		actions.keyDown(Keys.SHIFT).click(allRows.get(allRows.size() - 1)).keyUp(Keys.SHIFT).perform();

		Thread.sleep(500); // small wait to ensure selection

		// Now send Ctrl+C to copy the selection
		actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
		String copiedText = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
		System.out.println(copiedText);
		return copiedText;
	}

	public static void sendValuesToInputs(List<WebElement> inputElements, List<String> values) {
		int count = Math.min(inputElements.size(), values.size());

		for (int i = 0; i < count; i++) {
			WebElement input = inputElements.get(i);
			System.out.println(input);
			String value = values.get(i);
			System.out.println(value);
			Actions action = new Actions(driver);
			action.moveToElement(input).doubleClick().sendKeys(Keys.CLEAR).sendKeys(value).sendKeys(Keys.ENTER)
					.perform();
		}

		System.out.println("Pasted " + count + " values into UI using sendKeys().");
	}

	public static List<String> readSecondColumnValues(String excelPath) throws IOException {
		List<String> values = new ArrayList<>();

		try (FileInputStream fis = new FileInputStream(excelPath); Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheetAt(0);
			for (int r = 0; r <= sheet.getLastRowNum(); r++) {
				Row row = sheet.getRow(r);
				if (row == null)
					continue;

				Cell cell = row.getCell(1); // Column index 1 (2nd column)
				if (cell == null) {
					values.add("");
					continue;
				}

				switch (cell.getCellType()) {
				case STRING:
					values.add(cell.getStringCellValue());
					break;
				case NUMERIC:
					values.add(String.valueOf(cell.getNumericCellValue()));
					break;
				case BOOLEAN:
					values.add(String.valueOf(cell.getBooleanCellValue()));
					break;
				default:
					values.add("");
				}
			}
		}

		return values;
	}

	public static void updateSecondColumnAndCopy(String excelPath, double minValue, double maxValue)
			throws IOException {

		FileInputStream fis = new FileInputStream(excelPath);
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheetAt(0);

		Random random = new Random();
		int lastRow = sheet.getLastRowNum();

//Update column 1 (second column) in each row
		for (int r = 0; r <= lastRow; r++) {
			Row row = sheet.getRow(r);
			if (row == null)
				continue;

			Cell cell = row.getCell(1); // column index 1 = second column
			if (cell == null) {
				cell = row.createCell(1);
			}

			double randomVal = minValue + (maxValue - minValue) * random.nextDouble();
			cell.setCellValue(Math.round(randomVal * 100.0) / 100.0); // round to 2 decimal places
		}

//Copy all data to clipboard (tab-separated columns, newline-separated rows)
		StringBuilder clipboardData = new StringBuilder();

		for (int r = 0; r <= lastRow; r++) {
			Row row = sheet.getRow(r);
			if (row == null)
				continue;

			short lastCol = row.getLastCellNum();

			for (int c = 0; c < lastCol; c++) {
				Cell cell = row.getCell(c);
				String value = "";

				if (cell != null) {
					switch (cell.getCellType()) {
					case STRING:
						value = cell.getStringCellValue();
						break;
					case NUMERIC:
						value = String.valueOf(cell.getNumericCellValue());
						break;
					case BOOLEAN:
						value = String.valueOf(cell.getBooleanCellValue());
						break;
					default:
						value = "";
					}
				}

				clipboardData.append(value);

				if (c < lastCol - 1) {
					clipboardData.append("\t");
				}
			}
			clipboardData.append("\n");
		}

		fis.close();

//Save Excel file
		try (FileOutputStream fos = new FileOutputStream(excelPath)) {
			workbook.write(fos);
		}
		workbook.close();

//Copy to clipboard
		StringSelection selection = new StringSelection(clipboardData.toString().trim());
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

		System.out.println("Second column updated with random values and full data copied to clipboard.");
	}

	public static void pasteClipboardTextToExcel(String clipboardText, String excelPath) throws IOException {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("CopiedTable");

		String[] rows = clipboardText.split("\\r?\\n"); // split by new line

		for (int i = 0; i < rows.length; i++) {
			String[] cols = rows[i].split("\\t"); // split by tab for columns
			Row excelRow = sheet.createRow(i);

			for (int j = 0; j < cols.length; j++) {
				Cell cell = excelRow.createCell(j);
				cell.setCellValue(cols[j].trim());
			}
		}

		// Auto-size columns based on first row (optional)
		if (rows.length > 0) {
			int colCount = rows[0].split("\\t").length;
			for (int c = 0; c < colCount; c++) {
				sheet.autoSizeColumn(c);
			}
		}

		try (FileOutputStream fos = new FileOutputStream(excelPath)) {
			workbook.write(fos);
		}
		workbook.close();

		System.out.println("Clipboard data saved to Excel at: " + excelPath);
	}
}
