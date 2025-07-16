package webdriver.scripts.regression.opps2019calculations;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Opps2019JPackagingC extends CalculationHelper {

  String unexpectedErrorText = "ERROR: An Unexpected Error has Occurred:";
  static final String folderName = "Regression v10.0";
  static final String modelName = "v10 REGRESSION 2019 OPPS J Packaging C";
  final String expectedLogView =
      "v10 REGRESSION 2019 OPPS J Packaging C\\Contracting\\Unpublished Contract Calculation";
  String[] details = {
          "Total EFRs to be processed: 12",
          "Errors: 0",
          "Encounter Financial Records Calculated to Zero: 0"
  };
  Opps2019Data data = new Opps2019Data();
  private static ModelLibraryMap modelMap;

  /** Regression: Automated test script for ADS-1329 - 2019 OPPS: J Packaging C. */
  @BeforeClass
  public static void setupScript() throws Exception {
    modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
    System.out.println("Test Class: " + Opps2019JPackagingC.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    goToContractModelsAndSelectModel(folderName, modelName);
  }

  @Test
  public void test01ClickCalculateButtonAndAssertSummaryIsErrorFree() {
    try {
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
      modelMap.getModelLibraryContractingButtonCalculate().click();
      waitForCalculationToEndAndVerifySummaryDetailsOnViewDialogAndCloseDialog(
              expectedLogView,
              details
      );
      deleteFirstRow();
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test02QueryDatabaseAndAssertValuesAreCorrect() throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryJPackagingC,
            data.expectedValuesJPackagingC
    );
  }

}
