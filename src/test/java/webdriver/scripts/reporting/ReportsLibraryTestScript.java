package webdriver.scripts.reporting;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.users.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReportsLibraryTestScript extends GoHelper {

	@BeforeClass
	public static void setupScript() throws InterruptedException,Throwable {
		ExtentReport.reportCreate("ReportsLibraryTestScript", "webdriver.scripts.reporting", "ReportsLibraryTestScript");
		try {
			System.out.println("Test Class: " + ReportsLibraryTestScript.class.getSimpleName());
			Login.loginUser(Users.AutomationTester1);
			goToPage("report library");
			ExtentReport.logPass("PASS", "setupScript");

		} catch(Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);

		      fail(e.getMessage());
		    }
	}

	@Test
	public void test01RunStandardReportFromTemplates() throws Throwable {
		//waitForPresenceOfElement(reportDirectoryXpath("Templates", "Costing"));
		//waitForPresenceOfElement("//div[contains(text(),'Templates')]/ancestor::table/following-sibling::div/descendant::*[contains(text(), 'Costing')]");
		try {
			ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(text(),'Templates')]/ancestor::table/following-sibling::div/descendant::*[contains(text(), 'Costing')]"));
			ExpectedConditions.presenceOfElementLocated(By.xpath("//iframe[contains(@src,'reporting/main.html')]"));
			//Omkar 18/8/2022 : Switching the frame and the element is not clickable directly
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'reporting/main.html')]")));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.findElement(By.xpath("//div[contains(text(),'Templates')]/ancestor::table/following-sibling::div/descendant::*[contains(text(), 'General Reports')]")).isDisplayed();
			driver.findElement(By.xpath("//div[contains(text(),'Templates')]/ancestor::table/following-sibling::div/descendant::*[contains(text(), 'General Reports')]")).click();
			//Shilpa 16.09.2022 select all text and then input text, sometimes if input has text then req text will be appended
			driver.findElement(By.xpath("//input[@type='text' and @class='GJT013UBASB']")).sendKeys(Keys.chord(Keys.CONTROL,"a"));
			driver.findElement(By.xpath("//input[@type='text' and @class='GJT013UBASB']")).sendKeys("CM Cost Model per RVU");
			driverPause();//Shilpa 16.09.2022
			driver.switchTo().parentFrame();
			ExtentReport.logPass("PASS", "test01RunStandardReportFromTemplates");

		} catch(Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test01RunStandardReportFromTemplates", driver, e);

		      fail(e.getMessage());
		    }

	}

	@Test
	public void test02RunStandardReportFromSavedReports() throws InterruptedException,Throwable {
		try {
			goToPage("report library");
			driverDelay();
			ExtentReport.logPass("PASS", "test02RunStandardReportFromSavedReports");

		} catch(Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test02RunStandardReportFromSavedReports", driver, e);

		      fail(e.getMessage());
		    }
	}

	@Test
	public void test01RunFlexReportFromTemplates() throws InterruptedException,Throwable {
		try {
			goToPage("report library");
			driverDelay();
			ExtentReport.logPass("PASS", "test01RunFlexReportFromTemplates");

		} catch(Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test01RunFlexReportFromTemplates", driver, e);

		      fail(e.getMessage());
		    }	}

	@Test
	public void test01RunFlexReportFromSavedReports() throws InterruptedException,Throwable {
		try {
			goToPage("report library");
			driverDelay();
			ExtentReport.logPass("PASS", "test01RunFlexReportFromSavedReports");

		} catch(Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test01RunFlexReportFromSavedReports", driver, e);

		      fail(e.getMessage());
		    }	
	}

	public String reportDirectoryXpath (String directory, String subDirectory) {
		return "//div[contains(text(),'" + subDirectory + "')]/ancestor::table/following-sibling::div/descendant::*[contains(text(), '" + directory + "')]";
	}
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
