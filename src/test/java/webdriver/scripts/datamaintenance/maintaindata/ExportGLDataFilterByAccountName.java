package webdriver.scripts.datamaintenance.maintaindata;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import ExtentReport.ExtentReport;
import webdriver.core.Login;
import webdriver.helpers.CalculationHelper;
import webdriver.maps.ContractingMap;
import webdriver.maps.CostingMap;
import webdriver.maps.DataMaintenanceMap;
import webdriver.maps.mapbuilder.BuildMap;
import webdriver.scripts.costing.RVUExportImportFunction;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExportGLDataFilterByAccountName extends CalculationHelper{
	static DataMaintenanceMap dmMap;
	static CostingMap costing;
	static ContractingMap contracting;
	final static String aTozPageGLDataScenario="GL Data Scenarios";
	static String glDataScenario="* MHFY05 Reclass TB";
	static String[] filter= {"Name","Is","Equal To",glDataScenario};
	static String[] filterByAccountName= {"Account Name","Is","Contains","BAD"};
	/** Customer Issue: Automated test script for ADS-18678 */
	@BeforeClass
	public static void setupScript() throws Exception, Throwable {
		ExtentReport.reportCreate("ExportGLDataFilterByAccountName",
				"webdriver.scripts.datamaintenance.maintaindata", "ExportGLDataFilterByAccountName");
		try {
			dmMap = BuildMap.getInstance(driver, DataMaintenanceMap.class);
			costing = BuildMap.getInstance(driver, CostingMap.class);
			contracting=BuildMap.getInstance(driver, ContractingMap.class);
			Login.loginUser("AutomationTesterAdmin");
			goToPage("Maintain Data");
			selectMaintainDataAtoZ(aTozPageGLDataScenario);
			waitForSpinnerToEnd();
			waitForAjaxExtJs();
			ExtentReport.logPass("PASS", "setupScript");
		} catch (Exception | AssertionError e) {
			ExtentReport.logFail("FAIL", "Failure in setupScript", driver, e);
			fail(e.getMessage());
		}
	}
	@Test
	public void test01ExportGLData_ADS_18678() throws Throwable {
		try {
			doClick(DataMaintenanceMap.getazFilterBtn());
			doFilterCreate(filter);
			doClick(DataMaintenanceMap.getazEditBtn());
			doClick(DataMaintenanceMap.getglDataFilterBtn());
			doFilterCreate(filterByAccountName);
			doClick(DataMaintenanceMap.getglDataExportBtn());
			waitForPageTitle("Export Data");
			RVUExportImportFunction.selectFileLocAndaddFileName(DataMaintenanceMap.getglDataWindowExportBtn());
			doClick(DataMaintenanceMap.getazCancelCloseBtn());
			ExtentReport.logPass("PASS", "test01ExportGLData_ADS_18678");
		} catch (Exception|AssertionError e) {
			ExtentReport.logFail("FAIL", "test01CreateAdHocStatsMaster_ADS_18798", driver, e);
			
		} 
	}
	@AfterClass
	public static void endtest() throws Exception {
		ExtentReport.report.flush();
	}
}
