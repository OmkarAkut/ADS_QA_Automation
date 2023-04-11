package webdriver.scripts.costing;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.GeneralElementsMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/**Regression Test ADS-5920 **/
public class OverwriteRvuNoCMSResults extends GoHelper {
	static CostingMap costing;
	static ModelLibraryMap modelMap;
	static ContractingMap contractMap;
	static GeneralElementsMap generalMap;
	static DialogsMap dialog;
	static String costModel = "QA Cost Model";
	static String[] filterByCostModel = { "Cost Model Name", "Is", "Equal To", costModel };
	UcqcHelper ucqcHelper = new UcqcHelper();
	static String[] filterByDept = { "Department Code", "Is", "Equal To", "3110" };
	static String[] ucqcRequiredFields = { "QA Cost Model", "ADS-1309 A By Month", "150 Marina Medical Center", "3110",
			"Apr 2004 to Apr 2004" };

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {

		ExtentReport.reportCreate("OverwriteRvuNoCMSResults", "webdriver.scripts.costing", "OverwriteRvuNoCMSResults");
		try {
			generalMap = BuildMap.getInstance(driver, GeneralElementsMap.class);
			costing = BuildMap.getInstance(driver, CostingMap.class);
			contractMap = BuildMap.getInstance(driver, ContractingMap.class);
			dialog = BuildMap.getInstance(driver, DialogsMap.class);
			Login.loginUser("AutomationTesterAdmin");
			waitForAjaxExtJs();
			waitForDisplayedSpinnerToEnd();
			goToPage("Rvu Maintenance");
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test01OpenCostModel() throws Throwable {
		try {
			doClick(costing.getRvuMaintenanceButtonFilter());
			doFilterCreate(filterByCostModel);
			doClick(costing.getRvuMaintenanceButtonImport());
			assertTextIsDisplayed("Import Data");
			doClick(costing.getRvuSecImportSelectButton());
			costing.getRvuSecImportSelectButton().sendKeys(Keys.ENTER);
			;
			driverDelay(500);
			ContractModelsHelper.uploadTheFileusingAutoIT(driver,
					System.getProperty("user.dir") + "\\AutoIT\\UploadFile.exe",
					System.getProperty("user.dir") + "\\AutoIT\\ADS1309PreConditionsRVUImport.txt");
			doClick(costing.getRvuSharedLocDropdown());
			driverDelay(300);
			doClick(contractMap.getContractModelExportFileSharedLocOption());
			ContractModelsHelper.keyInValues(costing.getRvuFileNameInput(), "Test");
			doClick(costing.getRvuImportButton());
			waitForSpinnerToEnd();
			assertElementIsDisplayed(contractMap.getContractModelImportExportstatusPage());
			ContractModelsHelper.waitForFirstRowCalculationBarToReach100Percent();
			doClosePageOnLowerBar("Import/Export Status");
			ExtentReport.logPass("PASS", "test01OpenCostModel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01OpenCostModel", driver, e);
			fail(e.getMessage());
		}

	}

	@Test
	public void test02ApplyRvuFiltersAndVerifyHeaders() throws Throwable {
		try {
			doClick(costing.getRvuMaintenanceButtonMaintainRVUs());
			doDropdownSelectUsingOptionText(costing.getRvuMaintenanceDropdownEntity(),
					costing.getRvuMaintenanceDropdownEntityOptions(), "150 Marina Medical Center");
			UcqcHelper.updateDepartment("3110");
			doDropdownSelectUsingOptionText(costing.getRvuMaintenanceDropdownEffectiveMonthStartMonthDropdown(),
					costing.getRvuMaintenanceDropdownEffectiveMonthStartMonthOptions(), "Apr");
			doDropdownSelectUsingOptionText(costing.getRvuMaintenanceDropdownEffectiveMonthStartYearDropdown(),
					costing.getRvuMaintenanceDropdownEffectiveMonthStartYearOptions(), "2004");
			doClick(costing.getRvuMaintenanceButtonApplySelections());
			waitForDisplayedSpinnerToEnd();
			assertElementIsDisplayedWithXpath("//*[text()='Salaries and Wages'][text()='Apr 2004']");
			assertElementIsDisplayedWithXpath("//*[text()='Employee Benefits'][text()='Apr 2004']");
			assertElementIsDisplayedWithXpath("//*[text()='Medical Supplies'][text()='Apr 2004']");
			assertElementIsDisplayedWithXpath("//*[text()='Non-Medical Supplies'][text()='Apr 2004']");
			assertElementIsDisplayedWithXpath("//*[text()='Direct Depreciation'][text()='Apr 2004']");
			assertElementIsDisplayedWithXpath("//*[text()='Purchased Services'][text()='Apr 2004']");
			assertElementIsDisplayedWithXpath("//*[text()='Professional Fees'][text()='Apr 2004']");
			assertElementIsDisplayedWithXpath("//*[text()='Other Expenses'][text()='Apr 2004']");
			assertElementIsDisplayedWithXpath("//*[text()='Direct Overhead'][text()='Apr 2004']");
			assertElementIsDisplayedWithXpath("//*[text()='Hospital Overhead'][text()='Apr 2004']");
			assertElementIsDisplayedWithXpath("//*[text()='Depreciation'][text()='Apr 2004']");
			assertElementIsDisplayedWithXpath("//*[text()='Tech'][text()='Apr 2004']");
			ExtentReport.logPass("PASS", "test02ApplyRvuFiltersAndVerifyHeaders");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ApplyRvuFiltersAndVerifyHeaders", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test03VerifyGridParametersforChargeCode2100048() throws Throwable {
		try {
			ucqcHelper.VerifyCellValue("2100048", "", costing.SalariesAndWagesXpath);
			ucqcHelper.VerifyCellValue("2100048", "0.000000", costing.EmployeeBenifitsXpath);
			ucqcHelper.VerifyCellValue("2100048", "1.123403", costing.MedicalSuppliesXpath);
			ucqcHelper.VerifyCellValue("2100048", "-1.123404", costing.NonMedicalSuppliesXpath);
			ucqcHelper.VerifyCellValue("2100048", "", costing.EquipRepairMaintXpath);
			ucqcHelper.VerifyCellValue("2100048", "", costing.DirectDepreciationXpath);
			ucqcHelper.VerifyCellValue("2100048", "1.123407", costing.PurchasedServicesXpath);
			ucqcHelper.VerifyCellValue("2100048", "-1.123408", costing.ProfessionalFeesXpath);
			ucqcHelper.VerifyCellValue("2100048", "1111109.123456", costing.OtherExpensesXpath);
			ucqcHelper.VerifyCellValue("2100048", "-2222210.123456", costing.DirectOverheadXpath);
			ucqcHelper.VerifyCellValue("2100048", "3333311.123456", costing.HospitalOverheadXpath);
			ucqcHelper.VerifyCellValue("2100048", "-4444412.123456", costing.CorporateOverheadXpath);
			ucqcHelper.VerifyCellValue("2100048", "0.000001", costing.DepreciationXpath);
			ucqcHelper.VerifyCellValue("2100048", "-0.000002", costing.TechXpath);
			ExtentReport.logPass("PASS", "test03VerifyGridParametersforChargeCode2100048");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03VerifyGridParametersforChargeCode2100048", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test04VerifyGridParametersforChargeCode2100055() throws Throwable {
		try {
			ucqcHelper.VerifyCellValue("2100055", "", costing.SalariesAndWagesXpath);
			ucqcHelper.VerifyCellValue("2100055", "0.000000", costing.EmployeeBenifitsXpath);
			ucqcHelper.VerifyCellValue("2100055", "1.123403", costing.MedicalSuppliesXpath);
			ucqcHelper.VerifyCellValue("2100055", "-1.123404", costing.NonMedicalSuppliesXpath);
			ucqcHelper.VerifyCellValue("2100055", "", costing.EquipRepairMaintXpath);
			ucqcHelper.VerifyCellValue("2100055", "", costing.DirectDepreciationXpath);
			ucqcHelper.VerifyCellValue("2100055", "1.123407", costing.PurchasedServicesXpath);
			ucqcHelper.VerifyCellValue("2100055", "-1.123408", costing.ProfessionalFeesXpath);
			ucqcHelper.VerifyCellValue("2100055", "1111109.123456", costing.OtherExpensesXpath);
			ucqcHelper.VerifyCellValue("2100055", "-2222210.123456", costing.DirectOverheadXpath);
			ucqcHelper.VerifyCellValue("2100055", "3333311.123456", costing.HospitalOverheadXpath);
			ucqcHelper.VerifyCellValue("2100055", "-4444412.123456", costing.CorporateOverheadXpath);
			ucqcHelper.VerifyCellValue("2100055", "0.000001", costing.DepreciationXpath);
			ucqcHelper.VerifyCellValue("2100055", "-0.000002", costing.TechXpath);
			ExtentReport.logPass("PASS", "test04VerifyGridParametersforChargeCode2100055");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04VerifyGridParametersforChargeCode2100055", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test04VerifyGridParametersforChargeCode2100089() throws Throwable {
		try {
			ucqcHelper.VerifyCellValue("2100089", "0.000003", costing.SalariesAndWagesXpath);
			ucqcHelper.VerifyCellValue("2100089", "-0.000004", costing.EmployeeBenifitsXpath);
			ucqcHelper.VerifyCellValue("2100089", "", costing.MedicalSuppliesXpath);
			ucqcHelper.VerifyCellValue("2100089", "-8.123418", costing.NonMedicalSuppliesXpath);
			ucqcHelper.VerifyCellValue("2100089", "188.123419", costing.EquipRepairMaintXpath);
			ucqcHelper.VerifyCellValue("2100089", "7894.123402", costing.DirectDepreciationXpath);
			ucqcHelper.VerifyCellValue("2100089", "", costing.PurchasedServicesXpath);
			ucqcHelper.VerifyCellValue("2100089", "", costing.ProfessionalFeesXpath);
			ucqcHelper.VerifyCellValue("2100089", "", costing.OtherExpensesXpath);
			ucqcHelper.VerifyCellValue("2100089", "", costing.DirectOverheadXpath);
			ucqcHelper.VerifyCellValue("2100089", "", costing.HospitalOverheadXpath);
			ucqcHelper.VerifyCellValue("2100089", "", costing.CorporateOverheadXpath);
			ucqcHelper.VerifyCellValue("2100089", "", costing.DepreciationXpath);
			ucqcHelper.VerifyCellValue("2100089", "", costing.TechXpath);
			ExtentReport.logPass("PASS", "test04VerifyGridParametersforChargeCode2100089");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04VerifyGridParametersforChargeCode2100089", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test05FilterByDepartmentInRvuContainerList() throws Throwable {
		try {
			doClick(costing.getRvuMaintenanceButtonRvuContainerList());
			waitForElementToBeVisible(costing.getRvuContainerFilterButton());
			doClick(costing.getRvuContainerFilterButton());
			doFilterSetFilterParameters("Department Code", "Is", "Equal To", "3110");
			addFilter();
			assertElementIsDisplayedWithXpath("//label[text()='Filter to Match These Criteria 14/68']");
			doClick(dialog.getFilterDialogButtonApplyFilter());
			waitForSpinnerToEnd();

			ExtentReport.logPass("PASS", "test05OpenRvuContainerList");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05OpenRvuContainerList", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test06VerifyResultsInRvuContainerListMatchTheYear2004() throws Throwable {
		try {
			for (WebElement element : costing.getRvuContainerListStartMonth()) {
				if (element.getText().equals("Apr 2004")) {
					assertTrue(printout);
				}
			}
			ContractingMap.getCloseandDisplayButton();
			ExtentReport.logPass("PASS", "test06VerifyResultsInRvuContainerList");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06VerifyResultsInRvuContainerList", driver, e);
			fail(e.getMessage());
		} finally {
			doClosePageOnLowerBar("RVU Maintenance");
		}
	}

	@Test
	public void test07VerifyChargeCodesDisplayedInUcQCGrid() throws Throwable {
		try {
			goToPage("Unit Cost Quick Calculation");
			waitForDisplayedSpinnerToEnd();
			UcqcHelper.ucqcDisplayChargeCodeGrid("QA Cost Model", "ADS-1309 A By Month", "150 Marina Medical Center",
					"3110", "Apr 2004 to Apr 2004");
			assertElementIsDisplayedWithXpath("//*[text()='2100048']");
			assertElementIsDisplayedWithXpath("//*[text()='2100105']");
			assertElementIsNotDisplayed("//*[text()='2100055']");
			assertElementIsNotDisplayed("//*[text()='2100089']");

			ExtentReport.logPass("PASS", "test07VerifyChargeCodesDisplayedInUcQCGrid");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07VerifyChargeCodesDisplayedInUcQCGrid", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test08OverwriteRvuMaintenance() throws Throwable {
		try {
			doClick(costing.getUnitCostQuickCalculationButtonOverwriteRVUMaintenance());
			waitForDisplayedSpinnerToEnd();
			waitForPageTitle("Overwrite RVU Maintenance");
			doDropdownSelectUsingOptionText(
					costing.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalDropdownMonth(),
					costing.unitCostQuickCalculationOverwriteRVUMaintenanceModalDropdownMonthOptions(), "Apr");
			doDropdownSelectUsingOptionText(
					costing.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalDropdownYear(),
					costing.unitCostQuickCalculationOverwriteRVUMaintenanceModalDropdownYearOptions(), "2004");
			doClick(costing.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonSelectAll());
			doClick(costing.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonOverwriteRVUs());
			waitForElementToBeVisible(
					costing.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalWarningButtonOverwrite());
			doClick(costing.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalWarningButtonOverwrite());
			waitForDisplayedSpinnerToEnd();

			ExtentReport.logPass("PASS", "test08OverwriteRvuMaintenance");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test08OverwriteRvuMaintenance", driver, e);
			fail(e.getMessage());
		} finally {
			doClosePageOnLowerBar("Unit Cost Quick...");
		}
	}

	@Test
	public void test09ApplyFiltersInRvuMaintenance() throws Throwable {
		try {
			goToPage("RVU Maintenance");
			waitForDisplayedSpinnerToEnd();
			doClick(costing.getRvuMaintenanceButtonFilter());
			doFilterCreate(filterByCostModel);
			doClick(costing.getRvuMaintenanceButtonMaintainRVUs());
			doDropdownSelectUsingOptionText(costing.getRvuMaintenanceDropdownEntity(),
					costing.getRvuMaintenanceDropdownEntityOptions(), "150 Marina Medical Center");
			UcqcHelper.updateDepartment("3110");
			doDropdownSelectUsingOptionText(costing.getRvuMaintenanceDropdownEffectiveMonthStartMonthDropdown(),
					costing.getRvuMaintenanceDropdownEffectiveMonthStartMonthOptions(), "Apr");
			doDropdownSelectUsingOptionText(costing.getRvuMaintenanceDropdownEffectiveMonthStartYearDropdown(),
					costing.getRvuMaintenanceDropdownEffectiveMonthStartYearOptions(), "2004");
			doClick(costing.getRvuMaintenanceButtonApplySelections());
			waitForDisplayedSpinnerToEnd();

			ExtentReport.logPass("PASS", "test09ApplyFiltersInRvuMaintenance");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test09ApplyFiltersInRvuMaintenance", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test10ConfirmRvuValuesForChargeCode2100055() throws Throwable {
		try {
			test04VerifyGridParametersforChargeCode2100055();
			ExtentReport.logPass("PASS", "test10ConfirmRvuValuesForChargeCode2100055");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test10ConfirmRvuValuesForChargeCode2100055", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test10ConfirmRvuValuesForChargeCode2100089() throws Throwable {
		try {
			test04VerifyGridParametersforChargeCode2100089();
			ExtentReport.logPass("PASS", "test10ConfirmRvuValuesForChargeCode2100055");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test10ConfirmRvuValuesForChargeCode2100055", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test11FilterByDeptInRvuContinerList() throws Throwable {
		try {
			test05FilterByDepartmentInRvuContainerList();
			test06VerifyResultsInRvuContainerListMatchTheYear2004();
			ExtentReport.logPass("PASS", "test11FilterByDeptInRvuContinerList");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test11FilterByDeptInRvuContinerList", driver, e);
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();

	}
}
