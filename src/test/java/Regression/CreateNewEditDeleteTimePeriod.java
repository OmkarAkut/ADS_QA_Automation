package Regression;

import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreateNewEditDeleteTimePeriod extends GoHelper {
	static String costModel = "Test Cost Model 100";
	static CostingMap costing;
	static ContractingMap modelMap;
	static String month = "Feb";
	static String year = "2024";
	static String currentDateTime = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
	static String costTimePeriod = "Time Period" + currentDateTime;
	static String[] filter = { "Name", "Is", "Equal", costTimePeriod };


	/** Automates test ticket ADS-6673, ADS-6675 ,ADS-6672*/

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("CreateNewEditDeleteTimePeriod", "webdriver.scripts.costing", "CreateNewEditDeleteTimePeriod");
		try {
			costing = BuildMap.getInstance(driver, CostingMap.class);
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Costing Models");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-6673
	@Test
	public void test01OpenCostModel_6673() throws Throwable {
		try {
			doSearchForModel(costModel);
			tableDoubleClickCellFirstColumn(costModel);
			driverDelay(300);
			assertTextIsDisplayed("CM Test");
			assertTextIsDisplayed("FISCAL YEAR SETUP");
			assertTextIsDisplayed("COST PROCESS");
			ExtentReport.logPass("PASS", "test01OpenCostModel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01OpenCostModel", driver, e);
			fail(e.getMessage());
		}

	}
	//ADS-6673
	@Test
	public void test02CreateNewTimePeriod_6673() throws Throwable {
		try {
			doClickTreeItem("FISCAL YEAR SETUP");
			waitForMainPageTitle("Creat new Time Period");
			doClickTreeItemWithCheckbox("Creat new Time Period");
			doClick(CostingMap.getCostModelTimePeriodNewButton());
			waitForPageTitle("New Time Period");
			ContractModelsHelper.keyInValues(ContractingMap.getInputName(), costTimePeriod);
			doDropdownSelectUsingOptionText(ContractingMap.getMonthdropdown(),
					CostingMap.getCostModelTimePeriodMonthScenarioOptions(), month);
			doDropdownSelectUsingOptionText(ContractingMap.getYeardropdown(),
					CostingMap.getCostModelTimePeriodYearScenarioOptions(), year);
			doClick(DataMaintenanceMap.getSaveandCreateNewButton());
			waitForAjaxExtJs();
			doClick(ContractingMap.getContractModelRiskLimiterCancelCloseBtn());
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "test02CreateNewTimePeriod");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02CreateNewTimePeriod", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-6672
	@Test
	public void test02EditNewTimePeriod_6672() throws Throwable {
		try {
			doClick(CostingMap.getCostModelTimePeriodFilterButton());
			doFilterCreate(filter);
			doClick(CostingMap.getCostModelTimePeriodEditButton());
			waitForPageTitle(costModel);
			assertThatAttributeValue(ContractingMap.getInputName(), costTimePeriod, printout);
			assertThatAttributeValue(ContractingMap.getMonthdropdown(), month, printout);
			assertThatAttributeValue(ContractingMap.getYeardropdown(), year, printout);
			assertThatFieldReadonly(ContractingMap.getMonthdropdown());
			assertThatFieldReadonly(ContractingMap.getYeardropdown());
			doClick(ContractingMap.getContractModelRiskLimiterCancelCloseBtn());
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "test02CreateNewTimePeriod");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02CreateNewTimePeriod", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-6675
	@Test
	public void test03DeleteNewTimePeriod_6675() throws Throwable {
		try {
			doClick(CostingMap.getCostModelTimePeriodDeleteButton());
			waitForElementToBeVisible(ContractingMap.getWarningPopUpDeleteButton());
			assertElementIsDisplayed(ContractingMap.getWarningPopUpDeleteButton());
			assertElementIsDisplayed(ContractingMap.getContractCalculationCloseViewDialog());
			doClick(ContractingMap.getWarningPopUpDeleteButton());
			waitForDisplayedSpinnerToEnd();
			assertTextIsDisplayed("There is no data available to display.");
			ExtentReport.logPass("PASS", "test03DeleteNewTimePeriod");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03DeleteNewTimePeriod", driver, e);
			fail(e.getMessage());
		}
		finally {
			doClosePageOnLowerBar(costModel);
			doClosePageOnLowerBar("Model Library");
		}
	}

	@AfterClass
	public static void endtest() {
		ExtentReport.report.flush();
	}
}
