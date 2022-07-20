package webdriver.scripts.regression.opps2020calculations;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Opps2020CalculationJPackSiEqualsRAds2299 extends CalculationHelper {

  Opps2020Data opps2020 = new Opps2020Data();
  static String modelName; //"v102 REGRESSION OPPS 2020 J Pack SI=R";  //evolve
  //static final String modelName = "v102 REGRESSION OPPS 2020 J Pack SI=R A";
  final byte numberOfEfrs = 12;

  /** Regression: Automated test script for ADS-2299 - 2020 OPPS: J Pack SI=R. */
  @BeforeClass
  public static void setupScript() throws Exception {
    System.out.println("Test Class: " + Opps2020CalculationJPackSiEqualsRAds2299.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    if (testEnvironment.contains("echelon")) {
      modelName = "v102 REGRESSION OPPS 2020 J Pack SI=R A";
    } else {
      modelName = "v102 REGRESSION OPPS 2020 J Pack SI=R";  //evolve
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
    if (testEnvironment.contains("echelon")) {
      getDataFromDatabaseAndAssertExpectedValues(
              opps2020.sqlQueryJPackagingAndSiEqualsRechelon,
              opps2020.expectedValuesJPackagingAndSiEqualsR
      );
    } else {
      getDataFromDatabaseAndAssertExpectedValues(
              opps2020.sqlQueryJPackagingAndSiEqualsR,
              opps2020.expectedValuesJPackagingAndSiEqualsR
      );
    }
//    getDataFromDatabaseAndAssertExpectedValues(
//            opps2020.sqlQueryJPackagingAndSiEqualsR,
//            opps2020.expectedValuesJPackagingAndSiEqualsR
//    );
  }

}
