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
public class Ipps2020ExcludedServicesAds1651 extends CalculationHelper {

  static DataMaintenanceMap dmMap;
  static final String batchName = "v101 REGRESSION 2020 IPPS: Excluded Serv";
  final String expectedLogView = batchName + "\\Contracting\\Contract Batch";
  private Ipps2020Data data = new Ipps2020Data();

  /** Regression: Test script for ADS-1651 - 2020 IPPS: Excluded Services.
   *  Updated: 11/22/19. */
  @BeforeClass
  public static void setupScript() throws Exception {
    dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
    System.out.println("Test Class: " + Ipps2020ExcludedServicesAds1651.class.getSimpleName());
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
  public void test02QueryDatabaseAndAssertValuesAreCorrectForIpps2020ExcludedServices()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryIpps2020ExcludedServices,
            data.expectedValuesIpps2020ExcludedServices
    );
  }

}
