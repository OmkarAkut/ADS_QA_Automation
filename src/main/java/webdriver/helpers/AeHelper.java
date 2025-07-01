package webdriver.helpers;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import webdriver.corehelpers.GoHelper;
import webdriver.maps.AeMap;
import webdriver.maps.mapbuilder.BuildMap;

public class AeHelper extends GoHelper {
	private static AeMap aeMap=BuildMap.getInstance(driver, AeMap.class);
	
	public static void switchToNewTab(WebDriver driver) {
	    String originalWindow = driver.getWindowHandle();

	    // Wait for new window to appear (optional: use explicit wait if needed)
	    new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver1 -> driver1.getWindowHandles().size() > 1);

	    for (String windowHandle : driver.getWindowHandles()) {
	        if (!windowHandle.equals(originalWindow)) {
	            driver.switchTo().window(windowHandle);
	            break;
	        }
	    }
	}
	public void closeNewTabAndReturn(WebDriver driver, String originalWindowHandle) {
	    // Close the current (new) tab
	    driver.close();

	    // Switch back to the original tab
	    driver.switchTo().window(originalWindowHandle);
	}

	public static void openScheduleWindow() {
		doClick(aeMap.gettaskSelect());
		doClick(aeMap.getexecuteJobBtn());
		waitForElementToBeVisible(aeMap.getexecuteJobPopUp());
	}
}
