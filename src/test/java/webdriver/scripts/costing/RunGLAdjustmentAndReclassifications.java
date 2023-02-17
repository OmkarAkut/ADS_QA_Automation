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
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RunGLAdjustmentAndReclassifications extends CalculationHelper {
	static String costModel = "QA Marina";
	static String glModel = "SMOKETEST FY2008 RECLASS";
	static AdsHelper adsHelper = new AdsHelper();
	static String[] filter = { "Name", "Is", "Equal To", costModel };
	static String[] filterGLModel = { "Name", "Is", "Equal To", glModel };
	static ContractingMap contractMap;

	/** Automates test ticket ADS-5988. */
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("RunGLAdjustmentAndReclassifications", "webdriver.scripts.costing",
				"RunGLAdjustmentAndReclassifications");
		try {
			contractMap = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			waitForDisplayedSpinnerToEnd();
			goToPage("Costing Models");
			waitForDisplayedSpinnerToEnd();
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

	@Test
	public void test01OpenGLAdjustmentAndReclassification() throws Throwable {
		try {
			ContractModelsHelper.doClickTreeData("CM Test");
			waitForMainPageTitle("Cost Scnenarios");
			ContractModelsHelper.doClickTreeData("Cost Scnenarios");
			doClick("//div[text()='GL Adjustment and Reclassification Calculation Scenarios']//input[@title='Changes screen']");
			waitForElementToBeVisible(ContractingMap.getGLFilterButton());
			doClick(ContractingMap.getGLFilterButton());
			doFilterCreate(filterGLModel);
			tableDoubleClickCellFirstColumn(glModel);
			waitForElementToBeVisible(ContractingMap.getCalculateButton());
			doClick(ContractingMap.getCalculateButton());
			waitForPageTitle("Calculation Status");
			waitForFirstRowCalculationBarToReach100Percent();
			calculationStatusPageOpenViewDialog();
//			driverDelay(5000);
			waitForPresenceOfElement("//*[contains(text(),'Number of Reclassifications To Process: 6')]");
			confirmCalculationStatusDetailsContains("Number of Reclassifications To Process: 6");
			closeViewDialog();
			deleteCalculationStatusMyStatusPageFirstRow();
			doClosePageOnLowerBar("Calculation Status");
			doClick(ContractingMap.getContractModelRiskLimiterCancelCloseBtn());
			doClosePageOnLowerBar(costModel);
			driverDelay(100);
			doClosePageOnLowerBar("Model Library");
			ExtentReport.logPass("PASS", "test01OpenGLAdjustmentAndReclassification");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01OpenGLAdjustmentAndReclassification", driver, e);
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
