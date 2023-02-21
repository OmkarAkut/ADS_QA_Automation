package webdriver.globalscripts.accessibilitytests_old;

import static org.junit.Assert.fail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Order;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import net.bytebuddy.build.Plugin.Factory.UsingReflection.Priority;
import webdriver.globalstatic.LoginStatic;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;
import webdriver.utilities.Axe;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class AdsAccessibilityCostingSuite extends LoginStatic {

  private Axe ax = new Axe();
  private static final Logger logger = LogManager.getLogger();
  static DataMaintenanceMap dm;

  @Rule
  public TestName name = new TestName();

  /** Updated: 7-8-2020. Test suite to run Axe accessibility test across all pages of Costing.  Each new page that is added
   *  should be contained in its own atTest method.  
 * @throws Exception */
  
  @BeforeClass
  public static void setupScript() throws Exception,Throwable {
	 ExtentReport.reportCreate("AdsAccessibilityCostingSuite", "webdriver.globalscripts.accessibilitytests", "AdsAccessibilityCostingSuite");
    try {
		dm = BuildMap.getInstance(driver, DataMaintenanceMap.class);
		logger.info(AdsAccessibilityCostingSuite.class.getSimpleName());
		
		//modified by Omkar on 26/5/22 as only aadmin user is available for qa3 env
		//loginUser(Users.AutomationTester1);
		
		loginUser(Users.AutomationTesterAdmin);
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
	ExtentReport.logFail("FAIL", "Failure in setupScript ", driver, e);
	fail(e.getMessage());
	}
 // End of modification
  }

//  @Test
  //venkat updated method name
  //public void testCostingxCostModelScenarioCalculation() throws InterruptedException,Throwable {
  public void testCostingxZCostModelScenarioCalculation() throws InterruptedException,Throwable {
    try {
		goToPage("Cost Model Scenario Calculation");
		
		waitForAjaxExtJs();
		Thread.sleep(5000);
		ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName());
		doClosePageOnLowerBar("Cost Model...");
		ExtentReport.logPass("PASS", "testCostingxCostModelScenarioCalculation");
	} catch (Exception|AssertionError e) {
	ExtentReport.logFail("FAIL", "testCostingxCostModelScenarioCalculation", driver,e);
	fail(e.getMessage());
	}
  }

  @Test
  
  public void testCostingxRvuMaintenance() throws InterruptedException,Throwable{
    try {
		goToPage("RVU Maintenance");
		
		waitForAjaxExtJs();
		ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName());
		doClosePageOnLowerBar("RVU Maintenance");
		
		
		ExtentReport.logPass("PASS", "testCostingxRvuMaintenance");
	} catch (Exception|AssertionError e) {
	ExtentReport.logFail("FAIL", "testCostingxRvuMaintenance", driver, e);
	fail(e.getMessage());
	}
  }

  @Test
  public void testCostingxUnitCostQuickCalculation() throws InterruptedException,Throwable {
    try {
		goToPage("Unit Cost Quick Calculation");
		waitForAjaxExtJs();
		ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName());
		doClosePageOnLowerBar("Unit Cost Quick...");
		ExtentReport.logPass("PASS", "testCostingxUnitCostQuickCalculation");
	} catch (Exception|AssertionError e) {
	ExtentReport.logFail("FAIL", "testCostingxUnitCostQuickCalculation", driver, e);
	fail(e.getMessage());
	}
  }
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}
