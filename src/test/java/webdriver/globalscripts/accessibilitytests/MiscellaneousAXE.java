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
public class MiscellaneousAXE extends LoginStatic {

	private Axe ax = new Axe();
	private static final Logger logger = LogManager.getLogger();
	static DataMaintenanceMap dm;
	private boolean createJsonReport = true;

	@Rule
	public TestName name = new TestName();

	/** Updated: 22/02/2023. Test suite to run Axe accessibility test across all miscellaneous pages of ADS.  Each new page that is added
	 *  should be contained in its own atTest method.  
	 * @throws Exception */
	@BeforeClass
	public static void setupScript() throws Exception,Throwable {
		ExtentReport.reportCreate("MiscellaneousAXE", "webdriver.globalscripts.accessibilitytests", "MiscellaneousAXE");
		try {
			dm = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			logger.info(MiscellaneousAXE.class.getSimpleName());
			loginUser(Users.AppSupportUser);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void test01LandingPageAccessibilityTest() throws InterruptedException,Throwable {
		try {
			Thread.sleep(1000);
			waitForSpinnerToEnd();
			waitForAjaxExtJs();
			ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName());
			ExtentReport.logPass("PASS", "test01LandingPageAccessibilityTest");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test01LandingPageAccessibilityTest", driver, e);
			fail(e.getMessage());
		}
	}

	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}

}
