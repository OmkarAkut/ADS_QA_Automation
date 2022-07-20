package webdriver.helpers;

import static org.junit.Assert.*;

import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.corehelpers.GoHelper;

public class PageTestHelper extends GoHelper {

//    public void testHelpLinkWithFrames(String iframePartialXpath, WebElement helpLink, String expectedHelpPageTitle, boolean printout) throws InterruptedException {
//       String firstHandle;
////        try {
//            waitForJsWindowOnload();
//            firstHandle = driver.getWindowHandle();
//            waitForElementDoWhileLoop(driver.findElement(By.xpath(iframePartialXpath)), printout);
//            driver.switchTo().frame(driver.findElement(By.xpath(iframePartialXpath)));
//            //waitForElementDoWhileLoop(reportingMap.getReportingTabGemsAnalysisPageName(), printout);
//            waitForSpinnerToEnd();
//            waitForJsWindowOnload();
//            System.out.println("Sleep 3000 1");
//            Thread.sleep(3000);
//            helpLink.click();
//            waitForSpinnerToEnd();
//            waitForJsWindowOnload();
//            System.out.println("Sleep 5000 2");
//            Thread.sleep(5000);
//
//            //on help page
//            Set<String> handles = driver.getWindowHandles();
//            for(String handle:handles){
//                System.out.println("help handles: " + handle);
//            }
//
//            for (String handle : handles){
//                System.out.println("Current Handle: " + handle);
//                if (!firstHandle.equals(handle)){
//                    driver.switchTo().window(handle);
//                    System.out.println("Switched to Handle: " + handle);
//                    break;
//                }
//            }
//
//            //assert
//            String expectedHeader = expectedHelpPageTitle;
//            Thread.sleep(5000);
//            waitForJsWindowOnload();
//            driver.switchTo().frame("topic");
//            WebElement header = driver.findElement(By.xpath("//body/h1"));
//            String actualHeader = header.getText();
//            System.out.println("Expected Header Text: " + expectedHeader);
//            System.out.println("Actual Header Text  : " + actualHeader);
//            assertEquals(expectedHeader, actualHeader);
//
//            //switch back and close
//            for (String handle : handles){
//                System.out.println("Current Handle: " + handle);
//                if (firstHandle.equals(handle)){
//                    driver.switchTo().window(handle);
//                    System.out.println("Switched to Handle: " + handle);
//                    break;
//                }
//            }
////        } catch (Throwable e) {
////            e.printStackTrace();
////            System.out.println("TEST RESULT: FAILED");
////            fail(expectedHelpPageTitle + " Page Test Failed");
////        }
//    }

  public void testHelpLink(WebElement helpLinkElement, String expectedHeader, boolean printout) throws InterruptedException {
    Thread.sleep(500);
    waitForAjaxExtJs();
    String firstHandle = webSwitchToNewWindow(helpLinkElement, printout);
    assertHelpPageHeader(expectedHeader, printout);
    driver.switchTo().window(firstHandle);
  }

  public boolean testHelpLinkContains(WebElement helpLinkElement, String expectedHeader, boolean printout) throws InterruptedException {
    boolean testFailureCheck = false;
    //Thread.sleep(500);
    waitForAjaxExtJs();
    String firstHandle = webSwitchToNewWindow(helpLinkElement, printout);
    testFailureCheck = assertHelpPageHeaderContains(expectedHeader, printout);
    driver.switchTo().window(firstHandle);
    return testFailureCheck;
  }

  public void testHelpLinkAndCloseNewWindow(
          WebElement helpLinkElement, String expectedHeader, boolean printout)
          throws InterruptedException {
    String firstHandle = webSwitchToNewWindow(helpLinkElement, printout);
    Thread.sleep(10000);
    assertHelpPageHeader(expectedHeader, printout);
    driver.close();
    driver.switchTo().window(firstHandle);
  }

//    public void testHelpLinkWithIframes(WebElement helpLinkElement, String expectedHeader, String firstHandle, boolean printout) throws InterruptedException {
//        //String firstHandle = webSwitchToNewWindowWithIframes(helpLinkElement, printout);
//        webSwitchToNewWindowWithIframes(helpLinkElement, firstHandle, printout);
//        assertHelpPageHeaderIframes(expectedHeader, printout);
//        //driver.switchTo().window(firstHandle);
//    }

  /**
   * Returns a String of the starting page window handle, so can be used at the end of a String variable:
   * String firstHandle = webSwitchToNewWindow(element, printout);
   * AND THEN an ending switchTo is required to switch focus back or framework will not logout properly:
   * driver.switchTo().window(firstHandle);
   * --This statement goes at the end of the script or if focus needs to be moved back to initial page to
   * interact with elements.  The element that triggers the new window is the "elementToClick."
   *
   * @param elementToClick
   * @param printout
   * @return String firstHandle
   * @throws InterruptedException
   */
  public String webSwitchToNewWindow(WebElement elementToClick, boolean printout) throws InterruptedException {
    String firstHandle = driver.getWindowHandle();
    doClick(elementToClick);
    if (browser.toLowerCase().equals("ie") || browser.toLowerCase().equals("internetexplorer")) {
      clickThroughIeCertificateScreens();
    }
    waitForAjaxExtJs();
    Set<String> handles = driver.getWindowHandles();
    for (String handle : handles) {
      if (!firstHandle.equals(handle)) {
        driver.switchTo().window(handle);
        //waitForJsWindowOnload();
//        if (printout) {
//          System.out.println("First Handle: " + firstHandle);
//          System.out.println("Switched to Handle: " + handle);
//        }
        break;
      }
    }
    return firstHandle;
  }

//    public String webSwitchToNewWindowWithIframes(WebElement elementToClick, String firstHandle, boolean printout) throws InterruptedException {
//        waitForSpinnerToEnd();
//        waitForJsWindowOnload();
//        System.out.println("Sleep 3000");
//        Thread.sleep(3000);
//        elementToClick.click();
//        waitForSpinnerToEnd();
//        waitForJsWindowOnload();
//        System.out.println("Sleep 3000");
//        Thread.sleep(3000);
//        Set<String> handles = driver.getWindowHandles();
//        for (String handle : handles){
//            if (!firstHandle.equals(handle)){
//                driver.switchTo().window(handle);
//                //waitForJsWindowOnload();
//                if(printout){
//                    System.out.println("First Handle: " + firstHandle);
//                    System.out.println("Switched to Handle: " + handle);
//                }
//                break;
//            }
//        }
//        return firstHandle;
//    }

  public void assertHelpPageHeader(String expectedHeader, boolean printout) {
    try {
      //Thread.sleep(3000);  //original working wait-replaced by sleep 500 and waitForJsWindowOnload
      // System.out.println("Thread sleep 3000 - find better solution"); //find better solution
      Thread.sleep(2000);
      waitForJsWindowOnload();
      Thread.sleep(2000);
      driver.switchTo().frame("topic");
      WebElement header = driver.findElement(By.xpath("//body/h1"));
      String actualHeader = header.getText();
      if (printout) {
        System.out.println("Expected Header Text: " + expectedHeader);
        System.out.println("Actual Header Text  : " + actualHeader);
      }
      assertEquals(expectedHeader, actualHeader);
    } catch (Throwable e) {
      e.printStackTrace();
      fail("Failed: assertHelpPageHeader");
    }
  }

  public boolean assertHelpPageHeaderContains(String expectedHeader, boolean printout) {
    boolean testFailure = false;
    try {
      //Thread.sleep(3000);  //original working wait-replaced by sleep 500 and waitForJsWindowOnload
      // System.out.println("Thread sleep 3000 - find better solution"); //find better solution
      Thread.sleep(2000);
      waitForJsWindowOnload();
      driver.switchTo().frame("topic");
      WebElement header = driver.findElement(By.xpath("//body/h1"));
      String actualHeader = header.getText();
      if (printout) {
        System.out.println("Expected Header Text: " + expectedHeader);
        System.out.println("Actual Header Text  : " + actualHeader);
      }
      assertTrue(actualHeader.contains(expectedHeader));
    } catch (Throwable e) {
      e.printStackTrace();
      System.out.println("There is a test failure");
      testFailure = true;
      //fail("Failed: assertHelpPageHeader");
    }
    return testFailure;
  }

//    public void assertHelpPageHeaderIframes(String expectedHeader, boolean printout) {
//        try{
//            //Thread.sleep(3000);  //original working wait-replaced by sleep 500 and waitForJsWindowOnload
//            // System.out.println("Thread sleep 3000 - find better solution"); //find better solution
//            //waitForElementDoWhileLoop();
//            Thread.sleep(2000);
//            waitForJsWindowOnload();
//            driver.switchTo().frame("topic");
//            WebElement header = driver.findElement(By.xpath("//body/h1"));
//            String actualHeader = header.getText();
//            if (printout) {
//                System.out.println("Expected Header Text: " + expectedHeader);
//                System.out.println("Actual Header Text  : " + actualHeader);
//            }
//            assertEquals(expectedHeader, actualHeader);
//        } catch (Throwable e){
//            e.printStackTrace();
//            fail("Failed: assertHelpPageHeader");
//        }
//    }
}
