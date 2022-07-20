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
public class AdsAccessibilityMainPagesSuite extends LoginStatic {

  private Axe ax = new Axe();
  private static final Logger logger = LogManager.getLogger();
  static DataMaintenanceMap dm;
  private boolean createJsonReport = true;

  @Rule
  public TestName name = new TestName();

  /** Updated: 9-11-19. Test suite to run Axe accessibility test across all pages of ADS.  Each new page that is added
   *  should be contained in its own atTest method.  */
  @BeforeClass
  public static void setupScript() {
    dm = BuildMap.getInstance(driver, DataMaintenanceMap.class);
    logger.info(AdsAccessibilityMainPagesSuite.class.getSimpleName());
    loginUser(Users.AppSupportUser);
  }

  @Test
  public void test01LandingPageAccessibilityTest() throws InterruptedException {
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName());
  }

//  @Test
//  public void testAnalyticsTabAnalyticRefreshScenariosPage() throws InterruptedException {
//    goToPage("Analytic Refresh Scenarios");
//    waitForAjaxExtJs();
//    ax.runAxeAccessibilityTestOfPage(driver, logger, name.getMethodName());
//    doClosePageOnLowerBar("Analytic Refresh...");
//  }
//
//  @Test
//  public void testReportingTabIcd9Icd10GemsAnalysisPage() throws InterruptedException {
//    goToPage("Gems Analysis");
//    waitForAjaxExtJs();
//    ax.runAxeAccessibilityTestOfPage(driver, logger, name.getMethodName());
//    doClosePageOnLowerBar("ICD9/ICD10 GEMs...");
//  }
//
//  @Test
//  public void testReportingTabIcd9Icd10GemsInquiryPage() throws InterruptedException {
//    goToPage("Gems Inquiry");
//    waitForAjaxExtJs();
//    ax.runAxeAccessibilityTestOfPage(driver, logger, name.getMethodName());
//    doClosePageOnLowerBar("ICD9/ICD10 GEMs...");
//  }
//
//  @Test
//  public void testReportingTabReportDateMaintenancePage() throws InterruptedException {
//    goToPage("Report Date Maintenance");
//    waitForAjaxExtJs();
//    ax.runAxeAccessibilityTestOfPage(driver, logger, name.getMethodName());
//    doClosePageOnLowerBar("Report Date...");
//  }
//
//  @Test
//  public void testReportingTabReportMenuMaintenancePage() throws InterruptedException {
//    goToPage("Report Menu Maintenance");
//    waitForAjaxExtJs();
//    ax.runAxeAccessibilityTestOfPage(driver, logger, name.getMethodName());
//    doClosePageOnLowerBar("Report Menu...");
//  }
//
//  @Test
//  public void testReportingTabReportLibraryPage() throws InterruptedException {
//    goToPage("Report Library");
//    waitForAjaxExtJs();
//    ax.runAxeAccessibilityTestOfPage(driver, logger, name.getMethodName());
//    doClosePageOnLowerBar("Report Library");
//  }
//
//  @Test
//  public void testContractingTabApcAllocationPage() throws InterruptedException {
//    goToPage("APC Allocation");
//    waitForAjaxExtJs();
//    ax.runAxeAccessibilityTestOfPage(driver, logger, name.getMethodName());
//    doClosePageOnLowerBar("APC Allocation");
//  }
//
//  @Test
//  public void testContractingTabContractualAllowanceExportPage() throws InterruptedException {
//    goToPage("Contractual Allowance Export");
//    waitForAjaxExtJs();
//    ax.runAxeAccessibilityTestOfPage(driver, logger, name.getMethodName());
//    doClosePageOnLowerBar("Contractual...");
//  }
//
//  @Test
//  public void testCostingTabCostModelScenarioCalculationPage() throws InterruptedException {
//    goToPage("Cost Model Scenario Calculation");
//    waitForAjaxExtJs();
//    ax.runAxeAccessibilityTestOfPage(driver, logger, name.getMethodName());
//    doClosePageOnLowerBar("Cost Model...");
//  }

//  @Test
//  public void testCostingxRvuMaintenance() throws InterruptedException {
//    goToPage("RVU Maintenance");
//    waitForAjaxExtJs();
//    ax.runAxeTestOfPageJsonReport(driver, name.getMethodName());
//    //ax.runAxeAccessibilityTestOfPage(driver, logger, name.getMethodName());
//    doClosePageOnLowerBar("RVU Maintenance");
//  }
//
//  @Test
//  public void testCostingxUnitCostQuickCalculation() throws InterruptedException {
//    goToPage("Unit Cost Quick Calculation");
//    waitForAjaxExtJs();
//    ax.runAxeTestOfPageJsonReport(driver, name.getMethodName());
//    //ax.runAxeAccessibilityTestOfPage(driver, logger, name.getMethodName());
//    doClosePageOnLowerBar("Unit Cost Quick...");
//  }

//  @Test
//  public void testDataMaintenancexUtilities() throws InterruptedException {
//    goToPage("Utilities");
//    waitForAjaxExtJs();
////    doClick(dm.getUtilitiesPageEncountersWithNoChargesReport());
////    waitForAjaxExtJs();
//    //ax.runAxeTestOnPage(driver, name.getMethodName(), createJsonReport);
//    ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName());
//    doClosePageOnLowerBar("Utilities");
//  }
//
  @Test
  public void testSystemMaintenanceTabTerminalServerSessionsPage() throws InterruptedException {
    goToPage("Terminal Server Sessions");
    waitForAjaxExtJs();
    ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName());
    doClosePageOnLowerBar("Terminal Server...");
  }

  @Test
  public void testSystemMaintenanceTabCustomizeTaskListsPage() throws InterruptedException {
    goToPage("Customize Task Lists");
    waitForAjaxExtJs();
    ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName());
    doClosePageOnLowerBar("Customize Task Lists");
  }

  @Test
  public void testSystemMaintenanceTabCustomizeMaintainDataPage() throws InterruptedException {
    goToPage("Customize Maintain Data");
    waitForSpinnerToEnd();
    waitForAjaxExtJs();
    ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName());
    doClosePageOnLowerBar("Customize Maintain Data");
  }

  @Test
  public void testSystemMaintenanceTabGeneralSettingsPage() throws InterruptedException {
    goToPage("General Settings");
    waitForAjaxExtJs();
    ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName());
    doClosePageOnLowerBar("General Settings");
  }

  @Test
  public void testSystemMaintenanceTabSecuritySettingsPage() throws InterruptedException {
    goToPage("Security Settings");
    waitForAjaxExtJs();
    ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName());
    doClosePageOnLowerBar("Security Settings");
  }

  @Test
  public void testSystemMaintenanceTabRolesPage() throws InterruptedException {
    goToPage("Roles");
    waitForAjaxExtJs();
    ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName());
    doClosePageOnLowerBar("Roles");
  }

  @Test
  public void testSystemMaintenanceTabUsersPage() throws InterruptedException {
    goToPage("Users");
    waitForAjaxExtJs();
    ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName());
    doClosePageOnLowerBar("Users");
  }

//  @Test
//  public void testStatusTabCalculationStatusPage() throws InterruptedException {
//    goToPage("Calculation Status");
//    waitForAjaxExtJs();
//    ax.runAxeAccessibilityTestOfPage(driver, logger, name.getMethodName());
//    doClosePageOnLowerBar("Calculation Status");
//  }
//
//  @Test
//  public void testStatusTabImportExportStatusPage() throws InterruptedException {
//    goToPage("Import/Export Status");
//    waitForAjaxExtJs();
//    ax.runAxeAccessibilityTestOfPage(driver, logger, name.getMethodName());
//    doClosePageOnLowerBar("Import/Export Status");
//  }
//
//  @Test
//  public void testStatusTabUtilityStatusPage() throws InterruptedException {
//    goToPage("Utility Status");
//    waitForAjaxExtJs();
//    ax.runAxeAccessibilityTestOfPage(driver, logger, name.getMethodName());
//    doClosePageOnLowerBar("Utility Status");
//  }
}
