package webdriver.corehelpers;

import static org.junit.Assert.fail;

import java.time.Duration;
import junit.framework.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitHelper extends JavaHelper {

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

	public void waitForLandingPageFooter() {
		//WebDriverWait wait = new WebDriverWait(driver, 30);
		WebDriverWait webdriverWait = new WebDriverWait(driver, Duration.ofSeconds(30,0));;
		webdriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='footerText']")));
	}

	public void waitForMainPageTitle(String pageTitle) {
		waitForPresenceOfElement("//*[text()='" + pageTitle + "']");
		//    waitUntilElementIsVisible(
		//            driver.findElement(By.xpath("//*[text()='" + pageTitle + "']"))
		//    );
		//    waitUntilElementIsClickable(driver.findElement(By.name("inputItem")));
	}

	public void waitForPageTitle(String pageTitle) {
		waitUntilElementIsVisible(
				driver.findElement(By.xpath("//span[contains(@class, 'header') and text()='" + pageTitle + "']"))
				);
	}

	public static void waitUntilElementIsVisible(WebElement element) {
		//WebDriverWait wait = new WebDriverWait(driver, 30);
		WebDriverWait webdriverWait = new WebDriverWait(driver, Duration.ofSeconds(30,0));;
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
		int counter=0;

		while(spinner){
			try {
				spinner = driver.findElement(By.xpath("//*[contains(text(),'Loading...')]")).isDisplayed();
				if(counter==30) {
				if (spinner) {
					continue;
				}
				}
				else {
					break;
				}
			} catch (Throwable e) {
				break;
			}
			
		}
	}

	public static void waitForDisplayedSpinnerToEnd() {
		boolean spinner = true;
		int count=0;
		while(spinner){
			try {
				spinner = driver.findElement(By.xpath("//div[not(contains(@style, 'display: none'))]/div[contains(text(),'Loading...')]")).isDisplayed();
				if (spinner) {
					
					count++;
					if(count==10) {
						break;
					}
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
		//Edited by Omkar on 22/6/22 as the old wait is depreciated
		//WebDriverWait wait = new WebDriverWait(driver, 30);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30,0));
			wait.until(ExpectedConditions.visibilityOf(element));

	}

	/** Uses WebDriverWait ExpectedConditions.elementToBeClickable but does not click. Timeout is 30s. */
	public static void waitUntilElementIsClickable(WebElement element){
		//Edited by Omkar on 22/6/22 as the old wait is depreciated
		//WebDriverWait wait = new WebDriverWait(driver, 30);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40,0));
		wait.until(ExpectedConditions.elementToBeClickable(element));
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
		//WebDriverWait wait = new WebDriverWait(driver, 30);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10,0));
		wait.until((ExpectedCondition<Boolean>) wd -> ((JavascriptExecutor) wd)
				.executeScript("return document.readyState").equals("complete"));
	}

	/** Uses the WebDriver Javascript Executor to run window.onload, which waits for all page elements to be loaded */
	public static void waitForElementPresence(String byLocator) {
		//WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			//WebElement we = wait.until(ExpectedConditions.presenceOfElementLocated(by));
		} catch (Throwable e) {
			System.out.println("Js Ready State failure");
		}
	}

	/**  */
	public static void waitForPresenceOfElement(String xpath) {
		//WebDriverWait wait = new WebDriverWait(driver, 30);
		
			WebDriverWait webdriverWait = new WebDriverWait(driver, Duration.ofSeconds(50,0));;
			webdriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(""+xpath+"")));
			

	}

	public static void waitForPresenceOfElementText(String text) {
		waitForPresenceOfElement("//*[text()='" + text + "']");
	}





}
