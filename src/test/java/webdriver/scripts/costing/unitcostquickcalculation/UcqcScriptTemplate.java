package webdriver.scripts.costing.unitcostquickcalculation;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UcqcScriptTemplate extends UnitCostQuickCalculationHelperStatic {

  private static CostingMap costingMap;
  static final String[] requiredFields = {
    "QA Cost Model",
    /*modified by Omkar on 1st July 2022 as the below value is not available in the list
    "ADS-1377 By Month",
    */
    "ADS-1377 A By Month",
    "150 Marina Medical Center",
//    "2016 CHILDREN'S  DIABETES UNIT",
    //Shilpa 15.09.2022 updated data from 2016 CHILDREN'S  DIABETES UNIT
    "2016",
    "Jul 2004 to Jul 2004"
  };

  /**Test ticket ADS-.  Dev Story ADS-. 
 * @throws Throwable */
  @BeforeClass
  public static void setupScript() throws Throwable {
	  ExtentReport.reportCreate("UcqcScriptTemplate","webdriver.scripts.costing.unitcostquickcalculation", "UcqcScriptTemplate");
	  
    try {
		costingMap = BuildMap.getInstance(driver, CostingMap.class);
		System.out.println("Test Class: " + UcqcScriptTemplate.class.getSimpleName());
		evolveLoginStaticUser(Users.CostingDepartmentManager1);
		goToPage("Unit Cost Quick Calculation");
		doMaximizeWindow();
		ucqcDisplayChargeCodeGrid(requiredFields);
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "setupScript", driver, e);
		fail(e.getMessage());
		
	}
  }

  @Test
  public void test01UpdateQuickSalariesAndWagesValueAndAssertCalculateButtonIsDisabled() throws Throwable {
    try {
    	//Shilpa 15.09.2022, 3350022 not available
     // ucqcUpdateGridCellValue("3350022","Quick Salaries and Wages RVU","15",printout);
    	ucqcUpdateGridCellValue("8195307","Quick Salaries and Wages RVU","15",printout);
      assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonCalculate(),printout);
      ExtentReport.logPass("PASS", "test01UpdateQuickSalariesAndWagesValueAndAssertCalculateButtonIsDisabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test01UpdateQuickSalariesAndWagesValueAndAssertCalculateButtonIsDisabled", driver, e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test02UpdateQuickSalariesAndWagesValueAndAssertCalculationEnds() throws Throwable {
    try {
      String[][] pairs = {
        {"8195307", "2.000000"},
        //Shilpa 15.09.2022 updated charge code
//        {"8399370", "2.00000"},
        {"8301392", "2.000000"},
      };

      for (String[] pair : pairs) {
        try {
        	//Shilpa 15.09.2022 updated header name
         // String header = "Salaries & Wages Quick RVUs";
        	String header = "Quick Salaries and Wages RVU";
          ucqcUpdateGridCellValue(pair[0], header, pair[1], printout);
        } catch (Throwable e) {
          fail(e.getMessage());
        }
      }
      doClick(costingMap.getUnitCostQuickCalculationButtonSaveQuickRVUs());
      doClick(costingMap.getUnitCostQuickCalculationButtonCalculate());
      ucqcWaitForSpinnerToEnd();
      ExtentReport.logPass("PASS", "test02UpdateQuickSalariesAndWagesValueAndAssertCalculationEnds");
    } catch (Exception|AssertionError e) {
    	
    	ExtentReport.logFail("FAIL", "test02UpdateQuickSalariesAndWagesValueAndAssertCalculationEnds", driver, e);
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
  
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}

  
  
  
}
