package webdriver.scripts.costing;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-5984 **/
public class ActualCostingMethodPatientChgsDoesNotMatch extends CalculationHelper {
	static String costModel = "v1024 REGRESSION ADS-1634 Actual Cost";
	static String costModelCalcScenarioEntity = "v103 REGRESSION ADS-1634 CMS Entity";
	static String costModelCalcScenarioDept = "v103 REGRESSION ADS-1634 CMS Dept";
	static String costModelCalcScenarioNoActCost = "v103 REGRESSION ADS-1634 CMS No Act Cost";
	static CostingMap costing;
	static ContractingMap contractMap;
	static ModelLibraryMap modelMap;
	static String[] filter = { "Name", "Is", "Equal To", costModel };
	static String[] filterCostModelScenarioEntity = { "Name", "Is", "Equal To", costModelCalcScenarioEntity };
	static String[] filterCostModelScenarioDept = { "Name", "Is", "Equal To", costModelCalcScenarioDept };
	static String[] filterCostModelScenarioNoActCost = { "Name", "Is", "Equal To", costModelCalcScenarioNoActCost };

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ActualCostingMethodPatientChgsDoesNotMatch", "webdriver.scripts.costing",
				"ActualCostingMethodPatientChgsDoesNotMatch");
		try {
			costing = BuildMap.getInstance(driver, CostingMap.class);
			contractMap = BuildMap.getInstance(driver, ContractingMap.class);
			modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Costing Models");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test01OpenCostCalculationScenario() throws Throwable {
		try {
			doSearchForModel("");
			doClick(costing.getCostModelFilterButton());
			doFilterCreate(filter);
			tableDoubleClickCellFirstColumn(costModel);
			doClickTreeData("CM Test");
			waitForMainPageTitle("Cost Scnenarios");
			doClickTreeData("Cost Scnenarios");
			waitForMainPageTitle("Cost Model Calculation Scenarios");
			doClickTreeItemWithCheckbox("Cost Model Calculation Scenarios");
			ExtentReport.logPass("PASS", "test01OpenCostCalculationScenario");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01OpenCostCalculationScenario", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test02VerifyCalculationStatusForCostModelCalcScenarioEntity() throws Throwable {
		try {
			doClick(costing.getCostModelCalcFilterButton());
			doFilterCreate(filterCostModelScenarioEntity);
			tableClickCellFirstColumn(costModelCalcScenarioEntity);
			doClick(costing.getCostModelScenarioCalculationButtonCalculate());
			waitForFirstRowCalculationBarToReach100Percent();
			calculationStatusPageOpenViewDialog();
			confirmCalculationStatusDetailsContains("  Department: D3120 DEPT D3120");
			confirmCalculationStatusDetailsContains("  Sum Actual Costs = ");
			closeViewDialog();
			ExtentReport.logPass("PASS", "test02VerifyCalculationStatusForCostModelCalcScenarioEntity");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02VerifyCalculationStatusForCostModelCalcScenarioEntity", driver, e);
			fail(e.getMessage());
		} finally {
			doClosePageOnLowerBar("Calculation Status");
		}
	}

	@Test
	public void test03VerifyCalculationStatusForCostModelCalcScenarioDept() throws Throwable {
		try {
			doClick(costing.getCostModelCalcClearFilterButton());
			waitForDisplayedSpinnerToEnd();
			doClick(costing.getCostModelCalcFilterButton());
			doFilterCreate(filterCostModelScenarioDept);
			waitForAjaxExtJs();
			tableClickCellFirstColumn(costModelCalcScenarioDept);
			doClick(costing.getCostModelScenarioCalculationButtonCalculate());
			waitForFirstRowCalculationBarToReach100Percent();
			calculationStatusPageOpenViewDialog();
			confirmCalculationStatusDetailsContains("  Department: D2265 DEPT D2265");
			confirmCalculationStatusDetailsContains("  Sum Actual Costs = ");
			closeViewDialog();
			ExtentReport.logPass("PASS", "test03VerifyCalculationStatusForCostModelCalcScenarioDept");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03VerifyCalculationStatusForCostModelCalcScenarioDept", driver, e);
			fail(e.getMessage());
		} finally {
			doClosePageOnLowerBar("Calculation Status");
		}
	}

	@Test
	public void test04VerifyCalculationStatusForCostModelCalcScenarioNoActCost() throws Throwable {
		try {
			doClick(costing.getCostModelCalcClearFilterButton());
			waitForDisplayedSpinnerToEnd();
			doClick(costing.getCostModelCalcFilterButton());
			doFilterCreate(filterCostModelScenarioNoActCost);
			tableClickCellFirstColumn(costModelCalcScenarioNoActCost);
			doClick(costing.getCostModelScenarioCalculationButtonCalculate());
			waitForFirstRowCalculationBarToReach100Percent();
			calculationStatusPageOpenViewDialog();
			confirmCalculationStatusDetailsContains("  Department: D3047 DEPT D3047");
			confirmCalculationStatusDetailsContains("  Sum Actual Costs = ");
			closeViewDialog();
			ExtentReport.logPass("PASS", "test04VerifyCalculationStatusForCostModelCalcScenarioNoActCost");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04VerifyCalculationStatusForCostModelCalcScenarioNoActCost", driver, e);
			fail(e.getMessage());
		} finally {
			doClosePageOnLowerBar("Calculation Status");
		}
	}

	@AfterClass
	public static void endtest() throws Exception {
		doClosePageOnLowerBar("v1024 REGRESSION...");
		doClosePageOnLowerBar("Model Library");
		ExtentReport.report.flush();

	}

}
