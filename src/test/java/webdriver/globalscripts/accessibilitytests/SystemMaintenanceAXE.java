package webdriver.globalscripts.accessibilitytests;

import static org.junit.Assert.fail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import webdriver.globalstatic.LoginStatic;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;
import webdriver.utilities.Axe;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)


public class SystemMaintenanceAXE extends LoginStatic{

	private Axe ax = new Axe();
	private static final Logger logger = LogManager.getLogger();
	static DataMaintenanceMap dm;
	private boolean createJsonReport = true;

	@Rule
	public TestName name = new TestName();

	/** Updated: 21/02/2023. Test suite to run Axe accessibility test across System Maintenance pages of ADS.  Each new page that is added
	 *  should be contained in its own atTest method.  
	 * @throws Exception */
	@BeforeClass
	public static void setupScript() throws Exception,Throwable {
		ExtentReport.reportCreate("SystemMaintenanceAXE", "webdriver.globalscripts.accessibilitytests", "SystemMaintenanceAXE");
		try {
			dm = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			logger.info(SystemMaintenanceAXE.class.getSimpleName());
			loginUser(Users.AppSupportUser);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testSystemMaintenanceTabTerminalServerSessionsPage() throws InterruptedException,Throwable {
		try {
			goToPage("Terminal Server Sessions");
			waitForAjaxExtJs();
			ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName(), this.getClass().getSimpleName());
			doClosePageOnLowerBar("Terminal Server...");
			ExtentReport.logPass("PASS", "testSystemMaintenanceTabTerminalServerSessionsPage");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "testSystemMaintenanceTabTerminalServerSessionsPage", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void testSystemMaintenanceTabCustomizeTaskListsPage() throws InterruptedException,Throwable {
		try {
			goToPage("Customize Task Lists");
			waitForAjaxExtJs();
			ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName(), this.getClass().getSimpleName());
			doClosePageOnLowerBar("Customize Task Lists");
			ExtentReport.logPass("PASS", "testSystemMaintenanceTabCustomizeTaskListsPage");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "testSystemMaintenanceTabCustomizeTaskListsPage", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void testSystemMaintenanceTabCustomizeMaintainDataPage() throws InterruptedException,Throwable {
		try {
			goToPage("Customize Maintain Data");
			waitForSpinnerToEnd();
			waitForAjaxExtJs();
			ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName(), this.getClass().getSimpleName());
			doClosePageOnLowerBar("Customize Maintain Data");
			ExtentReport.logPass("PASS", "testSystemMaintenanceTabCustomizeMaintainDataPage");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "testSystemMaintenanceTabCustomizeMaintainDataPage", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void testSystemMaintenanceTabGeneralSettingsPage() throws InterruptedException,Throwable {
		try {
			goToPage("General Settings");
			waitForAjaxExtJs();
			ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName(), this.getClass().getSimpleName());
			doClosePageOnLowerBar("General Settings");
			ExtentReport.logPass("PASS", "testSystemMaintenanceTabGeneralSettingsPage");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "testSystemMaintenanceTabGeneralSettingsPage", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void testSystemMaintenanceTabSecuritySettingsPage() throws InterruptedException,Throwable {
		try {
			goToPage("Security Settings");
			waitForAjaxExtJs();
			ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName(), this.getClass().getSimpleName());
			doClosePageOnLowerBar("Security Settings");
			ExtentReport.logPass("PASS", "testSystemMaintenanceTabSecuritySettingsPage");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "testSystemMaintenanceTabSecuritySettingsPage", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void testSystemMaintenanceTabRolesPage() throws InterruptedException,Throwable {
		try {
			goToPage("Roles");
			waitForAjaxExtJs();
			ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName(), this.getClass().getSimpleName());
			doClosePageOnLowerBar("Roles");
			ExtentReport.logPass("PASS", "testSystemMaintenanceTabRolesPage");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "testSystemMaintenanceTabRolesPage", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void testSystemMaintenanceTabUsersPage() throws InterruptedException,Throwable {
		try {
			goToPage("Users");
			waitForAjaxExtJs();
			ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName(), this.getClass().getSimpleName());
			doClosePageOnLowerBar("Users");
			ExtentReport.logPass("PASS", "testSystemMaintenanceTabUsersPage");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "testSystemMaintenanceTabUsersPage", driver, e);
			fail(e.getMessage());
		}


	}
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}	
}
