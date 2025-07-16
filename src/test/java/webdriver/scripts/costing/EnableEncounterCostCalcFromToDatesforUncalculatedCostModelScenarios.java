package webdriver.scripts.costing;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-22123 **/
public class EnableEncounterCostCalcFromToDatesforUncalculatedCostModelScenarios extends CalculationHelper {
	static String costModel = "0-MarinaCostModel";
	static CostingMap costing;
	static ContractingMap contractMap;
	static ModelLibraryMap modelMap;
	static String[] filter = { "Name", "Is", "Equal To", costModel };
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String costscenarioNameWithCalc = "Cost Scenario A " + currentDateTime;
	static String costscenarioNameWithoutCalc = "Cost Scenario B " + currentDateTime;
	static String entity = "150 Marina Medical Center";
	static String glDataDescription = "* MHFY05 Reclass TB";
	static String actVol = "TBMHFY05VOL";
	static String department = "2110 ICU";
	static String priceList = "150FY02  Marina Hosp Price List FY02";
	String[] filterCostScenarioWithCalc = { "Name", "Is", "Equal To", costscenarioNameWithCalc };
	String[] filterCostScenarioWithoutCalc = { "Name", "Is", "Equal To", costscenarioNameWithoutCalc };
	String[] filterCalcScenario = { "Scenario Name", "Is", "Equal To", costscenarioNameWithCalc };
	String[] columnHeaderSubset = { costscenarioNameWithCalc, costscenarioNameWithoutCalc };
	static List<String> getFromList = new ArrayList<>();
	static List<String> getToList = new ArrayList<>();
	List<String> expDateList = Arrays.asList("Apr 2004", "May 2004", "Jun 2004", "Jul 2004", "Aug 2004", "Sep 2004",
			"Oct 2004", "Nov 2004", "Dec 2004", "Jan 2005", "Feb 2005", "Mar 2005");

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("EnableEncounterCostCalcFromToDatesforUncalculatedCostModelScenarios",
				"webdriver.scripts.costing", "EnableEncounterCostCalcFromToDatesforUncalculatedCostModelScenarios");
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

	public void createCostModelScenario(String scenarioName) throws Throwable {
		doClick(CostingMap.getCostScenarioNewButton());
		ContractModelsHelper.keyInValues(CostingMap.getCostScenarioName(), scenarioName);
		doDropdownSelectUsingOptionText(CostingMap.getGldataDesc(), CostingMap.getCostModelGLDataScenarioOptions(),
				glDataDescription);
		doDropdownSelectUsingOptionText(CostingMap.getVolScenario(), CostingMap.getCostModelVolScenarioOptions(),
				actVol);
		ContractModelsHelper.scrollToView(CostingMap.getEntitiesSelect());
		doClick(CostingMap.getEntitiesSelect());
		doClick(costing.getcostModelButtonColumnsToDisplayModalSelect());
		Thread.sleep(500);
		doClick(costing.getUnitCostQuickCalculationColumnsToDisplayModalApply());
		Thread.sleep(500);
		doClick(CostingMap.getDeptGroupsSelect());
		highlightColumnsToDisplayColumn("2110 ICU");
		doClick(costing.getcostModelButtonColumnsToDisplayModalSelect());
		Thread.sleep(500);
		doClick(costing.getUnitCostQuickCalculationColumnsToDisplayModalApply());
		Thread.sleep(500);
		doClick("//div[text()='150  Marina Medical Center']//following::input/..");
		doClick("//li[text()='" + priceList + "']");
		doDropdownSelectUsingOptionText(CostingMap.getCostModelStartMonthDrpdown(),
				CostingMap.getCostModelStartMonthScenarioOptions(), "Apr 2004");
		doDropdownSelectUsingOptionText(CostingMap.getCostModelEndMonthDrpdown(),
				CostingMap.getCostModelEndMonthScenarioOptions(), "May 2004");
		doClick(CostingMap.getCostModelSharedLogCheckbox());
		doClick(CostingMap.getCostScenarioSaveCloseBtn());

	}

	@Test
	public void test01CreateFirstCostCalculationScenario_WithCalc__22123() throws Throwable {
		try {
			doSearchForModel("");
			doClick(costing.getCostModelFilterButton());
			doFilterCreate(filter);
			tableDoubleClickCellFirstColumn(costModel);
			doClickTreeData("Assign Unit Costs");
			doClickTreeItem("Cost Model Calculation Scenarios");
			waitForMainPageTitle("Cost Model Calculation Scenarios");
			doClick(CostingMap.getCostScenarioNewButton());
			createCostModelScenario(costscenarioNameWithCalc);
			doClick(costing.getCostModelScenarioCalculationButtonFilter());
			doFilterCreate(filterCostScenarioWithCalc);
			doClick(costing.getCostModelScenarioCalculationButtonCalculate());
			doFilterCalculationPage(filterCalcScenario);
			waitForFirstRowCalculationBarToReach100Percent();
			doClosePageOnLowerBar("Calculation Status");
			doClick(CostingMap.getCostScenarioClearFilterButton());
			ExtentReport.logPass("PASS", "test01CreateFirstCostCalculationScenario_WithCalc__22123");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01CreateFirstCostCalculationScenario_WithCalc__22123", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test02CreateFirstCostCalculationScenario_WithoutCalc__22123() throws Throwable {
		try {
			createCostModelScenario(costscenarioNameWithoutCalc);
			ExtentReport.logPass("PASS", "test02CreateFirstCostCalculationScenario_WithoutCalc__22123");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02CreateFirstCostCalculationScenario_WithoutCalc__22123", driver, e);
			fail(e.getMessage());
		}
	}

	public static List<String> getFromDateList() {
		for (WebElement fromList : costing.getCostScenarioFromDrp()) {
			getFromList.add(fromList.getText());
		}
		return getFromList;
	}

	public static List<String> getToDateList() {
		for (WebElement fromList : costing.getCostScenarioToDrp()) {
			getToList.add(fromList.getText());
		}
		return getToList;

	}

	@Test
	public void test03Validate_CostScenarioWithCalc_Under_NewEncounterCostCalc_22123() throws Throwable {
		try {
			doClickTreeData("Assign Costs to Encounters");
			doClick("//td[contains(@class,'x-grid-cell-treecolumn')]//span[contains(text(),'Encounter Cost')]");
			doClick(costing.getEncCostModelNewButton());
			doClick(costing.getCostModelEvaluationOrderSelect());
			waitForMainPageTitle("Add Cost Model Scenarios");
			doClick(costing.getAddCostScenarioFilterButton());
			doFilterSetFilterParameters("Name", "Is", "Equal To", costscenarioNameWithCalc);
			doClick(costing.getAddCostScenarioFilterAddButton());
			doClick(costing.getAddCostScenarioApplyFilterButton());
			doClick(costing.getcostModelButtonColumnsToDisplayModalSelect());
			Thread.sleep(500);
			doClick(costing.getAddCostScenarioApplyButton());
			doClick(costing.getCostModelScenariosinEvaluationOrderFrom());
			getFromList = getFromDateList();
			assertListOfStringsContainsExpectedStrings(getFromList, expDateList);
			System.out.println(getFromList);
			doClick(costing.getCostModelScenariosinEvaluationOrderTo());
			getToList = getToDateList();
			assertListOfStringsContainsExpectedStrings(getToList, expDateList);
			doClick(costing.getEncCostModelCancelCloseButton());
			doClick(costing.getEncCostModelWarningCancelCloseButton());
			ExtentReport.logPass("PASS", "test03Validate_CostScenarioWithCalc_Under_NewEncounterCostCalc_22123");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03Validate_CostScenarioWithCalc_Under_NewEncounterCostCalc_22123", driver,
					e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test04Validate_CostScenarioWithCalc_Under_EditEncounterCostCalc_22123() throws Throwable {
		try {

			doClick(costing.getEncCostModelEditButton());
			doClick(costing.getCostModelEvaluationOrderSelect());
			waitForMainPageTitle("Add Cost Model Scenarios");
			doClick(costing.getcostModelButtonColumnsToDisplayModalRemove());
			doClick(costing.getAddCostScenarioFilterButton());
			doFilterSetFilterParameters("Name", "Is", "Equal To", costscenarioNameWithCalc);
			doClick(costing.getAddCostScenarioFilterAddButton());
			doClick(costing.getAddCostScenarioApplyFilterButton());
			doClick(costing.getcostModelButtonColumnsToDisplayModalSelect());
			Thread.sleep(500);
			doClick(costing.getAddCostScenarioApplyButton());
			scrollToView("//h1[text()='Encounter Cost Calculation Scenario']");
			doClick(costing.getCostModelScenariosinEvaluationOrderFrom());
			driverDelay(300);
			getFromList = getFromDateList();
			assertListOfStringsContainsExpectedStrings(getFromList, expDateList);
			System.out.println(getFromList);
			doClick(costing.getCostModelScenariosinEvaluationOrderTo());
			getToList = getToDateList();
			assertListOfStringsContainsExpectedStrings(getToList, expDateList);
			doClick(costing.getEncCostModelCancelCloseButton());
			doClick(costing.getEncCostModelWarningCancelCloseButton());
			ExtentReport.logPass("PASS", "test04Validate_CostScenarioWithCalc_Under_EditEncounterCostCalc_22123");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04Validate_CostScenarioWithCalc_Under_EditEncounterCostCalc_22123",
					driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test05Validate_CostScenario_WithoutCalc_NewEncounterCostCalc_22123() throws Throwable {
		try {
			doClick(costing.getEncCostModelNewButton());
			doClick(costing.getCostModelEvaluationOrderSelect());
			waitForMainPageTitle("Add Cost Model Scenarios");
			doClick(costing.getAddCostScenarioFilterButton());
			doFilterSetFilterParameters("Name", "Is", "Equal To", costscenarioNameWithoutCalc);
			doClick(costing.getAddCostScenarioFilterAddButton());
			doClick(costing.getAddCostScenarioApplyFilterButton());
			doClick(costing.getcostModelButtonColumnsToDisplayModalSelect());
			Thread.sleep(500);
			doClick(costing.getAddCostScenarioApplyButton());
			doClick(costing.getCostModelScenariosinEvaluationOrderFrom());
			getFromList = getFromDateList();
			assertListOfStringsContainsExpectedStrings(getFromList, expDateList);
			System.out.println(getFromList);
			doClick(costing.getCostModelScenariosinEvaluationOrderTo());
			getToList = getToDateList();
			assertListOfStringsContainsExpectedStrings(getToList, expDateList);
			doClick(costing.getEncCostModelCancelCloseButton());
			doClick(costing.getEncCostModelWarningCancelCloseButton());
			ExtentReport.logPass("PASS", "test05Validate_CostScenario_WithoutCalc_NewEncounterCostCalc_22123");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05Validate_CostScenario_WithoutCalc_NewEncounterCostCalc_22123", driver,
					e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test06Validate_CostScenario_WithoutCalc_EditEncounterCostCalc_22123() throws Throwable {
		try {

			doClick(costing.getEncCostModelEditButton());
			doClick(costing.getCostModelEvaluationOrderSelect());
			waitForMainPageTitle("Add Cost Model Scenarios");
			doClick(costing.getcostModelButtonColumnsToDisplayModalRemove());
			doClick(costing.getAddCostScenarioFilterButton());
			doFilterSetFilterParameters("Name", "Is", "Equal To", costscenarioNameWithoutCalc);
			doClick(costing.getAddCostScenarioFilterAddButton());
			doClick(costing.getAddCostScenarioApplyFilterButton());
			doClick(costing.getcostModelButtonColumnsToDisplayModalSelect());
			Thread.sleep(500);
			doClick(costing.getAddCostScenarioApplyButton());
			scrollToView("//h1[text()='Encounter Cost Calculation Scenario']");
			doClick(costing.getCostModelScenariosinEvaluationOrderFrom());
			driverDelay(300);
			getFromList = getFromDateList();
			assertListOfStringsContainsExpectedStrings(getFromList, expDateList);
			System.out.println(getFromList);
			doClick(costing.getCostModelScenariosinEvaluationOrderTo());
			getToList = getToDateList();
			assertListOfStringsContainsExpectedStrings(getToList, expDateList);
			doClick(costing.getEncCostModelCancelCloseButton());
			doClick(costing.getEncCostModelWarningCancelCloseButton());
			ExtentReport.logPass("PASS", "test06Validate_CostScenario_WithoutCalc_EditEncounterCostCalc_22123");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06Validate_CostScenario_WithoutCalc_EditEncounterCostCalc_22123", driver,
					e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test07Delete_CostScenario_22123() throws Throwable {
		try {
			doClickTreeItem("Cost Model Calculation Scenarios");
			waitForMainPageTitle("Cost Model Calculation Scenarios");
			doClick(costing.getCostModelScenarioCalculationButtonFilter());
			doFilterCreate(filterCostScenarioWithCalc);
			doClick(costing.getcostModelScenarioCalculationButtonDelete());
			doClick(costing.getcostModelScenarioCalculationButtonWarningDelete());
			doClick(costing.getcostModelScenarioCalcButtonClearFilter());
			doClick(costing.getCostModelScenarioCalculationButtonFilter());
			doFilterCreate(filterCostScenarioWithoutCalc);
			doClick(costing.getcostModelScenarioCalculationButtonDelete());
			doClick(costing.getcostModelScenarioCalculationButtonWarningDelete());
			doClosePageOnLowerBar("0-MarinaCostModel");
			ExtentReport.logPass("PASS", "test07Delete_CostScenario_22123");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07Delete_CostScenario_22123", driver, e);
			fail(e.getMessage());
		}
	}

	public void highlightColumnsToDisplayColumn(String column) throws InterruptedException, Throwable {
		String columnPath = "//*[contains(@class,'glAccountsGrid')]/descendant::*[text()='" + column + "']";
		// Shilpa 02.09.2022 added dimension , scroll to element
		addDimension(1000, 1000);
		WebElement element = driver.findElement(By.xpath(columnPath));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
		doClick(columnPath);
		driver.manage().window().maximize();
		Thread.sleep(2000);
		waitForAjaxExtJs();
	}
}
