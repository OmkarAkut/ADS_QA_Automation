package webdriver.scripts.costing.unitcostquickcalculation.ucqcrvus;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.JavaHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/**Regression test case ADS-5919 **/
public class OverWriteRVUValues5919 extends UcqcHelper {
	static CostingMap overwriteRvuMaint;
	static String[] filter = { "Cost Model Name", "Is", "Equal To", "QA Cost Model" };
	static String[] filterByDeptCode = { "Department Code", "Is", "Equal To", "3120" };
	private static CostingMap costing;
	static ContractingMap contracting;
	static final String[] requiredFields = { "QA Cost Model", "ADS-1238 A By Month", "150 Marina Medical Center",
			"3120", "Apr 2004 to Apr 2004" };

	int value = JavaHelper.javaGetRandomNumber(12, printout);

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("OverwriteRvuMaintenancePopupAds1181",
				"webdriver.scripts.costing.unitcostquickcalculation.ucqcrvus", "OverwriteRvuMaintenancePopupAds1181");
		try {
			costing = BuildMap.getInstance(driver, CostingMap.class);
			contracting = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("CostingDepartmentManager1");
			goToPage("RVU Maintenance");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test01OpenRvuCostModelAndImport() throws Throwable {
		try {
			doClick(costing.getRvuMaintenanceButtonFilter());
			doFilterCreate(filter);
			doClick(costing.getRvuMaintenanceButtonImport());
			doClick(costing.getRvuSecImportSelectButton());
			costing.getRvuSecImportSelectButton().sendKeys(Keys.ENTER);
			;
			driverDelay(500);
			ContractModelsHelper.uploadTheFileusingAutoIT(driver,
					System.getProperty("user.dir") + "\\AutoIT\\UploadFile.exe",
					System.getProperty("user.dir") + "\\AutoIT\\ADS1238PreConditionRVUs.txt");
			driverDelay(1200);
			doClick(costing.getRvuSharedLocDropdown());
			driverDelay(300);
			doClick(contracting.getContractModelExportFileSharedLocOption());
			ContractModelsHelper.keyInValues(costing.getRvuFileNameInput(), "Test");
			doClick(costing.getRvuImportButton());
			waitForSpinnerToEnd();
			ContractModelsHelper.waitForFirstRowCalculationBarToReach100Percent();
			ExtentReport.logPass("PASS", "test01OpenRvuCostModelAndImport");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01OpenRvuCostModelAndImport", driver, e);
			fail(e.getMessage());
		} finally {
			doClosePageOnLowerBar("Import/Export Status");
			doClosePageOnLowerBar("RVU Maintenance");

		}
	}

	@Test
	public void test02ApplyRvuSelections() throws Throwable {
		try {
			goToPage("Unit Cost Quick Calculation");
			waitForDisplayedSpinnerToEnd();
			ucqcDisplayChargeCodeGrid(requiredFields);
			ExtentReport.logPass("PASS", "test02ApplyRvuSelections");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ApplyRvuSelections", driver, e);
			fail(e.getMessage());
		}
	}

	public void getCell(String chargeCode, String headerName, String value)
			throws NumberFormatException, InterruptedException {
		String columnID;

		columnID = driver
				.findElement(By.xpath("//*[contains(@class,'column-header-text')][text()='" + headerName + "']"))
				.getAttribute("id");
		int columnIdDigits = Integer.parseInt(getNumbersFromStringWithRegex(columnID));
		String row = driver.findElement(By.xpath("//*[text()='" + chargeCode + "']/../../descendant::div[1]"))
				.getText();
		System.out.println(row);
		System.out.println(columnIdDigits);
		WebElement editCell = driver.findElement(By.xpath("//tr[contains(@class,'x-grid-row')][" + row
				+ "]/descendant::*[contains(@class,'x-grid-cell-numbercolumn-" + columnIdDigits + "')]/div"));
		if (!editCell.getText().equals(value)) {
			ucqcUpdateGridCellValue(chargeCode, headerName, String.valueOf(value), printout);

		}
	}

	@Test
	public void test03ApplyRvuSelections() throws Throwable {
		try {
			ucqcUpdateGridCellValue("1804582", "Quick Salaries and Wages RVU", String.valueOf(value), printout);
			getCell("1804582", "Quick Salaries and Wages RVU", "0.000000");
			getCell("1805373", "Quick Salaries and Wages RVU", "0.000000");
			getCell("1805381", "Quick Salaries and Wages RVU", "0.000000");
			getCell("1811355", "Quick Salaries and Wages RVU", "0.000000");
			getCell("1811751", "Quick Salaries and Wages RVU", "9999918.12409");
			getCell("1850205", "Quick Salaries and Wages RVU", "8888819.123456");
			getCell("1856251", "Quick Salaries and Wages RVU", "-7777710.123456");
			getCell("1890011", "Quick Salaries and Wages RVU", "-5555505.123456");
			getCell("1890029", "Quick Salaries and Wages RVU", ".000003");
			getCell("1890037", "Quick Salaries and Wages RVU", ".000004");
			getCell("1890045", "Quick Salaries and Wages RVU", "-.000005");
			getCell("1890052", "Quick Salaries and Wages RVU", "-.000006");
			getCell("1890060", "Quick Salaries and Wages RVU", "9654.123417");
			getCell("1890086", "Quick Salaries and Wages RVU", "56.123418");
			getCell("1890094", "Quick Salaries and Wages RVU", "-5681.123419");
			getCell("1890102", "Quick Salaries and Wages RVU", ".009902");
			doClick(costing.getUnitCostQuickCalculationButtonSaveQuickRVUs());
			waitForDisplayedSpinnerToEnd();
			doClick(costing.getUnitCostQuickCalculationButtonOverwriteRVUMaintenance());
			waitForDisplayedSpinnerToEnd();
			waitForPageTitle("Overwrite RVU Maintenance");
			doDropdownSelectUsingOptionText(
					costing.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalDropdownMonth(),
					costing.unitCostQuickCalculationOverwriteRVUMaintenanceModalDropdownMonthOptions(), "Apr");
			doDropdownSelectUsingOptionText(
					costing.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalDropdownYear(),
					costing.unitCostQuickCalculationOverwriteRVUMaintenanceModalDropdownYearOptions(), "2004");
			selectCostComponent("Salaries and Wages", printout);
			doClick(costing.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonOverwriteRVUs());
			waitForElementToBeVisible(
					costing.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalWarningButtonOverwrite());
			doClick(costing.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalWarningButtonOverwrite());
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "test02ApplyRvuSelections");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ApplyRvuSelections", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test04NavigateToRvuMaintenance() throws Throwable {
		try {
			goToPage("RVU Maintenance");
			waitForDisplayedSpinnerToEnd();
			doClick(costing.getRvuMaintenanceButtonFilter());
			doFilterCreate(filter);
			waitForAjaxExtJs();
			doClick(costing.getRvuMaintenanceButtonMaintainRVUs());
			waitForDisplayedSpinnerToEnd();
			waitForAjaxExtJs();
			doDropdownSelectUsingOptionText(costing.getRvuEntityDropdown(), costing.getRvuEntityDropdownOptions(),
					"150 Marina Medical Center");
			ContractModelsHelper.updateDepartment("3120");
			doDropdownSelectUsingOptionText(costing.getRvuMaintenanceDropdownEffectiveMonthStartMonthDropdown(),
					costing.getRvuMaintenanceDropdownEffectiveMonthStartMonthOptions(), "Apr");
			doDropdownSelectUsingOptionText(costing.getRvuMaintenanceDropdownEffectiveMonthStartYearDropdown(),
					costing.getRvuMaintenanceDropdownEffectiveMonthStartYearOptions(), "2004");

			ExtentReport.logPass("PASS", "test04NavigateToRvuMaintenance");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04NavigateToRvuMaintenance", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test05ApplyRvuSelections() throws Throwable {
		try {
			doClick(costing.getRvuApplySelections());
			waitForDisplayedSpinnerToEnd();
			assertElementIsDisplayedWithXpath("//*[text()='Salaries and Wages'][text()='Apr 2004']");
			VerifyCellValue("1804582", "0.000000");
			VerifyCellValue("1805373", "0.000000");
			VerifyCellValue("1805381", "0.000000");
			VerifyCellValue("1811355", "0.000000");
			VerifyCellValue("1811751", "9999918.12409");
			VerifyCellValue("1850205", "8888819.123456");
			VerifyCellValue("1856251", "-7777710.123456");
			doClick(costing.getRvuNextPageButton());
			waitForDisplayedSpinnerToEnd();
			VerifyCellValue("1890011", "-5555505.123456");
			VerifyCellValue("1890029", ".000003");
			VerifyCellValue("1890037", ".000004");
			VerifyCellValue("1890045", "-.000005");
			VerifyCellValue("1890052", "-.000006");
			VerifyCellValue("1890060", "9654.123417");
			VerifyCellValue("1890086", "56.123418");
			VerifyCellValue("1890094", "-5681.123419");
			VerifyCellValue("1890102", ".009902");
			ExtentReport.logPass("PASS", "test04NavigateToRvuMaintenance");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04NavigateToRvuMaintenance", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test06FilterByDepartmentCodeInRvuContainer() throws Throwable {
		try {
			doClick(costing.getRvuMaintenanceButtonRvuContainerList());
			waitForDisplayedSpinnerToEnd();
			doClick(costing.getRvuContainerFilterButton());
			doFilterCreate(filterByDeptCode);
			ExtentReport.logPass("PASS", "test06FilterByDepartmentCodeInRvuContainer");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06FilterByDepartmentCodeInRvuContainer", driver, e);
			fail(e.getMessage());
		}
	}

	public void VerifyCellValue(String chargeCode, String expValue) throws Throwable {
		try {
			ContractModelsHelper contractModelsHelper = new ContractModelsHelper();
			String value = contractModelsHelper.getCellValue(chargeCode);
			if (value.equals(expValue)) {
				assertTrue(printout);
			} else {
				assertFalse(false);
			}
		} catch (Exception | AssertionError e) {

		}
	}

	@AfterClass
	public static void endtest() throws Exception {
		doClosePageOnLowerBar("RVU Maintenance");
		doClosePageOnLowerBar("Unit Cost Quick...");
		ExtentReport.report.flush();

	}
}
