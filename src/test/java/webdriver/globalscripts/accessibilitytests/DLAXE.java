package webdriver.globalscripts.accessibilitytests;

import static org.junit.Assert.fail;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import ExtentReport.ExtentReport;
import webdriver.globalstatic.LoginStatic;
import webdriver.maps.AnalyticsMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;
import webdriver.utilities.Axe;

public class DLAXE extends LoginStatic{

	private Axe ax = new Axe();
	private static final Logger logger = LogManager.getLogger();
	static DataMaintenanceMap am;
	private boolean createJsonReport = true;

	@Rule
	public TestName name = new TestName();

	/** Updated: 23/02/2023. Test suite to run Axe accessibility test across all sub tabs under Analytics tab of ADS.  Each new page that is added
	 *  should be contained in its own atTest method.  
	 * @throws Exception */
	@BeforeClass
	public static void setupScript() throws Exception,Throwable {
		ExtentReport.reportCreate("AnalyticsAXE", "webdriver.globalscripts.accessibilitytests", "AnalyticsAXE");
		try {
			am = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			logger.info(DLAXE.class.getSimpleName());
//			loginUser(Users.AppSupportUser);
//			Omkar : 1/3/2023 : Login user changed to admin as all menu options were not available for earlier used user
			loginUser(Users.AutomationTesterAdmin);
			
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}



	@Test
	public void testAnalyticsTabAnalyticRefreshScenariosPage() throws InterruptedException,Throwable {
		try {
			goToPage("data loader");
			waitForAjaxExtJs();
			driverDelay(5000);
//			ax.runAxeAccessibilityTestOfPage(driver,name.getMethodName(), this.getClass().getSimpleName());
//			doClosePageOnLowerBar("Analytic Refresh...");
			   JavascriptExecutor js = (JavascriptExecutor) driver;

		        // Load axe script
		        String axeScript = new String(
		                Files.readAllBytes(Paths.get("E:\\Shilpa\\AffinityDecisionSupport\\AffinityDecisionSupport - CIM\\src\\main\\resources\\axe.min.js"))
		        );

		        js.executeScript(axeScript);

		        // Run axe
		        Object result = js.executeAsyncScript(
		                "var callback = arguments[arguments.length - 1];" +
		                "axe.run().then(function(results) {" +
		                "callback(JSON.stringify(results));" +
		                "});"
		        );
		        String accessibilityResults = result.toString();
		        JSONObject json = new JSONObject(accessibilityResults);

		        JSONArray violations = json.getJSONArray("violations");

		        if (violations.length() == 0) {
		            System.out.println("No Accessibility Violations Found!");
		        } else {

		            System.out.println("Total Violations: " + violations.length());

		            for (int i = 0; i < violations.length(); i++) {

		                JSONObject violation = violations.getJSONObject(i);

		                System.out.println("\n------------------------------------");
		                System.out.println("Rule ID: " + violation.getString("id"));
		                System.out.println("Impact: " + violation.getString("impact"));
		                System.out.println("Description: " + violation.getString("description"));
		                System.out.println("Help: " + violation.getString("help"));

		                JSONArray nodes = violation.getJSONArray("nodes");

		                for (int j = 0; j < nodes.length(); j++) {
		                    JSONObject node = nodes.getJSONObject(j);

		                    System.out.println("  Affected Element: "
		                            + node.getJSONArray("target").get(0));
		                }
		            }
		        }


			ExtentReport.logPass("PASS", "testAnalyticsTabAnalyticRefreshScenariosPage");
		}
		catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "testAnalyticsTabAnalyticRefreshScenariosPage", driver, e);
			fail(e.getMessage());
		}

	}
	/*
	@Test
	public void testAnalyticsTabAnalyticDashboardPage() throws InterruptedException,Throwable {
		try {
			goToPage("analytic dashboards");
			waitForAjaxExtJs();
			String firstHandle = webdriverSwitchToNewWindow(printout);
			ax.runAxeAccessibilityTestOfPage(driver,name.getMethodName(), this.getClass().getSimpleName());
			driver.close();
			driver.switchTo().window(firstHandle);
			ExtentReport.logPass("PASS", "analytic dashboards");
		}
		catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "analytic dashboards", driver, e);
			fail(e.getMessage());
		}

	}
*/
	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}

}
