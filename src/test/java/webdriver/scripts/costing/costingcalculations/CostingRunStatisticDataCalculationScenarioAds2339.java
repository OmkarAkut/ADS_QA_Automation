package webdriver.scripts.costing.costingcalculations;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
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
	 ExtentReport.reportCreate("CostingRunStatisticDataCalculationScenarioAds2339", "webdriver.scripts.costing.costingcalculations", "CostingRunStatisticDataCalculationScenarioAds2339");
    System.out.println("Test Class: " + CostingRunStatisticDataCalculationScenarioAds2339.class.getSimpleName());
    Login.loginUser("CostAnalyst1");
    goToPage("Costing Models");
  }

  @AfterClass
  public static void teardownScript() throws InterruptedException {
    doClosePageOnLowerBar("QA Marina");
    ExtentReport.report.flush();
  }

  @Test
    public void test01VerifyStaticDataScenarioPageConfiguration() throws Throwable {
    try {
		doSearchForModel(costModel);
		waitForSpinnerToEnd();
		waitForAjaxExtJs();
		driverDelay();
		tableDoubleClickCellFirstColumn(costModel);
		waitForSpinnerToEnd();
		waitForAjaxExtJs();
		driverDelay();
		//Shilpa 26.08.2022 updated test data
//    doClickTreeItem("Prepare Data");
		doClickTreeItem("CM Test");
//    waitSpinAjaxDelay(2000);
		driverDelay();
		doClickTreeItem("Cost Scnenarios");
		waitSpinAjaxDelay(2000);
		doClickTreeItem("Statistic Data Scenarios");
		driverDelay();
//    doClickTreeItemWithCheckbox("Statistic Data Scenarios");
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
		ExtentReport.logPass("PASS", "test01VerifyStaticDataScenarioPageConfiguration");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test01VerifyStaticDataScenarioPageConfiguration", driver, e);
	}
  }

  @Test
  public void test02VerifyStaticDataCalculationScenarioPageConfigurationAndSave() throws InterruptedException,Throwable {
    try {
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
		ExtentReport.logPass("PASS", "test02VerifyStaticDataCalculationScenarioPageConfigurationAndSave");

	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test02VerifyStaticDataCalculationScenarioPageConfigurationAndSave", driver, e);

	}
    //waitFor "Your Progress Has Been Saved" spinner to end
  }

  @Test
  public void test03RunCalculationAndAssertResultsMatchExpected() throws InterruptedException,Throwable {
    try {
		doClick(getWebElement("//div[1]/em/button/span[text()='Calculate']"));
		waitForFirstRowCalculationBarToReach100Percent(10000);
		deleteMyCalculationStatusFirstRow();
		doClosePageOnLowerBar("Calculation Status");
		waitForAjaxExtJs();
		doClick(getWebElement("//div[3]/em/button/span[text()='Save & Close']"));
		waitForSpinnerToEnd();
		ExtentReport.logPass("PASS", "test03RunCalculationAndAssertResultsMatchExpected");

	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test03RunCalculationAndAssertResultsMatchExpected", driver, e);

	}
  }

  @Test
  public void test04AssertActivityVolumeDataScenarioFilterResultsMatchExpected() throws Throwable,InterruptedException {
//    doClickTreeItemWithCheckbox("Statistic Data Scenarios");
//    waitForSpinnerToEnd();
//    waitForAjaxExtJs();
//    driverDelay();
//	  doClickTreeItem("Statistic Data Scenarios");
//	    driverDelay();
//	    doClickTreeItemWithCheckbox("Statistic Data Scenarios");
	  //Shilpa 26.08.2022 updated xpath
	    try {
			driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell-treecolumn')]/div[text()='Statistic Data Scenarios']")).click();
			waitForSpinnerToEnd();
			waitForAjaxExtJs();
			driverDelay();
			Thread.sleep(2000);
   tableDoubleClickCellFirstColumn(statisticDataScenario);
   waitForSpinnerToEnd();
   waitForAjaxExtJs();
   driverDelay();
   assertFilterResults(expectedFilterTotal);
   doClickButton("Cancel & Close");
   waitForSpinnerToEnd();
   ExtentReport.logPass("PASS", "test03RunCalculationAndAssertResultsMatchExpected");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test03RunCalculationAndAssertResultsMatchExpected", driver, e);

		}
  }

}
