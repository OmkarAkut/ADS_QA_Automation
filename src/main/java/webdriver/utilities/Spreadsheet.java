package webdriver.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.common.collect.Lists;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Description: Use Spreadsheet class methods to interact with Excel spreadsheets.
 * Assumptions:
 * 0) Spreadsheet should be an .xlsx type.
 * 1) All data columns within a spreadsheet have the same length.
 * 2) The first row of each sheet is used as a column name/header.
 * 3) Each spreadsheet cell contains only one data value.
 * 4) For spreadsheets added to the framework, set the path using the following basic format:
 * String filePath = "./src\\test\\java\\webdriver\\scripts\\regression\\Spreadsheet.xlsx";
 * 5) All of the empty rows at the end of the spreadsheet data that Excel recognizes have
 * been removed/deleted.  If these are not removed, POI will pick them up and throw off
 * the "getLastRow" numbers used below and will likely cause a Null Pointer exception.
 */

public class Spreadsheet {

  DataFormatter fmt = new DataFormatter();

  /** Creates a list from column values in a spreadsheet. */
  public List spreadsheetCreateDataFromColumn(
          String filePath, String sheetName, String columnName, boolean printout)
          throws IOException {
    ArrayList<Object> list = new ArrayList<>();
    Sheet worksheet = spreadsheetGetSheet(filePath, sheetName);
    int columnNumber = spreadsheetSetColumnHeaderIndexFromColumnName(worksheet, columnName);
    Cell cellValue;
    for (int i = 1; i < worksheet.getLastRowNum() + 1; i++) {
      cellValue = worksheet.getRow(i).getCell(columnNumber);
      Object formattedCell = fmt.formatCellValue(cellValue);
      list.add(formattedCell);
    }
    if (printout) {
      System.out.println("Worksheet: " + sheetName);
      System.out.println("Column: " + columnName);
      for (Object object : list) {
        System.out.println(object);
      }
    }
    return list;
  }

  /** Creates a list of lists, where each list is from a row of values in a spreadsheet. */
  public List<List<Object>> spreadsheetCreateDataFromAllRows(
          String filePath, String sheetName, boolean printout) throws IOException {
    Sheet worksheet = spreadsheetGetSheet(filePath, sheetName);
    int rowCount = worksheet.getLastRowNum() - worksheet.getFirstRowNum();
    ArrayList<Object> list;
    List<List<Object>> listOfLists = Lists.newArrayList();
    for (int i = 1; i < rowCount + 1; i++) {
      Row row = worksheet.getRow(i);
      list = new ArrayList<>();
      //Create a loop to print cell values in a row
      Cell cellValue;
      for (int j = 0; j < row.getLastCellNum(); j++) {
        cellValue = worksheet.getRow(i).getCell(j);
        Object formattedCell = fmt.formatCellValue(cellValue);
        list.add(formattedCell);
      }
      listOfLists.add(list);
    }
    if (printout) {
      for (List<Object> printlist : listOfLists) {
        System.out.println(printlist);
      }
    }
    return listOfLists;
  }

  private int spreadsheetSetColumnHeaderIndexFromColumnName(Sheet worksheet, String columnName) {
    Row columnHeaders = worksheet.getRow(0);
    int columnIndex = -1;
    for (int ci = 0; ci < columnHeaders.getLastCellNum(); ci++) {
      if (columnHeaders.getCell(ci).getStringCellValue().trim().equals(columnName.trim())) {
        columnIndex = ci;
      }
    }
    return columnIndex;
  }

  private Sheet spreadsheetGetSheet(String fileName, String sheetName) throws IOException {
    Workbook myWorkbook = null;
    Sheet mySheet = null;
    try {
      File file = new File(fileName);
      FileInputStream inputStream = new FileInputStream(file);
      myWorkbook = new XSSFWorkbook(inputStream);
      mySheet = myWorkbook.getSheet(sheetName);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      myWorkbook.close();
    }
    return mySheet;
  }

}