package webdriver.scripts.costing;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import java.text.DecimalFormat;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
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
	public static void setupScript() throws Exception {
		generalMap = BuildMap.getInstance(driver, GeneralElementsMap.class);
		costing = BuildMap.getInstance(driver, CostingMap.class);
		System.out.println("Test Class: " + RvuMaintenanceAds1492.class.getSimpleName());

		Login.loginUser("CostingDepartmentManager1");
		Thread.sleep(10000);
		goToPage("Rvu Maintenance");
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
	public void test01FilterCostModelAndVerifyCostModelAndCostModelMasterValues() {
		try {
			waitForAjaxExtJs();
			costing.getRvuMaintenanceButtonFilter().click();
			waitForAjaxExtJs();
			doFilterCreate(filter);
			costing.getRvuMaintenanceButtonMaintainRVUs().click();
			waitForSpinnerToEnd();
			waitForAjaxExtJs();
			assertElementIsDisplayed(driver.findElement(By.xpath("//div[contains(@id, 'displayfield') and contains(text(),'QA Marina')]")), printout);
			assertElementIsDisplayed(driver.findElement(By.xpath("//div[contains(@id, 'displayfield') and contains(text(),'Marina Health System')]")), printout);
		} catch (Throwable e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void test02SetPageParametersAndAssertColumnsToDisplayIsUncheckedAndSelectButtonEnabled() throws Exception {
		doDropdownSelectUsingOptionText(
				costing.getRvuMaintenanceDropdownEntity(),
				costing.getRvuMaintenanceDropdownEntityOptions(),
				"150 Marina Medical Center"
				);
		setDepartmentGroup("2130");
		doDropdownSelectUsingOptionText(
				costing.getRvuMaintenanceDropdownEffectiveMonthStartMonthDropdown(),
				costing.getRvuMaintenanceDropdownEffectiveMonthStartMonthOptions(),
				"Jan"
				);
		doDropdownSelectUsingOptionText(
				costing.getRvuMaintenanceDropdownEffectiveMonthStartYearDropdown(),
				costing.getRvuMaintenanceDropdownEffectiveMonthStartYearOptions(),
				"2005"
				);
		assertThatCheckBoxIsNotChecked("Columns to Display", "All");
		assertElementIsEnabled(
				driver.findElement(By.xpath("//label[text()='Columns to Display']/ancestor::table[contains(@class, 'x-field')]/following-sibling::div/descendant::button/span[text()='Select']")),
				printout
				);
	}

	@Test
	public void test03ColumnsToDisplaySectionCheckAllCheckboxAndAssertSelectButtonIsDisabled() throws Exception {
		driver.findElement(By.xpath("//label[text()='Columns to Display']/../following-sibling::td/label[text()='All']/preceding-sibling::input")).click();
		waitForAjaxExtJs();
		assertThatCheckBoxIsChecked("Columns to Display", "All");
		assertElementIsDisabled(
				driver.findElement(By.xpath("//label[text()='Columns to Display']/ancestor::table[contains(@class, 'x-field')]/following-sibling::div/descendant::button/span[text()='Select']")),
				printout
				);
	}

	@Test
	public void test04aAssertRedBoxAroundCostModelScenarioDropdownsAfterAllButtonIsChecked() {
		String cmsClass = driver.findElement(By.name("costModelScenarioId")).getAttribute("class");
		assertThat(cmsClass, containsString("invalid-field"));
	}

	@Test
	public void test04bAssertRedBoxAroundActivityVolumeScenarioDropdownAfterAllButtonIsChecked() {
		String avsClass = driver.findElement(By.name("activityVolumeScenarioId")).getAttribute("class");
		assertThat(avsClass, containsString("invalid-field"));
	}

	@Test
	public void test04cAssertRedBoxAroundPriceListDropdownAfterAllButtonIsChecked() {
		String plClass = driver.findElement(By.name("priceListId")).getAttribute("class");
		assertThat(plClass, containsString("invalid-field"));
	}

	@Test
	public void test05VerifyBasedOnDropdownMenusDefaultAccordingly() throws InterruptedException {
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
	}

	// other 2 dropdowns are not auto selected as expected. Mail sent to Ruchi on 29/6/2022
	@Test
	public void test06SelectCostModelScenarioAndAssertOtherDropdownsAreSetAccordingly() throws InterruptedException {
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
	}

	@Test
	public void test07ClickApplySelectionsAndAssertDataGridDisplays() throws InterruptedException {
		doClick(driver.findElement(By.xpath("//button/span[text()='Apply Selections']")));
		waitForSpinnerToEnd();
	}

	@Test
	public void test08ClickRvuContainerListButtonAndAssert() {
		doClick(driver.findElement(By.xpath("//button/span[text()='RVU Container List']")));
		waitForSpinnerToEnd();
	}

	@Test
	public void test09DoFilterBuilder() throws InterruptedException {
		String[][] filterStatements = {
				{"Department Code", "Is", "Equal To", "2130"},
				{"Cost Component Name", "Is", "Equal To", "Salaries and Wages"},
		};
		driver.findElement(By.xpath(
				"//*[text()='RVU Container List']/ancestor::div/following-sibling::div/descendant::button/span[text()='Filter']"))
		.click();
		waitForSpinnerToEnd();
		doFilter(filterStatements, costing.getRvuMaintenanceDialogFilterRvuContainerListFieldOptions());
		waitForAjaxExtJs();
		doClickButton("Close & Display");
		waitForSpinnerToEnd();
	}

	@Test
	public void test10aAssertEffectiveStartDateHasBeenUpdated() throws InterruptedException {
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
	}

	@Test
	public void test10bAssertCostModelScenarioFieldsHaveBeenClearedOfValuesAndDisabled() throws InterruptedException {
		assertValueAttributeValue(costing.getRvuMaintenanceDropdownCostModelScenario(), "<None>");
		assertElementContainsDisabledAttribute(costing.getRvuMaintenanceDropdownCostModelScenario());
		assertValueAttributeValue(costing.getRvuMaintenanceDropdownActivityVolumeScenario(), "<None>");
		assertElementContainsDisabledAttribute(costing.getRvuMaintenanceDropdownActivityVolumeScenario());
		assertValueAttributeValue(costing.getRvuMaintenanceDropdownPriceList(), "<None>");
		assertElementContainsDisabledAttribute(costing.getRvuMaintenanceDropdownPriceList());
	}

	@Test
	public void test10cAssertAllCheckboxIsUnchecked() {
		assertThatCheckBoxIsNotChecked("Columns to Display", "All");
	}

	@Test
	public void test15aAssertCopyRvusExportToSpreadsheetImportAndExportButtonsAreEnabled() throws Exception {
		assertElementIsEnabled(costing.getRvuMaintenanceButtonCopyRvus(), printout);
		assertElementIsEnabled(costing.getRvuMaintenanceButtonExportToSpreadsheet(), printout);
		assertElementIsEnabled(costing.getRvuMaintenanceMaintainRvuPageButtonExport(), printout);
		assertElementIsEnabled(costing.getRvuMaintenanceMaintainRvuPageButtonExport(), printout);
		assertElementIsEnabled(costing.getRvuMaintenanceButtonRvuContainerList(), printout);
	}

	@Test
	public void test15bAssertClearRvusAndSaveAndUndoAndSaveRvusButtonsAreDisabled() throws Exception {
		assertElementIsDisabled(costing.getRvuMaintenanceButtonClearRvusAndSave(), printout);
		assertElementIsDisabled(costing.getRvuMaintenanceButtonApplySelections(), printout);
		assertElementIsDisabled(costing.getRvuMaintenanceButtonUndo(), printout);
		assertElementIsDisabled(costing.getRvuMaintenanceButtonSaveRvus(), printout);
	}

	@Test
	public void test16EditDataGridCellAndAssertClearRvusAndSaveAndUndoAndSaveRvusButtonsAreEnabled() throws Exception {
		setDataGridCellValue("1100247", "Salaries and Wages", newCellValue, printout);
		assertElementIsEnabled(costing.getRvuMaintenanceButtonClearRvusAndSave(), printout);
		assertElementIsEnabled(costing.getRvuMaintenanceButtonUndo(), printout);
		assertElementIsEnabled(costing.getRvuMaintenanceButtonSaveRvus(), printout);
		assertElementIsDisabled(costing.getRvuMaintenanceButtonApplySelections(), printout);
	}

	@Test
	public void test18ClickSaveRvusButtonAndAssertValueWasSavedInCell() throws Exception {
		doClick(costing.getRvuMaintenanceButtonSaveRvus());
		waitForSpinnerToEnd();
		waitForAjaxExtJs();
		String gridCellValueNew = getDataGridCellValue("1100247", "Salaries and Wages");
		System.out.println("expected: " + newCellValue);
		System.out.println("actual: " + gridCellValueNew);
		assertThat(gridCellValueNew, containsString(newCellValue));
	}

	@Test
	public void test19AssertClearRvusAndSaveAndUndoAndSaveRvusButtonsAreDisabled() throws Exception {
		assertElementIsDisabled(costing.getRvuMaintenanceButtonClearRvusAndSave(), printout);
		assertElementIsDisabled(costing.getRvuMaintenanceButtonUndo(), printout);
		assertElementIsDisabled(costing.getRvuMaintenanceButtonSaveRvus(), printout);
		assertElementIsDisabled(costing.getRvuMaintenanceButtonApplySelections(), printout);
	}

	private void setDepartmentGroup(String groupId) throws InterruptedException {
		doClick(driver.findElement(By.xpath("//label[text()='Department/Group']/../following-sibling::td/descendant::button/span[text()='Select']")));
		waitForSpinnerToEnd();
		Thread.sleep(500);
		driver.findElement(By.xpath("//label[text()='Department / Group']/../following-sibling::input[@name='carrierfield']")).sendKeys(groupId);
		Thread.sleep(500);
		waitForSpinnerToEnd();
		doClickButton("Apply");
		waitForAjaxExtJs();
	}

}
