/*
 * Remark : Comment by Omkar on 27/5/22
 * This class has no tests so logically this should not be placed in Test folder.
 * 
 */



package webdriver.globalscripts.pagetests;

import static org.junit.Assert.*;

import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import webdriver.globalstatic.LoginStatic;

public class PageTestHelperStatic extends LoginStatic {


	public void testHelpLink(WebElement helpLinkElement, String expectedHeader, boolean printout) throws InterruptedException {
		Thread.sleep(500);
//		waitForAjaxExtJs();
		//below code is added by Omkar on 6/6/22 to move the mouse pointer to other location to close the menu
		Thread.sleep(1000);
		Actions builder = new Actions(driver);
		/*
		WebElement el = helpLinkElement;
		builder.keyUp(Keys.CONTROL)
		.moveByOffset( 10, 25 )
		//.clickAndHold(el)
		.build().perform();
		*/
		builder.moveByOffset(10,20).perform();
		//end of modification
		String firstHandle = webSwitchToNewWindow(helpLinkElement, printout);
		Thread.sleep(5000);
		assertHelpPageHeader(expectedHeader, printout);
		driver.close();//Shilpa 03.11.2022 
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
		assertHelpPageHeader(expectedHeader, printout);
		driver.close();
		driver.switchTo().window(firstHandle);
	}


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
		Thread.sleep(6000);
//		waitForAjaxExtJs();
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


	public void assertHelpPageHeader(String expectedHeader, boolean printout) {
		try {
			//Thread.sleep(3000);  //original working wait-replaced by sleep 500 and waitForJsWindowOnload
			// System.out.println("Thread sleep 3000 - find better solution"); //find better solution
			Thread.sleep(2000);
			waitForJsWindowOnload();
			driver.switchTo().frame("topic");
			Thread.sleep(3000);
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

}
