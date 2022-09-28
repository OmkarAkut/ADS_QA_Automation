package webdriver.scripts.regression.generalcalculations;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
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
public class GeneralCalculationsChargeItemServiceClassificationSchemeAds2342 extends CalculationHelper {

  static String viewLogTitleRemove = "Remove Charge Item Service Classification";
	//static String viewLogTitleRemove = "Remove Price List to Encounters Assignment";
	static String viewLogTitleAssign = "Charge Item Service Classification Scheme";
  static String xRecordsProcessed = "26";
  static DataMaintenanceMap dmMap;
  final static String aTozPage = "Charge Item Service Classification Schemes";
  final static String batch = "V10.2 REGRESSION ChgItem Serv Class Sch";
//  final static String batch = "v10.2 REGRESSION Price List Enc Assign";
  private static List<WebElement> encountersTable;
  static String encounter = "OPPS2020DCA005";
  List<String> expectedEncounters = Arrays.asList(
          "QA REGRESSION CHG>0",
          "QA REGRESSION CHG>0"
  );
  static final String recordsProcessedXpath = "//div[2]/div/div[4]/div/table/tbody/tr[2]/td[15]/div";

  /** Regression: Test script for ADS-2341. Updated: 7-8-21. */
  @BeforeClass
  public static void setupScript() throws Exception,Throwable {
	  ExtentReport.reportCreate("GeneralCalculationsChargeItemServiceClassificationSchemeAds2342", "webdriver.scripts.regression.generalcalculations", "GeneralCalculationsChargeItemServiceClassificationSchemeAds2342");
    try {
		dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
		System.out.println(
		  "Test Class: " + GeneralCalculationsChargeItemServiceClassificationSchemeAds2342.class.getSimpleName()
		);
		Login.loginUser("ContractAnalyst1");
		goToPage("Maintain Data");
		selectMaintainDataAtoZ(aTozPage);
		openMaintainDataBatch(batch);
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "Failure in setupScript ", driver, e);
		fail(e.getMessage());
	} 
  }

  @Test
  public void test01ClickRemoveButtonAndVerifyRemoveProcessRanSuccessfully()
          throws InterruptedException,Throwable {
    try {
  	  driverDelay();//Shilpa 16.09.2022

      waitForPresenceOfElement("(//button/span[text()='Remove'])");
      doClick(driver.findElement(By.xpath("(//button/span[text()='Remove'])")));
      waitForSpinnerToEnd();
      driverDelay();
      waitForFirstRowCalculationBarToReach100Percent();
      driverDelay();
    //Shilpa 16.09.2022 added wait
      calculationStatusPageOpenViewDialog();
      //waitForElementToBeVisible(driver.findElement(By.xpath("//span[text()='View Log']")));
driverDelay(5000);
      assertViewLogTitle(viewLogTitleRemove);
      confirmCalculationStatusDetailsContains("Process Completed");
      closeViewDialog();
      deleteMyCalculationStatusFirstRow();
      ExtentReport.logPass("PASS", "test01ClickRemoveButtonAndVerifyRemoveProcessRanSuccessfully");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test01ClickRemoveButtonAndVerifyRemoveProcessRanSuccessfully", driver, e);
		fail(e.getMessage());
	}  finally {
      try {
		doClosePageOnLowerBar("Calculation Status");
		  waitForPresenceOfElement("//button/span[text()='Save & Close']");      

		  doClick(driver.findElement(By.xpath("//button/span[text()='Save & Close']")));
		  waitForSpinnerToEnd();
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test01ClickRemoveButtonAndVerifyRemoveProcessRanSuccessfully", driver, e);
		fail(e.getMessage());
	} 
    }
  }

  @Test
  public void test03VerifyNoServicesAppearOnEncountersPage()
          throws InterruptedException ,Throwable{
    try {
		doMaintainDataPageSelectAtoZOption("Encounters");
		doSearchForModel(encounter);
		openMaintainDataBatch(encounter);
		waitForAjaxExtJs();
		waitForSpinnerToEnd();
		doClick(driver.findElement(By.xpath("//button/span[text()='Charges']")));
		waitForSpinnerToEnd();
		waitForAjaxExtJs();
		waitForPresenceOfElement("//label[text()='Charge Item Services']/preceding-sibling::input");
		doClick(getWebElement("//label[text()='Charge Item Services']/preceding-sibling::input"));
		waitForSpinnerToEnd();
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
          throws InterruptedException ,Throwable{
    try {
    	
      doMaintainDataPageSelectAtoZOption(aTozPage);
      driverDelay(5000);
      openMaintainDataBatch(batch);
      waitForAjaxExtJs();
      doClick(driver.findElement(By.xpath("//button/span[text()='Assign']")));
      waitForSpinnerToEnd();
      waitForFirstRowCalculationBarToReach100Percent();
      driverDelay(5000);
      String recordsProcessedText = getWebElement(recordsProcessedXpath).getText();
      assertEquals(xRecordsProcessed, recordsProcessedText);
      calculationStatusPageOpenViewDialog();
    //Shilpa 16.09.2022
      driverDelay(5000);
      assertViewLogTitle(viewLogTitleAssign);
      confirmCalculationStatusDetailsContains("Process Completed");
      closeViewDialog();
      driverDelay(3000);
      deleteMyCalculationStatusFirstRow();
      ExtentReport.logPass("PASS", "test04ClickAssignButtonAndAssertCalculationSummaryDetailsMatchExpected");
     	} catch (Exception|AssertionError e) {
     		ExtentReport.logFail("FAIL", "test04ClickAssignButtonAndAssertCalculationSummaryDetailsMatchExpected", driver, e);
     		fail(e.getMessage());
     	}  
    finally {
      try {
		doClosePageOnLowerBar("Calculation Status");
		  waitForAjaxExtJs();
		  driverDelay();//Shilpa 16.09.2022
		  doClick(driver.findElement(By.xpath("//button/span[text()='Save & Close']")));
		  waitForSpinnerToEnd();
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test01ClickRemoveButtonAndVerifyRemoveProcessRanSuccessfully", driver, e);
		fail(e.getMessage());
	} 
    }
  }

  @Test
  public void test05AssertServicesCountOnEncountersPage() throws InterruptedException ,Throwable{
      try {
		goToPage("Maintain Data");
		  doMaintainDataPageSelectAtoZOption("Encounters");
		  doSearchForModel(encounter);
		  openMaintainDataBatch(encounter);
		  waitForAjaxExtJs();
		waitForSpinnerToEnd();
		  doClick(driver.findElement(By.xpath("//button/span[text()='Charges']")));
		  waitForSpinnerToEnd();
		  waitForAjaxExtJs();
		  waitForPresenceOfElement("//label[text()='Charge Item Services']/preceding-sibling::input");
		  doClick(getWebElement("//label[text()='Charge Item Services']/preceding-sibling::input"));
		  waitForSpinnerToEnd();
		  encountersTable = getEncountersTableRows();
		  assertThat(encountersTable.size(), equalTo(2));
		  ExtentReport.logPass("PASS", "test05AssertServicesCountOnEncountersPage");
	
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test05AssertServicesCountOnEncountersPage", driver, e);
		fail(e.getMessage());
	}   //expected value includes header row
  }

  @Test
  public void test06VerifyServicesNowAppearOnEncountersPage() throws InterruptedException,Throwable {
    try {
      List<String> encountersTableStrings =
              javaMakeListOfStrings(encountersTable, "//td[3]/div");
      assertThat(encountersTableStrings, equalTo(expectedEncounters));
      doClick(driver.findElement(By.xpath("//button/span[text()='Cancel & Close']")));
      ExtentReport.logPass("PASS", "test06VerifyServicesNowAppearOnEncountersPage");
  	
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test06VerifyServicesNowAppearOnEncountersPage", driver, e);
		fail(e.getMessage());
	}    finally {
      try {
		doClosePageOnLowerBar("Maintain Data");
		  waitForLandingPageFooter();
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test06VerifyServicesNowAppearOnEncountersPage", driver, e);
		fail(e.getMessage());
	} 
    }
  }

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

