package webdriver.scripts.regression.opps2020calculations;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Opps2020CalculationJPackagingDAds2298 extends CalculationHelper {

  static final String modelName = "v102 REGRESSION OPPS 2020 J Packaging D";
  final byte numberOfEfrs = 16;
  Opps2020Data opps2020 = new Opps2020Data();

  /** Regression: Automated test script for ADS-2298 - 2020 OPPS: J Packaging D (same data as J Pack C). */
  @BeforeClass
  public static void setupScript() throws Exception {
    System.out.println("Test Class: " + Opps2020CalculationJPackagingDAds2298.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
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
            opps2020.sqlQueryJPackagingC,
            opps2020.expectedValuesJPackagingC
    );
  }

}
