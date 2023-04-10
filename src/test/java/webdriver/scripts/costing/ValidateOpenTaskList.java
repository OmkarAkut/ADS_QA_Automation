package webdriver.scripts.costing;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ValidateOpenTaskList extends GoHelper {
	static CostingMap costing;
	static ContractingMap modelMap;
	static String costModel="Actual Cost Model";
/** Automates test ticket ADS-6642,ADS-6668*/
	
	@BeforeClass
	public static void setupScript() throws Exception,Throwable {
		ExtentReport.reportCreate("ValidateOpenTaskList", "webdriver.scripts.costing", "ValidateOpenTaskList");
		try {
			costing = BuildMap.getInstance(driver, CostingMap.class);
			modelMap=BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Costing Models");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test01ExpandCosting() throws Throwable {
		try {
			ContractModelsHelper.doClickTreeData("Costing");
			driverDelay(3000);
			ContractModelsHelper.scrollToView("//div[text()='Tinesha']");
			ContractModelsHelper.doClickTreeData("Tinesha");
			driverDelay(3000);
			doClick("//div[text()='Actual Cost Method']");
			ExtentReport.logPass("PASS", "test01ExpandCosting");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ExpandCosting", driver, e);
			fail(e.getMessage());
		}
		
	}
	
	@Test
	public void test02OpenTaskList() throws Throwable {
		try {
			doSearchForModel(costModel);
			tableClickCellFirstColumn(costModel);
			doClickButton("Open Task List");
			waitForPageTitle(costModel);
			assertThatString(driver.findElement(By.xpath("//span[contains(@class, 'header') and text()='"+costModel+"']")), costModel, printout);
			ExtentReport.logPass("PASS", "test02OpenTaskList");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02OpenTaskList", driver, e);
			fail(e.getMessage());
		}
	
	}
	@Test
	public void test03ValidateTaskList() throws Throwable {
		try {
			assertTextIsDisplayed("CM Test");
			assertTextIsDisplayed("FISCAL YEAR SETUP");
			assertTextIsDisplayed("COST PROCESS");
			doClickTreeItem("CM Test");
			waitForMainPageTitle("All Masters");
			assertTextIsDisplayed("All Masters");
			assertTextIsDisplayed("Cost Scnenarios");
			assertTextIsDisplayed("Groupings");
			assertTextIsDisplayed("Miscellaneous");
			doClickTreeItem("CM Test");
			driverDelay(300);
			doClickTreeItem("FISCAL YEAR SETUP");
			waitForMainPageTitle("Create new Cost Model");
			assertTextIsDisplayed("Create new Cost Model");
			assertTextIsDisplayed("Creat new Time Period");
			assertTextIsDisplayed("Create new Price List");
			assertTextIsDisplayed("Create new Price List Calculation Scenario");
			assertTextIsDisplayed("Create/Update Activity Statistic Master");
			assertTextIsDisplayed("Create/Update Ad Hoc Statistic Master");
			assertTextIsDisplayed("Create/Update GL Statistic Master");
			assertTextIsDisplayed("Create new Activity Volume Data Scenario");
			assertTextIsDisplayed("Create new Activity Volume Data Calculation Scenario");
			assertTextIsDisplayed("Create new Activity Volume Data Calculation Scenario");
			assertTextIsDisplayed("Create new Actual GL Data Scenario");
			assertTextIsDisplayed("Create new Reclass GL Data Scenario(s)");
			assertTextIsDisplayed("Create new Statistic Data Scenario(s)");
			assertTextIsDisplayed("Import Ad Hoc Statistics into Statistic Data Scenario(s)");
			assertTextIsDisplayed("Create new Statistic Data Calculation Scenario(s)");
			assertTextIsDisplayed("Create/Update GL Adjustment Master");
			assertTextIsDisplayed("Create/Update GL Reclassification Master");
			assertTextIsDisplayed("Create new GL Adjustment and Reclassification Calculation Scenario(s)");
			assertTextIsDisplayed("Create/Update Cost Component Masters");
			assertTextIsDisplayed("Create/Update Cost Component Variability Masters");
			assertTextIsDisplayed("Create/Update Cost Method Masters");
			assertTextIsDisplayed("Complete General Information");
			assertTextIsDisplayed("Create/Update RVU Calculation Scenarios");
			assertTextIsDisplayed("Add/Update RVUs");
			assertTextIsDisplayed("Create new Direct Cost Model Calculation Scenario");
			assertTextIsDisplayed("Create new Total Cost Model Calculation Scenario");
			assertTextIsDisplayed("Review/Update Cost Component Group Masters");
			ContractModelsHelper.scrollToView("//*[text()='FISCAL YEAR SETUP']");
			doClickTreeItem("FISCAL YEAR SETUP");
			driverDelay(200);
			doClickTreeItem("COST PROCESS");
			assertTextIsDisplayed("LOAD COST DATA");
			assertTextIsDisplayed("STRUCTURE");
			assertTextIsDisplayed("DIRECT COST");
			assertTextIsDisplayed("TOTAL COST");
			assertTextIsDisplayed("ENCOUNTER COST");
			ExtentReport.logPass("PASS", "test03ValidateTaskList");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateTaskList", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		doClosePageOnLowerBar(costModel);
		doClosePageOnLowerBar("Model Library");
		ExtentReport.report.flush();

	}
}
