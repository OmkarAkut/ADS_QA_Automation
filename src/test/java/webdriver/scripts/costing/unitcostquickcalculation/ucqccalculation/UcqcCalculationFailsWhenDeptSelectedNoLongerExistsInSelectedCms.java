package webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import webdriver.scripts.costing.unitcostquickcalculation.UnitCostQuickCalculationHelperStatic;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UcqcCalculationFailsWhenDeptSelectedNoLongerExistsInSelectedCms extends UnitCostQuickCalculationHelperStatic {

  private static CostingMap costingMap;
  static String[] requiredFields = {
    "QA Cost Model",
    "ADS-1377 By Month",
    "150 Marina Medical Center",
    "2016  CHILDREN'S DIABETES UNIT",
    "Jul 2004 to Jul 2004"
  };

  /**Test ticket ADS-.  Dev Story ADS-. */
  @BeforeClass
  public static void setupScript() throws InterruptedException {
    costingMap = BuildMap.getInstance(driver, CostingMap.class);
    System.out.println("Test Class: " + UcqcCalculationFailsWhenDeptSelectedNoLongerExistsInSelectedCms.class.getSimpleName());
    evolveLoginStaticUser(Users.CostingDepartmentManager1);
    goToPage("Unit Cost Quick Calculation");
    doMaximizeWindow();
    //ucqcDisplayChargeCodeGrid(requiredFields);
  }

  @Test
  public void test01UpdateQuickSalariesAndWagesValueAndAssertCalculateButtonIsDisabled() {
    try {
      ucqcUpdateGridCellValue("3350022","Quick Salaries and Wages RVU","15",printout);
      assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonCalculate(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test02UpdateQuickSalariesAndWagesValueAndAssertCalculationEnds() {
    try {
      String[][] pairs = {
        {"8195307", "0.000000"},
        {"8399370", "2.00000"},
      };

      for (String[] pair : pairs) {
        try {
          String header = "Salaries & Wages Quick RVUs";
          ucqcUpdateGridCellValue(pair[0], header, pair[1], printout);
        } catch (Throwable e) {
          fail(e.getMessage());
        }
      }
      doClick(costingMap.getUnitCostQuickCalculationButtonSaveQuickRVUs());
      doClick(costingMap.getUnitCostQuickCalculationButtonCalculate());
      ucqcWaitForSpinnerToEnd();
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Ignore
  @Test
  public void test03GoToCalculationStatusPageAndAssertCalculationCompletedToOneHundredPercent() {
    try {
      goToPage("Calculation Status");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }
}
