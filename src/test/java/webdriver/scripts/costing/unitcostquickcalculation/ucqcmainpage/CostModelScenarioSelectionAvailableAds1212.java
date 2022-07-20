package webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CostModelScenarioSelectionAvailableAds1212 extends UcqcHelper {

  private static CostingMap costModelScenario;

  /** ADS-1212 (Dev Story ADS-365)
  This script tests the Cost Model Scenario drop down menu element.*/
  @BeforeClass
  public static void setupScript() throws Exception {
    costModelScenario = BuildMap.getInstance(driver, CostingMap.class);
    System.out.println("Test Class: " + CostModelScenarioSelectionAvailableAds1212.class.getSimpleName());
    Login.loginUser("CostingDepartmentManager1");
    goToPage("Unit Cost Quick Calculation");
    waitForAjaxExtJs();
  }

  @Test
  public void test01ConfirmCostModelScenarioIsDisabledByDefault() {
    try {
      waitForAjaxExtJs();
      assertUCQCDropdownIsDisabled("costModelScenarioId",printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test02ConfirmCostModelScenarioMenuListContainsNoneOption() {
    try {
      waitForAjaxExtJs();
      assertDropdownPlaceholder("costModelScenarioId",printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test03ConfirmRedAsteriskDisplaysForCostModelScenarioElementLabel() {
    try {
      waitForAjaxExtJs();
      assertAsteriskIsDisplayed("Cost Model Scenario");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test04ConfirmCostModelScenarioIsEnabledAfterRequiredFieldsArePopulated() {
    try {
      doDropdownSelectUsingOptionText(costModelScenario.getUnitCostQuickCalculationDropdownCostModel(),costModelScenario.getUnitCostQuickCalculationDropdownCostModelMenuList(),"Marina");
      assertUCQCDropdownIsEnabled("costModelScenarioId",printout);
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test05ConfirmCostModelScenarioDropdownMenuIsAscendingOrderWithNoneFirst() {
    try {
      doClick(costModelScenario.getUnitCostQuickCalculationDropdownCostModelScenario());
      List<String> cmsMenu = getDropdownMenuItemsTextAsArrayList(costModelScenario.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(), printout);
      List<String> sortedList = cmsMenu.stream().collect(Collectors.toList());  //make copy of actual list to sort according to requirements
      sortedList.remove(0);  //remove None
      Collections.sort(sortedList);  //sort
      sortedList.add(0, "<None>");  //add None back to top
      if (printout) {
        for (String liActual : cmsMenu) {
          System.out.println(liActual);
        }
        for (String liSorted : sortedList) {
          System.out.println(liSorted);
        }
      }
      assertEquals(sortedList, cmsMenu);  //compare actual list to sorted list
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }
}
