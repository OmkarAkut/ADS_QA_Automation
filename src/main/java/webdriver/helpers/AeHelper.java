package webdriver.helpers;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
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
	public static void closeNewTabAndReturn(WebDriver driver, String originalWindowHandle) {
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
	
	public static void openExecuteJob() {
		doClick(aeMap.getexecuteJobBtn());
		waitForElementToBeVisible(aeMap.getexecuteJobPopUp());
	}
	
	public static String addCustomTime(String date) {
		aeMap.getcustomDateTimeInput().sendKeys(date);
		Actions action=new Actions(driver);
		String Time=CimHelper.getSystemTimeFormatted();
		System.out.println(Time);
		String[] time=Time.toUpperCase().replace(":", " ").split(" ");
		action.moveToElement(aeMap.getcustomDateTimeInput()).sendKeys(Keys.ARROW_RIGHT).sendKeys(time[0]).sendKeys(time[1]).sendKeys(time[2]).perform();
		return Time;
	}
	public static void selectRecurrence(String recurrence) {
		doClick(aeMap.getrecurrenceDropdown());
		Select option=new Select(aeMap.getrecurrenceDropdown());
		option.selectByValue(recurrence);
	}
	public static String setRecurrence(String recurrence) throws Throwable {
		doClick(aeMap.getcustomDateTimeBtn());
		doClick(aeMap.getcustomDateTimeInput());
		String time = null;
		if(recurrence.equals("DAILY")) {
			String date=CimHelper.getCurrentDate("current");
			addCustomTime(date);
			selectRecurrence(recurrence);
		}
		if(recurrence.equals("MONTHLY")) {
			String date=CimHelper.getCurrentDate("current");
			addCustomTime(date);
			selectRecurrence(recurrence);
		}
		if(recurrence.equals("ANNUALLY")) {
			String date=CimHelper.getCurrentDate("current");
			addCustomTime(date);
			selectRecurrence(recurrence);
		}
		if(recurrence.equals("QUARTERLY")) {
			String date=CimHelper.getCurrentDate("current");
			addCustomTime(date);
			selectRecurrence(recurrence);
		}
		if(recurrence.equals("WEEKLY")) {
			String date=CimHelper.getCurrentDate("current");
			time=addCustomTime(date);
			selectRecurrence(recurrence);
		}
		return time;
	}
}
