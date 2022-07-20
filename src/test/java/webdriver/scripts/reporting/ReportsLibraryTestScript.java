package webdriver.scripts.reporting;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReportsLibraryTestScript extends GoHelper {

  @BeforeClass
  public static void setupScript() throws InterruptedException {
    System.out.println("Test Class: " + ReportsLibraryTestScript.class.getSimpleName());
    Login.loginUser(Users.AutomationTester1);
    goToPage("report library");
  }

  @Test
  public void test01RunStandardReportFromTemplates() {
    waitForPresenceOfElement(reportDirectoryXpath("Templates", "Costing"));
    waitForPresenceOfElement("//div[contains(text(),'Templates')]/ancestor::table/following-sibling::div/descendant::*[contains(text(), 'Costing')]");
    driver.findElement(By.xpath("//div[contains(text(),'Templates')]/ancestor::table/following-sibling::div/descendant::*[contains(text(), 'General Reports')]")).click();

    driver.findElement(By.xpath("//input[@type='text' and @class='GJT013UBASB']")).sendKeys("CM Cost Model per RVU");
  }

  @Test
  public void test02RunStandardReportFromSavedReports() throws InterruptedException {
    goToPage("report library");
  }

  @Test
  public void test01RunFlexReportFromTemplates() throws InterruptedException {
    goToPage("report library");
  }

  @Test
  public void test01RunFlexReportFromSavedReports() throws InterruptedException {
    goToPage("report library");
  }

  public String reportDirectoryXpath (String directory, String subDirectory) {
    return "//div[contains(text(),'" + subDirectory + "')]/ancestor::table/following-sibling::div/descendant::*[contains(text(), '" + directory + "')]";
  }

}
