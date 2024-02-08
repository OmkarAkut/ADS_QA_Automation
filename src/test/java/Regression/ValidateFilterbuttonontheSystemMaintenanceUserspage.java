package Regression;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.SystemMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/**
 * Regression test case ADS-6510
 **/
public class ValidateFilterbuttonontheSystemMaintenanceUserspage extends GoHelper {
	private static SystemMaintenanceMap sysmaint;
	private static ContractingMap modelMap;
	static String[] filter = { "First Name", "Is", "Equal To", "Test" };
	static String updateFilterModel = "Testing";
	static String filterText = "Test";
	Actions action = new Actions(driver);

	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ValidateFilterbuttonontheSystemMaintenanceUserspage",
				"webdriver.scripts.systemmaintenance", "ValidateFilterbuttonontheSystemMaintenanceUserspage");
		try {
			sysmaint = BuildMap.getInstance(driver, SystemMaintenanceMap.class);
			modelMap = BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Users");
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
//ADS-6510
	@Test
	public void test01ValidateFilterOption() throws Throwable {
		try {
			doClick(sysmaint.getUsersPageButtonFilter());
			doFilterSetFilterParameters("First Name", "Is", "Equal To", "Test");
//			doClick(driver.findElement(By.xpath("//span[text()='Add']")));
			//Shilpa udated xpath for 11.2 on 12.15.2023
			doClick("//div[contains(@id,'filter')]//span[text()='Add']");
			action.moveToElement(modelMap.getContractModelEditFilterButton()).click().pause(10).perform();
			driver.findElement(By.name("valuefield")).sendKeys(Keys.chord(Keys.CONTROL, "a"));
			driver.findElement(By.name("valuefield")).sendKeys(updateFilterModel);
			doClick(modelMap.getContractModelUpdateFilterButton());
			action.moveToElement(modelMap.getContractModelRemoveFilterButton()).click().pause(10).perform();
			doFilterCreate(filter);
			for (WebElement element : sysmaint.getSystemMaintenanceUserList()) {
				if (element.getText().equalsIgnoreCase(filterText)) {
					assertTrue(printout);
				}

				else {

					fail();
				}
			}
			ExtentReport.logPass("PASS", "test01ValidateFilterOption");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01ValidateFilterOption", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();

	}
}
