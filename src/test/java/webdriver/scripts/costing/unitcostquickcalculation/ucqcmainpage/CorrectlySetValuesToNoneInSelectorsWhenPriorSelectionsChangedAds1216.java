package webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import net.bytebuddy.dynamic.scaffold.inline.MethodRebaseResolver.Disabled;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CorrectlySetValuesToNoneInSelectorsWhenPriorSelectionsChangedAds1216 extends UcqcHelper {

  private static CostingMap costingMap;
  String dept = "2130  PED ICU";

  /** Test ticket ADS-1216 (Dev Story ADS-569)
   * This script tests that default option None is correctly set in selectors when prior selections are changed.
   * Updated 8-15-19 
 * @throws Throwable */
  @BeforeClass
  public static void setupScript() throws Throwable {
	  
	  ExtentReport.reportCreate("CorrectlySetValuesToNoneInSelectorsWhenPriorSelectionsChangedAds1216","webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage", "CorrectlySetValuesToNoneInSelectorsWhenPriorSelectionsChangedAds1216");
	  
    try {
		costingMap = BuildMap.getInstance(driver, CostingMap.class);
		System.out.println("Test Class: " + CorrectlySetValuesToNoneInSelectorsWhenPriorSelectionsChangedAds1216.class.getSimpleName());
		Login.loginUser("CostingDepartmentManager1");
		goToPage("Unit Cost Quick Calculation");
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","setupScript", driver,e);
		fail(e.getMessage());
		
	} 
  }

  @Test
  public void test01setUcqcCriteriaAndAssertRelevantDropdownsAreEnabled() throws Throwable {
    try {
      waitForAjaxExtJs();
      assertUCQCDropdownIsDisabled("costModelScenarioId",printout);
      assertUCQCDropdownIsDisabled("entityCode",printout);
      assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonSelect(),printout);
      assertUCQCDropdownIsDisabled("resultStored",printout);
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModel(),costingMap.getUnitCostQuickCalculationDropdownCostModelMenuList(),"Marina");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),printout);
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),costingMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),"*CM1 TB MHFY05 After Vol Change");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownEntity(),printout);
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownEntity(),costingMap.getUnitCostQuickCalculationDropdownEntityMenuList(),"150 Marina Medical Center");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonSelect(),printout);
      selectDepartment(dept);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),printout);
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),costingMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"Apr 2004 to Apr 2004");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      ExtentReport.logPass("PASS", "test01setUcqcCriteriaAndAssertRelevantDropdownsAreEnabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test01setUcqcCriteriaAndAssertRelevantDropdownsAreEnabled", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test02ChangeCostModelToNoneAndAssertRelevantDropdownsAreDisabled() throws Throwable {
    try {
      waitForAjaxExtJs();
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModel(),costingMap.getUnitCostQuickCalculationDropdownCostModelMenuList(),"<None>");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModel(),printout);
      assertUCQCDropdownIsDisabled("costModelScenarioId",printout);
      assertUCQCDropdownIsDisabled("entityCode",printout);
      assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonSelect(),printout);
      assertUCQCDropdownIsDisabled("resultStored",printout);
      ExtentReport.logPass("PASS", "test02ChangeCostModelToNoneAndAssertRelevantDropdownsAreDisabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test02ChangeCostModelToNoneAndAssertRelevantDropdownsAreDisabled", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test03ResetCriteriaAndAssertRelevantDropdownsAreEnabled() throws Throwable {
    try {
      waitForAjaxExtJs();
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModel(),costingMap.getUnitCostQuickCalculationDropdownCostModelMenuList(),"Marina");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),printout);
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),costingMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),"*CM1 TB MHFY05 After Vol Change");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownEntity(),printout);
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownEntity(),costingMap.getUnitCostQuickCalculationDropdownEntityMenuList(),"150 Marina Medical Center");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonSelect(),printout);
      selectDepartment(dept);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),printout);
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),costingMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"Apr 2004 to Apr 2004");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      ExtentReport.logPass("PASS", "test03ResetCriteriaAndAssertRelevantDropdownsAreEnabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test03ResetCriteriaAndAssertRelevantDropdownsAreEnabled", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test04ChangeCostModelScenarioToNoneAndAssertRelevantDropdownsAreDisabled() throws Throwable {
    try {
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),costingMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),"<None>");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModel(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),printout);
      assertUCQCDropdownIsDisabled("entityCode",printout);
      assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonSelect(),printout);
      assertUCQCDropdownIsDisabled("resultStored",printout);
      ExtentReport.logPass("PASS", "test04ChangeCostModelScenarioToNoneAndAssertRelevantDropdownsAreDisabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test04ChangeCostModelScenarioToNoneAndAssertRelevantDropdownsAreDisabled", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test05ResetCriteriaAndAndAssertRelevantDropdownsAreEnabled() throws Throwable {
    try {
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),costingMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),"*CM1 TB MHFY05 After Vol Change");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownEntity(),printout);
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownEntity(),costingMap.getUnitCostQuickCalculationDropdownEntityMenuList(),"150 Marina Medical Center");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonSelect(),printout);
      selectDepartment(dept);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),printout);
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),costingMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"Apr 2004 to Apr 2004");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      ExtentReport.logPass("PASS", "test05ResetCriteriaAndAndAssertRelevantDropdownsAreEnabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test05ResetCriteriaAndAndAssertRelevantDropdownsAreEnabled", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test06ChangeEntityToNoneAndAssertRelevantDropdownsAreDisabled() throws Throwable {
    try {
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownEntity(),costingMap.getUnitCostQuickCalculationDropdownEntityMenuList(),"<None>");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModel(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownEntity(),printout);
      assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonSelect(),printout);
      assertUCQCDropdownIsDisabled("resultStored",printout);
      ExtentReport.logPass("PASS", "test06ChangeEntityToNoneAndAssertRelevantDropdownsAreDisabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test06ChangeEntityToNoneAndAssertRelevantDropdownsAreDisabled", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test07ResetCriteriaAndAssertRelevantDropdownsAreEnabled() throws Throwable {
    try {
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownEntity(),costingMap.getUnitCostQuickCalculationDropdownEntityMenuList(),"150 Marina Medical Center");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonSelect(),printout);
      selectDepartment(dept);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),printout);
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),costingMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"Apr 2004 to Apr 2004");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      ExtentReport.logPass("PASS", "test07ResetCriteriaAndAssertRelevantDropdownsAreEnabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test07ResetCriteriaAndAssertRelevantDropdownsAreEnabled", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test08ChangeDepartmentToNoneAndAssertRelevantDropdownsAreDisabled() throws Throwable {
    try {
      selectDepartment("<None>");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(), printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModel(), printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(), printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownEntity(), printout);
      assertUCQCDropdownIsDisabled("resultStored", printout);
      ExtentReport.logPass("PASS", "test08ChangeDepartmentToNoneAndAssertRelevantDropdownsAreDisabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test08ChangeDepartmentToNoneAndAssertRelevantDropdownsAreDisabled", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test09ResetCriteriaAndAssertRelevantDropdownsAreEnabled() throws Throwable {
    try {
      selectDepartment(dept);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),printout);
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),costingMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"Apr 2004 to Apr 2004");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      ExtentReport.logPass("PASS", "test09ResetCriteriaAndAssertRelevantDropdownsAreEnabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test09ResetCriteriaAndAssertRelevantDropdownsAreEnabled", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test10ChangeResultsStoredForToNoneAndAssertRelevantDropdownsAreDisabled() throws Throwable {
    try {
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),costingMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"<None>");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModel(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownEntity(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonSelect(),printout);
      assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      ExtentReport.logPass("PASS", "test10ChangeResultsStoredForToNoneAndAssertRelevantDropdownsAreDisabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test10ChangeResultsStoredForToNoneAndAssertRelevantDropdownsAreDisabled", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test11ResetCriteriaAndAssertRelevantDropdownsAreEnabled() throws Throwable {
    try {
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),costingMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"Apr 2004 to Apr 2004");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      ExtentReport.logPass("PASS", "test11ResetCriteriaAndAssertRelevantDropdownsAreEnabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test11ResetCriteriaAndAssertRelevantDropdownsAreEnabled", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test12ChangeCostModelToDifferentValueAndAssertRelevantDropdownsAreDisabled() throws Throwable {
    try {
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModel(),costingMap.getUnitCostQuickCalculationDropdownCostModelMenuList(),"Marina Clinics");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModel(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),printout);
      assertUCQCDropdownIsDisabled("entityCode",printout);
      assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonSelect(),printout);
      assertUCQCDropdownIsDisabled("resultStored",printout);
      ExtentReport.logPass("PASS", "test12ChangeCostModelToDifferentValueAndAssertRelevantDropdownsAreDisabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test12ChangeCostModelToDifferentValueAndAssertRelevantDropdownsAreDisabled", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test13ResetCriteriaAndAssertRelevantDropdownsAreEnabled() throws Throwable {
    try {
      waitForAjaxExtJs();
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModel(),costingMap.getUnitCostQuickCalculationDropdownCostModelMenuList(),"Marina");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),printout);
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),costingMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),"*CM1 TB MHFY05 After Vol Change");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownEntity(),printout);
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownEntity(),costingMap.getUnitCostQuickCalculationDropdownEntityMenuList(),"150 Marina Medical Center");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonSelect(),printout);
      selectDepartment(dept);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),printout);
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),costingMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"Apr 2004 to Apr 2004");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      ExtentReport.logPass("PASS", "test13ResetCriteriaAndAssertRelevantDropdownsAreEnabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test13ResetCriteriaAndAssertRelevantDropdownsAreEnabled", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test14ChangeCostModelScenarioToDifferentValueAndAssertRelevantDropdownsAreDisabled() throws Throwable {
    try {
    	//venkat text data updated 19.09.2022
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),costingMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),"*CM2 TB MHFY05 No Price List");
//      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),costingMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),"*CM2 TB MHFY05 No Price List - 2");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModel(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownEntity(),printout);
//      Omkar 14/2/2024 : The below select button will not be disabled. The value is incorrect. It should be columns to select, 'Select' button
//      assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonSelect(),printout);
      assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonColumnsToDisplaySelect(),printout);
//      Omkar 14/2/2024 :The below element will be enabled and not Disabled.class Verified in QA3
//      assertUCQCDropdownIsDisabled("resultStored",printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),printout);
      ExtentReport.logPass("PASS", "test14ChangeCostModelScenarioToDifferentValueAndAssertRelevantDropdownsAreDisabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test14ChangeCostModelScenarioToDifferentValueAndAssertRelevantDropdownsAreDisabled", driver,e);
      fail(e.getMessage());
    }
  }

 @Test
  public void test15ResetCriteriaAndAssertRelevantDropdownsAreEnabled() throws Throwable {
    try {
    	//venkat updated text data 19.09.2022
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),costingMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),"*CM2 TB MHFY05 No Price List");
//    	doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),costingMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),"*CM2 TB MHFY05 No Price List - 2");
    	
    	
    	assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownEntity(),printout);
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownEntity(),costingMap.getUnitCostQuickCalculationDropdownEntityMenuList(),"150 Marina Medical Center");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonSelect(),printout);
      selectDepartment(dept);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),printout);
//      Omkar 15/2/2024 : Old value is not available
//      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),costingMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"Apr 2004 to Mar 2005");
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),costingMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"Apr 2004 to Apr 2004");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      ExtentReport.logPass("PASS", "test15ResetCriteriaAndAssertRelevantDropdownsAreEnabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test15ResetCriteriaAndAssertRelevantDropdownsAreEnabled", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test16ChangeEntityToDifferentValueAndAssertRelevantDropdownsAreDisabled() throws Throwable {
    try {
    	doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),costingMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),"*CM2 TB MHFY05 No Price List - 2");
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownEntity(),costingMap.getUnitCostQuickCalculationDropdownEntityMenuList(),"200 Southgate");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModel(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownEntity(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonSelect(),printout);
      assertUCQCDropdownIsDisabled("resultStored",printout);
      ExtentReport.logPass("PASS", "test16ChangeEntityToDifferentValueAndAssertRelevantDropdownsAreDisabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test16ChangeEntityToDifferentValueAndAssertRelevantDropdownsAreDisabled", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test17ResetCriteriaAndAssertRelevantDropdownsAreEnabled() throws Throwable {
    try {
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModel(),costingMap.getUnitCostQuickCalculationDropdownCostModelMenuList(),"Marina");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),printout);
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),costingMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),"ADS-1197 Multi Depts");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownEntity(),printout);
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownEntity(),costingMap.getUnitCostQuickCalculationDropdownEntityMenuList(),"150 Marina Medical Center");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonSelect(),printout);
      selectDepartment(dept);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),printout);
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),costingMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"Apr 2004 to Apr 2004");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      ExtentReport.logPass("PASS", "test17ResetCriteriaAndAssertRelevantDropdownsAreEnabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test17ResetCriteriaAndAssertRelevantDropdownsAreEnabled", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test18ChangeDepartmentToDifferentValueAndAssertResultsStoredForDropdownIsDisabled() throws Throwable {
    try {
      selectDepartment("2111  CCU");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModel(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownEntity(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),printout);
      assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      ExtentReport.logPass("PASS", "test18ChangeDepartmentToDifferentValueAndAssertResultsStoredForDropdownIsDisabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test18ChangeDepartmentToDifferentValueAndAssertResultsStoredForDropdownIsDisabled", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test19SelectResultsStoredForValueAndAssertAllDropdownsAreEnabled() throws Throwable {
    try {
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),costingMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"Apr 2004 to Apr 2004");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModel(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownEntity(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonSelect(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      ExtentReport.logPass("PASS", "test19SelectResultsStoredForValueAndAssertAllDropdownsAreEnabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test19SelectResultsStoredForValueAndAssertAllDropdownsAreEnabled", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test20ChangeResultsStoredForToDifferentValueAndAssertRelevantDropdownsAreEnabled() throws Throwable {
    try {
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),costingMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"May 2004 to May 2004");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModel(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownEntity(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonSelect(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      ExtentReport.logPass("PASS", "test20ChangeResultsStoredForToDifferentValueAndAssertRelevantDropdownsAreEnabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test20ChangeResultsStoredForToDifferentValueAndAssertRelevantDropdownsAreEnabled", driver,e);
    	
      fail(e.getMessage());
    }
  }
  
  @AfterClass
 	public static void endtest() throws Exception {

 		ExtentReport.report.flush();

 	}
}


