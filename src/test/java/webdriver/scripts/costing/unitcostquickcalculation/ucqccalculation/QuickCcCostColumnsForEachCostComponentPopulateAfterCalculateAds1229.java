package webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ExtentReport.ExtentReport;
import webdriver.scripts.costing.unitcostquickcalculation.UnitCostQuickCalculationHelperStatic;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QuickCcCostColumnsForEachCostComponentPopulateAfterCalculateAds1229 extends UnitCostQuickCalculationHelperStatic {

  private static CostingMap quickCostColumns;

  /** ADS-1229: Quick CC Cost columns for each cost component populate after Calculate (dev story ADS-672).
  This script confirms that quick cost component cost columns populate for each cost component after Calculate.
 * @throws Throwable */
  /** Regression tc ADS-5924 **/
  @BeforeClass
  public static void setupScript() throws Throwable {
	  
	  ExtentReport.reportCreate("QuickCcCostColumnsForEachCostComponentPopulateAfterCalculateAds1229","webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation", "QuickCcCostColumnsForEachCostComponentPopulateAfterCalculateAds1229");
    try {
		quickCostColumns = BuildMap.getInstance(driver, CostingMap.class);
		System.out.println("Test Class: " + QuickCcCostColumnsForEachCostComponentPopulateAfterCalculateAds1229.class.getSimpleName());
		evolveLoginStaticUser(Users.CostingDepartmentManager1);
		goToPage("Unit Cost Quick Calculation");
		doMaximizeWindow();
		 ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","setupScript", driver,e);
    	fail(e.getMessage());
	}
    
  }

  @Test
  public void test00UpdateTotalQuickCostColumnPopulatesAfterCalculate() throws Throwable {
    try {
      assertElementIsDisabled(quickCostColumns.getUnitCostQuickCalculationButtonApplySelections(), printout);
      setUcqcCriteria("Marina", "*CM1 TB MHFY05 After Vol Change", "150 Marina Medical Center", "2130", "Jan 2005 to Jan 2005");//Shilpa 13.09.2022 updated depatment from 2130  PED ICU, issue with department grp pop up
      doClick(quickCostColumns.getUnitCostQuickCalculationButtonApplySelections());
      waitForAjaxExtJs();
      //ucqcWaitForSpinnerToEnd();
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
      ExtentReport.logPass("PASS", "test00UpdateTotalQuickCostColumnPopulatesAfterCalculate");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test00UpdateTotalQuickCostColumnPopulatesAfterCalculate", driver,e);
    	fail(e.getMessage());
    }
  }

  @Test
  public void test01VerifyCalculatedScenarioNameIsAppendedWithUcqcOnCalculationStatusPage() throws Throwable {
	  
    try {
		goToPage("Calculation Status");
		waitForAjaxExtJs();
		WebElement calculationStatusRow1ScenarioName = driver.findElement(By.xpath("//*[contains(@class,'x-grid-row')][1]/descendant::*[contains(@class,'x-grid-cell-inner')][5]"));
		System.out.println(calculationStatusRow1ScenarioName.getText());
		assertElementText(calculationStatusRow1ScenarioName, "*CM1 TB MHFY05 After Vol Change_UCQC", printout);
		 ExtentReport.logPass("PASS", "test01VerifyCalculatedScenarioNameIsAppendedWithUcqcOnCalculationStatusPage");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","test01VerifyCalculatedScenarioNameIsAppendedWithUcqcOnCalculationStatusPage", driver,e);
    	fail(e.getMessage());
	}
  }

  @AfterClass
 	public static void endtest() throws Exception {

 		ExtentReport.report.flush();

 	}
  
  
  
  
  
  
}

