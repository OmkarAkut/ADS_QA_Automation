package webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ValidateTotalChangeValueCalculationStaticAds1278 extends UcqcHelper {

  private static CostingMap costingMap;
  int numberOfGridLinesToTestLimit = 11;

  static final String[] requiredFields = {
    "QA Marina",
    "ADS-302 LG By Month",
    "150 Marina Medical Center",
   // "Q302  QA Dept for ADS-302",
    "Q302", //Venkat updated Department filed 21.09.2022
    "Apr 2004 to Apr 2004"
  };

  //NOTE NEEDS UPDATING: This script should be updated to run the calculation and then test the result, since the
  //current approach of pointing to existing data produces issues.

  /** Test ticket: ADS-1278 (Total Change column populates as Total Quick Cost less Total Unit Cost). Dev story ADS-671.
   * This script checks the existing Total Change column values (and calculation) without running the calculate operation.
 * @throws Throwable 
   */
  @BeforeClass
  public static void setupScript() throws Throwable {
	  
	  ExtentReport.reportCreate("ValidateTotalChangeValueCalculationStaticAds1278","webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation", "ValidateTotalChangeValueCalculationStaticAds1278");
    try {
		costingMap = BuildMap.getInstance(driver, CostingMap.class);
		System.out.println("Test Class: " + ValidateTotalChangeValueCalculationStaticAds1278.class.getSimpleName());
		Login.loginUser("CostingDepartmentManager1");
		goToPage("Unit Cost Quick Calculation");
		doMaximizeWindow();
		waitForAjaxExtJs();
		ucqcDisplayChargeCodeGrid(requiredFields);
		 ExtentReport.logPass("PASS", "setupScript");
		
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "setupScript", driver, e);
        fail(e.getMessage());
    	
		
	} 
  }

  @Test
  public void test01ValidateTotalChangeValueCalculationWithDefaultValues() throws Throwable {
	  
    try {
		for (int i = 1; i < numberOfGridLinesToTestLimit; i++) {
		  assertChangeColumnCalculation(i,
		      "Total Change", "Total Unit Cost", "Total Quick Cost", printout);
		  assertThatValueHasRequiredDecimalPlaces(ucqcGetChargeCodeGridCellValueForGivenRowNumber(i,
		      "Total Change", printout),2, printout);
		}
		 ExtentReport.logPass("PASS", "test01ValidateTotalChangeValueCalculationWithDefaultValues");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test01ValidateTotalChangeValueCalculationWithDefaultValues", driver, e);
        fail(e.getMessage());
	}
  }

  @Test
  public void test02ValidateTotalChangeValueHasTwoDecimalPlaces() throws Throwable {
	  
    try {
		for (int i = 1; i < numberOfGridLinesToTestLimit; i++) {
		  assertThatValueHasRequiredDecimalPlaces(ucqcGetChargeCodeGridCellValueForGivenRowNumber(i,
		      "Total Change", printout),2, printout);
		}
		 ExtentReport.logPass("PASS", "test02ValidateTotalChangeValueHasTwoDecimalPlaces");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test02ValidateTotalChangeValueHasTwoDecimalPlaces", driver, e);
        fail(e.getMessage());
		
	}
  }

  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
