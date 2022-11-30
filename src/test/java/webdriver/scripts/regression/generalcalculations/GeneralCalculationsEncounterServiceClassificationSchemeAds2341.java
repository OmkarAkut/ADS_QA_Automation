package webdriver.scripts.regression.generalcalculations;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GeneralCalculationsEncounterServiceClassificationSchemeAds2341 extends CalculationHelper {

  static String encounter = "OPPS2020DCA001";
  static String encounterNoServices = "OPPS2020DCA002";
  //Encounter Services
  List<String> expectedEncounters = Arrays.asList(
          "QAREGRESSION34567890123456789012345678901", //"PC Pop Fac or Non F"
          "OPPS 2020"
  );
  static String viewLogTitleRemove = "Remove Encounter Service Classification";
  static String viewLogTitleApply = "Encounter Service Classification Scheme";

  static DataMaintenanceMap dmMap;
  final static String aTozPage = "Encounter Service Classification Schemes";
//  final static String batch = "v10.2 REGRESSION Enc Serv Class Scheme";
  final static String batch = "v10.4 REGRESSION Enc Serv Class Scheme";//updated 11.25.2022
  private static List<WebElement> encountersTable;

  /** Regression: Test script for ADS-2341. Updated: 7-7-21. */
  @BeforeClass
  public static void setupScript() throws Exception,Throwable {
	 ExtentReport.reportCreate("GeneralCalculationsEncounterServiceClassificationSchemeAds2341", "webdriver.scripts.regression.generalcalculations", "GeneralCalculationsEncounterServiceClassificationSchemeAds2341");
    try {
		dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
		System.out.println(
		  "Test Class: " + GeneralCalculationsEncounterServiceClassificationSchemeAds2341.class.getSimpleName()
		);
		Login.loginUser("ContractAnalyst1");
		goToPage("Maintain Data");
		selectMaintainDataAtoZ(aTozPage);
	    Thread.sleep(5000);

		tableClickColumnHeader("Name");
		 Thread.sleep(5000);
		openMaintainDataBatch(batch);
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "Failure to setupScript", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test01ClickRemoveButtonAndVerifyRemoveProcessRanSuccessfully()
          throws InterruptedException,Throwable {
    try {
      waitForPresenceOfElement("//button/span[text()='Remove']");
      doClick(driver.findElement(By.xpath("//button/span[text()='Remove']")));
      waitForSpinnerToEnd();
      waitForFirstRowCalculationBarToReach100Percent();
      driverDelay(5000);
      calculationStatusPageOpenViewDialog();
      assertViewLogTitle(viewLogTitleRemove);
      confirmCalculationStatusDetailsContains("Process Completed");
      closeViewDialog();
      deleteMyCalculationStatusFirstRow();
      ExtentReport.logPass("PASS", "test01ClickRemoveButtonAndVerifyRemoveProcessRanSuccessfully");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test01ClickRemoveButtonAndVerifyRemoveProcessRanSuccessfully", driver, e);
		fail(e.getMessage());
	} finally {
      try {
		doClosePageOnLowerBar("Calculation Status");
		  doClick(driver.findElement(By.xpath("//button/span[text()='Save & Close']")));
		  waitForSpinnerToEnd();
	} catch (Exception|AssertionError e) {
 		ExtentReport.logFail("FAIL", "test01ClickRemoveButtonAndVerifyRemoveProcessRanSuccessfully", driver, e);
 		fail(e.getMessage());
 	} 
    }
  }

//  @Ignore
//  @Test
//  public void test02VerifyNoServicesAppearOnEncountersPage()
//          throws InterruptedException {
//    ////    calculationsAssertDbRowCount(
////            data.getMedicalServiceAssignmentsSql(),
////            "equal to",
////            0
////    );
//
//  }

  @Test
  public void test03VerifyNoServicesAppearOnEncountersPage()
          throws InterruptedException,Throwable {
    try {
		doMaintainDataPageSelectAtoZOption("Encounters");
		doSearchForModel(encounterNoServices);
		openMaintainDataBatch(encounterNoServices);
		waitForAjaxExtJs();
		waitForSpinnerToEnd();
		doClick(driver.findElement(By.xpath("//button/span[text()='Services']")));
		waitForSpinnerToEnd();
		waitForAjaxExtJs();
		assertTextIsDisplayed("There is no data available to display.");
		doClick(driver.findElement(By.xpath("//button/span[text()='Cancel & Close']")));
		 ExtentReport.logPass("PASS", "test03VerifyNoServicesAppearOnEncountersPage");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test03VerifyNoServicesAppearOnEncountersPage", driver, e);
		fail(e.getMessage());
	} 
  }

  @Test
  public void test04ClickAssignButtonAndAssertCalculationSummaryDetailsMatchExpected()
          throws InterruptedException,Throwable {
    try {
      //goToPage("Maintain Data");
      doMaintainDataPageSelectAtoZOption(aTozPage);
      tableClickColumnHeader("Name");
      openMaintainDataBatch("v10.4 REGRESSION Enc Serv Class Scheme");
      waitForAjaxExtJs();
      doClick(driver.findElement(By.xpath("//button/span[text()='Assign']")));
      waitForSpinnerToEnd();
      waitForFirstRowCalculationBarToReach100Percent();
      calculationStatusPageOpenViewDialog();
      assertViewLogTitle(viewLogTitleApply);
      confirmCalculationStatusDetailsContains("Total Encounters Processed: 40");
      confirmCalculationStatusDetailsContains("Process Completed");
      closeViewDialog();
      deleteMyCalculationStatusFirstRow();
      ExtentReport.logPass("PASS", "test04ClickAssignButtonAndAssertCalculationSummaryDetailsMatchExpected");
     	} catch (Exception|AssertionError e) {
     		ExtentReport.logFail("FAIL", "test04ClickAssignButtonAndAssertCalculationSummaryDetailsMatchExpected", driver, e);
     		fail(e.getMessage());
     	}  finally {
      try {
		doClosePageOnLowerBar("Calculation Status");
		  waitForAjaxExtJs();
		  doClick(driver.findElement(By.xpath("//button/span[text()='Save & Close']")));
		  waitForSpinnerToEnd();
	} catch (Exception|AssertionError e) {
 		ExtentReport.logFail("FAIL", "test04ClickAssignButtonAndAssertCalculationSummaryDetailsMatchExpected", driver, e);
 		fail(e.getMessage());
 	} 
    }
  }

  @Test
  public void test05AssertServicesCountOnEncountersPage() throws InterruptedException,Throwable{
      try {
		goToPage("Maintain Data");
		  doMaintainDataPageSelectAtoZOption("Encounters");
		  doSearchForModel(encounter);
		  openMaintainDataBatch(encounter);
		  waitForSpinnerToEnd();
		  waitForAjaxExtJs();
		  doClick(driver.findElement(By.xpath("//button/span[text()='Services']")));
		  waitForSpinnerToEnd();
		  waitForAjaxExtJs();
		  encountersTable = getEncountersTableRows();
		  assertThat(encountersTable.size(), equalTo(2));  //expected value includes header row
		  ExtentReport.logPass("PASS", "test05AssertServicesCountOnEncountersPage");
   	} catch (Exception|AssertionError e) {
   		ExtentReport.logFail("FAIL", "test05AssertServicesCountOnEncountersPage", driver, e);
   		fail(e.getMessage());
   	} 
  }

  @Test
  public void test06VerifyServicesNowAppearOnEncountersPage() throws Throwable {
    try {
		List<String> encountersTableStrings =
		        javaMakeListOfStrings(encountersTable, "//td[6]/div");
		//assertListOfStringsContainsExpectedStrings(encountersTableStrings, expectedEncounters);
		assertThat(encountersTableStrings, equalTo(expectedEncounters));
		doClick(driver.findElement(By.xpath("//button/span[text()='Cancel & Close']")));
		  ExtentReport.logPass("PASS", "test06VerifyServicesNowAppearOnEncountersPage");
   	} catch (Exception|AssertionError e) {
   		ExtentReport.logFail("FAIL", "test06VerifyServicesNowAppearOnEncountersPage", driver, e);
   		fail(e.getMessage());
   	} 
  }

//  @Ignore
//  @Test
//  public void test10QueryDatabaseAndAssertValuesAreCorrectForMedicalServiceAssignments()
//          throws ClassNotFoundException {
//    calculationsAssertDbRowCount(
//            GeneralCalculationsData.getMedicalServiceAssignmentSql,
//            "greater than",
//            0
//    );
//  }

  private List<WebElement> getEncountersTableRows() {
    return tableGetTableRows(
            driver.findElement(By.xpath("//*[text()='Service Scheme']/ancestor::div[contains(@class,'x-grid-header')]" +
                    "/following-sibling::div/descendant::table/tbody")),
            "tr")
    ;
  }
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}

//  @Test
//  public void test02QueryDatabaseAndAssertValuesAreCorrectForIpps2021TransfersA()
//          throws ClassNotFoundException {
//    getDataFromDatabaseAndAssertExpectedValues(
//            data.sqlQueryIpps2021TransfersA,
//            data.expectedValuesIpps2021TransfersA
//    );
//  }

