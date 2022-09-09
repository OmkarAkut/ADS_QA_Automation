package webdriver.templates;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.scripts.regression.ipps2021calculations.Ipps2021Data;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IppsAndOppsCalculations extends CalculationHelper {

  static DataMaintenanceMap dmMap;
  static final String batchName = "v104 REGRESSION 2021 IPPS: Transfers";//Shilpa. 19.08.2022 updated the name from v104 REGRESSION 2021 IPPS Transfers to v104 REGRESSION 2021 IPPS: Transfers 
  final String expectedLogView = batchName + "\\Contracting\\Contract Batch";
  private Ipps2021Data data = new Ipps2021Data();

  /** Regression: Test script for ADS-3635 - 2021 IPPS: Transfers.
   *  Updated: 12/8/2020. */
  @BeforeClass
  public static void setupScript() throws Exception {
    dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
    System.out.println("Test Class: " + IppsAndOppsCalculations.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    goToMaintainDataPageAndSelectContractBatch(batchName);
  }

  @Test
  public void test01ClickResetAndVerifyDatabaseValuesAreNull() throws ClassNotFoundException, InterruptedException {
    waitForAjaxExtJs();
    waitForPresenceOfElement("//*[text()='Refresh']");
    doClick(driver.findElement(By.xpath("//*[text()='Refresh']")));
    waitForSpinnerToEnd();
    waitForFirstRowCalculationBarToReach100Percent(2000);
    deleteMyCalculationStatusFirstRow();
    getStringDataFromDatabaseAndAssertValuesAreNull(
            data.sqlQueryIpps2021PsychComorbidity,
            2
    );
    doClosePageOnLowerBar("Calculation Status");
  }

  @Test
  public void test01ClickCalculateButtonAndAssertCalculationSummaryDetailsMatchExpected() {
    try {
      doClick(dmMap.getMaintainDataPageButtonCalculate());
      waitForCalculationToEndAndVerifyViewLogContainsOnViewDialogAndCloseDialog(
              expectedLogView
      );
      deleteMyCalculationStatusFirstRow();
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test02QueryDatabaseAndAssertValuesAreCorrectForIpps2021TransfersA()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2021TransfersA,
            data.expectedValuesIpps2021TransfersA
    );
  }

}
