package Regression;
import static org.junit.Assert.fail;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.corehelpers.GoHelper;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/** Regression test case ADS-6512
 **/
public class ValidateSecuritySettingsGeneralSettingspagesintheSystemMaintenance extends GoHelper {
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("EditExistingRoleSetup", "webdriver.scripts.systemmaintenance",
				"EditExistingRoleSetup");
		try {
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Security Settings");
			waitForDisplayedSpinnerToEnd();
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	//ADS-6512
	@Test
	public void test01AssertSystemSettingsPageIsShown_6512() throws Throwable {
		try {
//			Omkar 29/5/2023 : xpath changes for 11.2
//			assertElementIsDisplayedWithXpath("//div[contains(@class,'x-container areaTitle')][text()='Security Settings']");
			assertElementIsDisplayedWithXpath("//div[contains(@class,'x-container areaTitle')]//div[text()='Security Settings']");
			ExtentReport.logPass("PASS", "test01AssertSystemSettingsPageIsShown");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test01AssertSystemSettingsPageIsShown", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test02ValidateSystemSettingParameters_6512() throws Throwable {
		try {
			assertTextIsDisplayed("Authentication");
			assertTextIsDisplayed("Password Policies");
			assertTextIsDisplayed("Business Objects Server");
			assertTextIsDisplayed("Authorization");
			assertTextIsDisplayed("Audit Logging");
			ExtentReport.logPass("PASS", "test02ValidateSystemSettingParameters");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test02ValidateSystemSettingParameters", driver, e);
			fail(e.getMessage());
		}
		finally {
			doClosePageOnLowerBar("Security Settings");
		}
	}
	@Test
	public void test03AssertGeneralSettingsPageIsShown_6512() throws Throwable {
		try {
			goToPage("General Settings");
			waitForDisplayedSpinnerToEnd();
//			Omkar 29/5/2023 : xpath changes for 11.2
//			assertElementIsDisplayedWithXpath("//div[contains(@class,'x-panel-header-text-container')]/span[text()='General Settings']");
			assertElementIsDisplayedWithXpath("//div[@id='generalsettings_panel_header-title-textEl'][text()='General Settings']");
			ExtentReport.logPass("PASS", "test03AssertGeneralSettingsPageIsShown");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03AssertGeneralSettingsPageIsShown", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test04ValidateGeneralSettingParameters_6512() throws Throwable {
		try {
//			Omkar 29/5/2023 : xpath changes for 11.2
//			assertElementIsDisplayedWithXpath("//div[contains(@class,'x-panel-header-text-container')]/span[text()='General Settings']");
			assertElementIsDisplayedWithXpath("//div[@id='generalsettings_panel_header-title-textEl'][text()='General Settings']");
			assertTextIsDisplayed("User Interface Settings");
			assertElementIsDisplayedWithXpath("//*[contains(text(),'Purge Logs Settings')]");
			ExtentReport.logPass("PASS", "test03AssertGeneralSettingsPageIsShown");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "test03AssertGeneralSettingsPageIsShown", driver, e);
			fail(e.getMessage());
		}
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();

	}
}
