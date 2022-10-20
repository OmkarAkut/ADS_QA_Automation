package webdriver.scripts.status.calculationstatus;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

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
import webdriver.maps.StatusMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CalculationStatusPageSmokeTest extends CalculationHelper {

  static final String modelName = "v102 REGRESSION OPPS 2020 J Packaging C";
  final int numberOfEfrs = 16;
  static String viewLogTitle = "\\Contracting\\Unpublished Contract Calculation";
  static StatusMap status;
  String currentDate = javaGetCurrentDate("MM/dd/yyyy");
  static String currentDateForPath = javaGetCurrentDate("MMddyyyy");

  static final String expectedName = "v102 REGRESSION OPPS 2020 J Packaging C";
  static final String expectedCategory = "Contracting";
  static final String expectedType = "Unpublished Contract Calculation";
  static final String expectedProgress = "100%";
  final String expectedEstCalcEndTime = currentDate;
  static final String expectedCalcStatus = "Completed";
  static final String expectedLogStatus = "Completed";
  static final String expectedSharedLocationA = "/PATH/TO/CALC_LOGS_SHARED_DIRECTORY2//automationcontraanalyst1_" + currentDateForPath;//Shilpa 29.08.2022 updated name from eolheiser_ to automationcontraanalyst1 
  static final String expectedSharedLocationB = "_v102 REGRESSION OPPS 2020 J Packaging C_V102 REGRESSION 2020 OPPS J Packaging C.zip";
  static final String expectedView = "View";
  static final String expectedDownload = "Download";
  static final String expectedRecordsProcessed = "16";
  static final String expectedRecordsPending = "0";
  static final String expectedTotalRecords = "16";
  String durationPatternMatch = "^\\d\\d:\\d\\d:\\d\\d$";

  //static String baseXpath = "//div[2]/div/div/div[2]/div/div[3]/div/div/div[1]/div[2]/div/table/tbody";
  static String nameXpath =         "//div[2]/div/div[4]/div/table/tbody/tr[2]/td[5]/div";
  static String categoryXpath =     "//div[2]/div/div[4]/div/table/tbody/tr[2]/td[6]/div";
  static String typeXpath =         "//div[2]/div/div[4]/div/table/tbody/tr[2]/td[7]/div";
  static String progressXpath =     "//div[2]/div/div[4]/div/table/tbody/tr[2]/td[8]/div/div/div";
  static String estCalcEndTimeXpath =   "//div[2]/div/div[4]/div/table/tbody/tr[2]/td[9]/div";
  static String calcStatusXpath =     "//div[2]/div/div[4]/div/table/tbody/tr[2]/td[10]/div";
  static String logStatusXpath =      "//div[2]/div/div[4]/div/table/tbody/tr[2]/td[11]/div";
  static String sharedLocationXpath =      "//div[2]/div/div[4]/div/table/tbody/tr[2]/td[12]/div";
  static String viewXpath =               "//div[2]/div/div[4]/div/table/tbody/tr[2]/td[13]/div";
  static String downloadXpath =           "//div[2]/div/div[4]/div/table/tbody/tr[2]/td[14]/div/a";
  static String recordsProcessedXpath =   "//div[2]/div/div[4]/div/table/tbody/tr[2]/td[15]/div";
  static String recordsPendingXpath =     "//div[2]/div/div[4]/div/table/tbody/tr[2]/td[16]/div";
  static String totalRecordsXpath =            "//div[2]/div/div[4]/div/table/tbody/tr[2]/td[17]/div";
  static String calcStartTimeXpath =      "//div[2]/div/div[4]/div/table/tbody/tr[2]/td[18]/div";
  static String calcEndTimeXpath =        "//div[2]/div/div[4]/div/table/tbody/tr[2]/td[19]/div";
  static String durationXpath =           "//div[2]/div/div[4]/div/table/tbody/tr[2]/td[20]/div";

  /** Smoke test for calculation and calculation status pages - 2020 OPPS: J Packaging C. */
  @BeforeClass
  public static void setupScript() throws Exception,Throwable {
	  ExtentReport.reportCreate("CalculationStatusPageSmokeTest", "webdriver.scripts.status.calculationstatus", "CalculationStatusPageSmokeTest");
    try {
		status = BuildMap.getInstance(driver, StatusMap.class);
		System.out.println("Test Class: " + CalculationStatusPageSmokeTest.class.getSimpleName());
		Login.loginUser("ContractAnalyst1");
		goToContractModelsPageAndSearchAndSelectModel(modelName);
		ExtentReport.logPass("PASS", "CalculationStatusPageSmokeTest");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "CalculationStatusPageSmokeTest", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test01ClickCalculateButtonAndAssertSummaryIsErrorFree() throws Throwable,InterruptedException {
    try {
      waitForAjaxExtJs();
      doClick(driver.findElement(By.xpath("//button/span[text()='Calculate']")));
      waitForSpinnerToEnd();
      waitForFirstRowCalculationBarToReach100Percent();
      driverDelay(4000);//venkat added explicitly time 21.09.2022
      calculationStatusPageOpenViewDialog();
      assertViewLogTitle(viewLogTitle);
    //venkat added java script 22.09.2022
      Thread.sleep(500); 
      ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",  driver.findElement(By.xpath("//*[contains(text(),'Total EFRs to be processed: "+numberOfEfrs+"')]")));
                                                                                                                           
      confirmCalculationStatusDetailsContains("Total EFRs to be processed: "+numberOfEfrs);
      confirmCalculationStatusDetailsContains("Errors: 0");
      confirmCalculationStatusDetailsContains("Process Completed");
     
		ExtentReport.logPass("PASS", "test01ClickCalculateButtonAndAssertSummaryIsErrorFree");

    } catch(Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test01ClickCalculateButtonAndAssertSummaryIsErrorFree", driver, e);

      fail(e.getMessage());
    } finally {
      try {
		closeViewDialog();
	} catch (Exception e) {
		
	}
    }
  }

  @Test
  public void test02VerifyCalculationStatusPageTableValueForNameColumn() throws Throwable,InterruptedException {
    try {
		waitForAjaxExtJs();
		String nameText = getWebElement(nameXpath).getText();
		assertEquals(expectedName, nameText);
		ExtentReport.logPass("PASS", "test02VerifyCalculationStatusPageTableValueForNameColumn");

	} catch(Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test02VerifyCalculationStatusPageTableValueForNameColumn", driver, e);

	      fail(e.getMessage());
	    }
  }

  @Test
  public void test03VerifyCalculationStatusPageTableValueForCategoryColumn() throws Throwable {
    try {
		String categoryText = getWebElement(categoryXpath).getText();
		assertEquals(expectedCategory, categoryText);
		ExtentReport.logPass("PASS", "test03VerifyCalculationStatusPageTableValueForCategoryColumn");

	} catch(Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test03VerifyCalculationStatusPageTableValueForCategoryColumn", driver, e);

	      fail(e.getMessage());
	    }
  }

  @Test
  public void test04VerifyCalculationStatusPageTableValueForTypeColumn() throws Throwable {
    try {
		String typeText = getWebElement(typeXpath).getText();
		assertEquals(expectedType, typeText);
		ExtentReport.logPass("PASS", "test04VerifyCalculationStatusPageTableValueForTypeColumn");

	} catch(Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test04VerifyCalculationStatusPageTableValueForTypeColumn", driver, e);

	      fail(e.getMessage());
	    }
  }

  @Test
  public void test05VerifyCalculationStatusPageTableValueForProgressColumn() throws Throwable {
    try {
		String progressText = getWebElement(progressXpath).getText();
		assertEquals(expectedProgress, progressText);
		ExtentReport.logPass("PASS", "test05VerifyCalculationStatusPageTableValueForProgressColumn");

	} catch(Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test05VerifyCalculationStatusPageTableValueForProgressColumn", driver, e);

	      fail(e.getMessage());
	    }
  }

  @Test
  public void test06VerifyCalculationStatusPageTableValueForEstCalcEndTimeColumn() throws Throwable {
    try {
		String estCalcEndTimeText = getWebElement(estCalcEndTimeXpath).getText();
		assertThat(estCalcEndTimeText, containsString(currentDate));
		ExtentReport.logPass("PASS", "test06VerifyCalculationStatusPageTableValueForEstCalcEndTimeColumn");

	} catch(Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test06VerifyCalculationStatusPageTableValueForEstCalcEndTimeColumn", driver, e);

	      fail(e.getMessage());
	    }
  }

  @Test
  public void test07VerifyCalculationStatusPageTableValueForCalcStatusColumn() throws Throwable {
    try {
		String calcStatusText = getWebElement(calcStatusXpath).getText();
		assertEquals(expectedCalcStatus, calcStatusText);
		ExtentReport.logPass("PASS", "test07VerifyCalculationStatusPageTableValueForCalcStatusColumn");

	} catch(Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test07VerifyCalculationStatusPageTableValueForCalcStatusColumn", driver, e);

	      fail(e.getMessage());
	    }
  }

  @Test
  public void test08VerifyCalculationStatusPageTableValueForLogStatusColumn() throws Throwable {
    try {
		String logStatusText = getWebElement(logStatusXpath).getText();
		assertEquals(expectedLogStatus, logStatusText);
		ExtentReport.logPass("PASS", "test08VerifyCalculationStatusPageTableValueForLogStatusColumn");

	} catch(Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test08VerifyCalculationStatusPageTableValueForLogStatusColumn", driver, e);

	      fail(e.getMessage());
	    }
  }

  @Test
  public void test09VerifyCalculationStatusPageTableValueForSharedLocationColumn() throws Throwable {
    try {
		String sharedLocationText = getWebElement(sharedLocationXpath).getText();
		assertThat(sharedLocationText, containsString(expectedSharedLocationA));
		assertThat(sharedLocationText, containsString(expectedSharedLocationB));
		ExtentReport.logPass("PASS", "test09VerifyCalculationStatusPageTableValueForSharedLocationColumn");

	} catch(Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test09VerifyCalculationStatusPageTableValueForSharedLocationColumn", driver, e);

	      fail(e.getMessage());
	    }
  }

  @Test
  public void test10VerifyCalculationStatusPageTableValueForViewColumn() throws Throwable {
    try {
		String viewText = getWebElement(viewXpath).getText();
		assertEquals(expectedView, viewText);
		ExtentReport.logPass("PASS", "test10VerifyCalculationStatusPageTableValueForViewColumn");

	} catch(Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test10VerifyCalculationStatusPageTableValueForViewColumn", driver, e);

	      fail(e.getMessage());
	    }
  }

  @Test
  public void test11aVerifyCalculationStatusPageTableValueForDownloadColumn() throws Throwable {
    try {
		String downloadText = getWebElement(downloadXpath).getText();
		assertEquals(expectedDownload, downloadText);
		ExtentReport.logPass("PASS", "test11aVerifyCalculationStatusPageTableValueForDownloadColumn");

	} catch(Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test11aVerifyCalculationStatusPageTableValueForDownloadColumn", driver, e);

	      fail(e.getMessage());
	    }
  }

  @Test
  public void test11bVerifyCalculationStatusPageTableValueForDownloadColumnLinkPathFormat() throws Throwable {
    try {
		String downloadLinkFormat = getWebElement(downloadXpath).getAttribute("href");
		assertThat(downloadLinkFormat, containsString("/services/calculationStatus/downLoad/"));
		ExtentReport.logPass("PASS", "test11bVerifyCalculationStatusPageTableValueForDownloadColumnLinkPathFormat");

	} catch(Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test11bVerifyCalculationStatusPageTableValueForDownloadColumnLinkPathFormat", driver, e);

	      fail(e.getMessage());
	    }
  }

  @Test
  public void test12VerifyCalculationStatusPageTableValueForRecordsProcessedColumn() throws Throwable {
    try {
		String recordsProcessedText = getWebElement(recordsProcessedXpath).getText();
		assertEquals(expectedRecordsProcessed, recordsProcessedText);
		ExtentReport.logPass("PASS", "test12VerifyCalculationStatusPageTableValueForRecordsProcessedColumn");

	} catch(Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test12VerifyCalculationStatusPageTableValueForRecordsProcessedColumn", driver, e);

	      fail(e.getMessage());
	    }
  }

  @Test
  public void test13VerifyCalculationStatusPageTableValueForRecordsPendingColumn() throws Throwable {
    try {
		String recordsPendingText = getWebElement(recordsPendingXpath).getText();
		assertEquals(expectedRecordsPending, recordsPendingText);
		ExtentReport.logPass("PASS", "test13VerifyCalculationStatusPageTableValueForRecordsPendingColumn");

	} catch(Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test13VerifyCalculationStatusPageTableValueForRecordsPendingColumn", driver, e);

	      fail(e.getMessage());
	    }
  }

  @Test
  public void test14VerifyCalculationStatusPageTableValueForTotalRecordsColumn() throws Throwable {
    try {
		String totalRecordsText = getWebElement(totalRecordsXpath).getText();
		assertEquals(expectedTotalRecords, totalRecordsText);
		ExtentReport.logPass("PASS", "test14VerifyCalculationStatusPageTableValueForTotalRecordsColumn");

	} catch(Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test14VerifyCalculationStatusPageTableValueForTotalRecordsColumn", driver, e);

	      fail(e.getMessage());
	    }
  }

  @Test
  public void test15VerifyCalculationStatusPageTableValueForCalcStartTimeColumn() throws Throwable {
    try {
		String calcStartTimeText = getWebElement(calcStartTimeXpath).getText();
		assertThat(calcStartTimeText, containsString(currentDate));
		ExtentReport.logPass("PASS", "test15VerifyCalculationStatusPageTableValueForCalcStartTimeColumn");

	} catch(Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test15VerifyCalculationStatusPageTableValueForCalcStartTimeColumn", driver, e);

	      fail(e.getMessage());
	    }
  }

  @Test
  public void test16VerifyCalculationStatusPageTableValueForCalcEndTimeColumn() throws Throwable {
    try {
		String calcEndTimeText = getWebElement(calcEndTimeXpath).getText();
		assertThat(calcEndTimeText, containsString(currentDate));
		ExtentReport.logPass("PASS", "test16VerifyCalculationStatusPageTableValueForCalcEndTimeColumn");

	} catch(Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test16VerifyCalculationStatusPageTableValueForCalcEndTimeColumn", driver, e);

	      fail(e.getMessage());
	    }
  }

  @Test
  public void test17VerifyCalculationStatusPageTableValueForDurationColumnFormat() throws Throwable {
    try {
		String durationText = getWebElement(durationXpath).getText();
		assertTrue(durationText.matches(durationPatternMatch));
		ExtentReport.logPass("PASS", "test17VerifyCalculationStatusPageTableValueForDurationColumnFormat");

	} catch(Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test17VerifyCalculationStatusPageTableValueForDurationColumnFormat", driver, e);

	      fail(e.getMessage());
	    }
  }

  @Test
  public void test18VerifyCalculationStatusPageSearch() throws Throwable {
    try {
		status.getCalculationStatusPageFormFieldSearch().sendKeys(currentDate);
		status.getCalculationStatusPageButtonSearchGlass().click();
		waitForSpinnerToEnd();
		String searchStartTime = getWebElement(calcStartTimeXpath).getText();
		assertThat(searchStartTime, containsString(currentDate));
		ExtentReport.logPass("PASS", "test18VerifyCalculationStatusPageSearch");

	} catch(Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test18VerifyCalculationStatusPageSearch", driver, e);

	      fail(e.getMessage());
	    }
  }

  @Test
  public void test19DeleteNewCalculatedRowOnStatusPage() throws Throwable {
    try {
		status.getCalculationStatusPageFormFieldSearch().clear();
		status.getCalculationStatusPageButtonSearchGlass().click();
		deleteCalculationStatusMyStatusPageFirstRow();
		ExtentReport.logPass("PASS", "test19DeleteNewCalculatedRowOnStatusPage");

	} catch(Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test19DeleteNewCalculatedRowOnStatusPage", driver, e);

	      fail(e.getMessage());
	    }
  }
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
