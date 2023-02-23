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

public class AnalyticsAXE extends LoginStatic{

	private Axe ax = new Axe();
	private static final Logger logger = LogManager.getLogger();
	static DataMaintenanceMap dm;
	private boolean createJsonReport = true;

	@Rule
	public TestName name = new TestName();

	/** Updated: 23/02/2023. Test suite to run Axe accessibility test across all sub tabs under Analytics tab of ADS.  Each new page that is added
	 *  should be contained in its own atTest method.  
	 * @throws Exception */
	@BeforeClass
	public static void setupScript() throws Exception,Throwable {
		ExtentReport.reportCreate("ReportingAXE", "webdriver.globalscripts.accessibilitytests", "ReportingAXE");
		try {
			dm = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			logger.info(ReportingAXE.class.getSimpleName());
			loginUser(Users.AppSupportUser);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}



	@Test
	public void testAnalyticsTabAnalyticRefreshScenariosPage() throws InterruptedException,Throwable {
		try {
			goToPage("Analytic Refresh Scenarios");
			waitForAjaxExtJs();
			ax.runAxeAccessibilityTestOfPage(driver,name.getMethodName());
			doClosePageOnLowerBar("Analytic Refresh...");
			ExtentReport.logPass("PASS", "testAnalyticsTabAnalyticRefreshScenariosPage");
		}
		catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "testAnalyticsTabAnalyticRefreshScenariosPage", driver, e);
			fail(e.getMessage());
		}

	}

	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}

}
