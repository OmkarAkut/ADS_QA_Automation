package webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DisableApplySelectionsButtonIfAnyOfTheFiveRequiredFieldsAreSwitchedToNoneAds1217 extends UcqcHelper {

  private static CostingMap ucqcMap;
  private String[] requiredFields = {"QA Cost Model", "QA MHFY05 After Vol Change", "150 Marina Medical Center",
		//  "2110  ICU", 
		  "2110", //Venkat update department filed 21.09.2022
		  "Apr 2004 to Mar 2005"};

  /**Zephyr ticket ADS-1217 (Dev Story ADS-571).
  Last Updated 08-16-19 
 * @throws Throwable */
  @BeforeClass
  public static void setupScript() throws Throwable {
	  
	  ExtentReport.reportCreate("DisableApplySelectionsButtonIfAnyOfTheFiveRequiredFieldsAreSwitchedToNoneAds1217","webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage", "DisableApplySelectionsButtonIfAnyOfTheFiveRequiredFieldsAreSwitchedToNoneAds1217");
		
    try {
		ucqcMap = BuildMap.getInstance(driver, CostingMap.class);
		System.out.println("Test Class: " + DisableApplySelectionsButtonIfAnyOfTheFiveRequiredFieldsAreSwitchedToNoneAds1217.class.getSimpleName());
		Login.loginUser("CostingDepartmentManager1");
		goToPage("Unit Cost Quick Calculation");
		doMaximizeWindow();
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","setupScript", driver,e);
		  fail(e.getMessage());
	} 
    
    
  }

  @Test
  public void test01UcqcPageVerifyDefaultStateOfApplySelectionsButtonAsDisabled() throws Throwable {
    try {
      waitForAjaxExtJs();
      assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      ExtentReport.logPass("PASS", "test01UcqcPageVerifyDefaultStateOfApplySelectionsButtonAsDisabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test01UcqcPageVerifyDefaultStateOfApplySelectionsButtonAsDisabled", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test02aUcqcPageVerifyDisabledStateOfApplySelectionsButtonAfterSelectingCostModel() throws Throwable {
    try {
      doDropdownSelectUsingOptionText(ucqcMap.getUnitCostQuickCalculationDropdownCostModel(),ucqcMap.getUnitCostQuickCalculationDropdownCostModelMenuList(),requiredFields[0]);
      assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      ExtentReport.logPass("PASS", "test02aUcqcPageVerifyDisabledStateOfApplySelectionsButtonAfterSelectingCostModel");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test02aUcqcPageVerifyDisabledStateOfApplySelectionsButtonAfterSelectingCostModel", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test02bUcqcPageVerifyDisabledStateOfApplySelectionsButtonAfterSelectingCostModelScenario() throws Throwable {
    try {
      doDropdownSelectUsingOptionText(ucqcMap.getUnitCostQuickCalculationDropdownCostModelScenario(),ucqcMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),requiredFields[1]);
      assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      ExtentReport.logPass("PASS", "test02bUcqcPageVerifyDisabledStateOfApplySelectionsButtonAfterSelectingCostModelScenario");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test02bUcqcPageVerifyDisabledStateOfApplySelectionsButtonAfterSelectingCostModelScenario", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test02cUcqcPageVerifyDisabledStateOfApplySelectionsButtonAfterSelectingEntity() throws Throwable {
    try {
      doDropdownSelectUsingOptionText(ucqcMap.getUnitCostQuickCalculationDropdownEntity(),ucqcMap.getUnitCostQuickCalculationDropdownEntityMenuList(),requiredFields[2]);
      assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      ExtentReport.logPass("PASS", "test02cUcqcPageVerifyDisabledStateOfApplySelectionsButtonAfterSelectingEntity");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test02cUcqcPageVerifyDisabledStateOfApplySelectionsButtonAfterSelectingEntity", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test02dUcqcPageVerifyDisabledStateOfApplySelectionsButtonAfterSelectingDepartment() throws Throwable {
    try {
      //selectDepartment(requiredFields[3]); 
      updateDepartment(requiredFields[3]);//venkat updated inner method 21.09.2022
      assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      ExtentReport.logPass("PASS", "test02dUcqcPageVerifyDisabledStateOfApplySelectionsButtonAfterSelectingDepartment");
    } catch (Throwable e) {
    	ExtentReport.logFail("FAIL","test02dUcqcPageVerifyDisabledStateOfApplySelectionsButtonAfterSelectingDepartment", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test03UcqcPageVerifyEnabledStateOfApplySelectionsButtonAfterSelectingResultsStoredFor() throws Throwable {
    try {
      doDropdownSelectUsingOptionText(ucqcMap.getUnitCostQuickCalculationFieldResultsStoredFor(),ucqcMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),requiredFields[4]);
      assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      ExtentReport.logPass("PASS", "test03UcqcPageVerifyEnabledStateOfApplySelectionsButtonAfterSelectingResultsStoredFor");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test03UcqcPageVerifyEnabledStateOfApplySelectionsButtonAfterSelectingResultsStoredFor", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test04UcqcPageUndoCostModelSelectionAndApplySelectionsButtonBecomesDisabled() throws Throwable {
    try {
      waitForAjaxExtJs();
      ucqcPopulateRequiredFieldsToDisplayGrid(requiredFields);
      assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      doDropdownSelectUsingOptionText(ucqcMap.getUnitCostQuickCalculationDropdownCostModel(),ucqcMap.getUnitCostQuickCalculationDropdownCostModelMenuList(),"<None>");
      assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      ExtentReport.logPass("PASS", "test04UcqcPageUndoCostModelSelectionAndApplySelectionsButtonBecomesDisabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test04UcqcPageUndoCostModelSelectionAndApplySelectionsButtonBecomesDisabled", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test11UcqcPageUndoCostModelScenarioSelectionAndApplySelectionsButtonBecomesDisabled() throws Throwable {
    try {
      waitForAjaxExtJs();
      ucqcPopulateRequiredFieldsToDisplayGrid(requiredFields);
      assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      doDropdownSelectUsingOptionText(ucqcMap.getUnitCostQuickCalculationDropdownCostModelScenario(),ucqcMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),"<None>");
      assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      ExtentReport.logPass("PASS", "test11UcqcPageUndoCostModelScenarioSelectionAndApplySelectionsButtonBecomesDisabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test11UcqcPageUndoCostModelScenarioSelectionAndApplySelectionsButtonBecomesDisabled", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test12UcqcPageUndoEntitySelectionAndApplySelectionsButtonBecomesDisabled() throws Throwable {
    try {
      waitForAjaxExtJs();
      ucqcPopulateRequiredFieldsToDisplayGrid(requiredFields);
      assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      doDropdownSelectUsingOptionText(ucqcMap.getUnitCostQuickCalculationDropdownEntity(),ucqcMap.getUnitCostQuickCalculationDropdownEntityMenuList(),"<None>");
      assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      ExtentReport.logPass("PASS", "test12UcqcPageUndoEntitySelectionAndApplySelectionsButtonBecomesDisabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test12UcqcPageUndoEntitySelectionAndApplySelectionsButtonBecomesDisabled", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test13UcqcPageUndoDepartmentSelectionAndApplySelectionsButtonBecomesDisabled() throws Throwable {
    try {
      waitForAjaxExtJs();
      ucqcPopulateRequiredFieldsToDisplayGrid(requiredFields);
      assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      selectDepartment("<None>");
      assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      ExtentReport.logPass("PASS", "test13UcqcPageUndoDepartmentSelectionAndApplySelectionsButtonBecomesDisabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test13UcqcPageUndoDepartmentSelectionAndApplySelectionsButtonBecomesDisabled", driver,e);
      fail(e.getMessage());
      
    }
  }

  @Test
  public void test14UcqcPageUndoResultsStoredForSelectionAndApplySelectionsButtonBecomesDisabled() throws Throwable {
    try {
      waitForAjaxExtJs();
      ucqcPopulateRequiredFieldsToDisplayGrid(requiredFields);
      assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      doDropdownSelectUsingOptionText(ucqcMap.getUnitCostQuickCalculationFieldResultsStoredFor(),ucqcMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"<None>");
      assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      ExtentReport.logPass("PASS", "test14UcqcPageUndoResultsStoredForSelectionAndApplySelectionsButtonBecomesDisabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test14UcqcPageUndoResultsStoredForSelectionAndApplySelectionsButtonBecomesDisabled", driver,e);
      fail(e.getMessage());
    }
  }
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}

}
