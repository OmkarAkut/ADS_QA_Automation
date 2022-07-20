package webdriver.scripts.regression.opps2020calculations;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Opps2020JPackagingUpdatesAds2363 extends CalculationHelper {

  static String modelName = "v1023 REGRESSION OPPS 2020 J Pack Update";
  final byte numberOfEfrs = 8;
  Opps2020Data opps2020 = new Opps2020Data();

  /** Regression: Automated test script for ADS-2244 - 2020 OPPS: Update Modifier PO Percent. */
  @BeforeClass
  public static void setupScript() throws Exception {
    System.out.println("Test Class: " + Opps2020JPackagingUpdatesAds2363.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    if (testEnvironment.contains("evolve")) {
      modelName = "v1023 REGRESSION OPPS 2020 JPACK Update";
    }
    goToContractModelsPageAndSearchAndSelectModel(modelName);
  }

  @Test
  public void test01ClickCalculateButtonAndAssertSummaryIsErrorFree() {
    try {
      clickCalculateButtonAndVerifyEfrCountAndZeroErrorsOnSummaryDialogAndCloseDialog(numberOfEfrs);
      deleteMyCalculationStatusFirstRow();
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test02QueryDatabaseAndAssertValuesAreCorrect() throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            opps2020.sqlQueryJPackagingUpdates,
            opps2020.expectedValuesJPackagingUpdates
    );
  }

}
