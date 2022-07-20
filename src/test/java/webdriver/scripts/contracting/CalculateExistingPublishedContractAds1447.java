package webdriver.scripts.contracting;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CalculateExistingPublishedContractAds1447 extends CalculationHelper {

  static final String modelName = "v10 REGRESSION Published Contract";
  static final String status = "Completed";
  static final String logStatus = "Completed";
  static final String recordsProcessed = "1470";
  static final String recordsPending = "0";
  static final String totalRecords = "1470";
  final String expectedLogViewTitle = modelName + "\\Contracting\\Published Contract Calculation";
  String logDetailTotalEfrs = "Total EFRs to be processed: 1470";
  String[] logViewDetails = {
          "Number of Encounter Financial Records to Process: 1470",
          "Non-Critical Warnings: 433",
          "Encounter Financial Records Calculated to Zero: 218"
  };
  private static ModelLibraryMap modelMap;

  /** Regression: Automated test script for ADS-1447 */
  @BeforeClass
  public static void setupScript() throws Exception {
    modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
    System.out.println("Test Class: " + CalculateExistingPublishedContractAds1447.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    goToPage("Contract Models");
    waitForSpinnerToEnd();
    filterAndSelectContractModelFromContractModelLibrary(modelName);
  }

  @Test
  public void test01RunPublishedContractAndVerifyStatusPageDetailsAfterCompleted() throws InterruptedException {
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    modelMap.getModelLibraryContractingButtonCalculate().click();
    waitForCalculationToEnd(90);
    waitForPresenceOfElement("//div[2]/div/div[4]/div/table/tbody/tr[2]/td[17]/div");  //total records xpath
    assertTrue(getCalculationStatusMyStatusFirstRowStatusCellText().contains(status));
    assertTrue(getCalculationStatusMyStatusFirstRowLogStatusCellText().contains(logStatus));
    assertTrue(getCalculationStatusMyStatusFirstRowRecordsProcessedCellText().contains(recordsProcessed));
    assertTrue(getCalculationStatusMyStatusFirstRowRecordsPendingCellText().contains(recordsPending));
    assertTrue(getCalculationStatusMyStatusFirstRowTotalRecordsCellText().contains(totalRecords));
  }

  @Test
  public void test02OpenViewDialogAndAssertTotalEfrs() throws InterruptedException {
	Thread.sleep(2000);
    calculationStatusPageOpenViewDialog();
    waitForPresenceOfElement("//div[4]/div/div/div[2]/div/div/div[4][contains(@id, 'tbtext')]");
    assertViewLogTitle(expectedLogViewTitle);
    confirmCalculationStatusDetailsContains(logDetailTotalEfrs);
  }

  @Test
  public void test03OnLastPageOfViewDialogAssertLogDetails() {
    clickLastPageIconOnCalculationStatusViewLog();
    waitForSpinnerToEnd();
    waitForPresenceOfElement("//*[text() = 'CALCULATION SUMMARY']");
    confirmCalculationStatusDetailsContains(logViewDetails);
    closeViewDialog();
  }

  @Test
  public void test04DeleteCalculationStatusPageF() throws InterruptedException {
    deleteCalculationStatusMyStatusPageFirstRow();
  }

}
