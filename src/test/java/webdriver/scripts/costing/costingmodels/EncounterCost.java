package webdriver.scripts.costing.costingmodels;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.DoHelper;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.GeneralElementsMap;
import webdriver.maps.mapbuilder.BuildMap;

public class EncounterCost extends UcqcHelper {
	static GeneralElementsMap generalMap;
	static CostingMap costing;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String costModel = "BC COST MODEL";
	static String costModelName = "Model" + currentDateTime;
	private static CostingMap selectColumn;
	static String dischargeDateFrom = "01/01/1800";
	static String dischargeDateTo = "01/01/1800";
	static String postingDateFrom = "04/01/2012";
	static String postingDateTo = "03/31/2013";
	String[] columnsToSelect = { "1S1 Office ", "1S2 Clinic " ,"1S3 Hospital "};
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("Encounter", "webdriver.scripts.costing.costingmodels", "Encounter");

		try {
			selectColumn = BuildMap.getInstance(driver, CostingMap.class);
			System.out.println("Test Class: " + EncounterCost.class.getSimpleName());
			Login.loginUser("AutomationTesterAdmin");
//			waitForDisplayedSpinnerToEnd();
			goToPage("costing models");
			waitForAjaxExtJs();
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}

	}

	@Test
	public void test01AssertEncounterCostCalculationScenario() throws Throwable {
		try {
			waitForAjaxExtJs();
			assertPageInformation("Costing Model Library");
			waitForElementToBeVisible(CostingMap.getCostingTreeItem());
			doClick(CostingMap.getCostingTreeItem());
			waitUntilTreeOptionIsClickable("BC");
			doClickTreeItem("BC");
			waitForAjaxExtJs();
			doSearchForModel(costModel);
			driverDelay();
			tableDoubleClickCellFirstColumn(costModel);
			waitForSpinnerToEnd();
			waitForPresenceOfElement("//div[contains(@id,'costing_modelcosts_tabId-bodyWrap')]//div[text()='BC COST MODEL']");
//			doClickTreeItem("COST PROCESS");
			doClickTreeItem("Assign Costs to Encounters");
//			waitUntilTreeOptionIsClickable("ENCOUNTER COST");
//			waitUntilTreeOptionIsClickable("Encounter Cost Calculation Scenarios");

			doClickTreeItemUsingXpathLocator("//span[text()='Encounter Cost Calculation Scenarios']/..");
//			doClick(CostingMap.getCalculateEncounterCostItem());
			doClick(CostingMap.getEncounterNewBtn());
			waitForElementToBeVisible(CostingMap.getEncounterPageText());
			// Encounter Cost Calculation Scenario page is shown
			assertTextIsDisplayed("Encounter Cost Calculation Scenario");
			ExtentReport.logPass("PASS", "test01AssertCostModelPageHeader");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01AssertCostModelPageHeader", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test02EnterEncounterCostModelScenarioDetails() throws Throwable {
		try {
			DoHelper.doEnterModelName(costModelName);
			Thread.sleep(400);
			doClick(selectColumn.getSelctCostModelScenariosInEvaluationOrder());
//			highlightColumnsToDisplayColumn("BC REGRESSION CMS");
			//Shilpa update test data
			highlightColumnsToDisplayColumn("*USE MHC FY05 Total Cost Scenario");

			doClick(selectColumn.getcostModelButtonColumnsToDisplayModalSelect());
			Thread.sleep(500);
			doClick(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply());
			Thread.sleep(500);
//			doDropdownSelectUsingOptionText(selectColumn.getCostModelScenariosinEvaluationOrderFrom(),
//					selectColumn.getCostModelScenariosinEvaluationOrderFromList(), "Apr 2012");
			doDropdownSelectUsingOptionText(selectColumn.getCostModelScenariosinEvaluationOrderFrom(),
					selectColumn.getCostModelScenariosinEvaluationOrderFromList(), "Apr 2004");
//			doDropdownSelectUsingOptionText(selectColumn.getCostModelScenariosinEvaluationOrderTo(),
//					selectColumn.getCostModelScenariosinEvaluationOrderToList(), "Mar 2013");
			doDropdownSelectUsingOptionText(selectColumn.getCostModelScenariosinEvaluationOrderTo(),
					selectColumn.getCostModelScenariosinEvaluationOrderToList(), "Mar 2005");
//			doDropdownSelectUsingOptionText(selectColumn.getCostModelScenariosinEvaluationOrderAssignedCost(),
//					selectColumn.getCostModelScenariosinEvaluationOrderAssignedCostList(), "10 : BC Destination");
			doDropdownSelectUsingOptionText(selectColumn.getCostModelScenariosinEvaluationOrderAssignedCost(),
					selectColumn.getCostModelScenariosinEvaluationOrderAssignedCostList(), "12 : Actual Cost Destination");
			doClick(selectColumn.getCostModelScenariosinEvaluationOrderEncounterSelect());
//
//			doClick(selectColumn.getCostModelScenariosinEvaluationOrderEncounterSelectAll()); //Selecting all entities lead to have more records for calculation so commented out

			ContractModelsHelper.selectMultipleColumnsToDisplay(columnsToSelect);
			doClick(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply());

			doClick(selectColumn.getCostModelScenariosinEvaluationOrderEntitiesSelect());

			doClick(selectColumn.getCostModelScenariosinEvaluationOrderEncounterSelectAll());

			doClick(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply());
			doClick("//span[text()='Continue']");
			doClick(selectColumn.getCostModelScenariosinEvaluationOrderAdmissionCheck());

			doClick(selectColumn.getCostModelScenariosinEvaluationOrderDischargeCheck());
			scrollToView(driver.findElement(By.name("admStartDate")));
//			driver.findElement(By.name("admStartDate")).click();

			driver.findElement(By.name("dsChgStartDate")).sendKeys(dischargeDateFrom);

			driver.findElement(By.name("dsChgEndDate")).sendKeys(dischargeDateFrom);

			doClick(selectColumn.getCostModelScenariosinEvaluationOrderPostingCheck());

			driver.findElement(By.name("postStartDate")).sendKeys(postingDateFrom);

			driver.findElement(By.name("postEndDate")).sendKeys(postingDateTo);
			scrollToView(driver.findElement(By.name("hostLocation")));
			driver.findElement(By.name("hostLocation")).click();
			waitForElementToBeVisible(CostingMap.getEncounterFileLocDropdown());
			doClick(CostingMap.getEncounterFileLocDropdown());
			Thread.sleep(300);
			driver.findElement(By.name("logLocation")).sendKeys("Test");
			doClick(CostingMap.getEncounterSave());
			doClick("//span[text()='Continue']");
			waitForDisplayedSpinnerToEnd();
			doClick(CostingMap.getEncounterCalculateBtn());
//			doClick("//span[text()='Save & Continue']");
			waitForSpinnerToEnd();
			CalculationHelper.waitForFirstRowCalculationBarToReach100Percent();
			ExtentReport.logPass("PASS", "test01AssertCostModelPageHeader");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01AssertCostModelPageHeader", driver, e);
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

	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
