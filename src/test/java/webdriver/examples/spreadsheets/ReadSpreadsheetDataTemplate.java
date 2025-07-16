package webdriver.examples.spreadsheets;

import java.io.IOException;
import java.util.List;
import org.junit.Test;
import webdriver.utilities.Spreadsheet;

public class ReadSpreadsheetDataTemplate {

  Spreadsheet sheet = new Spreadsheet();
  //String filePath = "./src\\test\\java\\webdriver\\templates\\Spreadsheets\\TestDataSet.xlsx";
  //Omkar : the file Test Data set is not available on the above path hence updating it
  String filePath =  "./src//test//java//webdriver//examples//spreadsheets//TestDataSet.xlsx";

  @Test
  public void spreadsheetCreateDataFromAllRows() throws IOException {
    String[] worksheets = {"REGRESSPL1", "REGRESSPL2"};
    for (String ws : worksheets) {
      System.out.println("Worksheet: " + ws);
      sheet.spreadsheetCreateDataFromAllRows(
              filePath,
              ws,
              true
      );
    }
  }

  @Test
  public void spreadsheetCreateDataFromColumnStrings() throws IOException {
    List strings = sheet.spreadsheetCreateDataFromColumn(
            filePath,
            "REGRESSPL2",
            "Chg",
            true
    );
  }

  @Test
  public void spreadsheetCreateDataFromColumnNumbers() throws IOException {
    List numbers = sheet.spreadsheetCreateDataFromColumn(
            filePath,
            "REGRESSPL1",
            "Dept",
            true
    );
    System.out.println("Print numbers to show use of created list:");
    for (Object number : numbers) {
      System.out.println(number);
    }
  }
}
