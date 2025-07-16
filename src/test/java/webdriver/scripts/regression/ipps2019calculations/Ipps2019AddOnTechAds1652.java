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
public class Ipps2019AddOnTechAds1652 extends CalculationHelper {

  //NOTE: There is a defect where a period in the batch name disables the Calculate button - the commented item below
  // point to the data to test this (currently commented until fixed).
  static DataMaintenanceMap dmMap;
  static final String batchName = "v101 REGRESSION 2019 IPPS: Add On Tech";
  //static final String batchName = "v10.1 REGRESSION 2019 IPPS: Add On Tech";
  final byte numberOfEfrs = 10;
  final byte nonCriticalWarnings = 0;
  final byte efrsCalculatedToZero = 0;
  final String expectedLogView = "v101 REGRESSION 2019 IPPS: Add On Tech\\Contracting\\Contract Batch";
  //final String expectedLogView = "v10.1 REGRESSION 2019 IPPS: Add On Tech\\Contracting\\Contract Batch";
  private Ipps2019CalculationsData data = new Ipps2019CalculationsData();

  /** Regression: Test script for ADS-1652 - 2019 IPPS: Add On Tech.
   * Updated: 11/22/19. */
  @BeforeClass
  public static void setupScript() throws Exception {
    dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
    System.out.println("Test Class: " + Ipps2019AddOnTechAds1652.class.getSimpleName());
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
  public void test02QueryDatabaseAndAssertValuesAreCorrectForIpps2019AddOnTechA1()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2019AddOnTechA1,
            data.expectedValuesIpps2019AddOnTechA1
    );
  }

  @Test
  public void test03QueryDatabaseAndAssertValuesAreCorrectForIpps2019AddOnTechA2()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2019AddOnTechA2,
            data.expectedValuesIpps2019AddOnTechA2
    );
  }

  @Test
  public void test04QueryDatabaseAndAssertValuesAreCorrectForIpps2019AddOnTechD()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2019AddOnTechD,
            data.expectedValuesIpps2019AddOnTechD
    );
  }

  @Test
  public void test05QueryDatabaseAndAssertValuesAreCorrectForIpps2019AddOnTechE()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2019AddOnTechE,
            data.expectedValuesIpps2019AddOnTechE
    );
  }

  @Test
  public void test06QueryDatabaseAndAssertValuesAreCorrectForIpps2019AddOnTechF()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2019AddOnTechF,
            data.expectedValuesIpps2019AddOnTechF
    );
  }

}
