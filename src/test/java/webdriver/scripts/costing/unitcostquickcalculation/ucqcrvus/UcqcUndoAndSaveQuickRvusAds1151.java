package webdriver.scripts.costing.unitcostquickcalculation.ucqcrvus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UcqcUndoAndSaveQuickRvusAds1151 extends UcqcHelper {

  private static CostingMap overwriteRVUMaintenance;

  /** Automates test ticket ADS-1151 (Jira Dev Story: ADS-287).  Updated: 08-14-19.
   Description: This script verifies that the Save Quick RVUs and Undo buttons on UCQC page enable and disable properly. */

  @BeforeClass
  public static void setupScript() throws Exception,Throwable {
		ExtentReport.reportCreate("UcqcUndoAndSaveQuickRvusAds1151", "webdriver.scripts.costing.unitcostquickcalculation.ucqcrvus", "UcqcUndoAndSaveQuickRvusAds1151");

	try {
		overwriteRVUMaintenance = BuildMap.getInstance(driver, CostingMap.class);
		System.out.println("Test Class: " + UcqcUndoAndSaveQuickRvusAds1151.class.getSimpleName());
		Login.loginUser("CostingDepartmentManager1");
		goToPage("Unit Cost Quick Calculation");
		ExtentReport.logPass("PASS", "setupScript is PASS");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "Failed in setupScript", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test02UndoAndSaveQuickRvuButtonsDisabledByDefault() throws Exception,Throwable {
    try {
		waitForAjaxExtJs();
		assertElementIsDisabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonUndo(),printout);
		assertElementIsDisabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonSaveQuickRVUs(),printout);
		ExtentReport.logPass("PASS", "test02UndoAndSaveQuickRvuButtonsDisabledByDefault");

    } catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test02UndoAndSaveQuickRvuButtonsDisabledByDefault", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test04aPopulateRequiredFieldsAndVerifySaveQuickRvusAndUndoButtonsAreDisabled() throws Throwable {
    try {
      waitForAjaxExtJs();
//      setUcqcCriteria("QA Cost Model","QA Automation Save Quick RVUs","150 Marina Medical Center","3010  SURGERY", "Apr 2004 to Apr 2004");
      setUcqcCriteria("QA Cost Model","QA Automation Save Quick RVUs","150 Marina Medical Center","3010", "Apr 2004 to Apr 2004");
      waitForAjaxExtJs();
      assertElementIsDisabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonUndo(),printout);
      assertElementIsDisabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonSaveQuickRVUs(),printout);
      ExtentReport.logPass("PASS", "test04aPopulateRequiredFieldsAndVerifySaveQuickRvusAndUndoButtonsAreDisabled");
    } catch (Exception|AssertionError e) {
      
      ExtentReport.logFail("FAIL", "test04aPopulateRequiredFieldsAndVerifySaveQuickRvusAndUndoButtonsAreDisabled", driver, e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test04bClickApplySelectionsButtonAndVerifySaveQuickRvusAndUndoButtonsAreEnabled() throws Throwable {
    try {
      Thread.sleep(500);
      doClick(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonApplySelections());
      ucqcWaitForSpinnerToEnd();
      waitForAjaxExtJs();
      assertElementIsDisabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonUndo(),printout);
      assertElementIsDisabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonSaveQuickRVUs(),printout);
      ExtentReport.logPass("PASS", "test04aPopulateRequiredFieldsAndVerifySaveQuickRvusAndUndoButtonsAreDisabled");
    } catch (Exception|AssertionError e) {
        ExtentReport.logFail("FAIL", "test04aPopulateRequiredFieldsAndVerifySaveQuickRvusAndUndoButtonsAreDisabled", driver, e);
        fail(e.getMessage());
    }
  }

  @Test
  public void test05aUpdateAQuickRvuCellAndVerifySaveQuickRvusAndUndoButtonsAreEnabled() throws Throwable {
    try {
      ucqcUpdateGridCellValue("2601201", "Quick Salaries and Wages RVU", "10", printout);
    	//ucqcUpdateGridCellValue("1100148", "Quick Salaries and Wages RVU", "10", printout);
      ucqcWaitForSpinnerToEnd();
      waitForAjaxExtJs();
      assertElementIsEnabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonUndo(),printout);
      assertElementIsEnabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonSaveQuickRVUs(),printout);
      ExtentReport.logPass("PASS", "test05aUpdateAQuickRvuCellAndVerifySaveQuickRvusAndUndoButtonsAreEnabled");

    } catch (Exception|AssertionError e) {
        ExtentReport.logFail("FAIL", "test05aUpdateAQuickRvuCellAndVerifySaveQuickRvusAndUndoButtonsAreEnabled", driver, e);

      fail(e.getMessage());
    }
  }

  public void ucqcUpdateGridCellValue(String chargeCode, String headerName, String newValue, boolean printout) throws InterruptedException {
    if (printout) {
      System.out.println("For cell update, Charge Code: " + chargeCode);
      System.out.println("For cell update, Header Name: " + headerName);
      System.out.println("For cell update, New Value: " + newValue);
    }
    //get row from charge code
    String rowAsString = driver.findElement(By.xpath("//*[text()='" + chargeCode + "']/../../descendant::div[1]")).getText();
    int row = Integer.parseInt(rowAsString);

    //adjust row if not on first page of grid
    if (row > 99) {
      int digit = Integer.parseInt(rowAsString.substring(0, 1)) * 100;
      if (printout) {
        System.out.println("subtract " + digit);
      }
      row = row - digit;
    }
    if (printout) {
      System.out.println("For cell update, Row Number: " + row);
    }

    //get column id from column header
    String columnId;
    columnId = driver.findElement(By.xpath("//*[contains(@class,'column-header-text')][text()='" + headerName + "']")).getAttribute("id");
    int columnIdDigits = Integer.parseInt(getNumbersFromStringWithRegex(columnId));
    if (printout) {
      System.out.println("For cell update, columnIdDigits: " + columnIdDigits);
    }
    //click in cell and update
    driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')][" + row + "]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-" + columnIdDigits + "')]")).click();
    waitForAjaxExtJs();
    WebElement editCell = driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')][" + row + "]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-" + columnIdDigits + "')]")); // /div/table
    Actions action = new Actions(driver);
    //Shilpa 08.09.2022 added below lines for input
    action.moveToElement(editCell).clickAndHold().sendKeys(Keys.chord(Keys.CONTROL, "a")).sendKeys(Keys.chord(Keys.CLEAR));
    Thread.sleep(1000);
    action.moveToElement(editCell).clickAndHold().sendKeys(newValue).sendKeys(Keys.chord(Keys.ENTER)).build().perform();
    waitForSpinnerToEnd();
  }

  @Test
  public void test05bMoveToAnotherPageAfterEditingCellAndVerifySaveQuickRvusAndUndoButtonsAreEnabled() throws Throwable {
    try {
      driver.findElement(By.xpath("//*[@class='x-btn-icon pagging-tbar-next-button']")).click();
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
    assertEquals(101, ucqcGridGetFirstRowNumberOnPage(printout)); //verifies new page is displayed
    assertElementIsEnabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonUndo(),printout);
      assertElementIsEnabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonSaveQuickRVUs(),printout);
      ExtentReport.logPass("PASS", "test05bMoveToAnotherPageAfterEditingCellAndVerifySaveQuickRvusAndUndoButtonsAreEnabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test05bMoveToAnotherPageAfterEditingCellAndVerifySaveQuickRvusAndUndoButtonsAreEnabled", driver, e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test05cMoveBackToInitialPageAfterEditingCellAndVerifySaveQuickRvusAndUndoButtonsAreEnabled() throws Throwable {
    try {

    	  ucqcUpdateGridCellValuex("2782092", "Quick Salaries and Wages RVU", "21", printout);
//    	ucqcUpdateGridCellValuex("2001626", "Quick Salaries and Wages RVU", "21", printout);
    	ucqcWaitForSpinnerToEnd();
      waitForAjaxExtJs();
      driver.findElement(By.xpath("//*[@class='x-btn-icon pagging-tbar-previous-button']")).click();
      ucqcWaitForSpinnerToEnd();
      waitForAjaxExtJs();
      
      ucqcGridAssertCellValueIsHighlighted("2601201", "Quick Salaries and Wages RVU", printout);

     // ucqcGridAssertCellValueIsHighlighted("1100148", "Quick Salaries and Wages RVU", printout);
      assertElementIsEnabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonUndo(),printout);
      assertElementIsEnabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonSaveQuickRVUs(),printout);
      ExtentReport.logPass("PASS", "test05cMoveBackToInitialPageAfterEditingCellAndVerifySaveQuickRvusAndUndoButtonsAreEnabled");

    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test05cMoveBackToInitialPageAfterEditingCellAndVerifySaveQuickRvusAndUndoButtonsAreEnabled", driver, e);

      fail(e.getMessage());
    }
  }

  @Test
  public void test08ClickUndoButtonAndVerifyOverwriteRvuMaintenanceButtonIsDisabledAndEditedCellsOnBothPagesAreNotHighlighted() throws Throwable {
    try {
      doClick(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonUndo());
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
      assertElementIsDisabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonUndo(),printout);
      assertElementIsDisabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonSaveQuickRVUs(),printout);
      ucqcGridAssertCellValueIsNotHighlighted("2601201", "Quick Salaries and Wages RVU", printout);

      driver.findElement(By.xpath("//*[@class='x-btn-icon pagging-tbar-next-button']")).click();
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
      assertEquals(101, ucqcGridGetFirstRowNumberOnPage(printout)); //verifies new page is displayed
      assertElementIsDisabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonUndo(),printout);
      assertElementIsDisabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonSaveQuickRVUs(),printout);
      ExtentReport.logPass("PASS", "test08ClickUndoButtonAndVerifyOverwriteRvuMaintenanceButtonIsDisabledAndEditedCellsOnBothPagesAreNotHighlighted");

    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test08ClickUndoButtonAndVerifyOverwriteRvuMaintenanceButtonIsDisabledAndEditedCellsOnBothPagesAreNotHighlighted", driver, e);

      fail(e.getMessage());
    }
  }

  @Test
  public void test09SaveUpdatedQuickRvuGridCellValuesAcrossPagesAndVerifyProperButtonsAreEnabledDisabled() throws Throwable {
    try {
      goToFirstPage();
      //set values on page 1
      String randomValue1 = javaGetRandomNumber(50,printout) + ".000000";
      System.out.println("randomValue1"+randomValue1);
      ucqcUpdateGridCellValuex("2601201", "Quick Salaries and Wages RVU", randomValue1, printout);
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
      //set value on page 2
      goToNextPage();
      String randomValue2 = javaGetRandomNumber(50,printout) + ".000000";
      System.out.println("randomValue2"+randomValue2);
      ucqcUpdateGridCellValuex("2782092", "Quick Salaries and Wages RVU", randomValue2, printout);
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
      doClick(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonSaveQuickRVUs()); //CLICK Save
      ucqcWaitForSpinnerToEnd();
      waitForAjaxExtJs();
      assertElementIsDisabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonSaveQuickRVUs(),printout);
      //check page 2
      String checkCellValue2 = getCellValuex("2782092", "Quick Salaries and Wages RVU");
      assertEquals(randomValue2, checkCellValue2);
      assertElementIsDisabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonSaveQuickRVUs(),printout);
      //check page 1
      goToFirstPage();
      String checkCellValue1 = getCellValuex("2601201", "Quick Salaries and Wages RVU");
      assertEquals(randomValue1, checkCellValue1);
      assertElementIsDisabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonSaveQuickRVUs(),printout);
      ExtentReport.logPass("PASS", "test09SaveUpdatedQuickRvuGridCellValuesAcrossPagesAndVerifyProperButtonsAreEnabledDisabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test09SaveUpdatedQuickRvuGridCellValuesAcrossPagesAndVerifyProperButtonsAreEnabledDisabled", driver, e);

      fail(e.getMessage());
    }
  }

  public String getCellValuex(String chargeCode, String headerName) throws InterruptedException {
    System.out.println(chargeCode);
    System.out.println(headerName);
    //get row from charge code
    String rowAsString = driver.findElement(By.xpath("//*[text()='" + chargeCode + "']/../../descendant::div[1]")).getText();
    int row = Integer.parseInt(rowAsString);
    //adjust row if not on first page of grid
    if (row > 99) {
      int digit = Integer.parseInt(rowAsString.substring(0, 1)) * 100;
      System.out.println("subtract " + digit);
      row = row - digit;
    }
    System.out.println("Row Number: " + row);
    String columnID;
    columnID = driver.findElement(By.xpath("//*[contains(@class,'column-header-text')][text()='" + headerName + "']")).getAttribute("id");
    int columnIdDigits = Integer.parseInt(getNumbersFromStringWithRegex(columnID));
    String columnValue = driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')][" + row + "]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-" + columnIdDigits + "')]")).getText();
    System.out.println("Value: " + columnValue);
    return columnValue;
  }

  private void goToFirstPage() throws InterruptedException {
    driver.findElement(By.xpath("//*[@class='x-btn-icon pagging-tbar-previous-button']")).click();
    ucqcWaitForSpinnerToEnd();
    waitForAjaxExtJs();
    assertEquals(1, ucqcGridGetFirstRowNumberOnPage(printout)); //verifies new page is displayed
  }

  private void goToNextPage() throws InterruptedException {
    driver.findElement(By.xpath("//*[@class='x-btn-icon pagging-tbar-next-button']")).click();
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    assertEquals(101, ucqcGridGetFirstRowNumberOnPage(printout)); //verifies new page is displayed
  }

  public void ucqcGridAssertCellValueIsHighlighted(String chargeCode, String headerName, boolean printout) throws InterruptedException {

    if (printout) {
      System.out.println(chargeCode);
      System.out.println(headerName);
    }
    //get row from charge code
    String rowAsString = driver.findElement(By.xpath("//*[text()='" + chargeCode + "']/../../descendant::div[1]")).getText();
    int row = Integer.parseInt(rowAsString);

    //adjust row if not on first page of grid
    if (row > 99) {
      int digit = Integer.parseInt(rowAsString.substring(0, 1)) * 100;
      if (printout) {
        System.out.println("subtract " + digit);
      }
      row = row - digit;
    }
    if (printout) {
      System.out.println("For cell update, Row Number: " + row);
    }
    String columnId;
    columnId = driver.findElement(By.xpath("//*[contains(@class,'column-header-text')][text()='" + headerName + "']")).getAttribute("id");
    int columnIdDigits = Integer.parseInt(getNumbersFromStringWithRegex(columnId));

    String columnValue = driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')][" + row + "]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-" + columnIdDigits + "')]")).getAttribute("class");
    if (printout) {
      System.out.println("Value: " + columnValue);
    }
    assertTrue(columnValue.contains("gridMarkCls"));
  }

  public void ucqcGridAssertCellValueIsNotHighlighted(String chargeCode, String headerName, boolean printout) throws InterruptedException {

    if (printout) {
      System.out.println("Cell Value Not Highlighted: " + chargeCode);
      System.out.println("Cell Value Not Highlighted: " + headerName);
    }
    //get row from charge code
    String rowAsString = driver.findElement(By.xpath("//*[text()='" + chargeCode + "']/../../descendant::div[1]")).getText();
    int row = Integer.parseInt(rowAsString);

    //adjust row if not on first page of grid
    if (row > 99) {
      int digit = Integer.parseInt(rowAsString.substring(0, 1)) * 100;
      if (printout) {
        System.out.println("subtract " + digit);
      }
      row = row - digit;
    }
    if (printout) {
      System.out.println("For Assert Grid Value Not Highlighted, Row Number: " + row);
    }
    String columnId;
    columnId = driver.findElement(By.xpath("//*[contains(@class,'column-header-text')][text()='" + headerName + "']")).getAttribute("id");
    int columnIdDigits = Integer.parseInt(getNumbersFromStringWithRegex(columnId));
    String columnValue = driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')][" + row + "]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-" + columnIdDigits + "')]")).getAttribute("class");
    if (printout) {
      System.out.println("For Assert Grid Value Not Highlighted (should not contain 'gridMarkCls', Value= " + columnValue);
    }
    assertFalse(columnValue.contains("gridMarkCls"));
  }

  public int ucqcGridGetFirstRowNumberOnPage(boolean printout) {
    int cellValue = Integer.parseInt(driver.findElement(By.xpath("//tbody/tr[2]/td[contains(@class,'rownumberer')]/div")).getText());
    if (printout) {
      System.out.println("First row number on page: " + cellValue);
    }
    return cellValue;
  }

  public void ucqcUpdateGridCellValuex(String chargeCode, String headerName, String newValue, boolean printout) throws InterruptedException {
    if (printout) {
      System.out.println("For cell update, Charge Code: " + chargeCode);
      System.out.println("For cell update, Header Name: " + headerName);
      System.out.println("For cell update, New Value: " + newValue);
    }
    //get row from charge code
    String rowAsString = driver.findElement(By.xpath("//*[text()='" + chargeCode + "']/../../descendant::div[1]")).getText();
    int row = Integer.parseInt(rowAsString);

    //adjust row if not on first page of grid
    if (row > 99) {
      int digit = Integer.parseInt(rowAsString.substring(0, 1)) * 100;
      if (printout) {
        System.out.println("subtract " + digit);
      }
      row = row - digit;
    }
    if (printout) {
      System.out.println("For cell update, Row Number: " + row);
    }

    //get column id from column header
    String columnId;
    columnId = driver.findElement(By.xpath("//*[contains(@class,'column-header-text')][text()='" + headerName + "']")).getAttribute("id");
    int columnIdDigits = Integer.parseInt(getNumbersFromStringWithRegex(columnId));
    if (printout) {
      System.out.println("For cell update, columnIdDigits: " + columnIdDigits);
    }
    //click in cell and update
    driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')][" + row + "]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-" + columnIdDigits + "')]")).click();
    waitForAjaxExtJs();
    WebElement editCell = driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')][" + row + "]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-" + columnIdDigits + "')]/div/table"));
    Actions action=new Actions(driver);
    action.moveToElement(editCell).clickAndHold().sendKeys(Keys.chord(Keys.CONTROL, "a")).sendKeys(Keys.chord(Keys.CLEAR));
    Thread.sleep(1000);
    action.moveToElement(editCell).clickAndHold().sendKeys(newValue).sendKeys(Keys.chord(Keys.ENTER)).build().perform();
  Thread.sleep(1000);
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
  }
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
