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
public class Ipps2020DeviceCreditAds1647 extends CalculationHelper {

  static DataMaintenanceMap dmMap;
  static final String batchName = "v101 REGRESSION 2020 IPPS: Device Credit";
  final String expectedLogView = batchName + "\\Contracting\\Contract Batch";
  private Ipps2020Data data = new Ipps2020Data();

  /** Regression: Test script for ADS-1647 - 2020 IPPS: Device Credit. */
  @BeforeClass
  public static void setupScript() throws Exception {
    dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
    System.out.println("Test Class: " + Ipps2020DeviceCreditAds1647.class.getSimpleName());
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
  public void test02QueryDatabaseAndAssertValuesAreCorrectForIpps2020DeviceCreditA()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2020DeviceCreditA,
            data.expectedValuesIpps2020DeviceCreditA
    );
  }

  @Test
  public void test03QueryDatabaseAndAssertValuesAreCorrectForIpps2020DeviceCreditB()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2020DeviceCreditB,
            data.expectedValuesIpps2020DeviceCreditB
    );
  }

  @Test
  public void test04QueryDatabaseAndAssertValuesAreCorrectForIpps2020DeviceCreditE()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2020DeviceCreditE,
            data.expectedValuesIpps2020DeviceCreditE
    );
  }

  @Test
  public void test05QueryDatabaseAndAssertValuesAreCorrectForIpps2020DeviceCreditF()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2020DeviceCreditF,
            data.expectedValuesIpps2020DeviceCreditF
    );
  }

}
