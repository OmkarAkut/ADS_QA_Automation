package webdriver.scripts.reporting;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RunReportLoop extends GoHelper {

  static String folder = "Charles";  //Auto
  //Omkar 18/8/222 : Commenting the below report name as it is no more available and adding the new available report
  //static String reportName = "CM Profit and Loss Statement";  //Auto
  static String reportName = "Cost Model Scenario Reconciliation Report";

  //int runs = 1;
  //int runs = 300;  //Run at 300 to create 10 server log archive directories
  int runs = 2;  //Run 450-600 to create 10 dssserver log archive directories

  @BeforeClass
  public static void setupScript() throws Exception,Throwable {
	  ExtentReport.reportCreate("RunReportLoop", "webdriver.scripts.reporting", "RunReportLoop");
    try {
		System.out.println("Test Class: " + RunReportLoop.class.getSimpleName());
		Login.loginUser("AutomationTester1");
		goToPage("report library");
		switchToReportingIframe();
		ExtentReport.logPass("PASS", "setupScript");

	} catch(Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);

	      fail(e.getMessage());
	    }
  }

  @AfterClass
  public static void teardownScript() {
    driver.switchTo().defaultContent();
    ExtentReport.report.flush();
  }

  @Test
  public void utilityRunReportLoop() throws Throwable {

    try {
		waitForPresenceOfElement("//*[contains(text(), '"+folder+"')]");
		driver.findElement(By.xpath("//*[contains(text(), '"+folder+"')]")).click();

		for (int i = 0; i < runs; i++) {
		  System.out.println("Run: " + i);
		  waitForPresenceOfElement("//*[@title = '"+reportName+"']");
		  driver.findElement(By.xpath("//*[@title = '"+reportName+"']")).click();

//      waitForPresenceOfElement("//button[not(@disabled) and @class= 'GJT013UBM2B' and text()='Run']");
//      driver.findElement(By.xpath("//button[@class= 'GJT013UBM2B' and text()='Run']")).click();
		  
		  waitForPresenceOfElement("//button[not(@disabled) and @class= 'GJT013UBIBC' and text()='Run']");
		  driver.findElement(By.xpath("//button[@class= 'GJT013UBIBC' and text()='Run']")).click();
		  waitForPresenceOfElement("//table/tbody/tr[1]/td/div/*[@title='"+reportName+"']");

		  waitForPresenceOfElement("//a[text()='Report Library']");
		  driver.findElement(By.xpath("//a[text()='Report Library']")).click();
		}
		ExtentReport.logPass("PASS", "utilityRunReportLoop");

	} catch(Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "utilityRunReportLoop", driver, e);

	      fail(e.getMessage());
	    }
  }

  public static void switchToReportingIframe() {
    waitForPresenceOfElement("//iframe[@src='../reporting/main.html']");
    driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@src='../reporting/main.html']")));
  }

}
