package webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation;

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
public class UcqcCalculateButtonDisabledIfAttemptToCalculateWithUnsavedQuickRvusAds1233 extends UcqcHelper {

  private static CostingMap costingMap;
  static final String[] requiredFields = {
    "Marina",
    "*CM1 TB MHFY05 After Vol Change_UCQC",
    "150 Marina Medical Center",
    "3145  ENDOSCOPY",
    "Jan 2005 to Jan 2005"
  };

  /** Test ticket ADS-1233 (dev story ADS-742)
  This script verifies Calculate button is disabled if unsaved Quick Rvu changes exist.*/
  @BeforeClass
  public static void setupScript() throws Exception {
    costingMap = BuildMap.getInstance(driver, CostingMap.class);
    System.out.println("Test Class: " + UcqcCalculateButtonEnableAndDisableAds1152.class.getSimpleName());
    Login.loginUser("CostingDepartmentManager1");
    goToPage("Unit Cost Quick Calculation");
    doMaximizeWindow();
    ucqcDisplayChargeCodeGrid(requiredFields);
  }

  @Test
  public void test01aAssertCalculateButtonIsDisabledByDefault() {
    try {
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonCalculate(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test01bUpdateQuickSalariesAndWagesValueAndAssertCalculateButtonIsDisabled() {
    try {
      ucqcUpdateGridCellValue("3350022","Quick Salaries and Wages RVU","15",printout);
      assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonCalculate(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }
}