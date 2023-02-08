package webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage;

import static org.junit.Assert.assertNotNull;
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
public class UcqcGridIsPopulatedAfterClickingApplySelectionsButtonAds1175 extends UcqcHelper {

  private static CostingMap ucqcMap;

  /** Zephyr test ticket ADS-1175,1554, 1174 (Dev Story ADS-1078).  Last Updated 08-14-19 
 * @throws Throwable */
  //Add checks for the columns Charge Code, Charge Code Name, and Modifier columns and
  //this test case can cover ADS-1219 as well - if scrolling is checked to start after
  //these three, then ADS-1204 covered.
  /**Regression test case : ADS-6644 ,ADS-5927**/
  @BeforeClass
  public static void setupScript() throws Throwable {
	  
	  ExtentReport.reportCreate("UcqcGridIsPopulatedAfterClickingApplySelectionsButtonAds1175","webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage", "UcqcGridIsPopulatedAfterClickingApplySelectionsButtonAds1175");
		
    try {
		ucqcMap = BuildMap.getInstance(driver, CostingMap.class);
		System.out.println("Test Class: " + UcqcGridIsPopulatedAfterClickingApplySelectionsButtonAds1175.class.getSimpleName());
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
  public void testUcqcGridDisplaysAfterClickingApplySelectionsButton() throws Throwable{
    try {
      waitForAjaxExtJs();
//      ucqcPopulateRequiredFieldsToDisplayGrid("QA Cost Model", "QA MHFY05 After Vol Change", "150 Marina Medical Center", "2110  ICU", "Apr 2004 to Mar 2005");
      ucqcPopulateRequiredFieldsToDisplayGrid("QA Cost Model", "QA MHFY05 After Vol Change", "150 Marina Medical Center", "2110", "Apr 2004 to Mar 2005");
      applySelections("1100023");
      ucqcPopulateRequiredFieldsToDisplayGrid("0-MarinaCostModel", "Dept 2016 only (by month)", "150 Marina Medical Center", "2016", "Apr 2004 to Apr 2004");
      applySelections("0ALL");
      ucqcPopulateRequiredFieldsToDisplayGrid("QA Cost Model", "QA ADS-1341 By Month/ ADS-660 CDEF Jun 4", "150 Marina Medical Center", "3311", "Dec 2004 to Dec 2004");
      applySelections("4099909");
      ucqcPopulateRequiredFieldsToDisplayGrid("0-MarinaCostModel", "entity 150 dept 2016 2110 3520", "150 Marina Medical Center", "2016", "Apr 2004 to Jun 2004");
      applySelections("0ALL");
      ExtentReport.logPass("PASS", "testUcqcGridDisplaysAfterClickingApplySelectionsButton");
    } catch (Exception|AssertionError e){
    	ExtentReport.logFail("FAIL","testUcqcGridDisplaysAfterClickingApplySelectionsButton", driver,e);
      fail(e.getMessage());
    }
  }
  
  public void applySelections(String chargeCode) throws Exception {
	  assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      doClick(ucqcMap.getUnitCostQuickCalculationButtonApplySelections());
      waitForAjaxExtJs();
      assertNotNull(getCellValue(chargeCode,"Quick Salaries and Wages RVU"));

  }
  
  @AfterClass
 	public static void endtest() throws Exception {

 		ExtentReport.report.flush();

 	}
}
