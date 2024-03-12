package webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VerifyUCQCdropdownValuesForOptionNone extends UcqcHelper {
	private static CostingMap costingMap;
	private static ContractingMap contractMap;
	static String costModel = "Marina";
	static String costModelScenario = "*CM1 TB MHFY05 After Vol Change";
	static String entity = "150 Marina Medical Center";
	static String department="2130 PED ICU";
	static final String[] requiredFields = { "Marina", "*CM1 TB MHFY05 After Vol Change", "150 Marina Medical Center",
			"2130", "Apr 2004 to Apr 2004" };
	
	static final String[] requiredFieldsUpdate = { "Marina", "*CM2 TB MHFY05 No Price List - 2", "150 Marina Medical Center",
			"2110", "Apr 2004 to Mar 2005" };
/** Regression Test ADS-5915 **/
	@BeforeClass
	public static void setupScript() throws Throwable {
		ExtentReport.reportCreate("VerifyUCQCdropdownValuesForOptionNone",
				"webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation",
				"VerifyUCQCdropdownValuesForOptionNone");

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
//ADS-5915
	@Test
	public void test01SetCostModelToNone_5915() throws Throwable {
		try {
			doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModel(),
					costingMap.getUnitCostQuickCalculationDropdownCostModelMenuList(), "<None>");
			assertUCQCElementHasAttributeDisabled(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),
					printout);
			assertUCQCElementHasAttributeDisabled(costingMap.getUnitCostQuickCalculationDropdownEntity(), printout);
			assertUCQCElementHasAttributeDisabled(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),
					printout);
			assertUCQCElementHasAttributeDisabled(costingMap.getUcqcDeptSelectButton(), printout);
			assertThatAttributeValue(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(), "<None>",
					printout);
			assertThatAttributeValue(costingMap.getUnitCostQuickCalculationDropdownEntity(), "<None>", printout);
			assertThatAttributeValue(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(), "<None>", printout);
			assertThatAttributeValue(costingMap.getUnitCostQuickCalculationDropdownCostModel(), "<None>", printout);
			assertThatString(costingMap.getUcqcDeptFieldNone(), "<None>", printout);
			ExtentReport.logPass("PASS", "test01SetCostModelToNone");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01SetCostModelToNone", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test02SetCostModelScenarioToNone_5915() throws Throwable {
		try {
			ucqcDisplayChargeCodeGrid(requiredFields);
			doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),
					costingMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(), "<None>");
			assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModel(), printout);
			assertUCQCElementHasAttributeDisabled(costingMap.getUcqcDeptSelectButton(), printout);
			assertUCQCElementHasAttributeDisabled(costingMap.getUnitCostQuickCalculationDropdownEntity(), printout);
			assertUCQCElementHasAttributeDisabled(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),
					printout);
			assertThatAttributeValue(costingMap.getUnitCostQuickCalculationDropdownEntity(), "<None>", printout);
			assertThatAttributeValue(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(), "<None>", printout);
			assertThatAttributeValue(costingMap.getUnitCostQuickCalculationDropdownCostModel(), costModel, printout);
			assertThatString(costingMap.getUcqcDeptFieldNone(), "<None>", printout);
			ExtentReport.logPass("PASS", "test02SetCostModelScenarioToNone");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02SetCostModelScenarioToNone", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test03SetCostModelEntityToNone_5915() throws Throwable {
		try {
			ucqcDisplayChargeCodeGrid(requiredFields);
			doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownEntity(),
					costingMap.getUnitCostQuickCalculationDropdownEntityMenuList(), "<None>");
			assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModel(), printout);
			assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(), printout);
			assertUCQCElementHasAttributeDisabled(costingMap.getUcqcDeptSelectButton(), printout);
			assertUCQCElementHasAttributeDisabled(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),
					printout);
			assertThatAttributeValue(costingMap.getUnitCostQuickCalculationDropdownEntity(), "<None>", printout);
			assertThatAttributeValue(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),
					costModelScenario, printout);
			assertThatAttributeValue(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(), "<None>", printout);
			assertThatAttributeValue(costingMap.getUnitCostQuickCalculationDropdownCostModel(), costModel, printout);
			assertThatString(costingMap.getUcqcDeptFieldNone(), "<None>", printout);
			ExtentReport.logPass("PASS", "test03SetCostModelEntityToNone");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03SetCostModelEntityToNone", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test04SetDepartmentValueToNone_5915() throws Throwable {
		try {
			ucqcDisplayChargeCodeGrid(requiredFields);
			updateDepartment("<None>");
			assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownEntity(), printout);
			assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModel(), printout);
			assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(), printout);
			assertUCQCElementHasAttributeDisabled(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),
					printout);
			assertThatAttributeValue(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),
					costModelScenario, printout);
			assertThatAttributeValue(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(), "<None>", printout);
			assertThatAttributeValue(costingMap.getUnitCostQuickCalculationDropdownCostModel(), costModel, printout);
			assertThatAttributeValue(costingMap.getUnitCostQuickCalculationDropdownEntity(), entity, printout);

			ExtentReport.logPass("PASS", "test04SetDepartmentValueToNone");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04SetDepartmentValueToNone", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test05SetResultsStoredToNone_5915() throws Throwable {
		try {
			ucqcDisplayChargeCodeGrid(requiredFields);
		    doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),costingMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"<None>");
			assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownEntity(), printout);
			assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModel(), printout);
			assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(), printout);
			assertElementIsEnabled(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),
					printout);
			assertThatAttributeValue(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),
					costModelScenario, printout);
			assertThatAttributeValue(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(), "<None>", printout);
			assertThatAttributeValue(costingMap.getUnitCostQuickCalculationDropdownCostModel(), costModel, printout);
			assertThatAttributeValue(costingMap.getUnitCostQuickCalculationDropdownEntity(), entity, printout);
			assertThatAttributeValue(costingMap.getUnitCostQuickCalculationDropdownEntity(), entity, printout);
			assertThatString(costingMap.getUcqcDeptFieldNone(), department, printout);

			ExtentReport.logPass("PASS", "test05SetDepartmentValueToNone");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test05SetDepartmentValueToNone", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test06ResetCostModel_5915() throws Throwable {
		try {
			ucqcDisplayChargeCodeGrid(requiredFields);
		    doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModel(),costingMap.getUnitCostQuickCalculationDropdownCostModelMenuList(),"Marina Clinics");
		    assertUCQCElementHasAttributeDisabled(costingMap.getUcqcDeptSelectButton(), printout);
			assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(), printout);
			assertThatAttributeValue(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(), "<None>",
					printout);
			assertThatAttributeValue(costingMap.getUnitCostQuickCalculationDropdownEntity(), "<None>", printout);
			assertThatAttributeValue(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(), "<None>", printout);
			assertThatString(costingMap.getUcqcDeptFieldNone(), "<None>", printout);
			ExtentReport.logPass("PASS", "test06ResetCostModel");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test06ResetCostModel", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test07ResetCostModelScenario_5915() throws Throwable {
		try {
			ucqcDisplayChargeCodeGrid(requiredFields);
		    doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),costingMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),"*CM2 TB MHFY05 No Price List - 2");
		    assertThatAttributeValue(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(), "*CM2 TB MHFY05 No Price List - 2",
					printout);
			assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownEntity(), printout);

		    assertThatAttributeValue(costingMap.getUnitCostQuickCalculationDropdownEntity(), "<None>", printout);
			assertThatString(costingMap.getUcqcDeptFieldNone(), "<None>", printout);
			assertThatAttributeValue(costingMap.getUnitCostQuickCalculationDropdownCostModel(), "Marina Clicnics", printout);

			ExtentReport.logPass("PASS", "test07ResetCostModelScenario");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test07ResetCostModelScenario", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test08ResetEntity_5915() throws Throwable {
		try {
			ucqcDisplayChargeCodeGrid(requiredFieldsUpdate);
		    doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownEntity(),costingMap.getUnitCostQuickCalculationDropdownEntityMenuList(),"200 Southgate");
		    assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownEntity(), printout);
			assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModel(), printout);
			assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(), printout);
		    assertElementIsEnabled(costingMap.getUcqcDeptFieldNone(),printout);
		    assertElementIsEnabled(costingMap.getUcqcDeptSelectButton(),printout);
		    assertThatString(costingMap.getUcqcDeptFieldNone(), "<None>", printout);
		    assertUCQCElementHasAttributeDisabled(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),
					printout);
			assertThatAttributeValue(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(), "<None>", printout);

			ExtentReport.logPass("PASS", "test08ResetEntity");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test08ResetEntity", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test09ResetDepartment_5915() throws Throwable {
		try {
			ucqcDisplayChargeCodeGrid(requiredFieldsUpdate);
			updateDepartment("2111");
			assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownEntity(), printout);
			assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModel(), printout);
			assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(), printout);
		    assertElementIsEnabled(costingMap.getUcqcDeptFieldNone(),printout);
		    assertElementIsEnabled(costingMap.getUcqcDeptSelectButton(),printout);
		    assertThatAttributeValue(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(), "<None>", printout);
		    assertElementIsEnabled(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),
					printout);
			ExtentReport.logPass("PASS", "test09ResetResultsStoredFor");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test09ResetResultsStoredFor", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test10ResetResultsStoredFor_5915() throws Throwable {
		try {
		    doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),costingMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"May 2004 to May 2004");
		    assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownEntity(), printout);
			assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModel(), printout);
			assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(), printout);
		    assertElementIsEnabled(costingMap.getUcqcDeptFieldNone(),printout);
		    assertElementIsEnabled(costingMap.getUcqcDeptSelectButton(),printout);
		    assertThatAttributeValue(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(), "May 2004 to May 2004", printout);
		    assertThatAttributeValue(costingMap.getUnitCostQuickCalculationDropdownEntity(), "200 Southgate", printout);
		    assertThatAttributeValue(costingMap.getUnitCostQuickCalculationDropdownCostModel(), "Marina clinics", printout);
		    assertThatAttributeValue(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(), "*CM2 TB MHFY05 No Price List", printout);
			assertThatString(costingMap.getUcqcDeptFieldNone(), "2111 CCU", printout);

		    ExtentReport.logPass("PASS", "test10ResetResultsStoredFor");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test10ResetResultsStoredFor", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
 	public static void endtest() throws Exception {

 		ExtentReport.report.flush();

 	}
  
  
}
