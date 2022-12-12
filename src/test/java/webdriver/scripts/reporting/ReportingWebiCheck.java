package webdriver.scripts.reporting;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;

public class ReportingWebiCheck extends GoHelper {

	static String firstHandle;

	@BeforeClass
	public static void setupScript() throws Exception,Throwable {
		ExtentReport.reportCreate("ReportingWebiCheck", "webdriver.scripts.reporting", "ReportingWebiCheck");
		try {
			System.out.println("Test Class: " + ReportingWebiCheck.class.getSimpleName());

//			Login.loginUser("AutomationTester1");
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Web Intelligence");
			ExtentReport.logPass("PASS", "setupScript");
		}  catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void testWebiPageLoads() throws Throwable {
		try {
			waitForAjaxExtJs();
			firstHandle = webdriverSwitchToNewWindow(printout);
			waitForPresenceOfElement("//*[@name = 'servletBridgeIframe']");
			webdriverSwitchToFrame("servletBridgeIframe");
			//Omkar (14/8/2022) to click on ok button for a popup that appears
			List<WebElement> chkbx = driver.findElements(By.id("dialogService_1_check"));
			if(chkbx.size()!= 0) {
				chkbx.get(0).click();
				driver.findElement(By.id("yui-gen1")).click();
			}
			//omkar : end of edit
/*
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
			*/
			//shilpa 12.12.2022
			driverDelay();
			waitForElementPresence("//span[contains(@id,'pageApplicationSection-anchor-content')]");
			assertThatElementIsDisplayed(driver.findElement(By.xpath(("//span[@id='Folders-title-inner']"))));
			assertThatElementIsDisplayed(driver.findElement(By.xpath(("//span[@id='Categories-title-inner']"))));
			assertThatElementIsDisplayed(driver.findElement(By.xpath(("//span[@id='Documents-title']"))));
			assertThatElementIsDisplayed(driver.findElement(By.xpath(("//span[@id='Inbox-title']"))));
			driver.findElement(By.xpath("//span[contains(@id,'pageApplicationSection-anchor-content')]")).click();
			driverDelay();
			driver.findElement(By.xpath("//span[@id='Web_Intelligence-title']")).click();
			driverDelay(10000);
			waitUntilElementIsClickable(driver.findElement(By.xpath("//div[contains(@class,'sapMDialogScrollCont ')]//button[contains(@class,'wingTestDataSourcePickerCancel')]//child::span[@class='sapMBtnContent']")));
			driver.findElement(By.xpath("//div[contains(@class,'sapMDialogScrollCont ')]//button[contains(@class,'wingTestDataSourcePickerCancel')]//child::span[@class='sapMBtnContent']")).click();
			driverDelay(1000);
			assertThatElementIsDisplayed(driver.findElement(By.xpath(("(//bdi[text()='Web Intelligence'])[1]"))));
			assertThatElementIsDisplayed(driver.findElement(By.xpath(("//span[@id='home-icon-img']"))));

			driver.findElement(By.id("avatar")). click();
			Thread.sleep(200);
			driver.findElement(By.xpath("//div[text()='Log out']")). click();
			ExtentReport.logPass("PASS", "testWebiPageLoads");
		}  catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "testWebiPageLoads", driver, e);
			fail(e.getMessage());
		}finally {
			try {
				driver.close();
				driver.switchTo().window(firstHandle);
			} catch (Exception e) {
				
			}
		}
	}
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}

