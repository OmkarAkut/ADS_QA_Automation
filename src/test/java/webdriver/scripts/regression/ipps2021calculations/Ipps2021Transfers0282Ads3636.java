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
public class Ipps2021Transfers0282Ads3636 extends CalculationHelper {

  static DataMaintenanceMap dmMap;
  static String batchName = getVersion() + " REGRESSION 2021 IPPS: Trans 02 82";
  final String expectedLogView = batchName + "\\Contracting\\Contract Batch";
  private Ipps2021Data data = new Ipps2021Data();

  /** Regression: Test script for ADS-3636 - 2021 IPPS: Transfers 02.
   *  Updated: 12/9/20. */
  @BeforeClass
  public static void setupScript() throws Exception {
    dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
    System.out.println("Test Class: " + Ipps2021Transfers0282Ads3636.class.getSimpleName());
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
  public void test02QueryDatabaseAndAssertValuesAreCorrectForIpps2021Transfers0282K1()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2021Transfers0282K1,
            data.expectedValuesIpps2021Transfers0282K1
    );
  }

  @Test
  public void test03QueryDatabaseAndAssertValuesAreCorrectForIpps2021Transfers0282K2()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2021Transfers0282K2,
            data.expectedValuesIpps2021Transfers0282K2
    );
  }

  @Test
  public void test04QueryDatabaseAndAssertValuesAreCorrectForIpps2021Transfers0282L()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2021Transfers0282L,
            data.expectedValuesIpps2021Transfers0282L
    );
  }

  @Test
  public void test05QueryDatabaseAndAssertValuesAreCorrectForIpps2021Transfers0282M()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2021Transfers0282M,
            data.expectedValuesIpps2021Transfers0282M
    );
  }

}
