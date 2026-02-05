package ExtentReport;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
		System.out.println("Pasted " + count + " values into UI");
	}

	public static List<String> readSecondColumnValues(String excelPath, int colIndex) throws IOException {
		List<String> values = new ArrayList<>();
		try (FileInputStream fis = new FileInputStream(excelPath); Workbook workbook = new XSSFWorkbook(fis)) {
			Sheet sheet = workbook.getSheetAt(0);
			for (int r = 0; r <= sheet.getLastRowNum(); r++) {
				Row row = sheet.getRow(r);
				if (row == null)
					continue;
				Cell cell = row.getCell(colIndex); // Column index 1 (2nd column)
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

	public static void updateColumnWithRandomValuesAndCopy(String excelPath, int colIndex, double minValue,
			double maxValue) throws IOException {
		FileInputStream fis = new FileInputStream(excelPath);
		Workbook workbook = new XSSFWorkbook(fis);
		Sheet sheet = workbook.getSheetAt(0);
		Random random = new Random();
		int firstRow = 0;
		int lastRow = sheet.getLastRowNum();
		StringBuilder clipboardData = new StringBuilder();
		for (int r = firstRow; r <= lastRow; r++) {
			Row row = sheet.getRow(r);
			if (row == null)
				continue;
			Cell cell = row.getCell(colIndex);
			if (cell == null) {
				cell = row.createCell(colIndex);
			}
			// Generate random number and round to 2 decimal places
			double randomVal = minValue + (maxValue - minValue) * random.nextDouble();
			double roundedVal = Math.round(randomVal * 100.0) / 100.0;
			// Set cell value
			cell.setCellValue(roundedVal);
			// Add to clipboard data
			clipboardData.append(roundedVal).append("\n");
		}
		fis.close();
		// Save updated Excel
		try (FileOutputStream fos = new FileOutputStream(excelPath)) {
			workbook.write(fos);
		}
		workbook.close();
		// Copy to clipboard
		StringSelection selection = new StringSelection(clipboardData.toString().trim());
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
		System.out.println("Column " + colIndex + " updated with random values and copied to clipboard.");
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
	public static void copyFromDownload(String fileName) throws Throwable {
		String downloadPath = System.getProperty("user.home") + "/Downloads";

        // Project path (destination)
        String projectPath = System.getProperty("user.dir")
                + "\\TestFiles";

        Path source = Path.of(downloadPath, fileName);
        Path destination = Path.of(projectPath, fileName);

        if (Files.exists(source)) {
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied successfully");
        } else {
            System.out.println("File not found in Downloads folder");
        }
    }
		 public static String searchFileAndFetchTheColValue(
		            String excelPath,
		            String searchId
		    ) throws Exception {

			 FileInputStream fis = new FileInputStream(excelPath);
		        Workbook workbook = WorkbookFactory.create(fis);
		        Sheet sheet = workbook.getSheetAt(0);

		        String search = searchId.trim();

		        for (Row row : sheet) {
		            for (Cell cell : row) {

		                String cellValue = readCell(cell);

		                if (cellValue.equals(search)) {

		                    int lastCol = row.getLastCellNum() - 1;
		                    Cell lastCell = row.getCell(
		                            lastCol,
		                            Row.MissingCellPolicy.CREATE_NULL_AS_BLANK
		                    );

		                    String result = readCell(lastCell);

		                    workbook.close();
		                    fis.close();
		                    return result;
		                }
		            }
		        }

		        workbook.close();
		        fis.close();
		        return null;
		    }
		  private static String readCell(Cell cell) {

		        if (cell == null) return "";

		        switch (cell.getCellType()) {

		            case STRING:
		                return cell.getStringCellValue().trim();

		            case NUMERIC:
		                if (DateUtil.isCellDateFormatted(cell)) {
		                    return cell.getDateCellValue().toString();
		                }
		                return String.valueOf((long) cell.getNumericCellValue());

		            case FORMULA:
		                return String.valueOf((long) cell.getNumericCellValue());

		            case BOOLEAN:
		                return String.valueOf(cell.getBooleanCellValue());

		            default:
		                return "";
		        }
		    }
		}
	


