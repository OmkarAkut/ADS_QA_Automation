package webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import ExtentReport.ExtentReport;
import webdriver.scripts.costing.unitcostquickcalculation.UnitCostQuickCalculationHelperStatic;
import webdriver.maps.CostingMap;
import webdriver.maps.GeneralElementsMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UcqcCalculateUcqcWithPublishedCmsAds1384 extends UnitCostQuickCalculationHelperStatic {

  static GeneralElementsMap generalMap;
  static CostingMap costingMap;
  static String[] requiredFields = {
    "QA Cost Model",
    "ADS-1384 Published",
    "150 Marina Medical Center",
    "3516",
     // "2516",
    "Dec 2004 to Dec 2004"
  };

  /**Test ticket ADS-1384.
   * Regression only - no corresponding dev ticket. 
 * @throws Throwable */
  @BeforeClass
  public static void setupScript() throws Throwable {
	  ExtentReport.reportCreate("UcqcCalculateUcqcWithPublishedCmsAds1384","webdriver.scripts.costing.unitcostquickcalculation.ucqccalculation", "UcqcCalculateUcqcWithPublishedCmsAds1384");
		
    try {
		generalMap = BuildMap.getInstance(driver, GeneralElementsMap.class);
		costingMap = BuildMap.getInstance(driver, CostingMap.class);
		System.out.println("Test Class: " + UcqcCalculateUcqcWithPublishedCmsAds1384.class.getSimpleName());
		evolveLoginStaticUser(Users.CostingDepartmentManager1);
		goToPage("Unit Cost Quick Calculation");
		doMaximizeWindow();
		waitForAjaxExtJs();
		ucqcDisplayChargeCodeGrid(requiredFields);
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL","setupScript", driver,e);
    	fail(e.getMessage());
		
	}
  }

  @Ignore
  @Test
  public void test01ConfirmSalariesAndWagesAreTheSameValuesAsQuickSalariesAndWages() {

  }

//  @Ignore
  @Test
  public void test02UpdateQuickRvuValuesAndCalculateAndAssertQuickValuesAreDifferent() {
    String[][] pairs = {
    		//Shilpa 21.09.2022 updates
//      {"4001061", "1"},
//      {"4003166", "10"},
//      {"6149009", "100"},
//      {"61450007", "10000000"},
      {"8460206", "1"},
      {"8462616", "10"},
      {"8462624", "100"},
      {"8462632", "10000000"},
    };

    for (String[] pair : pairs) {
      try {
    	 
//        String header = "Salaries and Wages Quick RVUs";
        String header = "Quick Salaries and Wages RVU";
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",        driver.findElement(By.xpath("//*[contains(@class,'column-header-text')][text()='Salaries & Wages Quick RVUs']"))
//);
      		  
//        driver.findElement(By.xpath("//*[contains(@class,'column-header-text')][text()='" + headerName + "']"))
        ucqcUpdateGridCellValue(pair[0], header, pair[1], printout);
      } catch (Throwable e) {
        fail(e.getMessage());
      }
    }
    doClick(costingMap.getUnitCostQuickCalculationButtonSaveQuickRVUs());
    doClick(costingMap.getUnitCostQuickCalculationButtonCalculate());
    ucqcWaitForSpinnerToEnd();
    //Confirm that Quick Salaries & Wages Costs are NOT the same as Salaries & Wages Costs.
    // Quick costs have changed
  }
  
  
  @AfterClass
 	public static void endtest() throws Exception {

 		ExtentReport.report.flush();

 	}
  
  
  
  
  
}
