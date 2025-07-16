package webdriver.scripts.costing.unitcostquickcalculation.ucqcrvus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UcqcEnableDisableOverwriteRvuMaintenanceButtonAds1182 extends UcqcHelper {

  private static CostingMap overwriteRVUMaintenance;
  private static String cellValue;

  /** Automates test ticket ADS-1182 (Dev Story: ADS-301).
   * This script confirms that the Overwrite RVU Maintenance button enables and disables properly.*/
  @BeforeClass
  public static void setupScript() throws Exception,Throwable{
	  ExtentReport.reportCreate("UcqcEnableDisableOverwriteRvuMaintenanceButtonAds1182", "webdriver.scripts.costing.unitcostquickcalculation.ucqcrvus", "UcqcEnableDisableOverwriteRvuMaintenanceButtonAds1182");
    try {
		overwriteRVUMaintenance = BuildMap.getInstance(driver, CostingMap.class);
		System.out.println("Test Class: " + UcqcEnableDisableOverwriteRvuMaintenanceButtonAds1182.class.getSimpleName());
		Login.loginUser("CostingDepartmentManager1");
		doMaximizeWindow();
		goToPage("Unit Cost Quick Calculation");
		 ExtentReport.logPass("PASS", "setupScript");
			} catch (Exception|AssertionError e) {
				ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
				fail(e.getMessage());
			}
  }

  @Test
  public void test01OverwriteRvuMaintenanceButtonDisabledByDefault() throws Throwable {
    try {
      waitForAjaxExtJs();
      assertElementIsDisabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonOverwriteRVUMaintenance(),printout);
      ExtentReport.logPass("PASS", "test01OverwriteRvuMaintenanceButtonDisabledByDefault");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test01OverwriteRvuMaintenanceButtonDisabledByDefault", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test02PopulateRequiredFieldsAndVerifyOverwriteRvuMaintenanceButtonIsDisabled() throws Throwable {  //QA Automation Save Quick RVUs
    try {
      waitForAjaxExtJs();
      //setUCQCCriteria("QA Cost Model","QA Automation Save Quick RVUs","150 Marina Medical Center","2110  ICU", "Apr 2004 to Apr 2004");
      setUcqcCriteria("QA Cost Model","QA Automation Save Quick RVUs","150 Marina Medical Center","2110", "Apr 2004 to Apr 2004");//Shilpa 15.09.2022 updated from 2110 ICU to 2110 
      assertElementIsEnabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonApplySelections(),printout);
      assertElementIsDisabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonOverwriteRVUMaintenance(),printout);
      ExtentReport.logPass("PASS", "test02PopulateRequiredFieldsAndVerifyOverwriteRvuMaintenanceButtonIsDisabled");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test02PopulateRequiredFieldsAndVerifyOverwriteRvuMaintenanceButtonIsDisabled", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test03ClickApplySelectionsButtonAndConfirmExpectedButtonsAreDisabled() throws Throwable {
    try {
      doClick(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonApplySelections());
      ucqcWaitForSpinnerToEnd();
      waitForAjaxExtJs();
      assertElementIsDisabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(),printout);
      assertElementIsDisabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonUndo(),printout);
      assertElementIsDisabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonSaveQuickRVUs(),printout);
      ExtentReport.logPass("PASS", "test03ClickApplySelectionsButtonAndConfirmExpectedButtonsAreDisabled");
     	} catch (Exception|AssertionError e) {
     		ExtentReport.logFail("FAIL", "test03ClickApplySelectionsButtonAndConfirmExpectedButtonsAreDisabled", driver, e);
     		fail(e.getMessage());
     	}
  }

  @Test
  public void test04ClickApplySelectionsButtonAndConfirmOverwriteRvuMaintenanceButtonIsEnabled() throws Throwable {
    try {
      assertElementIsEnabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonCopyToQuickRVUs(),printout);
      assertElementIsEnabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonCalculate(),printout);
      assertElementIsEnabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonOverwriteRVUMaintenance(),printout);
      ExtentReport.logPass("PASS", "test04ClickApplySelectionsButtonAndConfirmOverwriteRvuMaintenanceButtonIsEnabled");
 	} catch (Exception|AssertionError e) {
 		ExtentReport.logFail("FAIL", "test04ClickApplySelectionsButtonAndConfirmOverwriteRvuMaintenanceButtonIsEnabled", driver, e);
 		fail(e.getMessage());
 	}
  }

  @Test
  public void test05EditExistingValueListedUnderAQuickRvuGridColumnAndVerifyButtonsAreEnabledDisabled() throws Throwable {
    try {
      //Evolve: edit an existing value under the Quick Salaries and Wages RVU column*/
      cellValue = getCellValue("1100049", "Quick Salaries and Wages RVU");
      System.out.println("initial cell value: " + cellValue);
      ucqcUpdateGridCellValue("1100049", "Quick Salaries and Wages RVU", cellValue, printout);
      assertElementIsEnabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonUndo(),printout);
      assertElementIsEnabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonSaveQuickRVUs(),printout);
      assertElementIsDisabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonOverwriteRVUMaintenance(),printout);
      ExtentReport.logPass("PASS", "test05EditExistingValueListedUnderAQuickRvuGridColumnAndVerifyButtonsAreEnabledDisabled");
 	} catch (Exception|AssertionError e) {
 		ExtentReport.logFail("FAIL", "test05EditExistingValueListedUnderAQuickRvuGridColumnAndVerifyButtonsAreEnabledDisabled", driver, e);
 		fail(e.getMessage());
 	}
  }

  @Test
  public void test06ClickUndoButtonAfterUpdatingAnyQuickRvuGridCellValueAndVerifyProperButtonsAreEnabled() throws Throwable {
    try {
      doClick(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonUndo());
      ucqcWaitForSpinnerToEnd();
      waitForAjaxExtJs();
      String checkCellValue = getCellValue("1100049", "Quick Salaries and Wages RVU");
      assertEquals(cellValue, checkCellValue);
      assertElementIsEnabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonOverwriteRVUMaintenance(),printout);
      ExtentReport.logPass("PASS", "test06ClickUndoButtonAfterUpdatingAnyQuickRvuGridCellValueAndVerifyProperButtonsAreEnabled");
 	} catch (Exception|AssertionError e) {
 		ExtentReport.logFail("FAIL", "test06ClickUndoButtonAfterUpdatingAnyQuickRvuGridCellValueAndVerifyProperButtonsAreEnabled", driver, e);
 		fail(e.getMessage());
 	}
  }

  @Test
  public void test07ClickSaveQuickRvusButtonAndVerifyOverwriteRvuMaintenanceButtonIsEnabled() throws Throwable {
    try {
      ucqcUpdateGridCellValue("1100049", "Quick Salaries and Wages RVU", cellValue, printout);
      doClick(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonSaveQuickRVUs());
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
      assertElementIsEnabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonOverwriteRVUMaintenance(),printout);
      ExtentReport.logPass("PASS", "test07ClickSaveQuickRvusButtonAndVerifyOverwriteRvuMaintenanceButtonIsEnabled");
 	} catch (Exception|AssertionError e) {
 		ExtentReport.logFail("FAIL", "test07ClickSaveQuickRvusButtonAndVerifyOverwriteRvuMaintenanceButtonIsEnabled", driver, e);
 		fail(e.getMessage());
 	}
  }
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
