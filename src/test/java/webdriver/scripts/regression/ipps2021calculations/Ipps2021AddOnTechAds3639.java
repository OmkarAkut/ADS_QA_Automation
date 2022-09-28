package webdriver.scripts.regression.ipps2021calculations;

import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import oracle.jdbc.driver.DatabaseError;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Ipps2021AddOnTechAds3639 extends CalculationHelper {

  static DataMaintenanceMap dmMap;
  static String batchName = getVersion() + " REGRESSION 2021 IPPS: Add On Tech";
  private Ipps2021Data data = new Ipps2021Data();
  static String expectedLogView = null;

  /** Regression: Test script for ADS-3639 - 2021 IPPS: Add On Tech. */
  @BeforeClass
  public static void setupScript() throws Exception,Throwable {
	  ExtentReport.reportCreate("Ipps2021AddOnTechAds3639", "webdriver.scripts.regression.ipps2021calculations", "Ipps2021AddOnTechAds3639");
    try {
		System.out.println("Version: " + getVersion());
		expectedLogView = batchName + "\\Contracting\\Contract Batch";
		dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
		System.out.println("Test Class: " + Ipps2021AddOnTechAds3639.class.getSimpleName());
		Login.loginUser("ContractAnalyst1");
		goToMaintainDataPageAndSelectContractBatch(batchName);
		ExtentReport.logPass("PASS", "setupScript");
	}  catch (Exception|AssertionError e) {
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
	}  catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test01ClickCalculateButtonAndAssertCalculationSummaryDetailsMatchExpected", driver, e);
		fail(e.getMessage());
	}
   }

  @Test
  public void test02QueryDatabaseAndAssertValuesAreCorrectForIpps2021AddOnTechA1()
          throws ClassNotFoundException ,Throwable{
    try {
		final String sqlQueryIpps2021AddOnTechA1 = data.getIpps2021SqlQuery(
		        "IPPSFY21AOTA%",
		        " REGRESSION 2021 IPPS AOT A1",
		        version
		);
		getDataFromDatabaseAndAssertExpectedValues(
		        sqlQueryIpps2021AddOnTechA1,
		        data.expectedValuesIpps2021AddOnTechA1
		);
		 ExtentReport.logPass("PASS", "test02QueryDatabaseAndAssertValuesAreCorrectForIpps2021AddOnTechA1");
			}  catch (Exception|AssertionError e) {
				ExtentReport.logFail("FAIL", "test02QueryDatabaseAndAssertValuesAreCorrectForIpps2021AddOnTechA1", driver, e);
				fail(e.getMessage());
			}
  }

  @Test
  public void test03QueryDatabaseAndAssertValuesAreCorrectForIpps2021AddOnTechA2()
          throws ClassNotFoundException,Throwable {
    try {
		final String sqlQueryIpps2021AddOnTechA2 = data.getIpps2021SqlQuery(
		        "IPPSFY21AOTA%",
		        " REGRESSION 2021 IPPS AOT A2",
		        version
		);
		getDataFromDatabaseAndAssertExpectedValues(
		        sqlQueryIpps2021AddOnTechA2,
		        data.expectedValuesIpps2021AddOnTechA2
		);
		ExtentReport.logPass("PASS", "test03QueryDatabaseAndAssertValuesAreCorrectForIpps2021AddOnTechA2");
	}  catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test03QueryDatabaseAndAssertValuesAreCorrectForIpps2021AddOnTechA2", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test04QueryDatabaseAndAssertValuesAreCorrectForIpps2021AddOnTechD()
          throws ClassNotFoundException,Throwable {
    try {
		final String sqlQueryIpps2021AddOnTechD = data.getIpps2021SqlQuery(
		        "IPPSFY21AOTD%",
		        " REGRESSION 2021 IPPS AOT D",
		        version
		);
		getDataFromDatabaseAndAssertExpectedValues(
		        sqlQueryIpps2021AddOnTechD,
		        data.expectedValuesIpps2021AddOnTechD
		);
		ExtentReport.logPass("PASS", "test04QueryDatabaseAndAssertValuesAreCorrectForIpps2021AddOnTechD");
	}  catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test04QueryDatabaseAndAssertValuesAreCorrectForIpps2021AddOnTechD", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test05QueryDatabaseAndAssertValuesAreCorrectForIpps2021AddOnTechE()
          throws ClassNotFoundException,Throwable {
    try {
		final String sqlQueryIpps2021AddOnTechE = data.getIpps2021SqlQuery(
		        "IPPSFY21AOTE%",
		        " REGRESSION 2021 IPPS AOT E",
		        version
		);
		getDataFromDatabaseAndAssertExpectedValues(
		        sqlQueryIpps2021AddOnTechE,
		        data.expectedValuesIpps2021AddOnTechE
		);
		ExtentReport.logPass("PASS", "test05QueryDatabaseAndAssertValuesAreCorrectForIpps2021AddOnTechE");
	}  catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test05QueryDatabaseAndAssertValuesAreCorrectForIpps2021AddOnTechE", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test06QueryDatabaseAndAssertValuesAreCorrectForIpps2021AddOnTechF()
          throws ClassNotFoundException,Throwable {
    try {
		final String sqlQueryIpps2021AddOnTechF = data.getIpps2021SqlQuery(
		        "IPPSFY21AOTF%",
		        " REGRESSION 2021 IPPS AOT F",
		        version
		);
		getDataFromDatabaseAndAssertExpectedValues(
		        sqlQueryIpps2021AddOnTechF,
		        data.expectedValuesIpps2021AddOnTechF
		);
		ExtentReport.logPass("PASS", "test06QueryDatabaseAndAssertValuesAreCorrectForIpps2021AddOnTechF");
	}  catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test06QueryDatabaseAndAssertValuesAreCorrectForIpps2021AddOnTechF", driver, e);
		fail(e.getMessage());
	}
  }

//  public static String getVersion() {
//    if (testEnvironment.toLowerCase().contains("evolve")) {
//      version = "v104";
//    } else {
//      version = "v103";
//    }
//    return version;
//  }

  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}

}
