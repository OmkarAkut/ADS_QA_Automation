package webdriver.scripts.regression.ipps2021calculations;

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
public class Ipps2021TransfersAds3635 extends CalculationHelper {

  static DataMaintenanceMap dmMap;
  static String batchName = getVersion() + " REGRESSION 2021 IPPS: Transfers";
  final String expectedLogView = batchName + "\\Contracting\\Contract Batch";
  private Ipps2021Data data = new Ipps2021Data();

  /** Regression: Test script for ADS-3635 - 2021 IPPS: Transfers.
   *  Updated: 12/8/2020. */
  @BeforeClass
  public static void setupScript() throws Exception {
    dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
    System.out.println("Test Class: " + Ipps2021TransfersAds3635.class.getSimpleName());
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
  public void test02QueryDatabaseAndAssertValuesAreCorrectForIpps2021TransfersA()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2021TransfersA,
            data.expectedValuesIpps2021TransfersA
    );
  }

  @Test
  public void test03QueryDatabaseAndAssertValuesAreCorrectForIpps2021TransfersB()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2021TransfersB,
            data.expectedValuesIpps2021TransfersB
    );
  }

  @Test
  public void test04QueryDatabaseAndAssertValuesAreCorrectForIpps2021TransfersC()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2021TransfersC,
            data.expectedValuesIpps2021TransfersC
    );
  }

  @Test
  public void test05QueryDatabaseAndAssertValuesAreCorrectForIpps2021TransfersD()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2021TransfersD,
            data.expectedValuesIpps2021TransfersD
    );
  }
}
