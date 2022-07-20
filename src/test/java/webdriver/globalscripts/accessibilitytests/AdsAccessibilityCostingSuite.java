package webdriver.globalscripts.accessibilitytests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;
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
   *  should be contained in its own atTest method.  */
  @BeforeClass
  public static void setupScript() {
    dm = BuildMap.getInstance(driver, DataMaintenanceMap.class);
    logger.info(AdsAccessibilityCostingSuite.class.getSimpleName());
    
    /*modified by Omkar on 26/5/22 as only aadmin user is available for qa3 env
    loginUser(Users.AutomationTester1);
    */
    loginUser(Users.AutomationTesterAdmin);
 // End of modification
  }

  @Test
  public void testCostingxCostModelScenarioCalculation() throws InterruptedException {
    goToPage("Cost Model Scenario Calculation");
    waitForAjaxExtJs();
    ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName());
    doClosePageOnLowerBar("Cost Model...");
  }

  @Test
  public void testCostingxRvuMaintenance() throws InterruptedException {
    goToPage("RVU Maintenance");
    waitForAjaxExtJs();
    ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName());
    doClosePageOnLowerBar("RVU Maintenance");
  }

  @Test
  public void testCostingxUnitCostQuickCalculation() throws InterruptedException {
    goToPage("Unit Cost Quick Calculation");
    waitForAjaxExtJs();
    ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName());
    doClosePageOnLowerBar("Unit Cost Quick...");
  }
}
