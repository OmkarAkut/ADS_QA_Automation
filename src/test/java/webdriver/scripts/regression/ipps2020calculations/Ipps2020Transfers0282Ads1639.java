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
public class Ipps2020Transfers0282Ads1639 extends CalculationHelper {

  static DataMaintenanceMap dmMap;
  static final String batchName = "v101 REGRESSION 2020 IPPS: Trans 02 82";
  final String expectedLogView = batchName + "\\Contracting\\Contract Batch";
  private Ipps2020Data data = new Ipps2020Data();

  /** Regression: Test script for ADS-1639 - 2020 IPPS: Transfers 02 82.
   *  Updated: 11/22/19. */
  @BeforeClass
  public static void setupScript() throws Exception {
    dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
    System.out.println("Test Class: " + Ipps2020Transfers0282Ads1639.class.getSimpleName());
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
  public void test02QueryDatabaseAndAssertValuesAreCorrectForIpps2020Transfers0282K1()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2020Transfers0282K1,
            data.expectedValuesIpps2020Transfers0282K1
    );
  }

  @Test
  public void test03QueryDatabaseAndAssertValuesAreCorrectForIpps2020Transfers0282K2()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2020Transfers0282K2,
            data.expectedValuesIpps2020Transfers0282K2
    );
  }

  @Test
  public void test04QueryDatabaseAndAssertValuesAreCorrectForIpps2020Transfers0282L()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2020Transfers0282L,
            data.expectedValuesIpps2020Transfers0282L
    );
  }

  @Test
  public void test05QueryDatabaseAndAssertValuesAreCorrectForIpps2020Transfers0282M()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2020Transfers0282M,
            data.expectedValuesIpps2020Transfers0282M
    );
  }

}
