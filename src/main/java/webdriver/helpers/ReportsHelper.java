package webdriver.helpers;

import org.junit.BeforeClass;
import org.openqa.selenium.By;
import webdriver.corehelpers.GoHelper;
import webdriver.maps.EditContractingModelMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;

public class ReportsHelper extends GoHelper {

  private static ModelLibraryMap modelMap;
  private static EditContractingModelMap editModelMap;
  public static String reportingIframeXpath = "//iframe[@src='../reporting/main.html']";

  /** Helper Class for Contract Models pages - individual test scripts should extend this one to use it.
   */
  @BeforeClass
  public static void setupHelper() {
    modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
    editModelMap = BuildMap.getInstance(driver, EditContractingModelMap.class);
  }

  public String reportsSubDirectoryXpath (String directory, String subDirectory) {
    return "//div[contains(text(),'" + directory + "')]/ancestor::table/following-sibling::div/descendant::*[contains(text(), '" + subDirectory + "')]";
  }

  public static String reportsDirectoryXpath (String directory) {
    return "//div[contains(text(),'" + directory + "')]";
  }

  public static void switchToReportingIframe() {
    waitForPresenceOfElement(reportingIframeXpath);
    driver.switchTo().frame(driver.findElement(By.xpath(reportingIframeXpath))); //"//iframe[@src='../reporting/main.html']"
  }

}
