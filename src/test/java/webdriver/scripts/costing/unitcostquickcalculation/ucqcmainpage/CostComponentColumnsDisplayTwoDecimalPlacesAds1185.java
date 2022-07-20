package webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

public class CostComponentColumnsDisplayTwoDecimalPlacesAds1185 extends UcqcHelper {

  private static CostingMap CostComponentValue;

  String[] costComponents = {
    "Salaries and Wages Cost",
    "Employee Benefits Cost",
    "Medical Supplies Cost",
    "Non-Medical Supplies Cost",
    "Depreciation Cost",
    "Direct Depreciation Cost",
    "Purchased Services Cost",
    "Professional Fees Cost",
    "Other Expenses Cost",
    "Direct Overhead Cost",
    "Hospital Overhead Cost",
    "Corporate Overhead Cost",
    "Depreciation Cost",
    "Tech Cost"
  };

  /** ADS-1185 (Cost values displayed in CC Cost columns should only display 2 decimals) (dev story ADS-923)
  This script confirms that there are two decimal places for each value in the cost component columns in the UCQC table.*/
  @BeforeClass
  public static void setupScript() throws Exception {
    CostComponentValue = BuildMap.getInstance(driver, CostingMap.class);
    System.out.println("Test Class: " + CostComponentColumnsDisplayTwoDecimalPlacesAds1185.class.getSimpleName());
    Login.loginUser("CostingDepartmentManager1");
    goToPage("Unit Cost Quick Calculation");
    doMaximizeWindow();
  }

  @Test
  public void testCostComponentColumnsDisplayTwoDecimalPlaces() {
    try {
      setUcqcCriteria("Marina","*DM ADS-673 A","150 Marina Medical Center","2016  CHILDREN","Apr 2004 to Mar 2005");
      doClick(CostComponentValue.getUnitCostQuickCalculationButtonApplySelections());
      ucqcWaitForSpinnerToEnd();
      for (String decimalValues: costComponents) {
        getCellValueAndAssertDecimalPlace("8195307",decimalValues);
      }
    } catch (Throwable e) {
      fail("<< TEST FAILED >>");
    }
  }
}

