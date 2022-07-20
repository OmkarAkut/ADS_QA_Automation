package webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoadingSpinnerDisplaysWhileCalculationIsRunningAds1231
        extends UcqcHelper {

  static CostingMap totalQuickCost;
  static String[] ucqcRequiredFields = {
          "Marina",
          "*CM1 TB MHFY05 After Vol Change_UCQC",
          "150 Marina Medical Center",
          "3145  ENDOSCOPY",
          "Jan 2005 to Jan 2005"
  };
  String newValue = String.valueOf(javaGetRandomNumber(99, printout));

  /** Zephyr Test Ticket: ADS-1231 (dev story ADS-294).
   This script tests that the loading spinner displays while a Calculation is running. */
  @BeforeClass
  public static void setupScript() throws Exception {
    totalQuickCost = BuildMap.getInstance(driver, CostingMap.class);
    System.out.println(
      "Test Class: " + LoadingSpinnerDisplaysWhileCalculationIsRunningAds1231.class.getSimpleName()
    );
    Login.loginUser("CostingDepartmentManager1");
    goToPage("Unit Cost Quick Calculation");
    waitForAjaxExtJs();
    ucqcDisplayChargeCodeGrid(ucqcRequiredFields);
  }

  @Test
  public void test02ModifyAndSaveQuickSalariesAndWagesValueAndAssertCalculateButtonIsEnabled() {
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
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test03ConfirmLoadingSpinnerDisplaysWhileCalculationRuns() {
    try {
      waitForSpinnerToEnd();
      doClick(totalQuickCost.getUnitCostQuickCalculationButtonCalculate());
      final long startTiming = System.currentTimeMillis();
      assertElementIsDisplayed(
              driver.findElement(By.xpath("//*[contains(text(),'Loading...')]")), printout
      );
      assertElementIsDisabled(totalQuickCost.getUnitCostQuickCalculationButtonCalculate(),printout);
      waitForSpinnerToEnd();
      final long finalTiming = System.currentTimeMillis();
      double totalRunTime = (finalTiming - startTiming) / 1000;
      System.out.println("Total Time for UCQC Calculation: " + totalRunTime + " seconds");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test04NavigateToCalculationStatusPageAndAssertProgressIndicatorEquals100Percent() {
    try {
      goToPage("Calculation Status");
      waitForAjaxExtJs();
      WebElement calculationProgress = driver.findElement(By.xpath(
        "//span[text()='Progress']/following::*[@class='x-progress-text x-progress-text-back'][1]")
      );
      String calculationProgressValue = calculationProgress.getText();
      assertEquals(calculationProgressValue,"100%");
    } catch (Throwable e) {
      fail(e.getMessage());
    }
  }

  @Test
  public void test05VerifyUcqcSuccessfullyReCalculates() throws Exception {
    doClosePageOnLowerBar("Calculation Status");
    waitForAjaxExtJs();
    assertElementIsEnabled(totalQuickCost.getUnitCostQuickCalculationButtonCalculate(),printout);
    doClick(totalQuickCost.getUnitCostQuickCalculationButtonCalculate());
    final long startTiming = System.currentTimeMillis();
    assertElementIsDisplayed(
            driver.findElement(By.xpath("//*[contains(text(),'Loading...')]")), printout
    );
    assertElementIsDisabled(totalQuickCost.getUnitCostQuickCalculationButtonCalculate(),printout);
    waitForSpinnerToEnd();
    final long finalTiming = System.currentTimeMillis();
    double totalRunTime = (finalTiming - startTiming) / 1000;
    System.out.println("Total Time for UCQC Calculation: " + totalRunTime + " seconds");
  }

  @Test
  public void test06AssertUcqcPagesIsDisplayedAfterReCalculation() throws Exception {
    assertElementIsEnabled(totalQuickCost.getUnitCostQuickCalculationButtonCalculate(),printout);
  }
}
