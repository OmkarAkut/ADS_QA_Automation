package webdriver.templates;

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
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.scripts.regression.ipps2021calculations.Ipps2021Data;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IppsAndOppsCalculations extends CalculationHelper {

  static DataMaintenanceMap dmMap;
  static final String batchName = "v104 REGRESSION 2021 IPPS: Transfers";//Shilpa. 19.08.2022 updated the name from v104 REGRESSION 2021 IPPS Transfers to v104 REGRESSION 2021 IPPS: Transfers 
  final String expectedLogView = batchName + "\\Contracting\\Contract Batch";
  private Ipps2021Data data = new Ipps2021Data();

  /** Regression: Test script for ADS-3635 - 2021 IPPS: Transfers.
   *  Updated: 12/8/2020. */
  @BeforeClass
  public static void setupScript() throws Exception,Throwable {
	ExtentReport.reportCreate("IppsAndOppsCalculations", "webdriver.templates", "IppsAndOppsCalculations");
    try {
		dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
		System.out.println("Test Class: " + IppsAndOppsCalculations.class.getSimpleName());
		Login.loginUser("ContractAnalyst1");
		goToMaintainDataPageAndSelectContractBatch(batchName);
		ExtentReport.logPass("PASS", "setupScript");
	}  catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test01ClickResetAndVerifyDatabaseValuesAreNull() throws ClassNotFoundException, InterruptedException,Throwable {
    try {
		waitForAjaxExtJs();
		waitForPresenceOfElement("//*[text()='Refresh']");
		doClick(driver.findElement(By.xpath("//*[text()='Refresh']")));
		waitForSpinnerToEnd();
		waitForFirstRowCalculationBarToReach100Percent(2000);
		deleteMyCalculationStatusFirstRow();
		getStringDataFromDatabaseAndAssertValuesAreNull(
		        data.sqlQueryIpps2021PsychComorbidity,
		        2
		);
		doClosePageOnLowerBar("Calculation Status");
		ExtentReport.logPass("PASS", "test01ClickResetAndVerifyDatabaseValuesAreNull");
	}  catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test01ClickResetAndVerifyDatabaseValuesAreNull", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test01ClickCalculateButtonAndAssertCalculationSummaryDetailsMatchExpected() throws Throwable {
    try {
      doClick(dmMap.getMaintainDataPageButtonCalculate());
      waitForCalculationToEndAndVerifyViewLogContainsOnViewDialogAndCloseDialog(
              expectedLogView
      );
      deleteMyCalculationStatusFirstRow();
      ExtentReport.logPass("PASS", "test01ClickCalculateButtonAndAssertCalculationSummaryDetailsMatchExpected");
	}  catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test01ClickCalculateButtonAndAssertCalculationSummaryDetailsMatchExpected", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test02QueryDatabaseAndAssertValuesAreCorrectForIpps2021TransfersA()
          throws ClassNotFoundException,Throwable {
    try {
		getDataFromDatabaseAndAssertExpectedValues(
		        data.sqlQueryIpps2021TransfersA,
		        data.expectedValuesIpps2021TransfersA
		);
		 ExtentReport.logPass("PASS", "test02QueryDatabaseAndAssertValuesAreCorrectForIpps2021TransfersA");
			}  catch (Exception|AssertionError e) {
				ExtentReport.logFail("FAIL", "test02QueryDatabaseAndAssertValuesAreCorrectForIpps2021TransfersA", driver, e);
				fail(e.getMessage());
			}
  }
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
