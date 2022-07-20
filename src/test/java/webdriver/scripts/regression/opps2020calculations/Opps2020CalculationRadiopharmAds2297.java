package webdriver.scripts.regression.opps2020calculations;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Opps2020CalculationRadiopharmAds2297 extends CalculationHelper {

  static final String modelName = "v102 REGRESSION OPPS 2020 Radiopharm A";
  final byte numberOfEfrs = 51;
  Opps2020Data opps2020 = new Opps2020Data();

  /** Regression: Automated test script for ADS-2297 - 2020 OPPS: RadioPharm. */
  @BeforeClass
  public static void setupScript() throws Exception {
    System.out.println("Test Class: " + Opps2020CalculationRadiopharmAds2297.class.getSimpleName());
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
            opps2020.sqlQueryRadioPharm,
            opps2020.expectedValuesRadioPharm
    );
  }

}
