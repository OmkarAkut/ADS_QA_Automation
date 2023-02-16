package webdriver.scripts.costing.unitcostquickcalculation.ucqcrvus;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

public class OverWriteRVUValues5919 extends UcqcHelper {
	 static CostingMap overwriteRvuMaint;
	 static String[] filter={"Name","Is","Equal To","QA Cost Model"};
	@BeforeClass
	  public static void setupScript() throws Exception,Throwable {
		  ExtentReport.reportCreate("OverwriteRvuMaintenancePopupAds1181", "webdriver.scripts.costing.unitcostquickcalculation.ucqcrvus", "OverwriteRvuMaintenancePopupAds1181");
	    try {
			overwriteRvuMaint = BuildMap.getInstance(driver, CostingMap.class);
			Login.loginUser("CostingDepartmentManager1");
			goToPage("RVU Maintenance");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	  }
	
	@Test
	public void test01() throws Throwable {
		try {
			doClick(overwriteRvuMaint.getRvuMaintenanceButtonFilter());
			doFilterCreate(filter);
			
			ExtentReport.logPass("PASS", "test01OpenCostCalculationScenario");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01OpenCostCalculationScenario", driver, e);
			fail(e.getMessage());
		}
	}
}
