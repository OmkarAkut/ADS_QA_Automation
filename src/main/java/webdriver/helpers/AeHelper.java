package webdriver.helpers;

import java.time.Duration;
import java.util.List;

import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import webdriver.corehelpers.GoHelper;
import webdriver.maps.AeMap;
import webdriver.maps.CimMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DialogsMap;
import webdriver.maps.mapbuilder.BuildMap;

public class AeHelper extends GoHelper {
	public static AeMap aeMap;
	
	@BeforeClass
	public static void setupHelper() {
		aeMap=BuildMap.getInstance(driver, AeMap.class);
	}
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
	public static void  cancelScheduleJobs() throws Throwable {
		doClick(aeMap.getScheduledBtn());
		
			if(!aeMap.getnoJobSchedule().isEmpty()&&aeMap.getnoJobSchedule().get(0).isDisplayed()) {
				assertTextIsDisplayed("No jobs are currently scheduled.");
				doClick("(//*[text()='No jobs are currently scheduled.']//following::button)[1]");
//				doClick(aeMap.getschedulePopUpCloseBtn());
				
			}
			else {
			waitForElementToBeVisible(aeMap.getscheduleDetailsPopUp());
			List<WebElement> rows = aeMap.getcancelScheduleBtn();
			int count=rows.size();
			for( int i=0;i<count;i++){
				rows=aeMap.getcancelScheduleBtn();
				WebElement deleteRow=rows.get(0).findElement(By.xpath("//h1[@id='scheduledModalLabel']//following::table//tbody//tr//th//button[1]"));
				deleteRow.click();
				driverDelay(100);
			}
			doClick("(//*[text()='Job successfully cancelled.']//following::button)[1]");
			}
			
		
	}
	public static void createAeJob(String jobName) {
		doClick(aeMap.getexecuteJobBtn());
		waitForElementToBeVisible(aeMap.getexecuteJobPopUp());
		aeMap.getexecuteJobName().sendKeys(jobName);
		doClick(aeMap.getexecuteNowBtn());
		doClick(aeMap.getexecuteJobNowBtn());
		assertTextIsDisplayed("Job Created Successfully.");//Validate job created
		waitForElementToBeVisible(aeMap.getjobCloseBtn());
		doClick(aeMap.getjobCloseBtn());
	}
	public static String addCustomTime(String date) {
		aeMap.getcustomDateTimeInput().sendKeys(date);
		Actions action=new Actions(driver);
		String Time=CimHelper.getSystemTimeFormatted();
		System.out.println(Time);
		String[] time=Time.toUpperCase().replace(":", " ").split(" ");
		System.out.println(time[0]);
		System.out.println(time[1]);
		System.out.println(time[2]);
		System.out.println(time[3]);
		action.moveToElement(aeMap.getcustomDateTimeInput()).sendKeys(time[0]).sendKeys(time[1]).sendKeys(time[3]).perform();
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
