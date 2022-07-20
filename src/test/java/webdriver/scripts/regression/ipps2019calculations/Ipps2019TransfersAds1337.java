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
public class Ipps2019TransfersAds1337 extends CalculationHelper {


  static DataMaintenanceMap dmMap;
  static final String batchName = "v10 REGRESSION 2019 IPPS Transfers";
  final String expectedLogView = "v10 REGRESSION 2019 IPPS Transfers\\Contracting\\Contract Batch";
  final String[] calcDetails = {
          "Contract: v10 REGRESSION 2019 IPPS Tran C MSDRG1-1",
          "Contract: v10 REGRESSION 2019 IPPS Tran D MSDRG1-1"
  };
  private Ipps2019CalculationsData data = new Ipps2019CalculationsData();

  /** Regression: Test script for ADS-1337 - 2019 IPPS: Transfers.
   * Updated: 11/22/19. */
  @BeforeClass
  public static void setupScript() throws Exception {
    dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
    System.out.println("Test Class: " + Ipps2019TransfersAds1337.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    goToMaintainDataPageAndSelectContractBatch(batchName);
  }

  @Test
  public void test01ClickCalculateButtonAndAssertCalculationSummaryDetailsMatchExpected() {
    try {
      doClick(dmMap.getMaintainDataPageButtonCalculate());
      waitForCalculationToEndAndVerifySummaryDetailsOnViewDialogAndCloseDialog(
              expectedLogView,
              calcDetails
      );
      deleteMyCalculationStatusFirstRow();
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test02QueryDatabaseAndAssertValuesAreCorrectForIpps2019TransfersA()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2019TransfersA,
            data.expectedValuesIpps2019TransfersA
    );
  }

  @Test
  public void test03QueryDatabaseAndAssertValuesAreCorrectForIpps2019TransfersB()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2019TransfersB,
            data.expectedValuesIpps2019TransfersB
    );
  }

  @Test
  public void test04QueryDatabaseAndAssertValuesAreCorrectForIpps2019TransfersC()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2019TransfersC,
            data.expectedValuesIpps2019TransfersC
    );
  }

  @Test
  public void test05QueryDatabaseAndAssertValuesAreCorrectForIpps2019TransfersD()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2019TransfersD,
            data.expectedValuesIpps2019TransfersD
    );
  }

}
