package webdriver.scripts.costing;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

public class EditCostModelCalculationScenarios extends UcqcHelper {
	static CostingMap costing;
	static ContractingMap contractMap;
	static String costModel = "*CM1 TB MHFY05 After Vol Change";
	static String[] filter = { "Name", "Is", "Equal To", "*CM1 TB MHFY05 After Vol Change" };

	/** Regression: Automated test script for ADS-6645 */

	@BeforeClass
	public static void setupScript() throws Throwable {

		ExtentReport.reportCreate("EditCostModelCalculationScenarios", "webdriver.scripts.costing",
				"EditCostModelCalculationScenarios");

		try {
			costing = BuildMap.getInstance(driver, CostingMap.class);
			contractMap = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Cost Model Scenario Calculation");
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}

	}

	@Test
	public void test01OpenCostCalculationScenario() throws Throwable {
		try {
			doClick(costing.getCostModelScenarioCalculationButtonFilter());
			doFilterCreate(filter);
			ExtentReport.logPass("PASS", "test01EditCostCalculationScenario");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01EditCostCalculationScenario", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test02EditCostCalculationScenario() throws Throwable {
		try {
			doClick(costing.getCostModelScenarioCalculationButtonEdit());
			waitForMainPageTitle("Cost Model Calculation Scenario");
			assertTextIsDisplayed("Cost Model Calculation Scenario");
			ContractingMap.getContractFeeForServicePaymentSave();
			waitForDisplayedSpinnerToEnd();
			test01OpenCostCalculationScenario();
			doClick(costing.getCostModelScenarioCalculationButtonEdit());
			ContractingMap.getNewRiskLimiterPopUpCancelClose();
			ExtentReport.logPass("PASS", "test02EditCostCalculationScenario");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02EditCostCalculationScenario", driver, e);
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void endtest() throws Exception {
		doClosePageOnLowerBar("Cost Model...");
		doClosePageOnLowerBar("Model Library");
		ExtentReport.report.flush();

	}
}
