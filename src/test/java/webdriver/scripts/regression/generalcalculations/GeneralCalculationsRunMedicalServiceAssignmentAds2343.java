package webdriver.scripts.regression.generalcalculations;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GeneralCalculationsRunMedicalServiceAssignmentAds2343 extends CalculationHelper {

  static String viewLogTitleClear = "Clear Medical Service Assignment";
  static String viewLogTitleAssign = "Medical Service Assignment";
  final static String aTozPage = "Medical Service Assignments";
  final static String batch = "V10.2 REGRESSION Med Serv Assign";

  /** Regression: Test script for ADS-2343. Updated: 7-7-21. */
  @BeforeClass
  public static void setupScript() throws Exception {
    System.out.println(
            "Test Class: " + GeneralCalculationsRunMedicalServiceAssignmentAds2343.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    goToPage("Maintain Data");
    selectMaintainDataAtoZ(aTozPage);
    openMaintainDataBatch(batch);
  }

  @Test
  public void test01ClickClearResultsButtonAndVerifyCalculationStatusDetails()
          throws InterruptedException {
    try {
      waitForAjaxExtJs();
      waitForPresenceOfElement("//button/span[text()='Clear Results']");
      doClick(getButton("Clear Results"));
      waitForSpinnerToEnd();
      waitForFirstRowCalculationBarToReach100Percent();
      calculationStatusPageOpenViewDialog();
      assertViewLogTitle(viewLogTitleClear);
      confirmCalculationStatusDetailsContains("Number of batches to process: 1");
      confirmCalculationStatusDetailsContains("Process Completed");
      closeViewDialog();
      deleteMyCalculationStatusFirstRow();
    } catch(Throwable e) {
      fail(e.getMessage());
    } finally {
      doClosePageOnLowerBar("Calculation Status");
    }
  }

//  @Test
//  public void test02VerifyDatabaseValueCountIsZero()
//          throws ClassNotFoundException {
//    calculationsAssertDbRowCount(
//            GeneralCalculationsData.getMedicalServiceAssignmentSql,
//            "equal to",
//            0
//    );
//  }

  @Test
  public void test02ClickAssignButtonAndAssertCalculationStatusPageDetails()
          throws InterruptedException {
    try {
      waitForAjaxExtJs();
      waitForPresenceOfElement("//button/span[text()='Assign']");
      doClick(getButton("Assign"));
      waitForSpinnerToEnd();
      waitForFirstRowCalculationBarToReach100Percent(2000);
      calculationStatusPageOpenViewDialog();
      assertViewLogTitle(viewLogTitleAssign);
      confirmCalculationStatusDetailsContains("Number of batches to process: 1");
      clickLastPageIconOnCalculationStatusViewLog();
      confirmCalculationStatusDetailsContains("Process Completed");
      closeViewDialog();
      deleteMyCalculationStatusFirstRow();
    } catch(Throwable e) {
      fail(e.getMessage());
    } finally {
      doClosePageOnLowerBar("Calculation Status");
      waitForPresenceOfElement("//button/span[text()='Assign']");
      doClosePageOnLowerBar("Maintain Data");
    }
  }

//  @Test
//  public void test03QueryDatabaseAndAssertValuesAreCorrectForMedicalServiceAssignments()
//          throws ClassNotFoundException {
//    if (testEnvironment.contains("echelon")) {
//      calculationsAssertDbRowCount(
//              GeneralCalculationsData.getMedicalServiceAssignmentSql,
//              "equal",
//              GeneralCalculationsData.getMedicalServiceAssignmentExpectedValueEchelon
//      );
//    } else {
//      calculationsAssertDbRowCount(
//              GeneralCalculationsData.getMedicalServiceAssignmentSql,
//              "equal",
//              GeneralCalculationsData.getMedicalServiceAssignmentExpectedValue
//      );
//    }
//  }

}
