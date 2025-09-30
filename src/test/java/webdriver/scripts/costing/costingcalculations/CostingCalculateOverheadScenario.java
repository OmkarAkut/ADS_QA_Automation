package webdriver.scripts.costing.costingcalculations;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CostingCalculateOverheadScenario extends CalculationHelper {
	JavascriptExecutor executor = (JavascriptExecutor) driver;
	static String viewLogTitle = "Overhead Model Scenario Calculation";
//	static String CalculationScenario = "v11.2 REGRESSION OH Scenario";
	static String CalculationScenario = "V11.2 Overhead Scenario";
	static String[] filter = { "Name", "Is", "Equal To", CalculationScenario };
	static String currentDateTime = new SimpleDateFormat("HH.mm.ss").format(new java.util.Date());
	static ContractingMap modelMap;
	static CostingMap costing;
	static String scenarioName;
	static String[] filterCalcScenario ;
	/** Regression: Test script for ADS-5991 */
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("CostingCalculateOverheadScenario", "webdriver.scripts.costing.costingcalculations",
				"CostingCalculateOverheadScenario");
		try {
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);
			costing=BuildMap.getInstance(driver, CostingMap.class);
			System.out.println("Test Class: " + CostingCalculateOverheadScenario.class.getSimpleName());
			Login.loginUser("AutomationTester1");
			goToPage("Costing Models");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}

//ADS-5991, covered only for Costing , need to add steps[from step16] for Report library Costing for overhead cost model
	@Test
	public void test01AdsLoginLogout_ADS_5991() throws Throwable {
		try {
			doSearchForModel("2005 Overhead Allocation-Test2023");
			tableDoubleClickCellFirstColumn("2005 Overhead Allocation-Test2023");
			driverDelay(4000);
			doClickTreeItem("Allocate Overhead");
			driverDelay(4000);
			doClickTreeItem("Overhead Model Calculation Scenarios");
			// Omkar (19/7/2022) : value v102 REGRESSION OH Scenario is not found hence
			// changing it to v102 REGRESSION OH Calc Scenario
			// tableDoubleClickCellFirstColumn("v102 REGRESSION OH Scenario");
//      tableDoubleClickCellFirstColumn("v102 REGRESSION OH Calc Scenario");
//      tableDoubleClickCellFirstColumn("OH calculation June 2004");

			doClick(CostingMap.getOverheadModelFilterButton());
			doFilterCreate(filter);
			// Omkar (19/7/2022) : The below xpath is no more valid
			// doClick(driver.findElement(By.xpath("//button/span[text()='Calculate']")));
			// SHILPA update xpath for 11.2 on 12.01.2023
			scenarioName=CalculationScenario + currentDateTime;
			ContractModelsHelper.keyInValues(driver.findElement(By.name("name")),
					CalculationScenario + currentDateTime);
			doClick(modelMap.getContractModelButtonSaveAs());
			doClick(driver.findElement(By.xpath(
					"//div[contains(@id,'overheadmodelscenarioform')]//following::span[contains(@id,'button')][text()='Calculate']")));
			waitForFirstRowCalculationBarToReach100Percent();
			calculationStatusPageOpenViewDialog();
			assertViewLogTitle(viewLogTitle);
			checkForRecordsProcessed("Inserting 1 OH Received records");
//			checkForRecordsProcessed("Inserting 12 OH Received records");// Shilpa : 14.8.2025 updated data for 11.3
			confirmCalculationStatusDetailsContains("Process Completed");
			closeViewDialog();
			waitForSpinnerToEnd();
			deleteMyCalculationStatusFirstRow();
			doClosePageOnLowerBar("Calculation Status");
			waitForAjaxExtJs();
			Thread.sleep(500);
			doClick(driver.findElement(By.xpath(
					"//div[contains(@id,'overheadmodelscenarioform')]//following::span[contains(@id,'button')][text()='Save & Close']")));
			//Shilpa: Update for 11.2 : deleting created records, to avoid pile up of records 17.03.2025
			doClick(CostingMap.getOverheadModelFilterButton());
			String[] filterCalcScenario = { "Name", "Is", "Equal To", scenarioName };
			doFilterCreate(filterCalcScenario);
			doClick(CostingMap.getOverheadModelDeleteButton());
			doClick(CostingMap.getWarningDeleteButton());
			doClosePageOnLowerBar("2005 Overhead...");
			doClosePageOnLowerBar("Costing Models");
			waitForAjaxExtJs();
			ExtentReport.logPass("PASS", "testAdsLoginLogout");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "testAdsLoginLogout", driver, e);
			fail(e.getMessage());

		}
	}

	// Shilpa 27.07.2022, some wait is added and updated to click element inside
	// frame
	@Test // (Omkar 24/5/22 : Need to review which objectr needs to be clicked in tree in
			// step 2.The test is failing saying that object is not clickable)
	public void test02AssertReportLibrary_ADS_5991() throws InterruptedException, Throwable {
		try {
			goToPage("Report Library");
			waitForSpinnerToEnd();
			Thread.sleep(9000);
			doClickTreeItem("Costing");
			Thread.sleep(3000);
			executor.executeScript("arguments[0].click();",
					driver.findElement(By.xpath("//*[@title='Overhead Received']")));

//    doClick(driver.findElement(By.xpath("//*[@title='Overhead Received']")));

			// enter criteria
			// Shilpa: 27.07.2022 , added some wait
			Thread.sleep(5000);
			doClick(driver.findElement(By.xpath("//div[@class='gwt-Hyperlink']/a/u[text()='* TB MH FY05 Overhead']")));
			waitForElementPresence("//div[text()='" + CalculationScenario + "']");
			doClick("//div[text()='" + CalculationScenario + "']");
			ContractModelsHelper.scrollToView("//button[text()='OK']");

			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//button[text()='OK']")));
//		doClick("//button[text()='OK']");

			driverDelay();
			doClick("//button[text()='Run']");
			try {
				doClick("//button[text()='OK']");//shilpa added line for 11.2 on 10.24.2024
			} catch (Exception e) {
				
			}

			waitForOverheadReceivedReport();
			ExtentReport.logPass("PASS", "testAssertReportLibrary");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "testAssertReportLibrary", driver, e);
			fail(e.getMessage());
		}
		// Thread.sleep(4000);
		// doClick("//div[@class='gwt-Hyperlink']/a/u[text()='* TB MH FY05 Overhead']");

	}

	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
