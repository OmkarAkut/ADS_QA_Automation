package webdriver.scripts.regression.generalcalculations;

import static org.junit.Assert.assertTrue;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GeneralCalculationsRunContractualAllowancesScenarioAds2163 extends CalculationHelper {

  private GeneralCalculationsData data = new GeneralCalculationsData();
  String[] allowanceScenarios = {"v98ADS42SCENARIO1", "v98ADS42SCENARIO2", "v98ADS42SCENARIO3"};

  /**
   * Automated script for test ticket ADS-2163.
   * Updated: 3-26-2020.
   */
  @BeforeClass
  public static void setupScript() throws Exception {
    System.out.println(
         "Test Class: " + GeneralCalculationsRunContractualAllowancesScenarioAds2163.class.getSimpleName()
    );
    Login.loginUser("ContractAnalyst1");
    goToPage("Contractual Allowance Export");
    waitForSpinnerToEnd();
  }

  @AfterClass
  public static void teardownScript() throws InterruptedException {
    doClosePageOnLowerBar("Calculation Status");
  }

  @Test
  public void test01ExportContractualAllowanceScenarios()
          throws InterruptedException {
    for (String scenario : allowanceScenarios) {
      waitForAjaxExtJs();
      tableDoubleClickCellFirstColumn(scenario);
//      doClick(driver.findElement(By.xpath("//button/span[text()='Export']")));
//      waitForSpinnerToEnd();
//      waitForCalculationToEndAndVerifySummaryDetailsStringOnDialogAndCloseDialog(
//          "");
//      assertCalcStatusColumnForFirstRowOfStatusTableIsCompleted();
//      deleteMyCalculationStatusFirstRow();
    }
  }

  @Ignore
  @Test
  public void test02AssertContractualAllowanceScenariosOnDbServer()
          throws ClassNotFoundException {
    calculationsAssertDbRowCount(
            GeneralCalculationsData.getMedicalServiceAssignmentSql,
            "greater than",
            0
    );
  }

  private void assertCalcStatusColumnForFirstRowOfStatusTableIsCompleted() {
    assertTrue(
            driver.findElement(By.xpath("//table/tbody/tr[2]/td[contains(@class,'x-grid-cell')]/div[text()='Completed']"))
            .isDisplayed()
    );
  }

}
