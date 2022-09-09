package webdriver.scripts.status.calculationstatus;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
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
  public static void setupScript() throws Exception {
    status = BuildMap.getInstance(driver, StatusMap.class);
    System.out.println("Test Class: " + CalculationStatusPageSmokeTest.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    goToContractModelsPageAndSearchAndSelectModel(modelName);
  }

  @Test
  public void test01ClickCalculateButtonAndAssertSummaryIsErrorFree() throws InterruptedException {
    try {
      waitForAjaxExtJs();
      doClick(driver.findElement(By.xpath("//button/span[text()='Calculate']")));
      waitForSpinnerToEnd();
      waitForFirstRowCalculationBarToReach100Percent();
      calculationStatusPageOpenViewDialog();
      assertViewLogTitle(viewLogTitle);
      confirmCalculationStatusDetailsContains("Total EFRs to be processed: " + numberOfEfrs);
      confirmCalculationStatusDetailsContains("Errors: 0");
      confirmCalculationStatusDetailsContains("Process Completed");
      Thread.sleep(2000);
    } catch(Throwable e) {
      fail(e.getMessage());
    } finally {
      closeViewDialog();
    }
  }

  @Test
  public void test02VerifyCalculationStatusPageTableValueForNameColumn() throws InterruptedException {
    waitForAjaxExtJs();
    String nameText = getWebElement(nameXpath).getText();
    assertEquals(expectedName, nameText);
  }

  @Test
  public void test03VerifyCalculationStatusPageTableValueForCategoryColumn() {
    String categoryText = getWebElement(categoryXpath).getText();
    assertEquals(expectedCategory, categoryText);
  }

  @Test
  public void test04VerifyCalculationStatusPageTableValueForTypeColumn() {
    String typeText = getWebElement(typeXpath).getText();
    assertEquals(expectedType, typeText);
  }

  @Test
  public void test05VerifyCalculationStatusPageTableValueForProgressColumn() {
    String progressText = getWebElement(progressXpath).getText();
    assertEquals(expectedProgress, progressText);
  }

  @Test
  public void test06VerifyCalculationStatusPageTableValueForEstCalcEndTimeColumn() {
    String estCalcEndTimeText = getWebElement(estCalcEndTimeXpath).getText();
    assertThat(estCalcEndTimeText, containsString(currentDate));
  }

  @Test
  public void test07VerifyCalculationStatusPageTableValueForCalcStatusColumn() {
    String calcStatusText = getWebElement(calcStatusXpath).getText();
    assertEquals(expectedCalcStatus, calcStatusText);
  }

  @Test
  public void test08VerifyCalculationStatusPageTableValueForLogStatusColumn() {
    String logStatusText = getWebElement(logStatusXpath).getText();
    assertEquals(expectedLogStatus, logStatusText);
  }

  @Test
  public void test09VerifyCalculationStatusPageTableValueForSharedLocationColumn() {
    String sharedLocationText = getWebElement(sharedLocationXpath).getText();
    assertThat(sharedLocationText, containsString(expectedSharedLocationA));
    assertThat(sharedLocationText, containsString(expectedSharedLocationB));
  }

  @Test
  public void test10VerifyCalculationStatusPageTableValueForViewColumn() {
    String viewText = getWebElement(viewXpath).getText();
    assertEquals(expectedView, viewText);
  }

  @Test
  public void test11aVerifyCalculationStatusPageTableValueForDownloadColumn() {
    String downloadText = getWebElement(downloadXpath).getText();
    assertEquals(expectedDownload, downloadText);
  }

  @Test
  public void test11bVerifyCalculationStatusPageTableValueForDownloadColumnLinkPathFormat() {
    String downloadLinkFormat = getWebElement(downloadXpath).getAttribute("href");
    assertThat(downloadLinkFormat, containsString("/services/calculationStatus/downLoad/"));
  }

  @Test
  public void test12VerifyCalculationStatusPageTableValueForRecordsProcessedColumn() {
    String recordsProcessedText = getWebElement(recordsProcessedXpath).getText();
    assertEquals(expectedRecordsProcessed, recordsProcessedText);
  }

  @Test
  public void test13VerifyCalculationStatusPageTableValueForRecordsPendingColumn() {
    String recordsPendingText = getWebElement(recordsPendingXpath).getText();
    assertEquals(expectedRecordsPending, recordsPendingText);
  }

  @Test
  public void test14VerifyCalculationStatusPageTableValueForTotalRecordsColumn() {
    String totalRecordsText = getWebElement(totalRecordsXpath).getText();
    assertEquals(expectedTotalRecords, totalRecordsText);
  }

  @Test
  public void test15VerifyCalculationStatusPageTableValueForCalcStartTimeColumn() {
    String calcStartTimeText = getWebElement(calcStartTimeXpath).getText();
    assertThat(calcStartTimeText, containsString(currentDate));
  }

  @Test
  public void test16VerifyCalculationStatusPageTableValueForCalcEndTimeColumn() {
    String calcEndTimeText = getWebElement(calcEndTimeXpath).getText();
    assertThat(calcEndTimeText, containsString(currentDate));
  }

  @Test
  public void test17VerifyCalculationStatusPageTableValueForDurationColumnFormat() {
    String durationText = getWebElement(durationXpath).getText();
    assertTrue(durationText.matches(durationPatternMatch));
  }

  @Test
  public void test18VerifyCalculationStatusPageSearch() {
    status.getCalculationStatusPageFormFieldSearch().sendKeys(currentDate);
    status.getCalculationStatusPageButtonSearchGlass().click();
    waitForSpinnerToEnd();
    String searchStartTime = getWebElement(calcStartTimeXpath).getText();
    assertThat(searchStartTime, containsString(currentDate));
  }

  @Test
  public void test19DeleteNewCalculatedRowOnStatusPage() throws InterruptedException {
    status.getCalculationStatusPageFormFieldSearch().clear();
    status.getCalculationStatusPageButtonSearchGlass().click();
    deleteCalculationStatusMyStatusPageFirstRow();
  }

}
