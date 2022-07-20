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
public class Ipps2021DeviceCreditAds3637 extends CalculationHelper {

  static DataMaintenanceMap dmMap;
  static final String batchName = getVersion() + " REGRESSION 2021 IPPS: Device Credit";
  final String expectedLogView = batchName + "\\Contracting\\Contract Batch";
  private Ipps2021Data data = new Ipps2021Data();

  /** Regression: Test script for ADS-3637 - 2021 IPPS: Device Credit. */
  @BeforeClass
  public static void setupScript() throws Exception {
    dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
    System.out.println("Test Class: " + Ipps2021DeviceCreditAds3637.class.getSimpleName());
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
  public void test02QueryDatabaseAndAssertValuesAreCorrectForIpps2021DeviceCreditA()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2021DeviceCreditA,
            data.expectedValuesIpps2021DeviceCreditA
    );
  }

  @Test
  public void test03QueryDatabaseAndAssertValuesAreCorrectForIpps2021DeviceCreditB()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2021DeviceCreditB,
            data.expectedValuesIpps2021DeviceCreditB
    );
  }

  @Test
  public void test04QueryDatabaseAndAssertValuesAreCorrectForIpps2021DeviceCreditE()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2021DeviceCreditE,
            data.expectedValuesIpps2021DeviceCreditE
    );
  }

  @Test
  public void test05QueryDatabaseAndAssertValuesAreCorrectForIpps2021DeviceCreditF()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2021DeviceCreditF,
            data.expectedValuesIpps2021DeviceCreditF
    );
  }

}
