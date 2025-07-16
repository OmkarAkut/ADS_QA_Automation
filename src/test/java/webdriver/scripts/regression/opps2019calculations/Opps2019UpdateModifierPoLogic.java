package webdriver.scripts.regression.opps2019calculations;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Opps2019UpdateModifierPoLogic extends CalculationHelper {

  static final String folderName = "Automation";
  static final String modelName = "ADS-1305 OPPS 2019 ADS-1242 Mod PO";
  final byte numberOfEfrs = 9;
  final byte errors = 0;
  final byte efrsCalculatedToZero = 0;
  Opps2019Data data = new Opps2019Data();

  /** Regression: Automated test script for ADS-1305 - 2019 OPPS: Update Modifier PO Logic. */
  @BeforeClass
  public static void setupScript() throws Exception {
    System.out.println("Test Class: " + Opps2019UpdateModifierPoLogic.class.getSimpleName());
    Login.loginUser("ContractAnalyst1");
    goToContractModelsAndSelectFolder(folderName);
    filterAndSelectContractModelFromContractModelLibrary(modelName);
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
  public void test02QueryDatabaseAndAssertValuesAreCorrectForModifierPoLogic()
          throws ClassNotFoundException {
    getDataFromDatabaseAndAssertExpectedValues(
            data.sqlQueryUpdateModifierPoLogic,
            data.expectedValuesUpdateModifierPoLogic
    );
  }
}
