package webdriver.globalscripts.accessibilitytests;

import static org.junit.Assert.fail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.core.config.Order;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
//import net.bytebuddy.build.Plugin.Factory.UsingReflection.Priority;
import webdriver.globalstatic.LoginStatic;
import webdriver.maps.CostingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.ModelLibraryMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;
import webdriver.utilities.Axe;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)


public class EpisodesAXE extends LoginStatic {

	private Axe ax = new Axe();
	private static final Logger logger = LogManager.getLogger();
	static ModelLibraryMap dm;

	@Rule
	public TestName name = new TestName();

	/** Updated: 2-Mar-2023. Test suite to run Axe accessibility test across all pages of Episodes.  Each new page that is added
	 *  should be contained in its own atTest method.  
	 * @throws Exception */

	@BeforeClass
	public static void setupScript() throws Exception,Throwable {
		ExtentReport.reportCreate("EpisodesAXE", "webdriver.globalscripts.accessibilitytests", "EpisodesAXE");
		try {
			dm = BuildMap.getInstance(driver, ModelLibraryMap.class);
			logger.info(CostingAXE.class.getSimpleName());			
			loginUser(Users.AutomationTester1);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript ", driver, e);
			fail(e.getMessage());
		}
	}

	@Test
	public void testEpisodeModels() throws InterruptedException,Throwable{
		try {
			goToPage("Episode Models");
			waitForAjaxExtJs();
			ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName());
			doClosePageOnLowerBar("Model Library");
			ExtentReport.logPass("PASS", "testEpisodeModels");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "testEpisodeModels", driver, e);
			fail(e.getMessage());
		}
	}



}
