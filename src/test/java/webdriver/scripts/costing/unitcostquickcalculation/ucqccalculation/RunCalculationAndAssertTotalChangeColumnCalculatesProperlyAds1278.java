package webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RunCalculationAndAssertTotalChangeColumnCalculatesProperlyAds1278 extends UcqcHelper {

  private static CostingMap costingMap;

  static final String[] requiredFields = {
    "QA Marina",
    "ADS-302 LG By Month",
    "150 Marina Medical Center",
    "Q302  QA Dept for ADS-302",
    "Apr 2004 to Apr 2004"
  };

  /** Test ticket: ADS-1278 (Total Change column populates as Total Quick Cost less Total Unit Cost). Dev story ADS-671.
   * This script updates a value and then runs the calculation and checks that the Total Change column calculates properly.
   * It takes several minutes to run the calculation.*/
  @BeforeClass
  public static void setupScript() throws Exception {
    costingMap = BuildMap.getInstance(driver, CostingMap.class);
    System.out.println("Test Class: " + RunCalculationAndAssertTotalChangeColumnCalculatesProperlyAds1278.class.getSimpleName());
    Login.loginUser("CostingDepartmentManager1");
    goToPage("Unit Cost Quick Calculation");
    doMaximizeWindow();
    waitForAjaxExtJs();
    ucqcDisplayChargeCodeGrid(requiredFields);
  }

  @Test (timeout = 240000)
  public void test01RunCalculationAndAssertTotalChangeCalculatesProperly() throws InterruptedException {
    String chargeCode = "1120021";
    ucqcGetChargeCodeGridCellValue(chargeCode, "Total Change", printout);
    //Modify a value(s) listed under the Quick Salaries and Wages RVU column
    String randomValue = String.valueOf(javaGetRandomNumber(30, printout));
    ucqcUpdateGridCellValue(chargeCode, "Quick Salaries and Wages RVU", randomValue, printout);
    doClick(costingMap.getUnitCostQuickCalculationButtonSaveQuickRVUs());
    waitForSpinnerToEnd();
    doClick(costingMap.getUnitCostQuickCalculationButtonCalculate());
    ucqcWaitForSpinnerToEndNoHardStop();
    waitForAjaxExtJs();
    assertChangeColumnCalculation(chargeCode,
        "Total Change", "Total Unit Cost", "Total Quick Cost", printout);
  }

}
