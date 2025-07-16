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
public class Ipps2020AddOnTechAds1653 extends CalculationHelper {

  static DataMaintenanceMap dmMap;
  static final String batchName = "v101 REGRESSION 2020 IPPS: Add On Tech";
  final String expectedLogView = batchName + "\\Contracting\\Contract Batch";
  private Ipps2020Data data = new Ipps2020Data();

  /** Regression: Test script for ADS-1653 - 2020 IPPS: Add On Tech. */
  @BeforeClass
  public static void setupScript() throws Exception {
    dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
    System.out.println("Test Class: " + Ipps2020AddOnTechAds1653.class.getSimpleName());
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
  public void test02QueryDatabaseAndAssertValuesAreCorrectForIpps2020AddOnTechA1()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2020AddOnTechA1,
            data.expectedValuesIpps2020AddOnTechA1
    );
  }

  @Test
  public void test03QueryDatabaseAndAssertValuesAreCorrectForIpps2020AddOnTechA2()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2020AddOnTechA2,
            data.expectedValuesIpps2020AddOnTechA2
    );
  }

  @Test
  public void test04QueryDatabaseAndAssertValuesAreCorrectForIpps2020AddOnTechD()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2020AddOnTechD,
            data.expectedValuesIpps2020AddOnTechD
    );
  }

  @Test
  public void test05QueryDatabaseAndAssertValuesAreCorrectForIpps2020AddOnTechE()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2020AddOnTechE,
            data.expectedValuesIpps2020AddOnTechE
    );
  }

  @Test
  public void test06QueryDatabaseAndAssertValuesAreCorrectForIpps2020AddOnTechF()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2020AddOnTechF,
            data.expectedValuesIpps2020AddOnTechF
    );
  }

}
