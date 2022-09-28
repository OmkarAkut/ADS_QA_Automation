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
public class Ipps2021TransfersAds3635 extends CalculationHelper {

  static DataMaintenanceMap dmMap;
  static String batchName = getVersion() + " REGRESSION 2021 IPPS: Transfers";
  final String expectedLogView = batchName + "\\Contracting\\Contract Batch";
  private Ipps2021Data data = new Ipps2021Data();

  /** Regression: Test script for ADS-3635 - 2021 IPPS: Transfers.
   *  Updated: 12/8/2020. 
 * @throws Throwable */
  
  @BeforeClass
  public static void setupScript() throws Throwable {
	  ExtentReport.reportCreate("Ipps2021TransfersAds3635","webdriver.scripts.regression.ipps2021calculations", "Ipps2021TransfersAds3635");
	    
    try {
    	
		dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
		System.out.println("Test Class: " + Ipps2021TransfersAds3635.class.getSimpleName());
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
    } 
    catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test01ClickCalculateButtonAndAssertCalculationSummaryDetailsMatchExpected", driver,e);
      fail(e.getMessage());
    }
  }
  
  @Test
  public void test02QueryDatabaseAndAssertValuesAreCorrectForIpps2021TransfersA()throws Throwable {
	 try { 
		 
    getDataFromDatabaseAndAssertExpectedValues(data.sqlQueryIpps2021TransfersA,data.expectedValuesIpps2021TransfersA);
    ExtentReport.logPass("PASS", "test02QueryDatabaseAndAssertValuesAreCorrectForIpps2021TransfersA");
    
	 }catch(Exception|AssertionError e)
	 {
		 ExtentReport.logFail("FAIL","test02QueryDatabaseAndAssertValuesAreCorrectForIpps2021TransfersA", driver,e);
         fail(e.getMessage());
		 
	 }
    
    
    
  }

  @Test
  public void test03QueryDatabaseAndAssertValuesAreCorrectForIpps2021TransfersB()throws Throwable {
	  
	  try {
    getDataFromDatabaseAndAssertExpectedValues(data.sqlQueryIpps2021TransfersB,data.expectedValuesIpps2021TransfersB);
    ExtentReport.logPass("PASS", "test03QueryDatabaseAndAssertValuesAreCorrectForIpps2021TransfersB");
	  }
	  catch(Exception|AssertionError e)
	  {
		  ExtentReport.logFail("FAIL","test03QueryDatabaseAndAssertValuesAreCorrectForIpps2021TransfersB", driver,e);
	         fail(e.getMessage());
		  
	  }
  }
  
  @Test
  public void test04QueryDatabaseAndAssertValuesAreCorrectForIpps2021TransfersC()throws Throwable {
	  try {
    getDataFromDatabaseAndAssertExpectedValues(data.sqlQueryIpps2021TransfersC,data.expectedValuesIpps2021TransfersC);
    ExtentReport.logPass("PASS", "test04QueryDatabaseAndAssertValuesAreCorrectForIpps2021TransfersC");
	  }
	  catch(Exception|AssertionError e)
	  {
		  ExtentReport.logFail("FAIL","test04QueryDatabaseAndAssertValuesAreCorrectForIpps2021TransfersC", driver,e);
	         fail(e.getMessage());
		  
	  }
  }

  @Test
  public void test05QueryDatabaseAndAssertValuesAreCorrectForIpps2021TransfersD()throws Throwable {
	  
	  try {
    getDataFromDatabaseAndAssertExpectedValues(data.sqlQueryIpps2021TransfersD,data.expectedValuesIpps2021TransfersD);
    ExtentReport.logPass("PASS", "test05QueryDatabaseAndAssertValuesAreCorrectForIpps2021TransfersD");
	  }
	  catch(Exception|AssertionError e)
	  {
		  ExtentReport.logFail("FAIL","test05QueryDatabaseAndAssertValuesAreCorrectForIpps2021TransfersD", driver,e);
	         fail(e.getMessage());
	  }
  }
  
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
