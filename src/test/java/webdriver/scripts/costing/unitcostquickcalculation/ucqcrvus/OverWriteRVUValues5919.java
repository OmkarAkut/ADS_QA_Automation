package webdriver.scripts.costing.unitcostquickcalculation.ucqcrvus;

import static org.junit.Assert.fail;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
	UcqcHelper ucqcHelper=new UcqcHelper();
	int value = JavaHelper.javaGetRandomNumber(12, printout);

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("OverwriteRvuMaintenancePopupAds1181",
				"webdriver.scripts.costing.unitcostquickcalculation.ucqcrvus", "OverwriteRvuMaintenancePopupAds1181");
		try {
			costing = BuildMap.getInstance(driver, CostingMap.class);
			contracting = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("CostingDepartmentManager1");
			
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-5919
	@Test
	public void test07OpenRvuCostModelAndImport_5919() throws Throwable {
		try {
			goToPage("RVU Maintenance");
			doClick(costing.getRvuMaintenanceButtonFilter());
			doFilterCreate(filter);
			doClick(costing.getRvuMaintenanceButtonImport());
//			doClick(costing.getRvuSecImportSelectButton());
			doactionClick(costing.getRvuSecImportSelectButton());
//			costing.getRvuSecImportSelectButton().sendKeys(Keys.ENTER);
//			;
//			driverDelay(500);
			ContractModelsHelper.uploadTheFileusingAutoIT(driver,
					System.getProperty("user.dir") + "\\AutoIT\\UploadFile.exe",
//					System.getProperty("user.dir") + "\\AutoIT\\ADS1238PreConditionRVUs.txt");
					System.getProperty("user.dir") + "\\AutoIT\\ADS1309PreConditionsRVUImport.txt");
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
			doClosePageOnLowerBar("RVU Maintenance"); //uncomment this once ADS-17819 is fixed

		}
	}

	@Test
	public void test02ApplyRvuSelections_5919() throws Throwable {
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

		@Test
	public void test03ApplyRvuSelections_5919() throws Throwable {
		try {
			ucqcUpdateGridCellValue("1804582", "Quick Salaries and Wages RVU", String.valueOf(value), printout);
			ucqcHelper.getCellValue("1804582", "Quick Salaries and Wages RVU", "0.000000");
			ucqcHelper.getCellValue("1805373", "Quick Salaries and Wages RVU", "0.000000");
			ucqcHelper.getCellValue("1805381", "Quick Salaries and Wages RVU", "0.000000");
			ucqcHelper.getCellValue("1811355", "Quick Salaries and Wages RVU", "0.000000");
			ucqcHelper.getCellValue("1811751", "Quick Salaries and Wages RVU", "9999918.12409");
			ucqcHelper.getCellValue("1850205", "Quick Salaries and Wages RVU", "8888819.123456");
			ucqcHelper.getCellValue("1856251", "Quick Salaries and Wages RVU", "-7777710.123456");
			ucqcHelper.getCellValue("1890011", "Quick Salaries and Wages RVU", "-5555505.123456");
			ucqcHelper.getCellValue("1890029", "Quick Salaries and Wages RVU", ".000003");
			ucqcHelper.getCellValue("1890037", "Quick Salaries and Wages RVU", ".000004");
			ucqcHelper.getCellValue("1890045", "Quick Salaries and Wages RVU", "-.000005");
			ucqcHelper.getCellValue("1890052", "Quick Salaries and Wages RVU", "-.000006");
			ucqcHelper.getCellValue("1890060", "Quick Salaries and Wages RVU", "9654.123417");
			ucqcHelper.getCellValue("1890086", "Quick Salaries and Wages RVU", "56.123418");
			ucqcHelper.getCellValue("1890094", "Quick Salaries and Wages RVU", "-5681.123419");
			ucqcHelper.getCellValue("1890102", "Quick Salaries and Wages RVU", ".009902");
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
		finally {
			doClosePageOnLowerBar("Unit Cost Quick Calculation");
		}
	}

	@Test
	public void test04NavigateToRvuMaintenance_5919() throws Throwable {
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
			updateDepartment("3120");
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
	public void test05ApplyRvuSelections_5919() throws Throwable {
		try {
			doClick(costing.getRvuApplySelections());
			waitForDisplayedSpinnerToEnd();
			assertElementIsDisplayedWithXpath("//*[text()='Salaries and Wages'][text()='Apr 2004']");
			ucqcHelper.VerifyCellValue("1804582", "0.000000",costing.SalariesAndWagesXpath);
			ucqcHelper.VerifyCellValue("1805373", "0.000000",costing.SalariesAndWagesXpath);
			ucqcHelper.VerifyCellValue("1805381", "0.000000",costing.SalariesAndWagesXpath);
			ucqcHelper.VerifyCellValue("1811355", "0.000000",costing.SalariesAndWagesXpath);
			ucqcHelper.VerifyCellValue("1811751", "9999918.12409",costing.SalariesAndWagesXpath);
			ucqcHelper.VerifyCellValue("1850205", "8888819.123456",costing.SalariesAndWagesXpath);
			ucqcHelper.VerifyCellValue("1856251", "-7777710.123456",costing.SalariesAndWagesXpath);
			doClick(costing.getRvuNextPageButton());
			waitForDisplayedSpinnerToEnd();
			ucqcHelper.VerifyCellValue("1890011", "-5555505.123456",costing.SalariesAndWagesXpath);
			ucqcHelper.VerifyCellValue("1890029", ".000003",costing.SalariesAndWagesXpath);
			ucqcHelper.VerifyCellValue("1890037", ".000004",costing.SalariesAndWagesXpath);
			ucqcHelper.VerifyCellValue("1890045", "-.000005",costing.SalariesAndWagesXpath);
			ucqcHelper.VerifyCellValue("1890052", "-.000006",costing.SalariesAndWagesXpath);
			ucqcHelper.VerifyCellValue("1890060", "9654.123417",costing.SalariesAndWagesXpath);
			ucqcHelper.VerifyCellValue("1890086", "56.123418",costing.SalariesAndWagesXpath);
			ucqcHelper.VerifyCellValue("1890094", "-5681.123419",costing.SalariesAndWagesXpath);
			ucqcHelper.VerifyCellValue("1890102", ".009902",costing.SalariesAndWagesXpath);
			ExtentReport.logPass("PASS", "test04NavigateToRvuMaintenance");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04NavigateToRvuMaintenance", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test06FilterByDepartmentCodeInRvuContainer_5919() throws Throwable {
		try {
			doClick(costing.getRvuMaintenanceButtonRvuContainerList());
			waitForDisplayedSpinnerToEnd();
			doClick(costing.getRvuContainerFilterButton());
			doFilterCreate(filterByDeptCode);
			assertElementIsDisplayedWithXpath("//div[contains(@id,'rvucontainerlist')]//following::div[text()='Salaries and Wages']//following::div[text()='Apr 2004']");
			doClick(costing.getRvuContainerCloseDisplayButton());
			ExtentReport.logPass("PASS", "test06FilterByDepartmentCodeInRvuContainer");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06FilterByDepartmentCodeInRvuContainer", driver, e);
			fail(e.getMessage());
		}
		finally {
			doClosePageOnLowerBar("RVU Maintenance");
		}
	}
	public static void updateDepartment(String departmentText) throws InterruptedException {
	     doClick("((//label[contains(@id,'singleselectorform')])[1]//following::span[text()='Select'])[1]");
	   waitForDisplayedSpinnerToEnd();
	    waitForAjaxExtJs();
	    //Thread.sleep(1100);  //original value, which works
	    Thread.sleep(500);  //alternative value, to reduce run time - reset to original value if there are false positives with this one
	    JavascriptExecutor jse = (JavascriptExecutor)driver;
		   jse.executeScript("arguments[0].value='"+ departmentText +"';",  driver.findElement(By.name("carrierfield")));
			waitForSpinnerToEnd();
			try {
				driver.findElement(By.name("carrierfield")).sendKeys(" ");
				Thread.sleep(1000);
				driver.findElement(By.name("carrierfield")).sendKeys(Keys.BACK_SPACE);
				Thread.sleep(1000);

			doClick(driver.findElement(By.xpath("//div[contains(@class,'x-grid-cell-inner') and contains(text()," + departmentText +")]")));
			waitForAjaxExtJs();
			doClick(driver.findElement(By.xpath("//*[contains(@class,'docked-bottom')]/descendant::span[text()='Apply']")));
			waitForAjaxExtJs();
		} catch (Exception|AssertionError e) {
			doClick(driver.findElement(By.xpath("//div[contains(@class,'x-grid-cell-inner') and contains(text()," + departmentText +")]")));

			doClick(driver.findElement(By.xpath("//*[contains(@class,'docked-bottom')]/descendant::span[text()='Apply']")));
			waitForAjaxExtJs();
		}
	  }
	@AfterClass
	public static void endtest() throws Exception {
//		doClosePageOnLowerBar("RVU Maintenance");
		
		ExtentReport.report.flush();

	}
}
