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
import webdriver.maps.SystemMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ValidateOpenTaskList extends GoHelper {
	static CostingMap costing;
	static ContractingMap modelMap;
	static String costModel="Actual Cost Model";
	static SystemMaintenanceMap systemMap;
/** Automates test ticket ADS-6642,ADS-6668*/
	static saveSystemSettings settings=new saveSystemSettings();
	@BeforeClass
	public static void setupScript() throws Exception,Throwable {
		ExtentReport.reportCreate("ValidateOpenTaskList", "webdriver.scripts.costing", "ValidateOpenTaskList");
		try {
			costing = BuildMap.getInstance(driver, CostingMap.class);
			modelMap=BuildMap.getInstance(driver, ContractingMap.class);
			systemMap=BuildMap.getInstance(driver, SystemMaintenanceMap.class);
			Login.loginUser("AutomationTesterAdmin");
			settings.saveCustomSettings("Use Custom", "Costing Models");
//			goToPage("Costing Models");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-6642
	@Test
	public void test01ExpandCosting_ADS_6642() throws Throwable {
		try {
			ContractModelsHelper.doClickTreeData("Costing");
			waitForElementPresence("//span[text()='Tinesha']");
			ContractModelsHelper.scrollToView("//span[text()='Tinesha']");
			ContractModelsHelper.doClickTreeData("Tinesha");
			driverDelay(3000);
			doClick("//span[text()='Actual Cost Method']");
			ExtentReport.logPass("PASS", "test01ExpandCosting");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ExpandCosting", driver, e);
			fail(e.getMessage());
		}
		
	}
	//ADS-6642
	@Test
	public void test02OpenTaskList_ADS_6642() throws Throwable {
		try {
			doSearchForModel(costModel);
			tableClickCellFirstColumn(costModel);
			doClickButton("Open Task List");
			waitForPresenceOfElement("//div[contains(@id, 'header') and text()='Actual Cost Model']");
			assertThatString(driver.findElement(By.xpath("//div[contains(@id, 'header') and text()='Actual Cost Model']")), costModel, printout);
			ExtentReport.logPass("PASS", "test02OpenTaskList");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02OpenTaskList", driver, e);
			fail(e.getMessage());
		}
	
	}
	//ADS-6668
	@Test
	public void test03ValidateTaskList_ADS_6668() throws Throwable {
		try {
			assertTextIsDisplayedTaskFolder("CM Test");
			assertTextIsDisplayedTaskFolder("FISCAL YEAR SETUP");
			assertTextIsDisplayedTaskFolder("COST PROCESS");
			doClick("//div[contains(@id,'taskfolder')]//following::table[contains(@id,'treeview')]//span[text()='CM Test']");
//			doClickTreeItem("CM Test");
			waitForMainPageTitle("All Masters");
			assertTextIsDisplayedTaskFolder("All Masters");
//			assertTextIsDisplayedTaskFolder("Cost Scnenarios");
			assertTextIsDisplayedTaskFolder("Groupings");
			assertTextIsDisplayedTaskFolder("Miscellaneous");
			doClick("//div[contains(@id,'taskfolder')]//following::table[contains(@id,'treeview')]//span[text()='CM Test']");

			driverDelay(300);
			doClick("//div[contains(@id,'taskfolder')]//following::table[contains(@id,'treeview')]//span[text()='FISCAL YEAR SETUP']");

//			doClickTreeItem("FISCAL YEAR SETUP");
			waitForMainPageTitle("Create new Cost Model");
			assertTextIsDisplayedTaskFolder("Create new Cost Model");
			assertTextIsDisplayedTaskFolder("Creat new Time Period");
			assertTextIsDisplayedTaskFolder("Create new Price List");
			assertTextIsDisplayedTaskFolder("Create new Price List Calculation Scenario");
			assertTextIsDisplayedTaskFolder("Create/Update Activity Statistic Master");
			assertTextIsDisplayedTaskFolder("Create/Update Ad Hoc Statistic Master");
			assertTextIsDisplayedTaskFolder("Create/Update GL Statistic Master");
			assertTextIsDisplayedTaskFolder("Create new Activity Volume Data Scenario");
			assertTextIsDisplayedTaskFolder("Create new Activity Volume Data Calculation Scenario");
			assertTextIsDisplayedTaskFolder("Create new Activity Volume Data Calculation Scenario");
			assertTextIsDisplayedTaskFolder("Create new Actual GL Data Scenario");
			assertTextIsDisplayedTaskFolder("Create new Reclass GL Data Scenario(s)");
			assertTextIsDisplayedTaskFolder("Create new Statistic Data Scenario(s)");
			assertTextIsDisplayedTaskFolder("Import Ad Hoc Statistics into Statistic Data Scenario(s)");
			assertTextIsDisplayedTaskFolder("Create new Statistic Data Calculation Scenario(s)");
			assertTextIsDisplayedTaskFolder("Create/Update GL Adjustment Master");
			assertTextIsDisplayedTaskFolder("Create/Update GL Reclassification Master");
			assertTextIsDisplayedTaskFolder("Create new GL Adjustment and Reclassification Calculation Scenario(s)");
			assertTextIsDisplayedTaskFolder("Create/Update Cost Component Masters");
			assertTextIsDisplayedTaskFolder("Create/Update Cost Component Variability Masters");
			assertTextIsDisplayedTaskFolder("Create/Update Cost Method Masters");
			assertTextIsDisplayedTaskFolder("Complete General Information");
			assertTextIsDisplayedTaskFolder("Create/Update RVU Calculation Scenarios");
			assertTextIsDisplayedTaskFolder("Add/Update RVUs");
			assertTextIsDisplayedTaskFolder("Create new Direct Cost Model Calculation Scenario");
			assertTextIsDisplayedTaskFolder("Create new Total Cost Model Calculation Scenario");
			assertTextIsDisplayedTaskFolder("Review/Update Cost Component Group Masters");
			ContractModelsHelper.scrollToView("//*[text()='FISCAL YEAR SETUP']");
			doClick("//div[contains(@id,'taskfolder')]//following::table[contains(@id,'treeview')]//span[text()='FISCAL YEAR SETUP']");

			driverDelay(200);
			doClick("//div[contains(@id,'taskfolder')]//following::table[contains(@id,'treeview')]//span[text()='COST PROCESS']");

//			doClickTreeItem("COST PROCESS");
			assertTextIsDisplayedTaskFolder("LOAD COST DATA");
			assertTextIsDisplayedTaskFolder("STRUCTURE");
			assertTextIsDisplayedTaskFolder("DIRECT COST");
			assertTextIsDisplayedTaskFolder("TOTAL COST");
			assertTextIsDisplayedTaskFolder("ENCOUNTER COST");
			ExtentReport.logPass("PASS", "test03ValidateTaskList");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ValidateTaskList", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		doClosePageOnLowerBar(costModel);
		doClosePageOnLowerBar("Costing Models");
		settings.revertCustomSettings();
		ExtentReport.report.flush();

	}
}
