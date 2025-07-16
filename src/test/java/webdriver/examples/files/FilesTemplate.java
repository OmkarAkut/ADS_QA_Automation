package webdriver.examples.files;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import webdriver.utilities.FileUtility;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FilesTemplate {

  FileUtility filer = new FileUtility();
  //Shilpa updated below to get file from user.dir 
  
  File file = filer.getFile(System.getProperty("user.dir")+
        "//src//test//java//webdriver//examples//files//TestFile.txt")
  ;
  String filePath =  System.getProperty("user.dir")+"//src//test//java//webdriver//examples//files//TestFile.txt";
  //String directoryPath = "C:\\SpreadsheetsTest";  //external file-see commented out examples below
//  String dataFile = "./src\\test\\java\\webdriver\\templates\\files\\SampleDataTextFile.txt";
  String dataFile =System.getProperty("user.dir")+"//src//test//java//webdriver//examples//files//SampleDataTextFile.txt";
  static String zipPath = null;

  @Test
  public void parseFileRowsWithCommasIntoData() throws IOException {
	  System.out.println(filePath);
    //Note: The number of rows in the data file controls the number of test iterations
    //get file with comma-delimited rows of data
    List<String> myList = filer.convertFileLinesToList(dataFile);
    int row = 0;
    for (String eachRow : myList) {
      row++;
      System.out.print("Row " + row);
      String[] dataSet = filer.parseFileRowIntoArray(eachRow);
      System.out.println(": Datum = " + dataSet.length);
      //No need to assign array items to variables - just use indexes as below
      //Note that Arrays start with index 0.
      System.out.println(dataSet[0] + ": " + dataSet[1] + dataSet[2] + dataSet[3] + dataSet[4]);
      //add actual test steps that use data values above here
    }
  }

  @Test
  public void readFile() throws IOException {
    filer.readAndPrintFile(file);
  }

  @Test
  public void convertFileLinesToListAndAssertResult() throws IOException {
	  System.out.println(filePath);
    List<String> myList = filer.convertFileLinesToList(filePath);
    for (String line : myList) {
      System.out.println(line);
    }
    System.out.println("File Size: " + myList.size());
    assertThat(myList.size(), equalTo(3));
    assertTrue(myList.contains("1,2,3,4,5,6"));
  }

  @Test
  public void readFileToString() {
    String myList = filer.convertFileToString(filePath);
    System.out.println(myList);
    assertTrue(myList.contains("1,2,3"));
  }

  //Below examples commented out, since file would reside externally
//  @Test
//  public void test01GetTargetFilePathFromZipDirectory() {
//    zipPath = filer.getFilesFromDirectoryAndReturnTargetFilePath(
//            directoryPath,
//            "Encounters With No Charges Report",
//            "zip"
//    );
//    System.out.println("Zip Path: " + zipPath);
//  }
//
//  @Test
//  public void test02ConvertTargetFileLinesToListAndAssertListContainsTargetLine() throws IOException {
//    List<String> report = filer.convertFileInZipDirectoryToList(
//            zipPath,
//            "summary"
//    );
//    for (String line : report) {
//      System.out.println(line);
//    }
//    assertTrue(report.contains("Encounter Type selection 1: 1S1"));
//  }

}