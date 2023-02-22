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
public class AdsAccessibilityMainPagesSuite extends LoginStatic {

  private Axe ax = new Axe();
  private static final Logger logger = LogManager.getLogger();
  static DataMaintenanceMap dm;
  private boolean createJsonReport = true;

  @Rule
  public TestName name = new TestName();

  /** Updated: 9-11-19. Test suite to run Axe accessibility test across all pages of ADS.  Each new page that is added
   *  should be contained in its own atTest method.  
 * @throws Exception */
  @BeforeClass
  public static void setupScript() throws Exception,Throwable {
	  ExtentReport.reportCreate("AdsAccessibilityMainPagesSuite", "webdriver.globalscripts.accessibilitytests", "AdsAccessibilityMainPagesSuite");
    try {
		dm = BuildMap.getInstance(driver, DataMaintenanceMap.class);
		logger.info(AdsAccessibilityMainPagesSuite.class.getSimpleName());
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

  @Test
  public void testReportingTabIcd9Icd10GemsAnalysisPage() throws InterruptedException,Throwable {
	  try {
		  goToPage("Gems Analysis");
		    waitForAjaxExtJs();
		    ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName());
		    doClosePageOnLowerBar("ICD9/ICD10 GEMs...");
		    ExtentReport.logPass("PASS", "testReportingTabIcd9Icd10GemsAnalysisPage");
		}
		catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "testReportingTabIcd9Icd10GemsAnalysisPage", driver, e);
			fail(e.getMessage());
		} 
    
  }

  @Test
  public void testReportingTabIcd9Icd10GemsInquiryPage() throws InterruptedException,Throwable {
	  try {
		  goToPage("Gems Inquiry");
		    waitForAjaxExtJs();
		    ax.runAxeAccessibilityTestOfPage(driver,name.getMethodName());
		    doClosePageOnLowerBar("ICD9/ICD10 GEMs...");
		    ExtentReport.logPass("PASS", "testReportingTabIcd9Icd10GemsInquiryPage");
		}
		catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "testReportingTabIcd9Icd10GemsInquiryPage", driver, e);
			fail(e.getMessage());
		} 
    
  }

  @Test
  public void testReportingTabReportDateMaintenancePage() throws InterruptedException,Throwable {
	  try {
		  goToPage("Report Date Maintenance");
		  waitForAjaxExtJs();
		  ax.runAxeAccessibilityTestOfPage(driver,name.getMethodName());
		  doClosePageOnLowerBar("Report Date...");
		  ExtentReport.logPass("PASS", "testReportingTabReportDateMaintenancePage");
	  }
	  catch (Exception|AssertionError e) {
		  ExtentReport.logFail("FAIL", "testReportingTabReportDateMaintenancePage", driver, e);
		  fail(e.getMessage());
	  }  
  }

  @Test
  public void testReportingTabReportMenuMaintenancePage() throws InterruptedException,Throwable {
	  try {
		  goToPage("Report Menu Maintenance");
		  waitForAjaxExtJs();
		  ax.runAxeAccessibilityTestOfPage(driver,name.getMethodName());
		  doClosePageOnLowerBar("Report Menu...");
		  ExtentReport.logPass("PASS", "testReportingTabReportMenuMaintenancePage");
	  }
	  catch (Exception|AssertionError e) {
		  ExtentReport.logFail("FAIL", "testReportingTabReportMenuMaintenancePage", driver, e);
		  fail(e.getMessage());
	  }    
  }

  @Test
  public void testReportingTabReportLibraryPage() throws InterruptedException,Throwable {
  try {
	  goToPage("Report Library");
	    waitForAjaxExtJs();
	    ax.runAxeAccessibilityTestOfPage(driver,name.getMethodName());
	    doClosePageOnLowerBar("Report Library");
	    ExtentReport.logPass("PASS", "testReportingTabReportLibraryPage");
	}
	catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "testReportingTabReportLibraryPage", driver, e);
		fail(e.getMessage());
	}
   }

  @Test
  public void testContractingTabApcAllocationPage() throws InterruptedException,Throwable {
  try {
	  goToPage("APC Allocation");
	    waitForAjaxExtJs();
	    ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName());
	    doClosePageOnLowerBar("APC Allocation");
	    ExtentReport.logPass("PASS", "testContractingTabApcAllocationPage");
	}
	catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "testContractingTabApcAllocationPage", driver, e);
		fail(e.getMessage());
	}
    
  }

  @Test
  public void testContractingTabContractualAllowanceExportPage() throws InterruptedException,Throwable {
  try {
	  goToPage("Contractual Allowance Export");
	    waitForAjaxExtJs();
	    ax.runAxeAccessibilityTestOfPage(driver,name.getMethodName());
	    doClosePageOnLowerBar("Contractual...");
	    ExtentReport.logPass("PASS", "testContractingTabContractualAllowanceExportPage");
	}
	catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "testContractingTabContractualAllowanceExportPage", driver, e);
		fail(e.getMessage());
	}
    
  }


  @Test
  public void testDataMaintenancexUtilities() throws InterruptedException,Throwable {
  try {
	  goToPage("Utilities");
	    waitForAjaxExtJs();
	    ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName());
	    doClosePageOnLowerBar("Utilities");
	    ExtentReport.logPass("PASS", "testDataMaintenancexUtilities");
	}
	catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "testDataMaintenancexUtilities", driver, e);
		fail(e.getMessage());
	}
    
  }

  @Test
  public void testSystemMaintenanceTabTerminalServerSessionsPage() throws InterruptedException,Throwable {
    try {
		goToPage("Terminal Server Sessions");
		waitForAjaxExtJs();
		ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName());
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
		ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName());
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
		ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName());
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
		ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName());
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
		ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName());
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
		ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName());
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
		ax.runAxeAccessibilityTestOfPage(driver, name.getMethodName());
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
//  @Test
//  public void testStatusTabCalculationStatusPage() throws InterruptedException,Throwable {
//    goToPage("Calculation Status");
//    waitForAjaxExtJs();
//    ax.runAxeAccessibilityTestOfPage(driver, logger, name.getMethodName());
//    doClosePageOnLowerBar("Calculation Status");
//  }
//
//  @Test
//  public void testStatusTabImportExportStatusPage() throws InterruptedException,Throwable {
//    goToPage("Import/Export Status");
//    waitForAjaxExtJs();
//    ax.runAxeAccessibilityTestOfPage(driver, logger, name.getMethodName());
//    doClosePageOnLowerBar("Import/Export Status");
//  }
//
//  @Test
//  public void testStatusTabUtilityStatusPage() throws InterruptedException,Throwable {
//    goToPage("Utility Status");
//    waitForAjaxExtJs();
//    ax.runAxeAccessibilityTestOfPage(driver, logger, name.getMethodName());
//    doClosePageOnLowerBar("Utility Status");
//  }
}


public class MiscellaneousAXE {

}
