package webdriver.scripts.costing;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.text.DecimalFormat;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
	private static String borderValidationColor="2px solid rgb(255, 0, 0)";//Validation color for dropdown
	/** Automates test ticket ADS-1492. ADS-6646,ADS-5993, support case -ADS-12594*/
	
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
	//ADS-5993 all steps
	@Test
	public void test01FilterCostModelAndVerifyCostModelAndCostModelMasterValues_5993() throws Throwable {
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
	//ADS-6646
	@Test
	public void test02SetPageParametersAndAssertColumnsToDisplayIsUncheckedAndSelectButtonEnabled_6646_5993() throws Throwable {
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
//			assertElementIsEnabled(
//					driver.findElement(By.xpath("//label[text()='Columns to Display']/ancestor::table[contains(@class, 'x-field')]/following-sibling::div/descendant::button/span[text()='Select']")),
//					printout
//					);
			//Shilpa update xpath for 11.2 on 12.06.2023
			assertElementIsEnabled(
					driver.findElement(By.xpath("//span[text()='Columns to Display']//following::span[text()='Select']")),
					printout
					);
			ExtentReport.logPass("PASS", "test02SetPageParametersAndAssertColumnsToDisplayIsUncheckedAndSelectButtonEnabled");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test02SetPageParametersAndAssertColumnsToDisplayIsUncheckedAndSelectButtonEnabled", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test03ColumnsToDisplaySectionCheckAllCheckboxAndAssertSelectButtonIsDisabled_5993() throws Throwable {
		try {
//			driver.findElement(By.xpath("//label[text()='Columns to Display']/../following-sibling::td/label[text()='All']/preceding-sibling::input")).click();
			//Shilpa update xpath for 11.2 on 12.06.2023
			driver.findElement(By.xpath("//span[text()='Columns to Display']//following::input[@type='checkbox']")).click();
			waitForAjaxExtJs();
			assertThatCheckBoxIsChecked("Columns to Display", "All");
//			assertElementIsDisabled(
//					driver.findElement(By.xpath("//label[text()='Columns to Display']/ancestor::table[contains(@class, 'x-field')]/following-sibling::div/descendant::button/span[text()='Select']")),
//					printout
//					);
			//Shilpa update xpath for 11.2 on 12.06.2023
			
			assertTheElementIsDisabled(
					driver.findElement(By.xpath("//span[text()='Columns to Display']//following::span[text()='Select']/../../..")),
					printout
					);
			ExtentReport.logPass("PASS", "test03ColumnsToDisplaySectionCheckAllCheckboxAndAssertSelectButtonIsDisabled");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test03ColumnsToDisplaySectionCheckAllCheckboxAndAssertSelectButtonIsDisabled", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test04aAssertRedBoxAroundCostModelScenarioDropdownsAfterAllButtonIsChecked_5993() throws Throwable {
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
	public void test04bAssertRedBoxAroundActivityVolumeScenarioDropdownAfterAllButtonIsChecked_5993() throws Throwable {
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
	public void test04cAssertRedBoxAroundPriceListDropdownAfterAllButtonIsChecked_5993() throws Throwable {
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
	public void test05VerifyBasedOnDropdownMenusDefaultAccordingly_5993_12594() throws Throwable {
		try {
			
			if(((costing.getRvuMaintenanceCostScenarioDropdownBorder().getCssValue("border").contains(borderValidationColor)) && (costing.getRvuMaintenanceActDropdownBorder().getCssValue("border").contains(borderValidationColor)) && costing.getRvuMaintenancePriceDropdownBorder().getCssValue("border").contains(borderValidationColor))) {
				assertTrue(printout);
			}
			else {
				fail();
			}
			//Shilpa: commented line on 01.20.2026
//			driver.findElement(By.xpath("//span[text()='Columns to Display']//following::input[@type='checkbox']")).click();
			setDropdownValue(
					costing.getRvuMaintenanceDropdownBasedOnVolumes(),
					costing.getRvuMaintenanceDropdownBasedOnVolumesOptions(),
					"Volumes Exist",
					printout
					);
			if((costing.getRvuMaintenanceActDropdownBorder().getCssValue("border").contains(borderValidationColor))) {
				assertTrue(printout);
			}
			else {
				fail();
			}
			setDropdownValue(
					costing.getRvuMaintenanceDropdownBasedOnPrices(),
					costing.getRvuMaintenanceDropdownBasedOnPricesOptions(),
					"Prices Exist",
					printout
					);
			if(((costing.getRvuMaintenanceActDropdownBorder().getCssValue("border").contains(borderValidationColor)) && costing.getRvuMaintenancePriceDropdownBorder().getCssValue("border").contains(borderValidationColor))) {
				assertTrue(printout);
			}
			else {
				fail();
			}
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
	public void test06SelectCostModelScenarioAndAssertOtherDropdownsAreSetAccordingly_5993() throws Throwable {
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
	public void test07ClickApplySelectionsAndAssertDataGridDisplays_5993() throws Throwable {
		try {
//			doClick(driver.findElement(By.xpath("//button/span[text()='Apply Selections']")));
			//shilpa update xpath for 11.2 on 12.07.2023
			doClick(driver.findElement(By.xpath("//span[text()='Apply Selections']")));
			waitForSpinnerToEnd();
			ExtentReport.logPass("PASS", "test07ClickApplySelectionsAndAssertDataGridDisplays");

		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test07ClickApplySelectionsAndAssertDataGridDisplays", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test08ClickRvuContainerListButtonAndAssert_5993() throws Throwable {
		try {
			Thread.sleep(3000);
//			doClick(driver.findElement(By.xpath("//button/span[text()='RVU Container List']")));
			//shilpa update xpath for 11.2 on 12.07.2023
			doClick(driver.findElement(By.xpath("//span[text()='RVU Container List']")));
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "test08ClickRvuContainerListButtonAndAssert");

		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test08ClickRvuContainerListButtonAndAssert", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test09DoFilterBuilder_5993() throws Throwable {
		String[][] filterStatements = {
				{"Department Code", "Is", "Equal To", "2130"},
				{"Cost Component Name", "Is", "Equal To", "Salaries and Wages"},
		};
		try {
//			waitUntilElementIsClickable(driver.findElement(By.xpath(
//					"//*[text()='RVU Container List']/ancestor::div/following-sibling::div/descendant::button/span[text()='Filter']")));
			//Shilpa update xpath for 11.2
			waitUntilElementIsClickable(driver.findElement(By.xpath(
					"//div[contains(@id,'dynamicwindow')][text()='RVU Container List']//following::span[text()='Filter']")));
			driverDelay(2000);
//			driver.findElement(By.xpath(
//					"//*[text()='RVU Container List']/ancestor::div/following-sibling::div/descendant::button/span[text()='Filter']"))
//			.click();
			driver.findElement(By.xpath(
					"//div[contains(@id,'dynamicwindow')][text()='RVU Container List']//following::span[text()='Filter']"))
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
	public void test10aAssertEffectiveStartDateHasBeenUpdated_5993() throws Throwable {
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
//			doClick(driver.findElement(By.xpath("//button/span[text()='Apply Selections']")));
			//Shilpa update xpath for 11.2
			doClick(driver.findElement(By.xpath("//span[text()='Apply Selections']/../..")));
			waitForSpinnerToEnd();
			ExtentReport.logPass("PASS", "test10aAssertEffectiveStartDateHasBeenUpdated");

		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test10aAssertEffectiveStartDateHasBeenUpdated", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test10bAssertCostModelScenarioFieldsHaveBeenClearedOfValuesAndDisabled_5993() throws Throwable {
		try {
			assertValueAttributeValue(costing.getRvuMaintenanceDropdownCostModelScenarioNone(), "<None>");
//			assertElementContainsDisabledAttribute(costing.getRvuMaintenanceDropdownCostModelScenario());//Only this class dont have disabled attribute in class so generic method cannot be used
			assertdropdownDisabled(costing.getRvuMaintenanceDropdownCostModelScenario());
			assertValueAttributeValue(costing.getRvuMaintenanceDropdownActivityVolumeScenarioNone(), "<None>");
			assertdropdownDisabled(costing.getRvuMaintenanceDropdownActivityVolumeScenario());
//			assertValueAttributeValue(costing.getRvuMaintenanceDropdownPriceListNone(), "<None>"); this element is not showing in DOM Issue https://harrisaffinity.atlassian.net/browse/ADS-11871
			assertdropdownDisabled(costing.getRvuMaintenanceDropdownPriceList());
			ExtentReport.logPass("PASS", "test10bAssertCostModelScenarioFieldsHaveBeenClearedOfValuesAndDisabled");

		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test10bAssertCostModelScenarioFieldsHaveBeenClearedOfValuesAndDisabled", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test10cAssertAllCheckboxIsUnchecked_5993() throws Throwable {
		try {
			assertThatCheckBoxIsNotChecked("Columns to Display", "All");
			ExtentReport.logPass("PASS", "test10cAssertAllCheckboxIsUnchecked");

		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test10cAssertAllCheckboxIsUnchecked", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test15aAssertCopyRvusExportToSpreadsheetImportAndExportButtonsAreEnabled_5993() throws Throwable {
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
	public void test15bAssertClearRvusAndSaveAndUndoAndSaveRvusButtonsAreDisabled_5993() throws Throwable {
		try {
//			assertElementIsDisabled(costing.getRvuMaintenanceButtonClearRvusAndSave(), printout);
			//shilpa 12.12.2022
			assertElementIsEnabled(costing.getRvuMaintenanceButtonClearRvusAndSave(), printout);
			assertTheElementIsDisabled(costing.getRvuMaintenanceButtonApplySelections(), printout);
			assertTheElementIsDisabled(costing.getRvuMaintenanceButtonUndo(), printout);
			assertTheElementIsDisabled(costing.getRvuMaintenanceButtonSaveRvus(), printout);
			ExtentReport.logPass("PASS", "test15bAssertClearRvusAndSaveAndUndoAndSaveRvusButtonsAreDisabled");

		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test15bAssertClearRvusAndSaveAndUndoAndSaveRvusButtonsAreDisabled", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test16EditDataGridCellAndAssertClearRvusAndSaveAndUndoAndSaveRvusButtonsAreEnabled_5993() throws Throwable {
		try {
			setDataGridCellValue("1100247", "Salaries and Wages", newCellValue, printout);
			assertElementIsEnabled(costing.getRvuMaintenanceButtonClearRvusAndSave(), printout);
			assertElementIsEnabled(costing.getRvuMaintenanceButtonUndo(), printout);
			assertElementIsEnabled(costing.getRvuMaintenanceButtonSaveRvus(), printout);
			assertTheElementIsDisabled(costing.getRvuMaintenanceButtonApplySelections(), printout);
			ExtentReport.logPass("PASS", "test16EditDataGridCellAndAssertClearRvusAndSaveAndUndoAndSaveRvusButtonsAreEnabled");

		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test16EditDataGridCellAndAssertClearRvusAndSaveAndUndoAndSaveRvusButtonsAreEnabled", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test18ClickSaveRvusButtonAndAssertValueWasSavedInCell_5993() throws Throwable {
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
	public void test19AssertClearRvusAndSaveAndUndoAndSaveRvusButtonsAreDisabled_5993() throws Throwable {
		try {
//			assertElementIsDisabled(costing.getRvuMaintenanceButtonClearRvusAndSave(), printout);
			//shilpa 12.12.2022
			assertElementIsEnabled(costing.getRvuMaintenanceButtonClearRvusAndSave(), printout);

			assertTheElementIsDisabled(costing.getRvuMaintenanceButtonUndo(), printout);
			assertTheElementIsDisabled(costing.getRvuMaintenanceButtonSaveRvus(), printout);
			assertTheElementIsDisabled(costing.getRvuMaintenanceButtonApplySelections(), printout);
			ExtentReport.logPass("PASS", "test19AssertClearRvusAndSaveAndUndoAndSaveRvusButtonsAreDisabled");

		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test19AssertClearRvusAndSaveAndUndoAndSaveRvusButtonsAreDisabled", driver, e);
			fail(e.getMessage());
		}
	}
	public void assertdropdownDisabled(WebElement element) {
		String disabledAttributeText=null;
		try {
			waitForAjaxExtJs();
			disabledAttributeText = element.getAttribute("class");
			
		} catch (Throwable e) {
			System.out.println("Element Not Found");
			fail("element not found");
		}
		boolean isDisabled = disabledAttributeText.contains("disablField");
		if (printout) {
			System.out.println("Element class text: " + disabledAttributeText);
			System.out.println("IsDisabled: " + isDisabled);
		}
		assertTrue(disabledAttributeText, isDisabled);
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
