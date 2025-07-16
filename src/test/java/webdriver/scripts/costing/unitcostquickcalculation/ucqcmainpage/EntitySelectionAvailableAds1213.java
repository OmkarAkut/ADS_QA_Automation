package webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
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
  This script tests the Entity selection. Updated 8-14-19 
 * @throws Throwable */
  @BeforeClass
  public static void setupScript() throws Throwable {
	  
	  ExtentReport.reportCreate("EntitySelectionAvailableAds1213","webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage", "EntitySelectionAvailableAds1213");
		
    try {
		entity = BuildMap.getInstance(driver, CostingMap.class);
		dialogs = BuildMap.getInstance(driver, DialogsMap.class);
		System.out.println("Test Class: " + EntitySelectionAvailableAds1213.class.getSimpleName());
		Login.loginUser("CostingDepartmentManager1");
		doMaximizeWindow();
		goToPage("Unit Cost Quick Calculation");
		waitForAjaxExtJs();
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","setupScript", driver,e);
		fail(e.getMessage());
	} 
  }

  @Test
  public void test01ConfirmEntityDropdownIsDisabled() throws Throwable {
    try {
      waitForAjaxExtJs();
      assertElementIsDisabled(entity.getUnitCostQuickCalculationButtonSelect(), printout);
      ExtentReport.logPass("PASS", "test01ConfirmEntityDropdownIsDisabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test01ConfirmEntityDropdownIsDisabled", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test02ConfirmAsteriskDisplays() throws Throwable {
    try {
      waitForAjaxExtJs();
      assertAsteriskIsDisplayed("Entity");
      ExtentReport.logPass("PASS", "test02ConfirmAsteriskDisplays");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test02ConfirmAsteriskDisplays", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test03ConfirmEntityDisplaysNone() throws Throwable {
    try {
      waitForAjaxExtJs();
      assertDropdownPlaceholder("entityCode",printout);
      ExtentReport.logPass("PASS", "test03ConfirmEntityDisplaysNone");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test03ConfirmEntityDisplaysNone", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test04SelectUcqcCriteriaAndConfirmEntityIsEnabled() throws Throwable {
    try {
      //Changed Cost Model Scenario to one that had multiple Entities to verify test05 below
    	//venkat updated Cost Model Scenario text data 19.09.2022
      doDropdownSelectUsingOptionText(entity.getUnitCostQuickCalculationDropdownCostModel(), entity.getUnitCostQuickCalculationDropdownCostModelMenuList(), "Marina");
//      doDropdownSelectUsingOptionText(entity.getUnitCostQuickCalculationDropdownCostModelScenario(), entity.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(), "*CM2 TB MHFY05 No Price List");
      doDropdownSelectUsingOptionText(entity.getUnitCostQuickCalculationDropdownCostModelScenario(), entity.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(), "*CM2 TB MHFY05 No Price List - 2");
      
      
      
      assertElementIsEnabled(entity.getUnitCostQuickCalculationDropdownEntity(),printout);
      ExtentReport.logPass("PASS", "test04SelectUcqcCriteriaAndConfirmEntityIsEnabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test04SelectUcqcCriteriaAndConfirmEntityIsEnabled", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test05ConfirmOrderOfEntities() throws Throwable {
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
      ExtentReport.logPass("PASS", "test05ConfirmOrderOfEntities");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test05ConfirmOrderOfEntities", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test06ConfirmEntityFormat() throws Throwable {
    try {
      doDropdownSelectUsingOptionText(entity.getUnitCostQuickCalculationDropdownEntity(),entity.getUnitCostQuickCalculationDropdownEntityMenuList(),"150 Marina Medical Center");
      waitForAjaxExtJs();
      assertEntityFormat();
      ExtentReport.logPass("PASS", "test06ConfirmEntityFormat");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test06ConfirmEntityFormat", driver,e);
      fail(e.getMessage());
    }
  }

  public void assertEntityFormat() throws Throwable {
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
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
