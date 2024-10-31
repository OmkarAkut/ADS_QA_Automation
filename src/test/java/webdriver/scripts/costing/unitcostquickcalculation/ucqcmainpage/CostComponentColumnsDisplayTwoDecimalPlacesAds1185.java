package webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ExtentReport.ExtentReport;
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
  This script confirms that there are two decimal places for each value in the cost component columns in the UCQC table.
 * @throws Throwable */
  @BeforeClass
  public static void setupScript() throws Throwable {
	  
	  ExtentReport.reportCreate("CostComponentColumnsDisplayTwoDecimalPlacesAds1185","webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage", "CostComponentColumnsDisplayTwoDecimalPlacesAds1185");
		
    try {
		CostComponentValue = BuildMap.getInstance(driver, CostingMap.class);
		System.out.println("Test Class: " + CostComponentColumnsDisplayTwoDecimalPlacesAds1185.class.getSimpleName());
		Login.loginUser("CostingDepartmentManager1");
		goToPage("Unit Cost Quick Calculation");
		doMaximizeWindow();
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","setupScript", driver,e);
		fail(e.getMessage());
		
	} 
  }

  @Test
  public void testCostComponentColumnsDisplayTwoDecimalPlaces() throws Throwable {
    try {
//      setUcqcCriteria("Marina","*DM ADS-673 A","150 Marina Medical Center",
//    		 // "2016  CHILDREN",
//    		  "2016",//venkat added text data 15.09.2022
//    			// "2016  CHILDREN'S DIABETES UNIT12345678901234567890123456789646596846516544351686565454",
//    		  "Apr 2004 to Mar 2005");
    	//Shilpa updated test data for 11.2 0n 10.31.2024
    	 setUcqcCriteria("Marina","*DM ADS-673 B","150 Marina Medical Center",
        		 // "2016  CHILDREN",
        		  "2016",//venkat added text data 15.09.2022
        			// "2016  CHILDREN'S DIABETES UNIT12345678901234567890123456789646596846516544351686565454",
        		  "Apr 2004 to Mar 2005");
      doClick(CostComponentValue.getUnitCostQuickCalculationButtonApplySelections());
      ucqcWaitForSpinnerToEnd();
      for (String decimalValues: costComponents) {
        getCellValueAndAssertDecimalPlace("8195307",decimalValues);
      }
      ExtentReport.logPass("PASS", "testCostComponentColumnsDisplayTwoDecimalPlaces");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","testCostComponentColumnsDisplayTwoDecimalPlaces", driver,e);
		fail(e.getMessage());
    	
      fail("<< TEST FAILED >>");
    }
  }
  
  @AfterClass
 	public static void endtest() throws Exception {

 		ExtentReport.report.flush();

 	}
  
  
  
  
  
  
}

