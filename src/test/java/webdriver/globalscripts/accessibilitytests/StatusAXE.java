package webdriver.globalscripts.accessibilitytests;

import static org.junit.Assert.fail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import ExtentReport.ExtentReport;
import webdriver.globalstatic.LoginStatic;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;
import webdriver.utilities.Axe;

public class StatusAXE extends LoginStatic{
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
		ExtentReport.reportCreate("StatusAXE", "webdriver.globalscripts.accessibilitytests", "StatusAXE");
		try {
			dm = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			logger.info(StatusAXE.class.getSimpleName());
			loginUser(Users.AppSupportUser);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}


	@Test
	public void testStatusTabCalculationStatusPage() throws InterruptedException,Throwable {

		try {
			goToPage("Calculation Status");
			waitForAjaxExtJs();
			ax.runAxeAccessibilityTestOfPage(driver,name.getMethodName());
			doClosePageOnLowerBar("Calculation Status");
			ExtentReport.logPass("PASS", "testStatusTabCalculationStatusPage");
		}
		catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "testStatusTabCalculationStatusPage", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void testStatusTabImportExportStatusPage() throws InterruptedException,Throwable {
		try {
			goToPage("Import/Export Status");
			waitForAjaxExtJs();
			ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName());
			doClosePageOnLowerBar("Import/Export Status");
			ExtentReport.logPass("PASS", "testStatusTabImportExportStatusPage");  
		}

		catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "testStatusTabImportExportStatusPage", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void testStatusTabUtilityStatusPage() throws InterruptedException,Throwable {
		try {
			goToPage("Utility Status");
			waitForAjaxExtJs();
			ax.runAxeAccessibilityTestOfPage(driver,name.getMethodName());
			doClosePageOnLowerBar("Utility Status");
			ExtentReport.logPass("PASS", "testStatusTabUtilityStatusPage");  
		}
		catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "testStatusTabUtilityStatusPage", driver, e);
			fail(e.getMessage());
		} 
	}
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
