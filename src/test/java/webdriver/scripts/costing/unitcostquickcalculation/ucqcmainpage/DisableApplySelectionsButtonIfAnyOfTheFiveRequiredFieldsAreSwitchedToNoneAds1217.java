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
public class DisableApplySelectionsButtonIfAnyOfTheFiveRequiredFieldsAreSwitchedToNoneAds1217 extends UcqcHelper {

  private static CostingMap ucqcMap;
  private String[] requiredFields = {"QA Cost Model", "QA MHFY05 After Vol Change", "150 Marina Medical Center", "2110  ICU", "Apr 2004 to Mar 2005"};

  /**Zephyr ticket ADS-1217 (Dev Story ADS-571).
  Last Updated 08-16-19 */
  @BeforeClass
  public static void setupScript() throws Exception {
    ucqcMap = BuildMap.getInstance(driver, CostingMap.class);
    System.out.println("Test Class: " + DisableApplySelectionsButtonIfAnyOfTheFiveRequiredFieldsAreSwitchedToNoneAds1217.class.getSimpleName());
    Login.loginUser("CostingDepartmentManager1");
    goToPage("Unit Cost Quick Calculation");
    doMaximizeWindow();
  }

  @Test
  public void test01UcqcPageVerifyDefaultStateOfApplySelectionsButtonAsDisabled() {
    try {
      waitForAjaxExtJs();
      assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test02aUcqcPageVerifyDisabledStateOfApplySelectionsButtonAfterSelectingCostModel() {
    try {
      doDropdownSelectUsingOptionText(ucqcMap.getUnitCostQuickCalculationDropdownCostModel(),ucqcMap.getUnitCostQuickCalculationDropdownCostModelMenuList(),requiredFields[0]);
      assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test02bUcqcPageVerifyDisabledStateOfApplySelectionsButtonAfterSelectingCostModelScenario() {
    try {
      doDropdownSelectUsingOptionText(ucqcMap.getUnitCostQuickCalculationDropdownCostModelScenario(),ucqcMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),requiredFields[1]);
      assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test02cUcqcPageVerifyDisabledStateOfApplySelectionsButtonAfterSelectingEntity() {
    try {
      doDropdownSelectUsingOptionText(ucqcMap.getUnitCostQuickCalculationDropdownEntity(),ucqcMap.getUnitCostQuickCalculationDropdownEntityMenuList(),requiredFields[2]);
      assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test02dUcqcPageVerifyDisabledStateOfApplySelectionsButtonAfterSelectingDepartment() {
    try {
      selectDepartment(requiredFields[3]);
      assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test03UcqcPageVerifyEnabledStateOfApplySelectionsButtonAfterSelectingResultsStoredFor() {
    try {
      doDropdownSelectUsingOptionText(ucqcMap.getUnitCostQuickCalculationFieldResultsStoredFor(),ucqcMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),requiredFields[4]);
      assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test04UcqcPageUndoCostModelSelectionAndApplySelectionsButtonBecomesDisabled() {
    try {
      waitForAjaxExtJs();
      ucqcPopulateRequiredFieldsToDisplayGrid(requiredFields);
      assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      doDropdownSelectUsingOptionText(ucqcMap.getUnitCostQuickCalculationDropdownCostModel(),ucqcMap.getUnitCostQuickCalculationDropdownCostModelMenuList(),"<None>");
      assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test11UcqcPageUndoCostModelScenarioSelectionAndApplySelectionsButtonBecomesDisabled() {
    try {
      waitForAjaxExtJs();
      ucqcPopulateRequiredFieldsToDisplayGrid(requiredFields);
      assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      doDropdownSelectUsingOptionText(ucqcMap.getUnitCostQuickCalculationDropdownCostModelScenario(),ucqcMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),"<None>");
      assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test12UcqcPageUndoEntitySelectionAndApplySelectionsButtonBecomesDisabled() {
    try {
      waitForAjaxExtJs();
      ucqcPopulateRequiredFieldsToDisplayGrid(requiredFields);
      assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      doDropdownSelectUsingOptionText(ucqcMap.getUnitCostQuickCalculationDropdownEntity(),ucqcMap.getUnitCostQuickCalculationDropdownEntityMenuList(),"<None>");
      assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test13UcqcPageUndoDepartmentSelectionAndApplySelectionsButtonBecomesDisabled() {
    try {
      waitForAjaxExtJs();
      ucqcPopulateRequiredFieldsToDisplayGrid(requiredFields);
      assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      selectDepartment("<None>");
      assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test14UcqcPageUndoResultsStoredForSelectionAndApplySelectionsButtonBecomesDisabled() {
    try {
      waitForAjaxExtJs();
      ucqcPopulateRequiredFieldsToDisplayGrid(requiredFields);
      assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      doDropdownSelectUsingOptionText(ucqcMap.getUnitCostQuickCalculationFieldResultsStoredFor(),ucqcMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"<None>");
      assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }
}
