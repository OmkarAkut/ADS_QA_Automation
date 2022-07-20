package webdriver.scripts.datamaintenance.utilities;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import webdriver.core.Login;
import webdriver.helpers.UtilitiesHelper;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.StatusMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UtilitiesReportCheck
        extends UtilitiesHelper {

  static DataMaintenanceMap dm;
  static StatusMap status;
  final String reportName = "Encounters With No Charges Report";
  String[] codes = {
      "2S1  Office", "2S3  Hospital"
  };
  String startDate = "01/01/2019";
  String endDate = "06/30/2019";
  String currentDate = javaGetCurrentDate("MM/dd/yyyy");
  static final String expectedUtilityName = "Encounters With No Charges Report";
  static final String expectedUtilityStatus = "Completed";
  static final String expectedUtilityLogStatus = "Completed";
  static final String expectedUtilityDownload = "Download";
  String durationPatternMatch = "^\\d\\d:\\d\\d:\\d\\d$";

  static String utilityStatusUtilityNameXpath = "//table/tbody/tr[2]/td[5]/div[contains(@class, 'grid-cell')]";
  static String utilityStatusStatusXpath = "//table/tbody/tr[2]/td[6]/div[contains(@class, 'grid-cell')]";
  static String utilityStatusLogStatusXpath = "//table/tbody/tr[2]/td[7]/div[contains(@class, 'grid-cell')]";
  static String utilityStatusDownloadXpath = "//table/tbody/tr[2]/td[8]/div[contains(@class, 'grid-cell')]/a";
  static String utilityStatusStartTimeXpath = "//table/tbody/tr[2]/td[9]/div[contains(@class, 'grid-cell')]";
  static String utilityStatusEndTimeXpath = "//table/tbody/tr[2]/td[10]/div[contains(@class, 'grid-cell')]";
  static String utilityStatusDurationXpath = "//table/tbody/tr[2]/td[11]/div[contains(@class, 'grid-cell')]";

  /** Test script that verifies Encounters With No Charges Report. */
  @BeforeClass
  public static void setupScript() throws Exception {
    status = BuildMap.getInstance(driver, StatusMap.class);
    dm = BuildMap.getInstance(driver, DataMaintenanceMap.class);
    System.out.println(
            "Test Class: "
            + UtilitiesReportCheck.class.getSimpleName());
    Login.loginUser("ApplicationAdministrator1");
    goToPage("Utilities");
  }

  @Test
  public void test01VerifyReportDefaultConfiguration() {
    waitForPresenceOfElement("//div[contains(@id, 'datamaintenanceutilities')]" +
            "/descendant::button/*[text()='Encounters With No Charges Report']"
    );
    doClick(dm.getUtilitiesPageEncountersWithNoChargesReport());
    waitForPresenceOfElement("//div[contains(@id, 'datamaintenanceutilities')]" +
            "/descendant::button/*[text()='Encounters With No Charges Report']"
    );
    String startDate = driver.findElement(By.name("startDate1")).getAttribute("value");
    assertEquals(currentDate, startDate);
    String endDate = driver.findElement(By.name("endDate1")).getAttribute("value");
    assertEquals(currentDate, endDate);
    assertButtonIsActive("Select");
    assertButtonIsInactive("Run");
  }

  @Test
  public void test02RunReportAndVerifyCompletedStatus() throws InterruptedException {
    waitForAjaxExtJs();
    runUtilityReport(startDate, endDate, codes);
  }

  @Test
  public void test04aVerifyUtilityStatusPageTableValueForUtilityNameColumn() throws InterruptedException {
    waitForAjaxExtJs();
    String utilityNameText = getWebElement(utilityStatusUtilityNameXpath).getText();
    assertEquals(expectedUtilityName, utilityNameText);
  }

  @Test
  public void test04bVerifyUtilityStatusPageTableValueForStatusColumn() {
    String utilityStatusStatusText = getWebElement(utilityStatusStatusXpath).getText();
    assertEquals(expectedUtilityStatus, utilityStatusStatusText);
  }

  @Test
  public void test04cVerifyUtilityStatusPageTableValueForLogStatusColumn() {
    String utilityStatusLogStatusText = getWebElement(utilityStatusLogStatusXpath).getText();
    assertEquals(expectedUtilityLogStatus, utilityStatusLogStatusText);
  }

  @Test
  public void test04dVerifyUtilityStatusPageTableValueForDownloadColumn() {
    String utilityStatusDownloadText = getWebElement(utilityStatusDownloadXpath).getText();
    assertEquals(expectedUtilityDownload, utilityStatusDownloadText);

    String utilityStatusDownloadLinkFormat = getWebElement(utilityStatusDownloadXpath).getAttribute("href");
    assertThat(utilityStatusDownloadLinkFormat, containsString("/services/utilityStatus/downLoad/"));
  }

  @Test
  public void test04eVerifyUtilityStatusPageTableValueForStartTimeColumn() {
    String utilityStatusStartTimeText = getWebElement(utilityStatusStartTimeXpath).getText();
    assertThat(utilityStatusStartTimeText, containsString(currentDate));
  }

  @Test
  public void test04fVerifyUtilityStatusPageTableValueForEndTimeColumn() {
    String utilityStatusEndTimeText = getWebElement(utilityStatusEndTimeXpath).getText();
    assertThat(utilityStatusEndTimeText, containsString(currentDate));
  }

  @Test
  public void test04gVerifyUtilityStatusPageTableValueForDurationColumn() {
    String utilityStatusDurationText = getWebElement(utilityStatusDurationXpath).getText();
    assertTrue(utilityStatusDurationText.matches(durationPatternMatch));
  }

  @Test
  public void test05VerifyUtilityStatusPageSearch() {
    waitForPresenceOfElement(
            "//*[contains(@id,'utilitystatus') and contains(@id,'header')]/../descendant::table/descendant::input[@name='searchText']"
    );
    status.getUtilityStatusPageFormFieldSearch().sendKeys(currentDate);
    status.getUtilityStatusPageButtonSearchGlass().click();
    waitForSpinnerToEnd();
    String utilityStatusSearchStartTime = getWebElement(utilityStatusStartTimeXpath).getText();
    assertThat(utilityStatusSearchStartTime, containsString(currentDate));
  }

  @Test
  public void test06DeleteUtilityStatusFirstRowAndConfirm() throws InterruptedException {
    deleteUtilityStatusPageMyStatusFirstRow();
    assertElementIsDisplayed(status.getUtilityStatusPageFormFieldSearch());
    driver.findElements(By.xpath("//table/tbody/tr/td[11]/div[contains(@class, 'grid-cell')]"));
  }

  public void runUtilityReport(String startDate, String endDate, String[] codes) throws InterruptedException {
    waitForAjaxExtJs();
    populateReportFields(startDate, endDate, codes);
    waitForAjaxExtJs();
    waitForElementToBeVisible(driver.findElement(By.xpath("//button/span[text()='Run']")));
    driver.findElement(By.xpath("//button/span[text()='Run']")).click();
    waitForSpinnerToEnd();
    waitForUtilityFirstRowDownloadLinkToBecomeActive();
  }

  private void populateReportFields(String startDate, String endDate, String[] codes)
          throws InterruptedException {
    waitForElementToBeVisible(driver.findElement(By.name("startDate1")));
    driver.findElement(By.name("startDate1")).clear();
    driver.findElement(By.name("startDate1")).sendKeys(startDate);
    driver.findElement(By.name("endDate1")).clear();
    driver.findElement(By.name("endDate1")).sendKeys(endDate);
    driver.findElement(By.xpath("//button/span[text()='Select']")).click();
    selectItemsOnSelector(codes);
    driver.findElement(By.xpath("//button/span[text()='Apply']")).click();
  }

  public void assertButtonIsInactive(String buttonText) {
    try {
      waitForAjaxExtJs();
      boolean isInactive = driver.findElement(By.xpath(
              "//button/*[text()='"+buttonText+"']/ancestor::button[@disabled]"))
              .isDisplayed()
              ;
      assertTrue(isInactive);
    } catch(Throwable e) {
      fail("FAIL: "+buttonText+" button is active.");
    }
  }

  public void assertButtonIsActive(String buttonText) {
    boolean isInactive = false;
    try {
      waitForAjaxExtJs();
      isInactive = driver.findElement(By.xpath(
              "//button/*[text()='" + buttonText + "']/ancestor::button[@disabled]"))
              .isDisplayed()
      ;
      if (isInactive) {
        fail("FAIL: " + buttonText + " button is inactive.");
      }
    } catch (Throwable e) {
      assertFalse(isInactive);
    }
  }

}
