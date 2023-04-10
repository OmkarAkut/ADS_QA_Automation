package webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.ContractModelsHelper;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.mapbuilder.BuildMap;
/** Regression test ADS-5921 **/
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HorizontalScrollColumnsToDisplay extends UcqcHelper {
	 private static CostingMap costingMap;
	 static String TotalPagesToBeShown="/ 51";
	 static final String[] requiredFields = {
			    "QA Marina",
			    "ADS-302 LG By Month",
			    "150 Marina Medical Center",
			    "Q302",
			    "Apr 2004 to Apr 2004"
			  };
	 static ContractModelsHelper contractModelsHelper = new ContractModelsHelper();
	 static String[] columns= {"Charge Code Name",
			 "Total Unit Cost",
			 "Total Quick Cost",
			 "Total Change",
			 "Medical Supplies RVU",
			 "Quick Medical Supplies RVU",
			 "Medical Supplies Cost",
			 "Quick Medical Supplies Cost",
			 "Quick Tech RVU"
};
	@BeforeClass
	  public static void setupScript() throws Throwable {
		  
		  ExtentReport.reportCreate("HorizontalScrollColumnsToDisplay","webdriver.scripts.costing.unitcostquickcalculation.ucqcmainpage", "HorizontalScrollColumnsToDisplay");
			
	    try {
			costingMap = BuildMap.getInstance(driver, CostingMap.class);
			Login.loginUser("CostingDepartmentManager1");
			waitForDisplayedSpinnerToEnd();
			goToPage("Unit Cost Quick Calculation");
			ucqcDisplayChargeCodeGrid(requiredFields);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL","setupScript", driver,e);
			fail(e.getMessage());
			
		} 
	  }

		@Test
		public void test01UncheckAllColumnsAndRemoveColumnTodisplay() throws Throwable {
			try {
				doClick(driver.findElement(By.xpath("//label[text()='All']/preceding-sibling::input")));
				doClick(costingMap.getUnitCostQuickCalculationButtonColumnsToDisplaySelect());
				waitForMainPageTitle("Select Columns");
				ContractModelsHelper.highlightColumnsToDisplayColumn("Modifier");
				doClick(costingMap.getUnitCostQuickCalculationButtonColumnsToDisplayModalRemove());
				waitForAjaxExtJs();
				doClick(costingMap.getUnitCostQuickCalculationColumnsToDisplayModalApply());
				doClick(costingMap.getUnitCostQuickCalculationButtonApplySelections());
				waitForAjaxExtJs();
				assertElementIsNotDisplayed("//*[text()='Modifier']");
				ExtentReport.logPass("PASS", "test01UncheckAllColumnsAndRemoveColumnTodisplay");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test01UncheckAllColumnsAndRemoveColumnTodisplay", driver, e);
				fail(e.getMessage());
			}
		}

		@Test
		public void test02SelectMultipleColumnsAndVerifyappliedColumns() throws Throwable {
			try {
				doClick(costingMap.getUnitCostQuickCalculationButtonColumnsToDisplaySelect());
				waitForMainPageTitle("Select Columns");
				ContractModelsHelper.highlightColumnsToDisplayColumn("Charge Code Name");
				for (WebElement column : costingMap.getSelectColumnList()) {
					driver.findElement(
							By.xpath("//*[contains(@class,'glAccountsGrid')]/descendant::*[text()='Charge Code Name']"))
							.sendKeys(Keys.SHIFT, Keys.ARROW_DOWN);
					ContractModelsHelper
							.scrollToView("//*[contains(@class,'glAccountsGrid')]/descendant::*[text()='Tech Change']");
				}

				doClick(costingMap.getUnitCostQuickCalculationButtonColumnsToDisplayModalRemove());
				ContractModelsHelper.selectMultipleColumnsToDisplay(columns);
				doClick(costingMap.getUnitCostQuickCalculationColumnsToDisplayModalApply());
				waitForDisplayedSpinnerToEnd();
				doClick(costingMap.getUnitCostQuickCalculationButtonApplySelections());
				waitForAjaxExtJs();
				ContractModelsHelper.CompareListToArray(costingMap.getUcqcHeaderList(), columns);

				ExtentReport.logPass("PASS", "test01UncheckAllColumnsAndRemoveColumnTodisplay");
			} catch (Exception | AssertionError e) {
				ExtentReport.logFail("FAIL", "test01UncheckAllColumnsAndRemoveColumnTodisplay", driver, e);
				fail(e.getMessage());
			}
		}
//	@Test[resize column has issue]
	public void test01ResizColumnsAndVerifyTheSizeRetainedMovingToDifferentPages() throws Throwable {
		try {
//			assertElementIsDisplayedWithXpath("//div[contains(@id,'tbtext')][text()='"+TotalPagesToBeShown+"']");
//			 Actions action = new Actions(driver);
//		       driverDelay(400);
//		       System.out.println(driver.findElement(By.xpath("//div[@class='x-unselectable x-column-header-align-left x-box-item x-column-header x-unselectable-default x-column-header-sort-undefined x-column-header-sort-null x-column-header-first']")).getSize().getWidth());
//		       System.out.println(driver.findElement(By.xpath("//div[@class='x-unselectable x-column-header-align-left x-box-item x-column-header x-unselectable-default x-column-header-sort-undefined x-column-header-sort-null x-column-header-first']/div[1]")).getSize().getHeight()); 
//		       //Using appropriate x and y axis coordinates
////		       action.moveToElement()(driver.findElement(By.cssSelector("#numbercolumn-1245")), 122, 0).release().build().perform(); 
////		       action.moveToElement(driver.findElement(By.cssSelector("#numbercolumn-1246"))).clickAndHold().moveByOffset(296, 0).release().build(); 
////		       JavascriptExecutor js = (JavascriptExecutor) driver;
//		       js.executeScript("document.querySelector('#numbercolumn-1245').setAttribute('width',300)");
		       JavascriptExecutor js = (JavascriptExecutor) driver;

		       WebElement div =driver.findElement(By.xpath("(//div[contains(@id,'numbercolumn')])[1]"));

		       js.executeScript("arguments[0].style.width = '69px'", div);
//		       action.moveToElement(driver.findElement(By.xpath("//div[contains(@id,'headercontainer')]//following::div[@class='x-unselectable x-column-header-align-left x-box-item x-column-header x-unselectable-default x-column-header-sort-undefined x-column-header-sort-null x-column-header-first']")),120, 22).moveByOffset(86,22).perform();
		       ExtentReport.logPass("PASS", "test01ResizColumnsAndVerifyTheSizeRetainedMovingToDifferentPages");
	    } catch (Exception|AssertionError e) {
	    	ExtentReport.logFail("FAIL","test01ResizColumnsAndVerifyTheSizeRetainedMovingToDifferentPages", driver,e);
	      fail(e.getMessage());
	    }
	}
	@AfterClass
	  public static void endtest() throws Exception {

			ExtentReport.report.flush();

		}
}
