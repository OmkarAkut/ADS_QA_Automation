package webdriver.scripts.costing.unitcostquickcalculation.ucqcrvus;

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

public class UcqcCopyToQuickRvusPopupIsLaunchedAds1148 extends UcqcHelper {

  private static CostingMap ucqcMap;

  /** //Zephyr ticket ADS-1148 (Dev Story ADS-1015).  Last Updated 06-7-19 **/
  /**Regression test case ADS-6666 */
  @BeforeClass
  public static void setupScript() throws Exception,Throwable {
	  ExtentReport.reportCreate("UcqcCopyToQuickRvusPopupIsLaunchedAds1148", "webdriver.scripts.costing.unitcostquickcalculation.ucqcrvus", "UcqcCopyToQuickRvusPopupIsLaunchedAds1148");
    try {
		ucqcMap = BuildMap.getInstance(driver, CostingMap.class);
		System.out.println("Test Class: " + UcqcCopyToQuickRvusPopupIsLaunchedAds1148.class.getSimpleName());
		Login.loginUser("CostingDepartmentManager1");
		goToPage("Unit Cost Quick Calculation");
		doMaximizeWindow();
		 ExtentReport.logPass("PASS", "setupScript");
			} catch (Exception|AssertionError e) {
				ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
				fail(e.getMessage());
			}
  }

  @Test
  public void testUcqcCopyToQuickRvusPopupIsLaunched() throws Throwable {
    try {
      waitForAjaxExtJs();
      //Shilpa 19.09.2022 updated below line
//      ucqcPopulateRequiredFieldsToDisplayGrid("QA Cost Model", "QA MHFY05 After Vol Change", "150 Marina Medical Center", "2110 ICU", "Apr 2004 to Mar 2005");
      ucqcPopulateRequiredFieldsToDisplayGrid("QA Cost Model", "QA MHFY05 After Vol Change", "150 Marina Medical Center", "2110", "Apr 2004 to Mar 2005");
      assertElementIsEnabled(ucqcMap.getUnitCostQuickCalculationButtonApplySelections(), printout);
      doClick(ucqcMap.getUnitCostQuickCalculationButtonApplySelections());
      waitForSpinnerToEnd();
      waitForAjaxExtJs();
      doClick(ucqcMap.getUnitCostQuickCalculationButtonCopyToQuickRVUs());
      waitForAjaxExtJs();
//      Omkar 18/1/2024 : xpath changes for 11.2
//      assertElementIsDisplayed(driver.findElement(By.xpath("//*[@class='x-window-header-text x-window-header-text-default' and text()='Copy to Quick RVUs']")), printout);
      assertElementIsDisplayed(driver.findElement(By.xpath("//*[@class='x-title-text x-title-text-default x-title-item' and text()='Copy to Quick RVUs']")), printout);
      assertElementIsDisplayed(driver.findElement(By.xpath("//*[@class='listhelpLnk' and contains(@onclick, 'costing/Content/csucqccpfd.htm')]")), printout);
      assertElementIsDisplayed(driver.findElement(By.xpath("//*[contains(@class,'x-tool-close')]")), printout);
//      Omkar 18/1/2024 : xpath changes for 11.2
//      assertElementIsDisplayed(driver.findElement(By.xpath("//label[contains(@class,'fieldLabelCls') and text()='Copy Options']")), printout);
      assertElementIsDisplayed(driver.findElement(By.xpath("//span[contains(@class,'x-form-item-label-text') and text()='Copy Options']")), printout);
      
      assertElementIsDisplayed(driver.findElement(By.xpath("//*[@name='copyType']")), printout);

//      Omkar 18/1/2024 : xpath changes for 11.2
//      assertElementIsDisplayed(driver.findElement(By.xpath("//label[contains(@class,'x-form-item-label') and text()='Destination Cost Components']")), printout);
      assertElementIsDisplayed(driver.findElement(By.xpath("//span[contains(@class,'x-form-item-label-text') and text()='Destination Cost Components']")), printout);
      assertElementIsDisplayed(driver.findElement(By.xpath("//label[contains(@class,'x-component') and text()='Cost Components']")), printout);
      assertElementIsDisplayed(driver.findElement(By.xpath("//span[contains(@id,'button') and text()='Select All']")), printout);
      assertElementIsDisplayed(driver.findElement(By.xpath("//span[contains(@id,'button') and text()='Remove All']")), printout);
//      Omkar 18/1/2024 : xpath changes for 11.2
//      assertElementIsDisplayed(driver.findElement(By.xpath("//*[contains(@id,'ucqcgrid')]/div[@class='x-grid-view x-fit-item x-grid-view-default x-unselectable'] ")), printout);
      assertElementIsDisplayed(driver.findElement(By.xpath("//*[contains(@id,'ucqcgrid')]/div[contains(@class,'view-default x-unselectable x-scroller')]")), printout);

      assertElementIsDisplayed(driver.findElement(By.xpath("//span[contains(@id,'button') and text()='Copy and Save']")), printout);
      doClick(driver.findElement(By.xpath("//span[contains(@id,'button') and contains (text(),'Cancel & Close')]")));
      waitForAjaxExtJs();
//      Omkar 18/1/2024 : xpath changes for 11.2
//      doClosePageOnLowerBar("Unit Cost Quick...");
      doClosePageOnLowerBar("Unit Cost Quick Calculation");
      
      ExtentReport.logPass("PASS", "testUcqcCopyToQuickRvusPopupIsLaunched");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "testUcqcCopyToQuickRvusPopupIsLaunched", driver, e);
		fail(e.getMessage());
	}
  }
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}

