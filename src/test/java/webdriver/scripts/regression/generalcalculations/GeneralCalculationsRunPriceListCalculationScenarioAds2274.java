package webdriver.scripts.regression.generalcalculations;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import java.util.List;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GeneralCalculationsRunPriceListCalculationScenarioAds2274 extends CalculationHelper {

  static String viewLogTitle = "Remove Encounter Service Classification";
  final static String aTozPage = "Price Lists";
  final static String batch = "v10.2 REGRESSION Price List Enc Assign";
  final static String xSourcePriceList = "QAASSIGNPL";
  final static String xDestinationChargeScenario = "Estimated Charges 15";
  private static List<WebElement> encountersTable;

  /** Regression: Test script for ADS-2343. Updated: 7-7-21. */
  @BeforeClass
  public static void setupScript() throws Exception {
    System.out.println(
            "Test Class: " + GeneralCalculationsRunPriceListCalculationScenarioAds2274.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    goToPage("Maintain Data");
    selectMaintainDataAtoZ(aTozPage);
    openMaintainDataBatch(batch);
  }

  @Test
  public void test00ConfirmParametersOnPage() {
    String sourcePriceList = null;
    String destinationChargeScenario = null;
    assertThat(sourcePriceList, equalTo(xSourcePriceList));
    assertThat(destinationChargeScenario, equalTo(xDestinationChargeScenario));
  }

  @Test
  public void test01ClickClearResultsButtonAndVerifyCalculationStatusPageDetails()
          throws InterruptedException {
    try {
      waitForAjaxExtJs();
      waitForPresenceOfElement("//button/span[text()='Clear Results']");
      doClick(getButton("Clear Results"));
      waitForSpinnerToEnd();
      waitForFirstRowCalculationBarToReach100Percent();
      calculationStatusPageOpenViewDialog();
      assertViewLogTitle(viewLogTitle);
      confirmCalculationStatusDetailsContains("Process Completed");
      confirmCalculationStatusDetailsContains("Total number of Charge Items reset = 25");
      closeViewDialog();
      deleteMyCalculationStatusFirstRow();
    } catch(Throwable e) {
      fail(e.getMessage());
    } finally {
      doClosePageOnLowerBar("Calculation Status");
    }
  }

  @Test
  public void test02VerifyDatabaseValueCountIsZero() throws ClassNotFoundException {
//    calculationsAssertDbRowCount(
//            GeneralCalculationsData.getPriceListEncounterAssignmentSql,
//            "equal to",
//            0
//    );
  }

  @Test
  public void test02ClickCalculateButtonAndAssertCalculationStatusSummaryDetailsMatchExpected()
          throws InterruptedException {
    try {
      waitForAjaxExtJs();
      waitForPresenceOfElement("//button/span[text()='Assign']");
      doClick(getButton("Assign"));
      waitForSpinnerToEnd();
      waitForFirstRowCalculationBarToReach100Percent();
      calculationStatusPageOpenViewDialog();
      assertViewLogTitle(viewLogTitle);
      confirmCalculationStatusDetailsContains("Total number of charge items assigned = 25");
      confirmCalculationStatusDetailsContains("Total number of charge items processed = 25");
      clickLastPageIconOnCalculationStatusViewLog();
      confirmCalculationStatusDetailsContains("Process Completed");
      closeViewDialog();
      deleteMyCalculationStatusFirstRow();
    } catch(Throwable e) {
      fail(e.getMessage());
    } finally {
      doClosePageOnLowerBar("Calculation Status");
      waitForPresenceOfElement("//button/span[text()='Cancel & Close']");
      doClick(getWebElement("//button/span[text()='Cancel & Close']"));
      doClosePageOnLowerBar("Maintain Data");
    }
  }

  @Test
  public void test03QueryDatabaseAndAssertValuesAreCorrectForPriceListEncounterAssignments()
          throws ClassNotFoundException {
//    calculationsAssertDbRowCount(
//            GeneralCalculationsData.getPriceListEncounterAssignmentSql,
//            "equal",
//            GeneralCalculationsData.getPriceListEncounterAssignmentExpectedValue
//    );
  }

}
