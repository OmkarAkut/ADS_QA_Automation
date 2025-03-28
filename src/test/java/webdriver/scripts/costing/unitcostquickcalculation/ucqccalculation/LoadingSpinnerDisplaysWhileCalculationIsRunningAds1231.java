package webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoadingSpinnerDisplaysWhileCalculationIsRunningAds1231
        extends UcqcHelper {

  static CostingMap totalQuickCost;
  static String[] ucqcRequiredFields = {
          "Marina",
//          "*CM1 TB MHFY05 After Vol Change_UCQC", ADS-21467
          "*CM2 TB MHFY05 No Price List - 2",
          "150 Marina Medical Center",
        // "3145  ENDOSCOPY",
          "3145", //venkat updated Department filed data 21.09.2022
          "Apr 2004 to Mar 2005"
  };
  String newValue = String.valueOf(javaGetRandomNumber(99, printout));

  /** Zephyr Test Ticket: ADS-1231 (dev story ADS-294).
   This script tests that the loading spinner displays while a Calculation is running. 
 * @throws Throwable */
  @BeforeClass
  public static void setupScript() throws Throwable {
	  
	  ExtentReport.reportCreate("LoadingSpinnerDisplaysWhileCalculationIsRunningAds1231","webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation", "LoadingSpinnerDisplaysWhileCalculationIsRunningAds1231");
	   
    try {
		totalQuickCost = BuildMap.getInstance(driver, CostingMap.class);
		System.out.println("Test Class: " + LoadingSpinnerDisplaysWhileCalculationIsRunningAds1231.class.getSimpleName());
		Login.loginUser("CostingDepartmentManager1");
		goToPage("Unit Cost Quick Calculation");
		waitForAjaxExtJs();
		ucqcDisplayChargeCodeGrid(ucqcRequiredFields);
		ExtentReport.logPass("PASS", "setupScript");
		
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "setupScript", driver, e);
		fail(e.getMessage());
	} 
    
    
    
  }

  @Test
  public void test02ModifyAndSaveQuickSalariesAndWagesValueAndAssertCalculateButtonIsEnabled() throws Throwable {
    try {
      ucqcUpdateGridCellValue(
              "3350022","Quick Salaries and Wages RVU", newValue, printout
      );
      assertElementIsEnabled(
              totalQuickCost.getUnitCostQuickCalculationButtonSaveQuickRVUs(), printout
      );
      doClick(totalQuickCost.getUnitCostQuickCalculationButtonSaveQuickRVUs());
      waitForAjaxExtJs();
      assertElementIsEnabled(totalQuickCost.getUnitCostQuickCalculationButtonCalculate(),printout);
      ExtentReport.logPass("PASS", "test02ModifyAndSaveQuickSalariesAndWagesValueAndAssertCalculateButtonIsEnabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test02ModifyAndSaveQuickSalariesAndWagesValueAndAssertCalculateButtonIsEnabled", driver, e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test03ConfirmLoadingSpinnerDisplaysWhileCalculationRuns() throws Throwable {
    try {
      waitForSpinnerToEnd();
      doClick(totalQuickCost.getUnitCostQuickCalculationButtonCalculate());
      final long startTiming = System.currentTimeMillis();
      assertElementIsDisplayed(
              driver.findElement(By.xpath("//*[contains(text(),'Loading...')]")), printout
      );
      assertElementIsDisabled(totalQuickCost.getUnitCostQuickCalculationButtonCalculate(),printout);
//      waitForSpinnerToEnd();
      //Shilpa updated for 11.2 on 2.6.2025
      waitForDisplayedSpinnerToEnd();
      final long finalTiming = System.currentTimeMillis();
      double totalRunTime = (finalTiming - startTiming) / 1000;
      System.out.println("Total Time for UCQC Calculation: " + totalRunTime + " seconds");
      ExtentReport.logPass("PASS", "test03ConfirmLoadingSpinnerDisplaysWhileCalculationRuns");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test03ConfirmLoadingSpinnerDisplaysWhileCalculationRuns", driver, e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test04NavigateToCalculationStatusPageAndAssertProgressIndicatorEquals100Percent() throws Throwable {
    try {
      goToPage("Calculation Status");
      waitForAjaxExtJs();
    
      
      CalculationHelper.waitForFirstRowCalculationBarToReach100Percent();
      WebElement calculationProgress = driver.findElement(By.xpath(
        "//span[text()='Progress']/following::*[@class='x-progress-text x-progress-text-back'][1]")
      );
     
      String calculationProgressValue = calculationProgress.getText();
      assertEquals(calculationProgressValue,"100%");
      ExtentReport.logPass("PASS", "test04NavigateToCalculationStatusPageAndAssertProgressIndicatorEquals100Percent");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL", "test04NavigateToCalculationStatusPageAndAssertProgressIndicatorEquals100Percent", driver, e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test05VerifyUcqcSuccessfullyReCalculates() throws Throwable {
	  
    try {
		doClosePageOnLowerBar("Calculation Status");
		waitForAjaxExtJs();
		assertElementIsEnabled(totalQuickCost.getUnitCostQuickCalculationButtonCalculate(),printout);
		doClick(totalQuickCost.getUnitCostQuickCalculationButtonCalculate());
		final long startTiming = System.currentTimeMillis();
		assertElementIsDisplayed(
		        driver.findElement(By.xpath("//*[contains(text(),'Loading...')]")), printout
		);
		assertElementIsDisabled(totalQuickCost.getUnitCostQuickCalculationButtonCalculate(),printout);
//		waitForSpinnerToEnd();
		 //Shilpa updated for 11.2 on 2.6.2025
	      waitForDisplayedSpinnerToEnd();
		final long finalTiming = System.currentTimeMillis();
		double totalRunTime = (finalTiming - startTiming) / 1000;
		System.out.println("Total Time for UCQC Calculation: " + totalRunTime + " seconds");
		 ExtentReport.logPass("PASS", "test05VerifyUcqcSuccessfullyReCalculates");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test05VerifyUcqcSuccessfullyReCalculates", driver, e);
	      fail(e.getMessage());
	} 
    
  }

  @Test
  public void test06AssertUcqcPagesIsDisplayedAfterReCalculation() throws Throwable {
	  
    try {
		assertElementIsEnabled(totalQuickCost.getUnitCostQuickCalculationButtonCalculate(),printout);
		 ExtentReport.logPass("PASS", "test06AssertUcqcPagesIsDisplayedAfterReCalculation");
		
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test06AssertUcqcPagesIsDisplayedAfterReCalculation", driver, e);
	      fail(e.getMessage());
		
	}
  }
  
  
  
  @AfterClass
 	public static void endtest() throws Exception {

 		ExtentReport.report.flush();

 	}
  
  
  
  
  
  
  
  
  
  
  
  
  
}
