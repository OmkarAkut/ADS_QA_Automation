package webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation;

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
public class UcqcCalculateButtonDisabledIfAttemptToCalculateWithUnsavedQuickRvusAds1233 extends UcqcHelper {

  private static CostingMap costingMap;
  static final String[] requiredFields = {
    "Marina",
//    "*CM1 TB MHFY05 After Vol Change_UCQC", ADS-21467
    "*CM2 TB MHFY05 No Price List - 2_UCQC",
    "150 Marina Medical Center",
   // "3145  ENDOSCOPY",
    "3145",  //venkat updated department field data 21.09.2022
//    "Jan 2005 to Jan 2005" Shilpa updated data 24.5.2024
    "Apr 2004 to Mar 2005"
  };

  /** Test ticket ADS-1233 (dev story ADS-742)
  This script verifies Calculate button is disabled if unsaved Quick Rvu changes exist.
 * @throws Throwable */
  @BeforeClass
  public static void setupScript() throws Throwable {
	  ExtentReport.reportCreate("UcqcCalculateButtonDisabledIfAttemptToCalculateWithUnsavedQuickRvusAds1233","webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation", "UcqcCalculateButtonDisabledIfAttemptToCalculateWithUnsavedQuickRvusAds1233");
		
    try {
		costingMap = BuildMap.getInstance(driver, CostingMap.class);
		System.out.println("Test Class: " + UcqcCalculateButtonEnableAndDisableAds1152.class.getSimpleName());
		Login.loginUser("CostingDepartmentManager1");
		goToPage("Unit Cost Quick Calculation");
		doMaximizeWindow();
		ucqcDisplayChargeCodeGrid(requiredFields);
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","setupScript", driver,e);
    	fail(e.getMessage());
	} 
    
  }

  @Test
  public void test01aAssertCalculateButtonIsDisabledByDefault() throws Throwable {
    try {
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonCalculate(),printout);
      ExtentReport.logPass("PASS", "test01aAssertCalculateButtonIsDisabledByDefault");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test01aAssertCalculateButtonIsDisabledByDefault", driver,e);
         fail(e.getMessage());
    }
  }

  @Test
  public void test01bUpdateQuickSalariesAndWagesValueAndAssertCalculateButtonIsDisabled() throws Throwable {
    try {
      ucqcUpdateGridCellValue("3350022","Quick Salaries and Wages RVU","15",printout);
      assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonCalculate(),printout);
      ExtentReport.logPass("PASS", "test01bUpdateQuickSalariesAndWagesValueAndAssertCalculateButtonIsDisabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test01bUpdateQuickSalariesAndWagesValueAndAssertCalculateButtonIsDisabled", driver,e);
      fail(e.getMessage());
    }
  }
  
  
  @AfterClass
 	public static void endtest() throws Exception {

 		ExtentReport.report.flush();

 	}
  
  
  
  
  
  
  
}