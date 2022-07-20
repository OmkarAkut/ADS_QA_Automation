package webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EntitySelectionAvailableAds1213 extends UcqcHelper {

  private static CostingMap entity;
  private static DialogsMap dialogs;

  /** Automates ADS-1213 (Jira Dev Story: ADS-366)
  This script tests the Entity selection. Updated 8-14-19 */
  @BeforeClass
  public static void setupScript() throws Exception {
    entity = BuildMap.getInstance(driver, CostingMap.class);
    dialogs = BuildMap.getInstance(driver, DialogsMap.class);
    System.out.println("Test Class: " + EntitySelectionAvailableAds1213.class.getSimpleName());
    Login.loginUser("CostingDepartmentManager1");
    doMaximizeWindow();
    goToPage("Unit Cost Quick Calculation");
    waitForAjaxExtJs();
  }

  @Test
  public void test01ConfirmEntityDropdownIsDisabled() {
    try {
      waitForAjaxExtJs();
      assertElementIsDisabled(entity.getUnitCostQuickCalculationButtonSelect(), printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test02ConfirmAsteriskDisplays() {
    try {
      waitForAjaxExtJs();
      assertAsteriskIsDisplayed("Entity");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test03ConfirmEntityDisplaysNone() {
    try {
      waitForAjaxExtJs();
      assertDropdownPlaceholder("entityCode",printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test04SelectUcqcCriteriaAndConfirmEntityIsEnabled() {
    try {
      //Changed Cost Model Scenario to one that had multiple Entities to verify test05 below
      doDropdownSelectUsingOptionText(entity.getUnitCostQuickCalculationDropdownCostModel(), entity.getUnitCostQuickCalculationDropdownCostModelMenuList(), "Marina");
      doDropdownSelectUsingOptionText(entity.getUnitCostQuickCalculationDropdownCostModelScenario(), entity.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(), "*CM2 TB MHFY05 No Price List");
      assertElementIsEnabled(entity.getUnitCostQuickCalculationDropdownEntity(),printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test05ConfirmOrderOfEntities() {
    try {
      doClick(entity.getUnitCostQuickCalculationDropdownEntity());
      List<String> entityMenu = getDropdownMenuItemsTextAsArrayList(entity.getUnitCostQuickCalculationDropdownEntityMenuList(), printout);
      List<String> sortedList = entityMenu.stream().collect(Collectors.toList());  //make copy of actual list to sort according to requirements
      sortedList.remove(0);  //remove None
      Collections.sort(sortedList);  //sort
      sortedList.add(0, "<None>");  //add None back to top
      if (printout) {
        for (String liActual : entityMenu) {
          System.out.println(liActual);
        }
        for (String liSorted : sortedList) {
          System.out.println(liSorted);
        }
      }
      doClick(entity.getUnitCostQuickCalculationDropdownEntity());
      assertEquals(sortedList, entityMenu);  //compare actual list to sorted list
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test06ConfirmEntityFormat() {
    try {
      doDropdownSelectUsingOptionText(entity.getUnitCostQuickCalculationDropdownEntity(),entity.getUnitCostQuickCalculationDropdownEntityMenuList(),"150 Marina Medical Center");
      waitForAjaxExtJs();
      assertEntityFormat();
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  public void assertEntityFormat() {
    try {
      String entityValue = entity.getUnitCostQuickCalculationDropdownEntity().getAttribute("value");
      if (printout) {
        System.out.println(entityValue);
      }
      assertTrue(entityValue.matches("\\d{3}\\s[a-zA-Z]+") || entityValue.matches("\\d{3}\\s[a-zA-Z]+\\s[a-zA-Z]+\\s[a-zA-Z]+"));
    } catch (Throwable e) {
      fail("Format is not XXX NNNNN, where XXX is the Entity Code and NNNNN is the Entity Name");
    }
  }
}
