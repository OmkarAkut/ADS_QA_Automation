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
/** Regression test case ADS-5985 **/
public class ActualCostingMethodPatientChgsDateRange extends CalculationHelper {
	static String costModel = "v1024 REGRESSION ADS-1634 Actual Cost";
	static String costModelCalcScenarioApr = "v103 REGRESSION ADS-1634 CMS gh - Apr";
	static String costModelCalcScenarioMay = "v103 REGRESSION ADS-1634 CMS GH - May";
	static String costModelCalcScenarioJune = "v103 REGRESSION ADS-1634 CMS GH - June";
	static CostingMap costing;
	static ContractingMap contractMap;
	static ModelLibraryMap modelMap;
	static String[] filter = { "Name", "Is", "Equal To", costModel };
	static String[] filterCostModelScenarioApr = { "Name", "Is", "Equal To", costModelCalcScenarioApr };
	static String[] filterCostModelScenarioMay = { "Name", "Is", "Equal To", costModelCalcScenarioMay };
	static String[] filterCostModelScenarioJune = { "Name", "Is", "Equal To", costModelCalcScenarioJune };
	//Shilpa updated on 10.18.2024
	static String[] filtercostModelScenarioCalApr= {"Scenario Name", "Is", "Equal To", costModelCalcScenarioApr};
	static String[] filterCostModelScenarioCalMay= {"Scenario Name", "Is", "Equal To", costModelCalcScenarioMay};
	static String[] filterCostModelScenarioCalJune= {"Scenario Name", "Is", "Equal To", costModelCalcScenarioJune};
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ActualCostingMethodPatientChgsDateRange", "webdriver.scripts.costing",
				"ActualCostingMethodPatientChgsDateRange");
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
//			doClickTreeData("CM Test");
//			waitForMainPageTitle("Cost Scnenarios");
//			doClickTreeData("Cost Scnenarios");
//			doClickTreeData("Cost Model Calculation Scenarios");
//			waitForMainPageTitle("Cost Model Calculation Scenarios");
			// Shilpa added below lines for 11.2 on 12.4.2023
			doClickTreeData("Assign Unit Costs");
			doClickTreeItem("Cost Model Calculation Scenarios");
			waitForMainPageTitle("Cost Model Calculation Scenarios");
			ExtentReport.logPass("PASS", "test01OpenCostCalculationScenario");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01OpenCostCalculationScenario", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test02VerifyCalculationStatusForCostModelCalcScenarioApr() throws Throwable {
		try {
//			doClick("(//h1[(text()='Cost Model Calculation Scenarios')]//following::span[text()='Filter'])[1]");
			doClick(costing.getCostModelCalcFilterButton());
			doFilterCreate(filterCostModelScenarioApr);
			tableClickCellFirstColumn(costModelCalcScenarioApr);
			doClick(costing.getCostModelScenarioCalculationButtonCalculate());
			//Shilpa : Updated: 11.2 : 10.18.2024
			doClick(ContractingMap.getCalculationStatusButtonFilter());
			doFilterCreate(filtercostModelScenarioCalApr);
			waitForFirstRowCalculationBarToReach100Percent();
			calculationStatusPageOpenViewDialog();

//			confirmCalculationStatusDetailsContains("  Department: D2110 DEPT D2110");
//			ContractModelsHelper.gotToSpecifiedPage(ModelLibraryMap.getInputNumberPage(), ModelLibraryMap.getGoToPage(), "2");
//			confirmCalculationStatusDetailsContains("  Sum Actual Costs =        1444.000");
//			ContractModelsHelper.gotToSpecifiedPage(ModelLibraryMap.getInputNumberPage(), ModelLibraryMap.getGoToPage(), "4");
//			confirmCalculationStatusDetailsContains("  Sum Actual Costs = ");
//			confirmCalculationStatusDetailsContains("  Department: D2115 DEPT D2115");
			// Shilpa updated script for 11.2 ,Calling generic method to search view dialog
			checkForRecordsProcessed("  Department: D2110 DEPT D2110");
			checkForRecordsProcessed("  Sum Actual Costs =        1444.000");
			checkForRecordsProcessed("  Sum Actual Costs = ");
			checkForRecordsProcessed("  Department: D2115 DEPT D2115");
			closeViewDialog();
			doClosePageOnLowerBar("Calculation Status");
			ExtentReport.logPass("PASS", "test02VerifyCalculationStatusForCostModelCalcScenarioApr");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02VerifyCalculationStatusForCostModelCalcScenarioApr", driver, e);
			fail(e.getMessage());
		} 
		
	}

	@Test
	public void test03VerifyCalculationStatusForCostModelCalcScenarioMay() throws Throwable {
		try {
			doClick(costing.getCostModelCalcClearFilterButton());
			waitForDisplayedSpinnerToEnd();
			doClick(costing.getCostModelCalcFilterButton());
			doFilterCreate(filterCostModelScenarioMay);
			waitForAjaxExtJs();
			tableClickCellFirstColumn(costModelCalcScenarioMay);
			doClick(costing.getCostModelScenarioCalculationButtonCalculate());
			//Shilpa : Updated: 11.2 : 10.18.2024
			doClick(ContractingMap.getCalculationStatusButtonFilter());
			doFilterCreate(filterCostModelScenarioCalMay);
			waitForFirstRowCalculationBarToReach100Percent();
			calculationStatusPageOpenViewDialog();
//			confirmCalculationStatusDetailsContains("  Department: D2110 DEPT D2110");
//			confirmCalculationStatusDetailsContains("  Sum Actual Costs = ");
//			ContractModelsHelper.gotToSpecifiedPage(ModelLibraryMap.getInputNumberPage(), ModelLibraryMap.getGoToPage(),
//					"4");
//			confirmCalculationStatusDetailsContains("  Sum Actual Costs = ");
//			confirmCalculationStatusDetailsContains("  Department: D2115 DEPT D2115");
			checkForRecordsProcessed("  Department: D2110 DEPT D2110");
			checkForRecordsProcessed("  Sum Actual Costs = ");
			checkForRecordsProcessed("  Department: D2115 DEPT D2115");
			closeViewDialog();
			doClosePageOnLowerBar("Calculation Status");
			ExtentReport.logPass("PASS", "test03VerifyCalculationStatusForCostModelCalcScenarioMay");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03VerifyCalculationStatusForCostModelCalcScenarioMay", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test04VerifyCalculationStatusForCostModelCalcScenarioJune() throws Throwable {
		try {
			doClick(costing.getCostModelCalcClearFilterButton());
			waitForDisplayedSpinnerToEnd();
			doClick(costing.getCostModelCalcFilterButton());
			doFilterCreate(filterCostModelScenarioJune);
			tableClickCellFirstColumn(costModelCalcScenarioJune);
			doClick(costing.getCostModelScenarioCalculationButtonCalculate());
			//Shilpa : Updated: 11.2 : 10.18.2024
			doClick(ContractingMap.getCalculationStatusButtonFilter());
			doFilterCreate(filterCostModelScenarioCalJune);
			waitForFirstRowCalculationBarToReach100Percent();
			calculationStatusPageOpenViewDialog();
//			confirmCalculationStatusDetailsContains("  Department: D2110 DEPT D2110"); Shilpa updated for 11.2 on 23.5.2024 , generic method works good here 
			checkForRecordsProcessed("  Department: D2110 DEPT D2110");
			checkForRecordsProcessed("  Sum Actual Costs = ");
//			ContractModelsHelper.gotToSpecifiedPage(ModelLibraryMap.getInputNumberPage(), ModelLibraryMap.getGoToPage(),
//					"4");Shilpa updated for 11.2 on 23.5.2024 not required this line
			checkForRecordsProcessed("  Department: D2115 DEPT D2115");
			checkForRecordsProcessed("  Sum Actual Costs = ");
//			ContractModelsHelper.gotToSpecifiedPage(ModelLibraryMap.getInputNumberPage(), ModelLibraryMap.getGoToPage(),
//					"5");;Shilpa updated for 11.2 on 23.5.2024 not required this line
//			checkForRecordsProcessed("  Sum Actual Costs =        1444.000");
//			checkForRecordsProcessed("  Department: D2110 DEPT D2110");
////			checkForRecordsProcessed("  Sum Actual Costs = ");
//			checkForRecordsProcessed("  Department: D2115 DEPT D2115");
//			checkForRecordsProcessed("  Sum Actual Costs =        1444.000");
			closeViewDialog();
			doClosePageOnLowerBar("Calculation Status");
			doClosePageOnLowerBar("v1024 REGRESSION...");
			doClosePageOnLowerBar("Costing Models");
			ExtentReport.logPass("PASS", "test04VerifyCalculationStatusForCostModelCalcScenarioJune");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04VerifyCalculationStatusForCostModelCalcScenarioJune", driver, e);
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void endtest() throws Exception {
		
		ExtentReport.report.flush();

	}
}
