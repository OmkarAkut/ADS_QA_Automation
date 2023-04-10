package webdriver.scripts.regression.generalcalculations;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GeneralCalculationsRunPriceListCalculationScenarioAds2274 extends CalculationHelper {
	 //Shilpa 21.09.2022
//  static String viewLogTitle = "Remove Encounter Service Classification";
  static String viewLogTitle = "Remove Price List to Encounters Assignment";
  static String viewLogTitleAssign = "Price List Encounters Assignment";
  //Shilpa 21.09.2022
//  final static String aTozPage = "Price Lists";
  final static String batch = "v10.2 REGRESSION Price List Enc Assign";
  final static String aTozPage = "Price List to Encounters Assignments";

  final static String xSourcePriceList = "QAASSIGNPL";
  final static String xDestinationChargeScenario = "Estimated Charges 15";
  private static List<WebElement> encountersTable;

  /** Regression: Test script for ADS-2343. Updated: 7-7-21. ,ADS-6099*/
  @BeforeClass
  public static void setupScript() throws Exception, Throwable {
	  ExtentReport.reportCreate("GeneralCalculationsRunPriceListCalculationScenarioAds2274", "webdriver.scripts.regression.generalcalculations", "GeneralCalculationsRunPriceListCalculationScenarioAds2274");
    try {
		System.out.println(
		        "Test Class: " + GeneralCalculationsRunPriceListCalculationScenarioAds2274.class.getSimpleName());
		Login.loginUser("ContractAnalyst1");
		goToPage("Maintain Data");
		selectMaintainDataAtoZ(aTozPage);
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "Failure in setupscript", driver, e);
		fail(e.getMessage());
	}
    
    openMaintainDataBatch(batch);
  }

//  @Test
  public void test00ConfirmParametersOnPage() {
    String sourcePriceList = null;
    String destinationChargeScenario = null;
    assertThat(sourcePriceList, equalTo(xSourcePriceList));
    assertThat(destinationChargeScenario, equalTo(xDestinationChargeScenario));
  }

  @Test
  public void test01ClickClearResultsButtonAndVerifyCalculationStatusPageDetails()
          throws InterruptedException,Throwable {
    try {
      waitForAjaxExtJs();
      //shilpa 21.09.2022
//      waitForPresenceOfElement("//button/span[text()='Clear Results']");
//      doClick(getButton("Clear Results"));
      waitForPresenceOfElement("(//button/span[text()='Remove'])[2]");
      driver.findElement(By.xpath("(//button/span[text()='Remove'])[2]")).click();
//      doClick(getButton("Remove"));
      waitForSpinnerToEnd();
      waitForFirstRowCalculationBarToReach100Percent();
      driverDelay(5000);
      calculationStatusPageOpenViewDialog();
      driverDelay(5000);
      assertViewLogTitle(viewLogTitle);
      confirmCalculationStatusDetailsContains("Process Completed");
      //Shilpa 21.09.2022
//      confirmCalculationStatusDetailsContains("Total number of Charge Items reset = 25");
    confirmCalculationStatusDetailsContains("Total number of charge items reset: 25");

      closeViewDialog();
      deleteMyCalculationStatusFirstRow();
      ExtentReport.logPass("PASS", "test01ClickClearResultsButtonAndVerifyCalculationStatusPageDetails");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test01ClickClearResultsButtonAndVerifyCalculationStatusPageDetails", driver, e);
		fail(e.getMessage());
	} finally {
      try {
		doClosePageOnLowerBar("Calculation Status");
	}catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test01ClickClearResultsButtonAndVerifyCalculationStatusPageDetails", driver, e);
		fail(e.getMessage());
	}
    }
  }

//  @Test
  public void test02VerifyDatabaseValueCountIsZero() throws ClassNotFoundException {
    calculationsAssertDbRowCount(
            GeneralCalculationsData.getPriceListEncounterAssignmentSql,
            "equal to",
            0
    );
  }

  //Shilpa 21.09.2022 below tc fails due to error pop up onclick Assign button
  @Test
  public void test02ClickCalculateButtonAndAssertCalculationStatusSummaryDetailsMatchExpected()
          throws InterruptedException,Throwable {
    try {
      waitForAjaxExtJs();
      waitForPresenceOfElement("(//button/span[text()='Assign'])[2]");
//      doClick(getButton("Assign"));
      doClick(driver.findElement(By.xpath("(//button/span[text()='Assign'])[2]")));
      waitForSpinnerToEnd();
      waitForFirstRowCalculationBarToReach100Percent();
      driverDelay(4000);
      calculationStatusPageOpenViewDialog();
      driverDelay(4000);
      assertViewLogTitle(viewLogTitleAssign);
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
    		  driver.findElement(By.xpath("//*[contains(text(),'Total number of charge items processed: 25')]"))
    		  );
      confirmCalculationStatusDetailsContains("Total number of charge items processed: 25");
      clickLastPageIconOnCalculationStatusViewLog();
      confirmCalculationStatusDetailsContains("Process Completed");
      closeViewDialog();
      deleteMyCalculationStatusFirstRow();
      ExtentReport.logPass("PASS", "test02ClickCalculateButtonAndAssertCalculationStatusSummaryDetailsMatchExpected");
    	} catch (Exception|AssertionError e) {
    		ExtentReport.logFail("FAIL", "test02ClickCalculateButtonAndAssertCalculationStatusSummaryDetailsMatchExpected", driver, e);
    		fail(e.getMessage());
    	} finally {
      try {
		doClosePageOnLowerBar("Calculation Status");
		  waitForPresenceOfElement("//button/span[text()='Cancel & Close']");
		  doClick(getWebElement("//button/span[text()='Cancel & Close']"));
		  doClosePageOnLowerBar("Maintain Data");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test02ClickCalculateButtonAndAssertCalculationStatusSummaryDetailsMatchExpected", driver, e);
		fail(e.getMessage());
	}
    }
  }
  @AfterClass
  public static void teardownScript() {
    ExtentReport.report.flush();
  }
//  @Test
  public void test03QueryDatabaseAndAssertValuesAreCorrectForPriceListEncounterAssignments()
          throws ClassNotFoundException {
//    calculationsAssertDbRowCount(
//            GeneralCalculationsData.getPriceListEncounterAssignmentSql,
//            "equal",
//            GeneralCalculationsData.getPriceListEncounterAssignmentExpectedValue
//    );
  }

}
