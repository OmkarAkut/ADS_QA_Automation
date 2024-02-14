package Regression;

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
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UcqcApplySelectionsButtonActiveAndColumnHeadersDisplayAds1136 extends UcqcHelper {

  private static CostingMap ucqcMap;
  //Shilpa 19.09.2022 below line is updated
//  String[] requiredFields = {"QA Cost Model", "QA MHFY05 After Vol Change", "150 Marina Medical Center", "2110  ICU", "Apr 2004 to Mar 2005"};
  String[] requiredFields = {"QA Cost Model", "QA MHFY05 After Vol Change", "150 Marina Medical Center", "2110", "Apr 2004 to Mar 2005"};

  /**Zephyr ticket ADS-1136 (updated 06-21-19). ,ADS-5926
 * @throws Throwable */
  @BeforeClass
  public static void setupScript() throws Throwable {
	  
	  ExtentReport.reportCreate("UcqcApplySelectionsButtonActiveAndColumnHeadersDisplayAds1136","webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage", "UcqcApplySelectionsButtonActiveAndColumnHeadersDisplayAds1136");
		
    try {
		ucqcMap = BuildMap.getInstance(driver, CostingMap.class);
		System.out.println("Test Class: " + UcqcApplySelectionsButtonActiveAndColumnHeadersDisplayAds1136.class.getSimpleName());
		Login.loginUser("CostingDepartmentManager1");
		goToPage("Unit Cost Quick Calculation");
		doMaximizeWindow();
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","setupScript", driver,e);
		fail(e.getMessage());
	} 
  }
//ADS-5926 all steps
  @Test
  public void test01UcqcPageUndoCostModelApplySelectionsButtonDisabled() throws Throwable {
    try {
		try {
		  waitForAjaxExtJs();
		  ucqcPopulateRequiredFieldsToDisplayGrid(requiredFields);
		  assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
		  doDropdownSelectUsingOptionText(ucqcMap.getUnitCostQuickCalculationDropdownCostModel(),ucqcMap.getUnitCostQuickCalculationDropdownCostModelMenuList(),"<None>");
		  assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
		  
		} catch (Exception|AssertionError e) {
			try {
				ExtentReport.logFail("FAIL","setupScript", driver,e);
			} catch (Throwable e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			fail(e.getMessage());
		}
		ExtentReport.logPass("PASS", "test01UcqcPageUndoCostModelApplySelectionsButtonDisabled");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","test01UcqcPageUndoCostModelApplySelectionsButtonDisabled", driver,e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test02UcqcPageUndoCostModelScenarioApplySelectionsButtonDisabled() throws Throwable {
    try {
      ucqcPopulateRequiredFieldsToDisplayGrid(requiredFields);
      assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      doDropdownSelectUsingOptionText(ucqcMap.getUnitCostQuickCalculationDropdownCostModelScenario(),ucqcMap.getUnitCostQuickCalculationDropdownCostModelScenarioMenuList(),"<None>");
      assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      ExtentReport.logPass("PASS", "test02UcqcPageUndoCostModelScenarioApplySelectionsButtonDisabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test02UcqcPageUndoCostModelScenarioApplySelectionsButtonDisabled", driver,e);
		fail(e.getMessage());
     
    }
  }

  @Test
  public void test03UcqcPageUndoEntityFieldApplySelectionsButtonDisabled() throws Throwable {
    try {
      ucqcPopulateRequiredFieldsToDisplayGrid(requiredFields);
      assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      doDropdownSelectUsingOptionText(ucqcMap.getUnitCostQuickCalculationDropdownEntity(),ucqcMap.getUnitCostQuickCalculationDropdownEntityMenuList(),"<None>");
      assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      ExtentReport.logPass("PASS", "test03UcqcPageUndoEntityFieldApplySelectionsButtonDisabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test03UcqcPageUndoEntityFieldApplySelectionsButtonDisabled", driver,e);
		fail(e.getMessage());
    }
  }

  @Test
  public void test04UcqcPageUndoDepartmentFieldApplySelectionsButtonDisabled() throws Throwable {
    try {
      ucqcPopulateRequiredFieldsToDisplayGrid(requiredFields);
      assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      selectDepartment("<None>");
      assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      ExtentReport.logPass("PASS", "test04UcqcPageUndoDepartmentFieldApplySelectionsButtonDisabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test04UcqcPageUndoDepartmentFieldApplySelectionsButtonDisabled", driver,e);
		fail(e.getMessage());
    }
  }

  @Test
  public void test05UcqcPageUndoResultsStoredForFieldApplySelectionsButtonDisabled() throws Throwable {
    try {
      ucqcPopulateRequiredFieldsToDisplayGrid(requiredFields);
      assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      doDropdownSelectUsingOptionText(ucqcMap.getUnitCostQuickCalculationFieldResultsStoredFor(),ucqcMap.getUnitCostQuickCalculationDropdownResultsStoredForMenuList(),"<None>");
      assertElementIsDisabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      ExtentReport.logPass("PASS", "test05UcqcPageUndoResultsStoredForFieldApplySelectionsButtonDisabled");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test05UcqcPageUndoResultsStoredForFieldApplySelectionsButtonDisabled", driver,e);
		fail(e.getMessage());
    }
  }

  @Test
  public void test06UcqcPagePopulateRequiredFieldsAndClickApplySelectionsButtonToDisplayGrid() throws Throwable {
    try {
      ucqcPopulateRequiredFieldsToDisplayGrid(requiredFields);
      assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(),printout);
      doClick(ucqcMap.getUnitCostQuickCalculationButtonApplySelections());
      ucqcWaitForSpinnerToEnd();
      waitForAjaxExtJs();
      assertElementIsDisplayed(driver.findElement(By.xpath("//*[contains(@class,'x-column-header-text')][text()='Charge Code']")), printout);
      assertElementIsDisplayed(driver.findElement(By.xpath("//*[contains(@class,'x-column-header-text')][text()='Charge Code Name']")), printout);
      assertElementIsDisplayed(driver.findElement(By.xpath("//*[contains(@class,'x-column-header-text')][text()='Modifier']")), printout);
      assertElementIsDisplayed(driver.findElement(By.xpath("//*[contains(@class,'x-column-header-text')][text()='Total Unit Cost']")), printout);
      assertElementIsDisplayed(driver.findElement(By.xpath("//*[contains(@class,'x-column-header-text')][text()='Total Quick Cost']")), printout);
      assertElementIsDisplayed(driver.findElement(By.xpath("//*[contains(@class,'x-column-header-text')][text()='Total Change']")), printout);
      ExtentReport.logPass("PASS", "test06UcqcPagePopulateRequiredFieldsAndClickApplySelectionsButtonToDisplayGrid");
    } catch (Exception|AssertionError e) {
    	ExtentReport.logFail("FAIL","test06UcqcPagePopulateRequiredFieldsAndClickApplySelectionsButtonToDisplayGrid", driver,e);
		fail(e.getMessage());
     
    }
  }
  
  
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
