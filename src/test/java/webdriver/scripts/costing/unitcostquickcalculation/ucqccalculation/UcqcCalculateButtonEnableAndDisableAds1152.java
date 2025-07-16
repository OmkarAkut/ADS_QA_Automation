package webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.interactions.Actions;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

/** Zephyr ticket ADS-1152 (Dev Story ADS-364). Last Updated 06-21-19. */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UcqcCalculateButtonEnableAndDisableAds1152 extends UcqcHelper {

	boolean printout = false;
	private static CostingMap ucqcMap;
	private static Actions act;
	private String[] requiredFields = { "QA Cost Model", "QA MHFY05 After Vol Change", "150 Marina Medical Center",
			//"2110  ICU",
			"2110", //venkat update text data 13.09.2022
			"Apr 2004 to Mar 2005" };

	@BeforeClass
	public static void setupScript() throws Throwable {
		ExtentReport.reportCreate("UcqcCalculateButtonEnableAndDisableAds1152",
				"webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation",
				"UcqcCalculateButtonEnableAndDisableAds1152");
		
		try {
			act = new Actions(driver);
			ucqcMap = BuildMap.getInstance(driver, CostingMap.class);
			System.out.println("Test Class: " + UcqcCalculateButtonEnableAndDisableAds1152.class.getSimpleName());
			Login.loginUser("CostingDepartmentManager1");
			goToPage("Unit Cost Quick Calculation");
			doMaximizeWindow();
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}
			
		
	}

	@Test
	public void test02UcqcPageVerifyDefaultStateOfCalculateButtonAsDisabled() throws Throwable {
		try {
			
			waitForAjaxExtJs();
			assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonCalculate(), printout);
			ExtentReport.logPass("PASS", "test02UcqcPageVerifyDefaultStateOfCalculateButtonAsDisabled");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02UcqcPageVerifyDefaultStateOfCalculateButtonAsDisabled", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test03UcqcPageVerifyDisabledStateOfCalculateButtonAfterSelectingRequiredFields() throws Throwable {
		try {
			
			waitForAjaxExtJs();
			ucqcPopulateRequiredFieldsToDisplayGrid(requiredFields);
			assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonCalculate(), printout);
			ExtentReport.logPass("PASS",
					"test03UcqcPageVerifyDisabledStateOfCalculateButtonAfterSelectingRequiredFields");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL",
					"test03UcqcPageVerifyDisabledStateOfCalculateButtonAfterSelectingRequiredFields", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test04UcqcPageVerifyDisabledStateOfCalculateButtonAfterSelectingRequiredFieldsAndClickApplySelectionsButton()
			throws Throwable {
		try {
			doClick(ucqcMap.getUnitCostQuickCalculationButtonApplySelections());
			waitForSpinnerToEnd();
			waitForAjaxExtJs();
			assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonCalculate(), printout);
			ExtentReport.logPass("PASS",
					"test04UcqcPageVerifyDisabledStateOfCalculateButtonAfterSelectingRequiredFieldsAndClickApplySelectionsButton");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL",
					"test04UcqcPageVerifyDisabledStateOfCalculateButtonAfterSelectingRequiredFieldsAndClickApplySelectionsButton",
					driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test04UcqcPageVerifyDisabledStateOfCalculateButtonAfterUpdatingGridCell() throws Throwable {
		try {
			String cellValue = getCellValue("1100023", "Quick Salaries and Wages RVU");
			ucqcUpdateGridCellValue("1100023", "Quick Salaries and Wages RVU", cellValue, printout);
			waitForAjaxExtJs();
			assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonSaveQuickRVUs(), printout);
			assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonCalculate(), printout);
			ExtentReport.logPass("PASS", "test04UcqcPageVerifyDisabledStateOfCalculateButtonAfterUpdatingGridCell");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04UcqcPageVerifyDisabledStateOfCalculateButtonAfterUpdatingGridCell",
					driver, e);

			fail(e.getMessage());
		}
	}

	@Test
	public void test05UcqcPageVerifyEnabledStateOfCalculateButtonAfterSavingGridCellUpdate() {
		try {
			doClick(ucqcMap.getUnitCostQuickCalculationButtonSaveQuickRVUs());
			waitForSpinnerToEnd();
			waitForAjaxExtJs();
			assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonSaveQuickRVUs(), printout);
			assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonCalculate(), printout);
			ExtentReport.logPass("PASS", "test05UcqcPageVerifyEnabledStateOfCalculateButtonAfterSavingGridCellUpdate");
		} catch (Exception | AssertionError e) {
			ExtentReport.logPass("PASS", "test05UcqcPageVerifyEnabledStateOfCalculateButtonAfterSavingGridCellUpdate");
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}

}
