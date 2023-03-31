package webdriver.scripts.systemmaintenance;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.SystemMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-6516 **/
public class ValidateCustomizeTaskListspageSystemMaintenance extends CalculationHelper {
	static CostingMap costing;
	static ContractingMap contractMap;
	static ModelLibraryMap modelMap;
	private static SystemMaintenanceMap systemMap;

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ValidateCustomizeTaskListspageSystemMaintenance",
				"webdriver.scripts.systemmaintenance", "ValidateCustomizeTaskListspageSystemMaintenance");
		try {
			costing = BuildMap.getInstance(driver, CostingMap.class);
			contractMap = BuildMap.getInstance(driver, ContractingMap.class);
			modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
			systemMap = BuildMap.getInstance(driver, SystemMaintenanceMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Customize Task Lists");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test01assertCustomiseTaskListsTabs() throws Throwable {
		try {
			assertElementIsDisplayed(systemMap.getCustomizeTaskListsPageSubTabCost());
			assertElementIsDisplayed(systemMap.getCustomizeTaskListsPageSubTabOverhead());
			assertElementIsDisplayed(systemMap.getCustomizeTaskListsPageSubTabUnpublishedContract());
			assertElementIsDisplayed(systemMap.getCustomizeTaskListsPageSubTabPublishedContract());
			assertElementIsDisplayed(systemMap.getCustomizeTaskListsPageSubTabEpisode());
			ExtentReport.logPass("PASS", "test01assertCustomiseTaskListsTabs");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01assertCustomiseTaskListsTabs", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test02OpenOverheadTab() throws Throwable {
		try {
			doClick(systemMap.getCustomizeTaskListsPageSubTabOverhead());
			assertTextIsDisplayed("Default Overhead Model Task List");
			doClick("//span[text()='Default Overhead Model Task List']//following::div[text()='Prepare Costing Elements']");
			assertTextIsDisplayed("Entities");
			doClick("//span[text()='Default Overhead Model Task List']//following::div[text()='Prepare Costing Elements']");
			ExtentReport.logPass("PASS", "test02OpenOverheadTab");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02OpenOverheadTab", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test02OpenUnpublishedContract() throws Throwable {
		try {
			doClick(systemMap.getCustomizeTaskListsPageSubTabUnpublishedContract());
			doClick(ContractingMap.getContractModelRiskLimiterCancelCloseBtn());
			assertTextIsDisplayed("Default Unpublished Contract Model Task List");
			doClick("//span[text()='Default Unpublished Contract Model Task List']//following::div[text()='Build Structure Elements'][1]");
			assertTextIsDisplayed("Contract Types");
			doClick("//span[text()='Default Unpublished Contract Model Task List']//following::div[text()='Build Structure Elements'][1]");
			ExtentReport.logPass("PASS", "test02OpenUnpublishedContract");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02OpenUnpublishedContract", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test03OpenPublishedContract() throws Throwable {
		try {
			doClick(systemMap.getCustomizeTaskListsPageSubTabPublishedContract());
			doClick(ContractingMap.getContractModelRiskLimiterCancelCloseBtn());
			assertTextIsDisplayed("Edit Payment Terms");
			doClick("(//div[contains(@class,'nowrapRow')]//following::div[text()='Edit Payment Terms'])");
			assertElementIsDisplayedWithXpath(
					"//div[contains(@id,'treeview')]//div[text()='Fee For Service Payment Terms']");
			doClick("(//div[contains(@class,'nowrapRow')]//following::div[text()='Edit Payment Terms'])");
			ExtentReport.logPass("PASS", "test03OpenPublishedContract");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03OpenPublishedContract", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test04OpenEpisode() throws Throwable {
		try {
			doClick(systemMap.getCustomizeTaskListsPageSubTabEpisode());
			doClick(ContractingMap.getContractModelRiskLimiterCancelCloseBtn());
			doClick("//span[text()='Default Episode Model Task List']//following::div[text()='Build Structure Elements']");
			assertElementIsDisplayedWithXpath("//div[contains(@id,'treeview')]//div[text()='Services']");
			doClick("//span[text()='Default Episode Model Task List']//following::div[text()='Build Structure Elements']");
			ExtentReport.logPass("PASS", "test03OpenEpisode");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03OpenEpisode", driver, e);
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
