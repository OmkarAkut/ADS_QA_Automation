package webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
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
   * Updated 8-15-19 */
  @BeforeClass
  public static void setupScript() throws Exception {
    costingMap = BuildMap.getInstance(driver, CostingMap.class);
    System.out.println("Test Class: " + CorrectlySetValuesToNoneInSelectorsWhenPriorSelectionsChangedAds1216.class.getSimpleName());
    Login.loginUser("CostingDepartmentManager1");
    goToPage("Unit Cost Quick Calculation");
  }

  @Test
  public void test01setUcqcCriteriaAndAssertRelevantDropdownsAreEnabled() {
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
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test02ChangeCostModelToNoneAndAssertRelevantDropdownsAreDisabled() {
    try {
      waitForAjaxExtJs();
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModel(),costingMap.getUnitCostQuickCalculationDropdownCostModelMenuList(),"<None>");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModel(),printout);
      assertUCQCDropdownIsDisabled("costModelScenarioId",printout);
      assertUCQCDropdownIsDisabled("entityCode",printout);
      assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonSelect(),printout);
      assertUCQCDropdownIsDisabled("resultStored",printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test03ResetCriteriaAndAssertRelevantDropdownsAreEnabled() {
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
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test04ChangeCostModelScenarioToNoneAndAssertRelevantDropdownsAreDisabled() {
    try {
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),costingMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),"<None>");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModel(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),printout);
      assertUCQCDropdownIsDisabled("entityCode",printout);
      assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonSelect(),printout);
      assertUCQCDropdownIsDisabled("resultStored",printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test05ResetCriteriaAndAndAssertRelevantDropdownsAreEnabled() {
    try {
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),costingMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),"*CM1 TB MHFY05 After Vol Change");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownEntity(),printout);
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownEntity(),costingMap.getUnitCostQuickCalculationDropdownEntityMenuList(),"150 Marina Medical Center");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonSelect(),printout);
      selectDepartment(dept);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),printout);
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),costingMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"Apr 2004 to Apr 2004");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test06ChangeEntityToNoneAndAssertRelevantDropdownsAreDisabled() {
    try {
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownEntity(),costingMap.getUnitCostQuickCalculationDropdownEntityMenuList(),"<None>");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModel(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownEntity(),printout);
      assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonSelect(),printout);
      assertUCQCDropdownIsDisabled("resultStored",printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test07ResetCriteriaAndAssertRelevantDropdownsAreEnabled() {
    try {
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownEntity(),costingMap.getUnitCostQuickCalculationDropdownEntityMenuList(),"150 Marina Medical Center");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonSelect(),printout);
      selectDepartment(dept);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),printout);
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),costingMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"Apr 2004 to Apr 2004");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test08ChangeDepartmentToNoneAndAssertRelevantDropdownsAreDisabled() {
    try {
      selectDepartment("<None>");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(), printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModel(), printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(), printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownEntity(), printout);
      assertUCQCDropdownIsDisabled("resultStored", printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test09ResetCriteriaAndAssertRelevantDropdownsAreEnabled() {
    try {
      selectDepartment(dept);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),printout);
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),costingMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"Apr 2004 to Apr 2004");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test10ChangeResultsStoredForToNoneAndAssertRelevantDropdownsAreDisabled() {
    try {
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),costingMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"<None>");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModel(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownEntity(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonSelect(),printout);
      assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test11ResetCriteriaAndAssertRelevantDropdownsAreEnabled() {
    try {
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),costingMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"Apr 2004 to Apr 2004");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test12ChangeCostModelToDifferentValueAndAssertRelevantDropdownsAreDisabled() {
    try {
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModel(),costingMap.getUnitCostQuickCalculationDropdownCostModelMenuList(),"Marina Clinics");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModel(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),printout);
      assertUCQCDropdownIsDisabled("entityCode",printout);
      assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonSelect(),printout);
      assertUCQCDropdownIsDisabled("resultStored",printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test13ResetCriteriaAndAssertRelevantDropdownsAreEnabled() {
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
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test14ChangeCostModelScenarioToDifferentValueAndAssertRelevantDropdownsAreDisabled() {
    try {
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),costingMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),"*CM2 TB MHFY05 No Price List");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModel(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownEntity(),printout);
      assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonSelect(),printout);
      assertUCQCDropdownIsDisabled("resultStored",printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test15ResetCriteriaAndAssertRelevantDropdownsAreEnabled() {
    try {
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),costingMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),"*CM2 TB MHFY05 No Price List");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownEntity(),printout);
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownEntity(),costingMap.getUnitCostQuickCalculationDropdownEntityMenuList(),"150 Marina Medical Center");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonSelect(),printout);
      selectDepartment(dept);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),printout);
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),costingMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"Apr 2004 to Mar 2005");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test16ChangeEntityToDifferentValueAndAssertRelevantDropdownsAreDisabled() {
    try {
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationDropdownEntity(),costingMap.getUnitCostQuickCalculationDropdownEntityMenuList(),"200 Southgate");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModel(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownEntity(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonSelect(),printout);
      assertUCQCDropdownIsDisabled("resultStored",printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test17ResetCriteriaAndAssertRelevantDropdownsAreEnabled() {
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
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test18ChangeDepartmentToDifferentValueAndAssertResultsStoredForDropdownIsDisabled() {
    try {
      selectDepartment("2111  CCU");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModel(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownEntity(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),printout);
      assertElementIsDisabled(costingMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test19SelectResultsStoredForValueAndAssertAllDropdownsAreEnabled() {
    try {
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),costingMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"Apr 2004 to Apr 2004");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModel(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownEntity(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonSelect(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test20ChangeResultsStoredForToDifferentValueAndAssertRelevantDropdownsAreEnabled() {
    try {
      doDropdownSelectUsingOptionText(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),costingMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"May 2004 to May 2004");
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationFieldResultsStoredFor(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModel(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownCostModelScenario(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationDropdownEntity(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonSelect(),printout);
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }
}


