package webdriver.scripts.costing.costingcalculations;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CostingRunActivityVolumeCalculationScenarioAds2338 extends CalculationHelper {

  private final String costModel = "QA Marina";
  String activityVolumeDataScenario = "ATREGRESSAV";
//  String activityVolumeDataScenario = "041720140";
  String activityVolumeDataCalculationScenario = "Automated REGRESSION Act Vol Calc Scen";
  private final String expectedFilterTotal = "232";
//  private final String expectedFilterTotal = "74";

  @BeforeClass
    public static void setupScript() throws Exception,Throwable {
	  ExtentReport.reportCreate("CostingRunActivityVolumeCalculationScenarioAds2338", "webdriver.scripts.costing.costingcalculations", "CostingRunActivityVolumeCalculationScenarioAds2338");
    try {
		System.out.println("Test Class: "
		        + CostingRunActivityVolumeCalculationScenarioAds2338.class.getSimpleName());
		Login.loginUser("CostAnalyst1");
		goToPage("Costing Models");
		ExtentReport.logPass("PASS", "setupScript");
	}  catch (Exception e) {
		ExtentReport.logFail("FAIL", PROPS, driver, e);
		fail(e.getMessage());
	}
  }

  @AfterClass
  public static void teardownScript() throws InterruptedException,Throwable {
    try {
		doClosePageOnLowerBar("QA Marina");
		waitForAjaxExtJs();
		
	} catch (InterruptedException e) {
		ExtentReport.logFail("FAIL", "Failure in teardownScript", driver, e);
		fail(e.getMessage());
	}
    ExtentReport.report.flush();
  }

  @Test
    public void test01VerifyActivityVolumeDataScenarioPageConfigurationAndSave()
          throws InterruptedException,Throwable {
    try {
		doSearchForModel(costModel);
		  waitSpinAjaxDelay(2000);
		tableDoubleClickCellFirstColumn(costModel);
		  waitSpinAjaxDelay(2000);
		  //Shilpa 26.08.2022 updated test data
//    doClickTreeItem("Prepare Data");
		  doClickTreeItem("CM Test");
		  waitSpinAjaxDelay(2000);
//    doClickTreeItem("Activity Volume Data Scenarios");
		  doClickTreeItem("Cost Scnenarios");
		  waitSpinAjaxDelay(2000);
		doClickTreeItemWithCheckbox("Activity Volume Data Scenarios");
//      doClickTreeItemWithCheckbox("Activity Statistic Masters");
		  waitSpinAjaxDelay(2000);
		tableDoubleClickCellFirstColumn(activityVolumeDataScenario);
		  waitSpinAjaxDelay(2000);
		assertFilterResults(expectedFilterTotal);
		doClickButton("Cancel & Close");
		waitForSpinnerToEnd();
		waitForAjaxExtJs();
		ExtentReport.logPass("PASS", "test01VerifyActivityVolumeDataScenarioPageConfigurationAndSave");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test01VerifyActivityVolumeDataScenarioPageConfigurationAndSave", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test02VerifyActivityVolumeDataCalculationScenarioPageConfigurationAndSave()
          throws InterruptedException,Throwable {
    try {
		doClickTreeItemWithCheckbox("Activity Volume Data Calculation Scenarios");
		  waitSpinAjaxDelay(2000);
		tableDoubleClickCellFirstColumn(activityVolumeDataCalculationScenario);
		  waitSpinAjaxDelay(2000);
		//verify configuration on this page
		doClick(getWebElementButtonWithElementText("Save"));
		ExtentReport.logPass("PASS", "test02VerifyActivityVolumeDataCalculationScenarioPageConfigurationAndSave");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test02VerifyActivityVolumeDataCalculationScenarioPageConfigurationAndSave", driver, e);
		fail(e.getMessage());
	}
    //waitFor "Your Progress Has Been Saved" spinner to end
  }

  @Test
  public void test03RunCalculationAndAssertResultsMatchExpected()
          throws InterruptedException,Throwable {
    try {
		doClick(getWebElement("//div[1]/em/button/span[text()='Calculate']"));
		waitForFirstRowCalculationBarToReach100Percent();
		deleteMyCalculationStatusFirstRow();
		doClosePageOnLowerBar("Calculation Status");
		waitForAjaxExtJs();
		doClick(getWebElement("//div[3]/em/button/span[text()='Save & Close']"));
		waitForSpinnerToEnd();
		ExtentReport.logPass("PASS", "test03RunCalculationAndAssertResultsMatchExpected");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test03RunCalculationAndAssertResultsMatchExpected", driver, e);
		fail(e.getMessage());

	}
  }

  @Test
  public void test04AssertActivityVolumeDataScenarioFilterResultsMatchExpected()
          throws InterruptedException,Throwable {
    try {
		doClickTreeItemWithCheckbox("Activity Volume Data Scenarios");
		waitSpinAjaxDelay(2000);
		tableDoubleClickCellFirstColumn(activityVolumeDataScenario);
		waitSpinAjaxDelay(2000);
		assertFilterResults(expectedFilterTotal);
		ExtentReport.logPass("PASS", "test04AssertActivityVolumeDataScenarioFilterResultsMatchExpected");

	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test04AssertActivityVolumeDataScenarioFilterResultsMatchExpected", driver, e);
		fail(e.getMessage());

	}
  }

}
