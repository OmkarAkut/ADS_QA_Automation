package Regression;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.scripts.regression.generalcalculations.GeneralCalculationsData;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GeneralCalculationsRunContractualAllowancesScenarioAds2163 extends CalculationHelper {

	private GeneralCalculationsData data = new GeneralCalculationsData();
	String[] allowanceScenarios = {"v98ADS42SCENARIO1", "v98ADS42SCENARIO2", "v98ADS42SCENARIO3"};


	/**
	 * Automated script for test ticket ADS-2163.
	 * Updated: 3-26-2020.
	 */
	/** Regression Test ADS-6104**/
	@BeforeClass
	public static void setupScript() throws Exception,Throwable {
		ExtentReport.reportCreate("GeneralCalculationsRunContractualAllowancesScenarioAds2163", "webdriver.scripts.regression.generalcalculations", "GeneralCalculationsRunContractualAllowancesScenarioAds2163");
		try {
			System.out.println(
					"Test Class: " + GeneralCalculationsRunContractualAllowancesScenarioAds2163.class.getSimpleName()
					);
			Login.loginUser("ContractAnalyst1");
			goToPage("Contractual Allowance Export");
			waitForSpinnerToEnd();
			ExtentReport.logPass("PASS", "test01ExportContractualAllowanceScenarios");

		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ExportContractualAllowanceScenarios", driver, e);
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void teardownScript() throws InterruptedException {
		//doClosePageOnLowerBar("Calculation Status");
		ExtentReport.report.flush();
	}
//ADS-6104 only test01 db verification is ignored
	@Test
	public void test01ExportContractualAllowanceScenarios()
			throws InterruptedException,Throwable {
		try {
//			doClick(driver.findElement(By.xpath("//span[@class='x-btn-icon pagging-tbar-last-button']")));
			doClick(driver.findElement(By.xpath("//span[@class='x-btn-icon-el x-btn-icon-el-plain-toolbar-small pagging-tbar-last-button ']")));
			waitForDisplayedSpinnerToEnd();
			for (String scenario : allowanceScenarios) {
				waitForAjaxExtJs();
				tableDoubleClickCellFirstColumn(scenario);
//				doClick(driver.findElement(By.xpath("(//button/span[text()='Export'])[2]")));
				doClick(driver.findElement(By.xpath("//div[text()='Contractual Allowance']//following::span[text()='Export']")));
				waitForSpinnerToEnd();
				driverDelay(2000);
//				calculationStatusPageOpenViewDialog();
//				waitForCalculationToEndAndVerifyViewLogContainsOnViewDialogAndCloseDialog("Selection Criteria:");
				waitForCalculationToEndAndVerifySummaryDetailsStringOnDialogAndCloseDialog(
						"Selection Criteria:");//Shilpa 20.09.2022 added assert value
				assertCalcStatusColumnForFirstRowOfStatusTableIsCompleted();
				deleteMyCalculationStatusFirstRow();
				//Shilpa 20.09.2022 added below lines to complete the tc
				doClosePageOnLowerBar("Calculation Status");
//				driver.findElement(By.xpath("//button/span[text()='Cancel & Close']")).click();
				doClick("//span[text()='Cancel & Close']");
				waitForSpinnerToEnd();
				ExtentReport.logPass("PASS", "test01ExportContractualAllowanceScenarios");

			}
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ExportContractualAllowanceScenarios", driver, e);
			fail(e.getMessage());
		}
	}

	//  @Ignore
//	@Test
	public void test02AssertContractualAllowanceScenariosOnDbServer()
			throws ClassNotFoundException {
		calculationsAssertDbRowCount(
				GeneralCalculationsData.getMedicalServiceAssignmentSql,
				"greater than",
				0
				);
	}

	private void assertCalcStatusColumnForFirstRowOfStatusTableIsCompleted() {
//		assertTrue(
//				driver.findElement(By.xpath("//table/tbody/tr[2]/td[contains(@class,'x-grid-cell')]/div[text()='Completed']"))
//				.isDisplayed()
//				);
		assertTrue(
				driver.findElement(By.xpath("(//table/tbody/tr[1]/td[contains(@class,'x-grid-cell')]/div[text()='Completed'])[5]"))
				.isDisplayed()
				);
	}
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
