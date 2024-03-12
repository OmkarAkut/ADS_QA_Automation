package webdriver.scripts.contracting;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.CostingMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CalculateExistingPublishedContractAds1447 extends CalculationHelper {

  static final String modelName = "v10 REGRESSION Published Contract";
  static final String status = "Completed";
  static final String logStatus = "Completed";
  static final String recordsProcessed = "10359";
  static final String recordsPending = "0";
  static final String totalRecords = "10359";
  final String expectedLogViewTitle = modelName + "\\Contracting\\Published Contract Calculation";
  String logDetailTotalEfrs = "Total EFRs to be processed: 10359";
  String[] logViewDetails = {
          "Number of Encounter Financial Records to Process: 10359",
          "Non-Critical Warnings: 433",
          "Encounter Financial Records Calculated to Zero: 218"
  };
  private static ModelLibraryMap modelMap;


  /** Regression: Automated test script for ADS-6433,ADS-6085 **/

  @BeforeClass
  public static void setupScript() throws Exception,Throwable {
	  ExtentReport.reportCreate("CalculateExistingPublishedContractAds1447", "webdriver.scripts.contracting", "CalculateExistingPublishedContractAds1447");
    try {
		modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
		Login.loginUser("ContractAnalyst1");
		goToPage("Contract Models");
		waitForSpinnerToEnd();
		Thread.sleep(4000);
		doClick(CostingMap.getContractingName);
//		doClick(CostingMap.getContractingName());
		List<WebElement> elements = driver.findElements(By.xpath("//div[contains(@id,'treeview')]//table"));
		System.out.println(elements.size());
		for(int i=1;i<elements.size();i++) {
//			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//div[contains(@id,'treeview')]//table")));
			 Actions action=new Actions(driver);
				action.moveToElement(driver.findElement(By.xpath("(//div[contains(@id,'treeview')]//table)["+i+"]"))).click().perform();
			try {
				if(i==53) {
				if(driver.findElement(By.xpath("(//div[contains(@id,'treeview')]//table//span)["+i+"]")).getText().equals("Automation")) {
					doClick(CostingMap.getContractingAutomationName);
					doClick(CostingMap.getContractingAutomationRegressName);
					Thread.sleep(4000);
					filterAndSelectContractModelFromContractModelLibrary(modelName);
					break;
				}
				}
				else {
					continue;
				}
			} catch (Exception e) {
				
			}
			Thread.sleep(500); 
			continue;
		}
//		doClick(CostingMap.getContractingAutomationName);
		
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
	ExtentReport.logFail("FAIL", "setupScript", driver, e);
	fail(e.getMessage());
	}
  }
//ADS-6433
  @Test
  public void test01RunPublishedContractAndVerifyStatusPageDetailsAfterCompleted_ADS_6433_() throws Throwable, InterruptedException {
    try {
		waitForSpinnerToEnd();
		waitForAjaxExtJs();
		modelMap.getModelLibraryContractingButtonCalculate().click();
		waitForCalculationToEnd(1000);
//		driver.findElement(By.xpath("//button/span[text()='Refresh']")).click();
		driver.findElement(By.xpath("//span/span[text()='Refresh']")).click();//Shilpa: update xpath 11.2
//		waitForPresenceOfElement("//div[2]/div/div[4]/div/table/tbody/tr[2]/td[17]/div");  //total records xpath
		waitForPresenceOfElement("(//div[@class='x-grid-item-container'])[3]//table//tbody/tr/td[2]/div");//shilpa: xpath update 11.9.2023 for 11.2
		Thread.sleep(200);
		assertTrue(getCalculationStatusMyStatusFirstRowStatusCellText().contains(status));
		assertTrue(getCalculationStatusMyStatusFirstRowLogStatusCellText().contains(logStatus));
		assertTrue(getCalculationStatusMyStatusFirstRowRecordsProcessedCellText().contains(recordsProcessed));
		assertTrue(getCalculationStatusMyStatusFirstRowRecordsPendingCellText().contains(recordsPending));
		assertTrue(getCalculationStatusMyStatusFirstRowTotalRecordsCellText().contains(totalRecords));
		ExtentReport.logPass("PASS", "test01RunPublishedContractAndVerifyStatusPageDetailsAfterCompleted");
    	} catch (Exception|AssertionError e) {
	ExtentReport.logFail("FAIL", "test01RunPublishedContractAndVerifyStatusPageDetailsAfterCompleted", driver, e);
		fail(e.getMessage());
    	}
  }
//ADS-6085
  @Test
  public void test02OpenViewDialogAndAssertTotalEfrs_ADS_6085_() throws InterruptedException,Throwable {
	try {
		Thread.sleep(2000);
		calculationStatusPageOpenViewDialog();
		waitForPresenceOfElement("(//div[contains(@id,'displayfield')])[3]");
		assertViewLogTitle(expectedLogViewTitle);
   waitForAjaxExtJs();//Shilpa 07.09.2022 method added to wait loading
		confirmCalculationStatusDetailsContains(logDetailTotalEfrs);
		ExtentReport.logPass("PASS", "test02OpenViewDialogAndAssertTotalEfrs");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test02OpenViewDialogAndAssertTotalEfrs", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test03OnLastPageOfViewDialogAssertLogDetails() throws Throwable {
    try {
		clickLastPageIconOnCalculationStatusViewLog();
		waitForSpinnerToEnd();
		waitForPresenceOfElement("//*[text() = 'CALCULATION SUMMARY']");
		confirmCalculationStatusDetailsContains(logViewDetails);
		closeViewDialog();
		ExtentReport.logPass("PADD", "test03OnLastPageOfViewDialogAssertLogDetails");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test03OnLastPageOfViewDialogAssertLogDetails", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test04DeleteCalculationStatusPageF() throws InterruptedException,Throwable {
    try {
		deleteCalculationStatusMyStatusPageFirstRow();
		ExtentReport.logPass("PASS", "test04DeleteCalculationStatusPageF");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test04DeleteCalculationStatusPageF", driver, e);
		fail(e.getMessage());
	}
  }
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
