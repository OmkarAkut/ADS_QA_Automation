package webdriver.scripts.costing;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ValidateEditCostMethodMasters extends GoHelper{
	static CostingMap costing;
	static ContractingMap modelMap;
	static String costModel="Actual Cost Model";
	/** Automates test ticket ADS-6643,ADS-6669*/
	@BeforeClass
	public static void setupScript() throws Exception,Throwable {
		ExtentReport.reportCreate("ValidateEditCostMethodMasters", "webdriver.scripts.costing", "ValidateEditCostMethodMasters");
		try {
			costing = BuildMap.getInstance(driver, CostingMap.class);
			modelMap=BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Costing Data Maintenance");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01ExpandToCostMethodMasters() throws Throwable {
		try {
			doClickTreeData("Costing");
			driverDelay(200);
			doClickTreeData("Cost Model Information");
			driverDelay(200);
			doClickTreeSubItem("Cost Method Masters");
			driverDelay(200);
			ExtentReport.logPass("PASS", "test01ExpandToCostMethodMasters");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ExpandToCostMethodMasters", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test02EditCostMethodModel() throws Throwable {
		try {
			doClick(costing.getEditButton());
			driverDelay(200);
			assertTextIsDisplayed("Cost Method Master");
			doClick(ContractingMap.getContractModelRiskLimiterCancelCloseBtn());
			ExtentReport.logPass("PASS", "test02EditCostMethodModel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02EditCostMethodModel", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		doClosePageOnLowerBar("Maintain Data");
		ExtentReport.report.flush();

	}
}
