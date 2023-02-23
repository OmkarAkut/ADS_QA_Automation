package webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VerifyUCQCButtonsBackgroundColor extends UcqcHelper {
	private static CostingMap costingMap;
	private static ContractingMap contractMap;
	private static String BackgroundColor = "rgba(0, 0, 0, 0)";
	static final String[] requiredFields = { "QA Marina", "ADS-302 LG By Month", "150 Marina Medical Center", "Q302", 
			"Apr 2004 to Apr 2004" };

	@BeforeClass
	public static void setupScript() throws Throwable {
		ExtentReport.reportCreate("VerifyUCQCButtonsBackgroundColor",
				"webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation",
				"VerifyUCQCButtonsBackgroundColor");

		try {
			costingMap = BuildMap.getInstance(driver, CostingMap.class);
			contractMap = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("CostingDepartmentManager1");
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

	@Test
	public void test01AssertUCQCOptions() throws Throwable {
		try {
			assertElementIsDisplayed(costingMap.getUnitCostQuickCalculationButtonApplySelections());
			assertElementIsDisplayed(costingMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs());
			assertElementIsDisplayed(costingMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave());
			assertElementIsDisplayed(costingMap.getUnitCostQuickCalculationButtonUndo());
			assertElementIsDisplayed(costingMap.getUnitCostQuickCalculationButtonSaveQuickRVUs());
			assertElementIsDisplayed(costingMap.getUnitCostQuickCalculationButtonCalculate());
			assertElementIsDisplayed(costingMap.getUnitCostQuickCalculationButtonOverwriteRVUMaintenance());
			assertElementIsDisplayed(costingMap.getUnitCostQuickCalculationButtonCalculate());
			ExtentReport.logPass("PASS", "test01AssertUCQCOptions");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01AssertUCQCOptions", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test02VerifyBackgroundColorForUCQCOptions() throws Throwable {
		try {
			validateBackgroundColor(BackgroundColor, costingMap.getUnitCostQuickCalculationButtonApplySelections());
			validateBackgroundColor(BackgroundColor, costingMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs());
			validateBackgroundColor(BackgroundColor,
					costingMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave());
			validateBackgroundColor(BackgroundColor, costingMap.getUnitCostQuickCalculationButtonClearAndSave());
			validateBackgroundColor(BackgroundColor, costingMap.getUnitCostQuickCalculationButtonUndo());
			validateBackgroundColor(BackgroundColor, costingMap.getUnitCostQuickCalculationButtonSaveQuickRVUs());
			validateBackgroundColor(BackgroundColor, costingMap.getUnitCostQuickCalculationButtonCalculate());
			validateBackgroundColor(BackgroundColor,
					costingMap.getUnitCostQuickCalculationButtonOverwriteRVUMaintenance());
			validateBackgroundColor(BackgroundColor, costingMap.getUnitCostQuickCalculationButtonCalculate());
			ExtentReport.logPass("PASS", "test02VerifyBackgroundColorForUCQCOptions");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02VerifyBackgroundColorForUCQCOptions", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test03VerifyCopyToRvuOverwritePopUp() throws Throwable {
		try {
			doClick(costingMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs());
			assertElementIsDisplayed(costingMap.getCopyRvuWindow());
			selectCostComponent("Salaries and Wages", printout);
			assertElementIsEnabled(costingMap.getCopyandSaveRvuButton(), printout);
			doClick(costingMap.getCopyandSaveRvuButton());
			assertElementIsDisplayed(costingMap.getCopyandSaveRvuWarningPopUp());
			doClick(costingMap.getRvuCancelButton());
			doClick(costingMap.getRvuMaintenanceFilterButtonCancelAndClose());
			doClick(costingMap.getUnitCostQuickCalculationButtonOverwriteRVUMaintenance());
			assertElementIsDisplayed(costingMap.getOverwriteRvuWarningPopUp());
			selectCostComponent("Salaries and Wages", printout);
			assertElementIsEnabled(
					costingMap.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonOverwriteRVUs(), printout);
			doClick(costingMap.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonOverwriteRVUs());
			assertElementIsDisplayed(
					costingMap.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalWarningButtonOverwrite(),
					printout);
			doClick(costingMap.getRvuCancelButton());
			doClick(costingMap.getRvuMaintenanceFilterButtonCancelAndClose());
			updateGridCellValue("1160282", "Quick Salaries and Wages RVU", "10", printout);
			doClick(costingMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave());
			assertElementIsDisplayed(costingMap.getClearRvuWarningPopUp());
			doClick(contractMap.getContractModelCancelButtonInPopUp());
			ExtentReport.logPass("PASS", "test03VerifyCopyToRvuOverwritePopUp");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03VerifyCopyToRvuOverwritePopUp", driver, e);
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void endtest() throws Exception {
		doClosePageOnLowerBar("Unit Cost Quick...");
		ExtentReport.report.flush();

	}
}
