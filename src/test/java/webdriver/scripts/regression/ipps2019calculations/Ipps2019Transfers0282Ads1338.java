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
public class Ipps2019Transfers0282Ads1338 extends CalculationHelper {

  static DataMaintenanceMap dmMap;
  static final String batchName = "v10 REGRESSION 2019 IPPS Transfer 02 82";
  final byte numberOfEfrs = 5;
  final byte nonCriticalWarnings = 0;
  final byte efrsCalculatedToZero = 0;
  final String expectedLogView = batchName + "\\Contracting\\Contract Batch";
  final String[] calcDetails = {
          "Contract: v10 REGRESSION 2019 IPPS Tran C MSDRG1-1",
          "Contract: v10 REGRESSION 2019 IPPS Tran D MSDRG1-1"
  };
  private Ipps2019CalculationsData data = new Ipps2019CalculationsData();

  /** Regression: Test script for ADS-1338 - 2019 IPPS: Transfers 02 82.
   * Updated: 11/22/19. */
  @BeforeClass
  public static void setupScript() throws Exception {
    dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
    System.out.println("Test Class: " + Ipps2019Transfers0282Ads1338.class.getSimpleName());
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
  public void test02QueryDatabaseAndAssertValuesAreCorrectForIpps2019Transfers0282K1()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2019Transfers0282K1,
            data.expectedValuesIpps2019Transfers0282K1
    );
  }

  @Test
  public void test03QueryDatabaseAndAssertValuesAreCorrectForIpps2019Transfers0282K2()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2019Transfers0282K2,
            data.expectedValuesIpps2019Transfers0282K2
    );
  }

  @Test
  public void test04QueryDatabaseAndAssertValuesAreCorrectForIpps2019Transfers0282L()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2019Transfers0282L,
            data.expectedValuesIpps2019Transfers0282L
    );
  }

  @Test
  public void test05QueryDatabaseAndAssertValuesAreCorrectForIpps2019Transfers0282M()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2019Transfers0282M,
            data.expectedValuesIpps2019Transfers0282M
    );
  }

}
