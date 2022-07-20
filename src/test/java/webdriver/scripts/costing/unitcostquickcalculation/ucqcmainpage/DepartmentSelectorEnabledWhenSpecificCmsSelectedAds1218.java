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
public class DepartmentSelectorEnabledWhenSpecificCmsSelectedAds1218 extends UcqcHelper {

  private static CostingMap department;

  /** Zephyr Test Ticket: ADS-1218. Dev story ADS-573 (which fixed ADS-574, so covered as well).
  This script tests that the department list populates when specific Cost Model Scenarios are used.*/
  @BeforeClass
  public static void setupScript() throws Exception {
    department = BuildMap.getInstance(driver, CostingMap.class);
    System.out.println("Test Class: " + DepartmentSelectorEnabledWhenSpecificCmsSelectedAds1218.class.getSimpleName());
    Login.loginUser("CostingDepartmentManager1");
    doMaximizeWindow();
    goToPage("Unit Cost Quick Calculation");
  }

  @Test
  public void  test01SetUcqcCriteriaAndAssertSelectButtonIsEnabled() {
    try {
      waitForAjaxExtJs();
      doDropdownSelectUsingOptionText(department.getUnitCostQuickCalculationDropdownCostModel(),department.getUnitCostQuickCalculationDropdownCostModelMenuList(),"Phys Cost Mod Baseline");
      doDropdownSelectUsingOptionText(department.getUnitCostQuickCalculationDropdownCostModelScenario(),department.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),"Phys Cost TC CMS - No Dept Group");
      doDropdownSelectUsingOptionText(department.getUnitCostQuickCalculationDropdownEntity(),department.getUnitCostQuickCalculationDropdownEntityMenuList(),"150T TLC 150");
      assertElementIsEnabled(department.getUnitCostQuickCalculationButtonSelect(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test02ConfirmDepartmentsAreAvailable() {
    try {
      doClick(department.getUnitCostQuickCalculationButtonSelect());
      waitForAjaxExtJs();
      assertDepartmentListIsNotNull();
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test03ChangeUcqcCriteriaAndAssertSelectButtonIsEnabled() {
    try {
      doClick(department.getUnitCostQuickCalculationDepartmentButtonCancelAndClose());
      doDropdownSelectUsingOptionText(department.getUnitCostQuickCalculationDropdownCostModel(),department.getUnitCostQuickCalculationDropdownCostModelMenuList(),"Bug Test US17062PriceListsbyEntity");
      doDropdownSelectUsingOptionText(department.getUnitCostQuickCalculationDropdownCostModelScenario(),department.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),"Bug Test Pricing");
      doDropdownSelectUsingOptionText(department.getUnitCostQuickCalculationDropdownEntity(),department.getUnitCostQuickCalculationDropdownEntityMenuList(),"JKENTITY JKENTITY");
      assertElementIsEnabled(department.getUnitCostQuickCalculationButtonSelect(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test04ConfirmDepartmentsAreAvailable() {
    try {
      doClick(department.getUnitCostQuickCalculationButtonSelect());
      waitForAjaxExtJs();
      assertDepartmentListIsNotNull();
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test05ChangeUcqcCriteriaAndAssertSelectButtonIsEnabled() {
    try {
      doClick(department.getUnitCostQuickCalculationDepartmentButtonCancelAndClose());
      doDropdownSelectUsingOptionText(department.getUnitCostQuickCalculationDropdownCostModel(),department.getUnitCostQuickCalculationDropdownCostModelMenuList(),"Marina");
      doDropdownSelectUsingOptionText(department.getUnitCostQuickCalculationDropdownCostModelScenario(),department.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),"*CM1 TB MHFY05 After Vol Change");
      doDropdownSelectUsingOptionText(department.getUnitCostQuickCalculationDropdownEntity(),department.getUnitCostQuickCalculationDropdownEntityMenuList(),"150 Marina Medical Center");
      assertElementIsEnabled(department.getUnitCostQuickCalculationButtonSelect(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test06ConfirmDepartmentsAreAvailable() {
    try {
      doClick(department.getUnitCostQuickCalculationButtonSelect());
      waitForAjaxExtJs();
      assertDepartmentListIsNotNull();
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }
}
