package webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.UcqcHelper;
import webdriver.scripts.costing.unitcostquickcalculation.UnitCostQuickCalculationHelperStatic;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HideSelectionsSoMoreVerticalRealEstateAds1211 extends UcqcHelper {

  private static CostingMap costingMap;
  //static String[] requiredFields = {"Marina","*TB MHFY05 Before Vol Change","150 Marina Medical Center","2016  CHILDREN","Apr 2004 to Mar 2005"};

  /** Test ticket: ADS-1211 (dev story ADS-279).
   * This script tests the hide and show selections button on ucqc page. 
 * @throws Throwable */
  @BeforeClass
  public static void setupScript() throws Throwable {
	  
	  ExtentReport.reportCreate("HideSelectionsSoMoreVerticalRealEstateAds1211","webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage", "HideSelectionsSoMoreVerticalRealEstateAds1211");
		
    try {
		costingMap = BuildMap.getInstance(driver, CostingMap.class);
		System.out.println("Test Class: " + HideSelectionsSoMoreVerticalRealEstateAds1211.class.getSimpleName());
		Login.loginUser("CostingDepartmentManager1");
		doMaximizeWindow();
		goToPage("Unit Cost Quick Calculation");
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","setupScript", driver,e);
		fail(e.getMessage());
		
	} 
  }

  @Test
  public void test01ConfirmHideButtonIsDisplayedAndEnabledByDefault() throws Throwable {
    try {
      waitForAjaxExtJs();
      assertThatElementIsDisplayed(costingMap.getUnitCostQuickCalculationButtonHide());
      assertElementIsEnabled(costingMap.getUnitCostQuickCalculationButtonHide(), printout);
      assertElementIsDisplayed(costingMap.getUnitCostQuickCalculationDropdownCostModel(), printout);
      ExtentReport.logPass("PASS", "test01ConfirmHideButtonIsDisplayedAndEnabledByDefault");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test01ConfirmHideButtonIsDisplayedAndEnabledByDefault", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test02aClickHideButtonAndAssertFieldsAreNotDisplayed() throws Throwable {
	  
    try {
		doClick(costingMap.getUnitCostQuickCalculationButtonHide());
		waitForAjaxExtJs();
		String isDisplay = driver.findElement(By.xpath("//div[contains(@class,'benefitPlanListPanel')]")).getAttribute("style");
		System.out.println(isDisplay);
		assertThat(isDisplay, containsString("display: none"));
		ExtentReport.logPass("PASS", "test02aClickHideButtonAndAssertFieldsAreNotDisplayed");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","test02aClickHideButtonAndAssertFieldsAreNotDisplayed", driver,e);
	      fail(e.getMessage());
		
	}
  }

  @Test
  public void test02bAfterHideButtonIsClickedAssertButtonsAreDisplayed() throws Throwable {
    try {
      assertThatElementIsDisplayed(costingMap.getUnitCostQuickCalculationButtonApplySelections());
      assertThatElementIsDisplayed(costingMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs());
      assertThatElementIsDisplayed(costingMap.getUnitCostQuickCalculationButtonClearQuickRVUsAndSave());
      assertThatElementIsDisplayed(costingMap.getUnitCostQuickCalculationButtonUndo());
      assertThatElementIsDisplayed(costingMap.getUnitCostQuickCalculationButtonSaveQuickRVUs());
      assertThatElementIsDisplayed(costingMap.getUnitCostQuickCalculationButtonCalculate());
      assertThatElementIsDisplayed(costingMap.getUnitCostQuickCalculationButtonOverwriteRVUMaintenance());
      ExtentReport.logPass("PASS", "test02bAfterHideButtonIsClickedAssertButtonsAreDisplayed");
    
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test02bAfterHideButtonIsClickedAssertButtonsAreDisplayed", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test03AssertShowButtonIsDisplayed() throws Throwable {
    try {
      assertThatElementIsDisplayed(costingMap.getUnitCostQuickCalculationButtonShow());
      ExtentReport.logPass("PASS", "test03AssertShowButtonIsDisplayed");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test03AssertShowButtonIsDisplayed", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test04ClickShowButtonAndAssertThatFieldsAreDisplayed() throws Throwable {
    try {
      doClick(costingMap.getUnitCostQuickCalculationButtonShow());
      waitForAjaxExtJs();
      assertThatElementIsDisplayed(costingMap.getUnitCostQuickCalculationDropdownCostModel());
      assertThatElementIsDisplayed(driver.findElement(By.xpath("//div[contains(@class,'benefitPlanListPanel')]")));
      ExtentReport.logPass("PASS", "test04ClickShowButtonAndAssertThatFieldsAreDisplayed");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test04ClickShowButtonAndAssertThatFieldsAreDisplayed", driver,e);
      fail(e.getMessage());
    }
  }

  @Test
  public void test05AssertHideButtonIsDisplayed() throws Throwable {
    try {
      waitForAjaxExtJs();
      assertElementIsDisplayed(costingMap.getUnitCostQuickCalculationButtonHide(),printout);
      ExtentReport.logPass("PASS", "test05AssertHideButtonIsDisplayed");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test05AssertHideButtonIsDisplayed", driver,e);
      fail(e.getMessage());
    }
  }
  
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
  
  
  
  
  
  
  
}
