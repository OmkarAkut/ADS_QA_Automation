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
import webdriver.maps.ContractingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;
import webdriver.utilities.Axe;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContractingAXE extends LoginStatic {

  private Axe ax = new Axe();
  private static final Logger logger = LogManager.getLogger();
  static ContractingMap dm;
  private boolean createJsonReport = true;

  @Rule
  public TestName name = new TestName();

  /** Updated: 22/02/2023. Test suite to run Axe accessibility test across all Contracting pages of ADS.  Each new page that is added
   *  should be contained in its own atTest method.  
 * @throws Exception */
  @BeforeClass
  public static void setupScript() throws Exception,Throwable {
	  ExtentReport.reportCreate("ContractingAXE", "webdriver.globalscripts.accessibilitytests", "ContractingAXE");
    try {
		dm = BuildMap.getInstance(driver, ContractingMap.class);
		logger.info(ContractingAXE.class.getSimpleName());
//		loginUser(Users.AppSupportUser);
//		Omkar : 27/2/2023 : Login user changed to admin as all menu options were not available for earlier used user
		loginUser(Users.AutomationTesterAdmin);
		ExtentReport.logPass("PASS", "setupScript");
	} catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
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
  public void testContractingTabContractModelPage() throws InterruptedException,Throwable {
  try {
	  goToPage("contract models");
	    waitForAjaxExtJs();
	    ax.runAxeAccessibilityTestOfPage(driver,name.getMethodName());
	    doClosePageOnLowerBar("Model Library");
	    ExtentReport.logPass("PASS", "testContractingTabContractModelPage");
	}
	catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "testContractingTabContractModelPage", driver, e);
		fail(e.getMessage());
	}
    
  }
  
  @Test
  public void testContractingTabContractingDataMaintenancePage() throws InterruptedException,Throwable {
  try {
	  goToPage("contracting data maintenance");
	    waitForAjaxExtJs();
	    ax.runAxeAccessibilityTestOfPage(driver,name.getMethodName());
	    doClosePageOnLowerBar("Maintain Data");
	    ExtentReport.logPass("PASS", "testContractingTabContractingDataMaintenancePage");
	}
	catch (Exception|AssertionError e) {
		ExtentReport.logFail("FAIL", "testContractingTabContractingDataMaintenancePage", driver, e);
		fail(e.getMessage());
	}
    
  }
  @AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}
}

