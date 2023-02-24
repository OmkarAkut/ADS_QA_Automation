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
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression Test ADS-5992 **/
public class ValidateHelpLinkHideTabCosting extends GoHelper {
	String costModel = "QA Marina";
	static CostingMap costing;
	@BeforeClass
	public static void setupScript() throws Exception,Throwable {
		ExtentReport.reportCreate("ValidateHelpLinkHideTabCosting", "webdriver.scripts.costing", "ValidateHelpLinkHideTabCosting");
		try {
			costing = BuildMap.getInstance(driver, CostingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			waitForDisplayedSpinnerToEnd();
			goToPage("Costing Models");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01OpenRVUMaintenanceFromCostingModel() throws Throwable {
		try {
			ContractModelsHelper.doClickTreeData("Costing");
			driverDelay(1000);
			ContractModelsHelper.scrollToView("//div[text()='Marina Health']");
			ContractModelsHelper.doClickTreeData("Tinesha");
			driverDelay(3000);
			ContractModelsHelper.doClickTreeData("Marina Health");
			doSearchForModel(costModel);
			tableClickCellFirstColumn(costModel);
			doClickButton("Open Task List");
			waitForPageTitle(costModel);
			ContractModelsHelper.doClickTreeData("CM Test");
			waitForMainPageTitle("Miscellaneous");
			ContractModelsHelper.doClickTreeData("Miscellaneous");
			waitForMainPageTitle("RVU Maintenance");
			doClick("//div[text()='RVU Maintenance']//input[@title='Changes screen']");
			ExtentReport.logPass("PASS", "test01OpenRVUMaintenanceFromCostingModel");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test01OpenRVUMaintenanceFromCostingModel", driver, e);

			fail(e.getMessage());
		}
	}
	@Test
	public void test02VerifyHideHelpOptions() throws Throwable {
		try {
			assertElementIsDisplayedWithXpath("//*[contains(@onclick,'csrvumfd.htm') and @class='listhelpLnk']");		
			assertElementIsDisplayedWithXpath("//div[contains(@class,'hidetoppx expand-icon')]//span[text()='Hide']");
			ExtentReport.logPass("PASS", "test02VerifyHideHelpOptions");

		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test02VerifyHideHelpOptions", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03ValidateHideAndShowFunctions() throws Throwable {
		try {
			doClick(costing.getRvuHideButton());
			assertElementIsDisplayed(costing.getRvuShowButton());
			doClick(costing.getRvuShowButton());
			assertElementIsDisplayed(costing.getRvuFilterPanel());
			ExtentReport.logPass("PASS", "test03ValidateHideAndShowFunctions");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateHideAndShowFunctions", driver, e);

			fail(e.getMessage());
		}
	}
	 @AfterClass
		public static void endtest() throws Exception {
			ExtentReport.report.flush();
		}
}

