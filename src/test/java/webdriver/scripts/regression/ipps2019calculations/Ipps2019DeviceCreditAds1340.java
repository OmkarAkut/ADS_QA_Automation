package webdriver.scripts.regression.ipps2019calculations;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Ipps2019DeviceCreditAds1340 extends CalculationHelper {

  static DataMaintenanceMap dmMap;
  static final String batchName = "v10 REGRESSION 2019 IPPS Device Credit";
  final byte numberOfEfrs = 5;
  final byte nonCriticalWarnings = 0;
  final byte efrsCalculatedToZero = 0;
  final String expectedLogView = "v10 REGRESSION 2019 IPPS Device Credit\\Contracting\\Contract Batch";
  private Ipps2019CalculationsData data = new Ipps2019CalculationsData();

  /** Regression: Test script for ADS-1340 - 2019 IPPS: Device Credit.
   * Updated: 11/22/19. */
  @BeforeClass
  public static void setupScript() throws Exception {
    dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
    System.out.println("Test Class: " + Ipps2019DeviceCreditAds1340.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    goToMaintainDataPageAndSelectContractBatch(batchName);
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
  public void test02QueryDatabaseAndAssertValuesAreCorrectForIpps2019DeviceCreditA()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2019DeviceCreditA,
            data.expectedValuesIpps2019DeviceCreditA
    );
  }

  @Test
  public void test03QueryDatabaseAndAssertValuesAreCorrectForIpps2019DeviceCreditB()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2019DeviceCreditB,
            data.expectedValuesIpps2019DeviceCreditB
    );
  }

  @Test
  public void test04QueryDatabaseAndAssertValuesAreCorrectForIpps2019DeviceCreditE()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2019DeviceCreditE,
            data.expectedValuesIpps2019DeviceCreditE
    );
  }

  @Test
  public void test05QueryDatabaseAndAssertValuesAreCorrectForIpps2019DeviceCreditF()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2019DeviceCreditF,
            data.expectedValuesIpps2019DeviceCreditF
    );
  }

}
