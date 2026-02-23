package webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation;

import static org.junit.Assert.assertTrue;
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
import webdriver.corehelpers.AdsHelper;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QuickCcCostColumnsForEachCostComponentPopulateAfterCalculateAds1229 extends UnitCostQuickCalculationHelperStatic {
  private static CostingMap quickCostColumns;
  private static ModelLibraryMap modelMap;
  private static ContractingMap contractMap;
	String[] filter = { "Scenario Name", "Is", "Equal To", "*CM1 TB MHFY05 After Vol Change_UCQC" };
	
 
  /** ADS-1229: Quick CC Cost columns for each cost component populate after Calculate (dev story ADS-672).
  This script confirms that quick cost component cost columns populate for each cost component after Calculate.
 * @throws Throwable */
  /** Regression tc ADS-5924,ADS-6665 update tc  **/
  @BeforeClass
  public static void setupScript() throws Throwable {
	 
	  ExtentReport.reportCreate("QuickCcCostColumnsForEachCostComponentPopulateAfterCalculateAds1229","webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation", "QuickCcCostColumnsForEachCostComponentPopulateAfterCalculateAds1229");
    try {
		quickCostColumns = BuildMap.getInstance(driver, CostingMap.class);
		modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
		contractMap=BuildMap.getInstance(driver, ContractingMap.class);
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
//ADS -5924
  @Test
  public void test00UpdateTotalQuickCostColumnPopulatesAfterCalculate_5924() throws Throwable {
    try {
      assertElementIsDisabled(quickCostColumns.getUnitCostQuickCalculationButtonApplySelections(), printout);
      setUcqcCriteria("Marina", "*CM1 TB MHFY05 After Vol Change", "150 Marina Medical Center", "2130", "Jan 2005 to Mar 2005");//Shilpa 13.09.2022 updated depatment from 2130  PED ICU, issue with department grp pop up
      //Data update : Shilpa: 01.20.2026
//      setUcqcCriteria("Marina", "*CM1 TB MHFY05 After Vol Change", "150 Marina Medical Center", "2130", "Jan 2005 to Jan 2005");
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
//ADS-6665 
  @Test
  public void test01VerifyCalculatedScenarioNameIsAppendedWithUcqcOnCalculationStatusPage_6665() throws Throwable {
	  
    try {
		goToPage("Calculation Status");
		waitForAjaxExtJs();
		WebElement calculationStatusRow1ScenarioName = driver.findElement(By.xpath("//*[contains(@class,'x-grid-row')][1]/descendant::*[contains(@class,'x-grid-cell-inner')][2]"));
		System.out.println(calculationStatusRow1ScenarioName.getText());
		assertElementText(calculationStatusRow1ScenarioName, "*CM1 TB MHFY05 After Vol Change_UCQC", printout);
		doClick("(//*[contains(@id,'calculationstatus') and contains(@id,'header')]/..//a[text()='View'])[2]");
//		waitForPresenceOfElement("(//*[contains(@id,'calculationstatus') and contains(@id,'header')]/..//span[text()='View'])[2]");
//		driver.findElement(By.xpath("(//*[contains(@id,'calculationstatus') and contains(@id,'header')]/..//span[text()='View'])[2]")).click();
		waitForDisplayedSpinnerToEnd();
		driverDelay(2000);
		assertElementIsDisplayedWithXpath("//*[contains(text(),'*CM1 TB MHFY05 After Vol Change')]");
		doClick("//div[text()='View Log']//following::span[text()='Cancel']");
		doClosePageOnLowerBar("Calculation Status");
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

