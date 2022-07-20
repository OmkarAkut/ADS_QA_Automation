package webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TotalQuickCostColumnPopulatesAfterCalculateStaticAds1228 extends UcqcHelper {

  private static CostingMap costingMap;
  String chargeCode = "1100544";
  String headerName = "Total Quick Cost";
  String columnToUpdate = "Quick Salaries and Wages RVU";
  private static String initialTotalQuickCostCellValue = null;
  private static String finalTotalQuickCostCellValue = null;
  static String[] requiredFields = {
    "Marina",
    "*CM3 TB MHFY05 Before Vol Change",
    "150 Marina Medical Center",
    "2130  PED ICU",
    "Apr 2004 to Mar 2005"
  };

  /** Zephyr test ticket ADS-1228 (Dev Story ADS-671)
  This script confirms that the Total Quick Cost column populates after Calculate.
  Last Updated: 09-13-19 */
  @BeforeClass
  public static void setupScript() throws Exception {
    costingMap = BuildMap.getInstance(driver, CostingMap.class);
    System.out.println("Test Class: " + TotalQuickCostColumnPopulatesAfterCalculateStaticAds1228.class.getSimpleName());
    Login.loginUser("CostingDepartmentManager1");
    doMaximizeWindow();
    goToPage("Unit Cost Quick Calculation");
    waitForAjaxExtJs();
    ucqcDisplayChargeCodeGrid(requiredFields);
  }

  @AfterClass
  public static void teardownScript() throws InterruptedException {
    doClosePageOnLowerBar("Unit Cost Quick...");
  }

  @Test (timeout = 300000)
  public void test01GetDefaultTotalQuickCostColumnValueAndAssertTwoDecimalPlaces() throws InterruptedException {
    initialTotalQuickCostCellValue = ucqcGetChargeCodeGridCellValue(chargeCode, headerName, printout);
    if (printout) {
      System.out.println("Initial Total Quick Cost cell value: " + initialTotalQuickCostCellValue);
    }
    assertThatValueHasRequiredDecimalPlaces(initialTotalQuickCostCellValue,2, printout);
  }

  @Test (timeout = 300000)
  public void test02UpdateCellValueAndClickCalculateWaitForSpinnerToEndAndAssertUpdatedValueIsDifferentFromInitialValue() throws InterruptedException {
    ucqcGetChargeCodeGridCellValue(chargeCode, columnToUpdate, printout);
    ucqcUpdateGridCellValue(chargeCode, columnToUpdate,
        String.valueOf(javaGetRandomNumber(100, printout)), printout);
    doClick(costingMap.getUnitCostQuickCalculationButtonSaveQuickRVUs());
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    doClick(costingMap.getUnitCostQuickCalculationButtonCalculate());
    ucqcWaitForSpinnerToEndNoHardStop();
    waitForAjaxExtJs();
    finalTotalQuickCostCellValue = ucqcGetChargeCodeGridCellValue(chargeCode, headerName, printout);
    if (printout) {
      System.out.println("Initial Total Quick Cost cell value: " + initialTotalQuickCostCellValue);
      System.out.println("Final Total Quick Cost cell value: " + finalTotalQuickCostCellValue);
    }
    assertTrue(initialTotalQuickCostCellValue != finalTotalQuickCostCellValue);
  }

  @Test (timeout = 300000)
  public void test03AssertUpdatedTotalQuickCostValueHasTwoDecimalPlaces() {
    assertThatValueHasRequiredDecimalPlaces(finalTotalQuickCostCellValue,2, printout);
  }
}

