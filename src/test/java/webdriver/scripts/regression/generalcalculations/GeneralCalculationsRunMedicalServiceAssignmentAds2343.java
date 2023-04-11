package webdriver.scripts.regression.generalcalculations;

import static org.junit.Assert.fail;

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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GeneralCalculationsRunMedicalServiceAssignmentAds2343 extends CalculationHelper {

	static String viewLogTitleClear = "Clear Medical Service Assignment";
	static String viewLogTitleAssign = "Medical Service Assignment";
	final static String aTozPage = "Medical Service Assignments";
	final static String batch = "V10.2 REGRESSION Med Serv Assign";


	/** Regression: Test script for ADS-2343. Updated: 7-7-21. ,ADS-6100*/
	@BeforeClass
	public static void setupScript() throws Exception,Throwable {
		ExtentReport.reportCreate("GeneralCalculationsRunMedicalServiceAssignmentAds2343", "webdriver.scripts.regression.generalcalculations", "GeneralCalculationsRunMedicalServiceAssignmentAds2343");
		try {
			System.out.println(
					"Test Class: " + GeneralCalculationsRunMedicalServiceAssignmentAds2343.class.getSimpleName());
			Login.loginUser("ContractAnalyst1");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozPage);
			openMaintainDataBatch(batch);
			ExtentReport.logPass("PASS", "setupScript");
		}  catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}

	}

	@Test
	public void test01ClickClearResultsButtonAndVerifyCalculationStatusDetails()
			throws InterruptedException,Throwable {
		try {
			waitForAjaxExtJs();
			waitForPresenceOfElement("//button/span[text()='Clear Results']");
			doClick(getButton("Clear Results"));
			waitForSpinnerToEnd();
			waitForFirstRowCalculationBarToReach100Percent();
			driverDelay(4000);//Shilpa 19.09.2022 requires wait
			calculationStatusPageOpenViewDialog();
			driverDelay(2000);//Shilpa 19.09.2022 requires wait
			assertViewLogTitle(viewLogTitleClear);
			confirmCalculationStatusDetailsContains("Number of batches to process: 1");
			confirmCalculationStatusDetailsContains("Process Completed");
			closeViewDialog();
			deleteMyCalculationStatusFirstRow();
			ExtentReport.logPass("PASS", "test01ClickClearResultsButtonAndVerifyCalculationStatusDetails");
		}  catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ClickClearResultsButtonAndVerifyCalculationStatusDetails", driver, e);
			fail(e.getMessage());
		} finally {
			try {
				doClosePageOnLowerBar("Calculation Status");
			} catch (Exception|AssertionError e) {
				ExtentReport.logFail("FAIL", "test01ClickClearResultsButtonAndVerifyCalculationStatusDetails", driver, e);
				fail(e.getMessage());
			} 
		}
	}

	//  @Test
	//  public void test02VerifyDatabaseValueCountIsZero()
	//          throws ClassNotFoundException {
	//    calculationsAssertDbRowCount(
	//            GeneralCalculationsData.getMedicalServiceAssignmentSql,
	//            "equal to",
	//            0
	//    );
	//  }

	@Test
	public void test02ClickAssignButtonAndAssertCalculationStatusPageDetails()
			throws InterruptedException ,Throwable{
		try {
			waitForAjaxExtJs();
			waitForPresenceOfElement("//button/span[text()='Assign']");
			doClick(getButton("Assign"));
			waitForSpinnerToEnd();
			waitForFirstRowCalculationBarToReach100Percent(2000);
			driverDelay(4000);
			calculationStatusPageOpenViewDialog();
			driverDelay(3000);
			assertViewLogTitle(viewLogTitleAssign);
			driverDelay(4000);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",         	driver.findElement(By.xpath("//*[contains(text(),'Number of batches to process: 1')]"))
					);//Shilpa 19.09.2022 added this to make element visible
			driverDelay(4000);
			confirmCalculationStatusDetailsContains("Number of batches to process: 1");
			clickLastPageIconOnCalculationStatusViewLog();
			driverDelay(4000);
			confirmCalculationStatusDetailsContains("Process Completed");
			closeViewDialog();
			deleteMyCalculationStatusFirstRow();
			ExtentReport.logPass("PASS", "test02ClickAssignButtonAndAssertCalculationStatusPageDetails");
		}  catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ClickAssignButtonAndAssertCalculationStatusPageDetails", driver, e);
			fail(e.getMessage());
		} finally {
			try {
				doClosePageOnLowerBar("Calculation Status");
				waitForPresenceOfElement("//button/span[text()='Assign']");
				doClosePageOnLowerBar("Maintain Data");
			}catch (Exception|AssertionError e) {
				ExtentReport.logFail("FAIL", "test02ClickAssignButtonAndAssertCalculationStatusPageDetails", driver, e);
				fail(e.getMessage());
			} 
		}
	}

	//  @Test
	//  public void test03QueryDatabaseAndAssertValuesAreCorrectForMedicalServiceAssignments()
	//          throws ClassNotFoundException {
	//    if (testEnvironment.contains("echelon")) {
	//      calculationsAssertDbRowCount(
	//              GeneralCalculationsData.getMedicalServiceAssignmentSql,
	//              "equal",
	//              GeneralCalculationsData.getMedicalServiceAssignmentExpectedValueEchelon
	//      );
	//    } else {
	//      calculationsAssertDbRowCount(
	//              GeneralCalculationsData.getMedicalServiceAssignmentSql,
	//              "equal",
	//              GeneralCalculationsData.getMedicalServiceAssignmentExpectedValue
	//      );
	//    }
	//  }
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
