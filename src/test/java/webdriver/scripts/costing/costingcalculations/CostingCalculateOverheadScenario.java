package webdriver.scripts.costing.costingcalculations;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;

public class CostingCalculateOverheadScenario extends CalculationHelper {
	JavascriptExecutor executor = (JavascriptExecutor)driver;
	static String viewLogTitle="Overhead Model Scenario Calculation";

  @BeforeClass
    public static void setupScript() throws Exception,Throwable{
	  ExtentReport.reportCreate("CostingCalculateOverheadScenario", "webdriver.scripts.costing.costingcalculations", "CostingCalculateOverheadScenario");
    try {
		System.out.println("Test Class: " + CostingCalculateOverheadScenario.class.getSimpleName());
		Login.loginUser("AutomationTester1");
		goToPage("Costing Models");
		ExtentReport.logPass("PASS", "setupScript");
	}  catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
		fail(e.getMessage());
	}
  }

  @Test
    public void testAdsLoginLogout() throws Throwable {
    try {
//      doSearchForModel("v102 REGRESSION Overhead Model");
    	 doSearchForModel("2005 Overhead Allocation");
//      tableDoubleClickCellFirstColumn("v102 REGRESSION Overhead Model");
    	 tableDoubleClickCellFirstColumn("2005 Overhead Allocation");
     driverDelay(4000);
      doClickTreeItem("Allocate Overhead");
      driverDelay(4000);
      doClickTreeItem("Overhead Model Calculation Scenarios");
      //Omkar (19/7/2022) : value v102 REGRESSION OH Scenario is not found hence changing it to v102 REGRESSION OH Calc Scenario
      //tableDoubleClickCellFirstColumn("v102 REGRESSION OH Scenario");
//      tableDoubleClickCellFirstColumn("v102 REGRESSION OH Calc Scenario");
      tableDoubleClickCellFirstColumn("OH calculation June 2004");
      waitForAjaxExtJs();
      Thread.sleep(1000);
      //Omkar (19/7/2022) : The below xpath is no more valid
      //doClick(driver.findElement(By.xpath("//button/span[text()='Calculate']")));
      doClick(driver.findElement(By.xpath("(//button/span[text()='Calculate'])[2]")));
      waitForFirstRowCalculationBarToReach100Percent();
      calculationStatusPageOpenViewDialog();
      assertViewLogTitle(viewLogTitle);
      doClick(driver.findElement(By.xpath("(//div[contains(@id,'window')]//span[contains(@id,'btnInnerEl')]//parent::button)[6]")));
      driverDelay();
      confirmCalculationStatusDetailsContains("Process Completed");
      closeViewDialog();
     
//      waitForCalculationToEndAndVerifySummaryDetailsStringOnDialogAndCloseDialog(
//              "Number of batches to process: 1"
//      );

      //click last page icon
//      driver.findElement(By.xpath("//*[@class='x-btn-icon pagging-tbar-last-button']")).click();
      waitForSpinnerToEnd();

      deleteMyCalculationStatusFirstRow();
      doClosePageOnLowerBar("Calculation Status");
      waitForAjaxExtJs();
      Thread.sleep(500);
      doClick(driver.findElement(By.xpath("//button/span[text()='Save & Close']")));
//      doClosePageOnLowerBar("v102 REGRESSION...");
      doClosePageOnLowerBar("2005 Overhead...");
      doClosePageOnLowerBar("Model Library");



      waitForAjaxExtJs();
      ExtentReport.logPass("PASS", "testAdsLoginLogout");
    } catch (Exception|AssertionError e) {
      ExtentReport.logFail("FAIL", "testAdsLoginLogout", driver, e);
      fail(e.getMessage());

    }
  }

    //Shilpa 27.07.2022, some wait is added and updated to click element inside frame
  @Test //(Omkar 24/5/22 : Need to review which objectr needs to be clicked in tree in step 2.The test is failing saying that object is not clickable)
  public void testAssertReportLibrary() throws InterruptedException,Throwable {
    try {
		goToPage("Report Library");
waitForSpinnerToEnd();
		Thread.sleep(9000);
		doClickTreeItem("Costing");
		Thread.sleep(3000);
		executor.executeScript("arguments[0].click();", driver.findElement(By.xpath("//*[@title='Overhead Received']")));

//    doClick(driver.findElement(By.xpath("//*[@title='Overhead Received']")));

		//enter criteria
		//Shilpa: 27.07.2022 , added some wait
		Thread.sleep(5000);
		doClick(driver.findElement(By.xpath("//div[@class='gwt-Hyperlink']/a/u[text()='* TB MH FY05 Overhead']")));
		ExtentReport.logPass("PASS", "testAssertReportLibrary");
    } catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "testAssertReportLibrary", driver, e);
		fail(e.getMessage());
	}
  //  Thread.sleep(4000);
    //doClick("//div[@class='gwt-Hyperlink']/a/u[text()='* TB MH FY05 Overhead']");


  }
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
