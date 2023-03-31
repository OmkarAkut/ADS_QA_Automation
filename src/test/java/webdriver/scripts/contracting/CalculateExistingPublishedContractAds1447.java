package webdriver.scripts.contracting;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CalculateExistingPublishedContractAds1447 extends CalculationHelper {

  static final String modelName = "v10 REGRESSION Published Contract";
  static final String status = "Completed";
  static final String logStatus = "Completed";
  static final String recordsProcessed = "1470";
  static final String recordsPending = "0";
  static final String totalRecords = "1470";
  final String expectedLogViewTitle = modelName + "\\Contracting\\Published Contract Calculation";
  String logDetailTotalEfrs = "Total EFRs to be processed: 1470";
  String[] logViewDetails = {
          "Number of Encounter Financial Records to Process: 1470",
          "Non-Critical Warnings: 433",
          "Encounter Financial Records Calculated to Zero: 218"
  };
  private static ModelLibraryMap modelMap;

  /** Regression: Automated test script for ADS-1447, ADS-6782,ADS-6085 **/

  @BeforeClass
  public static void setupScript() throws Exception,Throwable {
	  ExtentReport.reportCreate("CalculateExistingPublishedContractAds1447", "webdriver.scripts.contracting", "CalculateExistingPublishedContractAds1447");
    try {
		modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
		Login.loginUser("ContractAnalyst1");
		goToPage("Contract Models");
		waitForSpinnerToEnd();
		Thread.sleep(4000);
		doClick(CostingMap.getContractingName);
//		doClick(CostingMap.getContractingName());
		doClick(CostingMap.getContractingAutomationName);
		doClick(CostingMap.getContractingAutomationRegressName);
		Thread.sleep(4000);
		filterAndSelectContractModelFromContractModelLibrary(modelName);
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
	ExtentReport.logFail("FAIL", "setupScript", driver, e);
	fail(e.getMessage());
	}
  }

  @Test
  public void test01RunPublishedContractAndVerifyStatusPageDetailsAfterCompleted() throws Throwable, InterruptedException {
    try {
		waitForSpinnerToEnd();
		waitForAjaxExtJs();
		modelMap.getModelLibraryContractingButtonCalculate().click();
		waitForCalculationToEnd(1000);
		driver.findElement(By.xpath("//button/span[text()='Refresh']")).click();
		waitForPresenceOfElement("//div[2]/div/div[4]/div/table/tbody/tr[2]/td[17]/div");  //total records xpath
		Thread.sleep(200);
		assertTrue(getCalculationStatusMyStatusFirstRowStatusCellText().contains(status));
		assertTrue(getCalculationStatusMyStatusFirstRowLogStatusCellText().contains(logStatus));
		assertTrue(getCalculationStatusMyStatusFirstRowRecordsProcessedCellText().contains(recordsProcessed));
		assertTrue(getCalculationStatusMyStatusFirstRowRecordsPendingCellText().contains(recordsPending));
		assertTrue(getCalculationStatusMyStatusFirstRowTotalRecordsCellText().contains(totalRecords));
		ExtentReport.logPass("PASS", "test01RunPublishedContractAndVerifyStatusPageDetailsAfterCompleted");
    	} catch (Exception|AssertionError e) {
	ExtentReport.logFail("FAIL", "test01RunPublishedContractAndVerifyStatusPageDetailsAfterCompleted", driver, e);
		fail(e.getMessage());
    	}
  }

  @Test
  public void test02OpenViewDialogAndAssertTotalEfrs() throws InterruptedException,Throwable {
	try {
		Thread.sleep(2000);
		calculationStatusPageOpenViewDialog();
		waitForPresenceOfElement("//div[4]/div/div/div[2]/div/div/div[4][contains(@id, 'tbtext')]");
		assertViewLogTitle(expectedLogViewTitle);
   waitForAjaxExtJs();//Shilpa 07.09.2022 method added to wait loading
		confirmCalculationStatusDetailsContains(logDetailTotalEfrs);
		ExtentReport.logPass("PASS", "test02OpenViewDialogAndAssertTotalEfrs");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test02OpenViewDialogAndAssertTotalEfrs", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test03OnLastPageOfViewDialogAssertLogDetails() throws Throwable {
    try {
		clickLastPageIconOnCalculationStatusViewLog();
		waitForSpinnerToEnd();
		waitForPresenceOfElement("//*[text() = 'CALCULATION SUMMARY']");
		confirmCalculationStatusDetailsContains(logViewDetails);
		closeViewDialog();
		ExtentReport.logPass("PADD", "test03OnLastPageOfViewDialogAssertLogDetails");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test03OnLastPageOfViewDialogAssertLogDetails", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test04DeleteCalculationStatusPageF() throws InterruptedException,Throwable {
    try {
		deleteCalculationStatusMyStatusPageFirstRow();
		ExtentReport.logPass("PASS", "test04DeleteCalculationStatusPageF");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test04DeleteCalculationStatusPageF", driver, e);
		fail(e.getMessage());
	}
  }
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
