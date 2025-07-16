package webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.AdsHelper;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentSelectionAvailableAds1214 extends UcqcHelper {

	static CostingMap department;
	static DialogsMap dialogs;
	String originalCriteriaFilter;
	String dept = "2130  PED ICU";
	private DialogsMap dialog = BuildMap.getInstance(driver, DialogsMap.class);
	AdsHelper helper = new AdsHelper();

	/**
	 * Test tickets ADS-1212, 1214 (Dev tickets ADS-367, 584, 1048) This script
	 * tests the Department drop down element.
	 * 
	 * @throws Throwable
	 */
	@BeforeClass
	public static void setupScript() throws Throwable {

		ExtentReport.reportCreate("DepartmentSelectionAvailableAds1214",
				"webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage",
				"DepartmentSelectionAvailableAds1214");

		try {
			department = BuildMap.getInstance(driver, CostingMap.class);
			dialogs = BuildMap.getInstance(driver, DialogsMap.class);
			System.out.println("Test Class: " + DepartmentSelectionAvailableAds1214.class.getSimpleName());
			Login.loginUser("CostingDepartmentManager1");
			goToPage("Unit Cost Quick Calculation");
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test01ConfirmSelectButtonIsDisabledByDefault() throws Throwable {
		try {
			waitForAjaxExtJs();
			assertElementIsDisabled(department.getUnitCostQuickCalculationButtonSelect(), printout);
			ExtentReport.logPass("PASS", "test01ConfirmSelectButtonIsDisabledByDefault");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ConfirmSelectButtonIsDisabledByDefault", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test02ConfirmAsteriskDisplaysForDepartmentElementLabel() throws Throwable {
		try {
			waitForAjaxExtJs();
			assertAsteriskIsDisplayed("Department");
			ExtentReport.logPass("PASS", "test02ConfirmAsteriskDisplaysForDepartmentElementLabel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ConfirmAsteriskDisplaysForDepartmentElementLabel", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test03SelectUcqcCriteriaAndAssertSelectButtonIsDisabled() throws Throwable {
		try {
			waitForAjaxExtJs();
			doDropdownSelectUsingOptionText(department.getUnitCostQuickCalculationDropdownCostModel(),
					department.getUnitCostQuickCalculationDropdownCostModelMenuList(), "Marina");
			doDropdownSelectUsingOptionText(department.getUnitCostQuickCalculationDropdownCostModelScenario(),
					department.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(), "ADS-1197 Multi Depts");
			assertElementIsDisabled(department.getUnitCostQuickCalculationButtonSelect(), printout);
			ExtentReport.logPass("PASS", "test03SelectUcqcCriteriaAndAssertSelectButtonIsDisabled");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03SelectUcqcCriteriaAndAssertSelectButtonIsDisabled", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test04SetDepartmentDropdownAndAssertDepartmentSelectButtonIsEnabled() throws Throwable {
		try {
			waitForAjaxExtJs();
			doDropdownSelectUsingOptionText(department.getUnitCostQuickCalculationDropdownEntity(),
					department.getUnitCostQuickCalculationDropdownEntityMenuList(), "150 Marina Medical Center");
			assertElementIsEnabled(department.getUnitCostQuickCalculationButtonSelect(), printout);
			ExtentReport.logPass("PASS", "test04SetDepartmentDropdownAndAssertDepartmentSelectButtonIsEnabled");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04SetDepartmentDropdownAndAssertDepartmentSelectButtonIsEnabled", driver,
					e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test05ConfirmDepartmentMenuOptionsAreFormattedProperly() throws Throwable {
		try {
			waitForAjaxExtJs();
			selectDepartment(dept);
			String text = driver.findElement(By.xpath("//*[contains(@class,'singleSelctLabl')]/div")).getText();
			if (printout) {
				System.out.println("Department menu option format: " + text);
			}
			assertEquals("2130 PED ICU", text);
			ExtentReport.logPass("PASS", "test05ConfirmDepartmentMenuOptionsAreFormattedProperly");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05ConfirmDepartmentMenuOptionsAreFormattedProperly", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test06ConfirmDeptModalFieldDropdown() throws Throwable {
		try {
			waitForAjaxExtJs();
			doClickButton("Select");
			doClickButton("Filter");
			doClick(department.getUnitCostQuickCalculationDepartmentFilterField());
			waitForAjaxExtJs();
			List<String> expectedField = Arrays.asList("Department Code", "Department Name");
			// Shilpa update xpath on 11.4.2024
//      WebElement fields = driver.findElement(By.xpath("//div[@class=\"x-mask\"][4]/preceding::div[@class=\"x-boundlist-list-ct\"][1]/ul"));
			WebElement fields = driver.findElement(By.xpath(
					"//div[contains(@class,'x-mask')][6]/preceding::div[contains(@class,'x-boundlist-list-ct')][1]/ul"));
			List<WebElement> existingFields = fields.findElements(By.tagName("li"));
			List<String> actualFields = new ArrayList<>();
			for (WebElement field : existingFields) {
				System.out.println("Actual field listed: " + field.getText());
				actualFields.add(field.getText());
			}
			assertEquals(expectedField, actualFields);
			ExtentReport.logPass("PASS", "test06ConfirmDeptModalFieldDropdown");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06ConfirmDeptModalFieldDropdown", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test07ConfirmDeptModalOperatorDropdown() throws Throwable {
		try {
			doClick(department.getUnitCostQuickCalculationDepartmentFilterOperator());
			waitForAjaxExtJs();
			List<String> expectedOperator = Arrays.asList("Is", "Is not");
			// shilpa update xpath on 11.4.2024
//      WebElement operators = driver.findElement(By.xpath("//div[@class=\"x-mask\"][5]/preceding::div[@class=\"x-boundlist-list-ct\"][1]/ul"));
			WebElement operators = driver.findElement(By.xpath(
					"(//div[contains(@class,'x-tab-guard')]/preceding::div[contains(@class,'x-boundlist-list-ct')][1]/ul)[7]"));

			List<WebElement> existingOperators = operators.findElements(By.tagName("li"));
			List<String> actualOperators = new ArrayList<>();
			for (WebElement operator : existingOperators) {
				System.out.println("Actual operator listed: " + operator.getText());
				actualOperators.add(operator.getText());
			}
			assertEquals(expectedOperator, actualOperators);
			ExtentReport.logPass("PASS", "test07ConfirmDeptModalOperatorDropdown");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07ConfirmDeptModalOperatorDropdown", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test08ConfirmDeptModalConditionDropdown() throws Throwable {
		try {
			// Shilpa updated 4.11.2024
//      doDropdownSelectUsingOptionText(department.getUnitCostQuickCalculationDepartmentFilterField(), department.getUnitCostQuickCalculationDepartmentFilterFieldMenuList(), "Department Code");
			doDropdownSelectUsingOptionTextServices(dialog.getFilterNameField(), dialog.getFilterDialogDropdownField(),
					"Department Code");

			doClick(department.getUnitCostQuickCalculationDepartmentFilterCondition());
			waitForAjaxExtJs();
			List<String> expectedCondition = Arrays.asList("Equal To", "Contains", "Starts With", "Ends With",
					"One Of");
			WebElement conditions = driver.findElement(By.xpath(
					"(//div[contains(@class,'x-tab-guard')]/preceding::div[contains(@class,'x-boundlist-list-ct')][1]/ul)[8]"));
			List<WebElement> existingConditions = conditions.findElements(By.tagName("li"));
			List<String> actualConditions = new ArrayList<>();
			for (WebElement condition : existingConditions) {
				System.out.println("Actual condition listed: " + condition.getText());
				actualConditions.add(condition.getText());
			}
			assertEquals(expectedCondition, actualConditions);
			ExtentReport.logPass("PASS", "test08ConfirmDeptModalConditionDropdown");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test08ConfirmDeptModalConditionDropdown", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test09ConfirmValueFieldIsNull() throws Throwable {
		try {
			String filterValue;
			filterValue = department.getUnitCostQuickCalculationDepartmentFilterValue().getText();
			if (printout) {
				System.out.println(filterValue);
			}
			assertEquals("", filterValue);
			ExtentReport.logPass("PASS", "test09ConfirmValueFieldIsNull");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test09ConfirmValueFieldIsNull", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test10filterDepartmentsWithFilterDepartmentCodeIsContains2() throws Throwable {
		try {
			originalCriteriaFilter = filterToMatchCriteria();
//      filterDialogSetAllFields("Department Code", "Is", "Contains", "2", printout);
			// Shilpa added below line 11.4.2024
			helper.doFilterSetFilterParameters("Department Code", "Is", "Contains", "2");
			doClick(department.getUnitCostQuickCalculationDepartmentFilterButtonAdd());
			waitForAjaxExtJs();
			String newCriteriaFilter = filterToMatchCriteria();
			assertNotEquals(originalCriteriaFilter, newCriteriaFilter);
			doClick(department.getUnitCostQuickCalculationDepartmentFilterButtonRemoveAll());
			ExtentReport.logPass("PASS", "test10filterDepartmentsWithFilterDepartmentCodeIsContains2");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test10filterDepartmentsWithFilterDepartmentCodeIsContains2", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test11filterDepartmentsWithFilterDepartmentCodeIsNotStartsWith2() throws Throwable {
		try {
			waitForAjaxExtJs();
//      filterDialogSetAllFields("Department Code", "Is not", "Starts With", "2", printout);
			// Shilpa added below line 11.4.2024
			helper.doFilterSetFilterParameters("Department Code", "Is not", "Contains", "2");
			doClick(department.getUnitCostQuickCalculationDepartmentFilterButtonAdd());
			waitForAjaxExtJs();
			String newCriteriaFilter2 = filterToMatchCriteria();
			assertNotEquals(originalCriteriaFilter, newCriteriaFilter2);
			doClick(department.getUnitCostQuickCalculationDepartmentFilterButtonRemoveAll());
			doClick(department.getUnitCostQuickCalculationDepartmentButtonCancelAndClose());
			waitForAjaxExtJs();
			ExtentReport.logPass("PASS", "test11filterDepartmentsWithFilterDepartmentCodeIsNotStartsWith2");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test11filterDepartmentsWithFilterDepartmentCodeIsNotStartsWith2", driver, e);
			fail(e.getMessage());
		}
	}

	public String filterToMatchCriteria() {
		String filterToMatchCriteria;
		filterToMatchCriteria = driver.findElement(By.xpath("//div[contains(@id,'filtergrid')]/descendant::label"))
				.getText();
		System.out.println(filterToMatchCriteria);
		return filterToMatchCriteria;
	}

	public void filterDialogSetAllFields(String filterDialogFieldOptionText, String filterDialogOperatorOptionText,
			String filterDialogConditionOptionText, String filterDialogValueFieldValue, boolean printout)
			throws InterruptedException {
		// set Field value
		waitForAjaxExtJs();
		doClick(dialogs.getFilterDialogDropdownField());
		waitForSpinnerToEnd();
		waitForAjaxExtJs();
		// below xpath may differ depending on the specific filter dialog-it should
		// capture the menu list for Field
		String fieldIdString = driver.findElement(By
				.xpath("//div[contains(@class,'x-mask')][4]/preceding::div[contains(@class,'x-boundlist-list-ct')][1]"))
				.getAttribute("id");
		int fieldIdInteger = Integer.parseInt(getNumbersFromStringWithRegex(fieldIdString));
		WebElement list = driver.findElement(
				By.xpath("//div[contains(@class,'floating')]/div[contains(@id,'" + fieldIdInteger + "-listEl')]/ul")); // div[contains(@class,'floating')]/
		List<WebElement> menu = list.findElements(By.tagName("li"));
		for (WebElement option : menu) {
			if (option.getText().equals(filterDialogFieldOptionText)) {
				option.click();
				break;
			}
		}
		// set Operator value
		int operatorIdInteger = fieldIdInteger + 1;
		doClick(dialogs.getFilterDialogDropdownOperator());
		for (int i = operatorIdInteger; i < operatorIdInteger + 10; i++) {
			try {
				list = driver.findElement(
						By.xpath("//div[contains(@class,'floating')]/div[contains(@id,'" + i + "-listEl')]/ul"));
				operatorIdInteger = i;
				break;
			} catch (Throwable e) {
				continue;
			}
		}
		menu = list.findElements(By.tagName("li"));
		for (WebElement option : menu) {
			if (option.getText().equals(filterDialogOperatorOptionText)) {
				option.click();
				break;
			}
		}
		// set Condition value
		waitForAjaxExtJs();
		doClick(dialogs.getFilterDialogDropdownCondition());
		waitForAjaxExtJs();
		int conditionIdInteger = operatorIdInteger + 1;
		for (int i = conditionIdInteger; i < conditionIdInteger + 10; i++) {
			try {
				list = driver.findElement(
						By.xpath("//div[contains(@class,'floating')]/div[contains(@id,'" + i + "-listEl')]/ul"));
				conditionIdInteger = i;
				break;
			} catch (Throwable e) {
				continue;
			}
		}
		menu = list.findElements(By.tagName("li"));
		for (WebElement option : menu) {
			if (option.getText().equals(filterDialogConditionOptionText)) {
				option.click();
				break;
			}
		}
		// set Value field value
		dialogs.getFilterDialogFormFieldValue().click();
		dialogs.getFilterDialogFormFieldValue().clear();
		dialogs.getFilterDialogFormFieldValue().sendKeys(filterDialogValueFieldValue);
		// print values
		if (printout) {
			System.out.println("filterDialogFieldOptionText: " + filterDialogFieldOptionText);
			System.out.println("fieldIdInteger: " + fieldIdInteger);
			System.out.println("filterDialogOperatorOptionText: " + filterDialogOperatorOptionText);
			System.out.println("operatorIdInteger: " + operatorIdInteger);
			System.out.println("filterDialogConditionOptionText: " + filterDialogConditionOptionText);
			System.out.println("conditionIdInteger: " + conditionIdInteger);
			System.out.println("filterDialogValueFieldValue: " + filterDialogValueFieldValue);
		}
	}

	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}

}
