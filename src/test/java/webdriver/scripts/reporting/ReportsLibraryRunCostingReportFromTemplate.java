package webdriver.scripts.reporting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReportsLibraryRunCostingReportFromTemplate extends GoHelper {
  int expectedTemplatesReportsListSize = 77;
  int expectedConsumersAndEncountersTemplateReportsListSize = 15;
  int expectedContractAndArManagementTemplateReportsListSize = 16;
  int expectedCostingTemplateReportsListSize = 23;
  int expectedFlexibleReportsTemplateReportsListSize = 4;
  int expectedGeneralReportsTemplateReportsListSize = 12;
  int expectedProfitabilityAndUtilizationTemplateReportsListSize = 7;

  @BeforeClass
  public static void setupScript() throws InterruptedException,Throwable{
	  ExtentReport.reportCreate("ReportsLibraryRunCostingReportFromTemplate", "webdriver.scripts.reporting", "ReportsLibraryRunCostingReportFromTemplate");
    try {
		System.out.println("Test Class: " + ReportsLibraryRunCostingReportFromTemplate.class.getSimpleName());
		Login.loginUser(Users.AutomationTester1);
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
  public void test00VerifyReportTemplatesDirectoryReportsCount() throws Throwable {
    try {
		waitForPresenceOfElement(reportsDirectoryXpath("Templates"));
		driverDelay(5000);//Shilpa 29.08.2022 added
		//assertEquals(expectedTemplatesReportsListSize, getListSize("80/20 Charge Analysis"));
		assertEquals(expectedTemplatesReportsListSize, getReportsListSize());
		ExtentReport.logPass("PASS", "test00VerifyReportTemplatesDirectoryReportsCount");

	} catch(Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test00VerifyReportTemplatesDirectoryReportsCount", driver, e);

	      fail(e.getMessage());
	    }
  }

  @Test
  public void test01VerifyReportTemplatesConsumersAndEncountersDirectoryReportsCount() throws Throwable {
    try {
		waitForPresenceOfElement(reportsSubDirectoryXpath("Templates", "Consumers and Encounters"));
		driver.findElement(By.xpath(reportsSubDirectoryXpath("Templates", "Consumers and Encounters"))).click();
		assertEquals(expectedConsumersAndEncountersTemplateReportsListSize, getListSize("Consumer Information"));
		ExtentReport.logPass("PASS", "test01VerifyReportTemplatesConsumersAndEncountersDirectoryReportsCount");

	} catch(Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test01VerifyReportTemplatesConsumersAndEncountersDirectoryReportsCount", driver, e);

	      fail(e.getMessage());
	    }
  }

  @Test
  public void test02VerifyReportTemplatesContractAndArManagementDirectoryReportsCount() throws Throwable {
    try {
		waitForPresenceOfElement(reportsSubDirectoryXpath("Templates", "Contract and A/R Management"));
		driver.findElement(By.xpath(reportsSubDirectoryXpath("Templates", "Contract and A/R Management"))).click();
		assertEquals(expectedContractAndArManagementTemplateReportsListSize, getListSize("Actual to Expected Payment Comparison"));
		ExtentReport.logPass("PASS", "test02VerifyReportTemplatesContractAndArManagementDirectoryReportsCount");

		} catch(Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test02VerifyReportTemplatesContractAndArManagementDirectoryReportsCount", driver, e);

		      fail(e.getMessage());
		    }
  }

  @Test
  public void test03VerifyReportTemplatesCostingDirectoryReportsCount() throws Throwable {
    try {
		waitForPresenceOfElement(reportsSubDirectoryXpath("Templates", "Costing"));
		driver.findElement(By.xpath(reportsSubDirectoryXpath("Templates", "Costing"))).click();
		assertEquals(expectedCostingTemplateReportsListSize, getListSize("80/20 Charge Analysis"));
		ExtentReport.logPass("PASS", "test03VerifyReportTemplatesCostingDirectoryReportsCount");

	} catch(Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test03VerifyReportTemplatesCostingDirectoryReportsCount", driver, e);

	      fail(e.getMessage());
	    }
  }

  @Test
  public void test04VerifyReportTemplatesFlexibleReportsDirectoryReportsCount() throws Throwable {
    try {
		waitForPresenceOfElement(reportsSubDirectoryXpath("Templates", "Flexible Reports"));
		driver.findElement(By.xpath(reportsSubDirectoryXpath("Templates", "Flexible Reports"))).click();
		assertEquals(expectedFlexibleReportsTemplateReportsListSize, getListSize("Contract Comparison"));//Shilpa 29.08.2022 removed extra space after "Contract Comparison "
		ExtentReport.logPass("PASS", "test04VerifyReportTemplatesFlexibleReportsDirectoryReportsCount");

	} catch(Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test04VerifyReportTemplatesFlexibleReportsDirectoryReportsCount", driver, e);

	      fail(e.getMessage());
	    }
  }

  @Test
  public void test05VerifyReportTemplatesGeneralReportsDirectoryReportsCount() throws Throwable {
    try {
		waitForPresenceOfElement(reportsSubDirectoryXpath("Templates", "General Reports"));
		driver.findElement(By.xpath(reportsSubDirectoryXpath("Templates", "General Reports"))).click();
		assertEquals(expectedGeneralReportsTemplateReportsListSize, getListSize("Chargeable Activities by Charge Master"));
		ExtentReport.logPass("PASS", "test05VerifyReportTemplatesGeneralReportsDirectoryReportsCount");

	} catch(Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test05VerifyReportTemplatesGeneralReportsDirectoryReportsCount", driver, e);

	      fail(e.getMessage());
	    }
  }

  @Test
  public void test06VerifyReportTemplatesProfitabilityAndUtilizationDirectoryReportsCount() throws Throwable {
    try {
		waitForPresenceOfElement(reportsSubDirectoryXpath("Templates", "Profitability and Utilization"));
		driver.findElement(By.xpath(reportsSubDirectoryXpath("Templates", "Profitability and Utilization"))).click();
		assertEquals(expectedProfitabilityAndUtilizationTemplateReportsListSize, getListSize("Case Mix Index Analysis"));
		ExtentReport.logPass("PASS", "test06VerifyReportTemplatesProfitabilityAndUtilizationDirectoryReportsCount");

	} catch(Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "test06VerifyReportTemplatesProfitabilityAndUtilizationDirectoryReportsCount", driver, e);

	      fail(e.getMessage());
	    }
  }

  private int getReportsListSize() {
    int reportsListSize;
    WebElement reportsList = driver.findElement(By.xpath("//div[@class='GJT013UBH']/descendant::tbody"));
    List reports = reportsList.findElements(By.tagName("tr"));
    reportsListSize = reports.size();
    System.out.println(reportsListSize);
    return reportsListSize;
  }

  private int getListSize(String firstReportListed) {
    int reportsListSize;
    WebElement reportsList = driver.findElement(By.xpath("//*[contains(text(), '" + firstReportListed + "')]/ancestor::tbody"));
    List reports = reportsList.findElements(By.tagName("tr"));
    reportsListSize = reports.size();
    System.out.println(reportsListSize);
    return reportsListSize;
  }

  private static void switchToReportingIframe() {
    waitForPresenceOfElement("//iframe[@src='../reporting/main.html']");
    driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@src='../reporting/main.html']")));
  }

  public String reportsSubDirectoryXpath (String directory, String subDirectory) {
    return "//div[contains(text(),'" + directory + "')]/ancestor::table/following-sibling::div/descendant::*[contains(text(), '" + subDirectory + "')]";
  }

  public static String reportsDirectoryXpath (String directory) {
    return "//div[contains(text(),'" + directory + "')]";
  }

  public static String reportsSearchXpath = "//input[@class='GJT013UBASB']";

  public static String reportingIframe = "//iframe[@src='../reporting/main.html']";

}
