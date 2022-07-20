package webdriver.scripts.costing.unitcostquickcalculation.ucqcrvus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
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
  public static void setupScript() throws Exception {
    overwriteRVUMaintenance = BuildMap.getInstance(driver, CostingMap.class);
    System.out.println("Test Class: " + UcqcEnableDisableOverwriteRvuMaintenanceButtonAds1182.class.getSimpleName());
    Login.loginUser("CostingDepartmentManager1");
    doMaximizeWindow();
    goToPage("Unit Cost Quick Calculation");
  }

  @Test
  public void test01OverwriteRvuMaintenanceButtonDisabledByDefault() {
    try {
      waitForAjaxExtJs();
      assertElementIsDisabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonOverwriteRVUMaintenance(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test02PopulateRequiredFieldsAndVerifyOverwriteRvuMaintenanceButtonIsDisabled() {  //QA Automation Save Quick RVUs
    try {
      waitForAjaxExtJs();
      //setUCQCCriteria("QA Cost Model","QA Automation Save Quick RVUs","150 Marina Medical Center","2110  ICU", "Apr 2004 to Apr 2004");
      setUcqcCriteria("QA Cost Model","QA Automation Save Quick RVUs","150 Marina Medical Center","2110  ICU", "Apr 2004 to Apr 2004");
      assertElementIsEnabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonApplySelections(),printout);
      assertElementIsDisabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonOverwriteRVUMaintenance(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test03ClickApplySelectionsButtonAndConfirmExpectedButtonsAreDisabled() {
    try {
      doClick(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonApplySelections());
      ucqcWaitForSpinnerToEnd();
      waitForAjaxExtJs();
      assertElementIsDisabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave(),printout);
      assertElementIsDisabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonUndo(),printout);
      assertElementIsDisabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonSaveQuickRVUs(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test04ClickApplySelectionsButtonAndConfirmOverwriteRvuMaintenanceButtonIsEnabled() {
    try {
      assertElementIsEnabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonCopyToQuickRVUs(),printout);
      assertElementIsEnabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonCalculate(),printout);
      assertElementIsEnabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonOverwriteRVUMaintenance(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test05EditExistingValueListedUnderAQuickRvuGridColumnAndVerifyButtonsAreEnabledDisabled() {
    try {
      //Evolve: edit an existing value under the Quick Salaries and Wages RVU column*/
      cellValue = getCellValue("1100049", "Quick Salaries and Wages RVU");
      System.out.println("initial cell value: " + cellValue);
      ucqcUpdateGridCellValue("1100049", "Quick Salaries and Wages RVU", cellValue, printout);
      assertElementIsEnabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonUndo(),printout);
      assertElementIsEnabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonSaveQuickRVUs(),printout);
      assertElementIsDisabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonOverwriteRVUMaintenance(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test06ClickUndoButtonAfterUpdatingAnyQuickRvuGridCellValueAndVerifyProperButtonsAreEnabled() {
    try {
      doClick(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonUndo());
      ucqcWaitForSpinnerToEnd();
      waitForAjaxExtJs();
      String checkCellValue = getCellValue("1100049", "Quick Salaries and Wages RVU");
      assertEquals(cellValue, checkCellValue);
      assertElementIsEnabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonOverwriteRVUMaintenance(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test07ClickSaveQuickRvusButtonAndVerifyOverwriteRvuMaintenanceButtonIsEnabled() {
    try {
      ucqcUpdateGridCellValue("1100049", "Quick Salaries and Wages RVU", cellValue, printout);
      doClick(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonSaveQuickRVUs());
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
      assertElementIsEnabled(overwriteRVUMaintenance.getUnitCostQuickCalculationButtonOverwriteRVUMaintenance(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }
}
