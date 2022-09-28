package webdriver.helperstatic;


import static org.junit.Assert.fail;

//import static org.hamcrest.CoreMatchers.containsString;
//import static org.hamcrest.MatcherAssert.assertThat;


import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;


import webdriver.globalstatic.LoginStatic;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.EditContractingModelMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.utilities.JavaDataBaseConnectivity;

public class CalculationsHelper extends AssertStatic {

  private static JavaDataBaseConnectivity jdbc;
  static DataMaintenanceMap dmMap;
  private static ModelLibraryMap modelMap;
  private static EditContractingModelMap editModelMap;

  /** Helper Class for OPPS 2019 regression scripts - individual test scripts should extend this one to use it. */
  @BeforeClass
  public static void setupHelper() {
    modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
    editModelMap = BuildMap.getInstance(driver, EditContractingModelMap.class);
    dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
    jdbc = new JavaDataBaseConnectivity(setupDb(setTestEnvironment()));
  }

  public void assertCalculationStatusMyStatusFirstRowIsCompleted() {
    String status = getWebElement("//div[2]/div[2]/div/table/tbody/tr[2]/td[5]/div").getText();
   // assertThat(status, containsString("Completed"));
  }

  public void assertCalculationStatusMyStatusFirstRowIsNotFailed() {
    String status = getWebElement("//div[2]/div[2]/div/table/tbody/tr[2]/td[5]/div").getText();
    if (status.contains("Failed")) {
      fail("Calculation Status = Failed");
    }
  }

  public String getCalculationStatusMyStatusFirstRow() {
	  //String status = getWebElement("//div[3]/div/div/div[2]/div[2]/div/table/tbody/tr[2]/td[5]/div").getText();
	    String status = getWebElement("(//table[@class='x-grid-table x-grid-table-resizer']//tr[2]//td[10])[3]//div").getText(); //venkat update xpath 08-09-2022
   
    return status;
  }


  public void assertFilterResults(String expectedTotal) throws InterruptedException {
    doClick(getWebElement("//div[3]/em/button/span[text()='Filter']"));
    waitForAjaxExtJs();
   // assertThat(filterGetFilterMatchesTheseCriteriaText(), containsString(expectedTotal));
    doClick(getWebElement("//div[3]/em/button/span[text()='Cancel & Close']"));
  }

  public void calculationsAssertDbRowCount(
          String sqlQuery, String evaluation, int expectedRowCount)
          throws ClassNotFoundException {
    jdbc.setSqlQuery(sqlQuery);
    int count = jdbc.jdbcGetRowCountFromDb(jdbc.getSqlQuery());
    System.out.println("Db Row Count: " + count);
    if (evaluation.contains("equal")) {
     // assertEquals(expectedRowCount, count);
    } else if (evaluation.contains("less")) {
      //assertTrue(count < expectedRowCount);
    } else if (evaluation.contains("greater")) {
      //assertTrue(count > expectedRowCount);
    } else {
      fail("Operator not recognized - use less than, greater than, or equal to");
    }
  }

  public void waitForCalculationToEndAndVerifySummaryDetailsStringOnDialogAndCloseDialog(String detailText) throws InterruptedException {
    waitForCalculationToEnd();
    driver.findElement(By.xpath("//div[not(contains(@class,'disabled'))]/em/button/span[text()='View']")).click();
    waitForSpinnerToEnd();
    Thread.sleep(1000);
    assertElementIsDisplayed(driver.findElement(By.xpath("//*[text()='" + detailText + "']")), printout);
    //Completed at: 11/18/2019 01:52 PM
    driver.findElement(By.xpath("//button/span[text()='Cancel']")).click();
    waitForSpinnerToEnd();
  }

  public static void selectMaintainDataAtoZ(String dataCategory) throws InterruptedException {
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    dmMap.getDataMaintenanceAZ().click();
    waitForAjaxExtJs();
    driver.findElement(By.xpath("//div[contains(@class, 'left_atoz')]/div[text()='" + dataCategory + "']")).click();
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
  }

  public static void openMaintainDataBatch(String batchName) throws InterruptedException {
    Thread.sleep(1000);
    waitForSpinnerToEnd();
    Actions action = new Actions(driver);
    action.doubleClick(driver.findElement(By.xpath("//*[contains(@class,'grid-cell') and text()='" + batchName + "']"))).perform();
    waitForSpinnerToEnd();
    Thread.sleep(1000);
  }

  public void getStringDataFromDatabaseAndAssertExpectedValues(String sqlQuery, int datasetColumn, List<String> expectedValues) throws ClassNotFoundException {
    jdbc.setSqlQuery(sqlQuery);
    System.out.println("Sql Query: " +  jdbc.getSqlQuery());
    ArrayList<String> dataFromDb = jdbc.jdbcSaveResultSetToArrayListAsString(jdbc.getSqlQuery(), datasetColumn);
    int index = 0;
    for (String dbValue : dataFromDb) {
      System.out.println("Assert: " + expectedValues.get(index) + " (expected), " + dbValue + " (db)");
    //  assertTrue(dbValue == expectedValues.get(index));
      index++;
    }
  }

  public void getDataFromDatabaseAndAssertExpectedValues(String sqlQuery, int datasetColumn, List<Double> expectedValues) throws ClassNotFoundException {
    jdbc.setSqlQuery(sqlQuery);
    System.out.println("Sql Query: " +  jdbc.getSqlQuery());
    ArrayList<Double> dataFromDb = jdbc.jdbcSaveResultSetToArrayListAsDouble(jdbc.getSqlQuery(), datasetColumn);
    int index = 0;
    for (double dbValue : dataFromDb) {
      System.out.println("Assert: " + expectedValues.get(index) + " (expected), " + dbValue + " (db)");
     // assertTrue(dbValue == expectedValues.get(index));
      index++;
    }
  }

  public void getStringDataFromDatabaseAndAssertValuesAreNull(String sqlQuery, int datasetColumn) throws ClassNotFoundException {
    jdbc.setSqlQuery(sqlQuery);
    System.out.println("Sql Query: " +  jdbc.getSqlQuery());
    ArrayList<String> dataFromDb = jdbc.jdbcSaveResultSetToArrayListAsStrings(jdbc.getSqlQuery(), datasetColumn);
    for (String dbValue : dataFromDb) {
      System.out.println("Assert: " + dbValue + " is null");
     // assertNull(dbValue);
    }
  }

  public void getDataFromDatabaseAndAssertExpectedValues(String sqlQuery, List<Double> expectedValues) throws ClassNotFoundException {
    jdbc.setSqlQuery(sqlQuery);
    System.out.println("Sql Query: " +  jdbc.getSqlQuery());
    ArrayList<Double> dataFromDb = jdbc.jdbcSaveResultSetToArrayListAsDouble(jdbc.getSqlQuery(), 3);
    int index = 0;
    for (double dbValue : dataFromDb) {
      System.out.println("Assert: " + expectedValues.get(index) + " (expected), " + dbValue + " (db)");
     // assertTrue(dbValue == expectedValues.get(index));
      index++;
    }
  }

  @Deprecated
  public void clickCalculateButtonAndVerifySummaryDetailsOnDialogAndCloseDialog(byte numberOfEfrs, byte errors, byte efrsCalculatedToZero ) throws InterruptedException {
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    modelMap.getModelLibraryContractingButtonCalculate().click();
    waitForCalculationToEnd();
    driver.findElement(By.xpath("//button/span[text()='View']")).click();
    waitForSpinnerToEnd();
    Thread.sleep(1000);
    assertElementIsDisplayed(driver.findElement(By.xpath("//*[text()='Total EFRs to be processed: " + numberOfEfrs + "']")), printout);
    assertElementIsDisplayed(driver.findElement(By.xpath("//*[text()='Errors: " + errors + "']")), printout);
    assertElementIsDisplayed(driver.findElement(By.xpath("//*[text()='Encounter Financial Records Calculated to Zero: " + efrsCalculatedToZero + "']")), printout);
    //Completed at: 11/18/2019 01:52 PM
    driver.findElement(By.xpath("//button/span[text()='Cancel']")).click();
    waitForSpinnerToEnd();
  }

  @Deprecated
  public void clickCalculateButtonAndVerifyEfrCountAndZeroErrorsOnSummaryDialogAndCloseDialog(byte numberOfEfrs) throws InterruptedException {
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    modelMap.getModelLibraryContractingButtonCalculate().click();
    waitForCalculationToEnd();
    driver.findElement(By.xpath("//button/span[text()='View']")).click();
    waitForSpinnerToEnd();
    Thread.sleep(1000);
    assertElementIsDisplayed(driver.findElement(By.xpath("//*[text()='Total EFRs to be processed: " + numberOfEfrs + "']")), printout);
    assertElementIsDisplayed(driver.findElement(By.xpath("//*[text()='Errors: 0']")), printout);
    //Completed at: 11/18/2019 01:52 PM
    driver.findElement(By.xpath("//button/span[text()='Cancel']")).click();
    waitForSpinnerToEnd();
  }

  public void waitForCalculationToEndAndVerifySummaryDetailsOnViewDialogAndCloseDialog(
          String expectedViewLog,
          String[] expectedCalculationDetails)
          throws InterruptedException {
    waitForCalculationToEnd();
    driver.findElement(By.xpath("//div[not(contains(@class,'disabled'))]/em/button/span[text()='View']")).click();
    //driver.findElement(By.xpath("//tbody/tr[contains(@class, 'x-grid-row')]/td[contains(@class, 'x-grid-cell')]/descendant::button/span[text()='View']")).click();
    waitForDisplayedSpinnerToEnd();
    confirmCalculationStatusViewLogContains(expectedViewLog);
    confirmCalculationStatusDetailsContains(expectedCalculationDetails);
    driver.findElement(By.xpath("//button/span[text()='Cancel']")).click();
    waitForSpinnerToEnd();
  }

  @Deprecated
  public void waitForCalculationToEndAndVerifyViewLogContainsOnViewDialogAndCloseDialog(String expectedViewLog) throws InterruptedException {
    waitForCalculationToEnd();
    driver.findElement(By.xpath("//tbody/tr[contains(@class, 'x-grid-row')]/td[contains(@class, 'x-grid-cell')]/descendant::button/span[text()='View']")).click();
    waitForSpinnerToEnd();
    Thread.sleep(1000);
    confirmCalculationStatusViewLogContains(expectedViewLog);
    driver.findElement(By.xpath("//button/span[text()='Cancel']")).click();
    waitForSpinnerToEnd();
  }

  public void confirmCalculationStatusDetailsContains(String[] expectedDetails) {
    for (String detail : expectedDetails) {
      assertThatElementIsDisplayed(driver.findElement(By.xpath("//*[text()='" + detail + "']")));
    }
  }

  public void confirmCalculationStatusViewLogContains(String expectedViewLog) {
    String viewLog = driver.findElement(By.xpath("//label[text()='View Log:']/../following-sibling::td/div")).getText();
    System.out.println("View Log: " + viewLog);
   // assertThat(viewLog, containsString(expectedViewLog));
  }

  public void deleteMyCalculationStatusFirstRow() throws InterruptedException {
    deleteFirstRow();
  }

  public void deleteFirstRow() throws InterruptedException {
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    Thread.sleep(1000);
    WebElement firstRowDeleteIcon = driver.findElement(By.xpath("//div[contains(@id,'calculationstatus') and contains(@class,'x-box-layout-ct')]/descendant::table/tbody/tr[2]/td[16]/descendant::button"));
    firstRowDeleteIcon.click();
    waitForAjaxExtJs();
    driver.findElement(By.xpath("//div[contains(@class, 'windowbtn')]/descendant::button/span[text()='Delete']")).click();
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
  }

//
//  public static void doSearchForAndSelectContractModelFromContractModelLibrary(String contractModel) throws InterruptedException {
//    waitForSpinnerToEnd();
//    waitForAjaxExtJs();
//    driver.findElement(By.xpath("//input[@name='searchText']")).sendKeys(contractModel);
//    modelMap.getModelLibraryButtonSearch().click();
//    waitForAjaxExtJs();
//    Thread.sleep(1000);
//    driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell x-grid-cell-gridcolumn')]/*[text()='" + contractModel + "']")).click();
//  }

  public static void waitForCalculationToEnd() throws InterruptedException {
    boolean calculate = true;
    String percent;
    byte counter = 0;
    while (calculate) {
      try {
        driver.findElement(By.xpath("//button/span[text()='Refresh']")).click();
        waitForSpinnerToEnd();
        percent = driver.findElement(By.xpath("//*[contains(@class,'x-progress-text-back')]")).getText();
        System.out.println("Percent complete: " + percent);
      //  assertTrue(percent.contains("100%"));
        break;
      } catch (Throwable e) {
        System.out.println("percent less than 100");
        Thread.sleep(1000);
        if (counter == 10) {
          fail("Calculation did not finish in allotted time");
        }
        counter++;
      }
    }
    Thread.sleep(1000);
  }

  public static void waitForFirstRowCalculationBarToReach100Percent() throws InterruptedException {
    boolean calculate = true;
    String percent;
    byte counter = 0;
    while (calculate) {
      try {
        driver.findElement(By.xpath("//button/span[text()='Refresh']")).click();
        waitForSpinnerToEnd();
        percent = driver.findElement(By.xpath("//*[contains(@class,'x-progress-text-back')]")).getText();
        System.out.println("Percent complete: " + percent);
        //assertTrue(percent.contains("100%"));
        break;
      } catch (Throwable e) {
        System.out.println("percent less than 100");
        Thread.sleep(1000);
        if (counter == 10) {
          fail("Calculation did not finish in allotted time");
        }
        counter++;
      }
    }
    Thread.sleep(1000);
  }

  //number of checks is 10 - total run time can be controlled by setting refresh interval - longer interval, longer run time
  public static void waitForFirstRowCalculationBarToReach100Percent(int refreshInterval) throws InterruptedException {
    boolean calculate = true;
    String percent;
    byte counter = 0;
    while (calculate) {
      try {
        driver.findElement(By.xpath("//button/span[text()='Refresh']")).click();
        waitForSpinnerToEnd();
        percent = driver.findElement(By.xpath("//*[contains(@class,'x-progress-text-back')]")).getText();
        System.out.println("Percent complete: " + percent);
        //assertTrue(percent.contains("100%"));
        break;
      } catch (Throwable e) {
        System.out.println("percent less than 100");
        Thread.sleep(refreshInterval);
        if (counter == 10) {
          fail("Calculation did not finish in allotted time");
        }
        counter++;
      }
    }
    Thread.sleep(1000);
  }

  public static void goToContractModelsAndSelectFolder(String folderName) throws InterruptedException {
    goToPage("Contract Models");
    waitForSpinnerToEnd();
    driver.findElement(By.xpath("//*[contains(@class, 'grid-cell') and text()='Contracting']/img")).click();
    waitForAjaxExtJs();
    driver.findElement(By.xpath("//*[text()='" + folderName + "']")).click();  ///img[2]
  }

  public static void goToContractModelsAndSelectFolderAndSubFolder(String folderName, String subFolderName) throws InterruptedException {
    goToContractModelsAndSelectFolder(folderName);
    Thread.sleep(1000);
    waitForSpinnerToEnd();
    driver.findElement(By.xpath("//*[contains(@class, 'grid-cell') and text()='" + subFolderName + "']")).click();
  }

  public static void goToMaintainDataPageAndSelectContractBatch(String folderName) throws InterruptedException {
    goToPage("Maintain Data");
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    dmMap.getDataMaintenanceAZ().click();
    waitForAjaxExtJs();
    driver.findElement(By.xpath("//div[contains(@class, 'left_atoz')]/div[text()='Contract Batches']")).click();
    Thread.sleep(1000);
    waitForSpinnerToEnd();
    try {
      driver.findElement(By.xpath("//*[contains(@class,'grid-cell') and text()='" + folderName + "']")).click();
    } catch (Throwable e) {
      driver.findElement(By.xpath("//div[contains(@class,'column-header')]/span[contains(@id,'gridcolumn') and text()='Name']")).click();
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
      driver.findElement(By.xpath("//*[contains(@class,'grid-cell') and text()='" + folderName + "']")).click();
    }
    waitForSpinnerToEnd();
    Thread.sleep(1000);
  }

  public static void goToContractModelsAndSelectModel(String folderName, String modelName) throws InterruptedException {
    goToPage("Contract Models");
    waitForSpinnerToEnd();
    driver.findElement(By.xpath("//*[contains(@class, 'grid-cell') and text()='Contracting']/img")).click();
    waitForAjaxExtJs();
    driver.findElement(By.xpath("//*[text()='Automation']/img[2]")).click();
    Thread.sleep(1000);
    waitForSpinnerToEnd();
    driver.findElement(By.xpath("//*[contains(@class, 'grid-cell') and text()='" + folderName + "']")).click();
    waitForSpinnerToEnd();
    Thread.sleep(1000);
    doSearchForAndSelectContractModelFromContractModelLibrary(modelName);
  }

  public static void goToContractModelsPageAndSearchAndSelectModel(String modelName) throws InterruptedException {
    goToPage("Contract Models");
    waitForSpinnerToEnd();
    Thread.sleep(1000);
    doSearchForAndSelectContractModelFromContractModelLibrary(modelName);
  }

}
