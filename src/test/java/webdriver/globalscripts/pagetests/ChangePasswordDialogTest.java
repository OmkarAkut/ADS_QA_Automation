package webdriver.globalscripts.pagetests;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
import webdriver.helpers.UcqcHelper;
import webdriver.maps.GeneralElementsMap;
import webdriver.maps.mapbuilder.BuildMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ChangePasswordDialogTest extends GoHelper {

	private static GeneralElementsMap generalElement;
	final static String warningText = "The old password you typed is not valid. Please type your old password again";
	final static String resetText = "To reset your password, provide your current password and new password";
	final static String termsOfUseText = "TERMS OF USE";
	private static String BackgroundColorTermsOfUse = "rgba(0, 73, 141, 1)";

	/** Regression test ADS-6585 **/
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ChangePasswordDialogTest", "webdriver.globalscripts.pagetests",
				"ChangePasswordDialogTest");
		try {
			generalElement = BuildMap.getInstance(driver, GeneralElementsMap.class);
			System.out.println("Test Class: " + ChangePasswordDialogTest.class.getSimpleName());
			/*
			 * modified by Omkar on 27/5/22 as only aadmin user is available for qa3 env
			 * Login.loginUser("AutomationTester1");
			 */
			Login.loginUser("AutomationTesterAdmin");
			ExtentReport.logPass("PASS", "setupScript");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);

			fail(e.getMessage());
		}
		// End of modification
	}

	@Test
	public void test01OpenChangePasswordDialogAndAssert_6614() throws Throwable {
		try {
			assertElementIsDisplayed(generalElement.getUserDropdown(), printout);
			generalElement.getUserDropdown().click();
			waitForPresenceOfElement("//*[@id = 'changePassword']");
			generalElement.getUserDropdownChangePassword().click();
			assertTextIsDisplayed(resetText);
			ExtentReport.logPass("PASS", "test01OpenChangePasswordDialogAndAssert");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01OpenChangePasswordDialogAndAssert", driver, e);

			fail(e.getMessage());
		}
	}

	/** [Main Page UI] Validate �Change Password� section on dashboard.ADS-6614 **/
	@Test
	public void test02OpenChangePasswordDialogAndAssert() throws InterruptedException, Throwable {
		try {
			driver.findElement(By.name("oldpassword")).sendKeys("a");
			driver.findElement(By.name("newpassword")).sendKeys("a");
			driver.findElement(By.name("confirmnewpassword")).sendKeys("a");
			doClickButton("Save & Close");
			waitForPresenceOfElement("//*[text()='" + warningText + "']");
			assertTextIsDisplayed(warningText);
			doClickButton("OK");
			ExtentReport.logPass("PASS", "test02OpenChangePasswordDialogAndAssert");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02OpenChangePasswordDialogAndAssert", driver, e);

			fail(e.getMessage());
		}
	}

	@Test
	public void test03CloseChangePasswordDialog() throws InterruptedException, Throwable {
		try {
			doClickButton("Cancel & Close");
			driverDelay(1000);
			assertTextIsNotDisplayed(resetText);
			ExtentReport.logPass("PASS", "test03CloseChangePasswordDialog");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03CloseChangePasswordDialog", driver, e);

			fail(e.getMessage());
		}
	}
//ADS-6585
	@Test
	public void test04AssertTermsOfUse_6585() throws InterruptedException, Throwable {
		try {
			assertElementIsDisplayed(generalElement.getUserDropdown(), printout);
			generalElement.getUserDropdown().click();
			UcqcHelper.validateBackgroundColor(BackgroundColorTermsOfUse,
					driver.findElement(By.xpath("//a[text()='Terms of Use']//parent::li")));
			waitForPresenceOfElement("//*[@id='adiv']/descendant::*[text()='Terms of Use']");
			generalElement.getUserDropdownTermsOfUse().click();
			waitForPresenceOfElementText(termsOfUseText);
			assertTextIsDisplayed(termsOfUseText);
			doClosePageOnLowerBar("Terms of Use");
			ExtentReport.logPass("PASS", "test04AssertTermsOfUse");

		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test04AssertTermsOfUse", driver, e);

			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
