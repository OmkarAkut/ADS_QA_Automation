package webdriver.scripts.costing.costingcalculations;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CostingRunStatisticDataCalculationScenarioAds2339 extends CalculationHelper {

  private final String costModel = "QA Marina";
  String statisticDataScenario = "Automated REGRESSION Stat Data Scenario";
  String statisticDataCalculationScenario = "Automated REGRESSION Stat Data Calc FY05";
  String expectedFilterTotal = "1237";

  @BeforeClass
    public static void setupScript() throws Exception {
    System.out.println("Test Class: " + CostingRunStatisticDataCalculationScenarioAds2339.class.getSimpleName());
    Login.loginUser("CostAnalyst1");
    goToPage("Costing Models");
  }

  @AfterClass
  public static void teardownScript() throws InterruptedException {
    doClosePageOnLowerBar("QA Marina");
  }

  @Test
    public void test01VerifyStaticDataScenarioPageConfiguration() throws InterruptedException {
    doSearchForModel(costModel);
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    driverDelay();
    tableDoubleClickCellFirstColumn(costModel);
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    driverDelay();
    doClickTreeItem("Prepare Data");
    driverDelay();
    doClickTreeItem("Statistic Data Scenarios");
    driverDelay();
    doClickTreeItemWithCheckbox("Statistic Data Scenarios");
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    driverDelay();
    tableDoubleClickCellFirstColumn(statisticDataScenario);
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    assertFilterResults(expectedFilterTotal);
    doClickButton("Cancel & Close");
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
  }

  @Test
  public void test02VerifyStaticDataCalculationScenarioPageConfigurationAndSave() throws InterruptedException {
    doClickTreeItemWithCheckbox("Statistic Data Calculation Scenarios");
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    driverDelay();
    tableDoubleClickCellFirstColumn(statisticDataCalculationScenario);
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    driverDelay();
    //verify configuration on this page
    //    GL Stat Master = GLSTATS
    //    GL Data Scen = MH FY05 Reclass
    //    Act Stat Master = ACTSTATS
    //    Act Vol Data Scen = MHFY05
    //    Entities = 150
    //    Dept = ALL
    // verify Destination Scenario
    // set dates Start = Apr 2004 End = Mar 2005
    //set calc option "Delete existing data and recalculate..."
    //Uncheck Shared Log check box
    doClick(getWebElementButtonWithElementText("Save"));
    //waitFor "Your Progress Has Been Saved" spinner to end
  }

  @Test
  public void test03RunCalculationAndAssertResultsMatchExpected() throws InterruptedException {
    doClick(getWebElement("//div[1]/em/button/span[text()='Calculate']"));
    waitForFirstRowCalculationBarToReach100Percent(10000);
    deleteMyCalculationStatusFirstRow();
    doClosePageOnLowerBar("Calculation Status");
    waitForAjaxExtJs();
    doClick(getWebElement("//div[3]/em/button/span[text()='Save & Close']"));
    waitForSpinnerToEnd();
  }

  @Test
  public void test04AssertActivityVolumeDataScenarioFilterResultsMatchExpected() throws InterruptedException {
    doClickTreeItemWithCheckbox("Statistic Data Scenarios");
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    driverDelay();
    tableDoubleClickCellFirstColumn(statisticDataScenario);
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    driverDelay();
    assertFilterResults(expectedFilterTotal);
    doClickButton("Cancel & Close");
    waitForSpinnerToEnd();
  }

}
