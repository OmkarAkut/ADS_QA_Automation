package webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.JavaHelper;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

public class CreateUCQCLogMakeUCQCProcessAvailableCalculationPage extends UcqcHelper {
	private static CostingMap costingMap;
	static ContractingMap contractingMap;
	static final String[] requiredFields = { "Marina", "*CM1 TB MHFY05 After Vol Change", "150 Marina Medical Center",
			"2130", "Apr 2004 to Apr 2004" };
	CalculationHelper calculationHelper = new CalculationHelper();

	// Regression Test ADS-5923 **/
	@BeforeClass
	public static void setupScript() throws Throwable {
		ExtentReport.reportCreate("CreateUCQCLogMakeUCQCProcessAvailableCalculationPage",
				"webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation",
				"CreateUCQCLogMakeUCQCProcessAvailableCalculationPage");

		try {
			costingMap = BuildMap.getInstance(driver, CostingMap.class);
			contractingMap = BuildMap.getInstance(driver, ContractingMap.class);
//			Login.loginUser("CostingDepartmentManager1"); check in stage if calculation status updated
			Login.loginUser("AutomationTesterAdmin");
			webdriverMaximizeWindow();
			goToPage("Unit Cost Quick Calculation");
			waitForAjaxExtJs();
			ucqcDisplayChargeCodeGrid(requiredFields);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}

	}
//ADS-5923, add step 14 -16
	@Test
	public void test01ChangeValues_5923() throws Throwable {
		int value = JavaHelper.javaGetRandomNumber(12, printout);
		ucqcUpdateGridCellValue("2200343", "Quick Salaries and Wages RVU", String.valueOf(value), printout);
		doClick(costingMap.getUnitCostQuickCalculationButtonSaveQuickRVUs());
		driverDelay(3000);
		doClick(costingMap.getUnitCostQuickCalculationButtonCalculate());
		waitForDisplayedSpinnerToEnd();
		driverDelay(10000);
		goToPage("Calculation Status");
		CalculationHelper.waitForFirstRowCalculationBarToReach100Percent();
		driverDelay();
		doClick("(//a[text()='Download'])[1]");
		calculationHelper.calculationStatusPageOpenViewDialog();
		calculationHelper.closeViewDialog();
	}

	@AfterClass
	public static void endtest() throws Exception {
		doClosePageOnLowerBar("Calculation Status");
		doClosePageOnLowerBar("Unit Cost Quick...");
		ExtentReport.report.flush();

	}
}
