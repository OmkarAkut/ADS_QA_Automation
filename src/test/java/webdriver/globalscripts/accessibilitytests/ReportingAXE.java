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
import webdriver.maps.ReportingMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.users.Users;
import webdriver.utilities.Axe;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ReportingAXE extends LoginStatic {

	private Axe ax = new Axe();
	private static final Logger logger = LogManager.getLogger();
	static ReportingMap dm;
	private boolean createJsonReport = true;

	@Rule
	public TestName name = new TestName();

	/** Updated: 22/02/2023. Test suite to run Axe accessibility test across all subtans under Reporting tab of ADS.  Each new page that is added
	 *  should be contained in its own atTest method.  
	 * @throws Exception */
	@BeforeClass
	public static void setupScript() throws Exception,Throwable {
		ExtentReport.reportCreate("ReportingAXE", "webdriver.globalscripts.accessibilitytests", "ReportingAXE");
		try {
			dm = BuildMap.getInstance(driver, ReportingMap.class);
			logger.info(ReportingAXE.class.getSimpleName());
			loginUser(Users.AppSupportUser);
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
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

	@AfterClass
	public static void endtest() throws Exception {

		ExtentReport.report.flush();

	}

}
