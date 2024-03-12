package webdriver.scripts.systemmaintenance;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.support.ui.WebDriverWait;

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
//ADS-6516
	@Test
	public void test01assertCustomiseTaskListsTabs_6516() throws Throwable {
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
	public void test02OpenOverheadTab_6516() throws Throwable {
		try {
			doClick(systemMap.getCustomizeTaskListsPageSubTabOverhead());
			assertTextIsDisplayed("Default Overhead Model Task List");
			Thread.sleep(500);
//			Omkar 21/4/2023 : xpath change for 11.2
//			doClick("//span[text()='Default Overhead Model Task List']//following::div[text()='Prepare Costing Elements']");
//			doClick("//div[text()='Default Overhead Model Task List']//following::span[text()='Prepare Costing Elements']");
			doClick("//div[text()='Default Overhead Model Task List']//following::span[text()='Prepare Costing Elements']/../child::div");
			assertTextIsDisplayed("Entities");
//			Omkar 21/4/2023 : xpath change for 11.2
//			doClick("//span[text()='Default Overhead Model Task List']//following::div[text()='Prepare Costing Elements']");
			doClick("//div[text()='Default Overhead Model Task List']//following::span[text()='Prepare Costing Elements']");
			ExtentReport.logPass("PASS", "test02OpenOverheadTab");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02OpenOverheadTab", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test02OpenUnpublishedContract_6516() throws Throwable {
		try {
			doClick(systemMap.getCustomizeTaskListsPageSubTabUnpublishedContract());
			doClick(ContractingMap.getWarningCancelCloseBtn());
			assertTextIsDisplayed("Default Unpublished Contract Model Task List");
//			Omkar 21/4/2023 : xpath change for 11.2
//			doClick("//span[text()='Default Unpublished Contract Model Task List']//following::div[text()='Build Structure Elements'][1]");
			doClick("//div[text()='Default Unpublished Contract Model Task List']//following::span[text()='Build Structure Elements']");
			assertTextIsDisplayed("Contract Types");
//			Omkar 21/4/2023 : xpath change for 11.2
//			doClick("//span[text()='Default Unpublished Contract Model Task List']//following::div[text()='Build Structure Elements'][1]");
			doClick("//div[text()='Default Unpublished Contract Model Task List']//following::span[text()='Build Structure Elements']");
			ExtentReport.logPass("PASS", "test02OpenUnpublishedContract");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02OpenUnpublishedContract", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test03OpenPublishedContract_6516() throws Throwable {
		try {
			doClick(systemMap.getCustomizeTaskListsPageSubTabPublishedContract());
			doClick(ContractingMap.getWarningCancelCloseBtn());
			assertTextIsDisplayed("Edit Payment Terms");
//			Omkar 12/4/2023 : xpath change for 11.2
//			doClick("(//div[contains(@class,'nowrapRow')]//following::div[text()='Edit Payment Terms'])");
			doClick("//div[contains(@class,'nowrapRow')]//following::span[text()='Edit Payment Terms']");
//			Omkar 11/5/2023 : xpath change for 11.2
//			assertElementIsDisplayedWithXpath(
//					"//div[contains(@id,'treeview')]//div[text()='Fee For Service Payment Terms']");
			//Shilpa update xpath for 11.2 on 12.15.2023 
			assertElementIsDisplayedWithXpath("//div[contains(@id,'treepanel')]//span[text()='Fee For Service Payment Terms']");
//			Omkar 21/4/2023 : xpath change for 11.2
//			doClick("(//div[contains(@class,'nowrapRow')]//following::div[text()='Edit Payment Terms'])");
			doClick("//div[contains(@class,'nowrapRow')]//following::span[text()='Edit Payment Terms']");
			ExtentReport.logPass("PASS", "test03OpenPublishedContract");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03OpenPublishedContract", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test04OpenEpisode_6516() throws Throwable {
		try {
			doClick(systemMap.getCustomizeTaskListsPageSubTabEpisode());
			//Shilpa updated xpath for 11.2 on 12.15.2023
			doClick(ContractingMap.getWarningCancelCloseBtn());
//			Omkar 11/5/2023 : xpath change for 11.2
//			doClick("//span[text()='Default Episode Model Task List']//following::div[text()='Build Structure Elements']");
//			assertElementIsDisplayedWithXpath("//div[contains(@id,'treeview')]//div[text()='Services']");
//			doClick("//span[text()='Default Episode Model Task List']//following::div[text()='Build Structure Elements']");
			//Shilpa commented below line for 11.2
//			doClick("//div[text()='Default Episode Model Task List']//following::span[text()='Build Structure Elements']");			
//			assertElementIsDisplayedWithXpath("//div[contains(@id,'treepanel')]//span[text()='Services']");
			//Shilpa updated xpath for 11.2 on 12.15.2023
			assertElementIsDisplayedWithXpath("//div[contains(@id,'ctlEpisodeTree-body')]//span[text()='Services']");
			doClick("//div[@id='ctlEpisodeTree-body']//following::span[text()='Build Structure Elements']");
			
			ExtentReport.logPass("PASS", "test03OpenEpisode");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03OpenEpisode", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test05OpenCostTab_6516() throws Throwable {
		doClick(systemMap.getCustomizeTaskListsPageSubTabCost());
		doClick("(//span[text()='Prepare Costing Elements']//parent::div/div)[1]");
		doClick("(//span[text()='Prepare Costing Elements']//parent::div/div)[1]");
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
