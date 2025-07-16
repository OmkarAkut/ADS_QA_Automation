package webdriver.scripts.regression.ipps2020calculations;

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
public class Ipps2020TransfersAds1638 extends CalculationHelper {

  static DataMaintenanceMap dmMap;
  static final String batchName = "v101 REGRESSION 2020 IPPS: Transfers";
  final String expectedLogView = batchName + "\\Contracting\\Contract Batch";
  private Ipps2020Data data = new Ipps2020Data();

  /** Regression: Test script for ADS-1638 - 2020 IPPS: Transfers.
   *  Updated: 11/22/2019. */
  @BeforeClass
  public static void setupScript() throws Exception {
    dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
    System.out.println("Test Class: " + Ipps2020TransfersAds1638.class.getSimpleName());
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
  public void test02QueryDatabaseAndAssertValuesAreCorrectForIpps2020TransfersA()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2020TransfersA,
            data.expectedValuesIpps2020TransfersA
    );
  }

  @Test
  public void test03QueryDatabaseAndAssertValuesAreCorrectForIpps2020TransfersB()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2020TransfersB,
            data.expectedValuesIpps2020TransfersB
    );
  }

  @Test
  public void test04QueryDatabaseAndAssertValuesAreCorrectForIpps2020TransfersC()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2020TransfersC,
            data.expectedValuesIpps2020TransfersC
    );
  }

  @Test
  public void test05QueryDatabaseAndAssertValuesAreCorrectForIpps2020TransfersD()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2020TransfersD,
            data.expectedValuesIpps2020TransfersD
    );
  }
}
