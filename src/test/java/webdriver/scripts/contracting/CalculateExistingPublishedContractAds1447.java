package webdriver.scripts.contracting;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assume.assumeTrue;

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
import webdriver.helpers.ContractModelsHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CalculateExistingPublishedContractAds1447 extends CalculationHelper {

  static final String modelName = "v10 REGRESSION Published Contract";
  static final String status = "Completed";
  static final String logStatus = "Completed";
  static final String recordsProcessed = "1470";
  static final String recordsPending = "0";
  static final String totalRecords = "1470";
  final String expectedLogViewTitle = modelName + "\\Contracting\\Published Contract Calculation";
  String[] filter= {"Scenario Name","Is","Equal To",modelName};
  String logDetailTotalEfrs = "Total EFRs to be processed: 1470";
//  String logDetailTotalEfrs = "Total EFRs to be processed: 10359";

//  String[] logViewDetails = {
//          "Number of Encounter Financial Records to Process: 10359",
//          "Non-Critical Warnings: 433",
//          "Encounter Financial Records Calculated to Zero: 218"
          String[] logViewDetails = {
                  "Number of Encounter Financial Records to Process: 1470",
                  "Non-Critical Warnings: 433",
                  "Encounter Financial Records Calculated to Zero: 218"
  };
  private static ModelLibraryMap modelMap;
  private static ContractingMap contractMap;
  private static boolean previousTestPassed  = false;

  /** Regression: Automated test script for ADS-6433,ADS-6085 **/

  @BeforeClass
  public static void setupScript() throws Exception,Throwable {
	  ExtentReport.reportCreate("CalculateExistingPublishedContractAds1447", "webdriver.scripts.contracting", "CalculateExistingPublishedContractAds1447");
    try {
		modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
		contractMap=BuildMap.getInstance(driver, ContractingMap.class);
		Login.loginUser("ContractAnalyst1");
		goToPage("Contract Models");
		waitForSpinnerToEnd();
		Thread.sleep(4000);
		doSearchForContractModel(modelName);
		driverDelay();
//		doClick("//div[text()='"+modelName+"']");
//		Actions act=new Actions(driver);
//		act.moveToElement(driver.findElement(By.xpath("//div[text()="+modelName+"]"))).doubleClick().perform();
		doClick(driver.findElement(By.xpath("//div[text()='"+modelName+"']")));
		/* uncomment when scroll works
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
		*/
		previousTestPassed =true;
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
		previousTestPassed  = false;
	ExtentReport.logFail("FAIL", "setupScript", driver, e);
	fail(e.getMessage());
	}
  }
//ADS-6433
  @Test
  public void test01RunPublishedContractAndVerifyStatusPageDetailsAfterCompleted_ADS_6433() throws Throwable, InterruptedException {
    try {
    	assumeTrue(previousTestPassed);
		waitForSpinnerToEnd();
		waitForAjaxExtJs();
		modelMap.getModelLibraryContractingButtonCalculate().click();
		waitForCalculationToEnd(1000);
		/*
//		driver.findElement(By.xpath("//button/span[text()='Refresh']")).click();
		driver.findElement(By.xpath("//span/span[text()='Refresh']")).click();//Shilpa: update xpath 11.2
//		waitForPresenceOfElement("//div[2]/div/div[4]/div/table/tbody/tr[2]/td[17]/div");  //total records xpath
		waitForPresenceOfElement("(//div[@class='x-grid-item-container'])[3]//table//tbody/tr/td[2]/div");//shilpa: xpath update 11.9.2023 for 11.2
		Thread.sleep(200);
		*/
//		doFilterCalculationPage(filter);
		waitForFirstRowCalculationBarToReach100Percent();
//		calculationStatusPageOpenViewDialog();
//		checkForRecordsProcessed(status);
//		checkForRecordsProcessed(logStatus);
//		checkForRecordsProcessed(recordsProcessed);
//		checkForRecordsProcessed(recordsPending);
//		checkForRecordsProcessed(totalRecords);
		driverDelay();
		assertTrue(getCalculationStatusMyStatusFirstRowStatusCellText().contains(status));
		assertTrue(getCalculationStatusMyStatusFirstRowLogStatusCellText().contains(logStatus));
		assertTrue(getCalculationStatusMyStatusFirstRowRecordsProcessedCellText().contains(recordsProcessed));
		assertTrue(getCalculationStatusMyStatusFirstRowRecordsPendingCellText().contains(recordsPending));
		assertTrue(getCalculationStatusMyStatusFirstRowTotalRecordsCellText().contains(totalRecords));
//		closeViewDialog();
		previousTestPassed =true;
		ExtentReport.logPass("PASS", "test01RunPublishedContractAndVerifyStatusPageDetailsAfterCompleted");
    	} catch (Exception|AssertionError e) {
    		previousTestPassed =false;
	ExtentReport.logFail("FAIL", "test01RunPublishedContractAndVerifyStatusPageDetailsAfterCompleted", driver, e);
		fail(e.getMessage());
    	}
  }
//ADS-6085
  @Test
  public void test02OpenViewDialogAndAssertTotalEfrs_ADS_6085() throws InterruptedException,Throwable {
	try {
		assumeTrue(previousTestPassed);//Shilpa update 11.2 on 22.5.2024 run tc 2 if tc1 pass
		Thread.sleep(2000);
		calculationStatusPageOpenViewDialog();
		waitForPresenceOfElement("(//div[contains(@id,'displayfield')])[3]");
		assertViewLogTitle(expectedLogViewTitle);
   waitForAjaxExtJs();//Shilpa 07.09.2022 method added to wait loading
   checkForRecordsProcessed(logDetailTotalEfrs);
		ExtentReport.logPass("PASS", "test02OpenViewDialogAndAssertTotalEfrs");
		previousTestPassed=true;
	} catch (Exception|AssertionError e) {
		previousTestPassed =false;
		ExtentReport.logFail("FAIL", "test02OpenViewDialogAndAssertTotalEfrs", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test03OnLastPageOfViewDialogAssertLogDetails() throws Throwable {
    try {
//    	assumeTrue(previousTestPassed);
//		clickLastPageIconOnCalculationStatusViewLog();
//		waitForSpinnerToEnd();
//		waitForPresenceOfElement("//*[text() = 'CALCULATION SUMMARY']");
		//Scroll not working here
    	//Shilpa updated for 11.2 on 11.5.2024
    	checkForRecordsProcessed("Number of Encounter Financial Records to Process: 1470");
    	checkForRecordsProcessed("Non-Critical Warnings: 4510");
    	checkForRecordsProcessed("Encounter Financial Records Calculated to Zero: 1470");
//		confirmCalculationStatusDetailsContains(logViewDetails);
		closeViewDialog();
		ExtentReport.logPass("PASS", "test03OnLastPageOfViewDialogAssertLogDetails");
		previousTestPassed =true;
	} catch (Exception|AssertionError e) {
		previousTestPassed =false;
		ExtentReport.logFail("FAIL", "test03OnLastPageOfViewDialogAssertLogDetails", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
  public void test04DeleteCalculationStatusPageF() throws InterruptedException,Throwable {
    try {
    	assumeTrue(previousTestPassed);
//    	closeViewDialog();//workaround till scroll works
    	doClick("(//*[contains(@id,'calculationstatus') and contains(@id,'header')]/..//span[contains(@class,'x-btn-icon-el x-btn-icon-el-default-small delBtn')])[1]");
    	driver.findElement(By.xpath("//div[contains(@id,'warningwindow')]//span[text()='Delete']")).click();
//    	deleteCalculationStatusMyStatusPageFirstRow();
		ExtentReport.logPass("PASS", "test04DeleteCalculationStatusPageF");
		previousTestPassed =true;
	} catch (Exception|AssertionError e) {
		previousTestPassed =false;
		ExtentReport.logFail("FAIL", "test04DeleteCalculationStatusPageF", driver, e);
		fail(e.getMessage());
	}
  }
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
