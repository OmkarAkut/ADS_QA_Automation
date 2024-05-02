package webdriver.scripts.costing;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.AdsHelper;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.SystemMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RunGLAdjustmentAndReclassifications extends CalculationHelper {
	static String costModel = "QA Marina";
	static String glModel = "SMOKETEST FY2008 RECLASS";
	static AdsHelper adsHelper = new AdsHelper();
	static String[] filter = { "Name", "Is", "Equal To", costModel };
	static String[] filterGLModel = { "Name", "Is", "Equal To", glModel };
	static ContractingMap contractMap;
	static SystemMaintenanceMap systemMap;
	static saveSystemSettings settings=new saveSystemSettings();

	/** Automates test ticket ADS-5988. */
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("RunGLAdjustmentAndReclassifications", "webdriver.scripts.costing",
				"RunGLAdjustmentAndReclassifications");
		try {
			contractMap = BuildMap.getInstance(driver, ContractingMap.class);
			systemMap=BuildMap.getInstance(driver, SystemMaintenanceMap.class);
			Login.loginUser("AutomationTesterAdmin");
			driverDelay(5000);
			ContractModelsHelper.saveCustomSettings("Use Custom", "Costing Models");
//			waitForDisplayedSpinnerToEnd();
//			goToPage("Costing Models");
//			waitForDisplayedSpinnerToEnd();
			doSearchForContractModel(" ");
			doClick(ContractingMap.getContractModelButtonFilter());
			adsHelper.doFilterCreate(filter);
			tableDoubleClickCellFirstColumn(costModel);

			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-5988 all steps
	@Test
	public void test01OpenGLAdjustmentAndReclassification_5988() throws Throwable {
		try {
//			ContractModelsHelper.doClickTreeData("CM Test");
//			waitForMainPageTitle("Cost Scnenarios");
			doClick("(//div[contains(@id,'taskfolder')]//following::table[contains(@id,'treeview')]//span[text()='CM Test'])");
			assertElementIsDisplayedWithXpath("//div[contains(@id,'taskfolder')]//following::table[contains(@id,'treeview')]//span[text()='All Masters']");
			doClick("//div[contains(@id,'taskfolder')]//following::table[contains(@id,'treeview')]//span[text()='All Masters']");
			doClick("//div[contains(@id,'taskfolder')]//following::table[contains(@id,'treeview')]//span[text()='GL Adjustment and Reclassification Calculation Scenarios']");
//			waitForElementPresence("//div[text()='GL Adjustment and Reclassification Calculation Scenarios']//input[@title='Changes screen']");
//			doClick("//div[text()='GL Adjustment and Reclassification Calculation Scenarios']//input[@title='Changes screen']");
			waitForElementToBeVisible(ContractingMap.getGLFilterButton());
			doClick(ContractingMap.getGLFilterButton());
			doFilterCreate(filterGLModel);
			tableDoubleClickCellFirstColumn(glModel);
			waitForElementToBeVisible(ContractingMap.getCalculateButton());
			doClick(ContractingMap.getCalculateButton());
			waitForPageTitle("Calculation Status");
			waitForFirstRowCalculationBarToReach100Percent();
			calculationStatusPageOpenViewDialog();
			waitForPresenceOfElement("//*[contains(text(),'Number of Reclassifications To Process: 6')]");
			confirmCalculationStatusDetailsContains("Number of Reclassifications To Process: 6");
			doClick("(//span[contains(@id,'button')]//span[text()='Cancel']/../../..)[3]");
			deleteCalculationStatusMyStatusPageFirstRow();
			doClosePageOnLowerBar("Calculation Status");
			doClick(ContractingMap.getgLCancelCloseBtn());
			doClosePageOnLowerBar(costModel);
			driverDelay(100);
			doClosePageOnLowerBar("Costing Models");
			
			ExtentReport.logPass("PASS", "test01OpenGLAdjustmentAndReclassification");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01OpenGLAdjustmentAndReclassification", driver, e);
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void endtest() throws Exception {
		ContractModelsHelper.revertCustomSettings();
		ExtentReport.report.flush();

	}
}
