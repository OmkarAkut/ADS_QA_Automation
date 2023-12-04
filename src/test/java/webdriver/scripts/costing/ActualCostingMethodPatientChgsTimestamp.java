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
/** Regression test case ADS-5986 **/
public class ActualCostingMethodPatientChgsTimestamp extends CalculationHelper {
	static String costModel = "v1024 REGRESSION ADS-1634 Actual Cost";
	static String costModelCalcScenarioA="v103 REGRESSION ADS-1634 CMS A";
	static String costModelCalcScenarioB="v103 REGRESSION ADS-1634 CMS B";
	static String costModelCalcScenarioC="v103 REGRESSION ADS-1634 CMS C";
	static String costModelCalcScenarioD="v103 REGRESSION ADS-1634 CMS D";
	static String costModelCalcScenarioE="v103 REGRESSION ADS-1634 CMS E";
	static String costModelScenarioUpdate="v103 REGRESSION ADS-1634 CMS E-Edit";
	static CostingMap costing;
	static ContractingMap contractMap;
	static ModelLibraryMap modelMap;
	static String[] filter = { "Name", "Is", "Equal To", costModel };
	static String[] filterCostModelScenarioA = { "Name", "Is", "Equal To", costModelCalcScenarioA };
	static String[] filterCostModelScenarioB = { "Name", "Is", "Equal To", costModelCalcScenarioB };
	static String[] filterCostModelScenarioC= { "Name", "Is", "Equal To", costModelCalcScenarioC };
	static String[] filterCostModelScenarioD= { "Name", "Is", "Equal To", costModelCalcScenarioD};
	static String[] filterCostModelScenarioE= { "Name", "Is", "Equal To", costModelCalcScenarioE};
	static String[] filterCostModelScenarioEdit= { "Name", "Is", "Equal To", costModelScenarioUpdate};
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ActualCostingMethodPatientChgsTimestamp", "webdriver.scripts.costing", "ActualCostingMethodPatientChgsTimestamp");
		try {
			costing = BuildMap.getInstance(driver, CostingMap.class);
			contractMap = BuildMap.getInstance(driver, ContractingMap.class);
			modelMap=BuildMap.getInstance(driver, ModelLibraryMap.class);
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
//			doClickTreeData("CM Test");
//			waitForMainPageTitle("Cost Scnenarios");
			// Shilpa added below lines for 11.2 on 12.4.2023
			doClickTreeData("Assign Unit Costs");
			doClickTreeItemWithCheckbox("Cost Model Calculation Scenarios");
			waitForMainPageTitle("Cost Model Calculation Scenarios");
			doClickTreeItemWithCheckbox("Cost Model Calculation Scenarios");
			ExtentReport.logPass("PASS", "test01OpenCostCalculationScenario");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01OpenCostCalculationScenario", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02VerifyCalculationStatusPageForCostModelScenarioA() throws Throwable {
		try {
			doClick(costing.getCostModelCalcFilterButton());
			doFilterCreate(filterCostModelScenarioA);
			tableClickCellFirstColumn(costModelCalcScenarioA);
			doClick(costing.getCostModelScenarioCalculationButtonCalculate());
			waitForFirstRowCalculationBarToReach100Percent();
			calculationStatusPageOpenViewDialog();
			ContractModelsHelper.gotToSpecifiedPage(ModelLibraryMap.getInputNumberPage(), ModelLibraryMap.getGoToPage(), "2");
			driverDelay(700);
			confirmCalculationStatusDetailsContains("  Sum Actual Costs =         748.000");
			closeViewDialog();
			ExtentReport.logPass("PASS", "test02VerifyCalculationStatusPageForCostModelScenarioA");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02VerifyCalculationStatusPageForCostModelScenarioA", driver, e);
			fail(e.getMessage());
		}
		finally {
			doClosePageOnLowerBar("Calculation Status");
		}
	}
	@Test
	public void test03VerifyCalculationStatusPageForCostModelScenarioB() throws Throwable {
		try {
			doClick(costing.getCostModelCalcClearFilterButton());
			waitForDisplayedSpinnerToEnd();
			doClick(costing.getCostModelCalcFilterButton());
			doFilterCreate(filterCostModelScenarioB);
			tableClickCellFirstColumn(costModelCalcScenarioB);
			doClick(costing.getCostModelScenarioCalculationButtonCalculate());
			waitForFirstRowCalculationBarToReach100Percent();
			calculationStatusPageOpenViewDialog();
			ContractModelsHelper.gotToSpecifiedPage(ModelLibraryMap.getInputNumberPage(), ModelLibraryMap.getGoToPage(), "2");
			confirmCalculationStatusDetailsContains("  Sum Actual Costs =         748.000");
			closeViewDialog();
			ExtentReport.logPass("PASS", "test03VerifyCalculationStatusPageForCostModelScenarioB");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03VerifyCalculationStatusPageForCostModelScenarioB", driver, e);
			fail(e.getMessage());
		}
		finally {
			doClosePageOnLowerBar("Calculation Status");
		}
	}
	@Test
	public void test04VerifyCalculationStatusPageForCostModelScenarioC() throws Throwable {
		try {
			doClick(costing.getCostModelCalcClearFilterButton());
			waitForDisplayedSpinnerToEnd();
			doClick(costing.getCostModelCalcFilterButton());
			doFilterCreate(filterCostModelScenarioC);
			tableClickCellFirstColumn(costModelCalcScenarioC);
			doClick(costing.getCostModelScenarioCalculationButtonCalculate());
			waitForFirstRowCalculationBarToReach100Percent();
			calculationStatusPageOpenViewDialog();
			ContractModelsHelper.gotToSpecifiedPage(ModelLibraryMap.getInputNumberPage(), ModelLibraryMap.getGoToPage(), "2");
			confirmCalculationStatusDetailsContains("  Sum Actual Costs =         748.000");
			closeViewDialog();
			ExtentReport.logPass("PASS", "test04VerifyCalculationStatusPageForCostModelScenarioC");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04VerifyCalculationStatusPageForCostModelScenarioC", driver, e);
			fail(e.getMessage());
		}
		finally {
			doClosePageOnLowerBar("Calculation Status");
		}
	}
	@Test
	public void test05VerifyCalculationStatusPageForCostModelScenarioD() throws Throwable {
		try {
			doClick(costing.getCostModelCalcClearFilterButton());
			waitForDisplayedSpinnerToEnd();
			doClick(costing.getCostModelCalcFilterButton());
			doFilterCreate(filterCostModelScenarioD);
			tableClickCellFirstColumn(costModelCalcScenarioD);
			doClick(costing.getCostModelScenarioCalculationButtonCalculate());
			waitForFirstRowCalculationBarToReach100Percent();
			calculationStatusPageOpenViewDialog();
			ContractModelsHelper.gotToSpecifiedPage(ModelLibraryMap.getInputNumberPage(), ModelLibraryMap.getGoToPage(), "2");
			confirmCalculationStatusDetailsContains("  Sum Actual Costs =         848.000");
			closeViewDialog();
			ExtentReport.logPass("PASS", "test05VerifyCalculationStatusPageForCostModelScenarioD");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05VerifyCalculationStatusPageForCostModelScenarioD", driver, e);
			fail(e.getMessage());
		}
		finally {
			doClosePageOnLowerBar("Calculation Status");
		}
	}
	@Test
	public void test06VerifyCalculationStatusPageForCostModelScenarioE() throws Throwable {
		try {
			doClick(costing.getCostModelCalcClearFilterButton());
			waitForDisplayedSpinnerToEnd();
			doClick(costing.getCostModelCalcFilterButton());
			doFilterCreate(filterCostModelScenarioE);
			tableDoubleClickCellFirstColumn(costModelCalcScenarioE);
			ContractModelsHelper.keyInValues(CostingMap.getCostScenarioName(), costModelScenarioUpdate);
			doClick(costing.getSaveAsButton());
			doClick(CostingMap.getEncounterCalculateBtn());
			waitForFirstRowCalculationBarToReach100Percent();
			calculationStatusPageOpenViewDialog();
			ContractModelsHelper.gotToSpecifiedPage(ModelLibraryMap.getInputNumberPage(), ModelLibraryMap.getGoToPage(), "2");
			confirmCalculationStatusDetailsContains("  Sum Actual Costs =        1044.000");
			closeViewDialog();
			ExtentReport.logPass("PASS", "test06VerifyCalculationStatusPageForCostModelScenarioE");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06VerifyCalculationStatusPageForCostModelScenarioE", driver, e);
			fail(e.getMessage());
		}
		finally {
			doClosePageOnLowerBar("Calculation Status");
			doClick("//span[text()='Cancel & Close']/../..");
			doClick(costing.getCostModelCalcClearFilterButton());
			waitForDisplayedSpinnerToEnd();
			doClick(costing.getCostModelCalcFilterButton());
			doFilterCreate(filterCostModelScenarioEdit);
			tableClickCellFirstColumn(costModelScenarioUpdate);
			doClick("(//h1//following::div[contains(@id,'costmodelscenariolist')]//following::div//following::div[contains(@id,'acommontbar')]//following::span[text()='Delete'])[1]");
			doClick("(//span[text()='Delete'])[4]");
			waitForDisplayedSpinnerToEnd();
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		doClosePageOnLowerBar("v1024 REGRESSION...");
		
		doClosePageOnLowerBar("Model Library");
		ExtentReport.report.flush();

	}
}
