package webdriver.scripts.systemmaintenance;

import static org.junit.Assert.assertTrue;
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
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.SystemMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/**Regression Test ADS-5799 **/
public class ValidateOverheadTaskListscreensAreAvailableforCost extends GoHelper {
	static CostingMap costing;
	static ContractingMap contractMap;
	static ModelLibraryMap modelMap;
	private static SystemMaintenanceMap systemMap;
	static String costModel = "0-MarinaCostModel";

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ValidateOverheadTaskListscreensAreAvailableforCost", "webdriver.scripts.systemmaintenance",
				"ValidateOverheadTaskListscreensAreAvailableforCost");
		try {
			costing = BuildMap.getInstance(driver, CostingMap.class);
			contractMap = BuildMap.getInstance(driver, ContractingMap.class);
			modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
			systemMap = BuildMap.getInstance(driver, SystemMaintenanceMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Customize Task Lists");
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-5799
	@Test
	public void test01VerifyCostingScreensUnderCustomizeTaskList_5799() throws Throwable {
		try {
			doClick(systemMap.getCustomTaskListClearFilterButton());
			waitForAjaxExtJs();
//			Omkar 24/4/2023 : xpath changes for 11.2
//			assertElementIsDisplayedWithXpath(
//					"//div[contains(@class,'x-tab-default-active')]//child::span[text()='Cost']");
			assertElementIsDisplayedWithXpath(
					"//span[contains(@class,'x-tab-wrap x-tab-wrap-default')]//child::span[text()='Cost']");
//			OMkar 25/04/2023 : Unable to get all the contents in the table because of scrollbar.getting only 46 count
			if (systemMap.getSystemMaintenanceCostScreenList().size() == 159) {
				assertTrue("Cost screens are 159", printout);

			} else {
				fail();
			}
			ExtentReport.logPass("PASS", "test01VerifyCostingScreensUnderCustomizeTaskList");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01VerifyCostingScreensUnderCustomizeTaskList", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test02CostingScreensIncludeOverheadTaksList() throws Throwable {
		try {
			assertListOfElementsContainsExpectedString(systemMap.getSystemMaintenanceCostScreenList(),
					"Allocation Exceptions");
			assertListOfElementsContainsExpectedString(systemMap.getSystemMaintenanceCostScreenList(),
					"Allocation Statistic Assignments");
			assertListOfElementsContainsExpectedString(systemMap.getSystemMaintenanceCostScreenList(),
					"General Information - Overhead");
			assertListOfElementsContainsExpectedString(systemMap.getSystemMaintenanceCostScreenList(),
					"Overhead Account Variability Masters");
			ExtentReport.logPass("PASS", "test02CostingScreensIncludeOverheadTaksList");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02CostingScreensIncludeOverheadTaksList", driver, e);
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();

	}
}
