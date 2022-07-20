package webdriver.helperstatic;

import junit.framework.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.fail;

import java.time.Duration;

public class WaitStatic extends JavaListStatic {

  public void waitSpinAjaxDelay(int milliseconds) throws InterruptedException {
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    Thread.sleep(milliseconds);
  }

  public void waitUntilTreeOptionIsClickable(String treeOptionName) {
    boolean wait = true;
    while (wait){
      try {
        waitUntilElementIsClickable(
                driver.findElement(By.xpath("//td[contains(@class,'x-grid-cell-treecolumn')]/div[text()='" + treeOptionName + "']"))
        );
        wait = false;
      } catch (NoSuchElementException nsee) {
        continue;
      } catch (Exception e) {
        TestCase.fail("Tree Element Not Found");
      }
    }
  }

  public void waitForMainPageTitle(String pageTitle) throws InterruptedException {
    waitUntilElementIsVisible(
            driver.findElement(By.xpath("//*[text()='" + pageTitle + "']"))
    );
    waitUntilElementIsClickable(driver.findElement(By.name("inputItem")));
    
    
  }

  public void waitForPageTitle(String pageTitle) {
    waitUntilElementIsVisible(
            driver.findElement(By.xpath("//span[contains(@class, 'header') and text()='" + pageTitle + "']"))
    );
  }

  public static void waitUntilElementIsVisible(WebElement element) {
    WebDriverWait webdriverWait = new WebDriverWait(driver,30);
    webdriverWait.until(ExpectedConditions.visibilityOf(element));
  }

  /** Waits for element to be displayed using do-while loop.  Does not fail the test. */
  public static void waitForElementDoWhileLoop(WebElement element, boolean printout) throws InterruptedException {
    int counter = 0;
    boolean foundElement;
    do {
      try {
        foundElement = element.isDisplayed();

        if (printout) {
          System.out.println("waitForElementDoWhileLoop: foundElement = " + foundElement);
        }
        if (counter > 30) {
          break;
        }
        counter++;
        Thread.sleep(500);
      } catch(Throwable e){
        foundElement = false;
      }
    }
    while(!foundElement);
  }

  /** Uses the WebDriver Javascript Executor to run window.onload, which waits for all page elements to be loaded */
  public static void waitForJsWindowOnload() {
    try {
      JavascriptExecutor js = (JavascriptExecutor) driver;
      js.executeScript("window.onload = function () {}" );
    } catch (Throwable e) {
      System.out.println("Js Window Onload Did Not Run Successfully");
    }
  }

  public static void waitForSpinnerToEndNoTimeout() {
    boolean spinner = true;
    while(spinner){
      try {
        spinner = driver.findElement(By.xpath("//*[contains(text(),'Loading...')]")).isDisplayed();
      } catch (Throwable e) {
        System.out.println("NOTICE: Could not find Spinner element");
        break;
      }
    }
  }

  /** Waits for onscreen "spinner box" to disappear. */
  public static void waitForSpinnerToEnd() {
    boolean spinner = true;
    while(spinner){
      try {
        spinner = driver.findElement(By.xpath("//*[contains(text(),'Loading...')]")).isDisplayed();
        if (spinner) {
          continue;
        } else {
          break;
        }
      } catch (Throwable e) {
        break;
      }
    }
  }

  public static void waitForDisplayedSpinnerToEnd() {
    boolean spinner = true;
    while(spinner){
      try {
        spinner = driver.findElement(By.xpath("//div[not(contains(@style, 'display: none'))]/div[contains(text(),'Loading...')]")).isDisplayed();
        if (spinner) {
          continue;
        } else {
          break;
        }
      } catch (Throwable e) {
        break;
      }
    }
  }

  /** Uses WebDriverWait ExpectedConditions.visibilityOf but does not click. Timeout is 30s. */
  public static void waitForElementToBeVisible(WebElement element){
	  /*
	   * below lines are commented by Omkar on 31/5/2022 as WebDriverWait is now deprecated in Selenium 4
		 
    WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(ExpectedConditions.visibilityOf(element));
    */
	  WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
	  wait.until(ExpectedConditions.visibilityOf(element));
  }

  /** Uses WebDriverWait ExpectedConditions.elementToBeClickable but does not click. Timeout is 30s. 
 * @throws InterruptedException */
  public static void waitUntilElementIsClickable(WebElement element){
	/*
	 * below lines are commented by Omkar on 31/5/2022 as WebDriverWait is now deprecated in Selenium 4
	 
	WebDriverWait wait = new WebDriverWait(driver, 30);
    wait.until(ExpectedConditions.elementToBeClickable(element));
    */
	  //below code is added by Omkar on 31/5/22 
	  //to hover the mouse location to a different location as button under the opened menu was not visible
	  try {
		Thread.sleep(2000);
		Actions builder = new Actions(driver);
		  WebElement el = element;
		  builder.keyDown(Keys.CONTROL)
		  .moveByOffset( 10, 25 )
		  .clickAndHold(el)
		  .build().perform();

	    Thread.sleep(2000);
	    WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
	    wait.until(ExpectedConditions.visibilityOf(element));
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
  }

  /** Using JS Executor, waits for Ext.Ajax.isLoading() to be false. */
  public static void waitForAjaxExtJs() throws InterruptedException {
    Boolean waitForAjax = true;
    JavascriptExecutor jse = (JavascriptExecutor) driver;
    while(waitForAjax){
      int counter = 0;
      waitForAjax = ((Boolean) jse.executeScript("return Ext.Ajax.isLoading();"));
      counter++;
      if(counter == 30) {
        //System.out.println("FAILED waiting for Ajax ExtJs to complete");
        fail("FAILED waiting for Ajax ExtJs to complete");
      }
    }
  }

  public static void waitForJsReadyState() throws InterruptedException {
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
            .executeScript("return document.readyState").equals("complete"));
    }

  /** Uses the WebDriver Javascript Executor to run window.onload, which waits for all page elements to be loaded */
  public static void waitForElementPresence(String byLocator) {
    WebDriverWait wait = new WebDriverWait(driver, 10);
    try {
      //WebElement we = wait.until(ExpectedConditions.presenceOfElementLocated(by));
    } catch (Throwable e) {
      System.out.println("Js Ready State failure");
    }
  }

  /**  */
  public static void waitForPresenceOfElement(String xpath) {
    WebDriverWait webdriverWait = new WebDriverWait(driver,30);
    webdriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(""+xpath+"")));
  }

}
