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
public class Ipps2021ExcludedServicesAds3641 extends CalculationHelper {

  static DataMaintenanceMap dmMap;
  static final String batchName = getVersion() + " REGRESSION 2021 IPPS: Exclude Serv";
  final String expectedLogView = batchName + "\\Contracting\\Contract Batch";
  private Ipps2021Data data = new Ipps2021Data();

  /** Regression: Test script for ADS-3641- 2021 IPPS: Excluded Services.
   *  Updated: 12/9/20. */
  @BeforeClass
  public static void setupScript() throws Exception {
    dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
    System.out.println("Test Class: " + Ipps2021ExcludedServicesAds3641.class.getSimpleName());
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
  public void test02QueryDatabaseAndAssertValuesAreCorrectForIpps2021ExcludedServices()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2021ExcludedServices,
            data.expectedValuesIpps2021ExcludedServices
    );
  }

}
