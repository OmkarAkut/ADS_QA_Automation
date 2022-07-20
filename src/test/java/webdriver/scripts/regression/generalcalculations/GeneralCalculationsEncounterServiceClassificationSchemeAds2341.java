package webdriver.scripts.regression.generalcalculations;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GeneralCalculationsEncounterServiceClassificationSchemeAds2341 extends CalculationHelper {

  static String encounter = "OPPS2020DCA001";
  List<String> expectedEncounters = Arrays.asList(
          "QAREGRESSION34567890123456789012345678901", //"PC Pop Fac or Non F"
          "OPPS 2020"
  );
  static String viewLogTitleRemove = "Remove Encounter Service Classification";
  static String viewLogTitleApply = "Remove Encounter Service Classification";

  static DataMaintenanceMap dmMap;
  final static String aTozPage = "Encounter Service Classification Schemes";
  final static String batch = "v10.2 REGRESSION Enc Serv Class Scheme";
  private static List<WebElement> encountersTable;

  /** Regression: Test script for ADS-2341. Updated: 7-7-21. */
  @BeforeClass
  public static void setupScript() throws Exception {
    dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
    System.out.println(
      "Test Class: " + GeneralCalculationsEncounterServiceClassificationSchemeAds2341.class.getSimpleName()
    );
    Login.loginUser("ContractAnalyst1");
    goToPage("Maintain Data");
    selectMaintainDataAtoZ(aTozPage);
    tableClickColumnHeader("Name");
    openMaintainDataBatch(batch);
  }

  @Test
  public void test01ClickRemoveButtonAndVerifyRemoveProcessRanSuccessfully()
          throws InterruptedException {
    try {
      waitForPresenceOfElement("//button/span[text()='Remove']");
      doClick(driver.findElement(By.xpath("//button/span[text()='Remove']")));
      waitForSpinnerToEnd();
      waitForFirstRowCalculationBarToReach100Percent();
      calculationStatusPageOpenViewDialog();
      assertViewLogTitle(viewLogTitleRemove);
      confirmCalculationStatusDetailsContains("Process Completed");
      closeViewDialog();
      deleteMyCalculationStatusFirstRow();
    } catch(Throwable e) {
      fail(e.getMessage());
    } finally {
      doClosePageOnLowerBar("Calculation Status");
      doClick(driver.findElement(By.xpath("//button/span[text()='Save & Close']")));
      waitForSpinnerToEnd();
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
          throws InterruptedException {
    doMaintainDataPageSelectAtoZOption("Encounters");
    doSearchForModel(encounter);
    openMaintainDataBatch(encounter);
    waitForAjaxExtJs();
    waitForSpinnerToEnd();
    doClick(driver.findElement(By.xpath("//button/span[text()='Services']")));
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    assertTextIsDisplayed("There is no data available to display.");
    doClick(driver.findElement(By.xpath("//button/span[text()='Cancel & Close']")));
  }

  @Test
  public void test04ClickAssignButtonAndAssertCalculationSummaryDetailsMatchExpected()
          throws InterruptedException {
    try {
      //goToPage("Maintain Data");
      doMaintainDataPageSelectAtoZOption(aTozPage);
      tableClickColumnHeader("Name");
      openMaintainDataBatch("v10.2 REGRESSION Enc Serv Class Scheme");
      waitForAjaxExtJs();
      doClick(driver.findElement(By.xpath("//button/span[text()='Assign']")));
      waitForSpinnerToEnd();
      waitForFirstRowCalculationBarToReach100Percent();
      calculationStatusPageOpenViewDialog();
      assertViewLogTitle(viewLogTitleApply);
      confirmCalculationStatusDetailsContains("Total Encounters Processed: 26.");
      confirmCalculationStatusDetailsContains("Process Completed");
      closeViewDialog();
      deleteMyCalculationStatusFirstRow();
    } catch(Throwable e) {
      fail(e.getMessage());
    } finally {
      doClosePageOnLowerBar("Calculation Status");
      waitForAjaxExtJs();
      doClick(driver.findElement(By.xpath("//button/span[text()='Save & Close']")));
      waitForSpinnerToEnd();
    }
  }

  @Test
  public void test05AssertServicesCountOnEncountersPage() throws InterruptedException {
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
  }

  @Test
  public void test06VerifyServicesNowAppearOnEncountersPage() {
    List<String> encountersTableStrings =
            javaMakeListOfStrings(encountersTable, "//td[6]/div");
    //assertListOfStringsContainsExpectedStrings(encountersTableStrings, expectedEncounters);
    assertThat(encountersTableStrings, equalTo(expectedEncounters));
    doClick(driver.findElement(By.xpath("//button/span[text()='Cancel & Close']")));
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

}

//  @Test
//  public void test02QueryDatabaseAndAssertValuesAreCorrectForIpps2021TransfersA()
//          throws ClassNotFoundException {
//    getDataFromDatabaseAndAssertExpectedValues(
//            data.sqlQueryIpps2021TransfersA,
//            data.expectedValuesIpps2021TransfersA
//    );
//  }

