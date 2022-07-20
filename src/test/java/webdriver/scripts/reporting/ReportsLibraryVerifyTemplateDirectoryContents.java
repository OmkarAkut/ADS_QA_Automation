package webdriver.scripts.reporting;

import static org.junit.Assert.assertEquals;

import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.helpers.ReportsHelper;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReportsLibraryVerifyTemplateDirectoryContents extends ReportsHelper {
  int expectedTemplatesReportsListSize = 77;
  int expectedConsumersAndEncountersTemplateReportsListSize = 15;
  int expectedContractAndArManagementTemplateReportsListSize = 16;
  int expectedCostingTemplateReportsListSize = 23;
  int expectedFlexibleReportsTemplateReportsListSize = 4;
  int expectedGeneralReportsTemplateReportsListSize = 12;
  int expectedProfitabilityAndUtilizationTemplateReportsListSize = 7;

  @BeforeClass
  public static void setupScript() throws Exception {
    System.out.println("Test Class: " + ReportsLibraryVerifyTemplateDirectoryContents.class.getSimpleName());
    loginUser("AutomationTester1");
    goToPage("report library");
    switchToReportingIframe();
  }

  @AfterClass
  public static void teardownScript() {
    driver.switchTo().defaultContent();
  }

  @Test
  public void test00VerifyReportTemplatesDirectoryReportsCount() throws InterruptedException {
    waitForPresenceOfElement(reportsDirectoryXpath("Templates"));
    assertEquals(expectedTemplatesReportsListSize, getListSize("80/20 Charge Analysis"));
  }

  @Test
  public void test01VerifyReportTemplatesConsumersAndEncountersDirectoryReportsCount() {
    waitForPresenceOfElement(reportsSubDirectoryXpath("Templates", "Consumers and Encounters"));
    driver.findElement(By.xpath(reportsSubDirectoryXpath("Templates", "Consumers and Encounters"))).click();
    assertEquals(expectedConsumersAndEncountersTemplateReportsListSize, getListSize("Consumer Information"));
  }

  @Test
  public void test02VerifyReportTemplatesContractAndArManagementDirectoryReportsCount() {
    waitForPresenceOfElement(reportsSubDirectoryXpath("Templates", "Contract and A/R Management"));
    driver.findElement(By.xpath(reportsSubDirectoryXpath("Templates", "Contract and A/R Management"))).click();
    assertEquals(expectedContractAndArManagementTemplateReportsListSize, getListSize("Actual to Expected Payment Comparison"));
  }

  @Test
  public void test03VerifyReportTemplatesCostingDirectoryReportsCount() {
    waitForPresenceOfElement(reportsSubDirectoryXpath("Templates", "Costing"));
    driver.findElement(By.xpath(reportsSubDirectoryXpath("Templates", "Costing"))).click();
    assertEquals(expectedCostingTemplateReportsListSize, getListSize("80/20 Charge Analysis"));
  }

  @Test
  public void test04VerifyReportTemplatesFlexibleReportsDirectoryReportsCount() {
    waitForPresenceOfElement(reportsSubDirectoryXpath("Templates", "Flexible Reports"));
    driver.findElement(By.xpath(reportsSubDirectoryXpath("Templates", "Flexible Reports"))).click();
    assertEquals(expectedFlexibleReportsTemplateReportsListSize, getListSize("Contract Comparison"));
  }

  @Test
  public void test05VerifyReportTemplatesGeneralReportsDirectoryReportsCount() {
    waitForPresenceOfElement(reportsSubDirectoryXpath("Templates", "General Reports"));
    driver.findElement(By.xpath(reportsSubDirectoryXpath("Templates", "General Reports"))).click();
    assertEquals(expectedGeneralReportsTemplateReportsListSize, getListSize("Chargeable Activities by Charge Master"));
  }

  @Test
  public void test06VerifyReportTemplatesProfitabilityAndUtilizationDirectoryReportsCount() {
    waitForPresenceOfElement(reportsSubDirectoryXpath("Templates", "Profitability and Utilization"));
    driver.findElement(By.xpath(reportsSubDirectoryXpath("Templates", "Profitability and Utilization"))).click();
    assertEquals(expectedProfitabilityAndUtilizationTemplateReportsListSize, getListSize("Case Mix Index Analysis"));
  }

  private int getListSize(String firstReportListed) {
    int reportsListSize;
    waitForPresenceOfElement("//*[contains(text(), '" + firstReportListed + "')]");
    WebElement reportsList = driver.findElement(By.xpath("//*[contains(text(), '" + firstReportListed + "')]/ancestor::tbody"));
    List reports = reportsList.findElements(By.tagName("tr"));
    reportsListSize = reports.size();
    return reportsListSize;
  }

}
