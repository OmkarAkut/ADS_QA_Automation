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
public class Ipps2021Transfers0282Ads3636 extends CalculationHelper {

  static DataMaintenanceMap dmMap;
  static String batchName = getVersion() + " REGRESSION 2021 IPPS: Trans 02 82";
  final String expectedLogView = batchName + "\\Contracting\\Contract Batch";
  private Ipps2021Data data = new Ipps2021Data();

  /** Regression: Test script for ADS-3636 - 2021 IPPS: Transfers 02.
   *  Updated: 12/9/20. 
 * @throws Throwable */
  @BeforeClass
  public static void setupScript() throws Throwable {
	  ExtentReport.reportCreate("Ipps2021Transfers0282Ads3636","webdriver.scripts.regression.ipps2021calculations", "Ipps2021Transfers0282Ads3636");
	  
    try {
		dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
		System.out.println("Test Class: " + Ipps2021Transfers0282Ads3636.class.getSimpleName());
		Login.loginUser("ContractAnalyst1");
		goToMaintainDataPageAndSelectContractBatch(batchName);
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","setupScript", driver,e);
		fail(e.getMessage());
		
	} 
    
  }

  @Test
  public void test01ClickCalculateButtonAndAssertCalculationSummaryDetailsMatchExpected() throws Throwable {
    try {
      doClick(dmMap.getMaintainDataPageButtonCalculate());
      waitForCalculationToEndAndVerifyViewLogContainsOnViewDialogAndCloseDialog(expectedLogView);
      deleteMyCalculationStatusFirstRow();
      ExtentReport.logPass("PASS", "test01ClickCalculateButtonAndAssertCalculationSummaryDetailsMatchExpected");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test01ClickCalculateButtonAndAssertCalculationSummaryDetailsMatchExpected", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test02QueryDatabaseAndAssertValuesAreCorrectForIpps2021Transfers0282K1()throws Throwable {
	  
    try {
		getDataFromDatabaseAndAssertExpectedValues(data.sqlQueryIpps2021Transfers0282K1,data.expectedValuesIpps2021Transfers0282K1);
		ExtentReport.logPass("PASS", "test02QueryDatabaseAndAssertValuesAreCorrectForIpps2021Transfers0282K1");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","test02QueryDatabaseAndAssertValuesAreCorrectForIpps2021Transfers0282K1", driver,e);
	      fail(e.getMessage());
		
	}
  }

  @Test
  public void test03QueryDatabaseAndAssertValuesAreCorrectForIpps2021Transfers0282K2()throws Throwable {
	  
    try {
		getDataFromDatabaseAndAssertExpectedValues(data.sqlQueryIpps2021Transfers0282K2,data.expectedValuesIpps2021Transfers0282K2);
		ExtentReport.logPass("PASS", "test03QueryDatabaseAndAssertValuesAreCorrectForIpps2021Transfers0282K2");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","test03QueryDatabaseAndAssertValuesAreCorrectForIpps2021Transfers0282K2", driver,e);
	      fail(e.getMessage());
		
	}
  }

  @Test
  public void test04QueryDatabaseAndAssertValuesAreCorrectForIpps2021Transfers0282L()throws Throwable {
	  
    try {
		getDataFromDatabaseAndAssertExpectedValues(data.sqlQueryIpps2021Transfers0282L,data.expectedValuesIpps2021Transfers0282L);
		ExtentReport.logPass("PASS", "test04QueryDatabaseAndAssertValuesAreCorrectForIpps2021Transfers0282L");
		
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","test04QueryDatabaseAndAssertValuesAreCorrectForIpps2021Transfers0282L", driver,e);
	      fail(e.getMessage());
	}
  }

  @Test
  public void test05QueryDatabaseAndAssertValuesAreCorrectForIpps2021Transfers0282M()throws Throwable {
	  
    try {
		getDataFromDatabaseAndAssertExpectedValues(data.sqlQueryIpps2021Transfers0282M,data.expectedValuesIpps2021Transfers0282M);
		ExtentReport.logPass("PASS", "test05QueryDatabaseAndAssertValuesAreCorrectForIpps2021Transfers0282M");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","test05QueryDatabaseAndAssertValuesAreCorrectForIpps2021Transfers0282M", driver,e);
	      fail(e.getMessage());
	}

    
  }
  
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}

}
