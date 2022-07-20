package webdriver.scripts.costing.costingcalculations;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CostingRunActivityVolumeCalculationScenarioAds2338 extends CalculationHelper {

  private final String costModel = "QA Marina";
  String activityVolumeDataScenario = "ATREGRESSAV";
  String activityVolumeDataCalculationScenario = "Automated REGRESSION Act Vol Calc Scen";
  private final String expectedFilterTotal = "232";

  @BeforeClass
    public static void setupScript() throws Exception {
    System.out.println("Test Class: "
            + CostingRunActivityVolumeCalculationScenarioAds2338.class.getSimpleName());
    Login.loginUser("CostAnalyst1");
    goToPage("Costing Models");
  }

  @AfterClass
  public static void teardownScript() throws InterruptedException {
    doClosePageOnLowerBar("QA Marina");
    waitForAjaxExtJs();
  }

  @Test
    public void test01VerifyActivityVolumeDataScenarioPageConfigurationAndSave()
          throws InterruptedException {
    doSearchForModel(costModel);
      waitSpinAjaxDelay(2000);
    tableDoubleClickCellFirstColumn(costModel);
      waitSpinAjaxDelay(2000);
    doClickTreeItem("Prepare Data");
      waitSpinAjaxDelay(2000);
    doClickTreeItem("Activity Volume Data Scenarios");
      waitSpinAjaxDelay(2000);
    doClickTreeItemWithCheckbox("Activity Volume Data Scenarios");
      waitSpinAjaxDelay(2000);
    tableDoubleClickCellFirstColumn(activityVolumeDataScenario);
      waitSpinAjaxDelay(2000);
    assertFilterResults(expectedFilterTotal);
    doClickButton("Cancel & Close");
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
  }

  @Test
  public void test02VerifyActivityVolumeDataCalculationScenarioPageConfigurationAndSave()
          throws InterruptedException {
    doClickTreeItemWithCheckbox("Activity Volume Data Calculation Scenarios");
      waitSpinAjaxDelay(2000);
    tableDoubleClickCellFirstColumn(activityVolumeDataCalculationScenario);
      waitSpinAjaxDelay(2000);
    //verify configuration on this page
    doClick(getWebElementButtonWithElementText("Save"));
    //waitFor "Your Progress Has Been Saved" spinner to end
  }

  @Test
  public void test03RunCalculationAndAssertResultsMatchExpected()
          throws InterruptedException {
    doClick(getWebElement("//div[1]/em/button/span[text()='Calculate']"));
    waitForFirstRowCalculationBarToReach100Percent();
    deleteMyCalculationStatusFirstRow();
    doClosePageOnLowerBar("Calculation Status");
    waitForAjaxExtJs();
    doClick(getWebElement("//div[3]/em/button/span[text()='Save & Close']"));
    waitForSpinnerToEnd();
  }

  @Test
  public void test04AssertActivityVolumeDataScenarioFilterResultsMatchExpected()
          throws InterruptedException {
    doClickTreeItemWithCheckbox("Activity Volume Data Scenarios");
    waitSpinAjaxDelay(2000);
    tableDoubleClickCellFirstColumn(activityVolumeDataScenario);
    waitSpinAjaxDelay(2000);
    assertFilterResults(expectedFilterTotal);
  }

}
