package Regression;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.GeneralElementsMap;
import webdriver.maps.mapbuilder.BuildMap;

public class TestGenericUIValidateCostingModelSearchTextbox extends GoHelper{
	 static GeneralElementsMap generalMap;
	  static CostingMap costing;
	  static String CostModelName="Test";
	  
	  /** Regression: Automated test script for ADS-6515 */
	 @BeforeClass
	  public static void setupScript() throws Exception,Throwable {
		  ExtentReport.reportCreate("TestGenericUIValidateCostingModelSearchTextbox", "webdriver.scripts.costing.costingmodels", "TestGenericUIValidateCostingModelSearchTextbox");

	    try {
			generalMap = BuildMap.getInstance(driver, GeneralElementsMap.class);
			costing = BuildMap.getInstance(driver, CostingMap.class);
			System.out.println("Test Class: " + TestGenericUIValidateCostingModelSearchTextbox.class.getSimpleName());
	  		Login.loginUser("CostAnalyst1");
			goToPage("costing models");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	  }
	 //ADS-6515
	 @Test
	 public void test01SearchCostModel() throws Throwable {
		 try {
			 waitForAjaxExtJs();
			assertPageInformation("Costing Model Library");
			doSearchForModel(CostModelName);
			driverDelay(500);
			for(WebElement costingElement:CostingMap.getCostingModelElementList()) {
				assertThatString(costingElement, CostModelName, printout);
			}
			ExtentReport.logPass("PASS", "test01SearchCostModel");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test01SearchCostModel", driver, e);
			fail(e.getMessage());
		}
	 }
	 @AfterClass
		public static void endtest() throws Exception {

			ExtentReport.report.flush();

		}
}
