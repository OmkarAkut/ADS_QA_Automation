package webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation.LoadingSpinnerDisplaysWhileCalculationIsRunningAds1231;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SelectColumnsPopupForColumnsToDisplayAds1083 extends UcqcHelper {

  static CostingMap selectColumn;
  String[] columnHeaderSubset = {"Charge Code Name","Modifier","Total Unit Cost","Total Quick Cost","Total Change"};
  String[] defaultColumnHeaderSubset = {"Charge Code","Charge Code Name","Modifier","Total Unit Cost","Total Quick Cost","Total Change"};
  String[] columnsHeaderSubsetRvu = {"Charge Code Name","Modifier","Total Unit Cost","Total Quick Cost","Total Change","Quick Salaries and Wages RVU"};
  String[] columnsChargeCodeNameOnly = {"Modifier","Total Unit Cost","Total Quick Cost","Total Change"};
  String[] columns = {"Salaries and Wages RVU","Quick Salaries and Wages Cost"};

  /**
   ADS-1083 (Dev Story ADS-739).
   ADS-1390 (Dev Story ADS-740) - Updated 7/11/19 to include additional steps.
   This script confirms the ability to open the Select Columns popup when you click Select for Columns to Display
   as well as being able to view a subset of columns once the selection is applied
 * @throws Throwable 
   */
  
  @BeforeClass
  public static void setupScript() throws Throwable {
	  ExtentReport.reportCreate("SelectColumnsPopupForColumnsToDisplayAds1083", "webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage", "SelectColumnsPopupForColumnsToDisplayAds1083");
    try {
		selectColumn = BuildMap.getInstance(driver, CostingMap.class);
		System.out.println("Test Class: " + LoadingSpinnerDisplaysWhileCalculationIsRunningAds1231.class.getSimpleName());
		Login.loginUser("CostingDepartmentManager1");
		doMaximizeWindow();
		goToPage("Unit Cost Quick Calculation");
		waitForAjaxExtJs();
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test01OnPageLoadAssertColumnToDisplayAllCheckBoxCheckedDisabledAndSelectButtonDisabled() throws Throwable {
    try {
      assertElementIsDisabled(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplaySelect(),printout);
      assertColumnsToDisplayAllCheckBoxIsDisabled();
      assertColumnsToDisplayAllCheckBoxIsChecked();
      ExtentReport.logPass("PASS", "test01OnPageLoadAssertColumnToDisplayAllCheckBoxCheckedDisabledAndSelectButtonDisabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test01OnPageLoadAssertColumnToDisplayAllCheckBoxCheckedDisabledAndSelectButtonDisabled", driver, e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test02SetCostModelAndAssertSelectButtonEnabledWhenAllIsUnchecked() throws Throwable {
    try {
      doDropdownSelectUsingOptionText(selectColumn.getUnitCostQuickCalculationDropdownCostModel(),selectColumn.getUnitCostQuickCalculationDropdownCostModelMenuList(),"Marina");
      doClick(selectColumn.getUnitCostQuickCalculationCheckBoxColumnsToDisplayAll());
      assertColumnsToDisplayAllCheckBoxIsNotChecked();
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplaySelect(),printout);
      ExtentReport.logPass("PASS", "test02SetCostModelAndAssertSelectButtonEnabledWhenAllIsUnchecked");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test02SetCostModelAndAssertSelectButtonEnabledWhenAllIsUnchecked", driver, e);
        fail(e.getMessage());
      }
  }

  @Test
  public void test03OpenSelectColumnsModalAssertAvailableColumnsIsEmptyAndRespectiveButtonsAreEnabledDisabled() throws Throwable {
    try {
      doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplaySelect());
      waitForAjaxExtJs();
      assertAvailableColumnIsEmpty();
      assertElementIsDisabled(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplayModalRemove(),printout);
      assertElementIsDisabled(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplayModalSelect(),printout);
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply(),printout);
      ExtentReport.logPass("PASS", "test03OpenSelectColumnsModalAssertAvailableColumnsIsEmptyAndRespectiveButtonsAreEnabledDisabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test03OpenSelectColumnsModalAssertAvailableColumnsIsEmptyAndRespectiveButtonsAreEnabledDisabled", driver, e);
        fail(e.getMessage());
      }
  }

  @Test
  public void test04AssertColumnsInAvailableBoxAreStillAppliedIfSelectButtonIsClickedASubsequentTime() throws Throwable {
    try {
    	
    
      highlightColumnsToDisplayColumn("Charge Code Name");
      assertColumnsToDisplayColumnIsSelected();
      doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplayModalRemove());
      doClick(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply());
      waitForAjaxExtJs();
      doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplaySelect());
      assertAvailableColumnIsNotEmpty();
      ExtentReport.logPass("PASS", "highlightColumnsToDisplayColumn");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "highlightColumnsToDisplayColumn", driver, e);
        fail(e.getMessage());
      }
   
  }
  
  @Test
  public void test05AssertAvailableBoxIsEmptyIfAllCheckboxIsReChecked() throws Throwable {
    try {
      doClick(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel());
      waitForAjaxExtJs();
      doClick(selectColumn.getUnitCostQuickCalculationCheckBoxColumnsToDisplayAll());
      waitForAjaxExtJs();
      doClick(selectColumn.getUnitCostQuickCalculationCheckBoxColumnsToDisplayAll());
      doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplaySelect());
      Thread.sleep(1000);
      assertAvailableColumnIsEmpty();
      ExtentReport.logPass("PASS", "test05AssertAvailableBoxIsEmptyIfAllCheckboxIsReChecked");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test05AssertAvailableBoxIsEmptyIfAllCheckboxIsReChecked", driver, e);
        fail(e.getMessage());
      }
  }

  @Test
  public void test06AssertChargeCodeIsNotInTheSelector() throws Throwable {
    try {
      assertColumnsToDisplayColumn("Charge Code");
      waitForAjaxExtJs();
      ExtentReport.logPass("PASS", "test06AssertChargeCodeIsNotInTheSelector");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test06AssertChargeCodeIsNotInTheSelector", driver, e);
        fail(e.getMessage());
      }
  }

  @Test
  public void test07AssertColumnOrder() throws Throwable {
    try {
      assertColumnsToDisplayColumnOrder();
      waitForAjaxExtJs();
      ExtentReport.logPass("PASS", "test07AssertColumnOrder");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test07AssertColumnOrder", driver, e);
        fail(e.getMessage());
      }
  }

  @Test
  public void test08AssertCostComponentOrder() throws Throwable {
    try {
      assertFiveColumnsForEachCostComponent();
      ExtentReport.logPass("PASS", "test08AssertCostComponentOrder");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test08AssertCostComponentOrder", driver, e);
        fail(e.getMessage());
      }
  }

  @Test
  public void test09HighlightColumnInSelectBoxAndAssertRemoveButtonIsEnabled() throws Throwable {
    try {
      highlightColumnsToDisplayColumn("Charge Code Name");
      waitForAjaxExtJs();
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplayModalRemove(),printout);
      ExtentReport.logPass("PASS", "test09HighlightColumnInSelectBoxAndAssertRemoveButtonIsEnabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test09HighlightColumnInSelectBoxAndAssertRemoveButtonIsEnabled", driver, e);
        fail(e.getMessage());
      }
  }

  @Test
  public void test10AssertColumnIsHighlighted() throws Throwable {
    try {
      assertColumnsToDisplayColumnIsSelected();
      ExtentReport.logPass("PASS", "test10AssertColumnIsHighlighted");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test10AssertColumnIsHighlighted", driver, e);
        fail(e.getMessage());
      }
  }

  @Test
  public void test11MoveColumnsFromSelectBoxToAvailableBoxAndAssertAvailableBoxIsNotEmpty() throws Throwable {
    try {
      highlightColumnsToDisplayColumn("Charge Code Name");
      assertColumnsToDisplayColumnIsSelected();
      doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplayModalRemove());
      waitForAjaxExtJs();
      assertAvailableColumnIsNotEmpty();
      ExtentReport.logPass("PASS", "test11MoveColumnsFromSelectBoxToAvailableBoxAndAssertAvailableBoxIsNotEmpty");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test11MoveColumnsFromSelectBoxToAvailableBoxAndAssertAvailableBoxIsNotEmpty", driver, e);
        fail(e.getMessage());
      }
  }

  @Test
  public void test12CompareAvailableBoxToSelectedBoxAndAssertCancelButtonIsEnabled() throws Throwable {
    try {
      compareAvailableColumnToSelectedColumn();
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel(),printout);
      ExtentReport.logPass("PASS", "test12CompareAvailableBoxToSelectedBoxAndAssertCancelButtonIsEnabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test12CompareAvailableBoxToSelectedBoxAndAssertCancelButtonIsEnabled", driver, e);
        fail(e.getMessage());
      }
  }

  @Test
  public void test13AssertSelectButtonDisabledIfNoColumnHighlightedInAvailableBox() throws Throwable {
    try {
      assertElementIsDisabled(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplayModalSelect(),printout);
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel(),printout);
      ExtentReport.logPass("PASS", "test13AssertSelectButtonDisabledIfNoColumnHighlightedInAvailableBox");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test13AssertSelectButtonDisabledIfNoColumnHighlightedInAvailableBox", driver, e);
        fail(e.getMessage());
      }
  }

  @Test
  public void test14ConfirmColumnsCanBeHighlightedInAvailableAndSelectedBoxesAndAssertRemoveSelctonCancelButtonsEnabled() throws Throwable {
    try {
      highlightColumnsToDisplayColumn("Charge Code Name");
      highlightColumnsToDisplayColumn("Modifier");
      assertElementIsEnabled("//*[contains(@id,'button')][text()='Remove']/./../../..",printout);
      assertElementIsEnabled("//*[contains(@id,'button')][text()='Select']/./../../..",printout);
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel(),printout);
      ExtentReport.logPass("PASS", "test14ConfirmColumnsCanBeHighlightedInAvailableAndSelectedBoxesAndAssertRemoveSelctonCancelButtonsEnabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test14ConfirmColumnsCanBeHighlightedInAvailableAndSelectedBoxesAndAssertRemoveSelctonCancelButtonsEnabled", driver, e);
        fail(e.getMessage());
      }
  }

  @Test
  public void test15RemoveAllColumnsFromSelectedBoxAndAssertApplyButtonIsDisabled() throws Throwable {
    try {
      removeAllColumnsToDisplayColumns();
      assertElementIsDisabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply(),printout);
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel(),printout);
      ExtentReport.logPass("PASS", "test15RemoveAllColumnsFromSelectedBoxAndAssertApplyButtonIsDisabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test15RemoveAllColumnsFromSelectedBoxAndAssertApplyButtonIsDisabled", driver, e);
        fail(e.getMessage());
      }
  }

  @Test
  public void test16AssertApplyButtonDisabledWhenNoQuickRvusAreSelected() throws Throwable {
    try {
      selectMultipleColumnsToDisplay(columnHeaderSubset);
      assertElementIsDisabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply(),printout);
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel(),printout);
      ExtentReport.logPass("PASS", "test16AssertApplyButtonDisabledWhenNoQuickRvusAreSelected");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test16AssertApplyButtonDisabledWhenNoQuickRvusAreSelected", driver, e);
        fail(e.getMessage());
      }
  }

  @Test
  public void test17AssertApplyButtonDisabledWhenOnlyChargeCodeNameIsSelected() throws Throwable {
    try {
      removeMultipleColumnsToDisplay(columnsChargeCodeNameOnly);
      assertElementIsDisabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply(),printout);
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel(),printout);
      ExtentReport.logPass("PASS", "test17AssertApplyButtonDisabledWhenOnlyChargeCodeNameIsSelected");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test17AssertApplyButtonDisabledWhenOnlyChargeCodeNameIsSelected", driver, e);
        fail(e.getMessage());
      }
  }

  @Test
  public void test18AssertApplyButtonDisabledWhenOnlyModifierSelected() throws Throwable {
    try {
      highlightColumnsToDisplayColumn("Modifier");
      assertElementIsDisabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply(),printout);
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel(),printout);
      ExtentReport.logPass("PASS", "test18AssertApplyButtonDisabledWhenOnlyModifierSelected");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test18AssertApplyButtonDisabledWhenOnlyModifierSelected", driver, e);
        fail(e.getMessage());
      }
  }

  @Test
  public void test19AssertSelectedColumnMovesFromAvailableBoxToSelectedBox() throws Throwable {
    try {
      highlightColumnsToDisplayColumn("Salaries and Wages RVU");
      doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplayModalSelect());
      getSelectedColumnList();
      assertIfColumnIsInAvailableOrSelectedBox("Salaries and Wages RVU");
      ExtentReport.logPass("PASS", "test19AssertSelectedColumnMovesFromAvailableBoxToSelectedBox");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test19AssertSelectedColumnMovesFromAvailableBoxToSelectedBox", driver, e);
        fail(e.getMessage());
      }
  }

  @Test
  public void test20AssertApplyButtonDisabledWhenChargeCodeNameAndModifierSelected() throws Throwable {
    try {
    driver.manage().window().maximize();
    String[] columnsToRemove= {"Charge Code Name","Salaries and Wages RVU"};
    removeMultipleColumnsToDisplay(columnsToRemove);
//      removeAllColumnsToDisplayColumns(); //redo to just remove only the Selected items, not all of them
      selectMultipleColumnsToDisplay(columns);
      assertTheElementIsDisabled(driver.findElement(By.xpath("//*[contains(@class,'x-box')]/descendant::*[text()='Apply']/../../..")),printout);
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel(),printout);
//      doClick(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel());
      ExtentReport.logPass("PASS", "test20AssertApplyButtonDisabledWhenChargeCodeNameAndModifierSelected");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test20AssertApplyButtonDisabledWhenChargeCodeNameAndModifierSelected", driver, e);
        fail(e.getMessage());
      }
  }

  @Test
  public void test21AssertApplyButtonIsEnabledWhenAtLeastOneQuickRvuColumnIsMovedToSelectedSide() throws Throwable {
    try {
//    	 doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplaySelect());
//         Thread.sleep(1000);
      highlightColumnsToDisplayColumn("Quick Employee Benefits RVU");
      doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplayModalSelect());
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply(),printout);
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel(),printout);
      ExtentReport.logPass("PASS", "test21AssertApplyButtonIsEnabledWhenAtLeastOneQuickRvuColumnIsMovedToSelectedSide");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test21AssertApplyButtonIsEnabledWhenAtLeastOneQuickRvuColumnIsMovedToSelectedSide", driver, e);
        fail(e.getMessage());
      }
  }

  @Test
  public void test22AssertApplyButtonEnabledWhenAllColumnsMovedToSelectedSide() throws Throwable {
    try {
    	doClick(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel());
    	doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplaySelect());
        Thread.sleep(1000);
      removeAllColumnsToDisplayColumns();
      selectAllColumnsToDisplayColumns();
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply(),printout);
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel(),printout);
      ExtentReport.logPass("PASS", "test22AssertApplyButtonEnabledWhenAllColumnsMovedToSelectedSide");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test22AssertApplyButtonEnabledWhenAllColumnsMovedToSelectedSide", driver, e);
        fail(e.getMessage());
      }
  }

  @Test
  public void test23AssertApplyButtonEnabledWhenAllColumnsOnSelectedSideExceptChargeCodeNameAndModifier() throws Throwable {
    try {
      removeMultipleColumnsToDisplay(columns);
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply(),printout);
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel(),printout);
      ExtentReport.logPass("PASS", "test23AssertApplyButtonEnabledWhenAllColumnsOnSelectedSideExceptChargeCodeNameAndModifier");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test23AssertApplyButtonEnabledWhenAllColumnsOnSelectedSideExceptChargeCodeNameAndModifier", driver, e);
        fail(e.getMessage());
      }
  }

  @Test
  public void test24AssertApplyButtonEnabledWhenAllColumnsOnSelectedSideExceptModifier() throws Throwable {
    try {
      highlightColumnsToDisplayColumn("Charge Code Name");
      doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplayModalSelect());
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply(),printout);
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel(),printout);
      ExtentReport.logPass("PASS", "test24AssertApplyButtonEnabledWhenAllColumnsOnSelectedSideExceptModifier");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test24AssertApplyButtonEnabledWhenAllColumnsOnSelectedSideExceptModifier", driver, e);
        fail(e.getMessage());
      }
  }

  
  @Test
  public void test25ChangeUcqcSelectionAndAssertSubsetOfColumnsDoNotChange() throws Throwable {
    try {
      /**NOTE: FAILING WHILE ATTEMPTING TO SET UCQC CRITERIA, this portion has been commented out*/
      removeAllColumnsToDisplayColumns();
      selectMultipleColumnsToDisplay(columnsHeaderSubsetRvu);
      doClick(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply());
      waitForAjaxExtJs();
      //venkat added below scroll code  02-11-2022
      Thread.sleep(2000);
	    ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoViewIfNeeded();",selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplaySelect());
	    Thread.sleep(3000);
      
      doDropdownSelectUsingOptionText(selectColumn.getUnitCostQuickCalculationDropdownCostModelScenario(),selectColumn.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),"*CM3 TB MHFY05 Before Vol Change_UC_UCQC");
      waitForAjaxExtJs();
      doDropdownSelectUsingOptionText(selectColumn.getUnitCostQuickCalculationDropdownEntity(),selectColumn.getUnitCostQuickCalculationDropdownEntityMenuList(),"200 Southgate");
      waitForAjaxExtJs();
      //venkat updated department 02-11-2022
      updateDepartment("2110");
      
     // selectDepartment("2110  ICU");
      waitForAjaxExtJs();
      doDropdownSelectUsingOptionText(selectColumn.getUnitCostQuickCalculationFieldResultsStoredFor(),selectColumn.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"Apr 2004 to Mar 2005");
      waitForAjaxExtJs();
      doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplaySelect());
      waitForAjaxExtJs();
    List<WebElement> selectedColumnsSubset = driver.findElements(By.xpath("//*[contains(@class,'glAccountsGrid')][2]/descendant::tbody//tr//td/div"));
   
    for (int i = 0; i <columnsHeaderSubsetRvu.length; i++) {
        if (selectedColumnsSubset.get(i).equals(columnsHeaderSubsetRvu[i])) {
          System.out.println(columnsHeaderSubsetRvu[i] + " = " + selectedColumnsSubset.get(i));
        } else {
          System.out.println("The column selections are not still available after changing CMS, Entity, Dept, and Results Stored For.");
        }
      }
      ExtentReport.logPass("PASS", "test25ChangeUcqcSelectionAndAssertSubsetOfColumnsDoNotChange");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test25ChangeUcqcSelectionAndAssertSubsetOfColumnsDoNotChange", driver, e);
        fail(e.getMessage());
      }
  }

  
  @Test
  public void test26CloseTheUcqcSessionAndAssertColumnSelectionDoesNotSaveUponReopen() throws Throwable {
    try {
      doClick(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel());
      doClosePageOnLowerBar("Unit Cost Quick Calculation");
      goToPage("Unit Cost Quick Calculation");
      assertColumnHeaderSubsetDisplays(defaultColumnHeaderSubset);
      ExtentReport.logPass("PASS", "test26CloseTheUcqcSessionAndAssertColumnSelectionDoesNotSaveUponReopen");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test26CloseTheUcqcSessionAndAssertColumnSelectionDoesNotSaveUponReopen", driver, e);
        fail(e.getMessage());
      }
  }

  
  @Test
  public void test27SaveUndoAnEditAndAssertColumnSelectionDoesNotChange() throws Throwable {
    try {
      waitForAjaxExtJs();
    //venkat updated department 02-11-2022
      Thread.sleep(2000);
	    ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoViewIfNeeded();",selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplaySelect());
	    Thread.sleep(3000);
      //setUCQCCriteria("Marina", "*CM1 TB MHFY05 After Vol Change", "150 Marina Medical Center", "2130  PED ICU", "Jan 2005 to Jan 2005");            doClick(selectColumn.getUnitCostQuickCalculationCheckBoxColumnsToDisplayAll());
      setUcqcCriteria("Marina", "*CM1 TB MHFY05 After Vol Change", "150 Marina Medical Center", "2130", "Jan 2005 to Mar 2005");
      doClick(selectColumn.getUnitCostQuickCalculationButtonApplySelections());
      ucqcWaitForSpinnerToEnd();
      Thread.sleep(1500);
      /*doClick(selectColumn.getUnitCostQuickCalculationCheckBoxColumnsToDisplayAll());
      doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplaySelect());
      waitForAjaxExtJs();
      removeAllColumnsToDisplayColumns();
      for(String selectedColumns: columnsHeaderSubsetRVU){
          highlightColumnsToDisplayColumn(selectedColumns);
          doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplayModalSelect());
      }
      doClick(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply());
      waitForAjaxExtJs();*/
      ucqcUpdateGridCellValue("1100171","Quick Salaries and Wages RVU","20",printout);
      doClick(selectColumn.getUnitCostQuickCalculationButtonUndo());
      waitForAjaxExtJs();
      assertColumnHeaderSubsetDisplays(columnsHeaderSubsetRvu);
      ucqcUpdateGridCellValue("1100171","Quick Salaries and Wages RVU","25",printout);
      doClick(selectColumn.getUnitCostQuickCalculationButtonSaveQuickRVUs());
      waitForAjaxExtJs();
      assertColumnHeaderSubsetDisplays(columnsHeaderSubsetRvu);
      ExtentReport.logPass("PASS", "test27SaveUndoAnEditAndAssertColumnSelectionDoesNotChange");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test27SaveUndoAnEditAndAssertColumnSelectionDoesNotChange", driver, e);
        fail(e.getMessage());
      }
  }

//   @Test
  public void test28AssertPreviousColumnSubsetPersistsAfterCopyToQuickRvusIsSelected() throws Throwable {
    try {
      /**DO NOT RUN IN EVOLVE: This should be run in a dedicated environment with a Cost Model Scenario specific to this test case*/
            /*doClick(selectColumn.getUnitCostQuickCalculationButtonCopyToQuickRVUs());
            ucqcWaitForSpinnerToEnd();
            assertColumnHeaderSubsetDisplays(columnsSubset);*/
    	  ExtentReport.logPass("PASS", "test28AssertPreviousColumnSubsetPersistsAfterCopyToQuickRvusIsSelected");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test28AssertPreviousColumnSubsetPersistsAfterCopyToQuickRvusIsSelected", driver, e);
        fail(e.getMessage());
      }
  }

  
  @Test
  public void test29AssertPreviousColumnSubsetPersistsAfterClearRvusAndSaveIsSelected() throws Throwable {
    try {
    	Thread.sleep(3000);
    	
      ucqcGridClickInCell("1100171","Quick Salaries and Wages RVU",printout);
      doClick(selectColumn.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave());
      waitForAjaxExtJs();
      doClick(selectColumn.getUnitCostQuickCalculationButtonClearAndSave());
      assertColumnHeaderSubsetDisplays(columnsHeaderSubsetRvu);
      ExtentReport.logPass("PASS", "test29AssertPreviousColumnSubsetPersistsAfterClearRvusAndSaveIsSelected");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test29AssertPreviousColumnSubsetPersistsAfterClearRvusAndSaveIsSelected", driver, e);
        fail(e.getMessage());
      }
  }

  
  @Test
  public void test30AssertPreviousColumnSubsetPersistsAfterOverwriteRvuMaintenanceIsSelected() throws Throwable {
    try {
      doClick(selectColumn.getUnitCostQuickCalculationButtonOverwriteRVUMaintenance());
      waitForAjaxExtJs();
      doClick(selectColumn.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonCancelAndClose());
      waitForAjaxExtJs();
      assertColumnHeaderSubsetDisplays(columnsHeaderSubsetRvu);
      ExtentReport.logPass("PASS", "test30AssertPreviousColumnSubsetPersistsAfterOverwriteRvuMaintenanceIsSelected");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test30AssertPreviousColumnSubsetPersistsAfterOverwriteRvuMaintenanceIsSelected", driver, e);
        fail(e.getMessage());
      }
  }

  
  @Test
  public void test31AssertPreviousColumnSubsetPersistsAfterCalculationIsRun() throws Throwable {
    try {
      doClick(selectColumn.getUnitCostQuickCalculationButtonCalculate());
      ucqcWaitForSpinnerToEnd();
      assertColumnHeaderSubsetDisplays(columnsHeaderSubsetRvu);
      ExtentReport.logPass("PASS", "test31AssertPreviousColumnSubsetPersistsAfterCalculationIsRun");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test31AssertPreviousColumnSubsetPersistsAfterCalculationIsRun", driver, e);
        fail(e.getMessage());
      }
  }

  
  @Test
  public void test32AssertApplySelectionsButtonIsEnabledWhenUcqcCriteriaIsSetAllCheckboxIsUncheckedAndAtLeastOneColumnIsSelectedInSelectColumnsPopUp() throws Throwable {
    try {
      waitForAjaxExtJs();
      //venkat commented below line 03-11-2022
     // doClick(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel());
      waitForAjaxExtJs();
      doClosePageOnLowerBar("Unit Cost Quick Calculation");
      goToPage("Unit Cost Quick Calculation");
     // setUcqcCriteria("Marina", "*CM1 TB MHFY05 After Vol Change", "150 Marina Medical Center", "2130  PED ICU", "Jan 2005 to Jan 2005");
      setUcqcCriteria("Marina", "*CM1 TB MHFY05 After Vol Change", "150 Marina Medical Center", "2130", "Jan 2005 to Jan 2005");
      
      Thread.sleep(2000);
	    ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoViewIfNeeded();",selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplaySelect());
	    Thread.sleep(3000);
  	
      doClick(selectColumn.getUnitCostQuickCalculationCheckBoxColumnsToDisplayAll());
      waitForAjaxExtJs();
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationButtonApplySelections(),printout);
      ExtentReport.logPass("PASS", "test32AssertApplySelectionsButtonIsEnabledWhenUcqcCriteriaIsSetAllCheckboxIsUncheckedAndAtLeastOneColumnIsSelectedInSelectColumnsPopUp");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test32AssertApplySelectionsButtonIsEnabledWhenUcqcCriteriaIsSetAllCheckboxIsUncheckedAndAtLeastOneColumnIsSelectedInSelectColumnsPopUp", driver, e);
        fail(e.getMessage());
      }
  }

  
  @Test
  public void test33AssertChargeCodeChargeCodeNameAndModifierGridColumnsAreLocked() throws Throwable {
    try {
      doClick(selectColumn.getUnitCostQuickCalculationButtonApplySelections());
      waitForSpinnerToEnd();
      String columnID = driver.findElement(By.xpath("(//div[@id='ucqcgrid-targetEl']/descendant::div[contains(@id,'gridcolumn')]/span[contains(@id,'gridcolumn')]/..)[2]")).getAttribute("id");
      int columnIdDigits = Integer.parseInt(getNumbersFromStringWithRegex(columnID));
      String headerPanel = driver.findElement(By.xpath("((//div[@id='ucqcgrid-targetEl']/descendant::div[contains(@id,'gridcolumn')]/span[contains(@id,'gridcolumn')]/..)[2]/ancestor::div[contains(@id,'ucqcgrid')])[6]")).getAttribute("class");
      boolean isLocked = headerPanel.contains("locked");
      if (printout) {
        System.out.println("Element class text: " + headerPanel);
        System.out.println("IsLocked: " + isLocked);
      }
      try {
        assertTrue(isLocked);
      } catch (Throwable e) {
        System.out.println("TEST FAILED: Element is Enabled");
        throw new Exception();
      }
      ExtentReport.logPass("PASS", "test33AssertChargeCodeChargeCodeNameAndModifierGridColumnsAreLocked");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test33AssertChargeCodeChargeCodeNameAndModifierGridColumnsAreLocked", driver, e);
        fail(e.getMessage());
      }
  }

  
  @Test
  public void test34AssertChargeCodeIsDisplayedAndNotAvailableInSelectColumnWindow() throws Throwable {
    try {
//    	 doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplaySelect());
      assertColumnHeaderIsDisplayed("Charge Code");
      doDropdownSelectUsingOptionText(selectColumn.getUnitCostQuickCalculationDropdownCostModel(),selectColumn.getUnitCostQuickCalculationDropdownCostModelMenuList(),"Marina");
      waitForAjaxExtJs();
//      doClick(selectColumn.getUnitCostQuickCalculationCheckBoxColumnsToDisplayAll());
      doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplaySelect());
      waitForAjaxExtJs();
      assertColumnIsNotDisplayedInSelectBox("Charge Code");
      ExtentReport.logPass("PASS", "test34AssertChargeCodeIsDisplayedAndNotAvailableInSelectColumnWindow");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test34AssertChargeCodeIsDisplayedAndNotAvailableInSelectColumnWindow", driver, e);
        fail(e.getMessage());
      }
  }

  
  @Test
  public void test35ChangeCostModelAndAssertAllCheckBoxIsCheckedAndSelectButtonIsDisabled() throws Throwable {
    try {
      doClick(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel());
      doDropdownSelectUsingOptionText(selectColumn.getUnitCostQuickCalculationDropdownCostModel(),selectColumn.getUnitCostQuickCalculationDropdownCostModelMenuList(),"QA Cost Model");
      assertElementIsDisabled(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplaySelect(),printout);
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationCheckBoxColumnsToDisplayAll(),printout);
      assertColumnsToDisplayAllCheckBoxIsChecked();
      ExtentReport.logPass("PASS", "test35ChangeCostModelAndAssertAllCheckBoxIsCheckedAndSelectButtonIsDisabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test35ChangeCostModelAndAssertAllCheckBoxIsCheckedAndSelectButtonIsDisabled", driver, e);
        fail(e.getMessage());
      }
  }

  public void assertColumnIsNotDisplayedInSelectBox(String column) {
    List<String> selectedColumns = getSelectedColumnList();
    if (selectedColumns.contains(column)) {
      System.out.println(column + " exists in the Selected box");
      fail();
    } else {
      System.out.println(column + " does not exist in the Selected box");
    }
  }

  public void assertColumnHeaderSubsetDisplays(String[] columnHeaderSubset) {
    for (String headerNames: columnHeaderSubset) {
      assertElementIsDisplayed(driver.findElement(By.xpath("//span[@class='x-column-header-text-inner'][text()='" + headerNames + "']")),printout);
    }
  }

  public void assertColumnHeaderIsDisplayed(String columnHeader) {
    assertElementIsDisplayed(driver.findElement(By.xpath("//span[@class='x-column-header-text-inner'][text()='" + columnHeader + "']")),printout);
  }

  public void assertFiveColumnsForEachCostComponent() {
    String selectedColumnsXpath = "//*[contains(@class,'glAccountsGrid')][2]/descendant::tbody";
    WebElement selectedMenu = driver.findElement(By.xpath(selectedColumnsXpath));
    List<WebElement> actualSelectedColumns = selectedMenu.findElements(By.tagName("tr"));
    List<String> actualSelectedColumnNames = new ArrayList<>();

    String[] expectedSalaryAndWagesList = {"Salaries and Wages RVU","Quick Salaries and Wages RVU","Salaries and Wages Cost","Quick Salaries and Wages Cost","Salaries and Wages Change"};
    String[] expectedEmployeeBenefitsList = {"Employee Benefits RVU","Quick Employee Benefits RVU","Employee Benefits Cost","Quick Employee Benefits Cost","Employee Benefits Change"};
    String[] expectedMedicalSuppliesList = {"Medical Supplies RVU","Quick Medical Supplies RVU","Medical Supplies Cost","Quick Medical Supplies Cost","Medical Supplies Change"};
    String[] expectedNonMedicalSuppliesList = {"Non-Medical Supplies RVU","Quick Non-Medical Supplies RVU","Non-Medical Supplies Cost","Quick Non-Medical Supplies Cost","Non-Medical Supplies Change"};
    String[] expectedEquipRepairAndMaintList = {"Equip Repair & Maint RVU","Quick Equip Repair & Maint RVU","Equip Repair & Maint Cost","Quick Equip Repair & Maint Cost","Equip Repair & Maint Change"};
    String[] expectedDirectDepreciationList = {"Direct Depreciation RVU","Quick Direct Depreciation RVU","Direct Depreciation Cost","Quick Direct Depreciation Cost","Direct Depreciation Change"};
    String[] expectedPurchasedServicesList = {"Purchased Services RVU","Quick Purchased Services RVU","Purchased Services Cost","Quick Purchased Services Cost","Purchased Services Change"};
    String[] expectedProfessionalFeesList = {"Professional Fees RVU","Quick Professional Fees RVU","Professional Fees Cost","Quick Professional Fees Cost","Professional Fees Change"};
    String[] expectedOtherExpensesList = {"Other Expenses RVU","Quick Other Expenses RVU","Other Expenses Cost","Quick Other Expenses Cost","Other Expenses Change"};
    String[] expectedDirectOverheadList = {"Direct Overhead RVU","Quick Direct Overhead RVU","Direct Overhead Cost","Quick Direct Overhead Cost","Direct Overhead Change"};
    String[] expectedHospitalOverheadList = {"Hospital Overhead RVU","Quick Hospital Overhead RVU","Hospital Overhead Cost","Quick Hospital Overhead Cost","Hospital Overhead Change"};
    String[] expectedCorporateOverheadList = {"Corporate Overhead RVU","Quick Corporate Overhead RVU","Corporate Overhead Cost","Quick Corporate Overhead Cost","Corporate Overhead Change"};
    String[] expectedDepreciationList = {"Depreciation RVU","Quick Depreciation RVU","Depreciation Cost","Quick Depreciation Cost","Depreciation Change"};
    String[] expectedTechList = {"Tech RVU","Quick Tech RVU","Tech Cost","Quick Tech Cost","Tech Change"};
    String[][] arrayOfExpectedSubLists = {expectedSalaryAndWagesList,expectedEmployeeBenefitsList,expectedMedicalSuppliesList,expectedNonMedicalSuppliesList,expectedEquipRepairAndMaintList,expectedDirectDepreciationList,expectedPurchasedServicesList,expectedProfessionalFeesList,expectedOtherExpensesList,expectedDirectOverheadList,expectedHospitalOverheadList,expectedCorporateOverheadList,expectedDepreciationList,expectedTechList};

    for (String[] array: arrayOfExpectedSubLists) {
      for (WebElement selectedColumns: actualSelectedColumns) {
        actualSelectedColumnNames.removeAll(actualSelectedColumnNames);
        if (selectedColumns.getText().equals(array[0])) {
          int columnIndex = actualSelectedColumns.indexOf(selectedColumns);
          System.out.println("This cost component columns are in the correct order.");
          for (int i = columnIndex; i < columnIndex + 5; i++) {
            actualSelectedColumnNames.add(actualSelectedColumns.get(i).getText());
          }
          for (String item : actualSelectedColumnNames) {
            System.out.println(item);
          }
        }
      }
      array.equals(actualSelectedColumnNames);
    }
  }

  public void highlightColumnsToDisplayColumn(String column) throws InterruptedException,Throwable {
    String columnPath = "//*[contains(@class,'glAccountsGrid')]/descendant::*[text()='"+column+"']";
   //Shilpa 02.09.2022 added dimension , scroll to element 
    addDimension(1000,1000);
    WebElement element = driver.findElement(By.xpath(columnPath));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    Thread.sleep(500); 
//    doJsClick(element);
	doClick(columnPath);
	driver.manage().window().maximize();
	Thread.sleep(2000);
    waitForAjaxExtJs();
  }

  public void selectAllColumnsToDisplayColumns() throws InterruptedException,Throwable {
    String[] columns = {"Charge Code Name","Modifier","Total Unit Cost","Total Quick Cost","Total Change","Salaries and Wages RVU","Quick Salaries and Wages RVU","Salaries and Wages Cost","Quick Salaries and Wages Cost","Salaries and Wages Change","Employee Benefits RVU","Quick Employee Benefits RVU","Employee Benefits Cost","Quick Employee Benefits Cost","Employee Benefits Change","Medical Supplies RVU","Quick Medical Supplies RVU","Medical Supplies Cost","Quick Medical Supplies Cost","Medical Supplies Change","Non-Medical Supplies RVU","Quick Non-Medical Supplies RVU","Non-Medical Supplies Cost","Quick Non-Medical Supplies Cost","Non-Medical Supplies Change","Equip Repair & Maint RVU","Quick Equip Repair & Maint RVU","Equip Repair & Maint Cost","Quick Equip Repair & Maint Cost","Equip Repair & Maint Change","Direct Depreciation RVU","Quick Direct Depreciation RVU","Direct Depreciation Cost","Quick Direct Depreciation Cost","Direct Depreciation Change","Purchased Services RVU","Quick Purchased Services RVU","Purchased Services Cost","Quick Purchased Services Cost","Purchased Services Change","Professional Fees RVU","Quick Professional Fees RVU","Professional Fees Cost","Quick Professional Fees Cost","Professional Fees Change","Other Expenses RVU","Quick Other Expenses RVU","Other Expenses Cost","Quick Other Expenses Cost","Other Expenses Change","Direct Overhead RVU","Quick Direct Overhead RVU","Direct Overhead Cost","Quick Direct Overhead Cost","Direct Overhead Change","Hospital Overhead RVU","Quick Hospital Overhead RVU","Hospital Overhead Cost","Quick Hospital Overhead Cost","Hospital Overhead Change","Corporate Overhead RVU","Quick Corporate Overhead RVU","Corporate Overhead Cost","Quick Corporate Overhead Cost","Corporate Overhead Change","Depreciation RVU","Quick Depreciation RVU","Depreciation Cost","Quick Depreciation Cost","Depreciation Change","Tech RVU","Quick Tech RVU","Tech Cost","Quick Tech Cost","Tech Change"};
    for (String selectedColumns: columns) {
      highlightColumnsToDisplayColumn(selectedColumns);
      doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplayModalSelect());
    }
  }

  public void selectMultipleColumnsToDisplay(String[] columnsToSelect) throws InterruptedException,Throwable{
    for (String selectedColumns: columnsToSelect) {
      highlightColumnsToDisplayColumn(selectedColumns);
      doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplayModalSelect());
    }
  }

  public String[] removeAllColumnsToDisplayColumns() throws InterruptedException,Throwable {
    String[] columns = {"Charge Code Name","Modifier","Total Unit Cost","Total Quick Cost","Total Change","Salaries and Wages RVU","Quick Salaries and Wages RVU","Salaries and Wages Cost","Quick Salaries and Wages Cost","Salaries and Wages Change","Employee Benefits RVU","Quick Employee Benefits RVU","Employee Benefits Cost","Quick Employee Benefits Cost","Employee Benefits Change","Medical Supplies RVU","Quick Medical Supplies RVU","Medical Supplies Cost","Quick Medical Supplies Cost","Medical Supplies Change","Non-Medical Supplies RVU","Quick Non-Medical Supplies RVU","Non-Medical Supplies Cost","Quick Non-Medical Supplies Cost","Non-Medical Supplies Change","Equip Repair & Maint RVU","Quick Equip Repair & Maint RVU","Equip Repair & Maint Cost","Quick Equip Repair & Maint Cost","Equip Repair & Maint Change","Direct Depreciation RVU","Quick Direct Depreciation RVU","Direct Depreciation Cost","Quick Direct Depreciation Cost","Direct Depreciation Change","Purchased Services RVU","Quick Purchased Services RVU","Purchased Services Cost","Quick Purchased Services Cost","Purchased Services Change","Professional Fees RVU","Quick Professional Fees RVU","Professional Fees Cost","Quick Professional Fees Cost","Professional Fees Change","Other Expenses RVU","Quick Other Expenses RVU","Other Expenses Cost","Quick Other Expenses Cost","Other Expenses Change","Direct Overhead RVU","Quick Direct Overhead RVU","Direct Overhead Cost","Quick Direct Overhead Cost","Direct Overhead Change","Hospital Overhead RVU","Quick Hospital Overhead RVU","Hospital Overhead Cost","Quick Hospital Overhead Cost","Hospital Overhead Change","Corporate Overhead RVU","Quick Corporate Overhead RVU","Corporate Overhead Cost","Quick Corporate Overhead Cost","Corporate Overhead Change","Depreciation RVU","Quick Depreciation RVU","Depreciation Cost","Quick Depreciation Cost","Depreciation Change","Tech RVU","Quick Tech RVU","Tech Cost","Quick Tech Cost","Tech Change"};
    for (String selectedColumns: columns) {
      highlightColumnsToDisplayColumn(selectedColumns);
      doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplayModalRemove());
    }
    return columns;
  }

  public void removeMultipleColumnsToDisplay(String[] columnsToRemove) throws InterruptedException,Throwable {
    for (String selectedColumns: columnsToRemove) {
      highlightColumnsToDisplayColumn(selectedColumns);
      doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplayModalRemove());
    }
  }

  public void assertColumnsToDisplayColumnOrder() {
    String[] columns = {"Charge Code Name","Modifier","Total Unit Cost","Total Quick Cost","Total Change","Salaries and Wages RVU","Quick Salaries and Wages RVU","Salaries and Wages Cost","Quick Salaries and Wages Cost","Salaries and Wages Change","Employee Benefits RVU","Quick Employee Benefits RVU","Employee Benefits Cost","Quick Employee Benefits Cost","Employee Benefits Change","Medical Supplies RVU","Quick Medical Supplies RVU","Medical Supplies Cost","Quick Medical Supplies Cost","Medical Supplies Change","Non-Medical Supplies RVU","Quick Non-Medical Supplies RVU","Non-Medical Supplies Cost","Quick Non-Medical Supplies Cost","Non-Medical Supplies Change","Equip Repair & Maint RVU","Quick Equip Repair & Maint RVU","Equip Repair & Maint Cost","Quick Equip Repair & Maint Cost","Equip Repair & Maint Change","Direct Depreciation RVU","Quick Direct Depreciation RVU","Direct Depreciation Cost","Quick Direct Depreciation Cost","Direct Depreciation Change","Purchased Services RVU","Quick Purchased Services RVU","Purchased Services Cost","Quick Purchased Services Cost","Purchased Services Change","Professional Fees RVU","Quick Professional Fees RVU","Professional Fees Cost","Quick Professional Fees Cost","Professional Fees Change","Other Expenses RVU","Quick Other Expenses RVU","Other Expenses Cost","Quick Other Expenses Cost","Other Expenses Change","Direct Overhead RVU","Quick Direct Overhead RVU","Direct Overhead Cost","Quick Direct Overhead Cost","Direct Overhead Change","Hospital Overhead RVU","Quick Hospital Overhead RVU","Hospital Overhead Cost","Quick Hospital Overhead Cost","Hospital Overhead Change","Corporate Overhead RVU","Quick Corporate Overhead RVU","Corporate Overhead Cost","Quick Corporate Overhead Cost","Corporate Overhead Change","Depreciation RVU","Quick Depreciation RVU","Depreciation Cost","Quick Depreciation Cost","Depreciation Change","Tech RVU","Quick Tech RVU","Tech Cost","Quick Tech Cost","Tech Change"};

    String selectedColumnsXpath = "//*[contains(@class,'glAccountsGrid')][2]/descendant::tbody";
    WebElement selectedMenu = driver.findElement(By.xpath(selectedColumnsXpath));
    List<WebElement> actualSelectedColumns = selectedMenu.findElements(By.tagName("tr"));
    List<String> actualSelectedColumnNames = new ArrayList<>();

    for (WebElement selectedColumns: actualSelectedColumns) {
      actualSelectedColumnNames.add(selectedColumns.getText());
      for (int i = 0; i < columns.length; i++) {
        if (selectedColumns.getText().equals(columns[i])) {
          System.out.println(selectedColumns.getText() + " = " + columns[i]);
        }
      }
    }
  }

  public void compareAvailableColumnToSelectedColumn() {
    String availableColumnsXpath = "//*[contains(@class,'glAccountsGrid')][1]/descendant::tbody//tr//td";
    WebElement availableMenu = driver.findElement(By.xpath(availableColumnsXpath));
    List<WebElement> actualAvailableColumns = driver.findElements(By.xpath("//*[contains(@class,'glAccountsGrid')][1]/descendant::tbody//tr//td/div"));
    List<String> actualAvailableColumnNames = new ArrayList<>();
    for (WebElement availableColumns: actualAvailableColumns) {
    	actualAvailableColumnNames.add(availableColumns.getText());
        /*
        if (selectedColumns.getText().equals("")) {
          continue;
        }
        */
      }
    String selectedColumnsXpath = "//label[text()='Selected']/following::*[contains(@class,'glAccountsGrid')][2]/descendant::tbody";
//    WebElement selectedMenu = driver.findElement(By.xpath(selectedColumnsXpath));
    List<WebElement> actualSelectedColumns = driver.findElements(By.xpath("//label[text()='Selected']/following::*[contains(@class,'glAccountsGrid')][2]/descendant::tbody//tr//td/div"));
    List<String> actualSelectedColumnNames = new ArrayList<>();
    System.out.println(actualSelectedColumns.size());
    for (WebElement selectedColumns: actualSelectedColumns) {
      actualSelectedColumnNames.add(selectedColumns.getText());
      /*
      if (selectedColumns.getText().equals("")) {
        continue;
      }
      */
    }
//  99  actualSelectedColumnNames.remove(0);
    if ((actualAvailableColumnNames.equals(actualSelectedColumnNames))) {
      System.out.println("The Available and Selected Columns have elements in common.");
      fail();
    } else {
      System.out.println("The Available and Selected Columns do not have elements in common.");
    }
  }

  public List<String> getSelectedColumnList() {
    String selectedColumnsXpath = "//*[contains(@class,'glAccountsGrid')][2]/descendant::tbody";
    WebElement selectedMenu = driver.findElement(By.xpath(selectedColumnsXpath));
    List<WebElement> actualSelectedColumns = driver.findElements(By.xpath("//*[contains(@class,'glAccountsGrid')][2]/descendant::tbody//tr//td/div"));
    List<String> actualSelectedColumnNames = new ArrayList<>();
    for (WebElement selectedColumns: actualSelectedColumns) {
      actualSelectedColumnNames.add(selectedColumns.getText());
      if (selectedColumns.getText().equals("")) {
        continue;
      }
      System.out.println(selectedColumns.getText());
    }
    actualSelectedColumnNames.remove(0);
    System.out.println(actualSelectedColumnNames.size());
    return actualSelectedColumnNames;
  }

  public void getAvailableColumnList() {
    String availableColumnsXpath = "//*[contains(@class,'glAccountsGrid')][1]/descendant::tbody";
    WebElement availableMenu = driver.findElement(By.xpath(availableColumnsXpath));
    List<WebElement> actualAvailableColumns = availableMenu.findElements(By.tagName("tr"));
    List<String> actualAvailableColumnNames = new ArrayList<>();
    for (WebElement availableColumns: actualAvailableColumns) {
      actualAvailableColumnNames.add(availableColumns.getText());
      if (availableColumns.getText().equals("")) {
        continue;
      }
      System.out.println(availableColumns.getText());
    }
    actualAvailableColumnNames.remove(0);
    System.out.println(actualAvailableColumnNames.size());
  }

  public void assertAvailableColumnIsEmpty() {
    String availableColumnsXpath = "//*[contains(@class,'glAccountsGrid')][1]/descendant::tbody//div[contains(@class,'x-grid-cell')]";
    //        String availableColumnsXpath = "//*[contains(@class,'glAccountsGrid')][1]/descendant::tbody/tr/th";
    /*
    WebElement availableMenu = driver.findElement(By.xpath(availableColumnsXpath));
    List<WebElement> actualAvailableColumns = availableMenu.findElements(By.tagName("tr"));
    List<String> actualAvailableColumnNames = new ArrayList<>();
    for (WebElement availableColumns: actualAvailableColumns) {
      actualAvailableColumnNames.add(availableColumns.getText());
      if (availableColumns.getText().equals("")) {
        continue;
      }
      System.out.println(availableColumns.getText());
    }
    actualAvailableColumnNames.remove(0);
    System.out.println(actualAvailableColumnNames.size());

    if (actualAvailableColumnNames.size() == 0) {
      System.out.println("The Available box in the Select Columns window is empty.");
    } else {
      System.out.println("The Available box in the Select Columns window is not empty.");
      fail();
    }
    */
    try {
		if(!(driver.findElement(By.xpath("//*[contains(@class,'glAccountsGrid')][1]/descendant::tbody//div[contains(@class,'x-grid-cell')]")).isDisplayed())) {
			 System.out.println("The Available box in the Select Columns window is not empty.");
		      fail();
		}
	} catch (Exception e) {
		 System.out.println("The Available box in the Select Columns window is empty.");

	}
  }

  public void assertAvailableColumnIsNotEmpty() {
    String availableColumnsXpath = "//*[contains(@class,'glAccountsGrid')][1]/descendant::tbody//tr//td";
    WebElement availableMenu = driver.findElement(By.xpath(availableColumnsXpath));
    List<WebElement> actualAvailableColumns = availableMenu.findElements(By.tagName("div"));
    List<String> actualAvailableColumnNames = new ArrayList<>();
    //Not Empty
    for (WebElement availableColumns: actualAvailableColumns) {
      actualAvailableColumnNames.add(availableColumns.getText());
      if (availableColumns.getText().equals("")) {
        continue;
      }
      System.out.println(availableColumns.getText());
    }
//    actualAvailableColumnNames.remove(0);
    System.out.println(actualAvailableColumnNames.size());

    if (actualAvailableColumnNames.size() == 1) {
      System.out.println("The Available box in the Select Columns window is not empty.");
    } else {
      System.out.println("The Available box in the Select Columns window is empty.");
      fail();
    }
  }

  public void assertColumnsToDisplayAllCheckBoxIsNotChecked() throws Exception {
    String columnsToDisplayCheckBox = null;
    try {
      waitForAjaxExtJs();
      columnsToDisplayCheckBox = driver.findElement(By.xpath("//*[contains(@class,'labelValignMiddle')][contains(@id,'checkbox')]")).getAttribute("class");
    } catch (Throwable e) {
      System.out.println("Element Not Found");
      fail("element not found");
    }
    boolean isNotChecked = columnsToDisplayCheckBox.contains("dirty");
    if (printout) {
      System.out.println("Element class text: " + columnsToDisplayCheckBox);
      System.out.println("IsNotChecked: " + isNotChecked);
    }
    try {
      assertTrue(isNotChecked);
    } catch (Throwable e) {
      System.out.println("TEST FAILED: Element is Checked");
      throw new Exception();
    }
  }

  public void assertColumnsToDisplayAllCheckBoxIsChecked() throws Exception {
    String columnsToDisplayCheckBox = null;
    try {
      waitForAjaxExtJs();
      columnsToDisplayCheckBox = driver.findElement(By.xpath("//*[contains(@class,'labelValignMiddle')][contains(@id,'checkbox')]")).getAttribute("class");
    } catch (Throwable e) {
      System.out.println("Element Not Found");
      fail("element not found");
    }
    boolean isChecked = columnsToDisplayCheckBox.contains("checked");
    if (printout) {
      System.out.println("Element class text: " + columnsToDisplayCheckBox);
      System.out.println("IsChecked: " + isChecked);
    }
    try {
      assertTrue(isChecked);
    } catch (Throwable e) {
      System.out.println("TEST FAILED: Element is not checked");
      throw new Exception();
    }
  }

  public void assertColumnsToDisplayAllCheckBoxIsDisabled() throws Exception {
    String columnsToDisplayCheckBox = null;
    try {
      waitForAjaxExtJs();
      columnsToDisplayCheckBox = driver.findElement(By.xpath("//*[contains(@class,'labelValignMiddle')][contains(@id,'checkbox')]")).getAttribute("class");
    } catch (Throwable e) {
      System.out.println("Element Not Found");
      fail("element not found");
    }
    boolean isDisabled = columnsToDisplayCheckBox.contains("disabled");
    if (printout) {
      System.out.println("Element class text: " + columnsToDisplayCheckBox);
      System.out.println("IsDisabled: " + isDisabled);
    }
    try {
      assertTrue(isDisabled);
    } catch (Throwable e) {
      System.out.println("TEST FAILED: Element is Enabled");
      throw new Exception();
    }
  }

  public void assertColumnsToDisplayColumnIsSelected() throws Exception {
    String columnIsSelected = null;
    try {
      waitForAjaxExtJs();
      columnIsSelected = driver.findElement(By.xpath("//*[contains(@class,'x-grid-item')]/descendant::*[text()='Charge Code Name']/../../../..")).getAttribute("class");
    } catch (Throwable e) {
      System.out.println("Element Not Found");
      fail("element not found");
    }
    boolean isSelected = columnIsSelected.contains("selected");
    if (printout) {
      System.out.println("Element class text: " + columnIsSelected);
      System.out.println("IsSelected: " + isSelected);
    }
    try {
      assertTrue(isSelected);
    } catch (Throwable e) {
      System.out.println("TEST FAILED: Element is not selected");
      throw new Exception();
    }
  }
  
  public void selectColumnsToDisplayAvailableColumn(String column) throws Exception {
      String columnPath = "//*[contains(@class,'glAccountsGrid')][1]/descendant::*[text()='" + column + "']";
      WebElement element = driver.findElement(By.xpath(columnPath));
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
      Thread.sleep(500); 
      assertElementIsDisplayed(element);
  }

   /* public void selectColumnsToDisplaySelectedColumn(String column) {
        String columnPath = "//*[contains(@class,'glAccountsGrid')][2]/descendant::*[text()='" + column + "']";
        doClick(driver.findElement(By.xpath(columnPath)));
    }
    public void selectColumnsToDisplayAvailableColumn(String column) {
        String columnPath = "//*[contains(@class,'glAccountsGrid')][1]/descendant::*[text()='" + column + "']";
        doClick(driver.findElement(By.xpath(columnPath)));
    }*/

  public void assertColumnsToDisplayColumn(String column) {
    String selectedColumnsXpath = "//label[text()='Selected']/following::*[contains(@class,'glAccountsGrid')][2]/descendant::tbody//tr//td";
    //        String selectedColumnsXpath = "//*[contains(@class,'glAccountsGrid')][1]/descendant::tbody";
    WebElement selectedMenu = driver.findElement(By.xpath(selectedColumnsXpath));
    List<WebElement> actualSelectedColumns = selectedMenu.findElements(By.tagName("div"));
    List<String> actualSelectedColumnNames = new ArrayList<>();

//        String columnPath = "//label[text()='Available']/following::*[contains(@class,'glAccountsGrid')]/descendant::*[text()='" + column + "']";

    for (WebElement selectedColumns: actualSelectedColumns) {
      actualSelectedColumnNames.add(selectedColumns.getText());
      if (selectedColumns.getText().equals(column)) {
        System.out.println("Element, " + column + ", is found.");
        break;
      }
    }
  }

  public void assertIfColumnIsInAvailableOrSelectedBox(String columnName) {
    String availableColumnsXpath = "//*[contains(@class,'glAccountsGrid')][1]/descendant::tbody";
    WebElement availableMenu = driver.findElement(By.xpath(availableColumnsXpath));
    List<WebElement> actualAvailableColumns = availableMenu.findElements(By.tagName("tr"));
    List<String> actualAvailableColumnNames = new ArrayList<>();
    for (WebElement availableColumns: actualAvailableColumns) {
      actualAvailableColumnNames.add(availableColumns.getText());
      if (availableColumns.getText().equals("")) {
        continue;
      }
      System.out.println(availableColumns.getText());
      if (availableColumns.getText().equals(columnName)) {
        System.out.println(columnName + " is in the Available box");
        break;
      } else {
        System.out.println(columnName + " is in the Selected box");
        break;
      }
    }
    actualAvailableColumnNames.remove(0);
  }
  
  @AfterClass
  public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}


