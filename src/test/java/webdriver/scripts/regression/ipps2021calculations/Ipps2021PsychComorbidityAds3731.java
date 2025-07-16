package webdriver.scripts.regression.ipps2021calculations;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Ipps2021PsychComorbidityAds3731 extends CalculationHelper {

  private Ipps2021Data data = new Ipps2021Data();
  private static String status;
  private String mappingFileName = "IPFC21WD_ICD10.txt";
  private boolean isFailed = false;
 // static String batchName = getVersion() + "1 REGRESSION Comorbidity Code 1st";
    static String batchName = getVersion() +  " REGRESSION Comorbidity Code 1st"; //Venkat update batch name text 14.09.2022
                                      
  
  final String expectedLogView = batchName + "\\General\\Psych Combined Comorbidity Assignment";

  /** Regression: Test script for ADS-3731 - Generate Psych Combined Comorbidity Factor - 2021
   *  The current test is a version that tests the 2021 year file, whereas Ads2153 tests 2020.
   *  Note that the expected values are the same as 2020.
   *  Updated: 12/8/2020. 
 * @throws Throwable */
  @BeforeClass
  public static void setupScript() throws Throwable {
	  
	  ExtentReport.reportCreate("Ipps2021PsychComorbidityAds3731","webdriver.scripts.regression.ipps2021calculations", "Ipps2021PsychComorbidityAds3731");
		
    try {
		System.out.println("Test Class: " + Ipps2021PsychComorbidityAds3731.class.getSimpleName());
		Login.loginUser("ContractAnalyst1");
		 
		
		
		goToPage("Maintain Data");
		selectMaintainDataAtoZ("Psych Combined Comorbidity Assignments");
		openMaintainDataBatch(batchName);
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","setupScript", driver,e);
		fail(e.getMessage());
	} 
  }

  @Test
  public void test01ClickResetAndVerifyDatabaseValuesAreNull() throws Throwable {
	  
	  
    try {
    	
		waitForAjaxExtJs();
		waitForPresenceOfElement("//*[text()='Reset']");
		assertEquals(mappingFileName,driver.findElement(By.xpath("//*[text()='Last Upload File Name:']/../following-sibling::td/div")).getText());
		doClick(driver.findElement(By.xpath("//*[text()='Reset']")));
		waitForSpinnerToEnd();
		waitForFirstRowCalculationBarToReach100Percent(2000);
		deleteMyCalculationStatusFirstRow();
		getStringDataFromDatabaseAndAssertValuesAreNull(data.sqlQueryIpps2021PsychComorbidity,2);
		doClosePageOnLowerBar("Calculation Status");
		ExtentReport.logPass("PASS", "test01ClickResetAndVerifyDatabaseValuesAreNull");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","test01ClickResetAndVerifyDatabaseValuesAreNull", driver,e);
		fail(e.getMessage());
	} 
    
    
  }

 @Test
  public void test02ClickCalculateButtonAndAssertCalculationSummaryDetailsMatchExpected() throws Throwable {
    try {
      waitForAjaxExtJs();
      doClick(driver.findElement(By.xpath("//div/em/button/span[text()='Calculate']")));
      waitForDisplayedSpinnerToEnd();
      status = getCalculationStatusMyStatusFirstRow();
      assertThat(status, not(containsString("Failed")));
      waitForFirstRowCalculationBarToReach100Percent();
      status = getCalculationStatusMyStatusFirstRow();
      assertThat(status, containsString("Completed"));
      waitForCalculationToEndAndVerifyViewLogContainsOnViewDialogAndCloseDialog(
              expectedLogView
      );
//      waitForCalculationToEndAndVerifySummaryDetailsStringOnDialogAndCloseDialog(
//              "Name: " + mappingFileName
//      );
      deleteMyCalculationStatusFirstRow();
      doClosePageOnLowerBar("Calculation Status");
      doClickButton("Cancel & Close");
      ExtentReport.logPass("PASS", "test02ClickCalculateButtonAndAssertCalculationSummaryDetailsMatchExpected");
    } catch (Exception|AssertionError e) {
      isFailed = true;
      ExtentReport.logFail("FAIL","test02ClickCalculateButtonAndAssertCalculationSummaryDetailsMatchExpected", driver,e);
      fail(e.getMessage());
      //doClosePageOnLowerBar("Calculation Status");
      //doClickButton("Cancel & Close");
    }
  }

  @Test
  public void test03QueryDatabaseAndAssertValuesAreCorrectForIpps2021PsychComorbidity() throws Throwable {
	  
		
		  try {
			if(isFailed) {
			  fail("Test Failure: Did not successfully complete calculation process--Check mapping file"
			  ); } assertThat(status, containsString("Completed"));
			  getDataFromDatabaseAndAssertExpectedValues(data.
			  sqlQueryIpps2021PsychComorbidity,2,data.
			  expectedValuesIpps2021PsychComorbidity);
			  ExtentReport.logPass("PASS", "test03QueryDatabaseAndAssertValuesAreCorrectForIpps2021PsychComorbidity");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL","test03QueryDatabaseAndAssertValuesAreCorrectForIpps2021PsychComorbidity", driver,e);
			fail(e.getMessage());
		}
		 
    
    
    
    
  }

  
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
  
  
}
