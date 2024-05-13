package webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import ExtentReport.ExtentReport;
import webdriver.scripts.costing.unitcostquickcalculation.UnitCostQuickCalculationHelperStatic;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.scripts.costing.unitcostquickcalculation.UcqcData;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UcqcIncludeInGridOnlyChargeCodesWithResultsInSelectedCmsAds1115
        extends UnitCostQuickCalculationHelperStatic {

  private static List<String> chargeCodesStrings;
  private static CostingMap ucqcMap;
  String[] requiredFields = {
    "Marina", "*CM1 TB MHFY05 After Vol Change", "150 Marina Medical Center",
   // "2130  PED ICU", 
    "2130", //venkat update text data 15.09.2022
    "Jan 2005 to Jan 2005"
  };
  private static String sqlQuery =
      "SELECT ac.actdscrcode"
      + " FROM ActivityCostItem2 ac, CostModelScenario2 cm, EntDeptCostContainer2 ed, CostItemDataSet2 ci"
      + " WHERE ci.ObjectID = ac.MASTERID and cm.ObjectID = ed.cstmodscenid and ci.containerID = ed.ObjectID"
      + " AND cm.name = '*CM1 TB MHFY05 After Vol Change'"
      + " AND ed.EntityCode = '150'"
      + " And ed.DeptCode = '2130'"
      + " And ci.startdate >= TO_DATE('2005 01 01 00 00 00', 'YYYY MM DD HH24 MI SS')"
      + " And ci.enddate <= TO_DATE('2005 01 31 23 59 59', 'YYYY MM DD HH24 MI SS')"
  ;

  /** Zephyr test ticket ADS-1115.  Last Updated 4-8-20.
  * Verifies that only charges codes that are "complete" (as determined by the
  * database query) for the given cost model parameters are displayed on the
  * ucqc results table (grid).
 * @throws Throwable 
  */
  @BeforeClass
  public static void setupScript() throws Throwable {
	  
	  ExtentReport.reportCreate("UcqcIncludeInGridOnlyChargeCodesWithResultsInSelectedCmsAds1115","webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage", "UcqcIncludeInGridOnlyChargeCodesWithResultsInSelectedCmsAds1115");
		
    try {
		ucqcMap = BuildMap.getInstance(driver, CostingMap.class);
		System.out.println( "Test Class: " + UcqcIncludeInGridOnlyChargeCodesWithResultsInSelectedCmsAds1115.class.getSimpleName());
		evolveLoginStaticUser(Users.CostingDepartmentManager1);
		goToPage("Unit Cost Quick Calculation");
		 ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","setupScript", driver,e);
	      fail(e.getMessage());
	}
    
  }

  @Test
  public void test01AssertUcqcGridDisplaysExpectedNumberOfChargeCodes() throws Throwable {
    try {
      waitForAjaxExtJs();
      ucqcDisplayChargeCodeGrid(requiredFields);
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
//Shilpa : commented below lines 16.04.2024
//      List<WebElement> chargeCodes = javaMakeListOfWebElements2(driver.findElement(By.xpath("//div[2]/div[2]/div[2]/div/div/div[1]/div[2]"+"/div[contains(@class, 'x-grid-view')]/table/tbody")),"//tr[contains(@class,'x-grid-row')]/td[2][contains(@class,'x-grid-cell-gridcolumn')]"+ "/div[contains(@class,'x-grid-cell-inner')]");
      
    
     
//      chargeCodesStrings = javaListConvertListOfWebElementsToStrings(chargeCodes, printout);
      chargeCodesStrings=javaMakeListOfStrings(driver.findElements(By.xpath("(//div[@id='ucqcgrid-body']//div[@class='x-grid-item-container']/table//tr//td[contains(@class,'x-grid-cell-gridcolumn')][1]/div)")));
      System.out.println("chargeCodesStrings:"+chargeCodesStrings);
      assertThat(chargeCodesStrings.size(), equalTo(UcqcData.ucqcChargeCodesExpectedList.size()));
      ExtentReport.logPass("PASS", "test01AssertUcqcGridDisplaysExpectedNumberOfChargeCodes");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test01AssertUcqcGridDisplaysExpectedNumberOfChargeCodes", driver,e);
      fail(e.getMessage());
    }
    
  }

  @Test
  public void test02AssertUcqcGridDisplaysExpectedChargeCodes() throws Throwable {
	  
    try {
		assertEquals(UcqcData.ucqcChargeCodesExpectedList, chargeCodesStrings);
		ExtentReport.logPass("PASS", "test02AssertUcqcGridDisplaysExpectedChargeCodes");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","test02AssertUcqcGridDisplaysExpectedChargeCodes", driver,e);
		fail(e.getMessage());
	}
  }

//  @Test tc- related to query db
  public void test03QueryDatabaseAndAssertResultsMatchDisplayedChargeCodes()throws Throwable {
	  
    try {
		getStringDataFromDatabaseAndAssertExpectedValues(sqlQuery,1,chargeCodesStrings);
		ExtentReport.logPass("PASS", "test03QueryDatabaseAndAssertResultsMatchDisplayedChargeCodes");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","test03QueryDatabaseAndAssertResultsMatchDisplayedChargeCodes", driver,e);
		fail(e.getMessage());
	}
    
  }
  
  
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}

}
