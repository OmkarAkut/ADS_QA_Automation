package webdriver.scripts.costing.costingcalculations;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CostingRunActivityVolumeCalculationScenarioAds2338 extends CalculationHelper {

	private final String costModel = "QA Marina";
	String activityVolumeSaveDataScenario = "v11.2 REGRESSION Act Vol Calc Scenario";
//  String activityVolumeDataScenario = "041720140";
	String activityVolumeDataCalculationScenario = "v105 REGRESSION Act Vol Calc Scenario";
	String[] filterVolScenario = { "Name", "Is", "Equal To", activityVolumeDataCalculationScenario };
	String[] filterCheckVolScenario = { "Name", "Is", "Equal To", activityVolumeSaveDataScenario };
	private final String expectedFilterTotal = "232";
	static String currentDateTime = new SimpleDateFormat("MM.HH.ss").format(new java.util.Date());
	static String Code = currentDateTime.replaceAll("\\W", "");
	String[] filterScenario = { "Name", "Is", "Equal To", scenarioName };
	static String entity="150  Marina Medical Center";
	static String scenarioName = "Scenario"+Code;
	private static ContractingMap modelMap;
	private static CostingMap costMap;
	private static String startMonth="Jan 2017";
	private static String endMonth="Feb 2017";
	private static int activityVolDeptCount=17;
	private static String chargeCodes="<ALL>";
//  private final String expectedFilterTotal = "74";
	/** Regression: Test script for ADS-5990, incomplete steps need to be added  */
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("CostingRunActivityVolumeCalculationScenarioAds2338",
				"webdriver.scripts.costing.costingcalculations", "CostingRunActivityVolumeCalculationScenarioAds2338");
		try {
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);
			costMap=BuildMap.getInstance(driver, CostingMap.class);
			System.out
					.println("Test Class: " + CostingRunActivityVolumeCalculationScenarioAds2338.class.getSimpleName());
			Login.loginUser("CostAnalyst1");
			goToPage("Costing Models");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void teardownScript() throws InterruptedException, Throwable {
		try {
			doClosePageOnLowerBar("QA Marina");
			waitForAjaxExtJs();

		} catch (Exception | AssertionError e) {

			ExtentReport.logFail("FAIL", "Failure in teardownScript", driver, e);
		}
		ExtentReport.report.flush();
	}

	@Test
	public void test01VerifyActivityVolumeDataScenarioPageConfigurationAndSave_ADS_5990()
			throws InterruptedException, Throwable {
		try {
			doSearchForModel(costModel);
			waitSpinAjaxDelay(2000);
			tableDoubleClickCellFirstColumn(costModel);
			waitSpinAjaxDelay(2000);
			// Shilpa 26.08.2022 updated test data
			doClickTreeItem("Prepare Data");
			doClickTreeItem("Activity Volume Data Scenarios");
			doClickTreeItemWithCheckbox("Activity Volume Data Scenarios");
			driverDelay();
			doClick(CostingMap.getActivityVolNew());
			ContractModelsHelper.keyInValues(CostingMap.getActivityVolCode(), Code);
			ContractModelsHelper.keyInValues(CostingMap.getActivityVolName(), scenarioName);
//			doClick(CostingMap.getActivityVolFiscalYear());
			doDropdownSelectUsingOptionText(CostingMap.getActivityVolFiscalYear(), CostingMap.getActivityVolFiscalYearList(), "**FY16");
			doClick(CostingMap.getActivityVolSaveBtn());
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "test01VerifyActivityVolumeDataScenarioPageConfigurationAndSave");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01VerifyActivityVolumeDataScenarioPageConfigurationAndSave", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test02VerifyActivityVolumeDataCalculationScenarioPageConfigurationAndSave_ADS_5990()
			throws InterruptedException, Throwable {
		try {
//			doClick("(//div[contains(@id,'activityvolumedatalist')]//following::span[text()='Clear Filter'])[1]");
			doClickTreeItemWithCheckbox("Activity Volume Data Calculation Scenarios");
			waitSpinAjaxDelay(2000);
			doClick(CostingMap.getActivityVolCalcFilterBtn());
			doFilterCreate(filterVolScenario);
			tableDoubleClickCellFirstColumn(activityVolumeDataCalculationScenario);
			waitSpinAjaxDelay(2000);
			ContractModelsHelper.keyInValues(CostingMap.getActivityVolDataCalcScenarioName(), activityVolumeSaveDataScenario);
			doDropdownSelectUsingOptionText(CostingMap.getActivityVolCalcDestScenBtn(),CostingMap.getActivityVolDataSCenarioList(), Code+" "+scenarioName);
			// verify configuration on this page
			doDropdownSelectUsingOptionText(CostingMap.getActivityVolDataCalcScenarioCalculateStart(),CostingMap.getActivityVolDataCalcScenarioCalculateStartList(), startMonth);
			doDropdownSelectUsingOptionText(CostingMap.getActivityVolDataCalcScenarioCalculateEnd(),CostingMap.getActivityVolDataCalcScenarioCalculateEndList(), endMonth);
			
			doClick(CostingMap.getActivityVolDataCalcScenarioSaveAS());
			doClick(CostingMap.getActivityVolDataCalcScenarioCalculate());
			waitForFirstRowCalculationBarToReach100Percent();
			doClosePageOnLowerBar("Calculation Status");
			doClick(CostingMap.getActivityVolDataSCenarioSaveClose());
			ExtentReport.logPass("PASS", "test02VerifyActivityVolumeDataCalculationScenarioPageConfigurationAndSave");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02VerifyActivityVolumeDataCalculationScenarioPageConfigurationAndSave",
					driver, e);
			fail(e.getMessage());
		}
		// waitFor "Your Progress Has Been Saved" spinner to end
	}

	@Test
	public void test03AssertActivityVolumeDataScenarioFilterResultsMatchExpected_ADS_5990() throws InterruptedException, Throwable {
		try {
			doClick("(//div[contains(@class,'x-grid-cell-inner')]//span[text()='Activity Volume Data Scenarios'])[2]");
			doClick(CostingMap.getActivityVolFilter());
			doFilterCreate(filterScenario);
			tableDoubleClickCellFirstColumn(Code);
			doClick(CostingMap.getActivityVolDataGridFilter());
			waitForAjaxExtJs();
			assertTextIsDisplayed("Filter to Match These Criteria 68/68");
			doClick(CostingMap.getActivityVolDataCloseFilter());
			doClick(CostingMap.getActivityVolDataCancelClose());
			
			ExtentReport.logPass("PASS", "test03AssertActivityVolumeDataScenarioFilterResultsMatchExpected");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03AssertActivityVolumeDataScenarioFilterResultsMatchExpected",
					driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test04AssertActivityVolumeDataCalculationScenario_ADS_5990()
			throws InterruptedException, Throwable {
		try {
			doClickTreeItem("Activity Volume Data Calculation Scenarios");
			doClick(CostingMap.getActivityVolCalcFilterBtn());
			doFilterCreate(filterCheckVolScenario);
			tableDoubleClickCellFirstColumn(activityVolumeSaveDataScenario);
			waitSpinAjaxDelay(2000);
			assertElementIsDisplayedWithXpath("//div[text()='Entities ']//following::li[text()='"+entity+"']");
			if(CostingMap.getActivityVolDeptList().size()==activityVolDeptCount) {
				assertTrue(printout);
			}
			else {
				fail();
			}
			assertElementIsDisplayedWithXpath("//div[text()='Charge Codes ']//following::li[@class='x-boundlist-item'][text()='"+chargeCodes+"']")
			;
//			assertElementIsDisplayedWithXpath("//label[text()='Posting']//preceding::span/input");
			assertElementIsDisplayedWithXpath("(//span[text()='Modifier Table To Adjust Costs']//following::input[@placeholder='<None>'])[1]");
			doClick(CostingMap.getActivityVolDataCalcScenarioCancelClose());
			waitForDisplayedSpinnerToEnd();
			doClick(CostingMap.getActivityVolCalcDeleteBtn());
			doClick(CostingMap.getActivityVolCalcDeletePopupBtn());
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "test04AssertActivityVolumeDataCalculationScenario");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04AssertActivityVolumeDataCalculationScenario", driver, e);
			fail(e.getMessage());

		}
	}

}
