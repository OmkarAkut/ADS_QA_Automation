package webdriver.scripts.costing.unitcostquickcalculation.ucqcrvus;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation.UcqcCalculateButtonEnableAndDisableAds1152;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ValidateWarningForRvuMaintenancePopUpAds1249 extends UcqcHelper {

  private static CostingMap costingMap;
  String expectedWarningMessage = "Decision Support will overwrite RVUs on the RVU Maintenance screen with the quick RVUs you selected. "
      + "Charge codes that do not have results for the cost model scenario do not appear on this screen; RVUs associated with those codes will retain their original value. "
      + "Click Overwrite to continue and replace the RVUs; or, click Cancel to return to the previous screen without overwriting the RVUs.";

  /** Test ticket ADS-1249 (dev story ADS-742)
  This script validates the warning on the Overwrite RVU Maintenance pop up.*/
  @BeforeClass
  public static void setupScript() throws Exception,Throwable {
	  ExtentReport.reportCreate("ValidateWarningForRvuMaintenancePopUpAds1249", "webdriver.scripts.costing.unitcostquickcalculation.ucqcrvus", "ValidateWarningForRvuMaintenancePopUpAds1249");
    try {
		costingMap = BuildMap.getInstance(driver, CostingMap.class);
		System.out.println("Test Class: " + UcqcCalculateButtonEnableAndDisableAds1152.class.getSimpleName());
		Login.loginUser("CostingDepartmentManager1");
		goToPage("Unit Cost Quick Calculation");
		doMaximizeWindow();
		ucqcDisplayChargeCodeGrid(
		        "Marina",
		        "*CM1 TB MHFY05 After Vol Change_UCQC",
		        "150 Marina Medical Center",
		       // "3145  ENDOSCOPY",
		        "3145", //venkat updated department filed data 21.09.2022
		        "Jan 2005 to Jan 2005");
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test01aClickOverwriteRvusButtonAndAssertWarningForRvuMaintenancePopUpDisplays() throws Throwable {
    try {
      doClick(costingMap.getUnitCostQuickCalculationButtonOverwriteRVUMaintenance());
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
      selectCostComponent("Salaries and Wages");
      doClick(costingMap.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonOverwriteRVUs());
      assertElementText(driver.findElement(By.xpath("//div[contains(@class,'errorMsg')]")), expectedWarningMessage, printout);
      ExtentReport.logPass("PASS", "test01aClickOverwriteRvusButtonAndAssertWarningForRvuMaintenancePopUpDisplays");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test01aClickOverwriteRvusButtonAndAssertWarningForRvuMaintenancePopUpDisplays", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test01bOnOverwriteRvuDialogAssertDialogElementsAreDisplayed() throws Throwable {
    try {
      assertElementIsDisplayedWithXpath("//span[contains(@id,'warningwindow') and text()='Warning']");
      assertElementIsDisplayedWithXpath("//button/span[text()='Cancel']");
      assertElementIsDisplayedWithXpath("//button/span[text()='Overwrite']");
      assertElementIsDisplayedWithXpath("//div[contains(@id,'warningwindow')]/div/img[@class='x-tool-close']");
      doClick(costingMap.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalWarningButtonCancel());
      waitForAjaxExtJs();
      doClick(costingMap.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonCancelAndClose());
      waitForAjaxExtJs();
      ExtentReport.logPass("PASS", "test01bOnOverwriteRvuDialogAssertDialogElementsAreDisplayed");
     	} catch (Exception|AssertionError e) {
     		ExtentReport.logFail("FAIL", "test01bOnOverwriteRvuDialogAssertDialogElementsAreDisplayed", driver, e);
     		fail(e.getMessage());
     	}
  }

  @Ignore
  @Test
  public void test02ClickOverwriteButtonAndAssertWarningDailogDisappears() throws Throwable {
    try {
      waitForAjaxExtJs();
      doClick(costingMap.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalWarningButtonOverwrite());
      ucqcWaitForSpinnerToEnd();
      ExtentReport.logPass("PASS", "test02ClickOverwriteButtonAndAssertWarningDailogDisappears");
 	} catch (Exception|AssertionError e) {
 		ExtentReport.logFail("FAIL", "test02ClickOverwriteButtonAndAssertWarningDailogDisappears", driver, e);
 		fail(e.getMessage());
 	}
  }

  @Ignore
  @Test
  public void test03ClickOverwriteButtonAndAssertWarningDailogDisappears() throws Throwable {
    try {
      Thread.sleep(1100);
      doClick(costingMap.getUnitCostQuickCalculationButtonOverwriteRVUMaintenance());
      ucqcWaitForSpinnerToEnd();
      waitForAjaxExtJs();
      selectCostComponent("Employee Benefits");
      doClick(costingMap.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonOverwriteRVUs());
      waitForAjaxExtJs();
      //on warning popup
      doClick(costingMap.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalWarningButtonCancel());
      doClick(costingMap.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonOverwriteRVUs());
      doClick(costingMap.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalWarningButtonClose());  //click x to close
      doClick(costingMap.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonOverwriteRVUs());
      assertElementText(driver.findElement(By.xpath("//div[contains(@class,'errorMsg')]")),expectedWarningMessage,printout);
      doClick(costingMap.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalWarningButtonOverwrite());
      ucqcWaitForSpinnerToEnd();
      Thread.sleep(1100);
      ExtentReport.logPass("PASS", "test03ClickOverwriteButtonAndAssertWarningDailogDisappears");
 	} catch (Exception|AssertionError e) {
 		ExtentReport.logFail("FAIL", "test03ClickOverwriteButtonAndAssertWarningDailogDisappears", driver, e);
 		fail(e.getMessage());
 	}
  }
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}