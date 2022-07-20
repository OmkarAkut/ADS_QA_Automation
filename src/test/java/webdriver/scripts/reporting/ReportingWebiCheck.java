package webdriver.scripts.reporting;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;

public class ReportingWebiCheck extends GoHelper {

  static String firstHandle;

  @BeforeClass
  public static void setupScript() throws Exception {
    System.out.println("Test Class: " + ReportingWebiCheck.class.getSimpleName());
    
    /*modified by Omkar on 13/6/22 as only aadmin user is available for qa3 env
    Login.loginUser("AutomationTester1");
    */
    Login.loginUser("AutomationTesterAdmin");
 // End of modification
    goToPage("Web Intelligence");
  }

  @Test
  public void testWebiPageLoads() {
    try {
      waitForAjaxExtJs();
      firstHandle = webdriverSwitchToNewWindow(printout);
      waitForPresenceOfElement("//*[@name = 'servletBridgeIframe']");
      webdriverSwitchToFrame("servletBridgeIframe");
    //Omkar (14/4/2022) to click on ok button for a popup that appears
      List<WebElement> chkbx = driver.findElements(By.id("dialogService_1_check"));
      if(chkbx.size()!= 0) {
    	  chkbx.get(0).click();
    	  driver.findElement(By.id("yui-gen1")).click();
      }
      //omkar : end of edit
      
      driver.findElement(By.id("gotomenubutton-button")).click();
      
      driver.findElement(By.xpath("//*[@role='menuitem' and text()='Web Intelligence']")).click();
      waitForSpinnerToEnd();
      waitForPresenceOfElement("//*[@title = 'Home']");
      assertThatElementIsDisplayed(driver.findElement(By.xpath(("//*[@title = 'Home']"))));
      assertThatElementIsDisplayed(driver.findElement(By.xpath(("//*[@title = 'Documents']"))));
      waitForPresenceOfElement("//*[@title = 'Web Intelligence']");
      assertThatElementIsDisplayed(driver.findElement(By.xpath(("//*[@title = 'Web Intelligence']"))));
      driver.findElement(By.id("logoffLink-button")). click();
      Thread.sleep(3000);
      waitForPresenceOfElement("//*[@class = 'logonProductName' and text() = 'SAP BusinessObjects']");
    } catch (Throwable e) {
      fail(e.getMessage());
    } finally {
      driver.close();
      driver.switchTo().window(firstHandle);
    }
  }

}

