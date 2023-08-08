package webdriver.scripts.datamaintenance.datamaintenancehelpers;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.aventstack.extentreports.Status;

import ExtentReport.ExtentReport;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import webdriver.globalstatic.LoginStatic;
import webdriver.maps.EditContractingModelMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;

public class UtilitiesPageHelper extends LoginStatic {

	private static ModelLibraryMap modelMap;
	private static EditContractingModelMap editModelMap;
	JavascriptExecutor jscript = (JavascriptExecutor) driver;

	/**
	 * Helper Class for Contract Models pages - individual test scripts should
	 * extend this one to use it.
	 */
	@BeforeClass
	public static void setupHelper() {
		modelMap = BuildMap.getInstance(driver, ModelLibraryMap.class);
		editModelMap = BuildMap.getInstance(driver, EditContractingModelMap.class);
	}

	public void runReport(String startDate, String endDate, String[] codes) throws InterruptedException {
		waitForAjaxExtJs();
		waitForElementToBeVisible(driver.findElement(By.name("startDate1")));
		// jscript.executeScript("document.getElementsByName('startDate1')[0].value=
		// 'startDate'");

		try {
			driver.findElement(By.name("startDate1")).click();
			Thread.sleep(200);
			driver.findElement(By.name("startDate1")).clear();
			Thread.sleep(200);
			driver.findElement(By.name("startDate1")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
			driver.findElement(By.name("startDate1")).sendKeys(startDate);
			Thread.sleep(200);
			driver.findElement(By.name("endDate1")).click();
			driver.findElement(By.name("endDate1")).clear();
			driver.findElement(By.name("endDate1")).sendKeys(endDate);
//			Omkar 8/8/2023 : xpath changes for 11.2
//			driver.findElement(By.xpath("//button/span[text()='Select']")).click();
//			driver.findElement(By.xpath("//button/span[text()='Apply']")).click();
			driver.findElement(By.xpath("//span[text()='Select']")).click();
			selectItemsOnSelector(codes);
			driver.findElement(By.xpath("//span[text()='Apply']")).click();
		} catch (Exception e) {
			ExtentReport.extenttest.log(Status.FAIL, "Element not Found");

			ExtentReport.extenttest.log(Status.INFO, e);
		}
		waitForAjaxExtJs();
//		Omkar 8/8/2023 : xpath changes for 11.2
//		waitForElementToBeVisible(driver.findElement(By.xpath("//button/span[text()='Run']")));
		waitForElementToBeVisible(driver.findElement(By.xpath("//span[text()='Run']")));
		try {
//			Omkar 8/8/2023 : xpath changes for 11.2
//			driver.findElement(By.xpath("//button/span[text()='Run']")).click();
			driver.findElement(By.xpath("//span[text()='Run']")).click();
		}catch (Exception e) {
			ExtentReport.extenttest.log(Status.FAIL, "Element not Found");

			ExtentReport.extenttest.log(Status.INFO, e);
		}
		waitForSpinnerToEnd();
		waitForUtilityFirstRowDownloadLinkToBecomeActive();
//		Omkar 8/8/2023 : xpath changes for 11.2
//		driver.findElement(By.xpath("//tbody/tr[2]/td/div/a[@class='stLinks' and text()='Download']")).click();
		driver.findElement(By.xpath("(//table/tbody/tr[1]/td)[5]/div/a")).click();
		Thread.sleep(1000);
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
				driver.findElement(By.xpath("//tr/td/div[text()='" + item + "']")).click();
				Thread.sleep(2000);
//				Omkar 8/8/2023 : xpath changes for 11.2
//				driver.findElement(By.xpath("//button[not(@disabled)]/span[text()='Select']")).click();
				driver.findElement(By.xpath("//div[(@class='x-container x-box-item x-container-default x-box-layout-ct')]//span[text()='Select']")).click();
				waitForSpinnerToEnd();
				Thread.sleep(900);
			}
		}
		catch (Throwable e) {
			System.out.println("Codes could not be selected");
		}
		
	}

	public static void waitForUtilityFirstRowDownloadLinkToBecomeActive() throws InterruptedException {
		boolean calculate = true;
		String download;
		byte counter = 0;
		while (calculate) {
			try {
//				Omkar 8/8/2023 : xpath changes for 11.2
//				driver.findElement(By.xpath("//span[text()='Refresh']")).click();
				driver.findElement(By.xpath("//span[text()='Refresh']")).click();
				waitForSpinnerToEnd();
				/*
				Omkar 8/8/2023 : xpath changes for 11.2
				download = driver
						.findElement(By.xpath("//tbody/tr[2]/td/div/a[@class='stLinks' and text()='Download']"))
						.getAttribute("class"); 
						*/
				download = driver.findElement(
						By.xpath("(//table/tbody/tr[1]/td)[5]/div/a"))
						.getAttribute("class")
						;
				System.out.println("Download: " + download);
				try {
					assertTrue(download.contains("stLinks"));
				} catch (AssertionError e) {
					ExtentReport.extenttest.log(Status.FAIL, e.getMessage());

					ExtentReport.extenttest.log(Status.INFO, e);
				}
				break;
			} catch (Throwable e) {
				System.out.println("Utility not complete");
			//	ExtentReport.extenttest.log(Status.INFO, e);
				Thread.sleep(5000);
				if (counter == 30) {
					try {
						fail("Utility did not finish in allotted time");
					} catch (AssertionError e1) {
						ExtentReport.extenttest.log(Status.FAIL, "Utility did not finish in allotted time");

						ExtentReport.extenttest.log(Status.INFO, e1);
					}
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
		//    Omkar 8/8/2023 : xpath changes for 11.2
		//    waitForPresenceOfElement("//div[contains(@class, 'delBtn')]/descendant::button/span[contains(@class, 'x-btn-icon')]");
		//    WebElement firstRowDeleteIcon = driver.findElement(By.xpath("//div[contains(@class, 'delBtn')]/descendant::button/span[contains(@class, 'x-btn-icon')]"));
		waitForPresenceOfElement("(//table/tbody/tr[1]/td)[9]//span[contains(@class, 'delBtn')]");
		WebElement firstRowDeleteIcon = driver.findElement(By.xpath("(//table/tbody/tr[1]/td)[9]//span[contains(@class, 'delBtn')]"));
		firstRowDeleteIcon.click();
		//    Omkar 8/8/2023 : xpath changes for 11.2
		//    waitForPresenceOfElement("//div[contains(@class,'windowbtn')]/descendant::button/span[text()='Delete']");
		//    driver.findElement(By.xpath("//div[contains(@class,'windowbtn')]/descendant::button/span[text()='Delete']")).click();
		waitForPresenceOfElement("//div[@role='dialog']//span[text()='Delete']");
		driver.findElement(By.xpath("//div[@role='dialog']//span[text()='Delete']")).click();
		waitForSpinnerToEnd();
		waitForAjaxExtJs();
	}

}
