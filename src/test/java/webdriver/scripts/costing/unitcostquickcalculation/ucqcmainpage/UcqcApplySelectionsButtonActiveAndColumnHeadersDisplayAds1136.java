package webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UcqcApplySelectionsButtonActiveAndColumnHeadersDisplayAds1136 extends UcqcHelper {

  private static CostingMap ucqcMap;
  String[] requiredFields = {"QA Cost Model", "QA MHFY05 After Vol Change", "150 Marina Medical Center", "2110  ICU", "Apr 2004 to Mar 2005"};

  /**Zephyr ticket ADS-1136 (updated 06-21-19). */
  @BeforeClass
  public static void setupScript() throws Exception {
    ucqcMap = BuildMap.getInstance(driver, CostingMap.class);
    System.out.println("Test Class: " + UcqcApplySelectionsButtonActiveAndColumnHeadersDisplayAds1136.class.getSimpleName());
    Login.loginUser("CostingDepartmentManager1");
    goToPage("Unit Cost Quick Calculation");
    doMaximizeWindow();
  }

  @Test
  public void test01UcqcPageUndoCostModelApplySelectionsButtonDisabled() {
    try {
      waitForAjaxExtJs();
      ucqcPopulateRequiredFieldsToDisplayGrid(requiredFields);
      assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      doDropdownSelectUsingOptionText(ucqcMap.getUnitCostQuickCalculationDropdownCostModel(),ucqcMap.getUnitCostQuickCalculationDropdownCostModelMenuList(),"<None>");
      assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
    } catch (Throwable e) {
      fail();
    }
  }

  @Test
  public void test02UcqcPageUndoCostModelScenarioApplySelectionsButtonDisabled() {
    try {
      ucqcPopulateRequiredFieldsToDisplayGrid(requiredFields);
      assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      doDropdownSelectUsingOptionText(ucqcMap.getUnitCostQuickCalculationDropdownCostModelScenario(),ucqcMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),"<None>");
      assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
    } catch (Throwable e) {
      fail();
    }
  }

  @Test
  public void test03UcqcPageUndoEntityFieldApplySelectionsButtonDisabled() {
    try {
      ucqcPopulateRequiredFieldsToDisplayGrid(requiredFields);
      assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      doDropdownSelectUsingOptionText(ucqcMap.getUnitCostQuickCalculationDropdownEntity(),ucqcMap.getUnitCostQuickCalculationDropdownEntityMenuList(),"<None>");
      assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
    } catch (Throwable e) {
      fail();
    }
  }

  @Test
  public void test04UcqcPageUndoDepartmentFieldApplySelectionsButtonDisabled() {
    try {
      ucqcPopulateRequiredFieldsToDisplayGrid(requiredFields);
      assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      selectDepartment("<None>");
      assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
    } catch (Throwable e) {
      fail();
    }
  }

  @Test
  public void test05UcqcPageUndoResultsStoredForFieldApplySelectionsButtonDisabled() {
    try {
      ucqcPopulateRequiredFieldsToDisplayGrid(requiredFields);
      assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      doDropdownSelectUsingOptionText(ucqcMap.getUnitCostQuickCalculationFieldResultsStoredFor(),ucqcMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"<None>");
      assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
    } catch (Throwable e) {
      fail();
    }
  }

  @Test
  public void test06UcqcPagePopulateRequiredFieldsAndClickApplySelectionsButtonToDisplayGrid() {
    try {
      ucqcPopulateRequiredFieldsToDisplayGrid(requiredFields);
      assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      doClick(ucqcMap.getUnitCostQuickCalculationButtonApplySelections());
      ucqcWaitForSpinnerToEnd();
      waitForAjaxExtJs();
      assertElementIsDisplayed(driver.findElement(By.xpath("//*[contains(@class,'x-column-header-text')][text()='Charge Code']")), printout);
      assertElementIsDisplayed(driver.findElement(By.xpath("//*[contains(@class,'x-column-header-text')][text()='Charge Code Name']")), printout);
      assertElementIsDisplayed(driver.findElement(By.xpath("//*[contains(@class,'x-column-header-text')][text()='Modifier']")), printout);
      assertElementIsDisplayed(driver.findElement(By.xpath("//*[contains(@class,'x-column-header-text')][text()='Total Unit Cost']")), printout);
      assertElementIsDisplayed(driver.findElement(By.xpath("//*[contains(@class,'x-column-header-text')][text()='Total Quick Cost']")), printout);
      assertElementIsDisplayed(driver.findElement(By.xpath("//*[contains(@class,'x-column-header-text')][text()='Total Change']")), printout);
    } catch (Throwable e) {
      fail();
    }
  }
}
