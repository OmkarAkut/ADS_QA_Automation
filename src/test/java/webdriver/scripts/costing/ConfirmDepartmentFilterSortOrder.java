package webdriver.scripts.costing;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

public class ConfirmDepartmentFilterSortOrder extends GoHelper {
	private static CostingMap costingMap;
	static ContractingMap contractMap;
	static String costModel = "ASESC-2909 Cost";
	static String costModelScenario = "ASESC2909 CMS";
	static String entity = "150 Marina Medical Center";

/** Regression Test ADS-5922 **/
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ConfirmDepartmentFilterSortOrder", "webdriver.scripts.costing",
				"ConfirmDepartmentFilterSortOrder");
		try {
			costingMap = BuildMap.getInstance(driver, CostingMap.class);
			contractMap = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Unit Cost Quick Calculation");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-5922
	@Test
	public void test01ConfirmDepartmentFilterSort_5922() throws Throwable {
		try {
			doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModel(),
					costingMap.getUnitCostQuickCalculationDropdownCostModelMenuList(), costModel);
			doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),
					costingMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(), costModelScenario);
			doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownEntity(),
					costingMap.getUnitCostQuickCalculationDropdownEntityMenuList(), entity);
			doClickButton("Select");
			waitForDisplayedSpinnerToEnd();
			waitForAjaxExtJs();
			doClick("//div[contains(@class,'dynamicformCls')]//following::span[text()='Filter']");
			assertTextIsDisplayed("Filter to Match These Criteria 300/300");
			doFilterSetFilterParameters("Department Code", "Is", "Contains", "0");
			doClick(costingMap.getCostModelScenarioCalculationFilterButtonAdd());
			waitForAjaxExtJs();
			assertTextIsDisplayed("Filter to Match These Criteria 138/300");
			ExtentReport.logPass("PASS", "test01ConfirmDepartmentFilterSort");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ConfirmDepartmentFilterSort", driver, e);
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();

	}
}
