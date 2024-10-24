package webdriver.scripts.costing;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RVUCalculationScenario extends CalculationHelper {
	static CostingMap costing;
	static ContractingMap modelMap;
	static String[] filter = { "Name", "Is", "Equal To", "Marina" };
	static String[] rvuFilter = { "Name", "Is", "Equal To", "*DM ADS-696 CMS G" };
	static String[] rvuCalcFilter = { "Scenario Name", "Is", "Equal To", "*DM ADS-696 CMS G" };
	static String costModel = "Marina";

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("RVUCalculationScenario", "webdriver.scripts.costing", "RVUCalculationScenario");
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

	@Test
	public void test01OpenCostModel_ADS_5987() throws Throwable {
		try {
			doClick(CostingMap.getCostingBtn());
			driverDelay();
			doClick("//span[text()='Marina Health']");
			driverDelay();
			doClick(costing.getCostModelFilterBtn());
			doFilterCreate(filter);
			tableDoubleClickCellFirstColumn(costModel);
			doClickTreeItem("Assign Unit Costs");
			driverDelay();
			doClickTreeItem("RVU Calculation Scenarios");
			ExtentReport.logPass("PASS", "test01OpenCostModel_ADS_5987");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02AssertNewCostModel_6253", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test02CalculateRVUScenario_ADS_5987() throws Throwable {
		try {
			doClick(CostingMap.getrvuCalcFilterBtn());
			doFilterCreate(rvuFilter);
			doClick(CostingMap.getrvuCalcBtn());
			doFilterCalculationPage(rvuCalcFilter);
			waitForFirstRowCalculationBarToReach100Percent();
			calculationStatusPageOpenViewDialog();
			driverDelay(500);
			checkForRecordsProcessed(
					"Perform Calculation for ---> 150 Marina Medical Center 3311 LAB CLINICAL LABORATORY Medical Supplies");
			checkForRecordsProcessed("Perform Calculation for ---> 150 Marina Medical Center 2016");
			checkForRecordsProcessed("Process Completed");
			closeViewDialog();
			doClosePageOnLowerBar("Calculation Status");
			doClosePageOnLowerBar("Marina");
			ExtentReport.logPass("PASS", "test02CalculateRVUScenario_ADS_5987");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02CalculateRVUScenario_ADS_5987", driver, e);
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void endtest() throws Exception {
		doClosePageOnLowerBar("Costing Models");
		ExtentReport.report.flush();

	}
}
