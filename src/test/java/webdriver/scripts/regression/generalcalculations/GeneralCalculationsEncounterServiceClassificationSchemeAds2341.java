package webdriver.scripts.regression.generalcalculations;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import java.util.ArrayList;
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

  static String encounter = "OPPS2021DRUGRA001";
//  static String encounterNoServices = "OPPS2020DCA002";
  //Encounter Services
//  List<String> expectedEncounters = Arrays.asList(
//          "QAREGRESSION34567890123456789012345678901", //"PC Pop Fac or Non F"
//          "OPPS 2020"
//  );
  //Shilpa updated for 11.2 on 24.4.2024
  List<String> expectedEncounters = Arrays.asList(
          "QAREGRESSION34567890123456789012345678901", //"PC Pop Fac or Non F"
          "OPPS 2021"
  );
  static String viewLogTitleRemove = "Remove Encounter Service Classification";
  static String viewLogTitleApply = "Encounter Service Classification Scheme";

  static DataMaintenanceMap dmMap;
  final static String aTozPage = "Encounter Service Classification Schemes";
//  final static String batch = "v10.2 REGRESSION Enc Serv Class Scheme";
  final static String batch = "v10.4 REGRESSION Enc Serv Class Scheme";//updated 11.25.2022
  private static List<WebElement> encountersTable;
  String[] filterCostModel = { "Name", "Is", "Equal To", batch };
  /** Regression: Test script for ADS-2341. Updated: 7-7-21.,ADS-6102 */
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

//		tableClickColumnHeader("Name");
//		 Thread.sleep(5000);
//		openMaintainDataBatch(batch);
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "Failure to setupScript", driver, e);
		fail(e.getMessage());
	}
  }
//ADS-6102 all steps
  @Test
  public void test01ClickRemoveButtonAndVerifyRemoveProcessRanSuccessfully()
          throws InterruptedException,Throwable {
    try {
    	doClick(DataMaintenanceMap.getLoadDataFilterButton());
		doFilterCreate(filterCostModel);
		driverDelay(4000);// Shilpa 16.09.2022
		tableDoubleClickCellFirstColumn(batch);
//      waitForPresenceOfElement("//button/span[text()='Remove']");
//      doClick(driver.findElement(By.xpath("//button/span[text()='Remove']")));
		//Shilpa update for 11.2 on 11.24.2023
		waitForPresenceOfElement("//span[text()='Remove']/../../..");
		doClick(driver.findElement(By.xpath("//span[text()='Remove']/../../..")));
      waitForSpinnerToEnd();
      waitForFirstRowCalculationBarToReach100Percent();
      driverDelay(5000);
      calculationStatusPageOpenViewDialog();
      assertViewLogTitle(viewLogTitleRemove);
      confirmCalculationStatusDetailsContains("Process Completed");
      closeViewDialog();
      deleteFirstRow();
//      deleteMyCalculationStatusFirstRow();
      ExtentReport.logPass("PASS", "test01ClickRemoveButtonAndVerifyRemoveProcessRanSuccessfully");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test01ClickRemoveButtonAndVerifyRemoveProcessRanSuccessfully", driver, e);
		fail(e.getMessage());
	} finally {
      try {
		doClosePageOnLowerBar("Calculation Status");
//		  doClick(driver.findElement(By.xpath("//button/span[text()='Save & Close']")));
		//Shilpa update for xpath on 11.2 on 11.24.2023
		waitForPresenceOfElement(
				"(//div[contains(@class,'x-toolbar-footer')]//following::span[text()='Save & Close']/../../..)[1]");
		doClick(driver.findElement(By.xpath(
				"(//div[contains(@class,'x-toolbar-footer')]//following::span[text()='Save & Close']/../../..)[1]")));
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
//		doSearchForModel(encounterNoServices);
//		tableDoubleClickCellFirstColumn(encounterNoServices);
		doSearchForModel(encounter);
		tableDoubleClickCellFirstColumn(encounter);
//		openMaintainDataBatch(encounterNoServices);
		waitForAjaxExtJs();
		waitForSpinnerToEnd();
//		doClick(driver.findElement(By.xpath("//span[text()='Services']")));
		//Shilpa update xpath for 11.2 on 11.24.2023
		doClick(driver.findElement(By.xpath("//span[text()='Services']/../../..")));
		waitForSpinnerToEnd();
		waitForAjaxExtJs();
		assertTextIsDisplayed("There is no data available to display.");
//		doClick(driver.findElement(By.xpath("//button/span[text()='Cancel & Close']")));
		//Shilpa update xpath for 11.2 on 11.24.2023
		doClick(driver.findElement(By.xpath("(//div[contains(@class,'x-toolbar-footer')]//following::span[text()='Cancel & Close']/../../..)[1]")));
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
//      tableClickColumnHeader("Name");
      doClick(DataMaintenanceMap.getLoadDataFilterButton());
      doFilterCreate(filterCostModel);
		tableDoubleClickCellFirstColumn(batch);
//      openMaintainDataBatch("v10.4 REGRESSION Enc Serv Class Scheme");
      waitForAjaxExtJs();
//      doClick(driver.findElement(By.xpath("//button/span[text()='Assign']")));
      //Shilpa update xpath for 11.2 on 11.24.2023
      doClick(driver.findElement(By.xpath("//span[text()='Assign']/../../..")));
      waitForSpinnerToEnd();
      waitForFirstRowCalculationBarToReach100Percent();
      calculationStatusPageOpenViewDialog();
      assertViewLogTitle(viewLogTitleApply);
      confirmCalculationStatusDetailsContains("Total Encounters Processed: 40");
      confirmCalculationStatusDetailsContains("Process Completed");
      closeViewDialog();
      deleteFirstRow();
      ExtentReport.logPass("PASS", "test04ClickAssignButtonAndAssertCalculationSummaryDetailsMatchExpected");
     	} catch (Exception|AssertionError e) {
     		ExtentReport.logFail("FAIL", "test04ClickAssignButtonAndAssertCalculationSummaryDetailsMatchExpected", driver, e);
     		fail(e.getMessage());
     	}  finally {
      try {
		doClosePageOnLowerBar("Calculation Status");
		  waitForAjaxExtJs();
		  doClick(driver.findElement(By.xpath("(//div[contains(@class,'x-toolbar-footer')]//following::span[text()='Cancel & Close']/../../..)[1]")));
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
		  tableDoubleClickCellFirstColumn(encounter);
//		  openMaintainDataBatch(encounter);
		  waitForSpinnerToEnd();
		  waitForAjaxExtJs();
//		  doClick(driver.findElement(By.xpath("//button/span[text()='Services']")));
		  //Shilpa update xpath for 11.2 on 11.24.2024
		  doClick(driver.findElement(By.xpath("//span[text()='Services']/../../..")));
		  waitForSpinnerToEnd();
		  waitForAjaxExtJs();
		  Thread.sleep(2000);
		  encountersTable = getEncountersTableRows();
		  System.out.println(encountersTable.size());
		  //Shilpa added below lines for 11.2 on 11.24.2023
		  List<WebElement> ele=driver.findElements(By.xpath("//*[text()='Service Scheme']/ancestor::div[contains(@class,'x-grid-header')]//following-sibling::div/descendant::table//tbody/tr"));
		  ArrayList<WebElement> actualList = new ArrayList<>();
		  for(WebElement element:ele) {
			if (element.getAttribute("class").contains("x-grid-row")) {
				actualList.add(element);
			} else {
				continue;
			}
		}
//		  assertThat(encountersTable.size(), equalTo(2));  //expected value includes header row
		  assertThat(actualList.size(), equalTo(2));
		  ExtentReport.logPass("PASS", "test05AssertServicesCountOnEncountersPage");
   	} catch (Exception|AssertionError e) {
   		ExtentReport.logFail("FAIL", "test05AssertServicesCountOnEncountersPage", driver, e);
   		fail(e.getMessage());
   	} 
  }

  @Test
  public void test06VerifyServicesNowAppearOnEncountersPage() throws Throwable {
    try {
//		  List<WebElement> ele=driver.findElements(By.xpath("//*[text()='Service Scheme']/ancestor::div[contains(@class,'x-grid-header')]//following-sibling::div/descendant::table//tbody/tr"));
    	System.out.println();
//		List<String> encountersTableStrings =
//		        javaMakeListOfStrings(encountersTable, "//td[6]/div");
    	//Shilpa:xpath update for 11.2 on 24.4.2024
    	List<String> encountersTableStrings =
		        javaMakeListOfStrings(encountersTable, "//td[3]/div");
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
//    return tableGetTableRows(
//            driver.findElement(By.xpath("//*[text()='Service Scheme']/ancestor::div[contains(@class,'x-grid-header')]" +
//                    "/following-sibling::div/descendant::table/tbody")),
//            "tr")
//    ;
    return tableGetTableRows(
            driver.findElement(By.xpath("//*[text()='Service Scheme']/ancestor::div[contains(@class,'x-grid-header')]"+"//following-sibling::div/descendant::table//tbody")),
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

