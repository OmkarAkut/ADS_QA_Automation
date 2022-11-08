package webdriver.scripts.costing;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import java.text.DecimalFormat;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;

import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.GeneralElementsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RvuMaintenanceAds1492 extends GoHelper {

	String costModel = "QA Marina";
	String[] filter = {"Cost Model Name", "Is", "Equal To", costModel};
	static GeneralElementsMap generalMap;
	static CostingMap costing;
	static String newCellValue = formatStringWithCommaGrouping(
			Integer.toString(javaGetRandomNumber(99,true))
			);

	/** Automates test ticket ADS-1492. */
	
	@BeforeClass
	public static void setupScript() throws Exception,Throwable {
		ExtentReport.reportCreate("RvuMaintenanceAds1492", "webdriver.scripts.costing", "RvuMaintenanceAds1492");
		try {
			generalMap = BuildMap.getInstance(driver, GeneralElementsMap.class);
			costing = BuildMap.getInstance(driver, CostingMap.class);
			System.out.println("Test Class: " + RvuMaintenanceAds1492.class.getSimpleName());

			Login.loginUser("CostingDepartmentManager1");
			Thread.sleep(10000);
			goToPage("Rvu Maintenance");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	private static String formatStringWithCommaGrouping(String value) {
		Double dval = Double.parseDouble(value);
		DecimalFormat df = new DecimalFormat();
		df.setGroupingUsed(true);
		df.setGroupingSize(3);
		value = df.format(dval);
		return value;
	}
	
	@Test
	public void test01FilterCostModelAndVerifyCostModelAndCostModelMasterValues() throws Throwable {
		try {
			waitForAjaxExtJs();
			costing.getRvuMaintenanceButtonFilter().click();
			Thread.sleep(2000);
			waitForAjaxExtJs();
			doFilterCreate(filter);
			costing.getRvuMaintenanceButtonMaintainRVUs().click();
			waitForSpinnerToEnd();
			waitForAjaxExtJs();
			assertElementIsDisplayed(driver.findElement(By.xpath("//div[contains(@id, 'displayfield') and contains(text(),'QA Marina')]")), printout);
			assertElementIsDisplayed(driver.findElement(By.xpath("//div[contains(@id, 'displayfield') and contains(text(),'Marina Health System')]")), printout);
			ExtentReport.logPass("PASS", "test01FilterCostModelAndVerifyCostModelAndCostModelMasterValues");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test01FilterCostModelAndVerifyCostModelAndCostModelMasterValues", driver, e);

			fail(e.getMessage());
		}
	}
	
	@Test
	public void test02SetPageParametersAndAssertColumnsToDisplayIsUncheckedAndSelectButtonEnabled() throws Throwable {
		try {
			doDropdownSelectUsingOptionText(
					costing.getRvuMaintenanceDropdownEntity(),
					costing.getRvuMaintenanceDropdownEntityOptions(),
					"150 Marina Medical Center"
					);
			setDepartmentGroup("2110");// 2110 ICU is not searched in Filter , has issue in Department Group search by 2110 ICU
			doDropdownSelectUsingOptionText(
					costing.getRvuMaintenanceDropdownEffectiveMonthStartMonthDropdown(),
					costing.getRvuMaintenanceDropdownEffectiveMonthStartMonthOptions(),
					"Apr"
					);
			doDropdownSelectUsingOptionText(
					costing.getRvuMaintenanceDropdownEffectiveMonthStartYearDropdown(),
					costing.getRvuMaintenanceDropdownEffectiveMonthStartYearOptions(),
					"2003"
					);
			assertThatCheckBoxIsNotChecked("Columns to Display", "All");
			assertElementIsEnabled(
					driver.findElement(By.xpath("//label[text()='Columns to Display']/ancestor::table[contains(@class, 'x-field')]/following-sibling::div/descendant::button/span[text()='Select']")),
					printout
					);
			ExtentReport.logPass("PASS", "test02SetPageParametersAndAssertColumnsToDisplayIsUncheckedAndSelectButtonEnabled");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test02SetPageParametersAndAssertColumnsToDisplayIsUncheckedAndSelectButtonEnabled", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test03ColumnsToDisplaySectionCheckAllCheckboxAndAssertSelectButtonIsDisabled() throws Throwable {
		try {
			driver.findElement(By.xpath("//label[text()='Columns to Display']/../following-sibling::td/label[text()='All']/preceding-sibling::input")).click();
			waitForAjaxExtJs();
			assertThatCheckBoxIsChecked("Columns to Display", "All");
			assertElementIsDisabled(
					driver.findElement(By.xpath("//label[text()='Columns to Display']/ancestor::table[contains(@class, 'x-field')]/following-sibling::div/descendant::button/span[text()='Select']")),
					printout
					);
			ExtentReport.logPass("PASS", "test03ColumnsToDisplaySectionCheckAllCheckboxAndAssertSelectButtonIsDisabled");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ColumnsToDisplaySectionCheckAllCheckboxAndAssertSelectButtonIsDisabled", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test04aAssertRedBoxAroundCostModelScenarioDropdownsAfterAllButtonIsChecked() throws Throwable {
		try {
			String cmsClass = driver.findElement(By.name("costModelScenarioId")).getAttribute("class");
			assertThat(cmsClass, containsString("invalid-field"));
			ExtentReport.logPass("PASS", "test04aAssertRedBoxAroundCostModelScenarioDropdownsAfterAllButtonIsChecked");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test04aAssertRedBoxAroundCostModelScenarioDropdownsAfterAllButtonIsChecked", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test04bAssertRedBoxAroundActivityVolumeScenarioDropdownAfterAllButtonIsChecked() throws Throwable {
		try {
			String avsClass = driver.findElement(By.name("activityVolumeScenarioId")).getAttribute("class");
			assertThat(avsClass, containsString("invalid-field"));
			ExtentReport.logPass("PASS", "test04bAssertRedBoxAroundActivityVolumeScenarioDropdownAfterAllButtonIsChecked");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test04bAssertRedBoxAroundActivityVolumeScenarioDropdownAfterAllButtonIsChecked", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test04cAssertRedBoxAroundPriceListDropdownAfterAllButtonIsChecked() throws Throwable {
		try {
			String plClass = driver.findElement(By.name("priceListId")).getAttribute("class");
			assertThat(plClass, containsString("invalid-field"));
			ExtentReport.logPass("PASS", "test04cAssertRedBoxAroundPriceListDropdownAfterAllButtonIsChecked");

		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test04cAssertRedBoxAroundPriceListDropdownAfterAllButtonIsChecked", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test05VerifyBasedOnDropdownMenusDefaultAccordingly() throws Throwable {
		try {
			setDropdownValue(
					costing.getRvuMaintenanceDropdownBasedOn(),
					costing.getRvuMaintenanceDropdownBasedOnOptions(),
					"Stored Month or Closest Prior",
					printout
					);
			setDropdownValue(
					costing.getRvuMaintenanceDropdownBasedOnVolumes(),
					costing.getRvuMaintenanceDropdownBasedOnVolumesOptions(),
					"With and Without Volumes",
					printout
					);
			setDropdownValue(
					costing.getRvuMaintenanceDropdownBasedOnPrices(),
					costing.getRvuMaintenanceDropdownBasedOnPricesOptions(),
					"With and Without Prices",
					printout
					);
			ExtentReport.logPass("PASS", "test05VerifyBasedOnDropdownMenusDefaultAccordingly");

		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test05VerifyBasedOnDropdownMenusDefaultAccordingly", driver, e);
			fail(e.getMessage());
		}
	}

	// other 2 dropdowns are not auto selected as expected. Mail sent to Ruchi on 29/6/2022
	@Test
	public void test06SelectCostModelScenarioAndAssertOtherDropdownsAreSetAccordingly() throws Throwable {
		try {
			doDropdownSelectUsingOptionText(
					costing.getRvuMaintenanceDropdownCostModelScenario(),
					costing.getRvuMaintenanceDropdownCostModelScenarioOptions(),
					"ADS-302 LG By Month"
					);
			waitForSpinnerToEnd();
			assertThatDropdownSelectedValue(
					costing.getRvuMaintenanceDropdownActivityVolumeScenario(),
					costing.getRvuMaintenanceDropdownActivityVolumeScenarioOptions(),
					"QAMHFY05 QA ADS-302 MHFY05"
					);
			assertThatDropdownSelectedValue(
					costing.getRvuMaintenanceDropdownPriceList(),
					costing.getRvuMaintenanceDropdownPriceListOptions(),
					"QA150FY05 QA Marina Hosp Price List FY05"
					);
			ExtentReport.logPass("PASS", "test06SelectCostModelScenarioAndAssertOtherDropdownsAreSetAccordingly");

		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test06SelectCostModelScenarioAndAssertOtherDropdownsAreSetAccordingly", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test07ClickApplySelectionsAndAssertDataGridDisplays() throws Throwable {
		try {
			doClick(driver.findElement(By.xpath("//button/span[text()='Apply Selections']")));
			waitForSpinnerToEnd();
			ExtentReport.logPass("PASS", "test07ClickApplySelectionsAndAssertDataGridDisplays");

		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test07ClickApplySelectionsAndAssertDataGridDisplays", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test08ClickRvuContainerListButtonAndAssert() throws Throwable {
		try {
			Thread.sleep(3000);
			doClick(driver.findElement(By.xpath("//button/span[text()='RVU Container List']")));
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "test08ClickRvuContainerListButtonAndAssert");

		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test08ClickRvuContainerListButtonAndAssert", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test09DoFilterBuilder() throws Throwable {
		String[][] filterStatements = {
				{"Department Code", "Is", "Equal To", "2130"},
				{"Cost Component Name", "Is", "Equal To", "Salaries and Wages"},
		};
		try {
			waitUntilElementIsClickable(driver.findElement(By.xpath(
					"//*[text()='RVU Container List']/ancestor::div/following-sibling::div/descendant::button/span[text()='Filter']")));
			driverDelay(2000);
			driver.findElement(By.xpath(
					"//*[text()='RVU Container List']/ancestor::div/following-sibling::div/descendant::button/span[text()='Filter']"))
			.click();
			
			waitForDisplayedSpinnerToEnd();
			doFilter(filterStatements, costing.getRvuMaintenanceDialogFilterRvuContainerListFieldOptions());
			waitForAjaxExtJs();
			doClickButton("Close & Display");
			
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "test09DoFilterBuilder");

		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test09DoFilterBuilder", driver, e);
			fail(e.getMessage());
		}
		
	}

	@Test
	public void test10aAssertEffectiveStartDateHasBeenUpdated() throws Throwable {
		try {
			assertThatDropdownSelectedValue(
					costing.getRvuMaintenanceDropdownEffectiveMonthStartMonthDropdown(),
					costing.getRvuMaintenanceDropdownEffectiveMonthStartMonthOptions(),
					"Apr"
					);
			assertThatDropdownSelectedValue(
					costing.getRvuMaintenanceDropdownEffectiveMonthStartYearDropdown(),
					costing.getRvuMaintenanceDropdownEffectiveMonthStartYearOptions(),
					"2004"
					);
			doClick(driver.findElement(By.xpath("//button/span[text()='Apply Selections']")));
			waitForSpinnerToEnd();
			ExtentReport.logPass("PASS", "test10aAssertEffectiveStartDateHasBeenUpdated");

		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test10aAssertEffectiveStartDateHasBeenUpdated", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test10bAssertCostModelScenarioFieldsHaveBeenClearedOfValuesAndDisabled() throws Throwable {
		try {
			assertValueAttributeValue(costing.getRvuMaintenanceDropdownCostModelScenario(), "<None>");
			assertElementContainsDisabledAttribute(costing.getRvuMaintenanceDropdownCostModelScenario());
			assertValueAttributeValue(costing.getRvuMaintenanceDropdownActivityVolumeScenario(), "<None>");
			assertElementContainsDisabledAttribute(costing.getRvuMaintenanceDropdownActivityVolumeScenario());
			assertValueAttributeValue(costing.getRvuMaintenanceDropdownPriceList(), "<None>");
			assertElementContainsDisabledAttribute(costing.getRvuMaintenanceDropdownPriceList());
			ExtentReport.logPass("PASS", "test10bAssertCostModelScenarioFieldsHaveBeenClearedOfValuesAndDisabled");

		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test10bAssertCostModelScenarioFieldsHaveBeenClearedOfValuesAndDisabled", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test10cAssertAllCheckboxIsUnchecked() throws Throwable {
		try {
			assertThatCheckBoxIsNotChecked("Columns to Display", "All");
			ExtentReport.logPass("PASS", "test10cAssertAllCheckboxIsUnchecked");

		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test10cAssertAllCheckboxIsUnchecked", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test15aAssertCopyRvusExportToSpreadsheetImportAndExportButtonsAreEnabled() throws Throwable {
		try {
			assertElementIsEnabled(costing.getRvuMaintenanceButtonCopyRvus(), printout);
			assertElementIsEnabled(costing.getRvuMaintenanceButtonExportToSpreadsheet(), printout);
			assertElementIsEnabled(costing.getRvuMaintenanceMaintainRvuPageButtonExport(), printout);
			assertElementIsEnabled(costing.getRvuMaintenanceMaintainRvuPageButtonExport(), printout);
			assertElementIsEnabled(costing.getRvuMaintenanceButtonRvuContainerList(), printout);
			ExtentReport.logPass("PASS", "test15aAssertCopyRvusExportToSpreadsheetImportAndExportButtonsAreEnabled");

		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test15aAssertCopyRvusExportToSpreadsheetImportAndExportButtonsAreEnabled", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test15bAssertClearRvusAndSaveAndUndoAndSaveRvusButtonsAreDisabled() throws Throwable {
		try {
			assertElementIsDisabled(costing.getRvuMaintenanceButtonClearRvusAndSave(), printout);
			assertElementIsDisabled(costing.getRvuMaintenanceButtonApplySelections(), printout);
			assertElementIsDisabled(costing.getRvuMaintenanceButtonUndo(), printout);
			assertElementIsDisabled(costing.getRvuMaintenanceButtonSaveRvus(), printout);
			ExtentReport.logPass("PASS", "test15bAssertClearRvusAndSaveAndUndoAndSaveRvusButtonsAreDisabled");

		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test15bAssertClearRvusAndSaveAndUndoAndSaveRvusButtonsAreDisabled", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test16EditDataGridCellAndAssertClearRvusAndSaveAndUndoAndSaveRvusButtonsAreEnabled() throws Throwable {
		try {
			setDataGridCellValue("1100247", "Salaries and Wages", newCellValue, printout);
			assertElementIsEnabled(costing.getRvuMaintenanceButtonClearRvusAndSave(), printout);
			assertElementIsEnabled(costing.getRvuMaintenanceButtonUndo(), printout);
			assertElementIsEnabled(costing.getRvuMaintenanceButtonSaveRvus(), printout);
			assertElementIsDisabled(costing.getRvuMaintenanceButtonApplySelections(), printout);
			ExtentReport.logPass("PASS", "test16EditDataGridCellAndAssertClearRvusAndSaveAndUndoAndSaveRvusButtonsAreEnabled");

		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test16EditDataGridCellAndAssertClearRvusAndSaveAndUndoAndSaveRvusButtonsAreEnabled", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test18ClickSaveRvusButtonAndAssertValueWasSavedInCell() throws Throwable {
		try {
			doClick(costing.getRvuMaintenanceButtonSaveRvus());
			waitForDisplayedSpinnerToEnd();
			waitForAjaxExtJs();
			String gridCellValueNew = getDataGridCellValue("1100247", "Salaries and Wages");
			System.out.println("expected: " + newCellValue);
			System.out.println("actual: " + gridCellValueNew);
			assertThat(gridCellValueNew, containsString(newCellValue));
			ExtentReport.logPass("PASS", "test18ClickSaveRvusButtonAndAssertValueWasSavedInCell");

		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test18ClickSaveRvusButtonAndAssertValueWasSavedInCell", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test19AssertClearRvusAndSaveAndUndoAndSaveRvusButtonsAreDisabled() throws Throwable {
		try {
			assertElementIsDisabled(costing.getRvuMaintenanceButtonClearRvusAndSave(), printout);
			assertElementIsDisabled(costing.getRvuMaintenanceButtonUndo(), printout);
			assertElementIsDisabled(costing.getRvuMaintenanceButtonSaveRvus(), printout);
			assertElementIsDisabled(costing.getRvuMaintenanceButtonApplySelections(), printout);
			ExtentReport.logPass("PASS", "test19AssertClearRvusAndSaveAndUndoAndSaveRvusButtonsAreDisabled");

		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test19AssertClearRvusAndSaveAndUndoAndSaveRvusButtonsAreDisabled", driver, e);
			fail(e.getMessage());
		}
	}

	private void setDepartmentGroup(String groupId) throws InterruptedException {
		UcqcHelper.updateDepartment(groupId);
		//Shilpa 13.09.2022 commented below lines search by department group not working in UI
		/*
		doClick(driver.findElement(By.xpath("//label[text()='Department/Group']/../following-sibling::td/descendant::button/span[text()='Select']")));
		waitForSpinnerToEnd();
		Thread.sleep(600);
		driver.findElement(By.xpath("//label[text()='Department / Group']/../following-sibling::input[@name='carrierfield']")).sendKeys(groupId);
		Thread.sleep(500);
		waitForSpinnerToEnd();
		driver.findElement(By.xpath("//div[contains(@class,'x-grid-cell-inner') and starts-with(text()," + groupId +")]")).click();
		doClickButton("Apply");
		waitForAjaxExtJs();
		*/
	}
	 @AfterClass
		public static void endtest() throws Exception {

			ExtentReport.report.flush();

		}
}
