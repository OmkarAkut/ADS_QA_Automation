package webdriver.helpers;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import com.aventstack.extentreports.Status;

import ExtentReport.ExtentReport;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import webdriver.corehelpers.GoHelper;
import webdriver.maps.EditContractingModelMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;

public class UtilitiesHelper extends GoHelper {

  private static ModelLibraryMap modelMap;
  private static EditContractingModelMap editModelMap;

  /** Helper Class for Contract Models pages - individual test scripts should extend this one to use it.
   */
  @BeforeClass
  public static void setupHelper() {
    modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
    editModelMap = BuildMap.getInstance(driver, EditContractingModelMap.class);
  }

  public void runReport(String startDate, String endDate, String[] codes) throws InterruptedException {
    waitForAjaxExtJs();
    waitForElementToBeVisible(driver.findElement(By.name("startDate1")));
    driver.findElement(By.name("startDate1")).clear();
    driver.findElement(By.name("startDate1")).sendKeys(startDate);
    driver.findElement(By.name("endDate1")).clear();
    driver.findElement(By.name("endDate1")).sendKeys(endDate);
    driver.findElement(By.xpath("//button/span[text()='Select']")).click();
    selectItemsOnSelector(codes);
    driver.findElement(By.xpath("//button/span[text()='Apply']")).click();
    waitForAjaxExtJs();
    waitForElementToBeVisible(driver.findElement(By.xpath("//button/span[text()='Run']")));
    driver.findElement(By.xpath("//button/span[text()='Run']")).click();
    waitForSpinnerToEnd();
    waitForUtilityFirstRowDownloadLinkToBecomeActive();
    driver.findElement(
            By.xpath("//tbody/tr[2]/td/div/a[@class='stLinks' and text()='Download']"))
            .click();
    Thread.sleep(5000);
    deleteUtilityStatusPageMyStatusFirstRow();
  }

  public static void failIfHeadless(String browser) {
   if (browser.toLowerCase().contains("headless")) {
     fail("Headless browser does not currently support file downloading - run test in regular mode");
    }
  }

  public void selectItemsOnSelector(String[] items) throws InterruptedException {
    waitForSpinnerToEnd();
    try {
		for (String item : items) {
		  driver.findElement(By.xpath("//tr/td/div[text()='" + item +"']")).click();
		  Thread.sleep(500);
		  driver.findElement(By.xpath("//button[not(@disabled)]/span[text()='Select']")).click();
		  waitForSpinnerToEnd();
		}
	} catch (Exception e) {
		ExtentReport.extenttest.log(Status.FAIL, e.getMessage());
		ExtentReport.extenttest.log(Status.INFO, e);
	}

  }

  public static void waitForUtilityFirstRowDownloadLinkToBecomeActive() throws InterruptedException {
    boolean calculate = true;
    String download;
    byte counter = 0;
    while (calculate) {
      try {
        driver.findElement(By.xpath("//button/span[text()='Refresh']")).click();
        waitForSpinnerToEnd();
        download = driver.findElement(
                By.xpath("//tbody/tr[2]/td/div/a[@class='stLinks' and text()='Download']"))
                .getAttribute("class")
        ;
        System.out.println("Download: " + download);
        assertTrue(download.contains("stLinks"));
        break;
      } catch (Throwable e) {
        System.out.println("Utility not complete");
        Thread.sleep(5000);
        if (counter == 30) {
          fail("Utility did not finish in allotted time");
          break;
        }
        counter++;
      }
    }
    Thread.sleep(1000);
  }

  public void deleteUtilityStatusPageMyStatusFirstRow() throws InterruptedException {
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    waitForPresenceOfElement("//div[contains(@class, 'delBtn')]/descendant::button/span[contains(@class, 'x-btn-icon')]");
    WebElement firstRowDeleteIcon = driver.findElement(By.xpath("//div[contains(@class, 'delBtn')]/descendant::button/span[contains(@class, 'x-btn-icon')]"));
    firstRowDeleteIcon.click();
    waitForPresenceOfElement("//div[contains(@class,'windowbtn')]/descendant::button/span[text()='Delete']");
    driver.findElement(By.xpath("//div[contains(@class,'windowbtn')]/descendant::button/span[text()='Delete']")).click();
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
  }


}
