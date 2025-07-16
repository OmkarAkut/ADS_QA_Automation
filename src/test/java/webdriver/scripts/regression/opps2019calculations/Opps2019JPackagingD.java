package webdriver.scripts.regression.opps2019calculations;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Opps2019JPackagingD extends CalculationHelper {

  static final String folderName = "Regression v10.0";
  static final String modelName = "v10 REGRESSION 2019 OPPS J Packaging D";
  final byte numberOfEfrs = 12;
  final byte errors = 0;
  final byte efrsCalculatedToZero = 0;
  Opps2019Data data = new Opps2019Data();

  /** Regression: Automated test script for ADS-1329 - 2019 OPPS: J Packaging D. */
  @BeforeClass
  public static void setupScript() throws Exception {
    System.out.println("Test Class: " + Opps2019JPackagingD.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    goToContractModelsAndSelectModel(folderName, modelName);
  }

  @Test
  public void test01ClickCalculateButtonAndAssertSummaryIsErrorFree() {
    try {
      clickCalculateButtonAndVerifySummaryDetailsOnDialogAndCloseDialog(
              numberOfEfrs,
              errors,
              efrsCalculatedToZero
      );
      deleteFirstRow();
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test02QueryDatabaseAndAssertValuesAreCorrect() throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryJPackagingD,
            data.expectedValuesJPackagingD
    );
  }

}
