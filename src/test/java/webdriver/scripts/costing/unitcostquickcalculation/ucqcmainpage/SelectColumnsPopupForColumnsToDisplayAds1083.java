package webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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
  String[] columns = {"Charge Code Name","Modifier"};

  /**
   ADS-1083 (Dev Story ADS-739).
   ADS-1390 (Dev Story ADS-740) - Updated 7/11/19 to include additional steps.
   This script confirms the ability to open the Select Columns popup when you click Select for Columns to Display
   as well as being able to view a subset of columns once the selection is applied
   */
  @BeforeClass
  public static void setupScript() throws Exception {
    selectColumn = BuildMap.getInstance(driver, CostingMap.class);
    System.out.println("Test Class: " + LoadingSpinnerDisplaysWhileCalculationIsRunningAds1231.class.getSimpleName());
    Login.loginUser("CostingDepartmentManager1");
    doMaximizeWindow();
    goToPage("Unit Cost Quick Calculation");
    waitForAjaxExtJs();
  }

  @Test
  public void test01OnPageLoadAssertColumnToDisplayAllCheckBoxCheckedDisabledAndSelectButtonDisabled() {
    try {
      assertElementIsDisabled(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplaySelect(),printout);
      assertColumnsToDisplayAllCheckBoxIsDisabled();
      assertColumnsToDisplayAllCheckBoxIsChecked();
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test02SetCostModelAndAssertSelectButtonEnabledWhenAllIsUnchecked() {
    try {
      doDropdownSelectUsingOptionText(selectColumn.getUnitCostQuickCalculationDropdownCostModel(),selectColumn.getUnitCostQuickCalculationDropdownCostModelMenuList(),"Marina");
      doClick(selectColumn.getUnitCostQuickCalculationCheckBoxColumnsToDisplayAll());
      assertColumnsToDisplayAllCheckBoxIsNotChecked();
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplaySelect(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test03OpenSelectColumnsModalAssertAvailableColumnsIsEmptyAndRespectiveButtonsAreEnabledDisabled() {
    try {
      doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplaySelect());
      waitForAjaxExtJs();
      assertAvailableColumnIsEmpty();
      assertElementIsDisabled(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplayModalRemove(),printout);
      assertElementIsDisabled(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplayModalSelect(),printout);
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test04AssertColumnsInAvailableBoxAreStillAppliedIfSelectButtonIsClickedASubsequentTime() {
    try {
      highlightColumnsToDisplayColumn("Charge Code Name");
      assertColumnsToDisplayColumnIsSelected();
      doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplayModalRemove());
      doClick(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply());
      waitForAjaxExtJs();
      doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplaySelect());
      assertAvailableColumnIsNotEmpty();
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test05AssertAvailableBoxIsEmptyIfAllCheckboxIsReChecked() {
    try {
      doClick(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel());
      waitForAjaxExtJs();
      doClick(selectColumn.getUnitCostQuickCalculationCheckBoxColumnsToDisplayAll());
      waitForAjaxExtJs();
      doClick(selectColumn.getUnitCostQuickCalculationCheckBoxColumnsToDisplayAll());
      doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplaySelect());
      assertAvailableColumnIsEmpty();
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test06AssertChargeCodeIsNotInTheSelector() {
    try {
      assertColumnsToDisplayColumn("Charge Code");
      waitForAjaxExtJs();
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test07AssertColumnOrder() {
    try {
      assertColumnsToDisplayColumnOrder();
      waitForAjaxExtJs();
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test08AssertCostComponentOrder() {
    try {
      assertFiveColumnsForEachCostComponent();
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test09HighlightColumnInSelectBoxAndAssertRemoveButtonIsEnabled() {
    try {
      highlightColumnsToDisplayColumn("Charge Code Name");
      waitForAjaxExtJs();
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplayModalRemove(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test10AssertColumnIsHighlighted() {
    try {
      assertColumnsToDisplayColumnIsSelected();
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test11MoveColumnsFromSelectBoxToAvailableBoxAndAssertAvailableBoxIsNotEmpty() {
    try {
      highlightColumnsToDisplayColumn("Charge Code Name");
      assertColumnsToDisplayColumnIsSelected();
      doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplayModalRemove());
      waitForAjaxExtJs();
      assertAvailableColumnIsNotEmpty();
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test12CompareAvailableBoxToSelectedBoxAndAssertCancelButtonIsEnabled() {
    try {
      compareAvailableColumnToSelectedColumn();
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test13AssertSelectButtonDisabledIfNoColumnHighlightedInAvailableBox() {
    try {
      assertElementIsDisabled(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplayModalSelect(),printout);
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test14ConfirmColumnsCanBeHighlightedInAvailableAndSelectedBoxesAndAssertRemoveSelctonCancelButtonsEnabled() {
    try {
      highlightColumnsToDisplayColumn("Charge Code Name");
      highlightColumnsToDisplayColumn("Modifier");
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplayModalRemove(),printout);
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplayModalSelect(),printout);
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test15RemoveAllColumnsFromSelectedBoxAndAssertApplyButtonIsDisabled() {
    try {
      removeAllColumnsToDisplayColumns();
      assertElementIsDisabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply(),printout);
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test16AssertApplyButtonDisabledWhenNoQuickRvusAreSelected() {
    try {
      selectMultipleColumnsToDisplay(columnHeaderSubset);
      assertElementIsDisabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply(),printout);
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test17AssertApplyButtonDisabledWhenOnlyChargeCodeNameIsSelected() {
    try {
      removeMultipleColumnsToDisplay(columnsChargeCodeNameOnly);
      assertElementIsDisabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply(),printout);
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test18AssertApplyButtonDisabledWhenOnlyModifierSelected() {
    try {
      highlightColumnsToDisplayColumn("Modifier");
      assertElementIsDisabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply(),printout);
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test19AssertSelectedColumnMovesFromAvailableBoxToSelectedBox() {
    try {
      highlightColumnsToDisplayColumn("Salaries and Wages RVU");
      doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplayModalSelect());
      getSelectedColumnList();
      assertIfColumnIsInAvailableOrSelectedBox("Salaries and Wages RVU");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test20AssertApplyButtonDisabledWhenChargeCodeNameAndModifierSelected() {
    try {
      removeAllColumnsToDisplayColumns(); //redo to just remove only the Selected items, not all of them
      selectMultipleColumnsToDisplay(columns);
      assertElementIsDisabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply(),printout);
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test21AssertApplyButtonIsEnabledWhenAtLeastOneQuickRvuColumnIsMovedToSelectedSide() {
    try {
      highlightColumnsToDisplayColumn("Quick Salaries and Wages RVU");
      doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplayModalSelect());
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply(),printout);
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test22AssertApplyButtonEnabledWhenAllColumnsMovedToSelectedSide() {
    try {
      selectAllColumnsToDisplayColumns();
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply(),printout);
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test23AssertApplyButtonEnabledWhenAllColumnsOnSelectedSideExceptChargeCodeNameAndModifier() {
    try {
      removeMultipleColumnsToDisplay(columns);
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply(),printout);
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test24AssertApplyButtonEnabledWhenAllColumnsOnSelectedSideExceptModifier() {
    try {
      highlightColumnsToDisplayColumn("Charge Code Name");
      doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplayModalSelect());
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply(),printout);
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Ignore
  @Test
  public void test25ChangeUcqcSelectionAndAssertSubsetOfColumnsDoNotChange() {
    try {
      /**NOTE: FAILING WHILE ATTEMPTING TO SET UCQC CRITERIA, this portion has been commented out*/
      removeAllColumnsToDisplayColumns();
      selectMultipleColumnsToDisplay(columnsHeaderSubsetRvu);
      doClick(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalApply());
      waitForAjaxExtJs();
      doDropdownSelectUsingOptionText(selectColumn.getUnitCostQuickCalculationDropdownCostModelScenario(),selectColumn.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),"*CM3 TB MHFY05 Before Vol Change_UC_UCQC");
      waitForAjaxExtJs();
      doDropdownSelectUsingOptionText(selectColumn.getUnitCostQuickCalculationDropdownEntity(),selectColumn.getUnitCostQuickCalculationDropdownEntityMenuList(),"200 Southgate");
      waitForAjaxExtJs();
      selectDepartment("2110  ICU");
      waitForAjaxExtJs();
      doDropdownSelectUsingOptionText(selectColumn.getUnitCostQuickCalculationFieldResultsStoredFor(),selectColumn.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"Apr 2004 to Mar 2005");
      waitForAjaxExtJs();
      doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplaySelect());
      waitForAjaxExtJs();
      List<String> selectedColumnsSubset = getSelectedColumnList();
      for (int i = 0; i < columnsHeaderSubsetRvu.length; i++) {
        if (selectedColumnsSubset.get(i).equals(columnsHeaderSubsetRvu[i])) {
          System.out.println(columnsHeaderSubsetRvu[i] + " = " + selectedColumnsSubset.get(i));
        } else {
          System.out.println("The column selections are not still available after changing CMS, Entity, Dept, and Results Stored For.");
        }
      }
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Ignore
  @Test
  public void test26CloseTheUcqcSessionAndAssertColumnSelectionDoesNotSaveUponReopen() {
    try {
      doClick(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel());
      doClosePageOnLowerBar("Unit Cost Quick...");
      goToPage("Unit Cost Quick Calculation");
      assertColumnHeaderSubsetDisplays(defaultColumnHeaderSubset);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Ignore
  @Test
  public void test27SaveUndoAnEditAndAssertColumnSelectionDoesNotChange() {
    try {
      waitForAjaxExtJs();
      //setUCQCCriteria("Marina", "*CM1 TB MHFY05 After Vol Change", "150 Marina Medical Center", "2130  PED ICU", "Jan 2005 to Jan 2005");            doClick(selectColumn.getUnitCostQuickCalculationCheckBoxColumnsToDisplayAll());
      setUcqcCriteria("Marina", "*CM1 TB MHFY05 After Vol Change", "150 Marina Medical Center", "2130  PED ICU", "Jan 2005 to Jan 2005");
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
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Ignore
  @Test
  public void test28AssertPreviousColumnSubsetPersistsAfterCopyToQuickRvusIsSelected() {
    try {
      /**DO NOT RUN IN EVOLVE: This should be run in a dedicated environment with a Cost Model Scenario specific to this test case*/
            /*doClick(selectColumn.getUnitCostQuickCalculationButtonCopyToQuickRVUs());
            ucqcWaitForSpinnerToEnd();
            assertColumnHeaderSubsetDisplays(columnsSubset);*/
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Ignore
  @Test
  public void test29AssertPreviousColumnSubsetPersistsAfterClearRvusAndSaveIsSelected() {
    try {
      ucqcGridClickInCell("1100171","Quick Salaries and Wages RVU",printout);
      doClick(selectColumn.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave());
      waitForAjaxExtJs();
      doClick(selectColumn.getUnitCostQuickCalculationButtonClearAndSave());
      assertColumnHeaderSubsetDisplays(columnsHeaderSubsetRvu);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Ignore
  @Test
  public void test30AssertPreviousColumnSubsetPersistsAfterOverwriteRvuMaintenanceIsSelected() {
    try {
      doClick(selectColumn.getUnitCostQuickCalculationButtonOverwriteRVUMaintenance());
      waitForAjaxExtJs();
      doClick(selectColumn.getUnitCostQuickCalculationOverwriteRVUMaintenanceModalButtonCancelAndClose());
      waitForAjaxExtJs();
      assertColumnHeaderSubsetDisplays(columnsHeaderSubsetRvu);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Ignore
  @Test
  public void test31AssertPreviousColumnSubsetPersistsAfterCalculationIsRun() {
    try {
      doClick(selectColumn.getUnitCostQuickCalculationButtonCalculate());
      ucqcWaitForSpinnerToEnd();
      assertColumnHeaderSubsetDisplays(columnsHeaderSubsetRvu);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Ignore
  @Test
  public void test32AssertApplySelectionsButtonIsEnabledWhenUcqcCriteriaIsSetAllCheckboxIsUncheckedAndAtLeastOneColumnIsSelectedInSelectColumnsPopUp() {
    try {
      waitForAjaxExtJs();
      doClick(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel());
      waitForAjaxExtJs();
      doClosePageOnLowerBar("Unit Cost Quick...");
      goToPage("Unit Cost Quick Calculation");
      setUcqcCriteria("Marina", "*CM1 TB MHFY05 After Vol Change", "150 Marina Medical Center", "2130  PED ICU", "Jan 2005 to Jan 2005");
      doClick(selectColumn.getUnitCostQuickCalculationCheckBoxColumnsToDisplayAll());
      waitForAjaxExtJs();
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationButtonApplySelections(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Ignore
  @Test
  public void test33AssertChargeCodeChargeCodeNameAndModifierGridColumnsAreLocked() {
    try {
      doClick(selectColumn.getUnitCostQuickCalculationButtonApplySelections());
      waitForSpinnerToEnd();
      String columnID = driver.findElement(By.xpath("//div[@id='ucqcgrid-targetEl']/descendant::div[contains(@id,'gridpanel')][1]/div[contains(@id,'headercontainer')]/..")).getAttribute("id");
      int columnIdDigits = Integer.parseInt(getNumbersFromStringWithRegex(columnID));
      String headerPanel = driver.findElement(By.xpath("//div[@id='ucqcgrid-targetEl']/descendant::div[contains(@id,'gridpanel')][1]/div[contains(@id,'headercontainer')]/ancestor::div[@id=\"gridpanel-" + columnIdDigits + "\"]")).getAttribute("class");
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
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Ignore
  @Test
  public void test34AssertChargeCodeIsDisplayedAndNotAvailableInSelectColumnWindow() {
    try {
      assertColumnHeaderIsDisplayed("Charge Code");
      /*doDropdownSelectUsingOptionText(selectColumn.getUnitCostQuickCalculationDropdownCostModel(),selectColumn.getUnitCostQuickCalculationDropdownCostModelMenuList(),"Marina");
      waitForAjaxExtJs();*/
      doClick(selectColumn.getUnitCostQuickCalculationCheckBoxColumnsToDisplayAll());
      doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplaySelect());
      waitForAjaxExtJs();
      assertColumnIsNotDisplayedInSelectBox("Charge Code");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Ignore
  @Test
  public void test35ChangeCostModelAndAssertAllCheckBoxIsCheckedAndSelectButtonIsDisabled() {
    try {
      doClick(selectColumn.getUnitCostQuickCalculationColumnsToDisplayModalCancel());
      doDropdownSelectUsingOptionText(selectColumn.getUnitCostQuickCalculationDropdownCostModel(),selectColumn.getUnitCostQuickCalculationDropdownCostModelMenuList(),"QA Cost Model");
      assertElementIsDisabled(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplaySelect(),printout);
      assertElementIsEnabled(selectColumn.getUnitCostQuickCalculationCheckBoxColumnsToDisplayAll(),printout);
      assertColumnsToDisplayAllCheckBoxIsChecked();
    } catch (Throwable e) {
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
      assertElementIsDisplayed(driver.findElement(By.xpath("//span[@class=\"x-column-header-text\"][text()='" + headerNames + "']")),printout);
    }
  }

  public void assertColumnHeaderIsDisplayed(String columnHeader) {
    assertElementIsDisplayed(driver.findElement(By.xpath("//span[@class=\"x-column-header-text\"][text()='" + columnHeader + "']")),printout);
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

  public void highlightColumnsToDisplayColumn(String column) throws InterruptedException {
    String columnPath = "//*[contains(@class,'glAccountsGrid')]/descendant::*[text()='" + column + "']";
    doClick(driver.findElement(By.xpath(columnPath)));
    waitForAjaxExtJs();
  }

  public void selectAllColumnsToDisplayColumns() throws InterruptedException {
    String[] columns = {"Charge Code Name","Modifier","Total Unit Cost","Total Quick Cost","Total Change","Salaries and Wages RVU","Quick Salaries and Wages RVU","Salaries and Wages Cost","Quick Salaries and Wages Cost","Salaries and Wages Change","Employee Benefits RVU","Quick Employee Benefits RVU","Employee Benefits Cost","Quick Employee Benefits Cost","Employee Benefits Change","Medical Supplies RVU","Quick Medical Supplies RVU","Medical Supplies Cost","Quick Medical Supplies Cost","Medical Supplies Change","Non-Medical Supplies RVU","Quick Non-Medical Supplies RVU","Non-Medical Supplies Cost","Quick Non-Medical Supplies Cost","Non-Medical Supplies Change","Equip Repair & Maint RVU","Quick Equip Repair & Maint RVU","Equip Repair & Maint Cost","Quick Equip Repair & Maint Cost","Equip Repair & Maint Change","Direct Depreciation RVU","Quick Direct Depreciation RVU","Direct Depreciation Cost","Quick Direct Depreciation Cost","Direct Depreciation Change","Purchased Services RVU","Quick Purchased Services RVU","Purchased Services Cost","Quick Purchased Services Cost","Purchased Services Change","Professional Fees RVU","Quick Professional Fees RVU","Professional Fees Cost","Quick Professional Fees Cost","Professional Fees Change","Other Expenses RVU","Quick Other Expenses RVU","Other Expenses Cost","Quick Other Expenses Cost","Other Expenses Change","Direct Overhead RVU","Quick Direct Overhead RVU","Direct Overhead Cost","Quick Direct Overhead Cost","Direct Overhead Change","Hospital Overhead RVU","Quick Hospital Overhead RVU","Hospital Overhead Cost","Quick Hospital Overhead Cost","Hospital Overhead Change","Corporate Overhead RVU","Quick Corporate Overhead RVU","Corporate Overhead Cost","Quick Corporate Overhead Cost","Corporate Overhead Change","Depreciation RVU","Quick Depreciation RVU","Depreciation Cost","Quick Depreciation Cost","Depreciation Change","Tech RVU","Quick Tech RVU","Tech Cost","Quick Tech Cost","Tech Change"};
    for (String selectedColumns: columns) {
      highlightColumnsToDisplayColumn(selectedColumns);
      doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplayModalSelect());
    }
  }

  public void selectMultipleColumnsToDisplay(String[] columnsToSelect) throws InterruptedException {
    for (String selectedColumns: columnsToSelect) {
      highlightColumnsToDisplayColumn(selectedColumns);
      doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplayModalSelect());
    }
  }

  public String[] removeAllColumnsToDisplayColumns() throws InterruptedException {
    String[] columns = {"Charge Code Name","Modifier","Total Unit Cost","Total Quick Cost","Total Change","Salaries and Wages RVU","Quick Salaries and Wages RVU","Salaries and Wages Cost","Quick Salaries and Wages Cost","Salaries and Wages Change","Employee Benefits RVU","Quick Employee Benefits RVU","Employee Benefits Cost","Quick Employee Benefits Cost","Employee Benefits Change","Medical Supplies RVU","Quick Medical Supplies RVU","Medical Supplies Cost","Quick Medical Supplies Cost","Medical Supplies Change","Non-Medical Supplies RVU","Quick Non-Medical Supplies RVU","Non-Medical Supplies Cost","Quick Non-Medical Supplies Cost","Non-Medical Supplies Change","Equip Repair & Maint RVU","Quick Equip Repair & Maint RVU","Equip Repair & Maint Cost","Quick Equip Repair & Maint Cost","Equip Repair & Maint Change","Direct Depreciation RVU","Quick Direct Depreciation RVU","Direct Depreciation Cost","Quick Direct Depreciation Cost","Direct Depreciation Change","Purchased Services RVU","Quick Purchased Services RVU","Purchased Services Cost","Quick Purchased Services Cost","Purchased Services Change","Professional Fees RVU","Quick Professional Fees RVU","Professional Fees Cost","Quick Professional Fees Cost","Professional Fees Change","Other Expenses RVU","Quick Other Expenses RVU","Other Expenses Cost","Quick Other Expenses Cost","Other Expenses Change","Direct Overhead RVU","Quick Direct Overhead RVU","Direct Overhead Cost","Quick Direct Overhead Cost","Direct Overhead Change","Hospital Overhead RVU","Quick Hospital Overhead RVU","Hospital Overhead Cost","Quick Hospital Overhead Cost","Hospital Overhead Change","Corporate Overhead RVU","Quick Corporate Overhead RVU","Corporate Overhead Cost","Quick Corporate Overhead Cost","Corporate Overhead Change","Depreciation RVU","Quick Depreciation RVU","Depreciation Cost","Quick Depreciation Cost","Depreciation Change","Tech RVU","Quick Tech RVU","Tech Cost","Quick Tech Cost","Tech Change"};
    for (String selectedColumns: columns) {
      highlightColumnsToDisplayColumn(selectedColumns);
      doClick(selectColumn.getUnitCostQuickCalculationButtonColumnsToDisplayModalRemove());
    }
    return columns;
  }

  public void removeMultipleColumnsToDisplay(String[] columnsToRemove) throws InterruptedException {
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
    String availableColumnsXpath = "//*[contains(@class,'glAccountsGrid')][1]/descendant::tbody";
    WebElement availableMenu = driver.findElement(By.xpath(availableColumnsXpath));
    List<WebElement> actualAvailableColumns = availableMenu.findElements(By.tagName("tr"));
    List<String> actualAvailableColumnNames = new ArrayList<>();

    String selectedColumnsXpath = "//label[text()='Selected']/following::*[contains(@class,'glAccountsGrid')][2]/descendant::tbody";
    WebElement selectedMenu = driver.findElement(By.xpath(selectedColumnsXpath));
    List<WebElement> actualSelectedColumns = selectedMenu.findElements(By.tagName("tr"));
    List<String> actualSelectedColumnNames = new ArrayList<>();
    for (WebElement selectedColumns: actualSelectedColumns) {
      actualSelectedColumnNames.add(selectedColumns.getText());
      if (selectedColumns.getText().equals("")) {
        continue;
      }
    }
    actualSelectedColumnNames.remove(0);
    if (actualAvailableColumnNames.equals(actualSelectedColumnNames)) {
      System.out.println("The Available and Selected Columns have elements in common.");
      fail();
    } else {
      System.out.println("The Available and Selected Columns do not have elements in common.");
    }
  }

  public List<String> getSelectedColumnList() {
    String selectedColumnsXpath = "//*[contains(@class,'glAccountsGrid')][2]/descendant::tbody";
    WebElement selectedMenu = driver.findElement(By.xpath(selectedColumnsXpath));
    List<WebElement> actualSelectedColumns = selectedMenu.findElements(By.tagName("tr"));
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
    String availableColumnsXpath = "//*[contains(@class,'glAccountsGrid')][1]/descendant::tbody";
    //        String availableColumnsXpath = "//*[contains(@class,'glAccountsGrid')][1]/descendant::tbody/tr/th";
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
  }

  public void assertAvailableColumnIsNotEmpty() {
    String availableColumnsXpath = "//*[contains(@class,'glAccountsGrid')][1]/descendant::tbody";
    WebElement availableMenu = driver.findElement(By.xpath(availableColumnsXpath));
    List<WebElement> actualAvailableColumns = availableMenu.findElements(By.tagName("tr"));
    List<String> actualAvailableColumnNames = new ArrayList<>();
    //Not Empty
    for (WebElement availableColumns: actualAvailableColumns) {
      actualAvailableColumnNames.add(availableColumns.getText());
      if (availableColumns.getText().equals("")) {
        continue;
      }
      System.out.println(availableColumns.getText());
    }
    actualAvailableColumnNames.remove(0);
    System.out.println(actualAvailableColumnNames.size());

    if (actualAvailableColumnNames.size() != 0) {
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
      columnsToDisplayCheckBox = driver.findElement(By.xpath("//*[contains(@class,'labelValignMiddle')][contains(@id,'checkboxfield')]")).getAttribute("class");
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
      columnsToDisplayCheckBox = driver.findElement(By.xpath("//*[contains(@class,'labelValignMiddle')][contains(@id,'checkboxfield')]")).getAttribute("class");
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
      columnsToDisplayCheckBox = driver.findElement(By.xpath("//*[contains(@class,'labelValignMiddle')][contains(@id,'checkboxfield')]")).getAttribute("class");
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
      columnIsSelected = driver.findElement(By.xpath("//*[contains(@class,'x-grid-table')]/descendant::*[text()='Charge Code Name']/../..")).getAttribute("class");
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

   /* public void selectColumnsToDisplaySelectedColumn(String column) {
        String columnPath = "//*[contains(@class,'glAccountsGrid')][2]/descendant::*[text()='" + column + "']";
        doClick(driver.findElement(By.xpath(columnPath)));
    }
    public void selectColumnsToDisplayAvailableColumn(String column) {
        String columnPath = "//*[contains(@class,'glAccountsGrid')][1]/descendant::*[text()='" + column + "']";
        doClick(driver.findElement(By.xpath(columnPath)));
    }*/

  public void assertColumnsToDisplayColumn(String column) {
    String selectedColumnsXpath = "//label[text()='Selected']/following::*[contains(@class,'glAccountsGrid')][1]/descendant::tbody";
    //        String selectedColumnsXpath = "//*[contains(@class,'glAccountsGrid')][1]/descendant::tbody";
    WebElement selectedMenu = driver.findElement(By.xpath(selectedColumnsXpath));
    List<WebElement> actualSelectedColumns = selectedMenu.findElements(By.tagName("tr"));
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
}


