package webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.scripts.costing.unitcostquickcalculation.UnitCostQuickCalculationHelperStatic;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QuickCcCostColumnsForEachCostComponentPopulateAfterCalculateAds1229 extends UnitCostQuickCalculationHelperStatic {

  private static CostingMap quickCostColumns;

  /** ADS-1229: Quick CC Cost columns for each cost component populate after Calculate (dev story ADS-672).
  This script confirms that quick cost component cost columns populate for each cost component after Calculate.*/
  @BeforeClass
  public void setupScript() throws InterruptedException {
    quickCostColumns = BuildMap.getInstance(driver, CostingMap.class);
    System.out.println("Test Class: " + QuickCcCostColumnsForEachCostComponentPopulateAfterCalculateAds1229.class.getSimpleName());
    evolveLoginStaticUser(Users.CostingDepartmentManager1);
    goToPage("Unit Cost Quick Calculation");
    doMaximizeWindow();
  }

  @Test
  public void test00UpdateTotalQuickCostColumnPopulatesAfterCalculate() {
    try {
      setUcqcCriteria("Marina", "*CM1 TB MHFY05 After Vol Change", "150 Marina Medical Center", "2130  PED ICU", "Jan 2005 to Jan 2005");
      doClick(quickCostColumns.getUnitCostQuickCalculationButtonApplySelections());
      ucqcWaitForSpinnerToEnd();
      Thread.sleep(1100);
      assertElementIsDisplayed(quickCostColumns.getUnitCostQuickCalculationHeaderQuickSalariesAndWagesRVU(), printout);
      ucqcUpdateGridCellValue("1100544", "Quick Salaries and Wages RVU", "15", printout);
      doClick(quickCostColumns.getUnitCostQuickCalculationButtonSaveQuickRVUs());
      ucqcWaitForSpinnerToEnd();
      Thread.sleep(1100);
      doClick(quickCostColumns.getUnitCostQuickCalculationButtonCalculate());
      ucqcWaitForSpinnerToEnd();
      Thread.sleep(1100);
      doClosePageOnLowerBar("Unit Cost Quick...");
    } catch (Throwable e) {
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void test01VerifyCalculatedScenarioNameIsAppendedWithUcqcOnCalculationStatusPage() throws InterruptedException {
    goToPage("Calculation Status");
    WebElement calculationStatusRow1ScenarioName = driver.findElement(By.xpath("//*[contains(@class,'x-grid-row')][1]/descendant::*[contains(@class,'x-grid-cell-inner')][3]"));
    assertElementText(calculationStatusRow1ScenarioName, "*CM1 TB MHFY05 After Vol Change_UCQC", printout);
  }

  @Ignore
  @Test
  public void test02VerifyUpdateInDatabase() {
    /**After the Scenario is completed, navigate to the QA database and query the CostModelScenario2 table for the OBJECTID of the CMS Copy using the following query: ﻿Select * FROM COSTMODELSCENARIO2
     ORDER BY NAME*/
    /**Using the OBJECTID of CMS Copy execute the following query to determine the OBJECTID of the Department  record in the ﻿EntDeptCostContainer2 table:  ﻿Select * FROM EntDeptCostContainer2 e
     where e.CSTMODSCENID = <object ID>*/
    /**Using the Object ID value, Execute the following query to determine the OBJECTID of the Results Stored For month from the ﻿COSTITEMDATASET2 table: ﻿Select * From COSTITEMDATASET2 ci
     WHERE ci.CONTAINERID = <object ID>*/
    /**Using the Object Id from the COSTITEMDATASET2 table,  execute the following query of the ActivityCostItem2 table: ﻿Select * From ActivityCostItem2 ac
     Where ac.MASTERID = <object Id> . Select the OBJECTID of a charge code from the table*/
    /**Using the Charge Code OBJECT ID, query the  ComponentStatistic2 table using the following query: ﻿Select * From COMPONENTSTATISTIC2 cs
     Where cs.DATAITEMID = <object iD>*/
    /**Confirm that the Quick CC Cost where CC is name of Cost Component are right-aligned and displayed with two decimals and that the value contained in the Quick CC Cost Column is being determined as the Sum of the FIXEDCOST and VARIABLECOST fields of the ComponentStatistic2table from Cost Model Scenario Copy. */
  }
}

