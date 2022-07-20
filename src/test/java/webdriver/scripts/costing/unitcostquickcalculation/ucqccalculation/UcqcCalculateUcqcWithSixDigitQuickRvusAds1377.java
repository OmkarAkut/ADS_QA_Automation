package webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import org.junit.*;
import org.junit.runners.MethodSorters;
import webdriver.scripts.costing.unitcostquickcalculation.UnitCostQuickCalculationHelperStatic;
import webdriver.maps.CostingMap;
import webdriver.maps.StatusMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UcqcCalculateUcqcWithSixDigitQuickRvusAds1377 extends UnitCostQuickCalculationHelperStatic {

  static CostingMap costingMap;
  static StatusMap statusMap;
  static String[] requiredFields = {
    "QA Cost Model",
    "ADS-1377 A By Month",
    "150 Marina Medical Center",
    "2016  CHILDREN'S DIABETES UNIT12345678901234567890123456789646596846516544351686565454",
    "Jul 2004 to Jul 2004"
  };

  /**Test ticket ADS-1377.  Dev Story ADS-873.
   * This scripts verifies successful calculations involving quick rvus with values
   * up to 9,999,999.000000.  Note that 9,999,999,999.000000 is specifically excluded.*/
  @BeforeClass
  public static void setupScript() throws InterruptedException {
    costingMap = BuildMap.getInstance(driver, CostingMap.class);
    statusMap = BuildMap.getInstance(driver, StatusMap.class);
    System.out.println("Test Class: " + UcqcCalculateUcqcWithSixDigitQuickRvusAds1377.class.getSimpleName());
    evolveLoginStaticUser(Users.CostingDepartmentManager1);
    goToPage("Unit Cost Quick Calculation");
    doMaximizeWindow();
    ucqcDisplayChargeCodeGrid(requiredFields);
  }

  @AfterClass
  public static void teardownScript() throws InterruptedException {
    doClosePageOnLowerBar("Calculation Status");
    doClosePageOnLowerBar("Unit Cost Quick...");
  }

  @Test
  public void test01UpdateQuickSalariesAndWagesValueAndAssertCalculateButtonIsDisabled() {
    //the charge code below isn't on the page.  Need to use a different one.
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
        {"8301392", "999.000000"},
        {"8301707", "9,999.00000"},

        {"83036", "99,999.00000"},
        {"8391021", "999,999.00000"},
        {"8392573", "9,999,999.00000"},
        {"8394389", "99,999,999.000000"},

        {"8396012", "999,999,999.000000"},
        {"8396020", "1,234,567,890.123456"},
        {"8396061", "1,000,000,000.000000"},
        {"8396095", "-999,999,999.000000"},

        {"8396111", "-99,999,999.000000"},
        {"8396509", "-999,999.000000"},
        {"8398497", "-9,999.000000"},
        {"8399362", "-99.000000"},
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

  @Test
  public void test03GoToCalculationStatusPageAndAssertCalculationCompletedToOneHundredPercent() {
    try {
      goToPage("Calculation Status");
      waitForSpinnerToEnd();
      String statusPercentage = statusMap.getCalculationStatusPageMyStatusFirstRowStatusBarPercentage().getText();
      assertThat(statusPercentage, equalTo("100"));
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }
}
