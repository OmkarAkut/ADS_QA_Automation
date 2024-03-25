package webdriver.scripts.costing;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;

	@FixMethodOrder(MethodSorters.NAME_ASCENDING)
	/** Regression test case ADS-5982 **/
	public class EncCostCalcScenarioSelectedCostModelScenariosdisplayed extends CalculationHelper {
		static String costModel = "0-MarinaCostModel";
		static String encCostScenario="#ADS-1533 Enc Cost performance issue";
		static String costModelScenEvaluationOrder="1: *USE SG FY05 Total Cost Scenario";
		static String costModelScenEvaluationOrderTest="*USE CHC FY03 Total Cost Scenario";
		  String[] columnHeaderSubset = {"*USE CHC FY05 Total Cost Scenario",
				  "*USE IP FY05 Total Cost Scenario",
				  "*USE MH FY05 Total Cost Scenario",
				  "*USE MH FY05 Total Dept 3710",
				  "*USE MHC FY05 Total Cost Scenario",
				  "*USE SG FY05 Total Cost Scenario",
				  "131141",
				  "4.2 CMS 3520 & 3522 with OH by month",
				  "4.2 CMS 3520 3522 OH by month - Apr June"
};
		String[] columns= {"1S1 Office ","1S2 Clinic "};
	    static CostingMap costing;
		static ContractingMap contractMap;
		static ModelLibraryMap modelMap;
		static String[] filter = { "Name", "Is", "Equal To", costModel };
		static String[] filterEncCostScenario = { "Name", "Is", "Equal To", encCostScenario };
		static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		static String encCostModelName = "Model" + currentDateTime;
		static String[] filterNewCostScenario= {"Name","Is","Equal To",encCostModelName};
		static String[] filterEntities= {"150 Marina Medical Center","200 Southgate","300 Ocean","350 Island Park","600 Orange Tree","800 Marina Health Clinics12345678901234567890123456789012345678901234567890"};
		@BeforeClass
		public static void setupScript() throws Exception, Throwable {
			ExtentReport.reportCreate("EncCostCalcScenarioSelectedCostModelScenariosdisplayed", "webdriver.scripts.costing",
					"EncCostCalcScenarioSelectedCostModelScenariosdisplayed");
			try {
				costing = BuildMap.getInstance(driver, CostingMap.class);
				contractMap = BuildMap.getInstance(driver, ContractingMap.class);
				modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
				Login.loginUser("CostAnalyst1");
				goToPage("Costing Models");
				ExtentReport.logPass("PASS", "setupScript");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
				fail(e.getMessage());
			}
		}
		//ADS-5982 all steps
	@Test
	public void test01OpenCostModel_5982() throws Throwable {
		try {
			doSearchForModel("");
			doClick(costing.getCostModelFilterButton());
			doFilterCreate(filter);
			tableDoubleClickCellFirstColumn(costModel);
			doClickTreeItem("Assign Costs to Encounters");
//			waitForMainPageTitle("Encounter Cost Calculation Scenarios");
			doClick("//*[contains(@id,'treeview')]/tbody/tr/td/div/span[contains(text(),'Encounter Cost')]");
			waitForElementPresence("//h1[text()='Encounter Cost Calculation Scenarios']");
//			doClick("//*[text()='Cost Scnenarios']//following::td[4]/div");
//			doClickTreeItemWithCheckbox("Encounter Cost Calculation Scenarios");
			ExtentReport.logPass("PASS", "test01OpenCostModel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01OpenCostModel", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02OpenEncounterCostScenario_5982() throws Throwable {
		try {
			doClick(costing.getEncCostModelFilterButton());
			doFilterCreate(filterEncCostScenario);
			tableDoubleClickCellFirstColumn(encCostScenario);
			ExtentReport.logPass("PASS", "test02OpenEncounterCostScenario");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02OpenEncounterCostScenario", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test03AssertCostScenario_5982() throws Throwable {
		try {
			assertElementIsDisplayedWithXpath("//label[text()='Cost Model Scenarios in Evaluation Order']//following::li[contains(text(),'"+costModelScenEvaluationOrder+"')]");


			ExtentReport.logPass("PASS", "test03AssertCostScenario");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03AssertCostScenario", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04VerifyCancelAndCloseCostScenario_5982() throws Throwable {
		try {
			doClick(costing.getCostModelEvaluationOrderSelect());
			waitForMainPageTitle("Add Cost Model Scenarios");
			ContractModelsHelper.assertColumnsToDisplayColumnIsSelected(encCostScenario);
			ContractModelsHelper.highlightColumnsToDisplayColumn(costModelScenEvaluationOrderTest);
		    doClick(contractMap.getSelectItem());

			ContractModelsHelper.assertColumnsToDisplayColumnIsSelected(costModelScenEvaluationOrderTest);
			ContractModelsHelper.scrollToView("//*[text()='2 Item(s) Selected']");
			assertElementIsDisplayedWithXpath("//*[text()='2 Item(s) Selected']");
			doClick("//h1[text()='Add Cost Model Scenarios']//following::span[text()='Apply']");
			assertElementIsDisplayedWithXpath("//label[text()='Cost Model Scenarios in Evaluation Order']//following::li[contains(text(),'"+costModelScenEvaluationOrder+"')]");
			assertElementIsDisplayedWithXpath("//label[text()='Cost Model Scenarios in Evaluation Order']//following::li[contains(text(),'"+costModelScenEvaluationOrderTest+"')]");
			doClick(costing.getRvuMaintenanceFilterButtonCancelAndClose());
			waitForElementToBeVisible(costing.getCostModelScenarioCalculationFilterButtonCancelAndClose());
			doClick(costing.getCostModelScenarioCalculationFilterButtonCancelAndClose());
			ExtentReport.logPass("PASS", "test04CostModelEvaluationOrder");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04CostModelEvaluationOrder", driver, e);
			fail(e.getMessage());
		}
		
		
	}
	@Test
	public void test05CreateNewEncCostScenario_5982() throws Throwable {
		try {
			doClick(CostingMap.getEncounterNewBtn());
			ContractModelsHelper.keyInValues(ContractingMap.getInputName(), encCostModelName);
			doClick(costing.getEncCostModelEvaluationSelectButton());
			Thread.sleep(500);
			ContractModelsHelper.selectMultipleColumnsToDisplay(columnHeaderSubset);
			doClick("//h1[text()='Add Cost Model Scenarios']//following::span[text()='Apply']");
			doDropdownSelectUsingOptionText(costing.getCostModelScenariosinEvaluationOrderFrom(),
					costing.getCostModelScenarioFromOptions(), "Apr 2004");

			doDropdownSelectUsingOptionText(costing.getCostModelScenariosinEvaluationOrderTo(),
					costing.getCostModelScenarioToOptions(), "Mar 2005");
			doDropdownSelectUsingOptionText(costing.getCostModelScenario(),
					costing.getCostModelScenarioOptions(), "4.2 CMS 3520 & 3522 with OH by month");
			doDropdownSelectUsingOptionText(costing.getCostModelScenarioMonthToUse(),
					costing.getCostModelScenarioMonthToUseOptions(), "Aug 2004");
			doDropdownSelectUsingOptionText(costing.getCostModelScenariosinEvaluationOrderAssignedCost(),
					costing.getCostModelScenariosinEvaluationOrderAssignedCostList(), "6 : Multiple CC Masters");
			doClick(costing.getCostModelScenariosinEvaluationOrderEncounterSelect());
			ContractModelsHelper.selectMultipleColumnsToDisplay(columns);
			assertElementIsDisplayedWithXpath("//*[text()='2 Item(s) Selected']");
			doClick("//div[text()='Add Encounter Types']//following::span[text()='Apply']");
			doClick(costing.getCostModelScenariosinEvaluationOrderEntitiesSelect());
			doClick(costing.getCostModelScenariosinEvaluationOrderEncounterSelectAll());
			doClick("//div[text()='Add Entities']//following::span[text()='Apply']");
			doClick(costing.getCostModelScenariosinEvaluationOrderDischargeCheck());
			doClick(costing.getCostModelScenariosinEvaluationOrderAdmissionCheck());
			doClick(CostingMap.getCostModelSharedLogCheckbox());
			doClick(costing.getEncCostModelCancelCloseButton());
			waitForElementToBeVisible(ContractingMap.getContractModelRiskLimiterMessageBoxCancelCloseBtn());
			doClick(ContractingMap.getContractModelRiskLimiterMessageBoxCancelCloseBtn());
			ExtentReport.logPass("PASS", "test05VerifyNewEncCostScenario");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05VerifyNewEncCostScenario", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06VerifyCreatedEncCostScenario_5982() throws Throwable {
		try {
			doClick(costing.getEncCostModelClearFilterButton());
			waitForDisplayedSpinnerToEnd();
			doClick(costing.getEncCostModelFilterButton());
			doFilterCreate(filterNewCostScenario);
			tableDoubleClickCellFirstColumn(encCostScenario);
			assertElementIsDisplayedWithXpath("//label[text()='Cost Model Scenarios in Evaluation Order']//following::li[contains(text(),'"+costModelScenEvaluationOrder+"')]");
			ContractModelsHelper.CompareListToArray(driver.findElements(By.xpath("(//div[@class='x-boundlist-list-ct'])[2]//div")), columns);
			ContractModelsHelper.CompareListToArray(driver.findElements(By.xpath("(//div[@class='x-boundlist-list-ct'])[3]//div")), filterEntities);
			doClick(costing.getEncCostModelCancelCloseButton());
			waitForElementToBeVisible(ContractingMap.getContractModelRiskLimiterMessageBoxCancelCloseBtn());
			doClick(ContractingMap.getContractModelRiskLimiterMessageBoxCancelCloseBtn());			
			ExtentReport.logPass("PASS", "test06VerifyCreatedEncCostScenario");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06VerifyCreatedEncCostScenario", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test07DeleteCreatedEncCostScenario_5982() throws Throwable {
		try {
			doClick(costing.getEncCostModelDeleteButton());
			waitForElementToBeVisible(ContractingMap.getWarningPopUpDeleteButton());
			doClick(ContractingMap.getWarningPopUpDeleteButton());
			waitForDisplayedSpinnerToEnd();
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test07DeleteCreatedEncCostScenario");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07DeleteCreatedEncCostScenario", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() {
		ExtentReport.report.flush();
	}
}
