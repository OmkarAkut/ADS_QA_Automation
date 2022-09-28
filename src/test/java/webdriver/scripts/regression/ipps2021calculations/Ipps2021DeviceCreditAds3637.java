package webdriver.scripts.regression.ipps2021calculations;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Ipps2021DeviceCreditAds3637 extends CalculationHelper {

  static DataMaintenanceMap dmMap;
//  static final String batchName = getVersion() + " REGRESSION 2021 IPPS: Device Credit";
  static final String batchName = getVersion() + " REGRESSION 2021 IPPS: Transfers";
  final String expectedLogView = batchName + "\\Contracting\\Contract Batch";
  private Ipps2021Data data = new Ipps2021Data();

  /** Regression: Test script for ADS-3637 - 2021 IPPS: Device Credit. */
  @BeforeClass
  public static void setupScript() throws Exception,Throwable {
	  ExtentReport.reportCreate("Ipps2021DeviceCreditAds3637", "webdriver.scripts.regression.ipps2021calculations", "Ipps2021DeviceCreditAds3637");
    try {
		dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
		System.out.println("Test Class: " + Ipps2021DeviceCreditAds3637.class.getSimpleName());
		Login.loginUser("ContractAnalyst1");
		goToMaintainDataPageAndSelectContractBatch(batchName);
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
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
  	} catch (Exception|AssertionError e) {
  		ExtentReport.logFail("FAIL", "test01ClickCalculateButtonAndAssertCalculationSummaryDetailsMatchExpected", driver, e);
  		fail(e.getMessage());
  	}
  }

  @Test
  public void test02QueryDatabaseAndAssertValuesAreCorrectForIpps2021DeviceCreditA()
          throws ClassNotFoundException ,Throwable{
    try {
		getDataFromDatabaseAndAssertExpectedValues(
		        data.sqlQueryIpps2021DeviceCreditA,
		        data.expectedValuesIpps2021DeviceCreditA
		);
		ExtentReport.logPass("PASS", "test02QueryDatabaseAndAssertValuesAreCorrectForIpps2021DeviceCreditA");
  	} catch (Exception|AssertionError e) {
  		ExtentReport.logFail("FAIL", "test02QueryDatabaseAndAssertValuesAreCorrectForIpps2021DeviceCreditA", driver, e);
  		fail(e.getMessage());
  	}
  }

  @Test
  public void test03QueryDatabaseAndAssertValuesAreCorrectForIpps2021DeviceCreditB()
          throws ClassNotFoundException,Throwable {
    try {
		getDataFromDatabaseAndAssertExpectedValues(
		        data.sqlQueryIpps2021DeviceCreditB,
		        data.expectedValuesIpps2021DeviceCreditB
		);
		ExtentReport.logPass("PASS", "test03QueryDatabaseAndAssertValuesAreCorrectForIpps2021DeviceCreditB");
  	} catch (Exception|AssertionError e) {
  		ExtentReport.logFail("FAIL", "test03QueryDatabaseAndAssertValuesAreCorrectForIpps2021DeviceCreditB", driver, e);
  		fail(e.getMessage());
  	}
  }

  @Test
  public void test04QueryDatabaseAndAssertValuesAreCorrectForIpps2021DeviceCreditE()
          throws ClassNotFoundException,Throwable {
    try {
		getDataFromDatabaseAndAssertExpectedValues(
		        data.sqlQueryIpps2021DeviceCreditE,
		        data.expectedValuesIpps2021DeviceCreditE
		);
		ExtentReport.logPass("PASS", "test04QueryDatabaseAndAssertValuesAreCorrectForIpps2021DeviceCreditE");
  	} catch (Exception|AssertionError e) {
  		ExtentReport.logFail("FAIL", "test04QueryDatabaseAndAssertValuesAreCorrectForIpps2021DeviceCreditE", driver, e);
  		fail(e.getMessage());
  	}
  }

  @Test
  public void test05QueryDatabaseAndAssertValuesAreCorrectForIpps2021DeviceCreditF()
          throws ClassNotFoundException,Throwable {
    try {
		getDataFromDatabaseAndAssertExpectedValues(
		        data.sqlQueryIpps2021DeviceCreditF,
		        data.expectedValuesIpps2021DeviceCreditF
		);
		ExtentReport.logPass("PASS", "test05QueryDatabaseAndAssertValuesAreCorrectForIpps2021DeviceCreditF");
  	} catch (Exception|AssertionError e) {
  		ExtentReport.logFail("FAIL", "test05QueryDatabaseAndAssertValuesAreCorrectForIpps2021DeviceCreditF", driver, e);
  		fail(e.getMessage());
  	}
  }
  @AfterClass
 	public static void endtest() throws Exception {

 		ExtentReport.report.flush();

 	}
}
