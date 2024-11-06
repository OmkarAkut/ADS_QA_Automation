package webdriver.scripts.costing.costingcalculations;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CostingRunStatisticDataCalculationScenarioAds2339 extends CalculationHelper {
	private static CostingMap costingMap;
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String statDataScenario = "Scenario " + currentDateTime;
	static String fiscalYear = "FY05";
	static String[] filter = { "Name", "Is", "Equal To", statDataScenario };
	private final String costModel = "QA Marina";
//  String statisticDataScenario = "Automated REGRESSION Stat Data Scenario";
//  String statisticDataCalculationScenario = "Automated REGRESSION Stat Data Calc FY05";
	// Shilpa: update test data for 11.2 on 2.5.2024
//	static String statisticDataCalculationScenario = "v105 REGRESSION Stat Data Calc Scenario";
	static String statisticDataCalculationScenario = "v11.2 REGRESSION Stat Data Calc Scenario";
	static String updateStatisticDataCalculationScenari = "Updated v11.2 REGRESSION Stat Data Calc";// Update as per
																										// version
																										// number
	static String[] filterScenario= {"Senario Name","Is","Equal To",updateStatisticDataCalculationScenari};
	static String[] filterCalcScenario = { "Name", "Is", "Equal To", statisticDataCalculationScenario };
	static String[] updatedfilterCalcScenario = { "Name", "Is", "Equal To", updateStatisticDataCalculationScenari };

	static String GLStatisticMaster = "GLSTATS General Ledger Statistics";
	static String GLDataScenario = "MH FY05 Reclass";
	static String ActivityStatisticMaster = "ACTSTATS Activity Statistics";
	static String ActivityVolDataScenario = "MHFY05 MH  Activity Vol Data Scenario FY05";
	static String entity = "150  Marina Medical Center";
	static String Departments = "ALL  ALL";
	static String startMonth = "Apr 2004";
	static String endMonth = "Mar 2005";
//  String statisticDataCalculationScenario = "QA REGRESSION Stat Data Calc FY05";
	String expectedFilterTotal = "1237";

	/** Regression: Test script for ADS-5989 */
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		costingMap = BuildMap.getInstance(driver, CostingMap.class);
		ExtentReport.reportCreate("CostingRunStatisticDataCalculationScenarioAds2339",
				"webdriver.scripts.costing.costingcalculations", "CostingRunStatisticDataCalculationScenarioAds2339");
		try {
			System.out
					.println("Test Class: " + CostingRunStatisticDataCalculationScenarioAds2339.class.getSimpleName());
			Login.loginUser("CostAnalyst1");
			goToPage("Costing Models");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("PASS", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void teardownScript() throws InterruptedException, Throwable {
		try {
			doClosePageOnLowerBar("QA Marina");
			ExtentReport.logPass("PASS", "teardownScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("PASS", "teardownScript", driver, e);
		}
		ExtentReport.report.flush();
	}

//ADS-5989 all steps
	@Test
	public void test01VerifyStaticDataScenarioPageConfiguration_ADS_5989() throws Throwable {
		try {
			/*
			 * doSearchForModel(costModel); waitForSpinnerToEnd(); waitForAjaxExtJs();
			 * driverDelay(); tableDoubleClickCellFirstColumn(costModel);
			 * waitForSpinnerToEnd(); waitForAjaxExtJs(); driverDelay(); //Shilpa 26.08.2022
			 * updated test data doClickTreeItem("Prepare Data"); //
			 * doClickTreeItem("CM Test"); // waitSpinAjaxDelay(2000); driverDelay(); //
			 * doClickTreeItem("Cost Scnenarios"); // waitSpinAjaxDelay(2000);
			 * doClickTreeItem("Statistic Data Scenarios"); driverDelay(); //
			 * doClickTreeItemWithCheckbox("Statistic Data Scenarios");
			 * doClick("(//tr[contains(@class,'x-grid-tree-node-leaf')]//td[contains(@class,'x-grid-cell-treecolumn')]//span[text()='Statistic Data Scenarios']//preceding::div[@class=' x-tree-checkbox'])[2]"
			 * ); waitForSpinnerToEnd(); waitForAjaxExtJs(); driverDelay();
			 * scrollToView(driver.findElement(By.
			 * xpath("//td[contains(@class,'x-grid-cell x-grid-td x-grid-cell-gridcolumn')]/*[text()='"
			 * + statisticDataScenario + "']")));
			 * tableDoubleClickCellFirstColumn(statisticDataScenario);
			 * waitForSpinnerToEnd(); waitForAjaxExtJs();
			 * 
			 * assertFilterResults(expectedFilterTotal); doClickButton("Cancel & Close");
			 * waitForSpinnerToEnd(); waitForAjaxExtJs();
			 */
			// Shilpa updated steps as per ADS-5989 on 2.5.2024
			doSearchForModel(costModel);
			waitForSpinnerToEnd();
			tableDoubleClickCellFirstColumn(costModel);
			waitForAjaxExtJs();
			doClickTreeItem("Prepare Data");
			driverDelay();
			doClickTreeItem("Statistic Data Scenarios");
			driverDelay();
			doClick("(//td[contains(@class,'x-grid-cell-treecolumn')]//span[text()='Statistic Data Scenarios'])[2]");
			doClick(costingMap.statisticDataScenarionNewBtn());
			ContractModelsHelper.keyInValues(costingMap.statisticDataScenarionName(), statDataScenario);
			doDropdownSelectUsingOptionText(costingMap.statisticDataFiscalYear(), CostingMap.getFiscalYearList(),
					fiscalYear);
			doClick(costingMap.getSaveStatisticScenario());
			ExtentReport.logPass("PASS", "test01VerifyStaticDataScenarioPageConfiguration");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01VerifyStaticDataScenarioPageConfiguration", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test02VerifyStaticDataCalculationScenarioPageConfigurationAndSave_ADS_5989()
			throws InterruptedException, Throwable {
		try {
			/*
			 * doClickTreeItem("Statistic Data Calculation Scenarios");
			 * waitForSpinnerToEnd(); waitForAjaxExtJs(); driverDelay();
			 * scrollToView(driver.findElement(By.
			 * xpath("//td[contains(@class,'x-grid-cell x-grid-td x-grid-cell-gridcolumn')]/*[text()='"
			 * + statisticDataCalculationScenario + "']")));
			 * tableDoubleClickCellFirstColumn(statisticDataCalculationScenario);
			 * waitForSpinnerToEnd(); waitForAjaxExtJs(); driverDelay(); //verify
			 * configuration on this page // GL Stat Master = GLSTATS // GL Data Scen = MH
			 * FY05 Reclass // Act Stat Master = ACTSTATS // Act Vol Data Scen = MHFY05 //
			 * Entities = 150 // Dept = ALL // verify Destination Scenario // set dates
			 * Start = Apr 2004 End = Mar 2005 //set calc option
			 * "Delete existing data and recalculate..." //Uncheck Shared Log check box
			 * doClick(getWebElementButtonWithElementText("Save"));
			 */
			// Shilpa updated steps as per ADS-5989 on 2.5.2024
			doClickTreeItem("Statistic Data Calculation Scenarios");
			waitForDisplayedSpinnerToEnd();
			doClick(costingMap.statisticDataCalcScenarionFilterBtn());
			doFilterCreate(filterCalcScenario);
			tableDoubleClickCellFirstColumn(statisticDataCalculationScenario);
//			Actions act=new Actions(driver);
//			act.moveToElement(costingMap.statisticDataScenarionName()).click().sendKeys(Keys.CLEAR).sendKeys(updatedfilterCalcScenario).build().perform();
			// Shilpa updated steps on 23.5.2024 normal sendkeys will send input but Save AS will not be enabled so sript to enter character by character
			costingMap.statisticDataScenarionName().click();
			costingMap.statisticDataScenarionName().clear();
			for (char ch : updateStatisticDataCalculationScenari.toCharArray()) {
				
				  costingMap.statisticDataScenarionName().sendKeys(Character.toString(ch));
		            // Optionally, add a small delay between keystrokes
		            try {
		                Thread.sleep(100); // 100 milliseconds
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		            }
		        }
//			ContractModelsHelper.keyInValues(costingMap.statisticDataScenarionName(),
//					updateStatisticDataCalculationScenari+" ");
			
			assertElementValueAttribute(costingMap.glStatisticMaster(), GLStatisticMaster, printout);
			assertElementValueAttribute(costingMap.glDataScenario(), GLDataScenario, printout);
			assertElementValueAttribute(costingMap.activityStatsMaster(), ActivityStatisticMaster, printout);
			assertElementValueAttribute(costingMap.activityVolDataScenario(), ActivityVolDataScenario, printout);
			assertElementIsDisplayedWithXpath("//div[text()='Entities']//following::li[text()='" + entity + "']");
			assertElementIsDisplayedWithXpath("(//div[text()='Departments']//following::li[text()='ALL  ALL'])[1]");
			assertElementValueAttribute(costingMap.statisticStartMonth(), startMonth, printout);
			assertElementValueAttribute(costingMap.statisticEndMonth(), endMonth, printout);
			doClick(costingMap.statisticDataCalcScenarionSaveAsBtn());
			waitForDisplayedSpinnerToEnd();
			
			ExtentReport.logPass("PASS", "test02VerifyStaticDataCalculationScenarioPageConfigurationAndSave");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02VerifyStaticDataCalculationScenarioPageConfigurationAndSave", driver,
					e);
			fail(e.getMessage());

		}
		// waitFor "Your Progress Has Been Saved" spinner to end
	}

	@Test
	public void test03RunCalculationAndAssertResultsMatchExpected_ADS_5989() throws InterruptedException, Throwable {
		try {
			/*
			 * doClick(getWebElement(
			 * "(//div[contains(@id,'calculationscenario')]//span[text()='Calculate'])[1]"))
			 * ; waitForFirstRowCalculationBarToReach100Percent(10000);
			 * deleteMyCalculationStatusFirstRow();
			 * doClosePageOnLowerBar("Calculation Status"); waitForAjaxExtJs();
			 * doClick(getWebElement("//div[3]/em/button/span[text()='Save & Close']"));
			 * waitForSpinnerToEnd();
			 */
			// Shilpa updated steps as per ADS-5989 on 2.5.2024
			doDropdownSelectUsingOptionText(costingMap.statisticVolDataScenarioDropdown(),
					costingMap.statisticVolDataScenarioList(), statDataScenario);
			if (costingMap.shareLogCheck().getAttribute("class").contains("checked")) {
				doClick(costingMap.shareLog());
			}
			doClick(costingMap.statisticDataCalcScenarionSaveBtn());
			doClick(costingMap.statisticDataCalcScenarionCalculateBtn());
			waitForSpinnerToEnd();;
			doFilterCalculationPage(filterScenario);
			waitForFirstRowCalculationBarToReach100Percent();
			doClosePageOnLowerBar("Calculation Status");
			doClick(costingMap.statisticDataCalcScenarioSaveCloseBtn());
			ExtentReport.logPass("PASS", "test03RunCalculationAndAssertResultsMatchExpected");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03RunCalculationAndAssertResultsMatchExpected", driver, e);
			fail(e.getMessage());

		}
	}

	@Test
	public void test04AssertActivityVolumeDataScenarioFilterResultsMatchExpected_ADS_5989()
			throws Throwable, InterruptedException {
//    doClickTreeItemWithCheckbox("Statistic Data Scenarios");
//    waitForSpinnerToEnd();
//    waitForAjaxExtJs();
//    driverDelay();
//	  doClickTreeItem("Statistic Data Scenarios");
//	    driverDelay();
//	    doClickTreeItemWithCheckbox("Statistic Data Scenarios");
		// Shilpa 26.08.2022 updated xpath
		try {
			/*
			 * driver.findElement(By.
			 * xpath("//td[contains(@class,'x-grid-cell-treecolumn')]/div[text()='Statistic Data Scenarios']"
			 * )).click(); waitForSpinnerToEnd(); waitForAjaxExtJs(); driverDelay();
			 * Thread.sleep(2000); tableDoubleClickCellFirstColumn(statisticDataScenario);
			 * waitForSpinnerToEnd(); waitForAjaxExtJs(); driverDelay();
			 * assertFilterResults(expectedFilterTotal); doClickButton("Cancel & Close");
			 * waitForSpinnerToEnd();
			 */// Shilpa updated steps as per ADS-5989 on 2.5.2024
			doClick("(//td[contains(@class,'x-grid-cell-treecolumn')]//span[text()='Statistic Data Scenarios'])[2]");
			doClick(costingMap.statisticDataScenarionFilterBtn());
			doFilterCreate(filter);
			tableDoubleClickCellFirstColumn(statDataScenario);
			waitForDisplayedSavingSpinnerToEnd();
			doClick(costingMap.statisticDataFilterBtn());
			waitForElementPresence("//span[text()='Filter Statistic Data']");
//			assertElementIsDisplayedWithXpath("//label[text()='Filter to Match These Criteria 1237/1237']");//Shilpa commented, as the counte may vary not valid to assert the count
			assertElementIsDisplayedWithXpath("//label[contains(text(),'Filter to Match These Criteria')]");
			doClick(costingMap.statisticDataFilterCancelClose());
			doClick(costingMap.getCancelCloseStatisticScenario());
			doClickTreeItem("Statistic Data Calculation Scenarios");
			waitForDisplayedSpinnerToEnd();
			doClick(costingMap.statisticDataCalcScenarionFilterBtn());
			doFilterCreate(updatedfilterCalcScenario);
			doClick(costingMap.statisticDataCalcScenarionDeleteBtn());
			doClick(costingMap.warningMessageDeleteBtn());
			ExtentReport.logPass("PASS", "test04AssertActivityVolumeDataScenarioFilterResultsMatchExpected");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04AssertActivityVolumeDataScenarioFilterResultsMatchExpected", driver, e);
			fail(e.getMessage());

		}
	}
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
