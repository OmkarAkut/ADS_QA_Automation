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
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EditCostModelCalculationScenarios extends CalculationHelper {
	static CostingMap costing;
	static ContractingMap contractMap;
	static String costModel = "*CM1 TB MHFY05 After Vol Change";
	static String costModelUpdate = "*CM1 TB MHFY05 After Vol ChangeEdit";
	static String[] filter = { "Name", "Is", "Equal To", "*CM1 TB MHFY05 After Vol Change" };
	static String[] filterAfterEdit = { "Name", "Is", "Equal To", "*CM1 TB MHFY05 After Vol ChangeEdit" };
	static String[] filterResults = { "Name", "Is", "Equal To", "*CMSR TXT IMP" };
	static String entity="150 Marina Medical Center";
	static String department="2110 ICU";
	static String results="Apr 2004 to Mar 2005";
	static String resultAfterApply="1100023 ADULT CARE LVL 2 IP DLY";
	/** Regression: Automated test script for ADS-6645,ADS-6662,ADS-6664,ADS-6663*/

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
			ExtentReport.logPass("PASS", "test01OpenCostCalculationScenario");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01OpenCostCalculationScenario", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-6645
	@Test
	public void test02EditCostModelCalculationScenario_6645() throws Throwable {
		try {
			doClick(costing.getCostModelScenarioCalculationButtonEdit());
			waitForMainPageTitle("Cost Model Calculation Scenario");
			assertTextIsDisplayed("Cost Model Calculation Scenario");
			ContractModelsHelper.keyInValues(CostingMap.getCostScenarioName(), costModel + "Edit");
			driverDelay(300);
			ExtentReport.logPass("PASS", "test02EditCostModelCalculationScenario");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02EditCostModelCalculationScenario", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-6662
	@Test
	public void test03SaveCostCalculationScenario_6662() throws Throwable {
		try {
			doClick(ContractingMap.getContractFeeForServicePaymentSave());
			waitForDisplayedSpinnerToEnd();
			doClick(costing.getCostModelScenarioCalculationButtonClearFilter());
			waitForDisplayedSpinnerToEnd();
			doClick(costing.getCostModelScenarioCalculationButtonFilter());
			doFilterCreate(filterAfterEdit);
			assertElementTextWithXpath(
					"(//div[contains(@id,'costmodelscenariolist')]//*[text()='*CM1 TB MHFY05 After Vol ChangeEdit'])[1]",
					costModelUpdate, printout);
			doClick(costing.getCostModelScenarioCalculationButtonEdit());
			waitForMainPageTitle("Cost Model Calculation Scenario");
			ContractModelsHelper.keyInValues(CostingMap.getCostScenarioName(), costModel);
			doClick(ContractingMap.getContractFeeForServicePaymentSave());
			waitForDisplayedSpinnerToEnd();
			doClick(costing.getCostModelScenarioCalculationButtonClearFilter());
			ExtentReport.logPass("PASS", "test03SaveCostCalculationScenario");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03SaveCostCalculationScenario", driver, e);
			fail(e.getMessage());
		}

	}
	//ADS-6645
	@Test
	public void test04CancelCostModelCalculationScenario_6645() throws Throwable {
		try {
			waitForDisplayedSpinnerToEnd();
			test01OpenCostCalculationScenario();
			doClick(costing.getCostModelScenarioCalculationButtonEdit());
			doClick("//h1[text()='Cost Model Calculation Scenario']//following::span[text()='Cancel & Close']");
			ExtentReport.logPass("PASS", "test04CancelCostModelCalculationScenario");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04CancelCostModelCalculationScenario", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-6663
	@Test
	public void test05CalculateCostCalcultionScenario_6663() throws Throwable {
		try {
			doClick(costing.getCostModelScenarioCalculationButtonCalculate());
			waitForFirstRowCalculationBarToReach100Percent();
			calculationStatusPageOpenViewDialog();
			driverDelay(500);
//			 clickLastPageIconOnCalculationStatusViewLog();
			 checkForRecordsProcessed("Studied Allocation Percent = 100");
//			confirmCalculationStatusDetailsContains("Studied Allocation Percent = 100");
			closeViewDialog();
			ExtentReport.logPass("PASS", "test05CalculateCostCalcultionScenario");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05CalculateCostCalcultionScenario", driver, e);
			fail(e.getMessage());
		}
		finally {
			doClosePageOnLowerBar("Calculation Status");

		}
	}
	//ADS-6664
	@Test
	public void test06ResultsForCostCalculationScenarios_6664() throws Throwable {
		try {
			doClick(costing.getCostModelScenarioCalculationButtonClearFilter());
			waitForDisplayedSpinnerToEnd();
			doClick(costing.getCostModelScenarioCalculationButtonFilter());
			doFilterCreate(filterResults);
			doClick(costing.getCostModelScenarioCalculationButtonResults());
			waitForAjaxExtJs();
			ContractModelsHelper.scrollToView(costing.getRvuCostCalcScenarioEntityDropdown());
			doDropdownSelectUsingOptionText(costing.getRvuCostCalcScenarioEntityDropdown(),costing.getRvuCostCalcScenarioEntityOptions(), entity);
			doDropdownSelectUsingOptionText(costing.getRvuCostCalcScenarioDeptDropdown(), costing.getRvuCostCalcScenarioDeptOptions(),department);
			doDropdownSelectUsingOptionText(costing.getUnitCostQuickCalculationFieldResultsStoredFor(),costing.getRvuCostCalcScenarioResultsOptions(),results);
			waitForElementToBeVisible(costing.getRvuCostCalcScenarioTotalCost());
			assertElementIsDisplayed(costing.getRvuCostCalcScenarioTotalCost());
			doClick(costing.getRvuCostCalcScenarioApplySelection());
			waitForDisplayedSpinnerToEnd();
			assertTextIsDisplayed(resultAfterApply);
			doClick(costing.getRvuCostCalcScenarioCloseButton());
			ExtentReport.logPass("PASS", "test06ResultsForCostCalculationScenarios");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06ResultsForCostCalculationScenarios", driver, e);
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void endtest() throws Exception {
		doClosePageOnLowerBar("Cost Model Calculation Scenarios");
		ExtentReport.report.flush();

	}
}
