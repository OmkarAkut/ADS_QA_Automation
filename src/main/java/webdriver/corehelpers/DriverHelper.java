package webdriver.corehelpers;

import static org.junit.Assert.fail;

import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DriverHelper extends WaitHelper {

  public static void checkForModelLibraryUserLock() {
    try {
      @SuppressWarnings("unused")
	String lockText = driver.findElement(By.xpath("//*[contains(text(),'currently locked by client')] ")).getText();
      //assertTrue(lockText.contains("locked"));
      fail();
    } catch (Throwable e) {
      System.out.println("No Domain Lock Present");
    }
  }

  public static void webdriverMaximizeWindow() {
    driver.manage().window().maximize();
  }

  public static void webdriverClick(WebElement element) {
    waitUntilElementIsClickable(element);
    element.click();
   
  }

  public void driverDoubleClick(WebElement element) {
    Actions actions = new Actions(driver);
    waitUntilElementIsClickable(element);
    actions.doubleClick(element).perform();
  }

  public void driverPause() throws InterruptedException {
    Thread.sleep(500);
  }

  public void driverWait() throws InterruptedException {
    Thread.sleep(1000);
  }

  public static void driverDelay() throws InterruptedException {
    Thread.sleep(2000);
  }

  public static void driverDelay(int milliseconds) throws InterruptedException {
    Thread.sleep(milliseconds);
  }

  public void webdriverSwitchToFrame(String iFrameNameOrId) {
    driver.switchTo().frame(iFrameNameOrId);
  }

  public String webdriverSwitchToNewWindow(boolean printout) throws InterruptedException {
    String firstHandle = driver.getWindowHandle();
    if (printout) {
      System.out.println("First Handle: " + firstHandle);
    }
    //doClick(elementToClick);
    if (browser.toLowerCase().equals("ie") || browser.toLowerCase().equals("internetexplorer")) {
      //clickThroughIeCertificateScreens();
    }
    waitForAjaxExtJs();
    Set<String> handles = driver.getWindowHandles();
    for (String handle : handles) {
      if (printout) {
        System.out.println("Current Handle: " + handle);
      }
      if (!firstHandle.equals(handle)) {
        driver.switchTo().window(handle);
        waitForJsWindowOnload();
        if (printout) {
          System.out.println("First Handle: " + firstHandle);
          System.out.println("Switched to Handle: " + handle);
        }
        break;
      }
    }
    return firstHandle;
  }

}
