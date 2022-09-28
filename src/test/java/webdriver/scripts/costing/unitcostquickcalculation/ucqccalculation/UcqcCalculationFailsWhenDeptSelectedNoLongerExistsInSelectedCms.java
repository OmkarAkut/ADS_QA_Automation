package webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import webdriver.scripts.costing.unitcostquickcalculation.UnitCostQuickCalculationHelperStatic;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UcqcCalculationFailsWhenDeptSelectedNoLongerExistsInSelectedCms extends UnitCostQuickCalculationHelperStatic {

  private static CostingMap costingMap;
  static String[] requiredFields = {
    "QA Cost Model",
    //"ADS-1377 By Month",
     "ADS-1377 A By Month",//venkat update text data 15.09.2022
    "150 Marina Medical Center",
   //"2016  CHILDREN'S DIABETES UNIT",
      "2016", //venkat update text data 15.09.2022
    "Jul 2004 to Jul 2004"
  };

  /**Test ticket ADS-.  Dev Story ADS-. 
 * @throws Throwable */
  @BeforeClass
  public static void setupScript() throws Throwable {
	  ExtentReport.reportCreate("UcqcCalculationFailsWhenDeptSelectedNoLongerExistsInSelectedCms","webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation", "UcqcCalculationFailsWhenDeptSelectedNoLongerExistsInSelectedCms");
    try {
		costingMap = BuildMap.getInstance(driver, CostingMap.class);
		System.out.println("Test Class: " + UcqcCalculationFailsWhenDeptSelectedNoLongerExistsInSelectedCms.class.getSimpleName());
		evolveLoginStaticUser(Users.CostingDepartmentManager1);
		goToPage("Unit Cost Quick Calculation");
		doMaximizeWindow();
		//ucqcDisplayChargeCodeGrid(requiredFields);
		ucqcDisplayChargeCodeGrid(requiredFields);//venkat uncomment this line 15.09.2022
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","setupScript", driver,e);
    	fail(e.getMessage());
		
	}
    
  }

  @Test
  public void test01UpdateQuickSalariesAndWagesValueAndAssertCalculateButtonIsDisabled() throws Throwable {
    try {
    //  ucqcUpdateGridCellValue("3350022","Quick Salaries and Wages RVU","15",printout);
      ucqcUpdateGridCellValue("8195307","Quick Salaries and Wages RVU","15",printout);//venkat update value 15.09.2022
      
      assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonCalculate(),printout);
      ExtentReport.logPass("PASS", "test01UpdateQuickSalariesAndWagesValueAndAssertCalculateButtonIsDisabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test01UpdateQuickSalariesAndWagesValueAndAssertCalculateButtonIsDisabled", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test02UpdateQuickSalariesAndWagesValueAndAssertCalculationEnds() throws Throwable {
    try {
		try {
		  String[][] pairs = {
		    {"8195307", "0.000000"},
		   // {"8399370", "2.00000"},
		    {"8195307", "2.00000"}, //venkat update text data 15.09.2022
		  };

		  for (String[] pair : pairs) {
		    try {
		   //   String header = "Salaries & Wages Quick RVUs";
		        String header = "Quick Salaries and Wages RVU";//venkat update text 15.09.2022
		      
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
		ExtentReport.logPass("PASS", "test02UpdateQuickSalariesAndWagesValueAndAssertCalculationEnds");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","test02UpdateQuickSalariesAndWagesValueAndAssertCalculationEnds", driver,e);
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
