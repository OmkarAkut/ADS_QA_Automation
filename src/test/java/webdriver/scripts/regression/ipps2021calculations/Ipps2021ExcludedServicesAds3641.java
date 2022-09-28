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
public class Ipps2021ExcludedServicesAds3641 extends CalculationHelper {

  static DataMaintenanceMap dmMap;
  //Shilpa 14.09.2022 commented below line as test data not available
//  static final String batchName = getVersion() + " REGRESSION 2021 IPPS: Exclude Serv";
  static final String batchName = getVersion() + " REGRESSION 2021 IPPS: Transfers";
  final String expectedLogView = batchName + "\\Contracting\\Contract Batch";
  private Ipps2021Data data = new Ipps2021Data();

  /** Regression: Test script for ADS-3641- 2021 IPPS: Excluded Services.
   *  Updated: 12/9/20. */
  @BeforeClass
  public static void setupScript() throws Exception,Throwable {
	 ExtentReport.reportCreate("Ipps2021ExcludedServicesAds3641", "webdriver.scripts.regression.ipps2021calculations", "Ipps2021ExcludedServicesAds3641");
    try {
		dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
		System.out.println("Test Class: " + Ipps2021ExcludedServicesAds3641.class.getSimpleName());
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
  public void test02QueryDatabaseAndAssertValuesAreCorrectForIpps2021ExcludedServices()
          throws ClassNotFoundException,Throwable {
    try {
		getDataFromDatabaseAndAssertExpectedValues(
		        data.sqlQueryIpps2021ExcludedServices,
		        data.expectedValuesIpps2021ExcludedServices
		);
		ExtentReport.logPass("PASS", "test02QueryDatabaseAndAssertValuesAreCorrectForIpps2021ExcludedServices");
  	} catch (Exception|AssertionError e) {
  		ExtentReport.logFail("FAIL", "test02QueryDatabaseAndAssertValuesAreCorrectForIpps2021ExcludedServices", driver, e);
  		fail(e.getMessage());
  	}
  }
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}

}
